package com.example.meditrackr.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Skryt on Nov 15, 2018
 */

public class CommentList implements Serializable {
    private ArrayList<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void removeComment(int comment){
        comments.remove(comment);
    }

    public Comment getComment(int index){
        return comments.get(index);
    }


    public Boolean imageExists(Comment comment){
        return comments.contains(comment);
    }

    public int getIndex(Comment comment){
        return comments.indexOf(comment);
    }

    public int getSize(){
        return comments.size();
    }

    public String toString() {
        return comments.toString();
    }

    public Comment getImage(int index){
        return comments.get(index);
    }

}
