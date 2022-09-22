package kirsurabaya.myapplication;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import kirsurabaya.myapplication.R;

/**
 * Created by TIA.WICAKSONO on 4/26/2017.
 */

public class BiayaFragment extends Fragment {

    BottomBar mBottomBar;

    public BiayaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.biaya_fragment, container, false);
//
//        mBottomBar = (BottomBar) view.findViewById(R.id.bottomBar);
//        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
//            Fragment fragment = null;
//            @Override
//            public void onTabSelected(@IdRes int tabId) {
//                if (tabId == R.id.BottomBarWiyung) {
//                    // The tab with id R.id.tab_favorites was selected,
//                    // change your content accordingly.
//                    fragment = new BiayaWiyungFragment();
//                } else if (tabId == R.id.BottomBarTandes) {
//                    // The tab with id R.id.tab_chats was selected,
//                    // change your content accordingly.
//                    fragment = new BiayaTandesFragment();
//                }
//                FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
//                transaction.replace(R.id.contentContainerBiaya, fragment);
//                transaction.commit();
//            }
//        });
        return inflater.inflate(R.layout.biaya_wiyung, container, false);
    }
}
