package kirsurabaya.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.model.HasilUji;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeHasilActivity extends AppCompatActivity {

    private FloatingSearchView mSearchView;
    private ProgressDialog pDialog;
    private Button scan_btn;
    private String dataTempNoUji;
    private ImageView myImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_hasil);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Hasil Pengujian");
//        toolbar.setLogo(R.drawable.dishub_blue);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.mipmap.back_row_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
            }
        });

        //SCAN QRCODE
        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setCaptureActivity(PotraitCaptureActivity.class);
                integrator.initiateScan();
            }
        });

        mSearchView = (FloatingSearchView) findViewById(R.id.noUjiKendaraanSearch);
        setupFloatingSearch();
    }

    private void setupFloatingSearch() {
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
//                Toast.makeText(getApplicationContext(),currentQuery,Toast.LENGTH_LONG).show();
                getData(currentQuery);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
            } else {
                //ENCODE
                //DECODE
                dataTempNoUji = result.getContents();
//                byte[] decodeValue = Base64.decode(dataTempNoUji,Base64.DEFAULT);
//                mSearchView.setSearchText(new String(decodeValue));
//                getData(new String(decodeValue));
                mSearchView.setSearchText(new String(dataTempNoUji));
                getData(new String(dataTempNoUji));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void getData(String noUji) {
        pDialog.setMessage("Please wait...");
        showDialog();
//        Toast.makeText(getApplicationContext(),noUji,Toast.LENGTH_LONG).show();
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<HasilUji> call = service.getHasilUji(noUji);
        call.enqueue(new Callback<HasilUji>() {
            @Override
            public void onResponse(Call<HasilUji> call, Response<HasilUji> response) {
                try {
                    hideDialog(); //progress dialog dihentikan

                    String getNoUji = response.body().getNoUji();
                    String getNoKendaraan = response.body().getNoKendaraan();
                    String getPemilik = response.body().getPemilik();
                    String getTglMatiUji = response.body().getHasilTglMatiUji();
                    String getTglTerakhirUji = response.body().getHasilTglTerakhirUji();
                    String getHasilPrauji = response.body().getHasilPrauji();
                    String getHasilEmisi = response.body().getHasilEmisi();
                    String getHasilLampu = response.body().getHasilLampu();
                    String getHasilPitlift = response.body().getHasilPitlift();
                    String getHasilBreak = response.body().getHasilBreak();
                    String getLtl = response.body().getLtl();
                    String getKeterangan = response.body().getKeterangan();
                    String getKondisi = response.body().getKondisi();
                    String fotoDepan = response.body().getImgDepan();
                    String fotoBelakang = response.body().getImgBelakang();
                    String fotoKanan = response.body().getImgKanan();
                    String fotoKiri = response.body().getImgKiri();

                    ImageView imgDepan = (ImageView) findViewById(R.id.imgDepan);
                    if(fotoDepan.equals("-")){
                        imgDepan.setImageDrawable(getDrawable(R.drawable.ic_image_disabled));
                    }else {
                        byte[] decodeStringImgDepan = Base64.decode(fotoDepan, Base64.DEFAULT);
                        Bitmap decodedImgDepan = BitmapFactory.decodeByteArray(decodeStringImgDepan, 0, decodeStringImgDepan.length);
                        imgDepan.setImageBitmap(decodedImgDepan);
                    }

                    ImageView imgBelakang = (ImageView) findViewById(R.id.imgBelakang);
                    if(fotoBelakang.equals("-")) {
                        imgBelakang.setImageDrawable(getDrawable(R.drawable.ic_image_disabled));
                    }else {
                        byte[] decodeStringImgBelakang = Base64.decode(fotoBelakang, Base64.DEFAULT);
                        Bitmap decodedImgBelakang = BitmapFactory.decodeByteArray(decodeStringImgBelakang, 0, decodeStringImgBelakang.length);
                        imgBelakang.setImageBitmap(decodedImgBelakang);
                    }

                    ImageView imgKanan = (ImageView) findViewById(R.id.imgKanan);
                    if(fotoKanan.equals("-")) {
                        imgKanan.setImageDrawable(getDrawable(R.drawable.ic_image_disabled));
                    }else {
                        byte[] decodeStringImgKanan = Base64.decode(String.valueOf(fotoKanan), Base64.DEFAULT);
                        Bitmap decodedImgKanan = BitmapFactory.decodeByteArray(decodeStringImgKanan, 0, decodeStringImgKanan.length);
                        imgKanan.setImageBitmap(decodedImgKanan);
                    }

                    ImageView imgKiri = (ImageView) findViewById(R.id.imgKiri);
                    if(fotoKiri.equals("-")) {
                        imgKiri.setImageDrawable(getDrawable(R.drawable.ic_image_disabled));
                    }else {
                        byte[] decodeStringImgKiri = Base64.decode(fotoKiri, Base64.DEFAULT);
                        Bitmap decodedImgKiri = BitmapFactory.decodeByteArray(decodeStringImgKiri, 0, decodeStringImgKiri.length);
                        imgKiri.setImageBitmap(decodedImgKiri);
                    }

                    TextView txtNoKend = (TextView) findViewById(R.id.txtNoKend);
                    TextView txtNoUji = (TextView) findViewById(R.id.txtNoUji);
                    TextView txtNamaPemilik = (TextView) findViewById(R.id.txtNamaPemilik);
                    TextView txtTglMatiUji = (TextView) findViewById(R.id.txtTglMatiUji);
                    TextView txtTglTerkahirUji = (TextView) findViewById(R.id.txtTglTerkahirUji);
                    TextView txtPrauji = (TextView) findViewById(R.id.txtPrauji);
                    TextView txtEmisi = (TextView) findViewById(R.id.txtEmisi);
                    TextView txtLampu = (TextView) findViewById(R.id.txtLampu);
                    TextView txtPitlift = (TextView) findViewById(R.id.txtPitlift);
                    TextView txtBrake = (TextView) findViewById(R.id.txtBrake);
                    TextView txtKeterangan = (TextView) findViewById(R.id.txtKeterangan);

                    txtNoUji.setText(getNoUji);
                    txtNoKend.setText(getNoKendaraan);
                    txtNamaPemilik.setText(getPemilik);
                    txtTglMatiUji.setText(getTglMatiUji);
                    txtTglTerkahirUji.setText(getTglTerakhirUji);
                    txtPrauji.setText(getHasilPrauji);
                    txtEmisi.setText(getHasilEmisi);
                    txtLampu.setText(getHasilLampu);
                    txtPitlift.setText(getHasilPitlift);
                    txtBrake.setText(getHasilBreak);
                    txtKeterangan.setText(getKeterangan);
                    if (getKondisi.equals("mati")) {
//                        txtMatiUji.setTextColor(Color.parseColor("#FF5656"));
                        txtTglMatiUji.setTextColor(Color.RED);
                    }
                    myImageView = (ImageView)findViewById(R.id.imgKelulusan);
                    if(getLtl.equals("LULUS")){
                        myImageView.setImageResource(R.drawable.ic_lulus);
                    }else if(getLtl.equals("TIDAK LULUS")){
                        myImageView.setImageResource(R.drawable.ic_tidak_lulus);
                    }else{
                        myImageView.setImageResource(R.drawable.ic_proses);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<HasilUji> call, Throwable t) {
                hideDialog();
            }

        });
    }
}
