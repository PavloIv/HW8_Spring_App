INSERT INTO producers("name") VALUES ('Intel'),('AMD'),('AeroStar'),('VemaKids');

INSERT INTO products("name",price,producer_id) VALUES ('CoreI5',600,1),('CoreI7',900,1),('Ryzen5',500,2),
('Ryzen7',1000,2),('SRV',1000,3),('FPD1000',1400,3),('Swing',2000,4);

INSERT INTO users(email,password,active) VALUES('admin','admin',true),('user','user',true);

INSERT INTO roles("name") VALUES('ROLE_ADMIN'),('ROLE_USER');