# 📄 TRABAJO: ORDER SERVICE (KUBERNETES)

**Módulo:** 5 

---

curl.exe -s -X POST http://localhost:8081/api/auth/login  -H "Content-Type: application/json" -d '{"email":"juan.perez@example.com","password":"admin123"}'

curl.exe -H "Authorization: Bearer $TOKEN" http://localhost:8081/api/users

---

**NOTA: Cómo desplegar:**

- Compilar el proyecto (si es necesario):

mvn clean package -DskipTests

- Construir imagen Docker:

docker build -t order-service:1.0 .

- Configurar Contexto para Docker Desktop

kubectl config use-context docker-desktop

- Actualizar el Secret con las nuevas variables de entorno codificadas en base64 (en la ruta order-service)

kubectl apply -f k8s\

- Cotejar que pods se han desplegado:

kubectl -n order-service get pods

- Reiniciar el despliegue para aplicar los cambios (opcional, ya que kubectl apply debería manejarlo)

kubectl rollout restart deployment product-service -n product-service

---

**NOTA: Cómo ejecutar peticiones:**
- Se actualizó el Docker Desktop a una version que solo usa KIND o KUBEADMIN para K8s.
- Se utilizó KIND que tiene mapeo por nodos internamente y no es visible las imagenes en el docker desktop directamente, por ello se agregó el "imagePullPolicy: IfNotPresent".
- La otra opción para este comando de port-forward es el mapeo de un ingress.yaml y la instalación de un nginx.

- Ejecutar los sgtes comandos, cada uno en una terminal, no cerrar el terminal para poder ejecutar las invocaciones:


- kubectl port-forward -n user-service svc/user-service 8081:80
- kubectl port-forward -n product-service svc/product-service 8082:80
- kubectl port-forward -n order-service svc/order-service 8083:80

---

## 🎯 OBJETIVO

Desarrollar un microservicio de **Gestión de Órdenes (Order Service)** que se integre con el microservicio **Product Service** y se despliegue localmente en Kubernete.

---

## 📋 DESCRIPCIÓN

En una arquitectura de microservicios para un sistema de pedidos de comida desde diferentes restaurantes, se requiere implementar el servicio de gestion de pedidos y coordinacion de entregas.
Los servicios principales son:

1. **Servicios de usuarios** permite registrar datos de cliente y direccion de entrega.
también gestiona los usuarios de restaurantes que pueden modificar el catalogo de productos del restaurante.
2. **Servicios de Catálogos** registra y actualiza el catalogo de restaurantes y sus direcciones, el stock de platos disponibles. 
3. **Servicios de Pedidos** gestiona la creación y seguimiento de pedidos.
4. **Servicio de Pagos** registra y actualiza los pagos realizados por pedidos y actualiza el estado del pedido a pago verificado o pago anulado.
5. **Servicios de Entregas** registra la traza del envio y actualiza el estado del pedido a entregado o no se entrego.

---

## 🏗️ ARQUITECTURA DEL SISTEMA

### Arquitectura Completa
```
┌─────────────────────────────────────────────────────────────┐
│               ARQUITECTURA COMPLETA            │
└─────────────────────────────────────────────────────────────┘

     ┌────────────┐ ┌────────────┐ ┌────────────┐ ┌────────────┐ ┌────────────┐
     │   User     │ │  Catalog   │ │   Order    │ │  Payment   │ │  Delivery  │
     │  Service   │ │  Service   │ │  Service   │ │  Service   │ │  Service   │
     │   :8081    │ │   :8082    │ │   :8083    │ │   :8084    │ │   :8085    │
     └──────┬─────┘ └──────┬─────┘ └──────┬─────┘ └──────┬─────┘ └──────┬─────┘
            │              │              │              |              |
            │              │              │              |              |
            ▼              ▼              ▼              ▼              ▼
       ┌────────┐     ┌─────────┐    ┌────────┐   ┌──────────┐    ┌───────────┐
       │userdb  │     │catalogdb│    │orderdb │   │paymentdb │    │deliverydb │ 
       │ :5434  │     │ :5440   │    │ :5442  │   │ :5443    │    │ :5444     │
       └────────┘     └─────────┘    └────────┘   └──────────┘    └───────────┘

COMUNICACIÓN:
Catalog Service ──(HTTP )──► User Service (validación del rol y estado para actualizar inventario)
Order Service ──(HTTP )──► Catalog Service (verificación y actualización de stock)
Order Service ──(Kafka )──► Payment Service (verificación y actualización de orden)
Order Service ──(Kafka )──► Delivery Service (verificación y actualización de envío)
```

### Flujo de Datos
```
┌─────────────────────────────────────────────────────────────┐
│  FLUJO: Crear Orden                                         │
└─────────────────────────────────────────────────────────────┘

User (cliente)
  │
  │ POST /api/orders
  │ { userId: 1, items: [...] }
  ▼
Order Service
  │
  ├─----------------------─► Product Service
  │                          GET /api/products/1
  │                          ✅ Producto válido + precio
  │
  ├─► Calcular totales
  │   quantity × unit_price = subtotal
  │   Σ subtotals = total_amount
  │
  ├─► Guardar en orderdb
  │   INSERT INTO orders (...)
  │   INSERT INTO order_items (...)
  │
  ▼
Respuesta 201 Created
{
  "id": 1,
  "orderNumber": "ORD-2025-001",
  "user": { ... },
  "items": [ ... ],
  "totalAmount": 2599.98
}
```

