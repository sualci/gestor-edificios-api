# Api - Gestión de Edificios
El proyecto consiste en una api para la gestion de edificios y habitaciones

## 1. Tecnologias
- IDE Intellj
- Springboot version 3.4.4
- Java  17.0.14
- Apache Maven 3.9.9
- MQTT
- H2 Database - para crear fácilmente la BD

## 2. Documentación API
Para probar la api se ha usado Postman. Se adjunta el archivo con las peticiones para abrir directamente en postman:
[Uploading api-gestionEdificios.postman_collection.json…]()

### Api Edificios
- GET /api/edificios : devuelve lista de todos los edificios
- GET /api/edificios/{edificioId} : devuelve datos del edificio concreto
- POST /api/edificios/ : crea nuevo edificio
- PUT /api/edificios/{edificioId} : actualiza campos del edificio
- DEL /api/edificios/{edificioId} : elimina edificio
- GET /api/edificios/{edificioId}/habitaciones : lista todas las habitaciones del edificio 

### Api Habitaciones
- GET /api/habitaciones/edificio/{edificioId} : lista todas las habitaciones del edificio
- GET /api/habitaciones/{habitacionId} : devuelve datos de la habitacion
- POST /api/habitaciones/edificio/{edificioId}" : crea habitacion en un edificio
- PUT /api/habitaciones/{habitacionId} : actualiza habitacion
- DEL /api/habitaciones/{habitacionId} : elimina habitacion


## 3. Parte IoT
Se ha añadido una pequeña parte relacionada con IoT. La idea es que existan sensores en las habitaciones que publiquen en un topic concreto (habitacion/{habitacionId}/temperatura) los valores que están midiendo de temperatura.

De esta forma, en la aplicacion lo que se hace es configurar el broker MQTT de prueba (test.mosquitto.org) al que se conecta, y se subscribe al topic (habitacion/+/temperatura). Cuando se publican mensajes en esos temas, el subscrptor lo que hace es llamar a un servicio que actualiza el atributo de temperaturaActual de la Habitacion concreta.

Para probar o simular la publicacion de datos de MQTT se puede instalar Mosquitto (https://mosquitto-org.translate.goog/download/?_x_tr_sl=en&_x_tr_tl=es&_x_tr_hl=es&_x_tr_pto=rq) y usar los siguientes comandos:

- Para publicar en un tema (usando broker local o broker de prueba test.mosquitto.org):
> mosquitto_pub -h localhost -t "habitacion/34/temperatura″ -m "22.3"

> mosquitto_sub -h test.mosquitto.org -t "habitacion/34/temperatura″

- Para subscribirse a un tema:
> mosquitto_sub -h localhost -t "habitacion/34/temperatura″

> mosquitto_sub -h test.mosquitto.org -t "habitacion/34/temperatura″

En el application.properties se pueden activar los logs de DEBUG para la parte de MQTT.

## 4. Cómo montar proyecto y ejecutarlo
- Abrir proyecto en Intelj
- Asegurarse de que se está utilizando las versiones correctas, Java 17 y Apache Maven 3.9.9

Ejecutar:
- clean compile y clean install
- spring-boot:run

En cuanto a la BD no hay que hacer ninguna configuracion más ya que se ha optado por usar H2 Database que va integrada y que permite la persistencia de los datos entre ejecuciones.


## 5. Algunas mejoras posibles
- Manejo de más errores y más test unitarios
- Uso de DTOs en todos los métodos del controlador

