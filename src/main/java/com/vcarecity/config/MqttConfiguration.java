package com.vcarecity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 * 读取yml
 * @author Administrator
 *
 */
@Component
@ConfigurationProperties(prefix = "com.mqtt")
public class MqttConfiguration {
	private String url;
	private String clientId;
	private String topics;
    private String username;
    private String password;
    private String timeout;
    private String keepalive;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTopics() {
		return topics;
	}
	public void setTopics(String topics) {
		this.topics = topics;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	public String getKeepalive() {
		return keepalive;
	}
	public void setKeepalive(String keepalive) {
		this.keepalive = keepalive;
	}
}