---

## 📊 MODELO DE DATOS

### Diagrama Entidad-Relación
```
┌─────────────────────────────┐
│            ORDERS           │
├─────────────────────────────┤
│ PK  id                      │
│     order_number (UNIQUE)   │
│     user_id                 │
│     status                  │
│     total_amount            │
│     created_at              │
│     updated_at              │
└─────────────┬───────────────┘
              │ 1      
              │         
              │                         
              │ N                        
              ▼                        
┌─────────────────────────────┐
│        ORDER_ITEMS          │
├─────────────────────────────┤
│ PK  id                      │
│ FK  order_id                │
│     product_id              │
│     quantity                │
│     unit_price              │
│     subtotal                │
└─────────────────────────────┘
                               
     product_id    ────────────────────┐
                                       │
        user_id    ──────────┐         │
                             │         │
                             ▼         ▼
                    User Service   Product Service
                      (userdb)      (productdb)
```

### Tabla: orders

| Campo | Tipo | Restricciones | Descripción |
|-------|------|---------------|-------------|
| `id` | BIGSERIAL | PRIMARY KEY | ID único de la orden |
| `order_number` | VARCHAR(50) | UNIQUE, NOT NULL | Número de orden (ej: ORD-2025-001) |
| `user_id` | BIGINT | NOT NULL | ID del usuario (ref. externa) |
| `status` | VARCHAR(20) | NOT NULL, DEFAULT 'PENDING' | Estado de la orden |
| `total_amount` | NUMERIC(10,2) | NOT NULL, >= 0 | Monto total |
| `created_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() | Fecha de creación |
| `updated_at` | TIMESTAMP | NOT NULL, DEFAULT NOW() | Fecha de actualización |

**Estados válidos:** `PENDING`, `CONFIRMED`, `SHIPPED`, `DELIVERED`, `CANCELLED`

### Tabla: order_items

| Campo | Tipo | Restricciones | Descripción |
|-------|------|---------------|-------------|
| `id` | BIGSERIAL | PRIMARY KEY | ID único del item |
| `order_id` | BIGINT | NOT NULL, FK → orders(id) CASCADE | ID de la orden |
| `product_id` | BIGINT | NOT NULL | ID del producto (ref. externa) |
| `quantity` | INTEGER | NOT NULL, > 0 | Cantidad |
| `unit_price` | NUMERIC(10,2) | NOT NULL, >= 0 | Precio unitario |
| `subtotal` | NUMERIC(10,2) | NOT NULL, >= 0 | Subtotal (qty × price) |

### Script SQL
```sql
-- Tabla de órdenes
CREATE TABLE orders (
    id BIGSERIAL PRIMARY KEY,
    order_number VARCHAR(50) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    total_amount NUMERIC(10, 2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT chk_status CHECK (status IN ('PENDING', 'CONFIRMED', 'SHIPPED', 'DELIVERED', 'CANCELLED')),
    CONSTRAINT chk_total_positive CHECK (total_amount >= 0)
);

CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_orders_status ON orders(status);
CREATE INDEX idx_orders_created_at ON orders(created_at);

-- Tabla de items
CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INTEGER NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL,
    subtotal NUMERIC(10, 2) NOT NULL,
    
    CONSTRAINT fk_order FOREIGN KEY (order_id) 
        REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT chk_quantity_positive CHECK (quantity > 0),
    CONSTRAINT chk_unit_price_positive CHECK (unit_price >= 0),
    CONSTRAINT chk_subtotal_positive CHECK (subtotal >= 0)
);

CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_order_items_product_id ON order_items(product_id);

-- Datos de prueba
INSERT INTO orders (order_number, user_id, status, total_amount) VALUES
('ORD-2025-001', 1, 'CONFIRMED', 2849.97),
('ORD-2025-002', 2, 'PENDING', 1199.98),
('ORD-2025-003', 1, 'SHIPPED', 149.99);

INSERT INTO order_items (order_id, product_id, quantity, unit_price, subtotal) VALUES
(1, 1, 1, 1299.99, 1299.99),
(1, 2, 1, 999.99, 999.99),
(1, 3, 1, 399.99, 399.99),
(2, 4, 1, 799.99, 799.99),
(2, 5, 1, 399.00, 399.00),
(3, 7, 1, 149.99, 149.99);
```

---

## 🎯 REQUERIMIENTOS FUNCIONALES

### RF-01: Crear Orden de Compra

**Endpoint:** `POST /api/orders`

**Request:**
```json
{
  "userId": 1,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    },
    {
      "productId": 3,
      "quantity": 1
    }
  ]
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "orderNumber": "ORD-2025-001",
  "userId": 1,
  "items": [
    {
      "id": 1,
      "product": {
        "id": 1,
        "name": "Laptop Dell XPS 15",
        "price": 1299.99
      },
      "quantity": 2,
      "unitPrice": 1299.99,
      "subtotal": 2599.98
    }
  ],
  "totalAmount": 2999.97,
  "status": "PENDING",
  "createdAt": "2025-01-20T10:30:00",
}
```

**Proceso:**
1. Para cada item:
   - Validar producto en Product Service
   - Obtener precio actual
   - Calcular subtotal
2. Calcular total de la orden
3. Generar número de orden único
4. Guardar en BD
5. Retornar orden completa

---

