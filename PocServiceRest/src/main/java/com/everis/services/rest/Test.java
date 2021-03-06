package com.everis.services.rest;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.soap.addressing.server.annotation.Action;

import com.everis.connectors.SOAPConnector;
import com.everis.webservice.afip.Autenticacion;
import com.everis.webservice.afip.ListaEmpresas;
import com.everis.webservice.afip.ListaEmpresasResponse;
import com.google.gson.Gson;

@RestController
public class Test {

	@Autowired
	Gson g;
	@Autowired
	SOAPConnector soapConnector;

	@GetMapping(value = "test")
	public String test() {
		try {

			ListaEmpresas request = new ListaEmpresas();
			Autenticacion au = new Autenticacion();

			au.setToken(
					"PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8c3NvIHZlcnNpb249IjIuMCI+CiAgICA8aWQgc3JjPSJDTj13c2FhaG9tbywgTz1BRklQLCBDPUFSLCBTRVJJQUxOVU1CRVI9Q1VJVCAzMzY5MzQ1MDIzOSIgZHN0PSJvdHJvcyIgdW5pcXVlX2lkPSIzMzE2NjIzNzk4IiBnZW5fdGltZT0iMTUyNDE1MDA5MCIgZXhwX3RpbWU9IjE1MjQxOTMzNTAiLz4KICAgIDxvcGVyYXRpb24gdHlwZT0ibG9naW4iIHZhbHVlPSJncmFudGVkIj4KICAgICAgICA8bG9naW4gZW50aXR5PSIzMzY5MzQ1MDIzOSIgc2VydmljZT0id0dlc1RhYlJlZiIgdWlkPSJTRVJJQUxOVU1CRVI9Q1VJVCAyMDM1Mjk4MDAzMCwgQ049a29jaHBydWViYSIgYXV0aG1ldGhvZD0iY21zIiByZWdtZXRob2Q9IjIyIj4KICAgICAgICAgICAgPHJlbGF0aW9ucz4KICAgICAgICAgICAgICAgIDxyZWxhdGlvbiBrZXk9IjIwMDAwMDAwMDM2IiByZWx0eXBlPSI0Ii8+CiAgICAgICAgICAgICAgICA8cmVsYXRpb24ga2V5PSIyMDM1Mjk4MDAzMCIgcmVsdHlwZT0iNCIvPgogICAgICAgICAgICA8L3JlbGF0aW9ucz4KICAgICAgICA8L2xvZ2luPgogICAgPC9vcGVyYXRpb24+Cjwvc3NvPgo=");
			au.setSign(
					"c6cP7N3Cr7ThQc2J0nca3MnO5M388sj1CJGpeVQTcCEs9A+zX2CRB9ICMLbY7eEdWAFBHxYN6L/Dd5ySHsM444nGKeOPoS86LDvna94E2RY3ZXfVI7eCyA7lo4VvScBeqy7doez9QD524nD6Ehj4rK0NkurSFzZbCVLCmqTr4rk=");
			au.setCuit("20000000036");
			au.setRol("EXTE");
			au.setTipoAgente("IMEX");

			request.setAutentica(au);
			request.setIdReferencia("EMP_TRAN");

			ListaEmpresasResponse response = (ListaEmpresasResponse) soapConnector
					.callWebService("https://testdia.afip.gob.ar/Dia/ws/wGesTabRef/wGesTabRef.asmx", request, "ar.gov.afip.dia.serviciosweb.wgesTabRef/ListaEmpresas");
//			g.toJson(response.getListaEmpresasResult());
			return g.toJson(response.getListaEmpresasResult()); //response.getListaEmpresasResult().getEmpresas().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
