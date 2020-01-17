package kr.co.itforone.chamsking;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TimePicker;
import android.widget.Toast;

class ClientManager extends WebViewClient {
    Activity activity;
    MainActivity mainActivity;
    ClientManager(Activity activity){
        this.activity = activity;
    }
    ClientManager(Activity activity, MainActivity mainActivity){
        this.mainActivity = mainActivity;
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        if(url.contains("competition_map")){
            double let = mainActivity.getlat() * 1000000;
            double lng = mainActivity.getlng() * 1000000;
            let = Math.ceil(let)/1000000;
            lng = Math.ceil(lng)/1000000;
            mainActivity.webView.loadUrl("javascript:move_map('"+let+"','"+lng+"');");
        }
    }
}
