package craft.spring_demo.dao;


import java.util.List;

/**
 * Created by phdnk on 10-Jul-16.
 */
public interface IDao<E> {
    void write(E record);
    E load(int id);
    List<E> loadAll();
}
