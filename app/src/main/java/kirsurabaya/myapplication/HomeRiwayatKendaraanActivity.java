package kirsurabaya.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.adapter.RecyclerViewRiwayatKendaraanAdapter;
import kirsurabaya.myapplication.model.CekKendaraan;
import kirsurabaya.myapplication.model.ListRiwayatKendaraan;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRiwayatKendaraanActivity extends AppCompatActivity {

    private FloatingSearchView mSearchView;
    private ProgressDialog pDialog;
    SwipeRefreshLayout dorefresh;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_riwayat_kendaraan);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Riwayat Kendaraan");
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

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
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

    private void getData(String noUji) {
        try {
            pDialog.setMessage("Please wait...");
            showDialog();

            APIService services = ApiClient.getClient().create(APIService.class);
            Call<CekKendaraan> calls = services.getDataKendaraan(noUji);
            calls.enqueue(new Callback<CekKendaraan>() {

                @Override
                public void onResponse(Call<CekKendaraan> call, Response<CekKendaraan> response) {
                    hideDialog();
                    String getNoUji = response.body().getNoUji();
                    String getNoKendaraan = response.body().getNoKendaraan();
                    String getMerk = response.body().getMerk();
                    String getTipe = response.body().getTipe();
                    String getNoChasis = response.body().getNoChasis();
                    String getNoMesin = response.body().getNoMesin();

                    TextView txtNoKend = (TextView) findViewById(R.id.txtNoKend);
                    TextView txtNoUji = (TextView) findViewById(R.id.txtNoUji);
                    TextView txtMerk = (TextView) findViewById(R.id.txtMerk);
                    TextView txtTipe = (TextView) findViewById(R.id.txtTipe);
                    TextView txtNoChasis = (TextView) findViewById(R.id.txtNoChasis);
                    TextView txtNoMesin = (TextView) findViewById(R.id.txtNoMesin);

                    txtNoUji.setText(getNoUji);
                    txtNoKend.setText(getNoKendaraan);
                    txtMerk.setText(getMerk);
                    txtTipe.setText(getTipe);
                    txtNoChasis.setText(getNoChasis);
                    txtNoMesin.setText(getNoMesin);

                    getDataRiwayat(getNoUji);
                }

                @Override
                public void onFailure(Call<CekKendaraan> call, Throwable t) {
                    hideDialog();
                    Log.d("onFailure", t.toString());
                }
            });


        } catch (Exception e) {
            hideDialog();
        }
    }

    private void getDataRiwayat(String getNoUji) {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<List<ListRiwayatKendaraan>> call = service.getRiwayatKendaraan(getNoUji);
        call.enqueue(new Callback<List<ListRiwayatKendaraan>>() {
            @Override
            public void onResponse(Call<List<ListRiwayatKendaraan>> call, Response<List<ListRiwayatKendaraan>> response) {
                try {
//                    hideDialog();
                    List<ListRiwayatKendaraan> rowListRiwayat = response.body();
                    layoutManager = new LinearLayoutManager(HomeRiwayatKendaraanActivity.this);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                    recyclerView.setLayoutManager(layoutManager);
                    RecyclerViewRiwayatKendaraanAdapter recyclerViewAdapter = new RecyclerViewRiwayatKendaraanAdapter(rowListRiwayat);
                    recyclerView.setAdapter(recyclerViewAdapter);
                } catch (Exception e) {
//                        e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ListRiwayatKendaraan>> call, Throwable t) {
//                hideDialog();
                Log.d("onFailure", t.toString());
            }
        });
    }
}
