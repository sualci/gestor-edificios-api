package com.clara.springboot.app.gestor_edificios_api;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

@SpringBootTest
public class IntegracionMqttTests {

    @Autowired
    private MqttPahoClientFactory clientFactory;


    /**
     * Test para probar unicamente si funciona la conexión al broker y la publicacion de datos al topic
     */
    @Test
    public void testMensajeTemperatura() throws Exception {
        MqttClient client = new MqttClient("tcp://test.mosquitto.org:1883", "test-demo");
        client.connect();

        // Publica un mensaje de prueba
        client.publish("habitacion/34/temperatura", "25.5".getBytes(), 1, false);

        client.disconnect();

        // Espera para asegurarse de que se recibe el mensaje en el broker
        Thread.sleep(2000);
    }
}