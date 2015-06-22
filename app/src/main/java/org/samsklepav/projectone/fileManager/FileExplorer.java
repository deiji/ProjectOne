package org.samsklepav.projectone.fileManager;

import android.widget.*;
import android.app.Activity;
import android.widget.TextView;


import org.samsklepav.projectone.R;

import java.io.File;
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
    private static TextView text;
    private static ListView listView;
    private static Button pathButton;

    public static void getDataList(Activity activity, String path){
        file = new File(path);
        text = (TextView) activity.findViewById(R.id.textFileExplorer);
        ((Button) activity.findViewById(R.id.pathButton)).setText(currentFolder(file));
        listView = (ListView) activity.findViewById(R.id.listView);

        File[] directories = file.listFiles();
        List<Item> dirs = new ArrayList<>();
        List<Item> files = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm");
        for(File temp : directories){
            if(temp.isDirectory()){
                dirs.add(new Item(temp.getName(), temp.getAbsolutePath(), "dir", simpleDateFormat.format(new Date(temp.lastModified()))));
            } else {
                files.add(new Item(temp.getName(), temp.getAbsolutePath(), "file", simpleDateFormat.format(new Date(temp.lastModified()))));
            }
        }
        Collections.sort(dirs);
        Collections.sort(files);
        dirs.addAll(files);
        //dirs.add(0, new Item("..", previousFolder(file), "previous", ""));

        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<>(
                activity,
                android.R.layout.simple_list_item_1,
                dirs);
        listView.setAdapter(arrayAdapter);
    }

    private static String previousFolder(File file){
        String path = file.getAbsolutePath();
        path = path.substring(0, path.lastIndexOf("/"));
        if (path.equals(""))
            return "/";
        return path;
    }

    private static String currentFolder(File file){
        String path = file.getAbsolutePath();
        path = path.substring(path.lastIndexOf("/") + 1);
        return path;
    }
}
