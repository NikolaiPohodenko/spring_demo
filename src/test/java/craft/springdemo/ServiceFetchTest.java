package craft.springdemo;

import craft.springdemo.model.RecordEntity;
import craft.springdemo.service.IRecordFetcherService;
import craft.springdemo.service.RecordFetcherService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by phdnk on 10-Jul-16.
 */


public class ServiceFetchTest {

    private Log log = log = LogFactory.getLog(ServiceFetchTest.class);


    @Test
    public void test_servicefetch() {
        RecordEntity expected = new RecordEntity();
        expected.setId(1);
        expected.setUserId(1);
        expected.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expected.setBody("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");

        IRecordFetcherService service = new RecordFetcherService();
        RecordEntity actual =  service.fetchFromWebservice(1);
        Assert.assertTrue(String.format("Got responce from web service %s", actual), actual != null);
        Assert.assertTrue("Responce comparison", expected.equals(actual));
    }
}




