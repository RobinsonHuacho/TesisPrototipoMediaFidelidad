package com.epn.robinsonhuacho.tesis_prototipomediafidelidad_v10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Robinson Huacho on 14/08/17.
 */

public class DatabaseHandlerProductosComprados extends SQLiteOpenHelper{

    //Nombre de la base de datos
    private static final String DATABASE_NAME = "productosCompradosManager";

    //Versión de la base de datos
    private static final int DATABASE_VERSION = 1;

    //Nombre de la tabla de casos
    private static final String TABLE_PRODUCTOS = "productosComprados";

    //Nombres de las columnas de la tabla
    private static final String KEY_ID_DETALLE = "ID_DETALLE_VENTA";
    private static final String KEY_ID_COMPRA = "ID_COMPRA";
    private static final String KEY_ID_PRODUCTO = "ID_PRODUCTO";
    private static final String KEY_PRECIO = "PRECIO_VENTA_DETALLE";
    private static final String KEY_CANTIDAD = "CANTIDAD_DETALLE";
    private static final String KEY_TOTAL = "TOTAL_DETALLE_VENTA";



    //Constructor de la base de datos
    public DatabaseHandlerProductosComprados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTOS + "("
                + KEY_ID_DETALLE + " INTEGER PRIMARY KEY,"
                + KEY_ID_COMPRA+ " TEXT ,"
                + KEY_ID_PRODUCTO + " TEXT,"
                + KEY_PRECIO + " TEXT,"
                + KEY_CANTIDAD + " TEXT,"
                + KEY_TOTAL + " TEXT" +")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        onCreate(db);
    }


    public void addProductos(ProductosComprados producto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID_DETALLE, producto.get_idDetalle());
        values.put(KEY_ID_COMPRA, producto.get_idCompra());
        values.put(KEY_ID_PRODUCTO, producto.get_idProducto());
        values.put(KEY_PRECIO, producto.get_precioVenta());
        values.put(KEY_CANTIDAD, producto.get_cantidadDetalle());
        values.put(KEY_TOTAL, producto.get_totalDetalle());

        db.insert(TABLE_PRODUCTOS, null, values);
        db.close();
    }

    public ProductosComprados getProducto(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.query(TABLE_PRODUCTOS, new
                    String[]{KEY_ID_DETALLE, KEY_ID_COMPRA, KEY_ID_PRODUCTO, KEY_PRECIO, KEY_CANTIDAD, KEY_TOTAL}, KEY_ID_DETALLE + "=?", new
                    String[]{String.valueOf(id)}, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            ProductosComprados producto = new ProductosComprados(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

            return producto;
        } catch (Exception error) {

            ProductosComprados productoR = new ProductosComprados();
            return productoR;
        }

    }

    /*public Producto getProductoNombre(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.query(TABLE_PRODUCTOS, new
                    String[]{KEY_ID_DETALLE, KEY_ID_COMPRA, KEY_ID_PRODUCTO, KEY_PRECIO, KEY_CANTIDAD, KEY_TOTAL}, KEY_NOMBRE + "=?", new
                    String[]{String.valueOf(nombre)}, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            Producto producto = new Producto(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));

            return producto;
        } catch (Exception error) {

            Producto productoR = new Producto();
            return productoR;
        }
    }*/



    public ArrayList getAllProductos(int elemento) {
        ArrayList<String> productosList = new ArrayList<>();
        String sql_select = "SELECT * FROM " + TABLE_PRODUCTOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql_select, null);
        if (cursor.moveToFirst()) {
            do {
                productosList.add(cursor.getString(elemento));
            } while (cursor.moveToNext());
        }
        return productosList;
    }



    /*public ArrayList getProductos(String idCategoria) {
        ArrayList<String> productosList = new ArrayList<>();
        String sql_select = "SELECT * FROM " + TABLE_PRODUCTOS+" WHERE "+KEY_ID_CATEGORIA+" = "+idCategoria;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql_select, null);
        if (cursor.moveToFirst()) {
            do {
                productosList.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        return productosList;
    }*/

   /* public int updateProducto(Producto producto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, producto.get_id());
        values.put(KEY_ID_CATEGORIA, producto.get_idCategoria());
        values.put(KEY_NOMBRE, producto.get_nombre());
        values.put(KEY_DESCRIPCION, producto.get_descripcion());
        values.put(KEY_PRECIO_UNITARIO, producto.get_precioUnitario());
        values.put(KEY_CANTIDAD, producto.get_cantidad());
        values.put(KEY_IMAGEN, producto.get_imagen());

        return db.update(TABLE_PRODUCTOS, values, KEY_ID + "=?",
                new String[]{String.valueOf(producto.get_id())});
    }


    }*/

    public void deleteProducto(ProductosComprados producto) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCTOS, KEY_ID_DETALLE + " =?",
                new String[]{String.valueOf(producto.get_idDetalle())});
        db.close();
    }

    public void deleteProductos(){
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTOS +" WHERE 1");
    }


}
