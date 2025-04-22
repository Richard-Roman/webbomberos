-- Eliminación de esquema público si es necesario
SET FOREIGN_KEY_CHECKS = 0;

-- Tabla actividad
CREATE TABLE IF NOT EXISTS actividad (
    id_actividad INT AUTO_INCREMENT PRIMARY KEY,
    tipo_actividad VARCHAR(100) NOT NULL,
    ubicacion VARCHAR(150) NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    estado ENUM('EN ESPERA', 'EN PROCESO', 'FINALIZADA') DEFAULT 'EN ESPERA',
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_actividad_registroActivo CHECK (registroActivo IN (0, 1))
);



-- Tabla personal
CREATE TABLE IF NOT EXISTS personal  (
    id_personal INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(15) UNIQUE,
    codigo_bombero VARCHAR(20),
    nombres VARCHAR(100),
    apellidos VARCHAR(100),
    tipo_sangre VARCHAR(5),
    fecha_nacimiento DATE,
    grado VARCHAR(50),
    cargo VARCHAR(100),
    alergias TEXT,
    foto_perfil TEXT,
    estado VARCHAR(50),
    desactivado TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_personal_registroActivo CHECK (desactivado IN (0, 1))
);

CREATE TABLE telefonos_emergencia (
    id_telefono INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_personal INT(11),
    nombre_contacto VARCHAR(100),
    parentesco VARCHAR(50),
    telefono VARCHAR(20),
    desactivado VARCHAR(1) DEFAULT '0' NOT NULL,
    CONSTRAINT CHK_telefonosEmergencia_desactivado CHECK (desactivado IN (0, 1)),
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal)
);

CREATE TABLE licencias_suspendidos (
    id_licencia INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_personal INT(11),
    tipo VARCHAR(50),
    descripcion TEXT,
    nombre_documento VARCHAR(100),
    fecha_inicio DATE,
    fecha_fin DATE,
    imagen_documento TEXT,
    desactivado VARCHAR(1) DEFAULT '0' NOT NULL,
    CONSTRAINT CHK_licenciasSuspendidos_desactivado CHECK (desactivado IN (0, 1)),
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal)
);

CREATE TABLE estudios (
    id_estudio INT AUTO_INCREMENT PRIMARY KEY,
    id_personal INT,
    nombre VARCHAR(100),
    descripcion TEXT,
    fecha_emision DATE,
    fecha_expedicion DATE,
    tipo VARCHAR(50),
    imagen_documento TEXT,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_estudios_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal)
);

CREATE TABLE asistencia_registro (
    id_registro INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    hora_inicio DATETIME,
    hora_fin DATETIME,
    estado VARCHAR(50),
    observaciones TEXT,
    desactivado VARCHAR(1) DEFAULT '0' NOT NULL,
    CONSTRAINT CHK_asistenciaRegistro_desactivado CHECK (desactivado IN (0, 1))
    
);



CREATE TABLE asistencia_detalle (
    id_detalle INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_registro INT(11),
    id_personal INT(11),
    hora_llegada DATETIME,
    hora_salida DATETIME,
    estado_asistencia VARCHAR(50),
    desactivado VARCHAR(1) DEFAULT '0' NOT NULL,
    CONSTRAINT CHK_asistenciaDetalle_desactivado CHECK (desactivado IN (0, 1)),
    FOREIGN KEY (id_registro) REFERENCES asistencia_registro(id_registro),
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal)
);



CREATE TABLE historial_cambios (
    id_historial INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tabla_afectada VARCHAR(50),
    id_registro_afectado INT(11),
    accion VARCHAR(20),
    fecha_cambio DATETIME,
    usuario VARCHAR(50),
    cambios_realizados TEXT,
    desactivado VARCHAR(1) DEFAULT '0' NOT NULL,
    CONSTRAINT CHK_historialCambios_desactivado CHECK (desactivado IN (0, 1))
);



-- ##################################3333

-- Tabla actividad_personal
CREATE TABLE IF NOT EXISTS actividad_personal (
    id_actividad_personal INT AUTO_INCREMENT PRIMARY KEY,
    id_personal INT NOT NULL,
    id_actividad INT NOT NULL,
    FOREIGN KEY (id_actividad) REFERENCES actividad(id_actividad),
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal)
);

