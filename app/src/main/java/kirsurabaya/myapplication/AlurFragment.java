package kirsurabaya.myapplication;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import kirsurabaya.myapplication.R;
import kirsurabaya.myapplication.adapter.PersyaratanAdapter;

/**
 * Created by TIA.WICAKSONO on 4/26/2017.
 */

public class AlurFragment extends Fragment {

    BottomBar mBottomBar;

    public AlurFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.alur_fragment, container, false);
//
//        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
//        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            Fragment fragment = null;
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                if (tabId == R.id.BottomBarWiyung) {
//                    // The tab with id R.id.tab_favorites was selected,
//                    // change your content accordingly.
//                    fragment = new AlurWiyungFragment();
//                } else if (tabId == R.id.BottomBarTandes) {
//                    // The tab with id R.id.tab_chats was selected,
//                    // change your content accordingly.
//                    fragment = new AlurTandesFragment();
//                }
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.contentContainerAlur, fragment)
//                        .commit();
//            }
//        });

        View view = inflater.inflate(R.layout.alur_wiyung, container, false);
        SubsamplingScaleImageView imageView = (SubsamplingScaleImageView) view.findViewById(R.id.imageView);
        imageView.setImage(ImageSource.resource(R.drawable.alur_wiyung));
        return view;
    }
}
