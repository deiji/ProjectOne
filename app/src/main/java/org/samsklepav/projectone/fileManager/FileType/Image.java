package org.samsklepav.projectone.fileManager.FileType;

import android.app.Activity;
import android.content.Intent;

import org.samsklepav.projectone.fileManager.OpenPictureActivity;

/**
 * Created by oleksandr on 7/2/15.
 */
public class Image extends Files {

    public Image(){    }
    @Override
    public void open(Activity activity){
        System.out.println("opened image");
        activity.startActivity(new Intent(activity, OpenPictureActivity.class));
    }
}
