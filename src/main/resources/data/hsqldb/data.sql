INSERT INTO Building(building_type) VALUES ('QUARTER');
INSERT INTO Building(building_type) VALUES ('RECRUITMENT_POST');
INSERT INTO Building(building_type) VALUES ('NOTICE_BOARD');

INSERT INTO Unit(unit_type, name, level, fk_building_id) VALUES ('RECRUIT', 'Fillefjong', 1, 1)

INSERT INTO Unit_Tile(unit_id, tile_id) VALUES (1, 'ATTACK');
INSERT INTO Unit_Tile(unit_id, tile_id) VALUES (1, 'ATTACK_STAR');
INSERT INTO Unit_Tile(unit_id, tile_id) VALUES (1, 'ATTACK_STAR');
INSERT INTO Unit_Tile(unit_id, tile_id) VALUES (1, 'BLOOD');
INSERT INTO Unit_Tile(unit_id, tile_id) VALUES (1, 'BLOOD');
INSERT INTO Unit_Tile(unit_id, tile_id) VALUES (1, 'BLOOD2');
INSERT INTO Unit_Tile(unit_id, tile_id) VALUES (1, 'SURGE_STAR');

INSERT INTO Recruiter(id, status) VALUES (1, 'AVAILABLE');