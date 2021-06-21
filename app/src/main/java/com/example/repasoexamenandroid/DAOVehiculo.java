package com.example.repasoexamenandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAOVehiculo implements IDAOVehiculo{

    private static IDAOVehiculo dao = null;
    public static ArrayList<Vehiculo> listaVehiculos;
    private SQLiteHelper conn;
    private SQLiteDatabase db;

    DAOVehiculo(Context context){
        listaVehiculos = new ArrayList<Vehiculo>();
        conn = new SQLiteHelper(context);

        rellenarListado();
    }

    private int rellenarListado() {
        try{
            db = conn.getWritableDatabase();
            listaVehiculos.clear();
            if(db != null){
                Cursor c = db.rawQuery("SELECT * FROM vehiculo", null);
                if(c.moveToFirst()){
                    do{
                        listaVehiculos.add(new Vehiculo(c.getString(0),c.getString(1),c.getString(2)));
                    }
                    while (c.moveToNext());
                }
                c.close();
                db.close();
                return 1;
            }
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    @Override
    public int insertarVehiculo(Vehiculo vehiculo) {
        try{
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("INSERT INTO vehiculo (matricula, marca, modelo) VALUES ('"+vehiculo.getMatricula()+"','"+vehiculo.getMarca()+"','"+vehiculo.getModelo()+"')");
                rellenarListado();
                db.close();
                return 1;
            }
            else
                return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    @Override
    public int eliminarVehiculo(Vehiculo vehiculo) {
        try{
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("DELETE FROM vehiculo WHERE matricula='"+vehiculo.getMatricula()+"'");
                db.close();
                rellenarListado();
                return 1;
            }
            rellenarListado();
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }
    }

    @Override
    public int modificarVehiculo(String matricula, String nuevaMatricula, String nuevaMarca, String nuevoModelo) {
        try{
            Vehiculo vehiculo = getVehiculo(matricula);
            db = conn.getWritableDatabase();
            if(db != null){
                db.execSQL("UPDATE vehiculo SET matricula='"+nuevaMatricula+"',marca='"+nuevaMarca+"',modelo='"+nuevoModelo+"' WHERE matricula='"+vehiculo.getMatricula()+"'");
                db.close();
                rellenarListado();
                return 1;
            }
            rellenarListado();
            return 0;
        }
        catch (SQLException ex){
            return 0;
        }

    }

    @Override
    public Vehiculo getVehiculo(String matricula) {
        for (Vehiculo vehiculo:listaVehiculos) {
            if(vehiculo.getMatricula().equals(matricula))
                return vehiculo;
        }
        return null;
    }

    @Override
    public ArrayList<Vehiculo> getVehiculos() {
        return listaVehiculos;
    }

    public Vehiculo getVehiculoPos(int posicion){
        return listaVehiculos.get(posicion);
    }

    public static IDAOVehiculo getInstance(Context context){
        if(dao==null)
            dao = new DAOVehiculo(context);
        return dao;
    }
}
