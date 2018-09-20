package it.redhat.demo.jaxrs;

import static it.redhat.demo.jaxrs.JacksonConfig.DATE_FORMAT;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.redhat.demo.jaxrs.model.Car;

@Path("/")
@Stateless
public class RestService {

	@Inject
	private Logger log;

	@GET
	@Path("/jaxrsMapper")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Car> cars() {
		Car black = new Car( "black" );
		Car red = new Car( "red" );
		Car blue = new Car( "blue" );

		ArrayList<Car> cars = new ArrayList<>();
		cars.add( black );
		cars.add( red );
		cars.add( blue );

		return cars;
	}

	@GET
	@Path("/jacksonMapper")
	@Produces(MediaType.APPLICATION_JSON)
	public String carsSting() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat( new SimpleDateFormat( DATE_FORMAT ) );

		List<Car> cars = cars();
		try {
			return mapper.writer().writeValueAsString( cars );
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException( e );
		}
	}
}
