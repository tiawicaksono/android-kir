package kirsurabaya.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import kirsurabaya.myapplication.R;


/**
 * Created by TIA.WICAKSONO on 5/5/2017.
 */

public class RecyclerViewHolderPersyaratan extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView listSyarat;

    public RecyclerViewHolderPersyaratan(View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);
        listSyarat = (TextView) itemView.findViewById(R.id.jsonDataPersyaratan);
    }

    @Override
    public void onClick(View v) {

    }
}
