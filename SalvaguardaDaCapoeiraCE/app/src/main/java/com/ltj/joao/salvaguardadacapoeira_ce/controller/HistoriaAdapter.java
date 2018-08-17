package com.ltj.joao.salvaguardadacapoeira_ce.controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ltj.joao.salvaguardadacapoeira_ce.R;


public class  HistoriaAdapter extends PagerAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
   public HistoriaAdapter(Context context){
       this.context = context;
   }


    String []url = {"file:///android_asset/historia/index1.html","file:///android_asset/historia/index2.html","file:///android_asset/historia/index3.html"};

    @Override
    public int getCount() {
        return url.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public Object instantiateItem(ViewGroup conteiner, int position){
        layoutInflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_historia,null);

        WebView webView = (WebView)view.findViewById(R.id.webView);
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(true);
        webView.loadUrl(url[position]);
        conteiner.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup conteiner,int position,Object object){
            conteiner.removeView((View)object);
    }
}
