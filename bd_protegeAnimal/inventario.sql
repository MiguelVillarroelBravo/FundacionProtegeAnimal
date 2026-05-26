-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-05-2026 a las 16:07:11
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
-- Base de datos: `inventario_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `id_insumo` bigint(20) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `stock_actual` double NOT NULL,
  `stock_critico` double NOT NULL,
  `stock_minimo` double NOT NULL,
  `tipo_insumo` varchar(50) NOT NULL,
  `unidad_medida` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`id_insumo`, `nombre`, `stock_actual`, `stock_critico`, `stock_minimo`, `tipo_insumo`, `unidad_medida`) VALUES
(1, 'Alimento Perro Adulto Premium', 120, 15, 40, 'Alimento', 'kg'),
(2, 'Alimento Gato Cachorro', 12, 8, 25, 'Alimento', 'kg'),
(3, 'Antiparasitario Canino Internal', 3, 4, 10, 'Medicamento', 'unidades'),
(4, 'Arena Sanitaria Gatos', 45, 10, 20, 'Insumo', 'kg'),
(5, 'Gasa Médica Estéril', 50, 5, 15, 'Medicamento', 'unidades');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`id_insumo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `id_insumo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
