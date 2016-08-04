package com.example.smc.treeview.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smc on 2016/7/21.
 */
public class LeafBean {

    private int ic;
    private String name;
    private int sex;//0女  1男
    private int seniority;//辈分    我是0; father  grandfather 2 ... ;  son and daughter -1 ;
    private String avatar;

    private Parents parents;//父母
    private LeafBean partner;//伴侣
    private List<LeafBean> children = new ArrayList<LeafBean>();

    private boolean isLayout;

    public LeafBean() {

    }

    public void setLayouted(boolean layouted) {
        this.isLayout = layouted;
    }

    public boolean getLayouted() {
        return isLayout;
    }

    public int getPersonCount() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public LeafBean getPartner() {
        return partner;
    }

    public void setPartner(LeafBean partner) {
        this.partner = partner;
    }

    public List<LeafBean> getChildren() {
        return children;
    }

    public void setChildren(List<LeafBean> children) {
        this.children.clear();
        this.children.addAll(children);
    }

    public void addChild(LeafBean child) {
        this.children.add(child);
    }

    public void removeChild(int position) {
        if (position >= children.size() || position < 0)
            return;
        this.children.remove(position);
    }
}
