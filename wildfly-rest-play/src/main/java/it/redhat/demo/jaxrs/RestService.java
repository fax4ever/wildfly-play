package it.redhat.demo.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class RestService {

	@GET
	public String ciao() {
		return "ciao";
	}
}
