package kirsurabaya.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import kirsurabaya.myapplication.R;

import kirsurabaya.myapplication.model.ListRiwayatKendaraan;
import kirsurabaya.myapplication.viewholder.RecyclerViewHolderRiwayatKendaraan;

import java.util.List;

/**
 * Created by TIA.WICAKSONO on 5/24/2018.
 */

public class RecyclerViewRiwayatKendaraanAdapter extends RecyclerView.Adapter<RecyclerViewHolderRiwayatKendaraan> {
    private List<ListRiwayatKendaraan> itemList;
    public RecyclerViewRiwayatKendaraanAdapter(List<ListRiwayatKendaraan> itemList){
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolderRiwayatKendaraan onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.riwayat_kendaraan_list, null);
        RecyclerViewHolderRiwayatKendaraan recyclerViewHolder = new RecyclerViewHolderRiwayatKendaraan(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderRiwayatKendaraan holder, int position) {
        ListRiwayatKendaraan riwayatKendaraanModel = itemList.get(position);
        holder.jsonDataJnsUji.setText((CharSequence) riwayatKendaraanModel.getNmUji());
        holder.jsonDataCatatan.setText((CharSequence) riwayatKendaraanModel.getCatatan());
        holder.jsonDataTglUji.setText(riwayatKendaraanModel.getTglUji());
        holder.jsonDataMatiUji.setText(riwayatKendaraanModel.getTglmati());
        holder.jsonDataNamaPenguji.setText((CharSequence) riwayatKendaraanModel.getNmPenguji());
        holder.jsonDataNrp.setText((CharSequence) riwayatKendaraanModel.getNrp());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
