/*
 *    Apache 2.0 License Notice
 *
 *    Copyright 2018 CMPUT301F18T15
 *
 *Licensed under the Apache License, Version 2.0 (the "License");
 *you may not use this file except in compliance with the License.
 *You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *Unless required by applicable law or agreed to in writing, software
 *distributed under the License is distributed on an "AS IS" BASIS,
 *WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *See the License for the specific language governing permissions and
 *limitations under the License.
 *
 */

package com.example.meditrackr;

//imports
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
    
    @Test
    public void constructorTest() {
        RecordPhotoList list = new RecordPhotoList();
        assertTrue(list.getSize() == 0); // is the list first empty
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

        imageList.removeImage(image);
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
        imageList.removeImage(image);
        assertEquals("Incorrect number of elements in Image List",
                imageList.getSize(), 1);
    }

}
