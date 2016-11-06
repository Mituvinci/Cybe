package app.wistem.com.cybe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import app.wistem.com.cybe.R;

/**
 * Created by mitu on 11/6/16.
 */

public class ImageFileAdapter  extends RecyclerView.Adapter<ImageFileAdapter.CustomViewHolder>  {
    private List<File> mImageList;
    private Context mContext;
    private static customImageInterface customInterface;



    public ImageFileAdapter(Context context, List<File> mDataSet) {
        this.mImageList = mDataSet;
        mContext  = context;

        Log.d("recycler :",mDataSet.size()+"");


    }
    @Override
    public ImageFileAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_iamge_layout,parent,false);
        ImageFileAdapter.CustomViewHolder customViewHolder = new ImageFileAdapter.CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        Glide.with(mContext).load(mImageList.get(position)).into(holder.mImageView);

        Log.d("recycler 1:", position + " " + mImageList.get(position) + "");
    }



    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public static class  CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageId1);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            customInterface.onItemClick(getAdapterPosition(),v);

        }
    }

    public void setOnItemClickListener(customImageInterface clickListener){
        this.customInterface = clickListener;
    }

    public interface customImageInterface {
        void onItemClick(int position, View v);    }
}
