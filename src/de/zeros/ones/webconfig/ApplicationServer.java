package de.zeros.ones.webconfig;

import javax.ws.rs.ApplicationPath;

import com.sun.jersey.api.core.PackagesResourceConfig;

@ApplicationPath("*")
public class ApplicationServer extends PackagesResourceConfig{
	public ApplicationServer() {
		super("de.zeros.ones.webservice");
	}
	
}
