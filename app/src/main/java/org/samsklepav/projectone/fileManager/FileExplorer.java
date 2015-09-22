package org.samsklepav.projectone.fileManager;

import android.widget.*;
import android.app.Activity;

import org.samsklepav.projectone.R;
import org.samsklepav.projectone.fileManager.FileType.*;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by oleksandr on 6/14/15.
 */
public class FileExplorer {
    private static File file;
    private static ListView listView;

    public static void getDataList(Activity activity, String path){
        file = new File(path);
        ((Button) activity.findViewById(R.id.pathButton)).setText(currentFolder(file));
        listView = (ListView) activity.findViewById(R.id.listView);

        File[] directories = file.listFiles();
        List<Files> dirs = new ArrayList<>();
        List<Files> files = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for(File temp : directories){
            if (temp.getName().charAt(0) == '.'){
                continue;
            } else {
                if (temp.isDirectory()) {
                    dirs.add(new Files(nameCutter(temp.getName()),
                            getFilesInFolder(temp),
                            "folder_icon",
                            simpleDateFormat.format(new Date(temp.lastModified())),
                            temp.getAbsolutePath()));
                } else {
                    Files items = getType(temp);

                    items.setName(nameCutter(temp.getName()));
                    items.setSize(calculateLength(temp.length()));
                    items.setImage("file_icon");
                    items.setDate(simpleDateFormat.format(new Date(temp.lastModified())));
                    items.setPath(temp.getAbsolutePath());

                    files.add(items);
                }
            }
        }
        Collections.sort(dirs);
        Collections.sort(files);
        dirs.addAll(files);

        FileAdapter arrayAdapter = new FileAdapter(
                activity,
                R.layout.file_manager_row_view,
                dirs);
        listView.setAdapter(arrayAdapter);
    }

    protected static String previousFolder(String path){
        path = path.substring(0, path.lastIndexOf("/"));
        if (path.equals(""))
            return "/";
        return path;
    }
    private static String getFilesInFolder(File file){
        int count = 0;
        if(file.listFiles() == null) {
            return "Empty folder";
        }
        for (File object : file.listFiles()) {
            if (object.getName().charAt(0) == '.')  //excluding hidden files from list
                continue;
            count++;
        }
        switch (count){
            case 0:
                return "Empty folder";
            case 1:
                return "1 item";
            default:
                return count + " items";
        }
    }

    private static String currentFolder(File file){
        String path = file.getAbsolutePath();
        path = path.substring(path.lastIndexOf("/") + 1);
        return path;
    }
    private static String nameCutter(String name){
        int position = 50;
        /*if(name.length() >= position){
            return name.substring(0, position) + "...";
        }*/
        return name;
    }
    private static String calculateLength(double length){
        int count = 0;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        while ((int)(length / 1024) > 0){
            length = length / 1024;
            count++;
        }
        switch (count) {
            case 1:
                return decimalFormat.format(length) + " kB";
            case 2:
                return decimalFormat.format(length) + " MB";
            case 3:
                return decimalFormat.format(length) + " GB";
            default:
                return decimalFormat.format(length) + " bytes";
        }
    }
    private static Files getType(File file){
        String name = file.getName();
        String type = name.substring(name.lastIndexOf('.') + 1);
        String music = "mp3 acc flac m4a ogg wav";
        String video = "flv mkv avi mp4";
        String text = "doc docx xls xlsx xml txt ";
        String image = "jpg jpeg tiff png";
        if(music.contains(type.toLowerCase()))
            return new Music();
        if(video.contains(type.toLowerCase()))
            return new Video();
        if(text.contains(type.toLowerCase()))
            return new Text();
        if(image.contains(type.toLowerCase()))
            return new Image();
        else
            return new Files();
    }
}
