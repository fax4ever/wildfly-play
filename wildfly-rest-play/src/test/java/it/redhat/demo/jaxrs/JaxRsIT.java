package it.redhat.demo.jaxrs;

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
				.addPackages( true, RestConfig.class.getPackage() );
	}

	@ArquillianResource
	private URL deploymentURL;

	@Test
	@RunAsClient
	public void jaxrsMapper() {
		String response = ClientBuilder.newClient()
				.target( deploymentURL.toString() )
				.path( "jaxrsMapper" )
				.request().get( String.class );

		LOG.info( "Response on /jaxrsMapper url: {}", response );
	}

	@Test
	@RunAsClient
	public void jacksonMapper() {
		String response = ClientBuilder.newClient()
				.target( deploymentURL.toString() )
				.path( "jacksonMapper" )
				.request().get( String.class );

		LOG.info( "Response on /jacksonMapper url: {}", response );
	}
}
