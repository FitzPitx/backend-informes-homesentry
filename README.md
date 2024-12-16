
# 📦 Homesentry Microservices

## 🚀 **Introducción del Proyecto**

Este proyecto consiste en una arquitectura basada en microservicios para gestionar información relacionada con categorías, sucursales y acumulados. Utiliza Spring Boot, Eureka Discovery, y un servidor de configuración centralizado.

### **Arquitectura General**

- **Microservicio Acumulado** (`microservice-acumulado`)
- **Microservicio Sucursales** (`microservice-branches`)
- **Microservicio Categorías** (`microservice-categories`)
- **Servidor de Configuración** (`microservice-config`)

Cada microservicio se registra en un servidor de descubrimiento Eureka y obtiene su configuración centralizada a través del servidor de configuración.

---

## 📚 **Tabla de Contenidos**

1.[Descripción de los Microservicios](#descripción-de-los-microservicios)
2.[Instalación y Configuración](#instalación-y-configuración)
3.[Cómo Ejecutar el Proyecto](#cómo-ejecutar-el-proyecto)
4.[Documentación de API](#documentación-de-api)
5.[Configuración de Eureka](#configuración-de-eureka)
6.[Configuración del Servidor de Configuración](#configuración-del-servidor-de-configuración)
7.[Autenticación y Seguridad](#autenticación-y-seguridad)
8.[Monitoreo y Logging](#monitoreo-y-logging)
9.[Despliegue](#despliegue)
10.[Pruebas](#pruebas)
11.[Contribución](#contribución)
12.[Licencia](#licencia)
13.[Contacto](#contacto)

---

## 📦 **Descripción de los Microservicios**

### 1. **Microservicio Acumulado** (`microservice-acumulado`)

- **Descripción**: Gestiona operaciones relacionadas con acumulados por categoría.
- **Puerto por defecto**: `8081`
- **Endpoints**:
    - `GET /api/v1/acumulado` - Obtiene acumulados por categoría.

---

### 2. **Microservicio Sucursales** (`microservice-branches`)

- **Descripción**: Maneja información de sucursales.
- **Puerto por defecto**: `8082`
- **Endpoints**:
    - `GET /api/v1/branches` - Lista todas las sucursales.

---

### 3. **Microservicio Categorías** (`microservice-categories`)

- **Descripción**: Gestiona la información de categorías.
- **Puerto por defecto**: `8083`
- **Endpoints**:
    - `GET /api/v1/categories` - Lista todas las categorías.

---

### 4. **Servidor de Configuración** (`microservice-config`)

- **Descripción**: Proporciona configuración centralizada para los microservicios.
- **Puerto por defecto**: `8888`

---

## ⚙️ **Instalación y Configuración**

### **Requisitos Previos**

- **Java 11 o superior**
- **Maven**

### **Instalación**

```bash
git clone https://github.com/FitzPitx/backend-informes-homesentry
cd HomesentryMicroservices
mvn clean install
```

---

## ▶️ **Cómo Ejecutar el Proyecto**

### **Servidor de Configuración**

```bash
cd microservice-config
mvn spring-boot:run
```

### **Microservicios**

```bash
cd microservice-acumulado
mvn spring-boot:run

cd microservice-branches
mvn spring-boot:run

cd microservice-categories
mvn spring-boot:run
```

---

## 📜 **Documentación de API**

- **Microservicio Acumulado**: `http://localhost:8081/swagger-ui.html`
- **Microservicio Sucursales**: `http://localhost:8082/swagger-ui.html`
- **Microservicio Categorías**: `http://localhost:8083/swagger-ui.html`

---

## 🔍 **Configuración de Eureka**

- **URL del Dashboard de Eureka**: `http://localhost:8761`

---

## 🚢 **Despliegue**

*(Instrucciones para Docker/Kubernetes si aplica)*

---

## 🧪 **Pruebas**

```bash
mvn test
```

---

## 🤝 **Contribución**

1. Crea un fork del repositorio.
2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`).
3. Haz un commit (`git commit -m "Descripción del cambio"`).
4. Envía tu rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

---

## 📄 **Licencia**

Este proyecto está bajo la licencia **MIT**.

---

## 📧 **Contacto**

- **Nombre**: Andres Felipe Nieto Jimenez
- **Email**: <nieto2013jimenez@gmail.com>
