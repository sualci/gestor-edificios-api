package com.clara.springboot.app.gestor_edificios_api.mqtt;

import com.clara.springboot.app.gestor_edificios_api.service.HabitacionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TemperaturaSubscriberService {

    private static final Logger log = LoggerFactory.getLogger(TemperaturaSubscriberService.class);

    private final HabitacionService habitacionService;
    private final MqttPahoMessageDrivenChannelAdapter adapter;


    public TemperaturaSubscriberService(HabitacionService habitacionService,
                                        MqttPahoClientFactory clientFactory) {
        this.habitacionService = habitacionService;

        // Configuración del adaptador
        this.adapter = new MqttPahoMessageDrivenChannelAdapter(
                "test-demo-subscriber",
                clientFactory,
                "habitacion/+/temperatura");

        this.adapter.setCompletionTimeout(5000);
        this.adapter.setConverter(new DefaultPahoMessageConverter());
        this.adapter.setQos(1);

        // Configurar el manejador
        this.adapter.setOutputChannelName("temperaturaChannel");
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "temperaturaChannel")
    public void handleMessage(Message<?> message) {
        try {
            String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
            String payload = (String) message.getPayload();

            // Procesamiento del mensaje recibido
            String[] topicParts = topic.split("/");
            Long habitacionId = Long.parseLong(topicParts[1]);
            Double temperatura = Double.parseDouble(payload);

            log.info("Actualizando temperatura - Habitación: {}, Temperatura: {}", habitacionId, temperatura);
            // se llama al servicio
            this.updateRoomTemperature(habitacionId, temperatura);
        } catch (Exception e) {
            log.error("Error procesando mensaje MQTT", e);
        }
    }

    @Transactional
    private void updateRoomTemperature(Long roomId, Double temperatura) {
        habitacionService.updateTemperaturaActual(roomId, temperatura);
    }
}
