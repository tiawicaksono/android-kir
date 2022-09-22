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

import kirsurabaya.myapplication.model.RetribusiMendatang;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRetribusiActivity extends AppCompatActivity {

    private FloatingSearchView mSearchView;
    private ProgressDialog pDialog;
    private ImageView myImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_retribusi);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Biaya Uji Mendatang");
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
        Call<RetribusiMendatang> call = service.getRetribusiMendatang(noUji);
        call.enqueue(new Callback<RetribusiMendatang>() {
            @Override
            public void onResponse(Call<RetribusiMendatang> call, Response<RetribusiMendatang> response) {
                try {
                    hideDialog(); //progress dialog dihentikan

                    String getNoUji = response.body().getNoUji();
                    String getNoKendaraan = response.body().getNoKendaraan();
                    String getTglUji = response.body().getTglUji();
                    String getRetribusi = response.body().getRetribusi();
                    String getBuku = response.body().getBuku();
                    String getDenda = response.body().getDenda();
                    String getTotal = response.body().getTotal();

                    TextView txtNoUji = (TextView) findViewById(R.id.txtNoUji);
                    TextView txtNoKend = (TextView) findViewById(R.id.txtNoKend);
                    TextView txtTglUji = (TextView) findViewById(R.id.txtTglUji);
                    TextView txtRetribusi = (TextView) findViewById(R.id.txtRetribusi);
                    TextView txtGantiBuku = (TextView) findViewById(R.id.txtGantiBuku);
                    TextView txtDenda = (TextView) findViewById(R.id.txtDenda);
                    TextView txtTotal = (TextView) findViewById(R.id.txtTotal);

                    txtNoUji.setText(getNoUji);
                    txtNoKend.setText(getNoKendaraan);
                    txtTglUji.setText(getTglUji);
                    txtRetribusi.setText(getRetribusi);
                    txtGantiBuku.setText(getBuku);
                    txtDenda.setText(getDenda);
                    txtTotal.setText(getTotal);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RetribusiMendatang> call, Throwable t) {
                hideDialog();
            }

        });
    }
}
