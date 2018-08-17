package com.ltj.joao.amorsecreto.controller;
import com.ltj.joao.amorsecreto.R;
import com.ltj.joao.amorsecreto.model.Festa;
import com.ltj.joao.amorsecreto.view.Agenda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import android.Manifest;
public class AdapterAgenda extends RecyclerView.Adapter<AdapterAgenda.ViewHolderAgenda>{

    private List<Festa> agenda;
    private Context context;
    private OnItemClickListener listener;

    public AdapterAgenda(Context context,List<Festa> agenda){
        this.agenda = agenda;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderAgenda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.design_lista_agenda,parent,false);
        ViewHolderAgenda holderAgenda = new ViewHolderAgenda(view);
        return holderAgenda;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAgenda holder, final int position) {
        Festa festa = agenda.get(position);
        holder.titulo.setText(festa.getTitulo());
        holder.local.setText(festa.getLocal());
        holder.data.setText(festa.getData());


    }

    @Override
    public int getItemCount() {
        return this.agenda.size();
    }




    public class ViewHolderAgenda extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener{
        public TextView titulo,local,data;

        public ViewHolderAgenda(View view) {
            super(view);
            titulo = (TextView)view.findViewById(R.id.design_titulo);
            local = (TextView)view.findViewById(R.id.design_local);
            data = (TextView)view.findViewById(R.id.design_data);
            view.setOnClickListener(this);
            view.setOnCreateContextMenuListener(this);


        }
        @Override
        public void onClick(View view) {
            if(listener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    listener.itemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("Selecione a opção");
            MenuItem menuItemUpdate = contextMenu.add(Menu.NONE,1,1,"Atualizar");
            MenuItem menuItemDelete = contextMenu.add(Menu.NONE,2,3,"Remover");



            menuItemDelete.setOnMenuItemClickListener(this);
            menuItemUpdate.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            if(listener != null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    int id = menuItem.getItemId();
                    switch (id){
                        case 1:
                            listener.itemUpdate(position);
                            break;
                        case 2:
                            listener.itemDelete(position);
                            break;

                    }

                    listener.itemDelete(position);
                }
            }

            return false;
        }
    }


    public interface OnItemClickListener{
        void itemClick(int position);
        void itemDelete(int position);
        void itemUpdate(int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
