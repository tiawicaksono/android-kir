package kirsurabaya.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import kirsurabaya.myapplication.R;

import java.util.HashMap;

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener, View.OnClickListener{

    private SliderLayout mDemoSlider;
    private Context mContext;
    private ImageView buttonImage;
    private Intent i;
    private LinearLayout linearLayout;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.home_fragment, container, false);
        buttonImage = (ImageView) view.findViewById(R.id.image_buttton_cek_kendaraan);
        buttonImage.setOnClickListener(this);
        linearLayout = (LinearLayout) view.findViewById(R.id.llCekKendaraan);
        linearLayout.setOnClickListener(this);

        buttonImage = (ImageView) view.findViewById(R.id.image_buttton_hasil);
        buttonImage.setOnClickListener(this);
        linearLayout = (LinearLayout) view.findViewById(R.id.llHasilUji);
        linearLayout.setOnClickListener(this);

//        buttonImage = (ImageView) view.findViewById(R.id.image_buttton_rekom);
//        buttonImage.setOnClickListener(this);
//        linearLayout = (LinearLayout) view.findViewById(R.id.llStatusRekom);
//        linearLayout.setOnClickListener(this);

        buttonImage = (ImageView) view.findViewById(R.id.image_buttton_kendaraan);
        buttonImage.setOnClickListener(this);
        linearLayout = (LinearLayout) view.findViewById(R.id.llKendaraanUji);
        linearLayout.setOnClickListener(this);

        buttonImage = (ImageView) view.findViewById(R.id.image_buttton_riwayat);
        buttonImage.setOnClickListener(this);
        linearLayout = (LinearLayout) view.findViewById(R.id.llRiwayatKendaraan);
        linearLayout.setOnClickListener(this);

        buttonImage = (ImageView) view.findViewById(R.id.image_buttton_cek_retribusi);
        buttonImage.setOnClickListener(this);
        linearLayout = (LinearLayout) view.findViewById(R.id.llCekRetribusi);
        linearLayout.setOnClickListener(this);

        buttonImage = (ImageView) view.findViewById(R.id.image_buttton_qrcode);
        buttonImage.setOnClickListener(this);
        linearLayout = (LinearLayout) view.findViewById(R.id.llQrCode);
        linearLayout.setOnClickListener(this);

        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("[1] UPTD PKB WIYUNG",R.drawable.slide2);
        file_maps.put("[2] UPTD PKB WIYUNG",R.drawable.slide1);
        file_maps.put("[3] UPTD PKB TANDES",R.drawable.slide4);
        file_maps.put("[4] UPTD PKB TANDES",R.drawable.slide3);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(12000);
        mDemoSlider.addOnPageChangeListener(this);
//        ListView l = (ListView) view.findViewById(R.id.transformers);
//        l.setAdapter(new TransformerAdapter(getActivity()));
//        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
//                Toast.makeText(getActivity(), ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_buttton_cek_kendaraan:
            case R.id.llCekKendaraan:
                i = new Intent(getActivity(), HomeCekKendaraanActivity.class);
                startActivity(i);
                break;
            case R.id.image_buttton_hasil:
            case R.id.llHasilUji:
                i = new Intent(getActivity(), HomeHasilActivity.class);
                startActivity(i);
                break;
//            case R.id.image_buttton_rekom:
//            case R.id.llStatusRekom:
//                i = new Intent(getActivity(), HomeRekomActivity.class);
//               startActivity(i);
//                break;
            case R.id.image_buttton_kendaraan:
            case R.id.llKendaraanUji:
                i = new Intent(getActivity(), HomeKendaraanUjiActivity.class);
                startActivity(i);
                break;
            case R.id.image_buttton_riwayat:
            case R.id.llRiwayatKendaraan:
                i = new Intent(getActivity(), HomeRiwayatKendaraanActivity.class);
                startActivity(i);
                break;
            case R.id.image_buttton_cek_retribusi:
            case R.id.llCekRetribusi:
                i = new Intent(getActivity(), HomeRetribusiActivity.class);
                startActivity(i);
                break;
            case R.id.image_buttton_qrcode:
            case R.id.llQrCode:
                i = new Intent(getActivity(), HomeQrCodeSmartCardActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
//        Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
//        Log.e("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
