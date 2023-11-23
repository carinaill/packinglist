INSERT INTO destination(id,name) VALUES (destination_seq.nextval, 'Spanien');
INSERT INTO destination(id,name) VALUES (destination_seq.nextval, 'Costa Rica');
INSERT INTO destination(id,name) VALUES (destination_seq.nextval, 'Norwegen');

INSERT INTO item(id,name,destination_id) VALUES (item_seq.nextval, 'Sonnenbrille', (SELECT ID FROM DESTINATION WHERE name='Spanien'));
INSERT INTO item(id,name,destination_id) VALUES (item_seq.nextval, 'Sonnenbrille', (SELECT ID FROM DESTINATION WHERE name='Costa Rica'));
INSERT INTO item(id,name,destination_id) VALUES (item_seq.nextval, 'Winterjacke', (SELECT ID FROM DESTINATION WHERE name='Norwegen'));
INSERT INTO item(id,name,destination_id) VALUES (item_seq.nextval, 'Ski', (SELECT ID FROM DESTINATION WHERE name='Norwegen'));

DELETE FROM destination WHERE name = 'Spanien' AND id=16;