package com.vcarecity.mqtt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.vcarecity.config.MqttConfiguration;

/**
 * MQTT消费端
 * 
 * @author Administrator
 *
 */
@Configuration
public class MqttInboundConfiguration {
	
	@Autowired
	private MqttConfiguration mqttProperties;
	

	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		    String[] array = mqttProperties.getUrl().split(",");
			MqttConnectOptions options = new MqttConnectOptions();
			options.setServerURIs(array);
			options.setUserName(mqttProperties.getUsername());
			options.setPassword(mqttProperties.getPassword().toCharArray());
			//接受离线消息
			options.setCleanSession(false);
			factory.setConnectionOptions(options);
		return factory;
	}
	
	@Bean
	public MessageProducer inbound() {
		String[] inboundTopics = mqttProperties.getTopics().split(",");
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
				mqttProperties.getClientId()+"_inbound",mqttClientFactory(), inboundTopics);
		adapter.setCompletionTimeout(5000);
		adapter.setQos(1);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}

	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {

		return new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				System.out.println("message:"+message);
				String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
				System.out.println("topic:"+topic);

			}

		};
	}
}
