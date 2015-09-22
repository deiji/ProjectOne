package org.samsklepav.projectone.fileManager.FileType;

import android.app.Activity;

/**
 * Created by oleksandr on 7/2/15.
 */
public class Text extends Files {

    public Text(){}
    @Override
    public void open(Activity activity){
        System.out.println("opened text");
    }
}
