package craft.springdemo.dao;


import craft.springdemo.model.RecordEntity;

import java.util.List;

/**
 * Created by phdnk on 10-Jul-16.
 */
public interface IRecordEntityDao {
    void write(RecordEntity record);
    RecordEntity load(int id);
    List<RecordEntity> loadAll();
}
