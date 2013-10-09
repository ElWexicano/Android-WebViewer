package ie.android.mywebviewer;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends Activity {

    public static String EXTRA_WEB_VIEW_URL = "extra_web_view_url";
    public static String EXTRA_ENABLE_FULLSCREEN = "extra_enable_fullscreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextUrl = (EditText) findViewById(R.id.edit_text_url);

        final CheckBox checkBoxEnableFullScreen = (CheckBox) findViewById(R.id.check_box_enable);

        final CheckBox checkBoxHttpSecure = (CheckBox) findViewById(R.id.check_box_http_secure);

        Button buttonOpenWebView = (Button) findViewById(R.id.button_open_view);

        buttonOpenWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = checkBoxHttpSecure.isChecked() ? "http://" : "https://";

                url += editTextUrl.getText().toString();

                boolean checked = checkBoxEnableFullScreen.isChecked();

                openWebView(url, checked);
            }
        });
    }


    private void openWebView(String url, boolean enableFullscreen) {
        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);

        intent.putExtra(EXTRA_WEB_VIEW_URL, url);
        intent.putExtra(EXTRA_ENABLE_FULLSCREEN, enableFullscreen);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
