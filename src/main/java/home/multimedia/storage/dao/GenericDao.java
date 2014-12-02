package home.multimedia.storage.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E, K extends Serializable> {
	void save(E entity);

	E findById(K id);

	List<E> findAll();

	void delete(E entity);
}
