package home.multimedia.storage.service.impl;

import home.multimedia.storage.dao.CatalogueDao;
import home.multimedia.storage.domain.Catalogue;
import home.multimedia.storage.service.CatalogueService;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nick on 5/25/14.
 */
@Service("catalogueService")
public class CatalogueServiceImpl implements CatalogueService, InitializingBean {

    private CatalogueDao catalogueDao;

    @Autowired(required = false)
    public void setCatalogueDao(CatalogueDao catalogueDao) {
        this.catalogueDao = catalogueDao;
    }

    @Transactional
    @Override
    public void save(Catalogue catalogue) {
        catalogueDao.save(catalogue);
    }

    @Transactional(readOnly = true)
    @Override
    public Catalogue getCatalogue(int id) {
        return catalogueDao.read(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Catalogue> getCatalogues() {
        return catalogueDao.read();
    }

    @Transactional
    @Override
    public void removeCatalogue(Catalogue catalogue) {
        catalogueDao.delete(catalogue);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (catalogueDao == null) {
            throw new BeanInitializationException("Need set CatalogueDao");
        }
    }
}
