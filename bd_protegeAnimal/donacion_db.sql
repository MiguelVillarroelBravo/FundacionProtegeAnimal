-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2026 a las 03:19:14
-- Versión del servidor: 10.1.25-MariaDB
-- Versión de PHP: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `donacion_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `donacion`
--

CREATE TABLE `donacion` (
  `id_don` bigint(20) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `fecha` date NOT NULL,
  `fk_rut` varchar(12) DEFAULT NULL,
  `monto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `donacion`
--

INSERT INTO `donacion` (`id_don`, `estado`, `fecha`, `fk_rut`, `monto`) VALUES
(1, 'Aprobada', '2026-05-20', '18.432.551', 15000),
(2, 'Aprobada', '2026-05-22', '19.223.441', 25000),
(3, 'Rechazada', '2026-05-24', '18.432.551', 5000),
(4, 'Aprobada', '2026-05-25', '15.882.114', 50000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `donante`
--

CREATE TABLE `donante` (
  `rut` varchar(12) NOT NULL,
  `dv_rut` char(1) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `donante`
--

INSERT INTO `donante` (`rut`, `dv_rut`, `nombre`, `telefono`, `correo`) VALUES
('15.882.114', '2', 'Pedro Juan Aguirre', '+56955667788', 'pedro.j@email.com'),
('18.432.551', '6', 'Carlos Mendoza', '+56987654321', 'carlos.mendoza@email.com'),
('19.223.441', 'K', 'Ana María Silva', '+56911223344', 'ana.silva@email.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `donacion`
--
ALTER TABLE `donacion`
  ADD PRIMARY KEY (`id_don`);

--
-- Indices de la tabla `donante`
--
ALTER TABLE `donante`
  ADD PRIMARY KEY (`rut`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `donacion`
--
ALTER TABLE `donacion`
  MODIFY `id_don` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
