package kirsurabaya.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.model.StatusRekom;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRekomActivity extends AppCompatActivity {

    private FloatingSearchView mSearchView;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_rekom);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Status Rekom");
//        toolbar.setLogo(R.drawable.dishub_blue);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.mipmap.back_row_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
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
        Call<StatusRekom> call = service.getStatusRekom(noUji);
        call.enqueue(new Callback<StatusRekom>() {
            @Override
            public void onResponse(Call<StatusRekom> call, Response<StatusRekom> response) {
                try {
                    hideDialog(); //progress dialog dihentikan

                    String getNoUji = response.body().getNoUji();
                    String getNoKendaraan = response.body().getNoKendaraan();
                    String getRekom = response.body().getRekom();
                    String getLokasiRekom = response.body().getLokasiRekom();
                    String getTglRekom = response.body().getTglRekom();
                    Integer getKasubag = response.body().getKasubag();
                    Integer getKaupt = response.body().getKaupt();
                    Integer getKadis = response.body().getKadis();
                    String getTglKasubag = response.body().getTglKasubag();
                    String getTglKaupt = response.body().getTglKaupt();
                    String getTglKadis = response.body().getTglKadis();

                    TextView txtNoKend = (TextView) findViewById(R.id.txtNoKend);
                    TextView txtNoUji = (TextView) findViewById(R.id.txtNoUji);
                    TextView txtRekomendasi = (TextView) findViewById(R.id.txtRekomendasi);
                    TextView txtLokasiRekom = (TextView) findViewById(R.id.txtLokasiRekom);
                    TextView txtTglRekom = (TextView) findViewById(R.id.txtTglRekom);
                    TextView tglApproveRekomKasubag = (TextView) findViewById(R.id.tglApproveRekomKasubag);
                    TextView tglApproveRekomKaupt = (TextView) findViewById(R.id.tglApproveRekomKaupt);
                    TextView tglApproveRekomKadis = (TextView) findViewById(R.id.tglApproveRekomKadis);

                    txtNoKend.setText(getNoKendaraan);
                    txtNoUji.setText(getNoUji);
                    txtRekomendasi.setText(getRekom);
                    txtLokasiRekom.setText(getLokasiRekom);
                    txtTglRekom.setText(getTglRekom);
                    tglApproveRekomKasubag.setText(getTglKasubag);
                    tglApproveRekomKaupt.setText(getTglKaupt);
                    tglApproveRekomKadis.setText(getTglKadis);

                    ImageView imgKasubag = (ImageView) findViewById(R.id.imgKasubag);
                    if(getKasubag == 0){
                        imgKasubag.setImageResource(R.mipmap.ic_rekom_progress);
                    }else if(getKasubag == 1){
                        imgKasubag.setImageResource(R.mipmap.ic_rekom_approve);
                    }else{
                        imgKasubag.setImageResource(R.mipmap.ic_rekom_reject);
                    }

                    ImageView imgKaupt = (ImageView) findViewById(R.id.imgKaupt);
                    if(getKaupt == 0){
                        imgKaupt.setImageResource(R.mipmap.ic_rekom_progress);
                    }else if(getKaupt == 1){
                        imgKaupt.setImageResource(R.mipmap.ic_rekom_approve);
                    }else{
                        imgKaupt.setImageResource(R.mipmap.ic_rekom_reject);
                    }

                    ImageView imgKadis = (ImageView) findViewById(R.id.imgKadis);
                    if(getKadis == 0){
                        imgKadis.setImageResource(R.mipmap.ic_rekom_progress);
                    }else if(getKadis == 1){
                        imgKadis.setImageResource(R.mipmap.ic_rekom_approve);
                    }else{
                        imgKadis.setImageResource(R.mipmap.ic_rekom_reject);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<StatusRekom> call, Throwable t) {
                hideDialog();
            }

        });
    }
}
