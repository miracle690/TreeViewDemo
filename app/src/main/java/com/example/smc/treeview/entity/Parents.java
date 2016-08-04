package com.example.smc.treeview.entity;

/**
 * Created by smc on 2016/7/21.
 */
public class Parents {
    private LeafBean father;
    private LeafBean mother;

    public Parents() {

    }

    public Parents(LeafBean father, LeafBean mother) {
        this.father = father;
        this.mother = mother;
    }

    public LeafBean getFather() {
        return father;
    }

    public void setFather(LeafBean father) {
        this.father = father;
    }

    public LeafBean getMother() {
        return mother;
    }

    public void setMother(LeafBean mother) {
        this.mother = mother;
    }

}
