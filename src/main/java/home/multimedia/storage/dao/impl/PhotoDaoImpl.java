package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.PhotoDao;
import home.multimedia.storage.domain.Photo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("photoDao")
public class PhotoDaoImpl implements PhotoDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Photo entity) {
		if (entity.isNew()) {
			this.em.persist(entity);
		} else {
			this.em.merge(entity);
		}
	}

	@Override
	public Photo findById(Integer id) {
		List<Photo> entities = em.createQuery("select distinct p from Photo p left join fetch p.catalogue left join fetch p.user where p.id = :id")
				.setParameter("id", id)
				.getResultList();
		if (entities.size() == 0) {
			return null;
		}
		return entities.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> findAll() {
		return em.createQuery("select distinct p from Photo p left join fetch p.catalogue left join fetch p.user")
				.getResultList();
	}

	@Override
	public void delete(Photo entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}
}
