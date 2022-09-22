package kirsurabaya.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import kirsurabaya.myapplication.PersyaratanDetailActivity;
import kirsurabaya.myapplication.R;

/**
 * Created by TIA.WICAKSONO on 4/28/2017.
 */

public class PersyaratanAdapter extends RecyclerView.Adapter<PersyaratanAdapter.myViewHolder> {

    private final LayoutInflater inflater;
    private Context mContext;
    String[] title;
    String[] desc;

    int[] images = {
            R.drawable.kb,
            R.drawable.ub,
            R.drawable.mm,
            R.drawable.mk,
            R.drawable.nm,
            R.drawable.nk,
            R.drawable.um,
            R.drawable.us
    };

    public interface ItemClickListener {
        void onClick(View view, int position, boolean isLongClick);
    }

    public PersyaratanAdapter(Context context, String[] objectTitle, String[] objectDesc) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
        this.title = objectTitle;
        this.desc = objectDesc;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.persyaratan_list, parent, false);
        myViewHolder holder = new myViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder._title.setText(title[position]);
        holder._desc.setText(desc[position]);
        holder._imgview.setImageResource(images[position]);

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent i;
                switch (position){
                    case 0:
                        i = new Intent(mContext, PersyaratanDetailActivity.class);
                        i.putExtra("kategori", "PUP");
                        mContext.startActivity(i);
                        break;
                    case 1:
                        i = new Intent(mContext, PersyaratanDetailActivity.class);
                        i.putExtra("kategori", "PUB");
                        mContext.startActivity(i);
                        break;
                    case 2:
                        i = new Intent(mContext, PersyaratanDetailActivity.class);
                        i.putExtra("kategori", "MUM");
                        mContext.startActivity(i);
                        break;
                    case 3:
                        i = new Intent(mContext, PersyaratanDetailActivity.class);
                        i.putExtra("kategori", "MUK");
                        mContext.startActivity(i);
                        break;
                    case 4:
                        i = new Intent(mContext, PersyaratanDetailActivity.class);
                        i.putExtra("kategori", "NUM");
                        mContext.startActivity(i);
                        break;
                    case 5:
                        i = new Intent(mContext, PersyaratanDetailActivity.class);
                        i.putExtra("kategori", "NUK");
                        mContext.startActivity(i);
                        break;
                    case 6:
                        i = new Intent(mContext, PersyaratanDetailActivity.class);
                        i.putExtra("kategori", "UBK");
                        mContext.startActivity(i);
                        break;
                    case 7:
                        i = new Intent(mContext, PersyaratanDetailActivity.class);
                        i.putExtra("kategori", "UBS");
                        mContext.startActivity(i);
                        break;
//                    default:
//                        break;
                }
//                Toast.makeText(mContext, "#" + position + " - short - " + title[position], Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView _imgview;
        TextView _title;
        TextView _desc;
        private ItemClickListener clickListener;

        public myViewHolder(View itemView) {
            super(itemView);
            _imgview = (ImageView) itemView.findViewById(R.id.img);
            _title = (TextView) itemView.findViewById(R.id.txttitle);
            _desc = (TextView) itemView.findViewById(R.id.txtdesc);
            itemView.setOnClickListener(this);
//            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getAdapterPosition(), false);
            return true;
        }
    }
}
