
insert  into restaurants (id, name,type, address, enabled) values(nextval('seq_restaurants'),'El Bigotes', 'Mexican food','Jr los Alamos 312, callao', true);
insert  into restaurants (id, name,type, address, enabled) values(nextval('seq_restaurants'),'Pizza Raul', 'American food','Av peru 320, SMP', true);
commit;

insert into categories (id,  name) values(nextval('seq_categories'), 'Beverages' );
insert into categories (id,  name) values(nextval('seq_categories'), 'Main dish' );
insert into categories (id,  name) values(nextval('seq_categories'), 'Dessert' );
commit;

insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),1,1,'Vaso Agua de Jamaica','ONZ',12.00,40);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),1,1,'Vaso de Michelada','ONZ',28.00,60);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),1,2,'Enchilada de pollo','UND',38.00,35);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),1,2,'Tacos al pastor con cerdo y piña','UND',38.00,25);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),1,3,'Pan de elote con leche condensada','UND',18.00,45);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),1,3,'Cocadas','UND',8.00,65);


insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),2,1,'Botella de CocaCola','UND',8.00,60);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),2,1,'Botella de Sprite','UND',8.00,60);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),2,2,'Slize de Pizza americana','UND',18.00,35);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),2,2,'Dedos de queso','UND',8.00,25);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),2,3,'Pie de Manzana','UND',8.00,45);
insert into products (id, restaurant_id, category_id, name, unitcode, price, stock) values(nextval('seq_products'),2,3,'Galleta de chocochips','UND',3.00,65);

commit;