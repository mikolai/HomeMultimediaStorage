package home.multimedia.storage.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import home.multimedia.storage.domain.Catalogue;
import home.multimedia.storage.domain.Photo;
import home.multimedia.storage.domain.User;
import home.multimedia.storage.service.CatalogueService;
import home.multimedia.storage.service.PhotoService;
import home.multimedia.storage.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by nick on 6/30/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/business-config.xml")
public class PhotoServiceImplTest {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CatalogueService catalogueService;
    @Autowired
    private UserService userService;

    @Ignore
    @Test
    public void firstSave() {
        for (Photo photo : getPhotos()) {
            if (photo.getCatalogue() != null) {
                photo.setCatalogue(catalogueService.getCatalogue(photo.getCatalogue().getId()));
            }
            if (photo.getUser() != null) {
                photo.setUser(userService.getUser(photo.getUser().getId()));
            }
            photoService.save(photo);
        }
    }

    @Test
    public void canGetPhoto() {
        final Integer expectedPhotoId = 1;
        final Photo expectedPhoto = getPhotos().get(expectedPhotoId - 1);
        final byte[] expectedPhotoData = expectedPhoto.getPhotoData();

        final Photo actualPhoto = photoService.getPhoto(expectedPhotoId);
        final byte[] actualPhotoData = actualPhoto.getPhotoData();

        assertEquals(expectedPhotoId, actualPhoto.getId());
        assertEquals(expectedPhoto.getTopic(), actualPhoto.getTopic());
        assertEquals(expectedPhoto.getDescription(), actualPhoto.getDescription());
        assertEquals(expectedPhoto.getTags(), actualPhoto.getTags());
        assertEquals(expectedPhoto.getCatalogue().getId(), actualPhoto.getCatalogue().getId());
        assertEquals(expectedPhoto.getUser().getId(), actualPhoto.getUser().getId());
        assertNotNull(actualPhoto.getDate());

        for (int i = 0; i < expectedPhotoData.length; i++) {
            assertEquals(expectedPhotoData[i], actualPhotoData[i]);
        }
    }

    @Test
    public void canGetPhotos() {
        final List<Photo> expectedPhotos = getPhotos();

        final List<Photo> actualPhotos = photoService.getPhotos();

        for (Photo actualPhoto : actualPhotos) {
            final int actualPhotoId = actualPhoto.getId();
            final Photo expectedPhoto = expectedPhotos.get(actualPhotoId - 1);
            final byte[] expectedPhotoData = expectedPhoto.getPhotoData();
            final byte[] actualPhotoData = actualPhoto.getPhotoData();

            assertEquals(expectedPhoto.getTopic(), actualPhoto.getTopic());
            assertEquals(expectedPhoto.getDescription(), actualPhoto.getDescription());
            assertEquals(expectedPhoto.getTags(), actualPhoto.getTags());
            assertEquals(expectedPhoto.getCatalogue().getId(), actualPhoto.getCatalogue().getId());
            assertEquals(expectedPhoto.getUser().getId(), actualPhoto.getUser().getId());
            assertNotNull(actualPhoto.getDate());

            for (int i = 0; i < expectedPhotoData.length; i++) {
                assertEquals(expectedPhotoData[i], actualPhotoData[i]);
            }
        }
    }

    private List<Photo> getPhotos() {
        List<Photo> photos = new ArrayList<Photo>();

        byte[] firstBytePhoto = new byte[] {(byte)0xe3, (byte)0x01, (byte)0xbc, (byte)0x13, (byte)0xf1};
        Photo firstPhoto = new Photo("first_photo_topic", "first_description", "first_tag", new Catalogue(1), new User(2), new Date(), firstBytePhoto);
        photos.add(firstPhoto);

        byte[] secondBytePhoto = new byte[] {(byte)0x13, (byte)0xc1, (byte)0xba, (byte)0x76, (byte)0xf1};
        Photo secondPhoto = new Photo("second_photo_topic", "second_description", "second_tag", new Catalogue(1), new User(1), new Date(), secondBytePhoto);
        photos.add(secondPhoto);

        byte[] thirdBytePhoto = new byte[] {(byte)0x47, (byte)0xff, (byte)0xbc, (byte)0xc6, (byte)0xf1, (byte)0xa1};
        Photo thirdPhoto = new Photo("third_photo_topic", "third_description", "third_tag", new Catalogue(3), new User(4), new Date(), thirdBytePhoto);
        photos.add(thirdPhoto);

        byte[] fourthBytePhoto = new byte[] {(byte)0x11, (byte)0x43, (byte)0xbc};
        Photo fourthPhoto = new Photo("fourth_photo_topic", "fourth_description", "fourth_tag", new Catalogue(3), new User(3), new Date(), fourthBytePhoto);
        photos.add(fourthPhoto);

        return photos;
    }

    @Test
    public void canAddRemovePhoto() {
        byte[] newBytePhoto = new byte[] {(byte)0x11, (byte)0x43, (byte)0xbc};
        Catalogue catalogue = catalogueService.getCatalogue(3);
        User user = userService.getUser(3);
        Photo newPhoto = new Photo("new_photo_topic", "new_description", "new_tag", catalogue, user, new Date(), newBytePhoto);

        photoService.save(newPhoto);
        Photo savedPhoto = photoService.getPhoto(newPhoto.getId());
        final byte[] savedPhotoData = savedPhoto.getPhotoData();

        assertEquals(newPhoto.getTopic(), savedPhoto.getTopic());
        assertEquals(newPhoto.getDescription(), savedPhoto.getDescription());
        assertEquals(newPhoto.getTags(), savedPhoto.getTags());
        assertEquals(newPhoto.getCatalogue().getId(), savedPhoto.getCatalogue().getId());
        assertEquals(newPhoto.getUser().getId(), savedPhoto.getUser().getId());
        assertNotNull(savedPhoto.getDate());

        for (int i = 0; i < newBytePhoto.length; i++) {
            assertEquals(savedPhotoData[i], savedPhotoData[i]);
        }

        photoService.removePhoto(savedPhoto);
        Photo removedPhoto = photoService.getPhoto(savedPhoto.getId());

        assertNull(removedPhoto);
    }

    @Test
    public void canUpdatePhoto() {
        final Integer photoId = 1;
        final String updatePhotoTopic = "updated_photo_topic";
        String photoTopic;

        Photo photo = photoService.getPhoto(photoId);
        photoTopic = photo.getTopic();
        photo.setTopic(updatePhotoTopic);
        photoService.save(photo);
        Photo updatedPhoto = photoService.getPhoto(photoId);

        assertEquals(photoId, updatedPhoto.getId());
        assertEquals(updatePhotoTopic, updatedPhoto.getTopic());

        updatedPhoto.setTopic(photoTopic);
        photoService.save(updatedPhoto);
        Photo restoredPhoto = photoService.getPhoto(photoId);

        assertEquals(photoId, restoredPhoto.getId());
        assertEquals(photoTopic, restoredPhoto.getTopic());
    }
}
