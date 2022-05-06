package com.sara.proyectofinal.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sara.proyectofinal.R;
import com.sara.proyectofinal.modelo.entidad.Planta;

import java.util.List;

public class AdaptadorPlantas extends RecyclerView.Adapter<AdaptadorPlantas.ViewHolder> {

    private List<Planta> listaPlanta;

    public AdaptadorPlantas(List<Planta> listaPlanta) {
        this.listaPlanta = listaPlanta;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nombre;



        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreAnimal);
        }
    }

    //será quien devuelva el ViewHolder con el layout seteado que previamente definimos
    @Override
    //Aqui recogemos el parent
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recicler_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Planta planta = listaPlanta.get(position);
        String sId = String.valueOf(planta.getId());
        holder.nombre.setText(planta.getNombre());

    }

    @Override
    public int getItemCount() {
        return listaPlanta.size();
    }

}

