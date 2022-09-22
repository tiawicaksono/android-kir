package kirsurabaya.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import kirsurabaya.myapplication.model.RetribusiMendatang;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeQrCodeSmartCardActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_qrcode_smartcard);

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setCaptureActivity(PotraitCaptureActivity.class);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Success, Please Wait", Toast.LENGTH_LONG).show();
            }
            handleResult(result);
        }
    }

    public void handleResult(IntentResult result) {
        if (Patterns.WEB_URL.matcher(result.getContents()).matches()) {
//            // Open URL
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getContents()));
//            startActivity(browserIntent);

            webView = (WebView) findViewById(R.id.webViewLink);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(String.valueOf(Uri.parse(result.getContents())));
        }else{
            Toast.makeText(this, "Not Match", Toast.LENGTH_LONG).show();
        }
    }
}
