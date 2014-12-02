package home.multimedia.storage.service;

import home.multimedia.storage.domain.Catalogue;

import java.util.List;

public interface CatalogueService {
	void save(Catalogue catalogue);

	Catalogue getCatalogue(int id);

	List<Catalogue> getCatalogues();

	void removeCatalogue(Catalogue catalogue);
}
