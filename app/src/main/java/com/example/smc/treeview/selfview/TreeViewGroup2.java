package com.example.smc.treeview.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.smc.treeview.entity.LeafBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 阶梯层级布局 并交错显示
 * Created by smc on 2016/7/21.
 */
public class TreeViewGroup2 extends ViewGroup {
    private int horizontal_space = 60, vertical_space = 60;
    private LeafBean data;
    private LeafView myself;
    private int itemWith = 0, itemHeight = 0;
    private List<LeafView> allViews = new ArrayList<LeafView>();//所有节点
    private List<LeafView> children = new ArrayList<LeafView>();

    private Map<Integer, LeafView> leaves = new HashMap<Integer, LeafView>();
    private LeafView partner;
    private LeafView fathre, mother;
    private Paint paint;

    public TreeViewGroup2(Context context) {
        this(context, null, 0);
    }

    public TreeViewGroup2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TreeViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        initData();
    }

    private void initData() {
        data = new LeafBean();
        children.add(new LeafView(getContext()));
        children.add(new LeafView(getContext()));
        children.add(new LeafView(getContext()));
        for (int i = 0; i < children.size(); i++)
            addView(children.get(i));

        fathre = new LeafView(getContext());
        mother = new LeafView(getContext());
        addView(fathre);
        addView(mother);

        myself = new LeafView(getContext(), data);
        partner = new LeafView(getContext(), data);
        allViews.add(myself);
//        for (int i = 0; i < 10; i++) {
////            leaves.put(data.)
////            转成图   节点 关系
////            然后 在 onLayout中遍历
////            布局时  根据同一级节点数量调整间距
//        }
        addView(partner);
        addView(myself);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        measureChild(myself, widthMeasureSpec, heightMeasureSpec);
        measureChild(partner, widthMeasureSpec, heightMeasureSpec);
        measureChild(fathre, widthMeasureSpec, heightMeasureSpec);
        measureChild(mother, widthMeasureSpec, heightMeasureSpec);
        itemHeight = myself.getMeasuredHeight();
        itemWith = myself.getMeasuredWidth();
        setMeasuredDimension(800, 1400);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (getChildCount() < 1 || myself == null)
            return;
        int mid_x = l + (r - l) / 2;
        int mid_y = t + (b - t) / 2;
        myself.layout(mid_x - itemWith / 2, mid_y - itemHeight / 2, mid_x + itemWith / 2, mid_y + itemHeight / 2);
        partner.layout(mid_x + itemWith / 2 + horizontal_space, mid_y - itemHeight / 2, mid_x + itemWith * 3 / 2 + horizontal_space, mid_y + itemHeight / 2);

        fathre.layout(mid_x - horizontal_space/2 - itemWith, mid_y - itemHeight / 2 - vertical_space - itemHeight, mid_x - horizontal_space/2, mid_y - itemHeight / 2 - vertical_space);
        mother.layout(mid_x + horizontal_space/2, mid_y - itemHeight / 2 - vertical_space - itemHeight, mid_x + horizontal_space/2 + itemWith, mid_y - itemHeight / 2 - vertical_space);
        children.get(0).layout(mid_x + itemWith / 2+vertical_space, mid_y + itemHeight / 2 + vertical_space, mid_x + itemWith*3 / 2+vertical_space, mid_y + itemHeight * 3 / 2 + vertical_space);
        children.get(1).layout(mid_x - itemWith / 2, mid_y + itemHeight / 2 + vertical_space, mid_x + itemWith / 2, mid_y + itemHeight * 3 / 2 + vertical_space);
        children.get(2).layout(mid_x - itemWith *3/ 2-vertical_space, mid_y + itemHeight / 2 + vertical_space, mid_x - itemWith / 2-vertical_space, mid_y + itemHeight * 3 / 2 + vertical_space);
    }

    private void layoutTop(View origin, View arg) {

    }

    private void layoutLeft(View origin, View arg) {

    }

    private void layoutRight(View origin, View arg) {

    }

    private void layoutBottom(View origin, View arg) {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Point midP = drawLinkLine(myself, partner, canvas);
        Point mid = drawLinkLine(fathre, mother, canvas);
        drawPoint2View(mid, myself, canvas);
        drawLinkLine(myself, children.get(0), canvas);
        drawLinkLine(myself, children.get(1), canvas);
        drawLinkLine(myself, children.get(2), canvas);
    }

    //画关系线
    private Point drawLinkLine(View v1, View v2, Canvas canvas) {
        if (v1 == null || v2 == null)
            return null;

        Point point = new Point();

        Point point2 = new Point();
        Point point1 = new Point();
        point1.set((int) (v1.getX() + v1.getWidth() / 2), (int) (v1.getY() + v1.getHeight() / 2));
        point2.set((int) v2.getX() + v2.getWidth() / 2, (int) v2.getY() + v2.getHeight() / 2);
//        point1.set((int) (v1.getX() + v1.getWidth()), (int) (v1.getY() + v1.getHeight() / 2));
//        point2.set((int) v2.getX(), (int) v2.getY() + v2.getHeight() / 2);

        drawLinkLine(point1, point2, canvas);
        point.set((point1.x + point2.x) / 2, (point1.y + point2.y) / 2);
        return point;
    }

    private void drawLinkLine(Point poi1, Point poi2, Canvas canvas) {
        Path path = new Path();
        path.moveTo(poi1.x, poi1.y);
        path.lineTo(poi2.x, poi2.y);
        canvas.drawPath(path, paint);
    }

    private void drawPoint2View(Point poi, View view, Canvas canvas) {
        Point point = new Point();
        int v_mid_x = (int) (view.getX() + view.getWidth() / 2);
        int v_mid_y = (int) (view.getY() + view.getHeight() / 2);
        if (poi.x > v_mid_x && poi.y == v_mid_y) {
            point.x = v_mid_x + view.getWidth() / 2;
            point.y = v_mid_y;
        } else if (poi.x < v_mid_x && poi.y == v_mid_y) {
            point.x = v_mid_x - view.getWidth() / 2;
            point.y = v_mid_y;
        } else if (poi.y > v_mid_y && poi.x == v_mid_x) {
            point.x = v_mid_x;
            point.y = v_mid_y + view.getHeight() / 2;
        } else if (poi.y < v_mid_y && poi.x == v_mid_x) {
            point.x = v_mid_x;
            point.y = v_mid_y - view.getHeight() / 2;
        }
        drawLinkLine(poi, point, canvas);
    }


}
