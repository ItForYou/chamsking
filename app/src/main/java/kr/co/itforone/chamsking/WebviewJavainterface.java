package kr.co.itforone.chamsking;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

class WebviewJavainterface {
    Activity activity;
    MainActivity mainActivity;
    SwipeRefreshLayout refreshLayout;
    Uri mCapturedImageURI;


    WebviewJavainterface (MainActivity mainActivity){
        this.mainActivity=mainActivity;
    }
    WebviewJavainterface(Activity activity){
        this.activity=activity;
    }
    WebviewJavainterface(Activity activity,SwipeRefreshLayout refreshLayout){
        this.activity=activity;
        this.refreshLayout=refreshLayout;
    }

    @JavascriptInterface
    public void call_app() {
        String number ="";
        number = "tel:051-891-0088";
        mainActivity.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(number)));
    }

    @JavascriptInterface
    public void NoRefresh(){
        refreshLayout.setEnabled(false);
    }

    @JavascriptInterface
    public void YesRefresh(){
        refreshLayout.setEnabled(true);
    }
}
