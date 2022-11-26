INSERT INTO producers("name") VALUES ('Intel'),('AMD'),('AeroStar'),('VemaKids');

INSERT INTO products("name",price,producer_id) VALUES ('CoreI5',600,1),('CoreI7',900,1),('Ryzen5',500,2),
('Ryzen7',1000,2),('SRV',1000,3),('FPD1000',1400,3),('Swing',2000,4);

INSERT INTO users(email,password,active)
 VALUES('admin','$2a$12$ujM00D7A1Wjs5uy.a9jDF.UptMN3X2AzQ0TbTG5eQQJSz2FOzXK2a',true)
      ,('user','$2a$12$1IaF.M89I/d90yqKik30d.IGFeMNEQCkLREzobfwLlEEzEsSLTSua',true);

INSERT INTO roles("name") VALUES('ROLE_ADMIN'),('ROLE_USER');