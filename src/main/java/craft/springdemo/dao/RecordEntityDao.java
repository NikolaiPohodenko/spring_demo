package craft.springdemo.dao;

import craft.springdemo.model.RecordEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by phdnk on 10-Jul-16.
 */

@Transactional
@Repository
public class RecordEntityDao implements IRecordEntityDao
{
    private Log log = log = LogFactory.getLog(RecordEntityDao.class);

    @Autowired
    private SessionFactory sessionFactory;
    private Session session() {
        return sessionFactory.getCurrentSession();
    }



    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void write(RecordEntity record) {
        log.debug("RecordEntityDao.write( " + record + " )");

        try {
            Session s = session();
            s.saveOrUpdate(record);
            s.flush();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public RecordEntity load(int id) {
        log.debug("RecordEntityDao.load( " + id + " )");

        try {
            return  session().get(RecordEntity.class, id);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<RecordEntity> loadAll() {
        log.debug("RecordEntityDao.loadAll()");

        try {
            Criteria crit = session().createCriteria(RecordEntity.class);
            return (List<RecordEntity>) crit.list();

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
