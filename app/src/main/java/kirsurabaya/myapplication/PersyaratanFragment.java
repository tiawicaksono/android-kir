package kirsurabaya.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.adapter.PersyaratanAdapter;


/**
 * Created by TIA.WICAKSONO on 4/26/2017.
 */

public class PersyaratanFragment extends Fragment {
    String[] title;
    String[] desc;

    public PersyaratanFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.persyaratan_fragment, container, false);

        title = getResources().getStringArray(R.array.judul_persyaratan);
        desc = getResources().getStringArray(R.array.sub_judul_persyaratan);
        PersyaratanAdapter adpater = new PersyaratanAdapter(getActivity(), title, desc);
        RecyclerView rview = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        rview.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext()));
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setAdapter(adpater);
        rview.setItemAnimator(new DefaultItemAnimator());
        rview.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }


}
