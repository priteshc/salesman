package com.example.dec.mylocation.pojo;

/**
 * Created by pritesh on 8/11/2017.
 */

public class Imageidpojo {
    private int imageId;
    private String title;


    public Imageidpojo(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;

    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

       public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}