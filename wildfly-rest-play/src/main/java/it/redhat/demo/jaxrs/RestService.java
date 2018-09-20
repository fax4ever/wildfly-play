package it.redhat.demo.jaxrs;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.redhat.demo.jaxrs.model.Car;

@Path("/")
@Stateless
public class RestService {

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
}
