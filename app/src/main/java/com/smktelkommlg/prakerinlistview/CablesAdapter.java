package com.smktelkommlg.prakerinlistview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.Locale;

public class CablesAdapter extends ArrayAdapter<Cables> {
    private List<Cables> resultsList;
    private Context mCtx;

    public CablesAdapter(List<Cables> resultsList, Context mCtx){
        super(mCtx, R.layout.cables_list, resultsList);
        this.resultsList = resultsList;
        this.mCtx = mCtx;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.cables_list, null, true);
        //getting text views
        TextView Id = listViewItem.findViewById(R.id.id_cables);
        TextView type  = listViewItem.findViewById(R.id.type);
        TextView site_A = listViewItem.findViewById(R.id.site_A);
        TextView site_B = listViewItem.findViewById(R.id.site_B);
        TextView status = listViewItem.findViewById(R.id.cables_status);


        //Getting the superHero for the specified position
        Cables results = resultsList.get(position);
        //setting superHero values to textviews
        Id.setText(String.valueOf(results.getId()));
        type.setText(results.getType());
        site_A.setText(results.getSite_a());
        site_B.setText(results.getSite_b());
        status.setText(results.getStatus());



        //returning the listitem
        return listViewItem;

    }


}
