# Auth Service

Microservicio de autenticación desarrollado en **Java 17** con **Spring Boot**.  
Permite el registro de usuarios, validación de contraseñas y autenticación basada en **JWT**.

---

## 🚀 Requisitos

- Java 17+
- Maven 3.8+ (puedes usar el wrapper incluido: `./mvnw`)

---

## ⚙️ Configuración

El archivo `application.yml` define:
- Puerto de la aplicación (por defecto `8080`)
- Reglas de validación de contraseñas
- Configuración de seguridad y JWT

---

## ▶️ Ejecución

### Usando Maven

## En Linux / MacOS
```bash
./mvnw clean install
./mvnw spring-boot:run
```

## En Windows (PowerShell o CMD)
```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```
## 🧪 Pruebas con Postman

### 🔹 Crear nueva petición
- Método: **POST**
- URL:
```
http://localhost:8080/api/auth/sign-up
```
### 🔹 Headers
```
Accept: application/json
Content-Type: application/json
```
### 🔹 Body
```
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "Hunter25",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

### 🔹 Response
```
{
    "id": "a308f614-c7d2-4757-9f42-00537ae16a20",
    "created": "2025-09-18T12:33:52.378304",
    "lastLogin": "2025-09-18T12:33:52.378304",
    "modified": "2025-09-18T12:33:52.378304",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhMzA4ZjYxNC1jN2QyLTQ3NTctOWY0Mi0wMDUzN2FlMTZhMjAiLCJlbWFpbCI6Imp1YW5Acm9kcmlndWV6Lm9yZyIsImlhdCI6MTc1ODIxNjgzMiwiZXhwIjoxNzU4MzAzMjMyfQ.wft1BP7cDnAenHrwJWIE7by9lNWBrRH60oaC4b1Ld2g",
    "isActive": true
}
```