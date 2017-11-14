package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.widget.SwipeMenuLayout;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.FooterHistData;
import hb.xnwdw.com.wendangwang.netdata.entity.MyFootDeleteEntity;

/**
 * Created by Administrator on 2017/5/25.
 */
public class FooterfisAdapter extends BaseExpandableListAdapter {


    private List<FooterHistData.ObjBean> data;
    private List<MyFootDeleteEntity> selectedId = new ArrayList<>();
    private boolean Flag = false;

    FooterfisAdapter.OnItemClickLitener mOnItemClickLitener;

    public interface OnItemClickLitener {
        void onItemClick(int flag, int groupPosition, int childPosition);

        void onCheckBox(List<MyFootDeleteEntity> selectedId);
    }

    public void setFlag(boolean flag) {
        Flag = flag;
    }

    public void setData() {
        selectedId.clear();
    }

    public FooterfisAdapter(FooterfisAdapter.OnItemClickLitener mOnItemClickLitener, List<FooterHistData.ObjBean> data) {
        this.mOnItemClickLitener = mOnItemClickLitener;
        this.data = data;
    }

    // 得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getContent().get(childPosition);
    }

    // 得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    // 设置子item的组件
    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final FooterfisAdapter.ChildrenHolder ch;
        if (convertView == null) {
            ch = new FooterfisAdapter.ChildrenHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.activity_myfoot_children, null);
            ch.swipe_menu = (SwipeMenuLayout) convertView.findViewById(R.id.swipe_menu);
            ch.my_collectsitem_ll = (LinearLayout) convertView.findViewById(R.id.my_collectsitem_ll);
            ch.name = (TextView) convertView.findViewById(R.id.my_collectslist_name);
            ch.content = (TextView) convertView.findViewById(R.id.my_collectslist_memo);
            ch.img = (SimpleDraweeView) convertView.findViewById(R.id.my_collectslist_img);
            ch.price = (TextView) convertView.findViewById(R.id.my_collectslist_price);
            ch.checkBox = (ImageView) convertView.findViewById(R.id.my_collectslist_chosse);
            ch.delete = (TextView) convertView.findViewById(R.id.my_collectslist_delete);
            convertView.setTag(ch);
        } else {
            ch = (FooterfisAdapter.ChildrenHolder) convertView.getTag();
        }
        ch.img.setImageURI(UrlApi.SERVER_IP + data.get(groupPosition).getContent().get(childPosition).getItemPic());
        ch.name.setText(data.get(groupPosition).getContent().get(childPosition).getItemName());
        ch.price.setText("¥" + data.get(groupPosition).getContent().get(childPosition).getUnit());
        if (Flag == true) {
            ch.checkBox.setVisibility(View.VISIBLE);
        } else {
            ch.checkBox.setVisibility(View.GONE);
        }
        boolean flag = false;
        for (int i = 0; i < selectedId.size(); i++) {
            if (data.get(groupPosition).getContent().get(childPosition).getID() == selectedId.get(i).getID()) {
                flag = true;
                break;
            }
        }
        if (flag) {
            ch.checkBox.setBackgroundResource(R.drawable.ico_chosen);
        } else {
            ch.checkBox.setBackgroundResource(R.drawable.ico_choose);
        }
        ch.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch.swipe_menu.quickClose();
                mOnItemClickLitener.onItemClick(0, groupPosition, childPosition);
            }
        });

        ch.my_collectsitem_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean endFleag = false;//默认表示点击行的itemid没在selectedId里面，如果已经在里面，endFleag为true，同时移除它,不为true就添加进去
                for (int i = 0; i < selectedId.size(); i++) {
                    if (data.get(groupPosition).getContent().get(childPosition).getID() == selectedId.get(i).getID()) {
                        endFleag = true;
                        selectedId.remove(i);
                        break;
                    } else {

                    }
                }
                if (Flag == false) {
                    mOnItemClickLitener.onItemClick(2, groupPosition, childPosition);
                } else {
                    if (!endFleag) {
                        MyFootDeleteEntity entity = new MyFootDeleteEntity();
                        entity.setGroupPosition(groupPosition);
                        entity.setChildPosition(childPosition);
                        entity.setItemID(data.get(groupPosition).getContent().get(childPosition).getItemID());
                        entity.setID(data.get(groupPosition).getContent().get(childPosition).getID());
                        selectedId.add(entity);
                    } else {

                    }
                }
                mOnItemClickLitener.onCheckBox(selectedId);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    // 获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return data.get(groupPosition).getContent().size();
    }

    // 获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return data.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // 设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        FooterfisAdapter.ParentHolder ph;
        if (convertView == null) {
            ph = new FooterfisAdapter.ParentHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.activity_myfoot_parent, null);
            ph.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(ph);
        } else {
            ph = (FooterfisAdapter.ParentHolder) convertView.getTag();
        }
        ph.time.setText(data.get(groupPosition).getKey().getST());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class ParentHolder {
        TextView time;
    }

    public class ChildrenHolder {
        SwipeMenuLayout swipe_menu;
        LinearLayout my_collectsitem_ll;
        SimpleDraweeView img;
        TextView name;
        TextView price;
        TextView content;
        ImageView checkBox;
        TextView delete;
    }

}
