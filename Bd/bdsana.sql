-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-06-2018 a las 18:05:17
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `bdsana`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE IF NOT EXISTS `actividad` (
  `Idact` int(11) NOT NULL,
  `nuevo` int(11) NOT NULL,
  `guardar` int(11) NOT NULL,
  `editar` int(11) NOT NULL,
  `elimiar` int(11) NOT NULL,
  `nuevo2` int(11) NOT NULL,
  `guardar2` int(11) NOT NULL,
  `editar2` int(11) NOT NULL,
  `eliminar2` int(11) NOT NULL,
  `nuevo3` int(11) NOT NULL,
  `guardar3` int(11) NOT NULL,
  `editar3` int(11) NOT NULL,
  `eliminar3` int(11) NOT NULL,
  `reporte3` int(11) NOT NULL,
  `nuevo4` int(11) NOT NULL,
  `guardar4` int(11) NOT NULL,
  `editar4` int(11) NOT NULL,
  `eliminar4` int(11) NOT NULL,
  `procesar5` int(11) NOT NULL,
  `seleccionar5` int(11) NOT NULL,
  `reporte5` int(11) NOT NULL,
  `busqueda6` int(11) NOT NULL,
  `busquedaav6` int(11) NOT NULL,
  `reporte6` int(11) NOT NULL,
  `busqueda7` int(11) NOT NULL,
  `busquedaav7` int(11) NOT NULL,
  `reporte7` int(11) NOT NULL,
  PRIMARY KEY (`Idact`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`Idact`, `nuevo`, `guardar`, `editar`, `elimiar`, `nuevo2`, `guardar2`, `editar2`, `eliminar2`, `nuevo3`, `guardar3`, `editar3`, `eliminar3`, `reporte3`, `nuevo4`, `guardar4`, `editar4`, `eliminar4`, `procesar5`, `seleccionar5`, `reporte5`, `busqueda6`, `busquedaav6`, `reporte6`, `busqueda7`, `busquedaav7`, `reporte7`) VALUES
(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE IF NOT EXISTS `auditoria` (
  `idaudi` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` varchar(11) NOT NULL,
  `nombre` varchar(70) NOT NULL,
  `rol` varchar(60) NOT NULL,
  `proceso` varchar(70) NOT NULL,
  `actividad` varchar(70) NOT NULL,
  `hora` varchar(80) NOT NULL,
  `fecha` varchar(30) NOT NULL,
  PRIMARY KEY (`idaudi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `codigo_persona`
--

CREATE TABLE IF NOT EXISTS `codigo_persona` (
  `idcodigo` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` int(11) NOT NULL,
  PRIMARY KEY (`idcodigo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `codigo_persona`
--

INSERT INTO `codigo_persona` (`idcodigo`, `Descripcion`) VALUES
(1, 412),
(2, 414),
(3, 424),
(4, 416),
(5, 426),
(6, 263);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `datos_usuario`
--

CREATE TABLE IF NOT EXISTS `datos_usuario` (
  `idusu` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` int(11) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `sexo` varchar(30) NOT NULL,
  `codigo` int(11) NOT NULL,
  `telefono` int(11) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `contraseña` varchar(100) NOT NULL,
  `estado` int(11) NOT NULL,
  `rol` int(11) NOT NULL,
  `rufo` varchar(100) NOT NULL,
  `nofo` varchar(30) NOT NULL,
  `pregunta` varchar(50) NOT NULL,
  `respuesta` varchar(50) NOT NULL,
  PRIMARY KEY (`idusu`),
  KEY `rol_usu` (`rol`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `datos_usuario`
--

INSERT INTO `datos_usuario` (`idusu`, `cedula`, `nombre`, `apellido`, `sexo`, `codigo`, `telefono`, `correo`, `usuario`, `contraseña`, `estado`, `rol`, `rufo`, `nofo`, `pregunta`, `respuesta`) VALUES
(1, 24950205, 'Selena', 'Mendez', 'Femenino', 412, 1234567, 'sdfgf@gmail.com', '8c98ce7dbdf61f9adad46a6d0dbd17fe', '92dfd0fbfff815080750ebd5e4cd9c71', 0, 3, 'No pose', 'No pose', 'Color', 'Negro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_altura_peso`
--

CREATE TABLE IF NOT EXISTS `estado_altura_peso` (
  `idpeal` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` int(11) NOT NULL,
  `peso` int(11) NOT NULL,
  `altura` int(11) NOT NULL,
  `edad` int(11) NOT NULL,
  `Resultado` varchar(60) NOT NULL,
  `fecha` varchar(30) NOT NULL,
  PRIMARY KEY (`idpeal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE IF NOT EXISTS `persona` (
  `Nac` varchar(10) NOT NULL,
  `cedula` varchar(15) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `apellido` varchar(60) NOT NULL,
  `edad` int(11) NOT NULL,
  `sexo` varchar(15) NOT NULL,
  `seccion` int(11) NOT NULL,
  `codigo` int(11) NOT NULL,
  `telfono` bigint(10) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `inicio` int(11) NOT NULL,
  `termino` int(11) NOT NULL,
  `rufo` varchar(100) NOT NULL,
  `nofo` varchar(100) NOT NULL,
  `fechana` varchar(60) NOT NULL,
  `fecha` varchar(20) NOT NULL,
  `nac2` varchar(3) NOT NULL,
  `cedurepr` varchar(15) NOT NULL,
  PRIMARY KEY (`cedula`),
  KEY `secper` (`seccion`),
  KEY `per_cod` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proceso`
--

CREATE TABLE IF NOT EXISTS `proceso` (
  `idpro` int(11) NOT NULL,
  `gesper` int(11) NOT NULL,
  `gessecc` int(11) NOT NULL,
  `gespro` int(11) NOT NULL,
  `gesrol` int(11) NOT NULL,
  `estn` int(11) NOT NULL,
  `consultaest` int(11) NOT NULL,
  `auditoria` int(11) NOT NULL,
  `respaldo` int(11) NOT NULL,
  `restaurar` int(11) NOT NULL,
  PRIMARY KEY (`idpro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proceso`
--

INSERT INTO `proceso` (`idpro`, `gesper`, `gessecc`, `gespro`, `gesrol`, `estn`, `consultaest`, `auditoria`, `respaldo`, `restaurar`) VALUES
(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE IF NOT EXISTS `profesor` (
  `cedula` int(11) NOT NULL,
  `nombre` varchar(70) NOT NULL,
  `apellido` varchar(70) NOT NULL,
  `sexo` varchar(30) NOT NULL,
  `nofo` varchar(200) NOT NULL,
  `rufo` varchar(200) NOT NULL,
  PRIMARY KEY (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor_seccion`
--

CREATE TABLE IF NOT EXISTS `profesor_seccion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `idsecc` int(11) NOT NULL,
  `cedpro` int(11) NOT NULL,
  `inicio` int(11) NOT NULL,
  `termino` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `sec_sec` (`idsecc`),
  KEY `pro_pro` (`cedpro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_usuario`
--

CREATE TABLE IF NOT EXISTS `rol_usuario` (
  `Idrol` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(60) NOT NULL,
  PRIMARY KEY (`Idrol`),
  UNIQUE KEY `Descripcion` (`Descripcion`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `rol_usuario`
--

INSERT INTO `rol_usuario` (`Idrol`, `Descripcion`) VALUES
(3, 'Administrador'),
(6, 'Coordinador'),
(4, 'Director'),
(9, 'Gerente'),
(7, 'Ninguno'),
(8, 'Nuevo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seccion`
--

CREATE TABLE IF NOT EXISTS `seccion` (
  `idsec` int(11) NOT NULL AUTO_INCREMENT,
  `Descripcion` varchar(30) NOT NULL,
  PRIMARY KEY (`idsec`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `seccion`
--

INSERT INTO `seccion` (`idsec`, `Descripcion`) VALUES
(1, 'U-6to'),
(2, 'U-5to'),
(3, 'U-4to'),
(4, 'U-3ro'),
(5, 'U-2do'),
(6, 'U-1ro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `secqper`
--

CREATE TABLE IF NOT EXISTS `secqper` (
  `cedula` int(11) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `apellido` varchar(80) NOT NULL,
  `altura` varchar(30) NOT NULL,
  `peso` varchar(30) NOT NULL,
  `estado` varchar(80) NOT NULL,
  `seccion` varchar(20) NOT NULL,
  `sexof` varchar(20) NOT NULL,
  `edad` varchar(20) NOT NULL,
  `fecha` varchar(30) NOT NULL,
  PRIMARY KEY (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usu_rol_pro_act`
--

CREATE TABLE IF NOT EXISTS `usu_rol_pro_act` (
  `Idurpa` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` int(11) NOT NULL,
  `idrol2` int(11) NOT NULL,
  `idpro2` int(11) NOT NULL,
  `idact2` int(11) NOT NULL,
  PRIMARY KEY (`Idurpa`),
  UNIQUE KEY `pro` (`idpro2`),
  UNIQUE KEY `acti` (`idact2`),
  KEY `rol_r` (`idrol2`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `usu_rol_pro_act`
--

INSERT INTO `usu_rol_pro_act` (`Idurpa`, `cedula`, `idrol2`, `idpro2`, `idact2`) VALUES
(1, 24950205, 3, 1, 1);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `datos_usuario`
--
ALTER TABLE `datos_usuario`
  ADD CONSTRAINT `rol_usu` FOREIGN KEY (`rol`) REFERENCES `rol_usuario` (`Idrol`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `per_co` FOREIGN KEY (`codigo`) REFERENCES `codigo_persona` (`idcodigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `secper` FOREIGN KEY (`seccion`) REFERENCES `seccion` (`idsec`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `profesor_seccion`
--
ALTER TABLE `profesor_seccion`
  ADD CONSTRAINT `pro_pro` FOREIGN KEY (`cedpro`) REFERENCES `profesor` (`cedula`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `secc_secc` FOREIGN KEY (`idsecc`) REFERENCES `seccion` (`idsec`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usu_rol_pro_act`
--
ALTER TABLE `usu_rol_pro_act`
  ADD CONSTRAINT `acti2` FOREIGN KEY (`idact2`) REFERENCES `actividad` (`Idact`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pro1` FOREIGN KEY (`idpro2`) REFERENCES `proceso` (`idpro`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rol_r` FOREIGN KEY (`idrol2`) REFERENCES `rol_usuario` (`Idrol`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
