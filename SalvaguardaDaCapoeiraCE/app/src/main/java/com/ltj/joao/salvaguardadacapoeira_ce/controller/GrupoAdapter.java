package com.ltj.joao.salvaguardadacapoeira_ce.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ltj.joao.salvaguardadacapoeira_ce.R;

import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Grupo;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class GrupoAdapter extends RecyclerView.Adapter<GrupoAdapter.ViewHolderGrupo>{

    private List<Grupo> grupos;

    private Context context;
    private ImageLoader imageLoader;

    public GrupoAdapter(Context context,List<Grupo> grupos){
        this.grupos = grupos;
        this.context = context;
        this.imageLoader = imageLoader;
    }



    @Override
    public GrupoAdapter.ViewHolderGrupo onCreateViewHolder(ViewGroup group, int viewType) {
       LayoutInflater layoutInflater = LayoutInflater.from(group.getContext());
      View view = layoutInflater.inflate(R.layout.layout_visualizar_grupo,group,false);
      ViewHolderGrupo holderGrupo = new ViewHolderGrupo(view);

        return holderGrupo;
    }

    @Override
    public void onBindViewHolder(GrupoAdapter.ViewHolderGrupo holder, int position) {
        if(grupos != null & grupos.size() > 0){

            try{


                holder.nomeGrupoLayout.setText(grupos.get(position).getNomeGrupo());
                holder.nomeResponsavelLayout.setText(grupos.get(position).getNomeResponsavel());
                holder.mestreGrupoLayout.setText(grupos.get(position).getMestreGrupo());
                holder.estadoGrupoLayout.setText(grupos.get(position).getEstado());
                holder.cidadeGrupoLayout.setText(grupos.get(position).getCidade());
                holder.bairroGrupoLayout.setText(grupos.get(position).getBairro());
                holder.ruaGrupoLayout.setText(grupos.get(position).getRua());
                holder.complementoGrupoLayout.setText(grupos.get(position).getComplemento());

                if(grupos.get(position).getUrlImagem().equals("NULL")){
                    holder.fotoGrupoLayout.setImageResource(R.drawable.ic_inserir_foto_grupo);
                }else{
                    Picasso.get().load(grupos.get(position).getUrlImagem()).into(holder.fotoGrupoLayout);
                }


            }catch (Exception e){

            }

        }
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

    public class ViewHolderGrupo extends RecyclerView.ViewHolder{
        CircleImageView fotoGrupoLayout;
        TextView nomeGrupoLayout,nomeResponsavelLayout,mestreGrupoLayout,estadoGrupoLayout,cidadeGrupoLayout,bairroGrupoLayout,ruaGrupoLayout,complementoGrupoLayout;

        public ViewHolderGrupo(View view) {
            super(view);
            fotoGrupoLayout = (CircleImageView)view.findViewById(R.id.fotoGrupoLayout);
            nomeGrupoLayout = (TextView) view.findViewById(R.id.nomeGrupoLayout);
            nomeResponsavelLayout = (TextView) view.findViewById(R.id.nomeResponsavelLayout);
            mestreGrupoLayout = (TextView) view.findViewById(R.id.mestreGrupoLayout);
            estadoGrupoLayout = (TextView) view.findViewById(R.id.estadoGrupoLayout);
            cidadeGrupoLayout = (TextView) view.findViewById(R.id.cidadeGrupoLayout);
            bairroGrupoLayout = (TextView) view.findViewById(R.id.bairroGrupoLayout);
            ruaGrupoLayout = (TextView) view.findViewById(R.id.ruaGrupoLayout);
            complementoGrupoLayout = (TextView) view.findViewById(R.id.complementoGrupoLayout);
        }
    }

    public void setFiltro(List<Grupo> listaFiltrada){
        this.grupos = new ArrayList<>();
        this.grupos.addAll(listaFiltrada);
        notifyDataSetChanged();
    }
}
