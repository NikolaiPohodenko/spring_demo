package craft.springdemo.service;

import craft.springdemo.model.RecordEntity;

import java.util.List;

/**
 * Created by phdnk on 10-Jul-16.
 */
public interface IRecordFetcherService {
    RecordEntity       fetchById(int id);
    List<RecordEntity> fetchAll ();
    void save(RecordEntity record);

    RecordEntity fetchFromWebservice(int id);
}
