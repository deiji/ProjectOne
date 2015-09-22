package org.samsklepav.projectone.fileManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;


import org.samsklepav.projectone.R;
import org.samsklepav.projectone.fileManager.FileType.Files;


/**
 * Created by oleksandr on 6/13/15.
 */
public class ExploreFilesActivity extends Activity {
    private static String path = "";
    private static String image = "";
    private static long timeBackPressed = 0;
    Activity activity = null;
    public static String getImage(){ return image; }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_viewer);
        FileExplorer.getDataList((Activity) this, path = Environment.getExternalStorageDirectory().getAbsolutePath());

        ListView listView = (ListView) findViewById(R.id.listView);
        activity = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Files files = (Files) parent.getItemAtPosition(position);
                    if (files.getImage().contains("file")) {
                        image = files.getPath();
                        files.open(activity);
                    } else {
                        FileExplorer.getDataList(activity, path = files.getPath());
                    }
                } catch(Exception e){
                    Toast.makeText(getBaseContext(), "ExploreFilesActivity oops", Toast.LENGTH_SHORT).show();
                }
            }
        });

        (findViewById(R.id.previousFolderButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = FileExplorer.previousFolder(path);
                FileExplorer.getDataList(activity, path);
            }
        });
    }
    @Override
    public void onBackPressed(){
        System.gc();
        if(path.equals(Environment.getExternalStorageDirectory().getAbsolutePath())){
            if (timeBackPressed + 2000 > System.currentTimeMillis()){
                super.onBackPressed();
            }
            else{
                Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
                timeBackPressed = System.currentTimeMillis();
            }
        } else {
            path = FileExplorer.previousFolder(path);
            FileExplorer.getDataList(activity, path);
        }
    }
}

