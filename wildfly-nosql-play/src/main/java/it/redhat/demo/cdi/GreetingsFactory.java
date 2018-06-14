package it.redhat.demo.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class GreetingsFactory {

	@Produces
	public String greetings() {
		return "ciao";
	}
}
