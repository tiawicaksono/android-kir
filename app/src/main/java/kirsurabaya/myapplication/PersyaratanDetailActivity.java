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

import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.adapter.RecyclerViewPersyaratanAdapter;
import kirsurabaya.myapplication.model.Persyaratan;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersyaratanDetailActivity extends AppCompatActivity {

    String idKiriman;
    SwipeRefreshLayout dorefresh;
    String persyaratanTitle;
    LinearLayoutManager layoutManager;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persyaratan_detail);
        Intent intent = getIntent();
        idKiriman = intent.getExtras().getString("kategori");
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        dorefresh = (SwipeRefreshLayout)findViewById(R.id.swipeRefresh);
        dorefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        /*event ketika widget dijalankan*/
        dorefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshItem();
            }

            void refreshItem() {
                getPersyaratan(idKiriman);
                onItemLoad();
            }

            void onItemLoad() {
                dorefresh.setRefreshing(false);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        switch (idKiriman) {
            case "PUP":
                persyaratanTitle = "Uji Baru";
                break;
            case "PUB":
                persyaratanTitle = "Uji Berkala";
                break;
            case "MUM":
                persyaratanTitle = "Mutasi Uji Masuk";
                break;
            case "MUK":
                persyaratanTitle = "Mutasi Uji Keluar";
                break;
            case "NUM":
                persyaratanTitle = "Numpang Uji Masuk";
                break;
            case "NUK":
                persyaratanTitle = "Numpang Uji Keluar";
                break;
            case "UBK":
                persyaratanTitle = "Ubah Bentuk";
                break;
            case "UBS":
                persyaratanTitle = "Ubah Sifat";
                break;
        }
        getSupportActionBar().setTitle("Persyaratan [" + persyaratanTitle + "]");
//        toolbar.setLogo(R.drawable.dishub_blue);
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.mipmap.back_row_white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                finish();
            }
        });

        getPersyaratan(idKiriman);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void getPersyaratan(String idKiriman) {
        try {
            pDialog.setMessage("Please wait...");
            showDialog();

            APIService service = ApiClient.getClient().create(APIService.class);
            Call<List<Persyaratan>> call = service.getPersyaratan(idKiriman);
            call.enqueue(new Callback<List<Persyaratan>>() {
                @Override
                public void onResponse(Call<List<Persyaratan>> call, Response<List<Persyaratan>> response) {
                    hideDialog();

                    List<Persyaratan> rowListPersyaratan = response.body();
                    layoutManager = new LinearLayoutManager(PersyaratanDetailActivity.this);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

                    recyclerView.setLayoutManager(layoutManager);
                    RecyclerViewPersyaratanAdapter recyclerViewAdapter = new RecyclerViewPersyaratanAdapter(rowListPersyaratan);
                    recyclerView.setAdapter(recyclerViewAdapter);
                }

                @Override
                public void onFailure(Call<List<Persyaratan>> call, Throwable t) {
                    hideDialog();
                    Log.d("onFailure", t.toString());
                }
            });
        } catch (Exception e) {
            hideDialog();
        }
    }

//    private void getPersyaratan(String idKiriman){
//        urlProses = "DetailPersyaratan/?kategori="+idKiriman;
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(urlProses)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIService service = retrofit.create(APIService.class);
//        Call<List<Persyaratan>> call = service.getPersyaratan();
//
//        call.enqueue(new Callback<List<Persyaratan>>(){
//            @Override
//            public void onResponse(Call<List<Persyaratan>> call, Response<List<Persyaratan>> response) {
//                List<Persyaratan> persyaratan = response.body();
//                String details = "";
//                for(int i=0; i<persyaratan.size(); i++){
//                    String deskripsi = persyaratan.get(i).getKeterangan();
//                    details += deskripsi;
//                    details += "\n \n";
//                }
//                TextView txt_persyaratan = (TextView) findViewById(R.id.jsonDataPersyaratan);
//                txt_persyaratan.setText(details);
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<List<Persyaratan>> call, Throwable t) {
//
//            }
//        });
//    }

}
