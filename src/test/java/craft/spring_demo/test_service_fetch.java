package craft.spring_demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import craft.spring_demo.cfg.db_config;
import craft.spring_demo.model.RecordEntity;
import craft.spring_demo.service.IRecordFetcherService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * Created by phdnk on 10-Jul-16.
 */


public class test_service_fetch {

    private Log log = log = LogFactory.getLog(test_service_fetch.class);

    private IRecordFetcherService service;

    @Before
    public void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(db_config.class);
        service = context.getBean(IRecordFetcherService.class);
    }

    @Test
    public void test_servicefetch() {
        RecordEntity expected = new RecordEntity();
        expected.setId(1);
        expected.setUserId(1);
        expected.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expected.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        RecordEntity actual =  service.fetchFromWebservice(1);
        Assert.assertTrue("Got responce from web service", actual != null);
        Assert.assertTrue("Responce comparison", expected.equals(actual));
    }
}




