package liwei.com.searchtest.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import liwei.com.searchtest.R;

/**
 * Created by wu  suo  wei on 2017/5/7.
 * 热门搜索的adapter
 */

public class HotRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private ArrayList<String> list=new ArrayList<>();
    public HotRecyclerViewAdapter(Context context) {
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotRecyclerViewHolder(LayoutInflater.from(context).
                inflate(R.layout.hot_item,parent,false));
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HotRecyclerViewHolder holder1 = (HotRecyclerViewHolder) holder;
        holder1.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 传递到adapter的数据
     * @param li
     */
    public void AddData(ArrayList<String> li) {
        if (li!=null){
            list.addAll(li);
            //刷新适配器
            notifyDataSetChanged();
        }
    }

    public class HotRecyclerViewHolder extends RecyclerView.ViewHolder{

        public final TextView tv;

        public HotRecyclerViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.rec_hot_tv);
        }
    }
}
