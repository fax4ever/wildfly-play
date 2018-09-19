package it.redhat.demo.jaxrs;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
public class JaxRsIT {

	private static final Logger LOG = LoggerFactory.getLogger( JaxRsIT.class );

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create( WebArchive.class )
				.addClasses( RestConfig.class, RestService.class );
	}

	@ArquillianResource
	private URL deploymentURL;

	@Test
	@RunAsClient
	public void test() {

		LOG.info( "Server url {}", deploymentURL );

		String response = ClientBuilder.newClient()
				.target( deploymentURL.toString() )
				.request().get( String.class );

		assertEquals( "ciao", response );
	}
}
