package kirsurabaya.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;

import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.model.JumlahKendaraanUji;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeKendaraanUjiActivity extends AppCompatActivity {

    BottomBar mBottomBar;
    SwipeRefreshLayout swipe;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_kendaraan_uji);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setTitle("Uji Kendaraan");
//        toolbar.setLogo(R.drawable.dishub_blue);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.mipmap.back_row_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
            }
        });

        swipe = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        /*event ketika widget dijalankan*/
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshItem();
            }

            void refreshItem() {
                getData();
                onItemLoad();
            }

            void onItemLoad() {
                swipe.setRefreshing(false);
            }
        });

        getData();

        /*mBottomBar = (BottomBar) findViewById(R.id.bottomBarKendaraanUji);
        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            Fragment fragment = null;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.BottomBarData) {
                    fragment = new KendaraanUjiDataFragment();
                } else if (tabId == R.id.BottomBarList) {
                    fragment = new KendaraanUjiListFragment();
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainerKendaraanUji, fragment)
                        .commit();
            }
        });*/
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void getData() {
        pDialog.setMessage("Please wait...");
        showDialog();
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<JumlahKendaraanUji> call = service.getTotalListKendaraan();
        call.enqueue(new Callback<JumlahKendaraanUji>() {
            @Override
            public void onResponse(Call<JumlahKendaraanUji> call, Response<JumlahKendaraanUji> response) {
                try {
                    hideDialog();
                    Integer getMobilBarangWiyung = response.body().getMobilBarangWiyung();
                    Integer getMobilBisWiyung = response.body().getMobilBisWiyung();
                    Integer getMobilPenumpangWiyung = response.body().getMobilPenumpangWiyung();
                    Integer getJumlahWiyung = response.body().getJumlahWiyung();
                    Integer getMobilDatangWiyung = response.body().getMobilDatangWiyung();
                    Integer getMobilTidakDatangWiyung = response.body().getMobilTidakDatangWiyung();
                    Integer getLulusUjiWiyung = response.body().getLulusUjiWiyung();
                    Integer getTidakLulusUjiWiyung = response.body().getTidakLulusUjiWiyung();

                    Integer getMobilBarangTandes = response.body().getMobilBarangTandes();
                    Integer getMobilBisTandes = response.body().getMobilBisTandes();
                    Integer getMobilPenumpangTandes = response.body().getMobilPenumpangTandes();
                    Integer getMobilKhususTandes = response.body().getMobilKhususTandes();
                    Integer getMobilGandenganTandes = response.body().getMobilGandenganTandes();
                    Integer getMobilTempelanTandes = response.body().getMobilTempelanTandes();
                    Integer getJumlahTandes = response.body().getJumlahTandes();
                    Integer getMobilDatangTandes = response.body().getMobilDatangTandes();
                    Integer getMobilTidakDatangTandes = response.body().getMobilTidakDatangTandes();
                    Integer getLulusUjiTandes = response.body().getLulusUjiTandes();
                    Integer getTidakLulusUjiTandes = response.body().getTidakLulusUjiTandes();

//                    TextView txtMobilBarangWiyung = (TextView) findViewById(R.id.txtMobilBarangWiyung);
//                    TextView txtMobilBusWiyung = (TextView) findViewById(R.id.txtMobilBusWiyung);
//                    TextView txtMobilPenumpangWiyung = (TextView) findViewById(R.id.txtMobilPenumpangWiyung);
//                    TextView txtTotalKendaraanWiyung = (TextView) findViewById(R.id.txtTotalKendaraanWiyung);
//                    TextView txtTotalKendaraanDatangWiyung = (TextView) findViewById(R.id.txtTotalKendaraanDatangWiyung);
//                    TextView txtTotalKendaraanTidakDatangWiyung = (TextView) findViewById(R.id.txtTotalKendaraanTidakDatangWiyung);
//                    TextView txtTotalLulusWiyung = (TextView) findViewById(R.id.txtTotalLulusWiyung);
//                    TextView txtTotalTidakLulusWiyung = (TextView) findViewById(R.id.txtTotalTidakLulusWiyung);

                    TextView txtMobilBarangTandes = (TextView) findViewById(R.id.txtMobilBarangTandes);
                    TextView txtMobilBusTandes = (TextView) findViewById(R.id.txtMobilBusTandes);
                    TextView txtMobilPenumpangTandes = (TextView) findViewById(R.id.txtMobilPenumpangTandes);
                    TextView txtMobilKhususTandes = (TextView) findViewById(R.id.txtMobilKhususTandes);
                    TextView txtMobilGandenganTandes = (TextView) findViewById(R.id.txtMobilGandenganTandes);
                    TextView txtMobilTempelanTandes = (TextView) findViewById(R.id.txtMobilTempelanTandes);
                    TextView txtTotalKendaraanTandes = (TextView) findViewById(R.id.txtTotalKendaraanTandes);
                    TextView txtTotalKendaraanDatangTandes = (TextView) findViewById(R.id.txtTotalKendaraanDatangTandes);
                    TextView txtTotalKendaraanTidakDatangTandes = (TextView) findViewById(R.id.txtTotalKendaraanTidakDatangTandes);
                    TextView txtTotalLulusTandes = (TextView) findViewById(R.id.txtTotalLulusTandes);
                    TextView txtTotalTidakLulusTandes = (TextView) findViewById(R.id.txtTotalTidakLulusTandes);

//                    txtMobilBarangWiyung.setText(String.valueOf(getMobilBarangWiyung));
//                    txtMobilBusWiyung.setText(String.valueOf(getMobilBisWiyung));
//                    txtMobilPenumpangWiyung.setText(String.valueOf(getMobilPenumpangWiyung));
//                    txtTotalKendaraanWiyung.setText(String.valueOf(getJumlahWiyung));
//                    txtTotalKendaraanDatangWiyung.setText(String.valueOf(getMobilDatangWiyung));
//                    txtTotalKendaraanTidakDatangWiyung.setText(String.valueOf(getMobilTidakDatangWiyung));
//                    txtTotalLulusWiyung.setText(String.valueOf(getLulusUjiWiyung));
//                    txtTotalTidakLulusWiyung.setText(String.valueOf(getTidakLulusUjiWiyung));

                    txtMobilBarangTandes.setText(String.valueOf(getMobilBarangTandes));
                    txtMobilBusTandes.setText(String.valueOf(getMobilBisTandes));
                    txtMobilPenumpangTandes.setText(String.valueOf(getMobilPenumpangTandes));
                    txtMobilKhususTandes.setText(String.valueOf(getMobilKhususTandes));
                    txtMobilGandenganTandes.setText(String.valueOf(getMobilGandenganTandes));
                    txtMobilTempelanTandes.setText(String.valueOf(getMobilTempelanTandes));
                    txtTotalKendaraanTandes.setText(String.valueOf(getJumlahTandes));
                    txtTotalKendaraanDatangTandes.setText(String.valueOf(getMobilDatangTandes));
                    txtTotalKendaraanTidakDatangTandes.setText(String.valueOf(getMobilTidakDatangTandes));
                    txtTotalLulusTandes.setText(String.valueOf(getLulusUjiTandes));
                    txtTotalTidakLulusTandes.setText(String.valueOf(getTidakLulusUjiTandes));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JumlahKendaraanUji> call, Throwable t) {
                hideDialog();
            }
        });
    }
}
