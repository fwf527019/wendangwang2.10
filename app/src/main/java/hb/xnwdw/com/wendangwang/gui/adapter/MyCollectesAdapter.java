package hb.xnwdw.com.wendangwang.gui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MyCollectionData;

/**
 * Created by Administrator on 2017/3/30.
 */

public class MyCollectesAdapter extends SwipeMenuAdapter<MyCollectesAdapter.ViewHolder> {


    private List<MyCollectionData.ObjBean> objBeen;
    private List<Boolean> selected = new ArrayList<>();
    private boolean Flag = false;

    OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(View view, int flag, int position);
    }

    public void setFlag(boolean flag) {
        Flag = flag;
    }

    public MyCollectesAdapter(OnItemClickLitener mOnItemClickLitener, List<MyCollectionData.ObjBean> objBeen, List<Boolean> selected) {
        this.mOnItemClickLitener = mOnItemClickLitener;
        this.objBeen = objBeen;
        this.selected = selected;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mycollects, parent, false);
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        ViewHolder viewHolder = new ViewHolder(realContentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.img.setImageURI(UrlApi.SERVER_IP + objBeen.get(position).getItemPic());
        holder.name.setText(objBeen.get(position).getItemName());
        holder.price.setText("¥" + objBeen.get(position).getUnit());
        if (Flag == true) {
            holder.checkBox.setVisibility(View.VISIBLE);
        } else {
            holder.checkBox.setVisibility(View.GONE);
        }
        if (selected.get(position)) {
            holder.checkBox.setBackgroundResource(R.drawable.ico_chosen);
        } else {
            holder.checkBox.setBackgroundResource(R.drawable.ico_choose);
        }
//        holder.my_collectsitem_ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        holder.my_collectsitem_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Flag==false){
                    mOnItemClickLitener.onItemClick(v, 2, position);
                }else {
                    if (selected.get(position)) {
                        selected.set(position, false);
                        mOnItemClickLitener.onItemClick(v, 0, position);
                    } else {
                        selected.set(position, true);
                        mOnItemClickLitener.onItemClick(v, 1, position);
                    }
                }

                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return objBeen == null ? 0 : objBeen.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout my_collectsitem_ll;
        SimpleDraweeView img;
        TextView name;
        TextView price;
        TextView content;
        ImageView checkBox;


        public ViewHolder(View itemView) {
            super(itemView);
            my_collectsitem_ll = (LinearLayout) itemView.findViewById(R.id.my_collectsitem_ll);
            img = (SimpleDraweeView) itemView.findViewById(R.id.my_collectslist_img);
            name = (TextView) itemView.findViewById(R.id.my_collectslist_name);
            price = (TextView) itemView.findViewById(R.id.my_collectslist_price);
            checkBox = (ImageView) itemView.findViewById(R.id.my_collectslist_chosse);
            content = (TextView) itemView.findViewById(R.id.my_collectslist_memo);

        }

    }

}
