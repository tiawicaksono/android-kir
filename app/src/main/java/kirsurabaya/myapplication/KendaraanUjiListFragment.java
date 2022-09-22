package kirsurabaya.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.adapter.RecyclerViewKendaraanUjiAdapter;
import kirsurabaya.myapplication.model.KendaraanUji;
import kirsurabaya.myapplication.network.ApiClient;
import kirsurabaya.myapplication.service.APIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TIA.WICAKSONO on 7/5/2017.
 */

public class KendaraanUjiListFragment extends Fragment {

    private ProgressDialog pDialog;
    List<KendaraanUji> kendaraanuji;
    RecyclerViewKendaraanUjiAdapter adapter;
    SwipeRefreshLayout swipe;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    int index = 1;
    private FloatingSearchView mSearchView;

    public KendaraanUjiListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.kendaraan_uji_list, container, false);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setCancelable(false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        kendaraanuji = new ArrayList<>();
        adapter = new RecyclerViewKendaraanUjiAdapter(kendaraanuji);
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //event ketika widget dijalankan//
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }

            void refreshItem() {
                kendaraanuji.clear();
                load(0,"");
                index = 1;
                adapter.setMoreDataAvailable(true);
                onItemLoad();
            }

            void onItemLoad() {
                swipe.setRefreshing(false);
            }
        });

        adapter.setLoadMoreListener(new RecyclerViewKendaraanUjiAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        loadMore(index,"");
                        index += 1;
                    }
                });
                //Calling loadMore function in Runnable to fix the
                // java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling error
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new VerticalLineDecorator(2));
        recyclerView.setAdapter(adapter);

        APIService service = ApiClient.getClient().create(APIService.class);
        load(0,"");

        mSearchView = (FloatingSearchView) view.findViewById(R.id.noUjiKendaraanSearch);
        setupFloatingSearch();
        return view;
    }

    private void setupFloatingSearch() {
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                recyclerView.setAdapter(null);
                load(0, currentQuery);
            }
        });
    }

    private void load(int index, String noUji) {
        try {
            pDialog.setMessage("Please wait...");
            showDialog();

            APIService service = ApiClient.getClient().create(APIService.class);
            Call<List<KendaraanUji>> call = service.getKendaraanUji(index, noUji);
            call.enqueue(new Callback<List<KendaraanUji>>() {
                @Override
                public void onResponse(Call<List<KendaraanUji>> call, Response<List<KendaraanUji>> response) {
                    hideDialog();
                    if (response.isSuccessful()) {
                        kendaraanuji.addAll(response.body());
                        adapter.notifyDataChanged();
//                        List<KendaraanUji> rowListKendaraanUji = response.body();
//                        layoutManager = new LinearLayoutManager(getActivity());
//                        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
//                        recyclerView.setLayoutManager(layoutManager);
//                        RecyclerViewKendaraanUjiAdapter recyclerViewAdapter = new RecyclerViewKendaraanUjiAdapter(rowListKendaraanUji);
//                        recyclerView.setAdapter(recyclerViewAdapter);
                    } else {
                        Toast.makeText(getActivity(), "Error Load Data", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<List<KendaraanUji>> call, Throwable t) {
//                Log.e(TAG," Response Error "+t.getMessage());
                    hideDialog();
                    Toast.makeText(getActivity(), "Cek Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            hideDialog();
        }
    }

    private void loadMore(int index, String noUji) {

        //add loading progress view
//        kendaraanuji.add(new RecyclerViewKendaraanUjiAdapter("load"));
//        adapter.notifyItemInserted(kendaraanuji.size()-1);

        APIService service = ApiClient.getClient().create(APIService.class);
        Call<List<KendaraanUji>> call = service.getKendaraanUji(index, noUji);
        call.enqueue(new Callback<List<KendaraanUji>>() {
            @Override
            public void onResponse(Call<List<KendaraanUji>> call, Response<List<KendaraanUji>> response) {

                if (response.isSuccessful()) {
                    //remove loading view
//                    kendaraanuji.remove(kendaraanuji.size()-1);

                    List<KendaraanUji> rowListKendaraanUji = response.body();
                    if (rowListKendaraanUji.size() > 0) {
                        kendaraanuji.addAll(rowListKendaraanUji);
//                        layoutManager = new LinearLayoutManager(getActivity());
//                        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
//                        recyclerView.setLayoutManager(layoutManager);
//                        RecyclerViewKendaraanUjiAdapter recyclerViewAdapter = new RecyclerViewKendaraanUjiAdapter(rowListKendaraanUji);
//                        recyclerView.setAdapter(recyclerViewAdapter);
                    } else {//result size 0 means there is no more data available at server
                        adapter.setMoreDataAvailable(false);
                        Toast.makeText(getActivity(), "No More Data Available", Toast.LENGTH_LONG).show();
                    }
                    adapter.notifyDataChanged();
                } else {
                    Toast.makeText(getActivity(), "Cannot Load Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<KendaraanUji>> call, Throwable t) {
                Log.d("onFailure", t.toString());
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
}
