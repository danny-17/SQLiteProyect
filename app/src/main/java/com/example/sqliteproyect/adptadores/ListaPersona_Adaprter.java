package com.example.sqliteproyect.adptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqliteproyect.R;
import com.example.sqliteproyect.VerActivity;
import com.example.sqliteproyect.entidades.Persona;

import java.util.ArrayList;

public class ListaPersona_Adaprter extends RecyclerView.Adapter<ListaPersona_Adaprter.PersonaViewHolder> {

    ArrayList<Persona> listaPersonas;

    public ListaPersona_Adaprter(ArrayList<Persona> listaPersonas){
        this.listaPersonas= listaPersonas;
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_persona,null,false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        holder.viewNombre.setText(listaPersonas.get(position).getNombre());
        holder.viewApellido.setText(listaPersonas.get(position).getApellido());
        holder.viewTelefono.setText(listaPersonas.get(position).getTelefono());
        holder.viewEmail.setText(listaPersonas.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewApellido, viewTelefono, viewEmail;
        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre=itemView.findViewById(R.id.viewNombre);
            viewApellido=itemView.findViewById(R.id.viewApellido);
            viewTelefono=itemView.findViewById(R.id.viewTelefono);
            viewEmail=itemView.findViewById(R.id.viewEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaPersonas.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
