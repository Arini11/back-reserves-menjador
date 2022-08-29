CREATE TABLE categories(
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(20) NOT NULL
);

CREATE TABLE dishes (
	id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(30),
    image blob,
    popularity int DEFAULT '0',
    status boolean DEFAULT '1'
);

CREATE TABLE categories_dishes(
	dish int,
    category int,
    PRIMARY KEY(idDish,idCategory),
    CONSTRAINT FK_categDish FOREIGN KEY (dish) REFERENCES dishes(id) ON UPDATE CASCADE,
    CONSTRAINT FK_categDishCat FOREIGN KEY (category) REFERENCES categories(id) ON UPDATE CASCADE
);

INSERT INTO categories_dishes(dish,category) VALUES 
	(131,51),
    (101,71),
    (111,81),
    (111,61),
    (91,61),
    (81,51);

INSERT INTO categories(name) VALUES 
	('carne'),
    ('pescado'), 
    ('arroz'), 
    ('shushi'), 
    ('pasta'); 
    
INSERT INTO dishes(name) VALUES 
	('solomillo de cerdo ibérico a la brasa'),
    ('calamar a la plancha'),
    ('arroz con setas y gambas'),
    ('tataki de salmón'),
    ('tortellini de trufa con queso azul'),
    ('entrecot a la pimienta');

delete from dishes where id = 131; #no lo borra porque tiene una categoria asiganada / el 1 si lo ha borrado
update dishes set name = 'solomillo de cerdo iberico'  where id = 81; #se actualiza
delete from categories where id = 71; #no deja porque tiene un plato asignado en cat_dish
update categories set status = 0 where name = 'shushi'; #lo hace bien
delete from categories_dishes where dish = 81; #lo borra bien
update categories_dishes set dish = 121 where dish = 111 and category = 61; #no lo permite - crear nuevas relacionesdishes

select * from dishes;
select * from categories;
select * from categories_dishes;
select * from roles;
select * from orders;
select * from orders_dishes;
select * from users;

INSERT INTO Users (username, password, role_id) VALUES ('admin', '$2a$12$CM0oJo/w9DFsye783cEtU.nel7rCzThAQm1uSZJTVVOqMcoDxedpq',1);

