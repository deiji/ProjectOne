package org.samsklepav.projectone.fileManager.FileType;

import android.app.Activity;

/**
 * Created by oleksandr on 6/15/15.
 */
public class Files implements Comparable<Files>{
    private String name = "";
    private String size = "";
    private String image = "";
    private String date = "";
    private String path = "";
    public Files(String name, String size, String image, String date, String path){
        this.size = size;
        this.image = image;
        this.date = date;
        this.name = name;
        this.path = path;
    }
    public void open(Activity activity){
        System.out.println("opened files");
    }
    public Files(){}
    public String getSize(){
        return this.size;
    }
    public String getImage(){
        return this.image;
    }
    public String getDate(){
        return this.date;
    }
    public String getName(){
        return this.name;
    }
    public String getPath() { return this.path; }

    public void setName(String name){
        this.name = name;
    }
    public void setSize(String size){
        this.size = size;
    }
    public void setImage(String image){
        this.image = image;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setPath(String path){
        this.path = path;
    }
    @Override
    public int compareTo(Files another) {
        if(!this.name.equals(null)){
            return this.name.toLowerCase().compareTo(another.getName().toLowerCase());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
