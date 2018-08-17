package com.ltj.joao.salvaguardadacapoeira_ce.controller;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class CapoeiristaAdapter extends RecyclerView.Adapter<CapoeiristaAdapter.ViewHolderCapoeirista>{

    private List<Capoeirista> capoeiristas;
    private Context context;


    public CapoeiristaAdapter(Context context, List<Capoeirista> capoeiristas){
        this.context = context;
        this.capoeiristas = capoeiristas;



    }

    @Override
    public CapoeiristaAdapter.ViewHolderCapoeirista onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_visualizar_capoeirista,parent,false);
        ViewHolderCapoeirista holderCapoeirista = new ViewHolderCapoeirista(view);

        return holderCapoeirista;
    }

    @Override
    public void onBindViewHolder(CapoeiristaAdapter.ViewHolderCapoeirista holder, int position) {

        try{
            if((capoeiristas != null) && (capoeiristas.size() > 0)) {




                holder.nome.setText(capoeiristas.get(position).getNome());
                holder.apelido.setText(capoeiristas.get(position).getApelido());
                holder.graduacao.setText(capoeiristas.get(position).getGraduacao());


                if(capoeiristas.get(position).getUrlImagem().equals("NULL")){
                   holder.foto.setImageResource(R.drawable.ic_inserir_foto);
                }else{
                    Picasso.get().load(capoeiristas.get(position).getUrlImagem()).into(holder.foto);
                }

            }
        }catch (Exception e){
            Log.i("erri","3");
        }

    }

    @Override
    public int getItemCount() {
        return capoeiristas.size();
    }

    private static ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolderCapoeirista extends RecyclerView.ViewHolder implements View.OnClickListener{

        public CircleImageView foto;
        public TextView nome,apelido,email,graduacao;


        public ViewHolderCapoeirista(View view) {
            super(view);
            foto = (CircleImageView)view.findViewById(R.id.fotoCapoeiristaLayout);
            nome = (TextView)view.findViewById(R.id.nomeCapoeiristaLayout);
            apelido = (TextView)view.findViewById(R.id.apelidoCapoeiristaLayout);
            email = (TextView)view.findViewById(R.id.emailCapoeiristaLayout);
            graduacao = (TextView)view.findViewById(R.id.graduacaoCapoeiristaLayout);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(itemClickListener != null) {
                itemClickListener.onItemClick(getAdapterPosition());
            }
        }

    }
    public void setFiltro(List<Capoeirista> listaFiltrada){
        this.capoeiristas = new ArrayList<>();
        this.capoeiristas.addAll(listaFiltrada);
        notifyDataSetChanged();
    }
}
