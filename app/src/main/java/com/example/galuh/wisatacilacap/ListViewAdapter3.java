package com.example.galuh.wisatacilacap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by galuh on 12/11/2016.
 */
public class ListViewAdapter3 extends BaseAdapter {

    // Declare Variables
    Context mContext;
    private ImageLoader mImageLoader;
    LayoutInflater inflater;
    private List<WorldPopulation> worldpopulationlist = null;
    private ArrayList<WorldPopulation> arraylist;

    public ListViewAdapter3(Context context, List<WorldPopulation> worldpopulationlist) {
        mContext = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<WorldPopulation>();
        this.arraylist.addAll(worldpopulationlist);
    }

    public class ViewHolder {
        TextView rank;
        NetworkImageView imageView;
    }

    @Override
    public int getCount() {
        return worldpopulationlist.size();
    }

    @Override
    public WorldPopulation getItem(int position) {
        return worldpopulationlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.daftar, null);
            // Locate the TextViews in listview_item.xml
            holder.rank = (TextView) view.findViewById(R.id.name);
            holder.imageView = (NetworkImageView) view.findViewById(R.id.image2);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        mImageLoader = new ImageLoader(ApplicationClass.getInstance().getRequestQueue(), new BitmapLruCache());
        holder.rank.setText(worldpopulationlist.get(position).getRank());
        holder.imageView.setImageUrl("http://10.0.2.2/wisataCilacap/imagesk/" + worldpopulationlist.get(position).getCountry(), mImageLoader);
        //holder.country.setText(worldpopulationlist.get(position).getCountry());


        // Listen for ListView Item Click


        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        worldpopulationlist.clear();
        if (charText.length() == 0) {
            worldpopulationlist.addAll(arraylist);
        }
        else
        {
            for (WorldPopulation wp : arraylist)
            {
                if (wp.getRank().toLowerCase(Locale.getDefault()).contains(charText))
                {
                    worldpopulationlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
