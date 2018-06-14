package it.redhat.demo.cdi;

import static org.junit.Assert.assertEquals;

import java.io.File;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import it.redhat.demo.ejb.MongoDataService;

@RunWith(Arquillian.class)
public class IntegrationTestIT {

	@Deployment
	public static WebArchive deployment() {
		return ShrinkWrap
				.create( WebArchive.class, "wildfly-nosql-play.war" )
				.addPackages( true, "it.redhat.demo" )
				.addAsWebInfResource( new File( "src/main/webapp/WEB-INF/jboss-deployment-structure.xml" ) );
	}

	@Inject
	private String message;

	@Inject
	private MongoDataService dataService;

	@Test
	public void verifyCDIInjection() {
		assertEquals( "ciao", message );
	}

	@Test
	public void playWithWildflyNosql() {
		dataService.createDropCollection();
	}
}
