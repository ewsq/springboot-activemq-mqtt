package com.vcarecity.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface  MqttGateway {
	
	void sendToMqtt(String payload);
	
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic,String payload);

	void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
	
	void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos,  @Header(MqttHeaders.RETAINED) String retain,String payload);


}
