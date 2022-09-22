package kirsurabaya.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kirsurabaya.myapplication.R;

/**
 * Created by TIA.WICAKSONO on 4/26/2017.
 */

public class BiayaWiyungFragment extends Fragment {
    public BiayaWiyungFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.biaya_wiyung, container, false);
    }
}
