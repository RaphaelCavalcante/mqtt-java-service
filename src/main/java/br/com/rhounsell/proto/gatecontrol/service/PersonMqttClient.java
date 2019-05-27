package br.com.rhounsell.proto.gatecontrol.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class PersonMqttClient implements MqttCallback {
	private final int qos = 1;
	private MqttClient client;
	private String topic;
	public PersonMqttClient(String uri) throws MqttException, URISyntaxException {
		this(new URI(uri));
	}

	public PersonMqttClient(URI uri) throws MqttException {
		String host = String.format("tcp://%s:%d", uri.getHost(), uri.getPort());
		String[] auth = this.getAuth(uri);
		String username = auth[0];
		String password = auth[1];
		String clientId = "MQTT-Java-Example";
		if (!uri.getPath().isEmpty()) {
			this.topic = uri.getPath().substring(1);
		}

		MqttConnectOptions conOpt = new MqttConnectOptions();
		conOpt.setCleanSession(true);
		conOpt.setUserName(username);
		conOpt.setPassword(password.toCharArray());

		this.client = new MqttClient(host, clientId, new MemoryPersistence());
		this.client.setCallback(this);
		this.client.connect(conOpt);

		this.client.subscribe(this.topic, qos);
	}



	private String[] getAuth(URI uri) {
		String a = uri.getAuthority();
		String[] first = a.split("@");
		return first[0].split(":");
	}

	@Override
	public void connectionLost(Throwable cause) {

	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(topic);

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub
		System.out.println(token);
	}

}
