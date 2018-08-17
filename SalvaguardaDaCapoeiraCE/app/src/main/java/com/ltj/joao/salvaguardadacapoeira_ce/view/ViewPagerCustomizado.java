package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Script on 07/04/2018.
 */

public class ViewPagerCustomizado extends ViewPager{

    private boolean scrolHabilitado = true;
    public ViewPagerCustomizado(Context context) {
        super(context);
    }

    public ViewPagerCustomizado(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.scrolHabilitado && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return  this.scrolHabilitado && super.onTouchEvent(ev);
    }
    public void setHabilitar(Boolean b){
        this.scrolHabilitado = b;
    }
}
