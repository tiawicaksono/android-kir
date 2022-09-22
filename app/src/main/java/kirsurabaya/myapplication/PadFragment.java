package kirsurabaya.myapplication;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import kirsurabaya.myapplication.R;

public class PadFragment extends Fragment {
    BottomBar mBottomBarPad;

    public PadFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.pad_fragment, container, false);

        mBottomBarPad = (BottomBar) view.findViewById(R.id.bottomBarPad);
        mBottomBarPad.setOnTabSelectListener(new OnTabSelectListener() {
            Fragment fragment = null;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.BottomBarWiyung) {
                    fragment = new PadWiyungFragment();
                } else if (tabId == R.id.BottomBarTandes) {
                    fragment = new PadTandesFragment();
                }
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentContainerPad, fragment)
                        .commit();
            }
        });
        return view;
    }
}
