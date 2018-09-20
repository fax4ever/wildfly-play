package it.redhat.demo.jaxrs;

import static org.junit.Assert.assertEquals;

import java.io.File;
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

	private static final String FORMATTING_DATES_AS_I_REALLY_WANT_RESPONSE = "[{\"colour\":\"black\",\"registeredOn\":\"1970-01-01 01:00:00\"},{\"colour\":\"red\",\"registeredOn\":\"1970-01-01 01:00:00\"},{\"colour\":\"blue\",\"registeredOn\":\"1970-01-01 01:00:00\"}]";
	private static final Logger LOG = LoggerFactory.getLogger( JaxRsIT.class );

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create( WebArchive.class )
				.addPackages( true, RestConfig.class.getPackage() )

				// this is very critical in order to use **our** ObjectMapper !!!
				.addAsWebInfResource( new File( "src/main/webapp/WEB-INF/jboss-deployment-structure.xml" ) );
	}

	@ArquillianResource
	private URL deploymentURL;

	@Test
	@RunAsClient
	public void jaxrsMapper() {
		String responseJaxRs = ClientBuilder.newClient()
				.target( deploymentURL.toString() )
				.path( "jaxrsMapper" )
				.request().get( String.class );
		LOG.info( "Response on /jaxrsMapper url: {}", responseJaxRs );

		assertEquals( FORMATTING_DATES_AS_I_REALLY_WANT_RESPONSE, responseJaxRs );
	}
}
