-- --------------------------------------------------------
-- Host:                         db-dev.sistema-estrella.com
-- Versión del servidor:         5.7.38-log - Source distribution
-- SO del servidor:              Linux
-- HeidiSQL Versión:             12.2.0.6576
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para escuela
CREATE DATABASE IF NOT EXISTS `escuela` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `escuela`;

-- Volcando estructura para tabla escuela.t_alumnos
CREATE TABLE IF NOT EXISTS `t_alumnos` (
  `id_t_usuarios` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) DEFAULT NULL,
  `ap_paterno` varchar(80) DEFAULT NULL,
  `ap_materno` varchar(80) DEFAULT NULL,
  `activo` int(1) DEFAULT NULL,
  PRIMARY KEY (`id_t_usuarios`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla escuela.t_alumnos: ~0 rows (aproximadamente)
DELETE FROM `t_alumnos`;
INSERT INTO `t_alumnos` (`id_t_usuarios`, `nombre`, `ap_paterno`, `ap_materno`, `activo`) VALUES
	(1, 'John', 'Dow', 'Down', 1);

-- Volcando estructura para tabla escuela.t_calificaciones
CREATE TABLE IF NOT EXISTS `t_calificaciones` (
  `id_t_calificaciones` int(11) NOT NULL AUTO_INCREMENT,
  `id_t_materias` int(11) NOT NULL,
  `id_t_usuarios` int(11) NOT NULL,
  `calificacion` decimal(10,2) DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  PRIMARY KEY (`id_t_calificaciones`),
  KEY `id_t_materias` (`id_t_materias`),
  KEY `id_t_usuarios` (`id_t_usuarios`),
  CONSTRAINT `FK_t_calificaciones_t_alumnos` FOREIGN KEY (`id_t_usuarios`) REFERENCES `t_alumnos` (`id_t_usuarios`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_calificaciones_ibfk_1` FOREIGN KEY (`id_t_materias`) REFERENCES `t_materias` (`id_t_materias`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla escuela.t_calificaciones: ~7 rows (aproximadamente)
DELETE FROM `t_calificaciones`;
INSERT INTO `t_calificaciones` (`id_t_calificaciones`, `id_t_materias`, `id_t_usuarios`, `calificacion`, `fecha_registro`) VALUES
	(2, 3, 1, 7.00, '2023-02-13'),
	(4, 2, 1, 10.00, '2023-02-12'),
	(7, 1, 1, 10.00, '2023-02-12'),
	(8, 1, 1, 8.00, '2023-02-12'),
	(9, 2, 1, 3.00, '2023-02-12'),
	(10, 3, 1, 7.00, '2023-02-13'),
	(11, 2, 1, 7.00, '2023-02-13');

-- Volcando estructura para tabla escuela.t_materias
CREATE TABLE IF NOT EXISTS `t_materias` (
  `id_t_materias` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(80) DEFAULT NULL,
  `activo` int(1) DEFAULT NULL,
  PRIMARY KEY (`id_t_materias`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla escuela.t_materias: ~2 rows (aproximadamente)
DELETE FROM `t_materias`;
INSERT INTO `t_materias` (`id_t_materias`, `nombre`, `activo`) VALUES
	(1, 'matematicas', 1),
	(2, 'programacion I', 1),
	(3, 'ingenieria de sofware', 1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
