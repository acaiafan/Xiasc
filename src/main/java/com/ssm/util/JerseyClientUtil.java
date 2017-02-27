package com.ssm.util;

import javax.ws.rs.client.Client;  
import javax.ws.rs.client.ClientBuilder;  
import javax.ws.rs.client.WebTarget;  
import javax.ws.rs.client.Invocation.Builder;  
import javax.ws.rs.core.MediaType; 

import com.ssm.bean.Testt;

public class JerseyClientUtil {
	String url="http://localhost:8080/rest/test/t1";
	
	Client client = ClientBuilder.newClient();
	WebTarget webTarget = client.target(url).path("");
	Builder builder = webTarget.request(MediaType.APPLICATION_JSON);
	String ss = builder.get(String.class);
	

}
