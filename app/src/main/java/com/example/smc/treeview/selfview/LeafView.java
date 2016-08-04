package com.example.smc.treeview.selfview;

import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.smc.treeview.R;
import com.example.smc.treeview.entity.LeafBean;

/**
 * Created by smc on 2016/7/21.
 */
public class LeafView extends FrameLayout {

    private LeafBean data;

    public LeafView(Context context) {
        this(context, null, 0);
    }

    public LeafView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LeafView(Context context, LeafBean data) {
        this(context, null, 0);
        this.data = data;
    }

    public LeafView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_leaf, this);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(getContext()).setTitle("编辑").setMessage("添加关系人").setPositiveButton("确定", null).setPositiveButton("取消", null).create().show();
                return true;
            }
        });
    }

}
