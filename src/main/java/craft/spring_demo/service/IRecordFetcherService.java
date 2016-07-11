package craft.spring_demo.service;

import craft.spring_demo.model.RecordEntity;

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
