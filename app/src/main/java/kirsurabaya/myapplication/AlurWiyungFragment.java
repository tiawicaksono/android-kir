package kirsurabaya.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import kirsurabaya.myapplication.R;

/**
 * Created by TIA.WICAKSONO on 4/26/2017.
 */

public class AlurWiyungFragment extends Fragment {
    public AlurWiyungFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.alur_wiyung, container, false);
        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView) view.findViewById(R.id.imageView);
        imageView.setImage(ImageSource.resource(R.drawable.alur_wiyung));
        return view;
    }
}
