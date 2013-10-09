package ie.android.mywebviewer;

import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

    private String mUrl;
    private boolean mEnableFullscreen;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            restoreInstanceState(bundle);
        }

        if (mEnableFullscreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        setContentView(R.layout.activity_web_view);

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setSaveFormData(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        mWebView.loadUrl(mUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_view, menu);
        return true;
    }

    private void restoreInstanceState(Bundle bundle) {

        if (bundle.containsKey(MainActivity.EXTRA_ENABLE_FULLSCREEN)) {
            mEnableFullscreen = bundle.getBoolean(MainActivity.EXTRA_ENABLE_FULLSCREEN);
        }

        if (bundle.containsKey(MainActivity.EXTRA_WEB_VIEW_URL)) {
            mUrl = bundle.getString(MainActivity.EXTRA_WEB_VIEW_URL);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            case R.id.action_refresh:
                mWebView.loadUrl(mUrl);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
