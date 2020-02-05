package kr.co.itforone.chamsking;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.os.Parcelable;
import android.provider.MediaStore;

import android.util.Log;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.kakao.auth.AuthType;
import com.kakao.auth.Session;

import java.io.File;

class ChoromeManager extends WebChromeClient {
    MainActivity mainActivity;
    Activity activity;
    static final int FILECHOOSER_LOLLIPOP_REQ_CODE=1300;
    Uri mCapturedImageURI;

    ChoromeManager(MainActivity mainActivity, Activity activity){
        this.mainActivity = mainActivity;
        this.activity = activity;
    }

    ChoromeManager(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    ChoromeManager(){}


    @Override
    public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture,
                                  Message resultMsg) {

        if(view.getUrl().contains("login")){
            Session session = Session.getCurrentSession();
            session.addCallback(new SessionCalBack());
            session.open(AuthType.KAKAO_ACCOUNT,mainActivity);
        }
        return true;
    }
    @Override
    public void onCloseWindow(WebView window){
        super.onCloseWindow(window);
        mainActivity.webView.removeView(window);
    }
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {

        mainActivity.set_filePathCallbackLollipop(filePathCallback);

        // Create AndroidExampleFolder at sdcard
        File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AndroidExampleFolder");
        if (!imageStorageDir.exists()) {
            // Create AndroidExampleFolder at sdcard
            imageStorageDir.mkdirs();
        }

        // Create camera captured image file path and name
        File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        mCapturedImageURI = Uri.fromFile(file);

        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");

        // Create file chooser intent
        Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
        // Set camera intent to file chooser
       // chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{captureIntent});

        // On select image call onActivityResult method of activity
        activity.startActivityForResult(chooserIntent, FILECHOOSER_LOLLIPOP_REQ_CODE);
        return true;
    }


    //어럴트 창 처리
    @Override
    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        new AlertDialog.Builder(view.getContext())
                .setTitle("")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                result.confirm();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                result.cancel();
                            }
                        })
                .create()
                .show();
        return true;
    }

    public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result)
    {
        new AlertDialog.Builder(view.getContext())
                .setTitle("")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok,
                        new AlertDialog.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                result.confirm();
                            }
                        })
                .setCancelable(false)
                .create()
                .show();

        return true;
    }
}
