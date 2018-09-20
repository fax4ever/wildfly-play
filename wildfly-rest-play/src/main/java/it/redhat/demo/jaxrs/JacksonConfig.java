package it.redhat.demo.jaxrs;

import java.text.SimpleDateFormat;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper> {

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private ObjectMapper objectMapper;

	public JacksonConfig() throws Exception {
		this.objectMapper = new ObjectMapper();
		this.objectMapper.setDateFormat( new SimpleDateFormat( DATE_FORMAT ) );
	}

	@Override
	public ObjectMapper getContext(Class<?> objectType) {
		return objectMapper;
	}

	public ObjectMapper getMapper() {
		return objectMapper;
	}
}
