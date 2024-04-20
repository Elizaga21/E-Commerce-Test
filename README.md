# E-Commerce-Test
SOFTWARE ENGINEER  TEST - Elisabet Agulló 

Este proyecto es un plataforma e-commerce desarrollada en Java y Spring Boot.

Para ejecutar el proyecto se debe ejecutar el archivo "TestSoftwareEngineerApplication", no he añadido Spring Security para poder hacer el CRUD más rápido y podáis comprobarlo sin utilizar una contraseña generada por la seguridad. 

POSTMAN (Para realizar peticiones y comprobar que funciona):

POST: http://localhost:8080/carts?id=1

GET: http://localhost:8080/carts/1 

POST: http://localhost:8080/carts/1/products y el JSON {
    "id": 1,
    "description": "Product 1",
    "amount": 10.99
}


DELETE: http://localhost:8080/carts/1 
