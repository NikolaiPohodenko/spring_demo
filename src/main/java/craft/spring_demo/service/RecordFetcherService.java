package craft.spring_demo.service;

import craft.spring_demo.dao.IDao;
import craft.spring_demo.dao.RecordEntityDao;
import craft.spring_demo.model.RecordEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by phdnk on 10-Jul-16.
 */

@Service
public class RecordFetcherService implements IRecordFetcherService {
    private Log log = log = LogFactory.getLog(RecordFetcherService.class);

    @Autowired
    private IDao<RecordEntity> dao;



    public RecordEntity fetchById(int id) { return dao.load(id); }

    public List<RecordEntity> fetchAll() { return dao.loadAll(); }

    public void save(RecordEntity record) { dao.write(record); }
}
