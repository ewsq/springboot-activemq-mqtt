package com.vcarecity.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.vcarecity.config.MqttConfiguration;

/**
 * MQTT生产端
 * 
 * @author Administrator
 *
 */
@Configuration
public class MqttOutboundConfiguration {
	@Autowired
	private MqttConfiguration mqttProperties;

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		String[] array = mqttProperties.getUrl().split(",");
		MqttConnectOptions options = new MqttConnectOptions();
		options.setServerURIs(array);
		options.setUserName(mqttProperties.getUsername());
		options.setPassword(mqttProperties.getPassword().toCharArray());
		// 接受离线消息
		options.setCleanSession(false);
		factory.setConnectionOptions(options);
		return factory;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttOutboundChannel")
	public MessageHandler mqttOutbound() {
		MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
				mqttProperties.getClientId()+"outbound", mqttClientFactory());
		messageHandler.setAsync(true);
		return messageHandler;
	}

	@Bean
	public MessageChannel mqttOutboundChannel() {
		return new DirectChannel();
	}

}
