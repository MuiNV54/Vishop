package com.example.vishop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vishop.R;

/**
 * Created by FRAMGIA\nguyen.van.mui on 10/03/2016.
 */
public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private int mNavTitles[];
    private int mIcons[];

    private String name;
    private int profile;
    private String email;

    private Context context;
    public MainMenuItemClick itemClick;

    private int itemSelected;

    public void setItemClick(MainMenuItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public void setItemSelected(int itemSelected) {
        if (mNavTitles != null && itemSelected < mNavTitles.length) {
            this.itemSelected = itemSelected + 1;
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;

        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;
        LinearLayout content;

        public ViewHolder(View itemView,int ViewType) {
            super(itemView);

            if(ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.row_txt);
                imageView = (ImageView) itemView.findViewById(R.id.row_icon);
                content = (LinearLayout) itemView.findViewById(R.id.main_menu_item_content);
                Holderid = 1;
            } else {
                Name = (TextView) itemView.findViewById(R.id.name);
                email = (TextView) itemView.findViewById(R.id.email);
                profile = (ImageView) itemView.findViewById(R.id.menu_icon);
                Holderid = 0;
            }
        }
    }

    public MainMenuAdapter(Context context, int Titles[], int Icons[], String Name, String Email, int Profile){
        mNavTitles = Titles;
        mIcons = Icons;
        name = Name;
        email = Email;
        profile = Profile;
        this.context = context;
    }

    @Override
    public MainMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
            ViewHolder vhItem = new ViewHolder(view,viewType);

            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu_header,parent,false);

            ViewHolder vhHeader = new ViewHolder(v,viewType);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MainMenuAdapter.ViewHolder holder, final int position) {
        if(holder.Holderid ==1) {
            holder.textView.setText(context.getString(mNavTitles[position - 1]));
            holder.imageView.setImageResource(mIcons[position - 1]);
            if (position == itemSelected) {
                holder.content.setBackgroundResource(R.color.main_menu_item_selected);
            } else {
                holder.content.setBackgroundResource(R.color.transparent);
            }

            holder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClick != null && position > 0) {
                        itemClick.onItemClick(position - 1);
                    }
                }
            });
        }
        else{
            holder.profile.setImageResource(profile);
            holder.Name.setText(name);
            holder.email.setText(email);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length+1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public interface MainMenuItemClick {
        void onItemClick(int position);
    }
}
