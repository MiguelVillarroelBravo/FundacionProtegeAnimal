# 🐾 Fundación Animal — Sistema de Gestión

# 

# Sistema web para una fundación de rescate animal, desarrollado como proyecto de la asignatura Ingeniería en Software. Permite gestionar donaciones, inventario, apadrinamientos, voluntariados y alertas a través de una arquitectura de microservicios.

# 

# 

# 🏗️ Arquitectura

# 

# El sistema está construido con una arquitectura de microservicios en el backend y un frontend SPA en React.

# 

# Frontend (React + Vite)

#         │

#         ▼

#    API Gateway :8080

#         │

#    ┌────┴─────────────────────────────────┐

#    │                                      │

#    ▼          ▼         ▼        ▼        ▼

# ms-donacion  ms-inventario  ms-alertas  ms-apadrinamiento  ms-voluntariado

#   :8081         :8082          :8083         :8084              :8085

# 

# 

# 🧩 Microservicios

# 

# MicroservicioPuertoDescripciónms-gateway8080API Gateway — enruta todas las peticiones del frontendms-donacion8081Gestión de donaciones y donantesms-inventario8082Control de insumos e inventario de la fundaciónms-alertas8083Alertas internas de la fundaciónms-apadrinamiento8084Gestión de padrinos (personas que aportan mensualmente a un animal específico)ms-voluntariado8085Registro y gestión de voluntarios

# 

# 

# 💻 Tecnologías

# 

# Backend

# 

# 

# Java 17

# Spring Boot

# Spring Cloud Gateway

# Spring Data JPA

# Lombok

# MySQL (XAMPP / phpMyAdmin en desarrollo local)

# 

# 

# Frontend

# 

# 

# React + Vite

# Bootstrap 5

# Axios

# 

# 

# 

# ⚙️ Requisitos previos

# 

# 

# Java 17+

# Node.js 18+

# Maven

# XAMPP (con MySQL activo en puerto 3306)

# Git

# 

# 

# 

# 🚀 Instalación y ejecución local

# 

# 1\. Clonar el repositorio

# 

# bashgit clone https://github.com/usuario/fundacion-animal.git

# cd fundacion-animal

# 

# 2\. Configurar la base de datos

# 

# Inicia XAMPP y asegúrate de que MySQL esté corriendo. Crea las bases de datos necesarias desde phpMyAdmin (una por microservicio según la configuración de cada application.properties).

# 

# 3\. Iniciar los microservicios (en orden)

# 

# Entra a cada carpeta de microservicio y ejecuta:

# 

# bash./mvnw spring-boot:run

# 

# Orden recomendado:

# 

# 

# ms-donacion

# ms-inventario

# ms-alertas

# ms-apadrinamiento

# ms-voluntariado

# ms-gateway (siempre último)

# 

# 

# 4\. Iniciar el frontend

# 

# bashcd frontend

# npm install

# npm run dev

# 

# El frontend estará disponible en http://localhost:5173.

# 

# 

# 🌐 Vistas

# 

# Vista pública (usuario/donante)

# 

# 

# Donaciones — Formulario para realizar una donación (monto, datos del donante)

# Apadrinamiento — Formulario para convertirse en padrino de un animal

# Voluntariado — Formulario para registrarse como voluntario

# 

# 

# Vista administrativa

# 

# 

# Gestión de Donaciones — Listado, edición y eliminación de donaciones registradas

# Inventario — Creación y gestión de insumos

# Alertas — Visualización y gestión de alertas internas

# 

# 

# 

# 📁 Estructura del repositorio

# 

# fundacion-animal/

# ├── ms-gateway/

# ├── ms-donacion/

# ├── ms-inventario/

# ├── ms-alertas/

# ├── ms-apadrinamiento/

# ├── ms-voluntariado/

# └── frontend/

# 

# 

# ☁️ Despliegue en la nube (objetivo)

# 

# El proyecto está pensado para ser desplegado en una plataforma cloud. Opciones recomendadas para este stack:

# 

# 

# Railway — Soporte nativo para Spring Boot y MySQL, despliegue simple desde GitHub

# Render — Alternativa gratuita para servicios web y bases de datos

# Azure Spring Apps — Opción institucional con soporte oficial para microservicios Java

# 

# 

# 

# Para el despliegue será necesario reemplazar la base de datos local (XAMPP) por un servicio de base de datos en la nube (Railway MySQL, PlanetScale, Azure Database for MySQL, etc.) y actualizar las variables de entorno en cada microservicio.

# 

# 

# 

# 

# 👥 Autores

# 

# Proyecto desarrollado en dupla para la asignatura Ingeniería en Software.

