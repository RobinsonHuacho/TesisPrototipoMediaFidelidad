package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by Robinson Huacho on 10/04/2018.
 */

public class Adaptador extends BaseAdapter{

    private Context contexto;
    private ArrayList<Elementos> listaItems;

    public Adaptador(Context contexto, ArrayList<Elementos> listaItems) {
        this.contexto = contexto;
        this.listaItems = listaItems;
    }

    @Override
    public int getCount() {
        return listaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listaItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Elementos item = getItem(position);

        convertView = LayoutInflater.from(contexto).inflate(R.layout.items_lista,null);
        ImageView imgFoto = (ImageView)convertView.findViewById(R.id.imgFoto);
        TextView textViewNombres = (TextView) convertView.findViewById(R.id.textViewNombres);
        TextView textViewTotalCompra = (TextView) convertView.findViewById(R.id.textViewTotalCompra);
        TextView textViewSaldo = (TextView) convertView.findViewById(R.id.textViewSaldo);

        imgFoto.setImageResource(item.getImgFoto());
        textViewNombres.setText(item.getNombres());
        textViewTotalCompra.setText(item.getTotalCompra());
        textViewSaldo.setText(item.getSaldo());

        return convertView;
    }
}
