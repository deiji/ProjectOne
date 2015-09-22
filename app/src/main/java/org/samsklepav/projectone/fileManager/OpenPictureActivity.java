package org.samsklepav.projectone.fileManager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.samsklepav.projectone.R;

/**
 * Created by oleksandr on 6/26/15.
 */
public class OpenPictureActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_viewer);
        try {
            openPicture(this, ExploreFilesActivity.getImage());
        } catch (Exception e){
            Toast.makeText(this.getBaseContext(), "OpenPictureActivity oops", Toast.LENGTH_SHORT);
        }
    }
    protected static void openPicture(Activity activity, String path) throws Exception{
        ImageView imageView = (ImageView) activity.findViewById(R.id.imageView);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap image = BitmapFactory.decodeFile(path, options);
        imageView.setImageBitmap(image);
    }
}
