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
public class ElementosCategoriaProductoAdaptador extends ArrayAdapter<ElementoCategoriaProducto> {
    public ElementosCategoriaProductoAdaptador(Context context, List<ElementoCategoriaProducto> objects) {
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
                    R.layout.item_categoria_producto,
                    parent,
                    false);

            holder = new ViewHolder();
            holder.imagen = (ImageView) convertView.findViewById(R.id.ImageView_Categoria_Producto);
            holder.nombres = (TextView) convertView.findViewById(R.id.TextView_Nombre_Categoria);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // ElementoCategoriaProducto actual
        ElementoCategoriaProducto elementoCategoriaProducto = getItem(position);

        // Setup
        holder.nombres.setText(elementoCategoriaProducto.getNombreCategoriaProducto());
        Glide.with(getContext()).load(elementoCategoriaProducto.getImagenCategoriaProducto()).into(holder.imagen);

        return convertView;
    }

    static class ViewHolder {
        ImageView imagen;
        TextView nombres;
        
    }
}
