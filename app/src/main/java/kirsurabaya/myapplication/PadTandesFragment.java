package kirsurabaya.myapplication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import kirsurabaya.myapplication.R;

/**
 * Created by TIA.WICAKSONO on 6/19/2017.
 */

public class PadTandesFragment extends Fragment{
    WebView mWebView;
    ProgressDialog pDialog;
    boolean timeout = true;

    public PadTandesFragment() {
        // Required empty public constructor
        timeout = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pad_tandes, container, false);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        String alamatUrl = "http://36.67.46.113/pad/pad.php";

        mWebView = (WebView) view.findViewById(R.id.webView);

//        ConnectivityManager connMgr = (ConnectivityManager) getActivity()
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isConnected()) {
        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
//                pDialog.setMessage("Please wait...");
//                showDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(timeout) {
                            // do what you want
//                            Toast.makeText(getActivity(),"Connection Timed out",Toast.LENGTH_LONG).show();
                        }
                    }
                }).start();
            }

            @Override
            public void onPageFinished(WebView view, String url){
//                hideDialog();
                timeout = false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                //Your code to do
                timeout = false;
//                Toast.makeText(getActivity(), "Your Internet Connection May not be active" , Toast.LENGTH_LONG).show();
            }

        });
        mWebView.loadUrl(alamatUrl);
//        }
//        else
//        {
//            Toast.makeText(getActivity(), "Your Internet Connection May not be active", Toast.LENGTH_LONG).show();
//        }
        return view;
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
