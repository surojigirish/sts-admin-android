package com.example.sts_admin.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sts_admin.R;
import com.example.sts_admin.model.Halts;

import java.util.List;

public class HaltAdapter extends RecyclerView.Adapter<HaltAdapter.ViewHolder> {

    Context context;
    List<Halts> halts;

    // on Click listener
    public OnHaltItemClickListener onSourceClick;

    public HaltAdapter(Context context, List<Halts> halts, OnHaltItemClickListener onSourceClick) {
        this.context = context;
        this.halts = halts;
        this.onSourceClick = onSourceClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.halts_list_item, parent, false);
        return new ViewHolder(view, onSourceClick, halts);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Halts halt = halts.get(position);
        holder.tvHalt.setText(halt.getHaltName());

        // handle onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                if (position != RecyclerView.NO_POSITION && onSourceClick != null) {
                    Halts halt = halts.get(position);
                    // store halt info
                    int id = halt.getId();
                    String name = halt.getHaltName();
                    String latitude = halt.getLatitude();
                    String longitude = halt.getLongitude();
                    // call click listener
                    onSourceClick.onHaltItemClick(id, name,latitude, longitude);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return halts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHalt;

        public ViewHolder(@NonNull View itemView, final OnHaltItemClickListener listener, final List<Halts> sources) {
            super(itemView);
            // init views
            tvHalt = itemView.findViewById(R.id.textView_halt);
        }
    }

    public interface OnHaltItemClickListener {
        void onHaltItemClick(Integer id, String name, String latitude, String longitude);
    }
}
