package home.multimedia.storage.service.impl;

import home.multimedia.storage.domain.Catalogue;
import home.multimedia.storage.domain.User;
import home.multimedia.storage.service.CatalogueService;
import home.multimedia.storage.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by nick on 6/10/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/servlet-context.xml")
public class CatalogueServiceImplTest {

    @Autowired
    private CatalogueService catalogueService;
    @Autowired
    private UserService userService;

    @Ignore
    @Test
    public void firstSave() {
        List<Catalogue> expectedCatalogues = getCatalogues();

        for (Catalogue catalogue : expectedCatalogues) {
            if (catalogue.getParent() != null) {
                catalogue.setParent(catalogueService.getCatalogue(catalogue.getParent().getId()));
            }
            if (catalogue.getUser() != null) {
                catalogue.setUser(userService.getUser(catalogue.getUser().getId()));
            }
            catalogueService.save(catalogue);
        }
    }

    @Test
    public void canGetCatalogue() {
        final int expectedCatalogueId = 1;
        final Catalogue expectedCatalogue = getCatalogues().get(expectedCatalogueId - 1);

        Catalogue actualCatalogue = catalogueService.getCatalogue(expectedCatalogueId);

        assertEquals(expectedCatalogueId, actualCatalogue.getId());
        assertEquals(expectedCatalogue.getName(), actualCatalogue.getName());
        assertEquals(expectedCatalogue.getUser().getId(), actualCatalogue.getUser().getId());
    }

    @Test
    public void canGetCatalogues() {
        List<Catalogue> expectedCatalogues = getCatalogues();

        List<Catalogue> actualCatalogues = catalogueService.getCatalogues();

        assertEquals(expectedCatalogues.size(), actualCatalogues.size());
        for (Catalogue actualCatalogue : actualCatalogues) {
            int id = actualCatalogue.getId();
            Catalogue expectedCatalogue = expectedCatalogues.get(id - 1);

            assertEquals(expectedCatalogue.getName(), actualCatalogue.getName());
            assertEquals(expectedCatalogue.getUser().getId(), actualCatalogue.getUser().getId());
        }
    }

    private List<Catalogue> getCatalogues() {
        List<Catalogue> catalogues = new ArrayList<Catalogue>();

        Catalogue firstCatalogue = new Catalogue(null, "first_catalogue", new User(1));
        catalogues.add(firstCatalogue);

        Catalogue secondCatalogue = new Catalogue(new Catalogue(1), "second_catalogue", new User(1));
        catalogues.add(secondCatalogue);

        Catalogue thirdCatalogue = new Catalogue(new Catalogue(2), "third_catalogue", new User(1));
        catalogues.add(thirdCatalogue);

        Catalogue fourthCatalogue = new Catalogue(null, "fourth_catalogue", new User(3));
        catalogues.add(fourthCatalogue);

        return catalogues;
    }

    @Test
    public void canAddRemoveCatalogue() {
        Catalogue newCatalogue = new Catalogue(new Catalogue(3), "new_catalogue", new User(4));
        newCatalogue.setParent(catalogueService.getCatalogue(newCatalogue.getParent().getId()));
        newCatalogue.setUser(userService.getUser(newCatalogue.getUser().getId()));

        catalogueService.save(newCatalogue);
        Catalogue actualCatalogue = catalogueService.getCatalogue(newCatalogue.getId());

        assertEquals(newCatalogue.getId(), actualCatalogue.getId());
        assertEquals(newCatalogue.getName(), actualCatalogue.getName());
        assertEquals(newCatalogue.getParent().getId(), actualCatalogue.getParent().getId());
        assertEquals(newCatalogue.getUser().getId(), actualCatalogue.getUser().getId());

        catalogueService.removeCatalogue(newCatalogue);
        Catalogue deletedCatalogue = catalogueService.getCatalogue(newCatalogue.getId());

        assertNull(deletedCatalogue);
    }

    @Test
    public void canUpdateCatalogue() {
        final int catalogueId = 1;
        final String newCatalogueName = "updated_catalogue";
        String catalogueName;

        Catalogue catalogue = catalogueService.getCatalogue(catalogueId);
        catalogueName = catalogue.getName();
        catalogue.setName(newCatalogueName);
        catalogueService.save(catalogue);
        Catalogue updatedCatalogue = catalogueService.getCatalogue(catalogueId);

        assertEquals(catalogueId, updatedCatalogue.getId());
        assertEquals(newCatalogueName, updatedCatalogue.getName());

        updatedCatalogue.setName(catalogueName);
        catalogueService.save(updatedCatalogue);
        Catalogue restoredCatalogue = catalogueService.getCatalogue(catalogueId);

        assertEquals(catalogueId, restoredCatalogue.getId());
        assertEquals(catalogueName, restoredCatalogue.getName());
    }
}
