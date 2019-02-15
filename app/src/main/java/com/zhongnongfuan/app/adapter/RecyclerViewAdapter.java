package com.zhongnongfuan.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhongnongfuan.app.R;
import com.zhongnongfuan.app.bean.Machine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author qichaoqun
 * @date 2019/1/19
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements View.OnClickListener {

    private Context mContext = null;
    private List<Machine> mMachines = null;
    private OnRecyclerviewItemClickListener mOnItemClickListener = null;

    public RecyclerViewAdapter(Context context,List<Machine> machines){
           mContext = context;
           mMachines = machines;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view,
                viewGroup,false);
        view.setOnClickListener(this);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.machineName.setText(mMachines.get(i).getMachineName());
        myViewHolder.machineCondition.setText(mMachines.get(i).getCondition());
        myViewHolder.machineAddress.setText(mMachines.get(i).getLocation());
        myViewHolder.itemView.setTag(i);
    }

    @Override
    public int getItemCount() {
        return mMachines.size();
    }

    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClickListener(v,((int) v.getTag()));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.machine_name)
        TextView machineName;
        @BindView(R.id.machine_condition)
        TextView machineCondition;
        @BindView(R.id.machine_address)
        TextView machineAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    /**
     * 点击事件的回调方法
     * @param itemClickListener 接口对象，用来实现的
     */
    public void setOnItemClickListener(OnRecyclerviewItemClickListener itemClickListener){
        mOnItemClickListener = itemClickListener;
    }

    public void flushList(List<Machine> list){
        mMachines = list;
        notifyDataSetChanged();
    }
}
