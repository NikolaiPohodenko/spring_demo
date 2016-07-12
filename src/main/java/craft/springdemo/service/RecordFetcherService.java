package craft.springdemo.service;

import craft.springdemo.dao.IRecordEntityDao;
import craft.springdemo.model.RecordEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by phdnk on 10-Jul-16.
 */

@Service
public class RecordFetcherService implements IRecordFetcherService {
    private Log log = LogFactory.getLog(RecordFetcherService.class);

    @Autowired
    private IRecordEntityDao dao;


    public RecordEntity fetchById(int id) {
        RecordEntity entity = fetchFromWebservice(id); // we want web request be fired every time
        if (RecordEntity.isValid(entity)) {
            dao.write(entity); // db is used as archive
        }
        else
        {
            entity = dao.load(id); // in case we have manually sored the entity into the DB
        }
        return entity;
    }

    public List<RecordEntity> fetchAll() { return dao.loadAll(); }

    public void save(RecordEntity record) { dao.write(record); }


    @Value("${subservice.host}")
    private String host;
    @Value("${subservice.path}")
    private String path;

    private WebTarget buildRequest(Client serviceClient, int id){
        if (host == null) host = "http://jsonplaceholder.typicode.com";
        if (path == null) path = "posts";
        return serviceClient.target(host).path(path).path(String.valueOf(id));
    }


    public RecordEntity fetchFromWebservice(int id) {
        log.info(String.format("fetchFromWebservice %d:", id));
        try {
            Client client = ClientBuilder.newClient(new ClientConfig());
            client.register(JacksonFeature.class);
            WebTarget target = buildRequest(client, id);
            Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
            RecordEntity entity = response.readEntity(RecordEntity.class);
            return entity;
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            return null;
        }
    }
}
