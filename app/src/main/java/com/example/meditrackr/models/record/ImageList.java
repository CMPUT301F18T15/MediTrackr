package com.example.meditrackr.models.record;

import java.io.Serializable;
import java.util.ArrayList;

public class ImageList implements Serializable {
    private ArrayList<Image> images = new ArrayList<>();

    public void addImage(Image newImage) {
        images.add(newImage);
    }

    public void removeImage(int image){
        images.remove(image);
    }

    public Boolean imageExists(Image image){
        return images.contains(image);
    }

    public int getIndex(Record image){
        return images.indexOf(image);
    }

    public int getSize(){
        return images.size();
    }

    public String toString() {
        return images.toString();
    }

    public Image getImage(int index){
        return images.get(index);
    }


}
