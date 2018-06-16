package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Adaptador de leads
 */
public class ElementosProductosAdaptador extends ArrayAdapter<ElementoProducto> {
    public ElementosProductosAdaptador(Context context, List<ElementoProducto> objects) {
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
                    R.layout.item_producto,
                    parent,
                    false);

            holder = new ViewHolder();
            holder.imagen = (ImageView) convertView.findViewById(R.id.ImageView_Foto);
            holder.nombre = (TextView) convertView.findViewById(R.id.TextView_Nombre);
            holder.descripcion = (TextView) convertView.findViewById(R.id.TextView_Descripcion);
            //holder.cantidad = (EditText) convertView.findViewById(R.id.EditText_Cantidad);
            //holder.cantidad.setFocusable(false);
            //holder.total=(TextView) convertView.findViewById(R.id.TextView_Total);
            holder.precio=(TextView) convertView.findViewById(R.id.TextView_Precio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        // ElementoCategoriaProducto actual
        ElementoProducto elemento = getItem(position);

        // Setup

        holder.nombre.setText(elemento.getNombres());
        holder.descripcion.setText(String.valueOf(elemento.getDescripcion()));
        //holder.cantidad.setText(String.valueOf(elemento.getCantidad()));
        holder.precio.setText(String.valueOf(elemento.getPrecio()));
        //holder.total.setText(String.valueOf(elemento.getTotal()));
        Glide.with(getContext()).load(elemento.getImagen()).into(holder.imagen);

        return convertView;
    }

    static class ViewHolder {
        ImageView imagen;
        TextView nombre;
        TextView descripcion;
        EditText cantidad;
        TextView precio;
        TextView total;
    }
}
