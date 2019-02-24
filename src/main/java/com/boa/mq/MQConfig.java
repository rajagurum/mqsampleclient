package com.boa.mq;


import javax.jms.JMSException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.ibm.mq.jms.MQQueueConnectionFactory;

@Configuration
@EnableJms
public class MQConfig {

	@Bean
	@Scope("prototype")
	public MQQueueConnectionFactory getConnection() throws JMSException {
		
		MQQueueConnectionFactory receiver = new MQQueueConnectionFactory();
		receiver.setChannel("drizzly");
		//receiver.setSSLCipherSuite();
	    receiver.setConnectionNameList("192.168.1.7(1414)");
		receiver.setTransportType(1);
		receiver.setQueueManager("QM_Demo");

		return receiver;
	}

	@Bean
	@Scope("prototype")
	public DefaultMessageListenerContainer configureEntitlementClient() throws JMSException{
		DefaultMessageListenerContainer messageListener = new DefaultMessageListenerContainer();
		messageListener.setConnectionFactory(getConnection());
		messageListener.setDestinationName("QL2");
		messageListener.setMessageListener(new HelloWorldMDB());
		messageListener.setAutoStartup(true);
		messageListener.afterPropertiesSet();
		
		return messageListener;
	}
}
