package org.samsklepav.projectone.fileManager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.*;
import android.widget.*;

import org.samsklepav.projectone.R;
import org.samsklepav.projectone.fileManager.FileType.Files;

import java.util.List;

/**
 * Created by oleksandr on 6/24/15.
 */
public class FileAdapter extends ArrayAdapter<Files> {
    private Context context;
    private int ID;
    private List<Files> items;

    public FileAdapter(Context context, int resource, List<Files> objects) {
        super(context, resource, objects);
        this.context = context;
        ID = resource;
        items = objects;
    }
    public Files getItem(int i){
        return items.get(i);
    }
    public Context getContext(){
        return context;
    }
    public int getID() {
        return ID;
    }
    public List<Files> getItems() {
        return items;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        View temp = view;
        if(temp == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            temp = layoutInflater.inflate(getID(), null);
        }
        final Files object = getItems().get(position);
        if(null != object){
            TextView name = (TextView) temp.findViewById(R.id.itemName);
            TextView date = (TextView) temp.findViewById(R.id.itemDate);
            TextView size = (TextView) temp.findViewById(R.id.itemSize);
            ImageView type = (ImageView) temp.findViewById(R.id.itemType);
            String uri = "drawable/" + object.getImage();
            int imageResource = getContext().getResources().getIdentifier(uri, null, getContext().getPackageName());
            Drawable image = getContext().getResources().getDrawable(imageResource);
            type.setImageDrawable(image);
            if(null != name){
                name.setText(object.getName());
            }
            if(null != date){
                date.setText(object.getDate());
            }
            if (null != size){
                size.setText(object.getSize());
            }
        }
        return temp;
    }
}
