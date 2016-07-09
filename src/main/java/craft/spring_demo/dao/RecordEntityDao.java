package craft.spring_demo.dao;

import craft.spring_demo.model.RecordEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Created by phdnk on 10-Jul-16.
 */
public class RecordEntityDao implements IDao<RecordEntity>
{
    private Log log = log = LogFactory.getLog(RecordEntityDao.class);


    public void write(RecordEntity record) {
        log.debug("RecordEntityDao.write( " + record + ")");

    }

    public RecordEntity load(int id) {
        log.debug("RecordEntityDao.load( " + id + ")");

        return null;
    }

    public List<RecordEntity> loadAll(int id) {
        log.debug("RecordEntityDao.loadAll()");

        return null;
    }
}
