package kirsurabaya.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kirsurabaya.myapplication.R;
import kirsurabaya.myapplication.model.KendaraanUji;
import kirsurabaya.myapplication.viewholder.RecyclerViewHolderKendaraanUji;

import java.util.List;

/**
 * Created by TIA.WICAKSONO on 7/5/2017.
 */

public class RecyclerViewKendaraanUjiAdapter extends RecyclerView.Adapter<RecyclerViewHolderKendaraanUji> {

    static Context context;
    List<KendaraanUji> kendaraanuji;
    boolean isLoading = false, isMoreDataAvailable = true;
    OnLoadMoreListener loadMoreListener;

    /*
    * isLoading - to set the remote loading and complete status to fix back to back load more call
    * isMoreDataAvailable - to set whether more data from server available or not.
    * It will prevent useless load more request even after all the server data loaded
    * */

    public RecyclerViewKendaraanUjiAdapter(List<KendaraanUji> kendaraanuji){
//        this.context = context;
        this.kendaraanuji = kendaraanuji;
    }

    @Override
    public RecyclerViewHolderKendaraanUji onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        return new RecyclerViewKendaraanUjiAdapter.KendaraanUjiHolder(inflater.inflate(R.layout.kendaraan_uji_lists,parent,false));

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kendaraan_uji_lists, null);
        RecyclerViewHolderKendaraanUji recyclerViewHolder = new RecyclerViewHolderKendaraanUji(view);
        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderKendaraanUji holder, int position) {
        if(position>=getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener!=null){
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
        KendaraanUji kendaraanUjiModel = kendaraanuji.get(position);
        holder.jsonDataNoUji.setText(kendaraanUjiModel.getNoUji());
        holder.jsonDataNoKendaraan.setText(kendaraanUjiModel.getNoKendaraan());
        holder.jsonDataPemilik.setText(kendaraanUjiModel.getNamaPemilik());
        String getHasil = kendaraanUjiModel.getStatusKelulusan();
        holder.jsonDataHasil.setText(getHasil);

        if (getHasil.equals("LULUS")) {
            holder.jsonDataHasil.setTextColor(Color.GREEN);
        }else{
            holder.jsonDataHasil.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return this.kendaraanuji.size();
    }

    public void setMoreDataAvailable(boolean moreDataAvailable) {
        isMoreDataAvailable = moreDataAvailable;
    }

    /* notifyDataSetChanged is final method so we can't override it
         call adapter.notifyDataChanged(); after update the list
         */
    public void notifyDataChanged(){
        notifyDataSetChanged();
        isLoading = false;
    }

    public interface OnLoadMoreListener{
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

}
