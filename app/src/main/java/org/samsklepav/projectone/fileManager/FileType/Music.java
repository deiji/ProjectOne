package org.samsklepav.projectone.fileManager.FileType;

import android.app.Activity;

/**
 * Created by oleksandr on 7/2/15.
 */
public class Music extends Files {

    public Music(){}
    @Override
    public void open(Activity activity){
        System.out.println("opened music");
    }
}
