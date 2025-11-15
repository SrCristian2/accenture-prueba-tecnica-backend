# 🏢 API de Gestión de Franquicias

API REST desarrollada con Spring Boot para la gestión completa de franquicias, sucursales y productos con operaciones CRUD completas y consultas avanzadas.

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED)](https://www.docker.com/)

---

## 📋 Descripción

Sistema completo de gestión de franquicias que permite administrar la estructura jerárquica de franquicias → sucursales → productos, con funcionalidades avanzadas como:

- ✨ Gestión completa de franquicias, sucursales y productos
- 🔍 Consultas avanzadas (producto con mayor stock por sucursal)
- 📊 Respuestas estructuradas con mensaje, estado y datos
- 🛡️ Validación robusta de datos de entrada
- ⚠️ Manejo global de excepciones
- 🌐 CORS habilitado para integración frontend
- 🐳 Completamente dockerizado

---

## 🚀 Tecnologías y Dependencias

### Core
- **Java 17** - Lenguaje de programación
- **Spring Boot 3.5.7** - Framework principal
- **Spring Data JPA** - Capa de persistencia
- **Spring Web** - REST API
- **Spring Validation** - Validación de datos

### Base de Datos
- **MySQL 8.0** - Sistema de gestión de base de datos
- **Hibernate** - ORM (incluido con Spring Data JPA)

### Utilidades
- **Lombok** - Reducción de código boilerplate
- **Spring Dotenv** - Gestión de variables de entorno
- **Spring DevTools** - Herramientas de desarrollo

### Containerización
- **Docker** - Containerización de aplicaciones
- **Docker Compose** - Orquestación multi-contenedor

---

## 📦 Requisitos Previos

### Usar API en Render (Sin Requisitos)
- 🌐 Solo necesitas un navegador o cliente HTTP (cURL, Postman, etc.)
- ⏱️ Paciencia para la primera petición (~60 segundos)

### Desarrollo Local (sin Docker)
- ☕ **Java 17** o superior
- 🗄️ **MySQL 8.0** o superior
- 📦 **Maven 3.6** o superior

### Con Docker (Recomendado para Desarrollo)
- 🐳 **Docker Desktop** instalado y en ejecución
- 🔧 **Docker Compose** (incluido con Docker Desktop)

---

## ⚙️ Instalación y Configuración

### 🌐 Opción 1: Usar API Desplegada en Render (Más Fácil)

**¡Acceso Instantáneo sin Instalación!**

La API está desplegada y disponible públicamente en Render:

```
https://accenture-prueba-tecnica-backend.onrender.com
```

**⚠️ IMPORTANTE - Primera Petición (Plan Gratuito de Render):**

Debido al plan gratuito de Render, la aplicación entra en "sleep" después de inactividad:

1. **La primera petición tomará ~50-60 segundos** en responder mientras el servidor se inicia
2. Las peticiones subsecuentes serán instantáneas (mientras el servidor esté activo)
3. Si no hay actividad por 15 minutos, el servidor volverá a "sleep"

**Ejemplo de uso:**
```powershell
# Primera petición (esperar ~60 segundos)
Invoke-RestMethod -Method GET -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/franchises"

# Peticiones siguientes serán rápidas
Invoke-RestMethod -Method POST -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/franchises" `
  -Body '{"name":"McDonald's"}' -ContentType "application/json"
```

**Endpoints disponibles:**
- Base URL: `https://accenture-prueba-tecnica-backend.onrender.com`
- Franquicias: `https://accenture-prueba-tecnica-backend.onrender.com/api/franchises`
- Sucursales: `https://accenture-prueba-tecnica-backend.onrender.com/api/branches`
- Productos: `https://accenture-prueba-tecnica-backend.onrender.com/api/products`

---

### 🐳 Opción 2: Docker Compose (Recomendado para Desarrollo)

**Inicio Rápido - 3 pasos:**

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/SrCristian2/accenture-prueba-tecnica-backend.git
   cd accenture-prueba-tecnica-backend
   ```

2. **Ejecutar Docker Compose**
   ```powershell
   # Opción recomendada: Usar el script automatizado
   .\run-docker.ps1
   
   # O ejecutar manualmente
   docker compose up --build -d
   ```

3. **Verificar que esté funcionando**
   ```powershell
   curl http://localhost:8080/api/franchises
   ```

**Comandos útiles de Docker:**
```bash
# Ver logs de la aplicación
docker logs franchise-app

# Ver logs en tiempo real
docker logs -f franchise-app

# Detener contenedores
docker compose down

# Detener y eliminar volúmenes (datos)
docker compose down -v

# Reconstruir tras cambios
docker compose up --build -d
```

---

### 💻 Opción 3: Desarrollo Local

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/SrCristian2/accenture-prueba-tecnica-backend.git
   cd accenture-prueba-tecnica-backend
   ```

2. **Configurar MySQL**
   ```sql
   CREATE DATABASE franchise_db;
   ```

3. **Configurar variables de entorno**
   
   Crear archivo `.env` en la raíz:
   ```env
   DB_HOST=localhost
   DB_PORT=3306
   DB_NAME=franchise_db
   DB_USERNAME=root
   DB_PASSWORD=tu_password
   JPA_DDL_AUTO=update
   JPA_SHOW_SQL=true
   SERVER_PORT=8080
   ```

4. **Compilar y ejecutar**
   ```powershell
   # Usando el script PowerShell
   .\run.ps1
   
   # O usando Maven
   mvn clean install
   mvn spring-boot:run
   
   # O ejecutar el JAR directamente
   mvn clean package
   java -jar target/franchise-api-0.0.1-SNAPSHOT.jar
   ```

La aplicación estará disponible en: **http://localhost:8080**

---

## 📚 Documentación de la API

### 🔄 Formato de Respuesta Estándar

Todas las respuestas siguen este formato:

**Éxito:**
```json
{
  "message": "Operation description",
  "state": "success",
  "data": { /* resultado */ }
}
```

**Error:**
```json
{
  "message": "Error description",
  "state": "error",
  "data": null
}
```

---

### 🏢 Endpoints - Franquicias

#### Crear Franquicia
```http
POST /api/franchises
Content-Type: application/json

{
  "name": "McDonald's"
}
```

**Respuesta:**
```json
{
  "message": "Franchise created successfully",
  "state": "success",
  "data": {
    "id": 1,
    "name": "McDonald's",
    "branches": []
  }
}
```

#### Listar Todas las Franquicias
```http
GET /api/franchises
```

#### Obtener Franquicia por ID
```http
GET /api/franchises/{id}
```

#### Actualizar Nombre de Franquicia
```http
PATCH /api/franchises/{id}/name
Content-Type: application/json

{
  "name": "McDonald's International"
}
```

#### Obtener Productos con Mayor Stock por Sucursal
```http
GET /api/franchises/{id}/products-max-stock
```

**Respuesta:**
```json
{
  "message": "Products with max stock retrieved successfully",
  "state": "success",
  "data": [
    {
      "productId": 2,
      "productName": "Papas Fritas",
      "stock": 120,
      "branchId": 1,
      "branchName": "McDonald's centro"
    },
    {
      "productId": 5,
      "productName": "Hamburguesa",
      "stock": 90,
      "branchId": 2,
      "branchName": "McDonald's poblado"
    }
  ]
}
```

---

### 🏪 Endpoints - Sucursales

#### Crear Sucursal
```http
POST /api/branches
Content-Type: application/json

{
  "name": "McDonald's centro",
  "franchiseId": 1
}
```

#### Listar Todas las Sucursales
```http
GET /api/branches
```

#### Obtener Sucursal por ID
```http
GET /api/branches/{id}
```

#### Listar Sucursales de una Franquicia
```http
GET /api/branches/franchise/{franchiseId}
```

#### Actualizar Nombre de Sucursal
```http
PATCH /api/branches/{id}/name
Content-Type: application/json

{
  "name": "McDonald's centro dos"
}
```

---

### 📦 Endpoints - Productos

#### Crear Producto
```http
POST /api/products
Content-Type: application/json

{
  "name": "Big Mac",
  "stock": 50,
  "branchId": 1
}
```

#### Listar Todos los Productos
```http
GET /api/products
```

#### Obtener Producto por ID
```http
GET /api/products/{id}
```

#### Listar Productos de una Sucursal
```http
GET /api/products/branch/{branchId}
```

#### Actualizar Stock de Producto
```http
PATCH /api/products/{id}/stock
Content-Type: application/json

{
  "stock": 150
}
```

#### Actualizar Nombre de Producto
```http
PATCH /api/products/{id}/name
Content-Type: application/json

{
  "name": "Big Mac Deluxe"
}
```

#### Eliminar Producto
```http
DELETE /api/products/{id}
```

**Respuesta:**
```json
{
  "message": "Product deleted successfully",
  "state": "success",
  "data": null
}
```

---

## 📊 Modelo de Datos

### Diagrama de Relaciones
```
Franchise (1) ----< (N) Branch (1) ----< (N) Product
```

### Entidades

#### **Franchise**
```java
{
  "id": Long,           // PK, Auto-increment
  "name": String,       // NOT NULL, único
  "branches": List<Branch>  // Relación One-to-Many
}
```

#### **Branch**
```java
{
  "id": Long,           // PK, Auto-increment
  "name": String,       // NOT NULL
  "franchise": Franchise,   // FK, NOT NULL
  "products": List<Product>  // Relación One-to-Many
}
```

#### **Product**
```java
{
  "id": Long,           // PK, Auto-increment
  "name": String,       // NOT NULL
  "stock": Integer,     // NOT NULL, >= 0
  "branch": Branch      // FK, NOT NULL
}
```

---

## 🏗️ Arquitectura del Proyecto

```
src/
└── main/
    └── java/
        └── com/franchise/api/
            ├── 📁 config/           # Configuración (CORS, etc.)
            │   └── WebConfig.java
            ├── 📁 controller/       # Controladores REST
            │   ├── FranchiseController.java
            │   ├── BranchController.java
            │   └── ProductController.java
            ├── 📁 dto/              # Data Transfer Objects
            │   ├── ApiResponse.java
            │   ├── FranchiseRequestDTO.java
            │   ├── BranchRequestDTO.java
            │   ├── ProductRequestDTO.java
            │   ├── ProductMaxStockDTO.java
            │   ├── UpdateNameDTO.java
            │   └── UpdateStockDTO.java
            ├── 📁 exception/        # Manejo global de excepciones
            │   ├── GlobalExceptionHandler.java
            │   └── ResourceNotFoundException.java
            ├── 📁 model/            # Entidades JPA
            │   ├── Franchise.java
            │   ├── Branch.java
            │   └── Product.java
            ├── 📁 repository/       # Interfaces de repositorio
            │   ├── FranchiseRepository.java
            │   ├── BranchRepository.java
            │   └── ProductRepository.java
            ├── 📁 service/          # Lógica de negocio
            │   ├── FranchiseService.java
            │   ├── BranchService.java
            │   └── ProductService.java
            └── FranchiseApplication.java  # Clase principal
```

---

## 🎯 Criterios de Aceptación

### ✅ Requisitos Funcionales Cumplidos

| Requisito | Estado | Endpoint |
|-----------|--------|----------|
| Agregar nueva franquicia | ✅ | `POST /api/franchises` |
| Agregar sucursal a franquicia | ✅ | `POST /api/branches` |
| Agregar producto a sucursal | ✅ | `POST /api/products` |
| Eliminar producto | ✅ | `DELETE /api/products/{id}` |
| Modificar stock de producto | ✅ | `PATCH /api/products/{id}/stock` |
| Producto con más stock por sucursal | ✅ | `GET /api/franchises/{id}/products-max-stock` |
| Sistema de persistencia MySQL | ✅ | MySQL + JPA/Hibernate |

### ⭐ Puntos Extra Implementados

- ✅ **Docker**: Aplicación completamente dockerizada con Docker Compose
- ✅ **Actualizar nombre de franquicia**: `PATCH /api/franchises/{id}/name`
- ✅ **Actualizar nombre de sucursal**: `PATCH /api/branches/{id}/name`
- ✅ **Actualizar nombre de producto**: `PATCH /api/products/{id}/name`
- ✅ **Respuestas estructuradas**: Formato `ApiResponse<T>` consistente
- ✅ **Manejo de excepciones**: `GlobalExceptionHandler` centralizado
- ✅ **Validación de datos**: Bean Validation en todos los DTOs
- ✅ **CORS configurado**: Permite peticiones desde cualquier origen
- ✅ **Documentación completa**: README exhaustivo con ejemplos

---

## 🛡️ Manejo de Errores

### Tipos de Errores

#### 404 - Recurso No Encontrado
```json
{
  "message": "Franchise not found with id: 999",
  "state": "error",
  "data": null
}
```

#### 400 - Error de Validación
```json
{
  "message": "Validation error",
  "state": "error",
  "data": {
    "name": "must not be blank",
    "stock": "must be greater than or equal to 0"
  }
}
```

#### 500 - Error Interno del Servidor
```json
{
  "message": "Internal server error: ...",
  "state": "error",
  "data": null
}
```

---

## 🧪 Validación y Pruebas

### Probar API en Render

**Importante:** La primera petición tomará ~60 segundos debido al plan gratuito de Render.

```powershell
# Esperar a que el servidor inicie (primera vez - plan gratuito)
Invoke-RestMethod -Method GET -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/franchises"

# Crear franquicia
$body = @{ name = "franquicia de pueba" } | ConvertTo-Json
Invoke-RestMethod -Method POST -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/franchises" `
  -Body $body -ContentType "application/json"

# Listar franquicias
Invoke-RestMethod -Method GET -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/franchises"
```

### Pruebas Manuales de Endpoints

**Local:**
```powershell
$body = @{ name = "Test Franchise" } | ConvertTo-Json
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/franchises" -Body $body -ContentType "application/json"
```

**Render (Producción):**
```powershell
# NOTA: Primera petición toma ~60 segundos (plan gratuito)
$body = @{ name = "Test Franchise" } | ConvertTo-Json
Invoke-RestMethod -Method POST -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/franchises" -Body $body -ContentType "application/json"
```

### Tests Automatizados

> 📝 **Nota**: Los tests unitarios están planificados para futuras versiones. Actualmente la validación se realiza mediante:
> - ✅ Pruebas manuales de endpoints
> - ✅ Validación de DTOs con Bean Validation
> - ✅ Manejo de excepciones con GlobalExceptionHandler
> - ✅ Verificación en entornos de desarrollo y producción

---

## 🔐 Configuración de Seguridad

- 🔑 Variables de entorno para credenciales sensibles
- 🚫 Archivo `.env` excluido del control de versiones
- 📋 Plantilla `.env.example` incluida
- 🌐 CORS habilitado para desarrollo (configurar en producción)

---

## 🐳 Configuración Docker

### Scripts de Automatización

El proyecto incluye scripts PowerShell para facilitar el despliegue:

#### **run-docker.ps1** - Ejecución con Docker
```powershell
# Script para ejecutar la aplicación con Docker Compose

Write-Host "=== Starting Franchise Application with Docker ===" -ForegroundColor Green
Write-Host ""

# Verificar si Docker está instalado
try {
    $dockerVersion = docker --version
    Write-Host "✓ Docker found: $dockerVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Docker is not installed or not in PATH" -ForegroundColor Red
    exit 1
}

# Verificar si Docker Compose está instalado
try {
    $composeVersion = docker-compose --version
    Write-Host "✓ Docker Compose found: $composeVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Docker Compose is not installed" -ForegroundColor Red
    exit 1
}

Write-Host ""
Write-Host "=== Building and starting containers ===" -ForegroundColor Cyan
Write-Host "This may take several minutes the first time..." -ForegroundColor Yellow
Write-Host ""

docker-compose up --build

if ($LASTEXITCODE -ne 0) {
    Write-Host ""
    Write-Host "✗ Error starting containers" -ForegroundColor Red
    exit 1
}
```

#### **run.ps1** - Ejecución Local
```powershell
# Script para ejecutar la aplicación localmente

Write-Host "=== Starting Franchise Application ===" -ForegroundColor Green
Write-Host ""

# Verificar si Java está instalado
try {
    $javaVersion = java -version 2>&1 | Select-String "version"
    Write-Host "✓ Java found: $javaVersion" -ForegroundColor Green
} catch {
    Write-Host "✗ Java is not installed or not in PATH" -ForegroundColor Red
    exit 1
}

# Verificar si Maven está instalado
$useMvnw = $false
try {
    $mavenVersion = mvn -version 2>&1 | Select-String "Apache Maven"
    Write-Host "✓ Maven found: $mavenVersion" -ForegroundColor Green
} catch {
    Write-Host "! Maven not found, using Maven Wrapper" -ForegroundColor Yellow
    $useMvnw = $true
}

Write-Host ""
Write-Host "=== Compiling the project ===" -ForegroundColor Cyan

if ($useMvnw) {
    .\mvnw.cmd clean package -DskipTests
} else {
    mvn clean package -DskipTests
}

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "✓ Compilation successful" -ForegroundColor Green
    Write-Host ""
    Write-Host "=== Starting the application ===" -ForegroundColor Cyan
    Write-Host "The application will be available at: http://localhost:8080" -ForegroundColor Yellow
    Write-Host "Press Ctrl+C to stop the application" -ForegroundColor Yellow
    Write-Host ""
    
    if ($useMvnw) {
        .\mvnw.cmd spring-boot:run
    } else {
        mvn spring-boot:run
    }
} else {
    Write-Host ""
    Write-Host "✗ Compilation error" -ForegroundColor Red
    exit 1
}
```

**Características de los scripts:**
- ✅ Verificación automática de dependencias (Java, Maven, Docker)
- 🔄 Uso de Maven Wrapper si Maven no está instalado
- 🎨 Salida con colores para mejor legibilidad
- ⚠️ Validación de errores en cada paso
- 📝 Mensajes informativos del progreso

---

### docker-compose.yml
```yaml
services:
  mysql:
    image: mysql:8.0
    ports:
      - "3308:3306"
    environment:
      MYSQL_ROOT_PASSWORD: root123
      MYSQL_DATABASE: franchise_db
    
  app:
    build: .
    depends_on:
      mysql:
        condition: service_healthy
    ports:
      - "8080:8080"
    environment:
      DB_HOST: mysql
      DB_PORT: 3306
