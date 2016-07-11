package craft.spring_demo;

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

/**
 * Created by phdnk on 10-Jul-16.
 */


public class service_fetch_Test {

    private Log log = log = LogFactory.getLog(service_fetch_Test.class);

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




