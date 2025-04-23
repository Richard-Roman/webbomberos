-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-04-2025 a las 22:18:51
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdbomberos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `id_actividad` int(11) NOT NULL,
  `tipo_actividad` varchar(100) NOT NULL,
  `ubicacion` varchar(150) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estado` enum('EN ESPERA','EN PROCESO','FINALIZADA') DEFAULT 'EN ESPERA',
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad_personal`
--

CREATE TABLE `actividad_personal` (
  `id_actividad_personal` int(11) NOT NULL,
  `id_personal` int(11) NOT NULL,
  `id_actividad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `adquisicion`
--

CREATE TABLE `adquisicion` (
  `idadquisicion` int(11) NOT NULL,
  `nombre_adquisicion` varchar(255) NOT NULL,
  `tipo_adquisicion` varchar(255) DEFAULT NULL,
  `fecha_adquisicion` varchar(255) NOT NULL,
  `idProveedor` int(11) NOT NULL,
  `monto_estimado` double DEFAULT NULL,
  `doc_adquisicion` varchar(255) NOT NULL,
  `mostrar` int(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion`
--

CREATE TABLE `asignacion` (
  `idAsignacion` int(11) NOT NULL,
  `id_personal` int(11) NOT NULL,
  `codigoEjemplarRecurso` varchar(7) NOT NULL,
  `fechaAsignacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fechaDevolucion` date DEFAULT NULL,
  `fechaRegistro` timestamp NOT NULL DEFAULT current_timestamp(),
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia_detalle`
--

CREATE TABLE `asistencia_detalle` (
  `id_detalle` int(11) NOT NULL,
  `id_registro` int(11) DEFAULT NULL,
  `id_personal` int(11) DEFAULT NULL,
  `hora_llegada` datetime DEFAULT NULL,
  `hora_salida` datetime DEFAULT NULL,
  `estado_asistencia` varchar(255) DEFAULT NULL,
  `desactivado` varchar(255) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia_registro`
--

CREATE TABLE `asistencia_registro` (
  `id_registro` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora_inicio` datetime DEFAULT NULL,
  `hora_fin` datetime DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `desactivado` varchar(255) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bomberosasignados`
--

CREATE TABLE `bomberosasignados` (
  `id_asignacion` int(11) NOT NULL,
  `id_incidencia` int(11) NOT NULL,
  `id_personal` int(11) DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `claserecursos`
--

CREATE TABLE `claserecursos` (
  `idClaseRecurso` int(11) NOT NULL,
  `nombreClase` varchar(40) NOT NULL,
  `descripción` tinytext DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `componentesequiposvehiculos`
--

CREATE TABLE `componentesequiposvehiculos` (
  `idComponentes` int(11) NOT NULL,
  `codigoEquipoVehiculo` varchar(7) NOT NULL,
  `codigoEjemplarRecurso` varchar(7) NOT NULL,
  `fechaAsignacionEquipo` timestamp NOT NULL DEFAULT current_timestamp(),
  `fechaActualizacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleaquisicion`
--

CREATE TABLE `detalleaquisicion` (
  `id_detalle` int(11) NOT NULL,
  `idadquisicion` int(11) NOT NULL,
  `codigoEjemplarRecurso` varchar(7) DEFAULT NULL,
  `precio_unitario` decimal(18,2) NOT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplarrecursos`
--

CREATE TABLE `ejemplarrecursos` (
  `codigoEjemplarRecurso` varchar(7) NOT NULL,
  `ubicacionEjemplarRecursos` tinytext DEFAULT NULL,
  `idRecurso` int(11) NOT NULL,
  `idEstado` int(11) DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especificacionestecnicas`
--

CREATE TABLE `especificacionestecnicas` (
  `idEspeTecnica` int(11) NOT NULL,
  `espeTecnica` varchar(40) NOT NULL,
  `unidadMedida` varchar(30) DEFAULT NULL,
  `descripción` tinytext DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadosrecursos`
--

CREATE TABLE `estadosrecursos` (
  `idEstado` int(11) NOT NULL,
  `nombreEstado` varchar(20) DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudios`
--

CREATE TABLE `estudios` (
  `id_estudio` int(11) NOT NULL,
  `id_personal` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_expedicion` date DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `imagen_documento` varchar(255) DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1,
  `registro_activo` varchar(255) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_cambios`
--

CREATE TABLE `historial_cambios` (
  `id_historial` int(11) NOT NULL,
  `tabla_afectada` varchar(255) DEFAULT NULL,
  `id_registro_afectado` int(11) DEFAULT NULL,
  `accion` varchar(255) DEFAULT NULL,
  `fecha_cambio` datetime DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  `cambios_realizados` varchar(255) DEFAULT NULL,
  `desactivado` varchar(255) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencias`
--

CREATE TABLE `incidencias` (
  `id_incidencia` int(11) NOT NULL,
  `codigo_incidencia` varchar(20) NOT NULL,
  `fecha_reporte` timestamp NOT NULL DEFAULT current_timestamp(),
  `tipo_emergencia_id` int(11) NOT NULL,
  `descripcion` text NOT NULL,
  `nivel_prioridad` text NOT NULL,
  `estado` text DEFAULT 'Pendiente',
  `ubicacion` varchar(255) NOT NULL,
  `reportado_por` varchar(100) NOT NULL,
  `atendido_por` varchar(100) DEFAULT NULL,
  `fecha_atencion` timestamp NOT NULL DEFAULT current_timestamp(),
  `fecha_cierre` timestamp NOT NULL DEFAULT current_timestamp(),
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `licencias_suspendidos`
--

CREATE TABLE `licencias_suspendidos` (
  `id_licencia` int(11) NOT NULL,
  `id_personal` int(11) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre_documento` varchar(255) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `imagen_documento` varchar(255) DEFAULT NULL,
  `desactivado` varchar(1) NOT NULL DEFAULT '0'
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimiento`
--

CREATE TABLE `mantenimiento` (
  `idMantenimiento` int(11) NOT NULL,
  `idResponMantenimiento` int(11) NOT NULL,
  `fechaEntrega` datetime NOT NULL,
  `fechaDevolucion` datetime DEFAULT NULL,
  `estadoMantenimiento` enum('EN PROCESO','FINALIZADO') DEFAULT 'EN PROCESO',
  `observaciones` tinytext DEFAULT NULL,
  `fechaCreacion` timestamp NOT NULL DEFAULT current_timestamp(),
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimientorecursos`
--

CREATE TABLE `mantenimientorecursos` (
  `idMantenimientoRecursos` int(11) NOT NULL,
  `idMantenimiento` int(11) NOT NULL,
  `codigoEjemplarRecurso` varchar(7) NOT NULL,
  `tipoMantenimiento` enum('PREVENTIVO','CORRECTIVO') DEFAULT NULL,
  `costoMantenimiento` decimal(7,2) DEFAULT NULL,
  `fechaRegistro` timestamp NOT NULL DEFAULT current_timestamp(),
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id_permisos` int(11) NOT NULL,
  `nombre_permiso` varchar(255) NOT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id_permisos`, `nombre_permiso`, `registroActivo`) VALUES
(1, 'CREATE', 1),
(2, 'DELETE', 1),
(3, 'READ', 1),
(4, 'UPDATE', 1),
(5, 'REFACTOR', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `id_personal` int(11) NOT NULL,
  `dni` varchar(15) DEFAULT NULL,
  `codigo_bombero` varchar(20) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `tipo_sangre` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `grado` varchar(255) DEFAULT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `alergias` varchar(255) DEFAULT NULL,
  `foto_perfil` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `desactivado` varchar(255) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `idproveedor` int(11) NOT NULL,
  `nombre_proveedor` varchar(255) NOT NULL,
  `tipo_proveedor` varchar(255) NOT NULL,
  `docidentidad_proveedor` varchar(255) DEFAULT NULL,
  `tipo_docidentidad` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `mostrar` int(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recursos`
--

CREATE TABLE `recursos` (
  `idRecurso` int(11) NOT NULL,
  `nombreRecurso` varchar(40) NOT NULL,
  `categoria` enum('VEHICUO','HERRAMIENTA','EQUIPO') DEFAULT 'HERRAMIENTA',
  `idClaseRecurso` int(11) DEFAULT NULL,
  `descripción` tinytext DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recursosasignados`
--

CREATE TABLE `recursosasignados` (
  `id_asignacion` int(11) NOT NULL,
  `id_incidencia` int(11) NOT NULL,
  `codigoEjemplarRecurso` varchar(7) DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recursos_especificacionestecnicas`
--

CREATE TABLE `recursos_especificacionestecnicas` (
  `idEspeTecnicaRecursos` int(11) NOT NULL,
  `idEspeTecnica` int(11) DEFAULT NULL,
  `idRecurso` int(11) DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registroespetecnicasrecursos`
--

CREATE TABLE `registroespetecnicasrecursos` (
  `idRegistroEspeTecnicasRecursos` int(11) NOT NULL,
  `idEspeTecnica` int(11) NOT NULL,
  `codigoEjemplarRecurso` varchar(7) NOT NULL,
  `valorEspeTecnica` varchar(30) DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportesincidencias`
--

CREATE TABLE `reportesincidencias` (
  `id_reporte` int(11) NOT NULL,
  `id_incidencia` int(11) NOT NULL,
  `resumen` text NOT NULL,
  `daños_materiales` text DEFAULT NULL,
  `victimas` text DEFAULT NULL,
  `tiempo_respuesta` time NOT NULL,
  `observaciones` text DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responmantenimiento`
--

CREATE TABLE `responmantenimiento` (
  `idResponMantenimiento` int(11) NOT NULL,
  `tipoDocIdentidad` enum('RUC','DNI') NOT NULL,
  `numeroDocIdentidad` varchar(11) DEFAULT NULL,
  `nombreResponsable` varchar(50) DEFAULT NULL,
  `telefono` varchar(9) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `revisionestecnica`
--

CREATE TABLE `revisionestecnica` (
  `idRevisionTecnica` int(11) NOT NULL,
  `codigoEjemplarRecurso` varchar(7) NOT NULL,
  `fechaRevisionTecnica` date NOT NULL,
  `resultado` enum('APROBADO','OBSERVADO','DESAPROBADO') DEFAULT NULL,
  `centroRevision` varchar(50) DEFAULT NULL,
  `ubicacionCentroRevision` varchar(50) DEFAULT NULL,
  `numCertInspeccion` varchar(15) DEFAULT NULL,
  `observaciones` tinytext DEFAULT NULL,
  `docCertificado` varchar(50) NOT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1,
  `fechaRegistro` timestamp NOT NULL DEFAULT current_timestamp()
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `rol_name` enum('ADMIN','DEVELOPER','INVITED','USER') NOT NULL DEFAULT 'USER',
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `rol_name`, `registroActivo`) VALUES
(1, 'ADMIN', 1),
(2, 'DEVELOPER', 1),
(3, 'USER', 1),
(4, 'INVITED', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_permisos`
--

CREATE TABLE `roles_permisos` (
  `id_rol` int(11) NOT NULL,
  `id_permiso` int(11) NOT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

--
-- Volcado de datos para la tabla `roles_permisos`
--

INSERT INTO `roles_permisos` (`id_rol`, `id_permiso`, `registroActivo`) VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(2, 1, 1),
(2, 2, 1),
(2, 3, 1),
(2, 4, 1),
(2, 5, 1),
(3, 1, 1),
(3, 2, 1),
(4, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `segurovehicular`
--

CREATE TABLE `segurovehicular` (
  `idSeguroVehicular` int(11) NOT NULL,
  `docSeguroVehicular` varchar(50) NOT NULL,
  `codigoEjemplarRecurso` varchar(7) NOT NULL,
  `numeroPoliza` varchar(100) NOT NULL,
  `aseguradora` varchar(100) NOT NULL,
  `fechaEmision` date NOT NULL,
  `fechaVencimiento` date NOT NULL,
  `costo` decimal(10,2) NOT NULL,
  `estado` enum('VIGENTE','VENCIDO') DEFAULT 'VIGENTE',
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1,
  `fechaRegistro` timestamp NOT NULL DEFAULT current_timestamp()
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonos_emergencia`
--

CREATE TABLE `telefonos_emergencia` (
  `id_telefono` int(11) NOT NULL,
  `id_personal` int(11) DEFAULT NULL,
  `nombre_contacto` varchar(255) DEFAULT NULL,
  `parentesco` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `desactivado` varchar(255) NOT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposemergencia`
--

CREATE TABLE `tiposemergencia` (
  `id_tipo_emergencia` int(11) NOT NULL,
  `nombre_tipo` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `recursos_necesarios` text NOT NULL,
  `protocolos` text NOT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usuario` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `account_no_locked` tinyint(1) DEFAULT 1,
  `account_no_expired` tinyint(1) DEFAULT 1,
  `credential_no_expired` tinyint(1) DEFAULT 1,
  `is_enabled` tinyint(1) DEFAULT 1,
  `email` varchar(255) DEFAULT NULL,
  `id_personal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `nombre_usuario`, `contrasena`, `account_no_locked`, `account_no_expired`, `credential_no_expired`, `is_enabled`, `email`, `id_personal`) VALUES
(1, 'Jhon', '$2a$10$wUhctvY6B.oFYYCchnOsNOwoiujmF46s38LIrWGYg/2r4.M.4/d9.', 1, 1, 1, 1, 'jd.huaman@unsm.edu.pe', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_roles`
--

CREATE TABLE `usuarios_roles` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `registroActivo` tinyint(1) NOT NULL DEFAULT 1
) ;

--
-- Volcado de datos para la tabla `usuarios_roles`
--

INSERT INTO `usuarios_roles` (`id_usuario`, `id_rol`, `registroActivo`) VALUES
(1, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`id_actividad`);

--
-- Indices de la tabla `actividad_personal`
--
ALTER TABLE `actividad_personal`
  ADD PRIMARY KEY (`id_actividad_personal`),
  ADD KEY `id_actividad` (`id_actividad`),
  ADD KEY `id_personal` (`id_personal`);

--
-- Indices de la tabla `adquisicion`
--
ALTER TABLE `adquisicion`
  ADD PRIMARY KEY (`idadquisicion`),
  ADD KEY `idProveedor` (`idProveedor`);

--
-- Indices de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD PRIMARY KEY (`idAsignacion`),
  ADD KEY `codigoEjemplarRecurso` (`codigoEjemplarRecurso`),
  ADD KEY `id_personal` (`id_personal`);

--
-- Indices de la tabla `asistencia_detalle`
--
ALTER TABLE `asistencia_detalle`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_registro` (`id_registro`),
  ADD KEY `id_personal` (`id_personal`);

--
-- Indices de la tabla `asistencia_registro`
--
ALTER TABLE `asistencia_registro`
  ADD PRIMARY KEY (`id_registro`);

--
-- Indices de la tabla `bomberosasignados`
--
ALTER TABLE `bomberosasignados`
  ADD PRIMARY KEY (`id_asignacion`),
  ADD KEY `id_incidencia` (`id_incidencia`),
  ADD KEY `id_personal` (`id_personal`);

--
-- Indices de la tabla `claserecursos`
--
ALTER TABLE `claserecursos`
  ADD PRIMARY KEY (`idClaseRecurso`);

--
-- Indices de la tabla `componentesequiposvehiculos`
--
ALTER TABLE `componentesequiposvehiculos`
  ADD PRIMARY KEY (`idComponentes`),
  ADD KEY `codigoEquipoVehiculo` (`codigoEquipoVehiculo`),
  ADD KEY `codigoEjemplarRecurso` (`codigoEjemplarRecurso`);

--
-- Indices de la tabla `detalleaquisicion`
--
ALTER TABLE `detalleaquisicion`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `codigoEjemplarRecurso` (`codigoEjemplarRecurso`),
  ADD KEY `idadquisicion` (`idadquisicion`);

--
-- Indices de la tabla `ejemplarrecursos`
--
ALTER TABLE `ejemplarrecursos`
  ADD PRIMARY KEY (`codigoEjemplarRecurso`),
  ADD KEY `idEstado` (`idEstado`),
  ADD KEY `idRecurso` (`idRecurso`);

--
-- Indices de la tabla `especificacionestecnicas`
--
ALTER TABLE `especificacionestecnicas`
  ADD PRIMARY KEY (`idEspeTecnica`);

--
-- Indices de la tabla `estadosrecursos`
--
ALTER TABLE `estadosrecursos`
  ADD PRIMARY KEY (`idEstado`);

--
-- Indices de la tabla `estudios`
--
ALTER TABLE `estudios`
  ADD PRIMARY KEY (`id_estudio`),
  ADD KEY `id_personal` (`id_personal`);

--
-- Indices de la tabla `historial_cambios`
--
ALTER TABLE `historial_cambios`
  ADD PRIMARY KEY (`id_historial`);

--
-- Indices de la tabla `incidencias`
--
ALTER TABLE `incidencias`
  ADD PRIMARY KEY (`id_incidencia`),
  ADD UNIQUE KEY `codigo_incidencia` (`codigo_incidencia`),
  ADD KEY `tipo_emergencia_id` (`tipo_emergencia_id`);

--
-- Indices de la tabla `licencias_suspendidos`
--
ALTER TABLE `licencias_suspendidos`
  ADD PRIMARY KEY (`id_licencia`),
  ADD KEY `id_personal` (`id_personal`);

--
-- Indices de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD PRIMARY KEY (`idMantenimiento`),
  ADD KEY `idResponMantenimiento` (`idResponMantenimiento`);

--
-- Indices de la tabla `mantenimientorecursos`
--
ALTER TABLE `mantenimientorecursos`
  ADD PRIMARY KEY (`idMantenimientoRecursos`),
  ADD KEY `idMantenimiento` (`idMantenimiento`),
  ADD KEY `codigoEjemplarRecurso` (`codigoEjemplarRecurso`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id_permisos`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`id_personal`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`idproveedor`);

--
-- Indices de la tabla `recursos`
--
ALTER TABLE `recursos`
  ADD PRIMARY KEY (`idRecurso`),
  ADD KEY `FK_Recursos_ClaseRecursos` (`idClaseRecurso`);

--
-- Indices de la tabla `recursosasignados`
--
ALTER TABLE `recursosasignados`
  ADD PRIMARY KEY (`id_asignacion`),
  ADD KEY `codigoEjemplarRecurso` (`codigoEjemplarRecurso`),
  ADD KEY `id_incidencia` (`id_incidencia`);

--
-- Indices de la tabla `recursos_especificacionestecnicas`
--
ALTER TABLE `recursos_especificacionestecnicas`
  ADD PRIMARY KEY (`idEspeTecnicaRecursos`),
  ADD KEY `idEspeTecnica` (`idEspeTecnica`),
  ADD KEY `idRecurso` (`idRecurso`);

--
-- Indices de la tabla `registroespetecnicasrecursos`
--
ALTER TABLE `registroespetecnicasrecursos`
  ADD PRIMARY KEY (`idRegistroEspeTecnicasRecursos`),
  ADD KEY `idEspeTecnica` (`idEspeTecnica`),
  ADD KEY `codigoEjemplarRecurso` (`codigoEjemplarRecurso`);

--
-- Indices de la tabla `reportesincidencias`
--
ALTER TABLE `reportesincidencias`
  ADD PRIMARY KEY (`id_reporte`),
  ADD KEY `id_incidencia` (`id_incidencia`);

--
-- Indices de la tabla `responmantenimiento`
--
ALTER TABLE `responmantenimiento`
  ADD PRIMARY KEY (`idResponMantenimiento`);

--
-- Indices de la tabla `revisionestecnica`
--
ALTER TABLE `revisionestecnica`
  ADD PRIMARY KEY (`idRevisionTecnica`),
  ADD KEY `codigoEjemplarRecurso` (`codigoEjemplarRecurso`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `roles_permisos`
--
ALTER TABLE `roles_permisos`
  ADD PRIMARY KEY (`id_rol`,`id_permiso`),
  ADD KEY `id_permiso` (`id_permiso`);

--
-- Indices de la tabla `segurovehicular`
--
ALTER TABLE `segurovehicular`
  ADD PRIMARY KEY (`idSeguroVehicular`),
  ADD KEY `codigoEjemplarRecurso` (`codigoEjemplarRecurso`);

--
-- Indices de la tabla `telefonos_emergencia`
--
ALTER TABLE `telefonos_emergencia`
  ADD PRIMARY KEY (`id_telefono`),
  ADD KEY `id_personal` (`id_personal`);

--
-- Indices de la tabla `tiposemergencia`
--
ALTER TABLE `tiposemergencia`
  ADD PRIMARY KEY (`id_tipo_emergencia`),
  ADD UNIQUE KEY `nombre_tipo` (`nombre_tipo`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `id_personal` (`id_personal`);

--
-- Indices de la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD PRIMARY KEY (`id_usuario`,`id_rol`),
  ADD KEY `id_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividad`
--
ALTER TABLE `actividad`
  MODIFY `id_actividad` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `actividad_personal`
--
ALTER TABLE `actividad_personal`
  MODIFY `id_actividad_personal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `adquisicion`
--
ALTER TABLE `adquisicion`
  MODIFY `idadquisicion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  MODIFY `idAsignacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `asistencia_detalle`
--
ALTER TABLE `asistencia_detalle`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `asistencia_registro`
--
ALTER TABLE `asistencia_registro`
  MODIFY `id_registro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `bomberosasignados`
--
ALTER TABLE `bomberosasignados`
  MODIFY `id_asignacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `claserecursos`
--
ALTER TABLE `claserecursos`
  MODIFY `idClaseRecurso` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `componentesequiposvehiculos`
--
ALTER TABLE `componentesequiposvehiculos`
  MODIFY `idComponentes` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalleaquisicion`
--
ALTER TABLE `detalleaquisicion`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `especificacionestecnicas`
--
ALTER TABLE `especificacionestecnicas`
  MODIFY `idEspeTecnica` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estadosrecursos`
--
ALTER TABLE `estadosrecursos`
  MODIFY `idEstado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `estudios`
--
ALTER TABLE `estudios`
  MODIFY `id_estudio` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `historial_cambios`
--
ALTER TABLE `historial_cambios`
  MODIFY `id_historial` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `incidencias`
--
ALTER TABLE `incidencias`
  MODIFY `id_incidencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `licencias_suspendidos`
--
ALTER TABLE `licencias_suspendidos`
  MODIFY `id_licencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  MODIFY `idMantenimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mantenimientorecursos`
--
ALTER TABLE `mantenimientorecursos`
  MODIFY `idMantenimientoRecursos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id_permisos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `personal`
--
ALTER TABLE `personal`
  MODIFY `id_personal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `idproveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `recursos`
--
ALTER TABLE `recursos`
  MODIFY `idRecurso` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `recursosasignados`
--
ALTER TABLE `recursosasignados`
  MODIFY `id_asignacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `recursos_especificacionestecnicas`
--
ALTER TABLE `recursos_especificacionestecnicas`
  MODIFY `idEspeTecnicaRecursos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `registroespetecnicasrecursos`
--
ALTER TABLE `registroespetecnicasrecursos`
  MODIFY `idRegistroEspeTecnicasRecursos` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reportesincidencias`
--
ALTER TABLE `reportesincidencias`
  MODIFY `id_reporte` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `responmantenimiento`
--
ALTER TABLE `responmantenimiento`
  MODIFY `idResponMantenimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `revisionestecnica`
--
ALTER TABLE `revisionestecnica`
  MODIFY `idRevisionTecnica` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `segurovehicular`
--
ALTER TABLE `segurovehicular`
  MODIFY `idSeguroVehicular` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `telefonos_emergencia`
--
ALTER TABLE `telefonos_emergencia`
  MODIFY `id_telefono` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tiposemergencia`
--
ALTER TABLE `tiposemergencia`
  MODIFY `id_tipo_emergencia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad_personal`
--
ALTER TABLE `actividad_personal`
  ADD CONSTRAINT `actividad_personal_ibfk_1` FOREIGN KEY (`id_actividad`) REFERENCES `actividad` (`id_actividad`),
  ADD CONSTRAINT `actividad_personal_ibfk_2` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`);

--
-- Filtros para la tabla `adquisicion`
--
ALTER TABLE `adquisicion`
  ADD CONSTRAINT `adquisicion_ibfk_1` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`);

--
-- Filtros para la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`codigoEjemplarRecurso`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`),
  ADD CONSTRAINT `asignacion_ibfk_2` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`);

--
-- Filtros para la tabla `asistencia_detalle`
--
ALTER TABLE `asistencia_detalle`
  ADD CONSTRAINT `asistencia_detalle_ibfk_1` FOREIGN KEY (`id_registro`) REFERENCES `asistencia_registro` (`id_registro`),
  ADD CONSTRAINT `asistencia_detalle_ibfk_2` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`);

--
-- Filtros para la tabla `bomberosasignados`
--
ALTER TABLE `bomberosasignados`
  ADD CONSTRAINT `bomberosasignados_ibfk_1` FOREIGN KEY (`id_incidencia`) REFERENCES `incidencias` (`id_incidencia`),
  ADD CONSTRAINT `bomberosasignados_ibfk_2` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`);

--
-- Filtros para la tabla `componentesequiposvehiculos`
--
ALTER TABLE `componentesequiposvehiculos`
  ADD CONSTRAINT `componentesequiposvehiculos_ibfk_1` FOREIGN KEY (`codigoEquipoVehiculo`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`) ON UPDATE CASCADE,
  ADD CONSTRAINT `componentesequiposvehiculos_ibfk_2` FOREIGN KEY (`codigoEjemplarRecurso`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalleaquisicion`
--
ALTER TABLE `detalleaquisicion`
  ADD CONSTRAINT `detalleaquisicion_ibfk_1` FOREIGN KEY (`codigoEjemplarRecurso`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`),
  ADD CONSTRAINT `detalleaquisicion_ibfk_2` FOREIGN KEY (`idadquisicion`) REFERENCES `adquisicion` (`idadquisicion`);

--
-- Filtros para la tabla `ejemplarrecursos`
--
ALTER TABLE `ejemplarrecursos`
  ADD CONSTRAINT `ejemplarrecursos_ibfk_1` FOREIGN KEY (`idEstado`) REFERENCES `estadosrecursos` (`idEstado`) ON UPDATE CASCADE,
  ADD CONSTRAINT `ejemplarrecursos_ibfk_2` FOREIGN KEY (`idRecurso`) REFERENCES `recursos` (`idRecurso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `estudios`
--
ALTER TABLE `estudios`
  ADD CONSTRAINT `estudios_ibfk_1` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`);

--
-- Filtros para la tabla `incidencias`
--
ALTER TABLE `incidencias`
  ADD CONSTRAINT `incidencias_ibfk_1` FOREIGN KEY (`tipo_emergencia_id`) REFERENCES `tiposemergencia` (`id_tipo_emergencia`);

--
-- Filtros para la tabla `licencias_suspendidos`
--
ALTER TABLE `licencias_suspendidos`
  ADD CONSTRAINT `licencias_suspendidos_ibfk_1` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`);

--
-- Filtros para la tabla `mantenimiento`
--
ALTER TABLE `mantenimiento`
  ADD CONSTRAINT `mantenimiento_ibfk_1` FOREIGN KEY (`idResponMantenimiento`) REFERENCES `responmantenimiento` (`idResponMantenimiento`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `mantenimientorecursos`
--
ALTER TABLE `mantenimientorecursos`
  ADD CONSTRAINT `mantenimientorecursos_ibfk_1` FOREIGN KEY (`idMantenimiento`) REFERENCES `mantenimiento` (`idMantenimiento`) ON UPDATE CASCADE,
  ADD CONSTRAINT `mantenimientorecursos_ibfk_2` FOREIGN KEY (`codigoEjemplarRecurso`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `recursos`
--
ALTER TABLE `recursos`
  ADD CONSTRAINT `FK_Recursos_ClaseRecursos` FOREIGN KEY (`idClaseRecurso`) REFERENCES `claserecursos` (`idClaseRecurso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `recursosasignados`
--
ALTER TABLE `recursosasignados`
  ADD CONSTRAINT `recursosasignados_ibfk_1` FOREIGN KEY (`codigoEjemplarRecurso`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`),
  ADD CONSTRAINT `recursosasignados_ibfk_2` FOREIGN KEY (`id_incidencia`) REFERENCES `incidencias` (`id_incidencia`);

--
-- Filtros para la tabla `recursos_especificacionestecnicas`
--
ALTER TABLE `recursos_especificacionestecnicas`
  ADD CONSTRAINT `recursos_especificacionestecnicas_ibfk_1` FOREIGN KEY (`idEspeTecnica`) REFERENCES `especificacionestecnicas` (`idEspeTecnica`) ON UPDATE CASCADE,
  ADD CONSTRAINT `recursos_especificacionestecnicas_ibfk_2` FOREIGN KEY (`idRecurso`) REFERENCES `recursos` (`idRecurso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `registroespetecnicasrecursos`
--
ALTER TABLE `registroespetecnicasrecursos`
  ADD CONSTRAINT `registroespetecnicasrecursos_ibfk_1` FOREIGN KEY (`idEspeTecnica`) REFERENCES `especificacionestecnicas` (`idEspeTecnica`) ON UPDATE CASCADE,
  ADD CONSTRAINT `registroespetecnicasrecursos_ibfk_2` FOREIGN KEY (`codigoEjemplarRecurso`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `reportesincidencias`
--
ALTER TABLE `reportesincidencias`
  ADD CONSTRAINT `reportesincidencias_ibfk_1` FOREIGN KEY (`id_incidencia`) REFERENCES `incidencias` (`id_incidencia`);

--
-- Filtros para la tabla `revisionestecnica`
--
ALTER TABLE `revisionestecnica`
  ADD CONSTRAINT `revisionestecnica_ibfk_1` FOREIGN KEY (`codigoEjemplarRecurso`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `roles_permisos`
--
ALTER TABLE `roles_permisos`
  ADD CONSTRAINT `roles_permisos_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`),
  ADD CONSTRAINT `roles_permisos_ibfk_2` FOREIGN KEY (`id_permiso`) REFERENCES `permisos` (`id_permisos`);

--
-- Filtros para la tabla `segurovehicular`
--
ALTER TABLE `segurovehicular`
  ADD CONSTRAINT `segurovehicular_ibfk_1` FOREIGN KEY (`codigoEjemplarRecurso`) REFERENCES `ejemplarrecursos` (`codigoEjemplarRecurso`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `telefonos_emergencia`
--
ALTER TABLE `telefonos_emergencia`
  ADD CONSTRAINT `telefonos_emergencia_ibfk_1` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`id_personal`);

--
-- Filtros para la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD CONSTRAINT `usuarios_roles_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`),
  ADD CONSTRAINT `usuarios_roles_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
