package craft.spring_demo.dao;


import craft.spring_demo.model.RecordEntity;

import java.util.List;

/**
 * Created by phdnk on 10-Jul-16.
 */
public interface IRecordEntityDao {
    void write(RecordEntity record);
    RecordEntity load(int id);
    List<RecordEntity> loadAll();
}
