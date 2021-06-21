package com.example.repasoexamenandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Listado extends AppCompatActivity {
    private IDAOVehiculo dao = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        dao = DAOVehiculo.getInstance(getApplicationContext());

        ListView listViewVehiculo = findViewById(R.id.listViewVehiculos);

        ArrayAdapter<Vehiculo> adapter = new ArrayAdapter<Vehiculo>(this, android.R.layout.simple_list_item_1, dao.getVehiculos());
        listViewVehiculo.setAdapter(adapter);
        listViewVehiculo.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int posicion = position;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Listado.this);
                dialogo1.setTitle("Eliminar");
                dialogo1.setMessage("¿Desea eliminar este vehiculo?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.eliminarVehiculo(dao.getVehiculoPos(posicion));
                        adapter.notifyDataSetChanged();
                        Toast.makeText(Listado.this, "Vehiculo eliminado correctamente", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });
    }
}
