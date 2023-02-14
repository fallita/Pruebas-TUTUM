DELIMITER ;;
CREATE TRIGGER insert_almacen AFTER INSERT ON material_clave
FOR EACH ROW
BEGIN
	INSERT INTO almacen_materia_prima VALUES(new.id,0,1);
END;
;;
