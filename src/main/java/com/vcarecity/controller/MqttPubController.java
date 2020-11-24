package com.vcarecity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vcarecity.mqtt.MqttGateway;

@RestController
public class MqttPubController {
	
	@Autowired
	private MqttGateway mqttGateway;

	
	@RequestMapping("/hello")
	public String hello() {
		return "hello!";
	}
	
	 @RequestMapping("/sendMqtt")
	    public String sendMqtt(String  sendData){
	    	System.out.println("进入sendMqtt-------");
	        mqttGateway.sendToMqtt("payload","topic3");
	        return "OK";
	    }
	
}
