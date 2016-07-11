package craft.spring_demo.controller;

import craft.spring_demo.model.RecordEntity;
import craft.spring_demo.service.RecordFetcherService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by phdnk on 11-Jul-16.
 */
public class SampleController {
    private static final Logger log = Logger.getLogger(SampleController.class);

    @Autowired
    RecordFetcherService service;


    @RequestMapping(value="demo/posts", method=RequestMethod.GET)
    public @ResponseBody
    List<RecordEntity> getPosts() {
        log.info("All records requested:");
        List<RecordEntity> res =  service.fetchAll();
        log.info(String.format("Fetched %d records.", res.size()));
        return res;
    }


    @RequestMapping(value="demo/posts/{id}", method=RequestMethod.GET)
    public @ResponseBody  RecordEntity getPost(@PathVariable("id") int id) {
        log.info(String.format("Record %d requested:", id));
        RecordEntity res =  service.fetchById(id);
        log.info(String.format("Fetched record: %s", res));
        return res;
    }
}
