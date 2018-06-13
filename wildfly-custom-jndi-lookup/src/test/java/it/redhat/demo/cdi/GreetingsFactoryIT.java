package it.redhat.demo.cdi;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

@RunWith(Arquillian.class)
public class GreetingsFactoryIT {

	@Deployment
	public static JavaArchive deployment() {
		return ShrinkWrap
			.create( JavaArchive.class, "wildfly-custom-jndi-lookup.jar" )
			.addPackages( true, "it.redhat.demo" );
	}

	@Inject
	private String message;

	@Test
	public void verifyInjection() {
		assertEquals( "ciao", message );
	}
}
