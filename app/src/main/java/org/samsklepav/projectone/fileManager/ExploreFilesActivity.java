package org.samsklepav.projectone.fileManager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;



import org.samsklepav.projectone.R;


/**
 * Created by oleksandr on 6/13/15.
 */
public class ExploreFilesActivity extends Activity {
    private String path = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_viewer);
        FileExplorer.getDataList((Activity) this, path = Environment.getExternalStorageDirectory().getAbsolutePath());

        ListView listView = (ListView) findViewById(R.id.listView);
        final Activity activity = this;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FileExplorer.getDataList(activity, path = ((Item) parent.getItemAtPosition(position)).getPath());
            }
        });

        ((Button)findViewById(R.id.previousFolderButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path = path.substring(0, path.lastIndexOf("/"));
                FileExplorer.getDataList(activity, path);
            }
        });
    }
}

