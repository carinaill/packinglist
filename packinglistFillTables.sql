INSERT INTO destination(id,name) VALUES (destination_seq.nextval, 'Spanien');
INSERT INTO destination(id,name) VALUES (destination_seq.nextval, 'Costa Rica');
INSERT INTO destination(id,name) VALUES (destination_seq.nextval, 'Norwegen');

INSERT INTO item(id,name,destination_id,done) VALUES (item_seq.nextval, 'Sonnenbrille', (SELECT ID FROM DESTINATION WHERE name='Spanien'),0);
INSERT INTO item(id,name,destination_id,done) VALUES (item_seq.nextval, 'Sonnenbrille', (SELECT ID FROM DESTINATION WHERE name='Costa Rica'),0);
INSERT INTO item(id,name,destination_id,done) VALUES (item_seq.nextval, 'Winterjacke', (SELECT ID FROM DESTINATION WHERE name='Norwegen'),0);
INSERT INTO item(id,name,destination_id,done) VALUES (item_seq.nextval, 'Ski', (SELECT ID FROM DESTINATION WHERE name='Norwegen'),0);
INSERT INTO item(id,name,destination_id,done) VALUES (item_seq.nextval, 'FlipFlops', (SELECT ID FROM DESTINATION WHERE name='Costa Rica'),0);
INSERT INTO item(id,name,destination_id,done) VALUES (item_seq.nextval, 'Handtuch', (SELECT ID FROM DESTINATION WHERE name='Spanien'),1);
INSERT INTO item(id,name,destination_id,done) VALUES (item_seq.nextval, 'Sonnencreme', (SELECT ID FROM DESTINATION WHERE name='Costa Rica'),0);

DELETE FROM destination WHERE name = 'Spanien' AND id=16;