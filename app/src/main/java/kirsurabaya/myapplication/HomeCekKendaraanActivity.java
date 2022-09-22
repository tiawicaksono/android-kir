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

import kirsurabaya.myapplication.model.CekKendaraan;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeCekKendaraanActivity extends AppCompatActivity {

    private FloatingSearchView mSearchView;
    private ProgressDialog pDialog;
    private Button scan_btn;
    private String dataTempNoUji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cek_kendaraan);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Cek Kendaraan");
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

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<CekKendaraan> call = service.getDataKendaraan(noUji);
        call.enqueue(new Callback<CekKendaraan>() {
            @Override
            public void onResponse(Call<CekKendaraan> call, Response<CekKendaraan> response) {
                try {
                    hideDialog(); //progress dialog dihentikan

                    String getNoUji = response.body().getNoUji();
                    String getNoKendaraan = response.body().getNoKendaraan();
                    String getPemilik = response.body().getPemilik();
                    String getJnsKend = response.body().getJnsKend();
                    String getMerk = response.body().getMerk();
                    String getTipe = response.body().getTipe();
                    String getNoChasis = response.body().getNoChasis();
                    String getNoMesin = response.body().getNoMesin();
                    String getMatiUji = response.body().getMatiUji();
                    String getPanjang = response.body().getPanjang();
                    String getLebar = response.body().getLebar();
                    String getTinggi = response.body().getTinggi();
                    String getDimpanjang = response.body().getDimpanjang();
                    String getDimlebar = response.body().getDimlebar();
                    String getDimtinggi = response.body().getDimtinggi();

                    String getKondisi = response.body().getKondisi();
                    String getNamaKomersil = response.body().getNamaKomersil();
                    String getJenisKaroseri = response.body().getJenisKaroseri();
                    String getBahanUtama = response.body().getBahanUtama();
                    /*BERAT KOSONG*/
                    String getSb1 = response.body().getSb1();
                    String getSb2 = response.body().getSb2();
                    String getSb3 = response.body().getSb3();
                    String getSb4 = response.body().getSb4();
                    String getSb5 = response.body().getSb5();
                    String getTotalSb = response.body().getTotalSb();
                    String getOrang = response.body().getOrang();
                    String getBarang = response.body().getBarang();
                    String getJbb = response.body().getJbb();
                    String getJbi = response.body().getJbi();
                    String getMst = response.body().getMst();
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
                    TextView txtJnsKend = (TextView) findViewById(R.id.txtJnsKend);
                    TextView txtMerk = (TextView) findViewById(R.id.txtMerk);
                    TextView txtTipe = (TextView) findViewById(R.id.txtTipe);
                    TextView txtNoChasis = (TextView) findViewById(R.id.txtNoChasis);
                    TextView txtNoMesin = (TextView) findViewById(R.id.txtNoMesin);
                    TextView txtMatiUji = (TextView) findViewById(R.id.txtMatiUji);

                    TextView txtNamaKomersil = (TextView) findViewById(R.id.txtNamaKomersil);
                    TextView txtJenisKaroseri = (TextView) findViewById(R.id.txtJenisKaroseri);
                    TextView txtBahanUtama = (TextView) findViewById(R.id.txtBahanUtama);

                    TextView txtPanjangUtama = (TextView) findViewById(R.id.txtPanjangUtama);
                    TextView txtLebarUtama = (TextView) findViewById(R.id.txtLebarUtama);
                    TextView txtTinggiUtama = (TextView) findViewById(R.id.txtTinggiUtama);
                    TextView txtPanjang = (TextView) findViewById(R.id.txtPanjang);
                    TextView txtLebar = (TextView) findViewById(R.id.txtLebar);
                    TextView txtTinggi = (TextView) findViewById(R.id.txtTinggi);

                    TextView txtSb1 = (TextView) findViewById(R.id.txtSb1);
                    TextView txtSb2 = (TextView) findViewById(R.id.txtSb2);
                    TextView txtSb3 = (TextView) findViewById(R.id.txtSb3);
                    TextView txtSb4 = (TextView) findViewById(R.id.txtSb4);
                    TextView txtSb5 = (TextView) findViewById(R.id.txtSb5);
                    TextView txtTotalBeratSumbu = (TextView) findViewById(R.id.txtTotalBeratSumbu);

                    TextView txtOrang = (TextView) findViewById(R.id.txtOrang);
                    TextView txtBarang = (TextView) findViewById(R.id.txtBarang);
                    TextView txtJbb = (TextView) findViewById(R.id.txtJbb);
                    TextView txtJbi = (TextView) findViewById(R.id.txtJbi);
                    TextView txtMst = (TextView) findViewById(R.id.txtMst);

                    txtNoUji.setText(getNoUji);
                    txtNoKend.setText(getNoKendaraan);
                    txtNamaPemilik.setText(getPemilik);
                    txtJnsKend.setText(getJnsKend);
                    txtMerk.setText(getMerk);
                    txtTipe.setText(getTipe);
                    txtNoChasis.setText(getNoChasis);
                    txtNoMesin.setText(getNoMesin);
                    txtMatiUji.setText(getMatiUji);
                    if (getKondisi.equals("mati")) {
//                        txtMatiUji.setTextColor(Color.parseColor("#FF5656"));
                        txtMatiUji.setTextColor(Color.RED);
                    }

                    txtNamaKomersil.setText(getNamaKomersil);
                    txtJenisKaroseri.setText(getJenisKaroseri);
                    txtBahanUtama.setText(getBahanUtama);

                    txtPanjangUtama.setText(getPanjang);
                    txtLebarUtama.setText(getLebar);
                    txtTinggiUtama.setText(getTinggi);
                    txtPanjang.setText(getDimpanjang);
                    txtLebar.setText(getDimlebar);
                    txtTinggi.setText(getDimtinggi);

                    txtSb1.setText(getSb1);
                    txtSb2.setText(getSb2);
                    txtSb3.setText(getSb3);
                    txtSb4.setText(getSb4);
                    txtSb5.setText(getSb5);
                    txtTotalBeratSumbu.setText(getTotalSb);

                    txtOrang.setText(getOrang);
                    txtBarang.setText(getBarang);
                    txtJbb.setText(getJbb);
                    txtJbi.setText(getJbi);
                    txtMst.setText(getMst);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<CekKendaraan> call, Throwable t) {
                hideDialog();
            }

        });
    }
}