```

### Puertos Expuestos
- **8080**: API REST
- **3308**: MySQL (mapeado desde 3306 interno)

---

## 📝 Ejemplos de Uso

### Ejemplo con API Local

```powershell
# 1. Crear franquicia
$franchise = Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/franchises" `
  -Body '{"name":"McDonald's"}' -ContentType "application/json"

# 2. Crear sucursal
$branch = Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/branches" `
  -Body "{`"name`":`"Downtown`",`"franchiseId`":$($franchise.data.id)}" `
  -ContentType "application/json"

# 3. Crear productos
Invoke-RestMethod -Method POST -Uri "http://localhost:8080/api/products" `
  -Body "{`"name`":`"Big Mac`",`"stock`":50,`"branchId`":$($branch.data.id)}" `
  -ContentType "application/json"

# 4. Consultar productos con mayor stock
Invoke-RestMethod -Method GET `
  -Uri "http://localhost:8080/api/franchises/$($franchise.data.id)/products-max-stock"
```

### Ejemplo con API en Render

```powershell
# ⚠️ IMPORTANTE: La primera petición tomará ~60 segundos (plan gratuito de Render)

# 1. Crear franquicia (esperar inicio del servidor - solo la primera vez)
$franchise = Invoke-RestMethod -Method POST -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/franchises" `
  -Body '{"name":"McDonald's"}' -ContentType "application/json"

# 2. Crear sucursal (ya será rápido)
$branch = Invoke-RestMethod -Method POST -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/branches" `
  -Body "{`"name`":`"Downtown`",`"franchiseId`":$($franchise.data.id)}" `
  -ContentType "application/json"

# 3. Crear productos
Invoke-RestMethod -Method POST -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/products" `
  -Body "{`"name`":`"Big Mac`",`"stock`":50,`"branchId`":$($branch.data.id)}" `
  -ContentType "application/json"

# 4. Consultar productos con mayor stock
Invoke-RestMethod -Method GET `
  -Uri "https://accenture-prueba-tecnica-backend.onrender.com/api/franchises/$($franchise.data.id)/products-max-stock"
```
