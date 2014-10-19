package home.multimedia.storage.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nick on 5/14/14.
 */
public interface GenericDao<E, K extends Serializable> {
    void save(E entity);
    E read(K id);
    List<E> read();
    void delete(E entity);
}
