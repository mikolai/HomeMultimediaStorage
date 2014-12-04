package home.multimedia.storage.dao.impl;

import home.multimedia.storage.dao.RoleDao;
import home.multimedia.storage.domain.Role;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Role entity) {
		if (entity.isNew()) {
			em.persist(entity);
			em.flush();
		} else {
			em.merge(entity);
		}
	}

	@Override
	public Role findById(Integer id) {
		List<Role> entities = em.createQuery("select distinct r from Role r left join fetch r.users where r.id = :id")
				.setParameter("id", id)
				.getResultList();
		if (entities.size() == 0) {
			return null;
		}
		return entities.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findAll() {
		return em.createQuery("select distinct r from Role r left join fetch r.users order by r.name")
				.getResultList();
	}

	@Override
	public void delete(Role entity) {
		entity = em.merge(entity);
		em.remove(entity);
	}
}
