package home.multimedia.storage.service;

import home.multimedia.storage.domain.Catalogue;

import java.util.List;

/**
 * Created by nick on 6/9/14.
 */
public interface CatalogueService {
    void save(Catalogue catalogue);
    Catalogue getCatalogue(int id);
    List<Catalogue> getCatalogues();
    void removeCatalogue(Catalogue catalogue);
}
