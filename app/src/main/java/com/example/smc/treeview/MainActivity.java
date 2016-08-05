package com.example.smc.treeview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.smc.treeview.entity.LeafBean;
import com.example.smc.treeview.selfview.TreeViewGroup;

public class MainActivity extends AppCompatActivity {

    private TreeViewGroup treeView;
    private LeafBean data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        treeView = (TreeViewGroup) findViewById(R.id.treeView);
        initData();
    }

    private void initData() {

    }
}
