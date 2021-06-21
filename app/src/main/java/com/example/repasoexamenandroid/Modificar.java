package com.example.repasoexamenandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Modificar extends AppCompatActivity {

    private IDAOVehiculo dao = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        dao = DAOVehiculo.getInstance(getApplicationContext());

        EditText txtMatricula = findViewById(R.id.editTextModMatricula1);
        EditText txtMatricula2 = findViewById(R.id.editTextModMatricula2);
        EditText txtMarca = findViewById(R.id.editTextModMarca);
        EditText txtModelo = findViewById(R.id.editTextModModelo);

        Button btnModificar = findViewById(R.id.btnModificarVehiculo);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtMatricula.getText().toString().isEmpty() && !txtMatricula2.getText().toString().isEmpty() && !txtMarca.getText().toString().isEmpty() && !txtModelo.getText().toString().isEmpty()){
                    if(dao.modificarVehiculo(txtMatricula.getText().toString(),txtMatricula2.getText().toString(),txtMarca.getText().toString(),txtModelo.getText().toString()) == 1){
                        Toast.makeText(Modificar.this, "Vehiculo modificado correctamente", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Modificar.this, "Error. No se pudo modificar el vehiculo", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Modificar.this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
        Button btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtMatricula.getText().toString().isEmpty()){
                    Vehiculo vehiculo = dao.getVehiculo(txtMatricula.getText().toString());
                    if(vehiculo != null){
                        txtMatricula2.setText(vehiculo.getMatricula());
                        txtMarca.setText(vehiculo.getMarca());
                        txtModelo.setText(vehiculo.getModelo());
                        Toast.makeText(Modificar.this, "Vehiculo encontrado", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Modificar.this, "El vehiculo no pudo ser encontrado", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Modificar.this, "Debes introducir una matricula valida", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
