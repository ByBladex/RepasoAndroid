package com.example.repasoexamenandroid;

import java.util.ArrayList;

public interface IDAOVehiculo {

    public int insertarVehiculo(Vehiculo vehiculo);
    public int eliminarVehiculo(Vehiculo vehiculo);
    public int modificarVehiculo(String matricula, String nuevaMatricula, String nuevaMarca, String nuevoModelo);
    public Vehiculo getVehiculo(String matricula);
    public ArrayList<Vehiculo> getVehiculos();
    public Vehiculo getVehiculoPos(int posicion);
}
