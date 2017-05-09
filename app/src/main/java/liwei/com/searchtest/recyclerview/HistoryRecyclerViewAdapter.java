package liwei.com.searchtest.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import liwei.com.searchtest.R;
import liwei.com.searchtest.sqlite.Bean;

/**
 * Created by wu  suo  wei on 2017/5/7.
 */

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    ArrayList<Bean> list=new ArrayList<>();
    public HistoryRecyclerViewAdapter(Context context) {
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryRecyclerViewHolder(LayoutInflater.from(context).
                inflate(R.layout.history_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HistoryRecyclerViewHolder holder1 = (HistoryRecyclerViewHolder) holder;
        holder1.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        int size = list.size();
        if (size>10){
            size=10;
        }
        return size;
    }

    public void AddData(ArrayList<Bean> query) {
        if(query!=null){
            list.addAll(query);
            notifyDataSetChanged();
        }
    }

    public class HistoryRecyclerViewHolder extends RecyclerView.ViewHolder{

        public final TextView name;

        public HistoryRecyclerViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.history_name);
        }
    }
}
