package com.everis.connectors;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class SOAPConnector extends WebServiceGatewaySupport {

	public Object callWebService(String url, Object request, String action){
		return getWebServiceTemplate().marshalSendAndReceive(url, request, new SoapActionCallback(action));
	}
}