package app.wistem.com.cybe.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.wistem.com.cybe.R;

/**
 * Created by mitu on 7/17/16.
 */
public class ScareScoreAdapter extends RecyclerView.Adapter<ScareScoreAdapter.DataObjectHolder> {

    private List<String> mDataset;
    private Context mContext;

    private final static String KNWOING_SOURCE = "knowingSource";
    private final static String KNOWING_POSITION = "knowingposition";

    public ArrayList<Integer> selectedIds = new ArrayList<Integer>();
    private String knowingSource = "";
    private int knowingSourcePosition = -1;
    private String knowingSourceForSet ="";
    //private static DistrictChooseListener districtChooseListener;

    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView KnowingSourceTextView;
        RadioButton radioButton;
        LinearLayout linearLayout;



        public DataObjectHolder(View itemView) {
            super(itemView);
            KnowingSourceTextView = (TextView) itemView.findViewById(R.id.district_adapter_title_name_text_view);
            radioButton = (RadioButton) itemView.findViewById(R.id.district_adapter_radiobutton);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.district_adapter_choosdistrict);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

        }

    }

    public ScareScoreAdapter(List<String> dataSet, Context applicationContext, String knowingsource11) {
        mDataset = dataSet;
        mContext = applicationContext;
        knowingSourceForSet = knowingsource11;
        //Log.d("kns1 -> ", knowingSourceForSet);


    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_layout_scare_score, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }



    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        knowingSource = mDataset.get(position);
        knowingSourcePosition = position;
       // Log.d("kns2 -> ",knowingSourceForSet+" ,"+knowingSource);
        if (knowingSourceForSet.trim().equals(knowingSource.trim())){
           // Log.d("kns2 -> ",knowingSourceForSet+" ,"+knowingSource);

            toggleSelected(new Integer(position));
            knowingSourceForSet ="";
        }

        holder.KnowingSourceTextView.setText(mDataset.get(position));

        holder.radioButton.setChecked(false);
        if (selectedIds.contains(position)) {
            holder.radioButton.setChecked(true);

        } else {
            holder.radioButton.setChecked(false);
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSelected(new Integer(position));
                notifyDataSetChanged();
                holder.radioButton.setChecked(true);
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
                editor.putString(KNWOING_SOURCE, knowingSource);
                editor.putInt(KNOWING_POSITION, knowingSourcePosition);
                editor.apply();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void toggleSelected(Integer position) {



        if (selectedIds.contains(position)) {
            selectedIds.remove(position);
            knowingSource = "";
            knowingSourcePosition = -1;


        } else {
            try {
                selectedIds.remove(0);
                knowingSource = "";
                knowingSourcePosition = -1;

            } catch (Exception e) {

            }

            knowingSource = mDataset.get(position);
            knowingSourcePosition = position;
            selectedIds.add(position);
        }
    }
}
