package org.samsklepav.projectone.fileManager;

/**
 * Created by oleksandr on 6/15/15.
 */
public class Item implements Comparable<Item>{
    private String name;
    private String path;
    private String image;
    private String date;
    public Item(String name, String path, String image, String date){
        this.path = path;
        this.image = image;
        this.date = date;
        this.name = name;
    }
    public String getPath(){
        return this.path;
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

    @Override
    public String toString(){
        return this.getName(); //+ " " + this.getPath() + " " + this.getDate() + " " + this.getImage();
    }
    @Override
    public int compareTo(Item another) {
        if(!this.name.equals(null)){
            return this.name.toLowerCase().compareTo(another.getName().toLowerCase());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
