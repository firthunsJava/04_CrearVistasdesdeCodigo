package com.firthuns.a04_crearvistasdesdecodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firthuns.a04_crearvistasdesdecodigo.modelos.Alumno;

public class VerAlumnoActivity extends AppCompatActivity {

    private EditText txtNombre, txtApellidos;
    private Button btnGuardar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_alumno);

        txtNombre = findViewById(R.id.txtNombreVer);
        txtApellidos = findViewById(R.id.txtApellidosVer);
        btnGuardar = findViewById(R.id.btnGuardarVer);
        btnGuardar = findViewById(R.id.btnGuardarVer);
        btnEliminar = findViewById(R.id.btnEliminarAlumnoVer);


        final Alumno alumno = getIntent().getExtras().getParcelable("ALUMNO");
        if (alumno != null) {
            final int posicion = getIntent().getExtras().getInt("POSICION");

            txtNombre.setText(alumno.getNombre());
            txtApellidos.setText(alumno.getApellidos());

            setTitle(alumno.getNombre());
            {
                btnGuardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!txtNombre.getText().toString().isEmpty() &&
                                !txtApellidos.getText().toString().isEmpty()) {
                            alumno.setNombre(txtNombre.getText().toString());
                            alumno.setApellidos(txtApellidos.getText().toString());
                            Intent intent = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("ALUMNO", alumno);
                            bundle.putInt("POSICION", posicion);
                            intent.putExtras(bundle);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                });// fin setOnClickListener

                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putInt("POSICION", posicion);
                        intent.putExtras(bundle);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });


            }// fin condicional if
        } // fin onCreate
    }
}