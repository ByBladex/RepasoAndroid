package com.example.repasoexamenandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Insertar extends AppCompatActivity {
    private IDAOVehiculo dao = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        dao = DAOVehiculo.getInstance(getApplicationContext());

        EditText txtMatricula = findViewById(R.id.editTextMatricula);
        EditText txtMarca = findViewById(R.id.editTextMarca);
        EditText txtModelo = findViewById(R.id.editTextModelo);

        Button btnInsertar = findViewById(R.id.btnInsertarVehiculo);
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtMatricula.getText().toString().isEmpty() && !txtMarca.getText().toString().isEmpty() && !txtModelo.getText().toString().isEmpty()){
                    if(dao.insertarVehiculo(new Vehiculo(txtMatricula.getText().toString(),txtMarca.getText().toString(),txtModelo.getText().toString())) == 1){
                        Toast.makeText(Insertar.this, "Vehiculo añadido correctamente", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(Insertar.this, "Error. No se pudo añadir el vehiculo", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Insertar.this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
