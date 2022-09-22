package kirsurabaya.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kirsurabaya.myapplication.R;


/**
 * Created by TIA.WICAKSONO on 5/5/2017.
 */

public class RecyclerViewHolderKendaraanUji extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView jsonDataNoUji, jsonDataNoKendaraan,jsonDataPemilik, jsonDataHasil;

    public RecyclerViewHolderKendaraanUji(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        jsonDataNoUji = (TextView) itemView.findViewById(R.id.txtDataNoUji);
        jsonDataNoKendaraan = (TextView) itemView.findViewById(R.id.txtDataNoKendaraan);
        jsonDataPemilik = (TextView) itemView.findViewById(R.id.txtDataPemilik);
        jsonDataHasil = (TextView) itemView.findViewById(R.id.txtDataHasil);
    }

    @Override
    public void onClick(View v) {

    }
}
