package kr.co.itforone.chamsking;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
}
