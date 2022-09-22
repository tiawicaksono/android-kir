package kirsurabaya.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kirsurabaya.myapplication.R;


/**
 * Created by TIA.WICAKSONO on 5/24/2018.
 */

public class RecyclerViewHolderRiwayatKendaraan extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView jsonDataJnsUji, jsonDataCatatan, jsonDataTglUji, jsonDataMatiUji, jsonDataNamaPenguji, jsonDataNrp;

    public RecyclerViewHolderRiwayatKendaraan(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        jsonDataJnsUji = (TextView) itemView.findViewById(R.id.txtJnsUji);
        jsonDataCatatan = (TextView) itemView.findViewById(R.id.txtMutasiKe);
        jsonDataTglUji = (TextView) itemView.findViewById(R.id.txtTglUji);
        jsonDataMatiUji = (TextView) itemView.findViewById(R.id.txtMatiUji);
        jsonDataNamaPenguji = (TextView) itemView.findViewById(R.id.txtNamaPenguji);
        jsonDataNrp = (TextView) itemView.findViewById(R.id.txtNrp);
    }

    @Override
    public void onClick(View v) {

    }
}
