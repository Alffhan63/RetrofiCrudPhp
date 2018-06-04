package com.example.alfhanrf.cobaretrofit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alfhanrf.cobaretrofit.R;
import com.example.alfhanrf.cobaretrofit.model.Mahasiswa;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alfhanrf on 6/2/2018.
 */

public class RecycleViewAdapater extends RecyclerView.Adapter<RecycleViewAdapater.ViewHolder> {

    private Context context;
    private List<Mahasiswa> mahasiswa;

    public RecycleViewAdapater(Context context, List<Mahasiswa> mahasiswa){
        this.context = context;
        this.mahasiswa = mahasiswa;
    }

    @Override
    public RecycleViewAdapater.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder( RecycleViewAdapater.ViewHolder holder, int position) {

        Mahasiswa mhs = mahasiswa.get(position);
        holder.textnim.setText(mhs.getNim());
        holder.textnama.setText(mhs.getNama());
        holder.textjuruasan.setText(mhs.getJurusan());
        holder.textjk.setText(mhs.getJk());


    }

    @Override
    public int getItemCount() {
        return mahasiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textNIM)TextView textnim;
        @BindView(R.id.textNama)TextView textnama;
        @BindView(R.id.textJurusan)TextView textjuruasan;
        @BindView(R.id.textJenisKel)TextView textjk;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
