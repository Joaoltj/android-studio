package com.ltj.joao.amorsecreto.controller;
import com.ltj.joao.amorsecreto.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltj.joao.amorsecreto.model.Imagem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterImagem extends RecyclerView.Adapter<AdapterImagem.ViewHolderImagem>{
    private List<Imagem> galeria;
    private Context context;
    private OnItemClickListener listener;
    public AdapterImagem(Context context, List<Imagem> g){
        this.galeria = g;

        Log.i("MrScript","contur");
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolderImagem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.design_lista_imagem,parent,false);
        ViewHolderImagem holder = new ViewHolderImagem(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolderImagem holder, int position) {

        Imagem imagem = galeria.get(position);
        holder.txtDescricao.setText(imagem.getDescricao());
        Picasso.get().load(imagem.getUrl()).fit().centerInside().into(holder.ivImg);




    }

    @Override
    public int getItemCount() {
        return galeria.size();
    }


    public class ViewHolderImagem extends RecyclerView.ViewHolder implements View.OnClickListener,DialogInterface.OnClickListener{
        ImageView ivImg;
        TextView txtDescricao;

        public ViewHolderImagem(View view) {
            super(view);
            ivImg = (ImageView)view.findViewById(R.id.desing_img_imagem);
            txtDescricao = (TextView) view.findViewById(R.id.design_descricao_imagem);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(listener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    listener.itemClick(position);
                    createDialog().show();
                }
            }
        }


        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i){
                case 0:
                    listener.itemClickBaixar(getAdapterPosition());
                    break;
                case 1:
                    listener.itemClickRemover(getAdapterPosition());
                    break;
            }
        }
        public AlertDialog createDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Selecioner as opções");
            CharSequence items[] = {"Baixar","Remover"};
            builder.setItems(items,this);
            return builder.create();


    }

    }
    public interface OnItemClickListener{
        void itemClick(int position);
        void itemClickBaixar(int position);
        void itemClickRemover(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


}
