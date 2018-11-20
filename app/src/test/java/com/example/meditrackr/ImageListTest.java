package com.example.meditrackr;

import android.graphics.Bitmap;

import com.example.meditrackr.models.record.RecordPhotoList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * RecordPhotoList Unit Tests
 */

public class ImageListTest {
    private RecordPhotoList imageList;
    private Bitmap image;

    public ImageListTest() {}

    // Set an initial null bitmap and an empty RecordPhotoList
    @Before
    public void initImageList() {
        imageList = new RecordPhotoList();
        final byte[] colours = {10, 10, 10};
        image = null;
    }

    // Test if images are being added to the list
    @Test
    public void addImageTest() {
        imageList.addImage(image);
        assertTrue("Image not added to Image List",
                imageList.getSize() == 1);
    }

    // Test if an image can be removed
    @Test
    public void removeImageTest() {
        imageList.addImage(image);
        assertTrue("Image not added to Image List",
                imageList.getSize() == 1);

        imageList.removeImage(0);
        assertTrue("Image not removed from Image List",
                imageList.getSize() == 0);
    }

    // Test if an existing image can be identified from the list
    @Test
    public void hasImageTest() {
        imageList.addImage(image);
        assertTrue("Image not added to Image List",
                imageList.imageExists(image));

        assertEquals(imageList.getImage(0), image);
        assertEquals(imageList.getIndex(image), 0);
    }

    // Test if the size of the list is updating
    @Test
    public void imageListSizeTest() {
        imageList.addImage(image);

        final Bitmap tempImage = null;
        imageList.addImage(tempImage);

        assertEquals("Incorrect number of elements in Image List",
                imageList.getSize(), 2);
        imageList.removeImage(1);
        assertEquals("Incorrect number of elements in Image List",
                imageList.getSize(), 1);
    }

}
