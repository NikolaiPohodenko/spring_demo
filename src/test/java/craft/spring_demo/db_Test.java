package craft.spring_demo;

import craft.spring_demo.cfg.db_config;
import craft.spring_demo.model.RecordEntity;
import craft.spring_demo.service.IRecordFetcherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


/**
 * Created by phdnk on 10-Jul-16.
 */

public class db_Test {

    private IRecordFetcherService service;

    @Before
    public void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(db_config.class);
        service = context.getBean(IRecordFetcherService.class);
    }

    @Test
    public void test_db_is_empty() {
        List<RecordEntity> resultset = service.fetchAll();
        Assert.assertTrue("Empty DB", resultset.isEmpty());
    }

    @Test
    public void test_fetch_nonexistent_element()  {
        RecordEntity resultset = service.fetchById(Integer.MAX_VALUE);
        Assert.assertTrue(String.format("Nonexistent element: %s", resultset), resultset == null);
    }


    @Test
    public void test_insert_and_fetch()  {
        RecordEntity entity_01 = new RecordEntity();
        entity_01.setId(1024);
        entity_01.setUserId(1024);
        entity_01.setTitle("alien title");
        entity_01.setTitle("alien body");

        RecordEntity entity_02 = new RecordEntity();
        entity_01.setId(1025);
        entity_02.setUserId(1025);
        entity_02.setTitle("stranger");
        entity_02.setTitle("rubbish");

        service.save(entity_01);
        service.save(entity_02);

        List<RecordEntity> resultSet = service.fetchAll();
        resultSet.sort( (RecordEntity e1, RecordEntity e2) -> (e2.getId() - e1.getId()) );

        Assert.assertTrue("Result set size", resultSet.size() == 2);

        Assert.assertTrue("EntityComparison 0.1",  entity_01       .equals(entity_01)        );
        Assert.assertTrue("EntityComparison 0.2",  entity_02       .equals(entity_02)        );
        Assert.assertTrue("EntityComparison 0.3", !entity_01       .equals(entity_02)        );
        Assert.assertTrue("EntityComparison 1.1", !resultSet.get(0).equals(resultSet.get(1)) );
        Assert.assertTrue("EntityComparison 2.1",  entity_01       .equals(resultSet.get(0)) );
        Assert.assertTrue("EntityComparison 2.2",  resultSet.get(0).equals(entity_01)        );
        Assert.assertTrue("EntityComparison 2.3",  entity_02       .equals(resultSet.get(1)) );
        Assert.assertTrue("EntityComparison 2.4",  resultSet.get(1).equals(entity_02)        );
    }

}