-- Tabla estudios
CREATE TABLE IF NOT EXISTS estudios (
    id_estudio INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_personal INT(11),
    nombre VARCHAR(100),
    descripcion TEXT,
    fecha_emision DATE,
    fecha_expedicion DATE,
    tipo VARCHAR(50),
    imagen_documento TEXT,
    desactivado VARCHAR(1) DEFAULT '0' NOT NULL,
    CONSTRAINT CHK_estudios_desactivado CHECK (desactivado IN (0, 1)),
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal)
);

-- Tablas de inventario

CREATE TABLE especificacionesTecnicas (
    idEspeTecnica INT AUTO_INCREMENT NOT NULL,
    espeTecnica VARCHAR(40) NOT NULL,
    unidadMedida VARCHAR(30),
    descripción TINYTEXT,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT PK_EspecificacionesTecnicas PRIMARY KEY(idEspeTecnica),
    CONSTRAINT CHK_EspecificacionesTecnicas_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE claseRecursos (
    idClaseRecurso INT AUTO_INCREMENT PRIMARY KEY,
    nombreClase VARCHAR(40) NOT NULL,
    descripción TINYTEXT,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_ClaseRecursos_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE recursos (
    idRecurso INT AUTO_INCREMENT PRIMARY KEY,
    nombreRecurso VARCHAR(40) NOT NULL,
    categoria ENUM('VEHICUO', 'HERRAMIENTA', 'EQUIPO') DEFAULT 'HERRAMIENTA',
    idClaseRecurso INT,
    descripción TINYTEXT,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_Recursos_registroActivo CHECK (registroActivo IN (0, 1)),
    CONSTRAINT FK_Recursos_ClaseRecursos FOREIGN KEY (idClaseRecurso) REFERENCES claseRecursos(idClaseRecurso)
        ON UPDATE CASCADE
);

CREATE TABLE recursos_EspecificacionesTecnicas (
    idEspeTecnicaRecursos INT AUTO_INCREMENT PRIMARY KEY,
    idEspeTecnica INT,
    idRecurso INT,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    FOREIGN KEY (idEspeTecnica) REFERENCES especificacionesTecnicas(idEspeTecnica)
        ON UPDATE CASCADE,
    FOREIGN KEY (idRecurso) REFERENCES recursos(idRecurso)
        ON UPDATE CASCADE,
    CONSTRAINT CHK_TipoRecursosEspecificacionesTecnicas_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE estadosRecursos (
    idEstado INT AUTO_INCREMENT PRIMARY KEY,
    nombreEstado VARCHAR(20),
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_EstadosRecursos_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE ejemplarRecursos (
    codigoEjemplarRecurso VARCHAR(7) NOT NULL PRIMARY KEY,
    ubicacionEjemplarRecursos TINYTEXT,
    idRecurso INT NOT NULL,
    idEstado INT,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    FOREIGN KEY (idEstado) REFERENCES estadosRecursos(idEstado)
        ON UPDATE CASCADE,
    FOREIGN KEY (idRecurso) REFERENCES recursos(idRecurso)
        ON UPDATE CASCADE,
    CONSTRAINT CHK_Recursos_registroActivo CHECK (registroActivo IN (0, 1))
);


CREATE TABLE registroEspeTecnicasRecursos (
    idRegistroEspeTecnicasRecursos INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    idEspeTecnica INT NOT NULL,
    codigoEjemplarRecurso VARCHAR(7) NOT NULL,
    valorEspeTecnica VARCHAR(30),
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    FOREIGN KEY (idEspeTecnica) REFERENCES especificacionesTecnicas(idEspeTecnica)
        ON UPDATE CASCADE,
    FOREIGN KEY (codigoEjemplarRecurso) REFERENCES ejemplarRecursos(CodigoEjemplarRecurso)
        ON UPDATE CASCADE,
    CONSTRAINT CHK_RegistroEspeTecnicasRecursos_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE componentesEquiposVehiculos (
    idComponentes INT AUTO_INCREMENT PRIMARY KEY,
    codigoEquipoVehiculo VARCHAR(7) NOT NULL,
    codigoEjemplarRecurso VARCHAR(7) NOT NULL,
    fechaAsignacionEquipo TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fechaActualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    FOREIGN KEY (codigoEquipoVehiculo) REFERENCES ejemplarRecursos(codigoEjemplarRecurso)
        ON UPDATE CASCADE,
    FOREIGN KEY (codigoEjemplarRecurso) REFERENCES ejemplarRecursos(codigoEjemplarRecurso)
        ON UPDATE CASCADE,
    CONSTRAINT CHK_ComponentesEquiposVehiculos_registroActivo CHECK (registroActivo IN (0, 1))
);
-- hola mundo


CREATE TABLE responMantenimiento (
    idResponMantenimiento INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    tipoDocIdentidad ENUM('RUC', 'DNI') NOT NULL,
    numeroDocIdentidad VARCHAR(11),
    nombreResponsable VARCHAR(50),
    telefono VARCHAR(9),
    correo VARCHAR(45),
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_ResponMantenimiento_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE mantenimiento (
    idMantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    idResponMantenimiento INT NOT NULL,
    fechaEntrega DATETIME NOT NULL,
    fechaDevolucion DATETIME,
    estadoMantenimiento ENUM('EN PROCESO', 'FINALIZADO') DEFAULT 'EN PROCESO',
    observaciones TINYTEXT,
    fechaCreacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    FOREIGN KEY (idResponMantenimiento) REFERENCES responMantenimiento(idResponMantenimiento)
        ON UPDATE CASCADE,
    CONSTRAINT CHK_Mantenimiento_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE mantenimientoRecursos (
	idMantenimientoRecursos INT AUTO_INCREMENT PRIMARY KEY,
    idMantenimiento INT NOT NULL,
    codigoEjemplarRecurso VARCHAR(7) NOT NULL,
    tipoMantenimiento ENUM('PREVENTIVO', 'CORRECTIVO'),
    costoMantenimiento DECIMAL(7,2),
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    FOREIGN KEY (idMantenimiento) REFERENCES mantenimiento(idMantenimiento)
        ON UPDATE CASCADE,
    FOREIGN KEY (CodigoEjemplarRecurso) REFERENCES ejemplarRecursos(CodigoEjemplarRecurso)
        ON UPDATE CASCADE,
    CONSTRAINT CHK_MantenimientoRecursos_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE seguroVehicular (
    idSeguroVehicular INT AUTO_INCREMENT PRIMARY KEY,
    docSeguroVehicular VARCHAR(50) NOT NULL,
    codigoEjemplarRecurso VARCHAR(7) NOT NULL,
    numeroPoliza VARCHAR(100) NOT NULL,
    aseguradora VARCHAR(100) NOT NULL,
    fechaEmision DATE NOT NULL,
    fechaVencimiento DATE NOT NULL,
    costo DECIMAL(10,2) NOT NULL,
    estado ENUM('VIGENTE', 'VENCIDO') DEFAULT 'Vigente',
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (codigoEjemplarRecurso) REFERENCES ejemplarRecursos(codigoEjemplarRecurso)
        ON UPDATE CASCADE,
    CONSTRAINT CHK_SeguroVehicular_registroActivo CHECK (registroActivo IN (0, 1))
);

CREATE TABLE revisionesTecnica (
    idRevisionTecnica INT AUTO_INCREMENT PRIMARY KEY,
    codigoEjemplarRecurso VARCHAR(7) NOT NULL,
    fechaRevisionTecnica DATE NOT NULL,
    resultado ENUM('APROBADO', 'OBSERVADO', 'DESAPROBADO'),
    centroRevision VARCHAR(50),
    ubicacionCentroRevision VARCHAR(50),
    numCertInspeccion VARCHAR(15),
    observaciones TINYTEXT,
    docCertificado VARCHAR(50) NOT NULL,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CodigoEjemplarRecurso) REFERENCES ejemplarRecursos(codigoEjemplarRecurso)
        ON UPDATE CASCADE,
    CONSTRAINT CHK_RevisionesTecnica_registroActivo CHECK (registroActivo IN (0, 1))
);

-- Tabla asignacion
CREATE TABLE asignacion (
    idAsignacion INT AUTO_INCREMENT PRIMARY KEY,
    id_personal INT NOT NULL,
    codigoEjemplarRecurso VARCHAR(7) NOT NULL,
    fechaAsignacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fechaDevolucion DATE,
    fechaRegistro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    FOREIGN KEY (CodigoEjemplarRecurso) REFERENCES ejemplarRecursos(CodigoEjemplarRecurso),
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal),
    CONSTRAINT CHK_Asignacion_registroActivo CHECK (registroActivo IN (0, 1))
);

-- Tabla tiposemergencia
CREATE TABLE IF NOT EXISTS tiposemergencia (
    id_tipo_emergencia INT AUTO_INCREMENT PRIMARY KEY,
    nombre_tipo VARCHAR(100) NOT NULL UNIQUE,
    descripcion TEXT NOT NULL,
    recursos_necesarios TEXT NOT NULL,
    protocolos TEXT NOT NULL,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_tiposEmergencia_registroActivo CHECK (registroActivo IN(0, 1))
);

-- Tabla incidencias
CREATE TABLE IF NOT EXISTS incidencias (
    id_incidencia INT AUTO_INCREMENT PRIMARY KEY,
    codigo_incidencia VARCHAR(20) NOT NULL UNIQUE,
    fecha_reporte TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_emergencia_id INT NOT NULL,
    descripcion TEXT NOT NULL,
    nivel_prioridad TEXT NOT NULL,
    estado TEXT DEFAULT 'Pendiente',
    ubicacion VARCHAR(255) NOT NULL,
    reportado_por VARCHAR(100) NOT NULL,
    atendido_por VARCHAR(100),
    fecha_atencion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_incidencias_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (tipo_emergencia_id) REFERENCES tiposemergencia(id_tipo_emergencia)
);

CREATE TABLE IF NOT EXISTS recursosasignados (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    id_incidencia INT NOT NULL,
    codigoEjemplarRecurso VARCHAR(7),
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_recursosasignados_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (CodigoEjemplarRecurso) REFERENCES ejemplarRecursos(CodigoEjemplarRecurso),
    FOREIGN KEY (id_incidencia) REFERENCES incidencias(id_incidencia)
);

-- Tabla bomberosasignados
CREATE TABLE IF NOT EXISTS bomberosasignados (
    id_asignacion INT AUTO_INCREMENT PRIMARY KEY,
    id_incidencia INT NOT NULL,
    id_personal INT,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_bomberosAsignados_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (id_incidencia) REFERENCES incidencias(id_incidencia),
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal)
);

-- Tabla reportesincidencias
CREATE TABLE IF NOT EXISTS reportesincidencias (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    id_incidencia INT NOT NULL,
    resumen TEXT NOT NULL,
    daños_materiales TEXT,
    victimas TEXT,
    tiempo_respuesta TIME NOT NULL,
    observaciones TEXT,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_reportesIncidencias_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (id_incidencia) REFERENCES incidencias(id_incidencia)
);

-- Tabla proveedor
CREATE TABLE IF NOT EXISTS proveedor (
    idProveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombres_proveedor VARCHAR(255) NOT NULL,
    apellidos_proveedor VARCHAR(255) NOT NULL,
    tipo_proveedor VARCHAR(20) NOT NULL,
    docIdentidad_proveedor VARCHAR(8) NOT NULL,
    tipo_docidentidad VARCHAR(3) NOT NULL,
    telefono VARCHAR(9) NOT NULL,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_proveedor_registroActivo CHECK (registroActivo IN (0, 1))
);

-- Tabla adquisicion
CREATE TABLE IF NOT EXISTS adquisicion (
    idadquisicion INT AUTO_INCREMENT PRIMARY KEY,
    nombre_adquisicion VARCHAR(255) NOT NULL,
    tipo_adquisicion VARCHAR(50) NOT NULL,
    fecha_adquisicion DATE NOT NULL,
    idProveedor INT NOT NULL,
    monto_estimado DECIMAL(18,2) NOT NULL,
    docaquisicion VARCHAR(30) NOT NULL,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_adquisicion_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (idProveedor) REFERENCES proveedor(idProveedor)
);

-- Tabla detalleaquisicion
CREATE TABLE IF NOT EXISTS detalleaquisicion (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    idadquisicion INT NOT NULL,
    codigoEjemplarRecurso VARCHAR(7),
    precio_unitario DECIMAL(18,2) NOT NULL,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_detalleAquisicion_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (CodigoEjemplarRecurso) REFERENCES ejemplarRecursos(CodigoEjemplarRecurso),
    FOREIGN KEY (idadquisicion) REFERENCES adquisicion(idadquisicion)
);

-- Tabla usuario
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(255),
    contrasena VARCHAR(255),
    account_no_locked BOOLEAN DEFAULT TRUE,
    account_no_expired BOOLEAN DEFAULT TRUE,
    credential_no_expired BOOLEAN DEFAULT TRUE,
    is_enabled BOOLEAN DEFAULT TRUE,
    email VARCHAR(255) UNIQUE,
    id_personal INT,
    FOREIGN KEY (id_personal) REFERENCES personal(id_personal)
);

-- Tabla roles
CREATE TABLE IF NOT EXISTS roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    rol_name ENUM('ADMIN', 'DEVELOPER', 'INVITED', 'USER') NOT NULL DEFAULT 'USER',
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_roles_registroActivo CHECK (registroActivo IN (0, 1))
);

-- Tabla usuarios_roles
CREATE TABLE IF NOT EXISTS usuarios_roles (
    id_usuario INT NOT NULL,
    id_rol INT NOT NULL,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    PRIMARY KEY (id_usuario, id_rol),
    CONSTRAINT CHK_usuarioRoles_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Tabla permisos
CREATE TABLE IF NOT EXISTS permisos (
    id_permisos INT AUTO_INCREMENT PRIMARY KEY,
    nombre_permiso VARCHAR(100),
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    CONSTRAINT CHK_permisos_registroActivo CHECK (registroActivo IN (0, 1))
);

-- Tabla roles_permisos
CREATE TABLE IF NOT EXISTS roles_permisos (
    id_rol INT NOT NULL,
    id_permiso INT NOT NULL,
    registroActivo TINYINT(1) DEFAULT 1 NOT NULL,
    PRIMARY KEY (id_rol, id_permiso),
    CONSTRAINT CHK_rolesPermisos_registroActivo CHECK (registroActivo IN (0, 1)),
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol),
    FOREIGN KEY (id_permiso) REFERENCES permisos(id_permisos)
);


-- Activar restricciones de clave foránea
SET FOREIGN_KEY_CHECKS = 0;

-- Inserción de permisos
INSERT INTO permisos (nombre_permiso) VALUES 
('CREATE'),    -- id = 1
('DELETE'),    -- id = 2
('READ'),      -- id = 3
('UPDATE'),    -- id = 4
('REFACTOR');  -- id = 5

-- Inserción de roles
INSERT INTO roles (rol_name) VALUES 
('ADMIN'),      -- id = 1
('DEVELOPER'),  -- id = 2
('USER'),       -- id = 3
('INVITED');    -- id = 4

-- Inserción de permisos por rol
INSERT INTO roles_permisos (id_rol, id_permiso) VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(3,1),
(3,2),
(4,3);  -- Asumiendo que "INVITED" solo puede leer

-- Inserción de usuario
INSERT INTO usuario (nombre_usuario, contrasena, email) VALUES
('Jhon', '$2a$10$wUhctvY6B.oFYYCchnOsNOwoiujmF46s38LIrWGYg/2r4.M.4/d9.', 'jd.huaman@unsm.edu.pe');

-- Asignación de rol al usuario
INSERT INTO usuarios_roles (id_usuario, id_rol) VALUES
(1,1);  -- Usuario Jhon es ADMIN

-- Rehabilitar las restricciones de clave foránea
SET FOREIGN_KEY_CHECKS = 1;







