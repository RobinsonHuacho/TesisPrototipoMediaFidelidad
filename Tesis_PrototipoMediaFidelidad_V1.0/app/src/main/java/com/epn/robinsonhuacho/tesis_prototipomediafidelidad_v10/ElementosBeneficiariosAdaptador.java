package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Adaptador de leads
 */
public class ElementosBeneficiariosAdaptador extends ArrayAdapter<ElementoBeneficiarioDonacion> {
    public ElementosBeneficiariosAdaptador(Context context, List<ElementoBeneficiarioDonacion> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;

        // ¿Ya se infló este view?
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            convertView = inflater.inflate(
                    R.layout.item_lista_beneficiario,
                    parent,
                    false);

            holder = new ViewHolder();
            holder.imagen = (ImageView) convertView.findViewById(R.id.ImageView_Foto);
            holder.nombres = (TextView) convertView.findViewById(R.id.TextView_Nombres);
            holder.totalCompra = (TextView) convertView.findViewById(R.id.TextView_TotalCompra);
            holder.saldo = (TextView) convertView.findViewById(R.id.TextView_Precio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // ElementoBeneficiarioDonacion actual
        ElementoBeneficiarioDonacion elementoBeneficiarioDonacion = getItem(position);

        // Setup
        holder.nombres.setText(elementoBeneficiarioDonacion.getNombres());
        holder.totalCompra.setText(String.valueOf(elementoBeneficiarioDonacion.getTotalCompra()));
        holder.saldo.setText(String.valueOf(elementoBeneficiarioDonacion.getSaldo()));
        Glide.with(getContext()).load(elementoBeneficiarioDonacion.getImagen()).into(holder.imagen);

        return convertView;
    }

    static class ViewHolder {
        ImageView imagen;
        TextView nombres;
        TextView totalCompra;
        TextView saldo;
    }
}
