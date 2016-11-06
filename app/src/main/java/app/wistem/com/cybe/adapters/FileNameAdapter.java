package app.wistem.com.cybe.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.wistem.com.cybe.R;

/**
 * Created by mitu on 11/3/16.
 */

public class FileNameAdapter extends RecyclerView.Adapter<FileNameAdapter.CustomViewHolder>  {
    private List<String> mDataSet;
    private Context mContext;
    private static customInterface customInterface;

    public FileNameAdapter(Context context, List<String>mDataSet) {
        this.mDataSet = mDataSet;
        mContext  = context;
        Log.d("recycler :",mDataSet.size()+"");


    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_filename,parent,false);
        CustomViewHolder customViewHolder = new CustomViewHolder(view);
        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder,final int position) {

            holder.mNameTextView.setText(mDataSet.get(position));


        Log.d("recycler :", position + " " + mDataSet.get(position) + "");

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class  CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mNameTextView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            customInterface.onItemClick(getAdapterPosition(),v);

        }
    }

    public void setOnItemClickListener(customInterface clickListener){
        this.customInterface = clickListener;
    }

    public interface customInterface{
        void onItemClick(int position, View v);    }
}
