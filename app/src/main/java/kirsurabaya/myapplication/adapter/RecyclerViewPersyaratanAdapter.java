package kirsurabaya.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kirsurabaya.myapplication.R;
import kirsurabaya.myapplication.model.Persyaratan;
import kirsurabaya.myapplication.viewholder.RecyclerViewHolderPersyaratan;

import java.util.List;

/**
 * Created by TIA.WICAKSONO on 5/5/2017.
 */

public class RecyclerViewPersyaratanAdapter extends RecyclerView.Adapter<RecyclerViewHolderPersyaratan> {

    private List<Persyaratan> itemList;
    public RecyclerViewPersyaratanAdapter(List<Persyaratan> itemList){
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolderPersyaratan onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persyaratan_list_detail, null);
        RecyclerViewHolderPersyaratan recyclerViewHolder = new RecyclerViewHolderPersyaratan(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderPersyaratan holder, int position) {
        Persyaratan persyaratan = itemList.get(position);
        holder.listSyarat.setText(persyaratan.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

//    @Override
//    public int getItemViewType(int position) {
//        return 1;
//    }
}
