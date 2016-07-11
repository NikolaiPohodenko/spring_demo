package craft.spring_demo;

import craft.spring_demo.cfg.db_config;
import craft.spring_demo.service.IRecordFetcherService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * Created by phdnk on 10-Jul-16.
 */
public class app_init_Test {

    @Test
    public void testSpringHibernateInit() {
        ApplicationContext context = new AnnotationConfigApplicationContext(db_config.class);
        IRecordFetcherService service = context.getBean(IRecordFetcherService.class);
        Assert.assertTrue("RecordFetcher service initialization", service != null);
    }
}
