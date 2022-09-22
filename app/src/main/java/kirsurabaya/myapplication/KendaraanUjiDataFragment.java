package kirsurabaya.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.model.JumlahKendaraanUji;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TIA.WICAKSONO on 7/5/2017.
 */

public class KendaraanUjiDataFragment extends Fragment{
    private ProgressDialog pDialog;
    SwipeRefreshLayout swipe;

    public KendaraanUjiDataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.kendaraan_uji_data, container, false);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);

        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
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

    private void getData() {
        pDialog.setMessage("Please wait...");
        showDialog();
        APIService service = ApiClient.getClient().create(APIService.class);
//        APIService serviceTandes = ApiClient.getClientTandes().create(APIService.class);
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

//                    TextView txtMobilBarangWiyung = (TextView) getActivity().findViewById(R.id.txtMobilBarangWiyung);
//                    TextView txtMobilBusWiyung = (TextView) getActivity().findViewById(R.id.txtMobilBusWiyung);
//                    TextView txtMobilPenumpangWiyung = (TextView) getActivity().findViewById(R.id.txtMobilPenumpangWiyung);
//                    TextView txtTotalKendaraanWiyung = (TextView) getActivity().findViewById(R.id.txtTotalKendaraanWiyung);
//                    TextView txtTotalKendaraanDatangWiyung = (TextView) getActivity().findViewById(R.id.txtTotalKendaraanDatangWiyung);
//                    TextView txtTotalKendaraanTidakDatangWiyung = (TextView) getActivity().findViewById(R.id.txtTotalKendaraanTidakDatangWiyung);
//                    TextView txtTotalLulusWiyung = (TextView) getActivity().findViewById(R.id.txtTotalLulusWiyung);
//                    TextView txtTotalTidakLulusWiyung = (TextView) getActivity().findViewById(R.id.txtTotalTidakLulusWiyung);

                    TextView txtMobilBarangTandes = (TextView) getActivity().findViewById(R.id.txtMobilBarangTandes);
                    TextView txtMobilBusTandes = (TextView) getActivity().findViewById(R.id.txtMobilBusTandes);
                    TextView txtMobilPenumpangTandes = (TextView) getActivity().findViewById(R.id.txtMobilPenumpangTandes);
                    TextView txtMobilKhususTandes = (TextView) getActivity().findViewById(R.id.txtMobilKhususTandes);
                    TextView txtMobilGandenganTandes = (TextView) getActivity().findViewById(R.id.txtMobilGandenganTandes);
                    TextView txtMobilTempelanTandes = (TextView) getActivity().findViewById(R.id.txtMobilTempelanTandes);
                    TextView txtTotalKendaraanTandes = (TextView) getActivity().findViewById(R.id.txtTotalKendaraanTandes);
                    TextView txtTotalKendaraanDatangTandes = (TextView) getActivity().findViewById(R.id.txtTotalKendaraanDatangTandes);
                    TextView txtTotalKendaraanTidakDatangTandes = (TextView) getActivity().findViewById(R.id.txtTotalKendaraanTidakDatangTandes);
                    TextView txtTotalLulusTandes = (TextView) getActivity().findViewById(R.id.txtTotalLulusTandes);
                    TextView txtTotalTidakLulusTandes = (TextView) getActivity().findViewById(R.id.txtTotalTidakLulusTandes);

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
