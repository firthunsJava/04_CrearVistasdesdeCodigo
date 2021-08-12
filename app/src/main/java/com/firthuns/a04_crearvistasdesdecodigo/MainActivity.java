package com.firthuns.a04_crearvistasdesdecodigo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.firthuns.a04_crearvistasdesdecodigo.modelos.Alumno;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int VER_ACTIVITY = 2;
    private final int CREAR_ALUMNO = 1;
    private LinearLayout contenedor;
    private Button btnCrear;
    private ArrayList<Alumno> alumnosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenedor = findViewById(R.id.contenedorMain);
        btnCrear = findViewById(R.id.btnCrearMain);

        alumnosList = new ArrayList<>();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CrearAlumnoActivity.class);
                startActivityForResult(intent, CREAR_ALUMNO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREAR_ALUMNO && resultCode==RESULT_OK){
            if (data != null){
                final Alumno alumno = data.getExtras().getParcelable("ALUMNO");
                // Inserto el Alumno en el ArrayList
                alumnosList.add(alumno);
                final int posicion = alumnosList.size()-1;

                if (alumno != null){
                    // Desde 0 un TextView
                    TextView txtAlumno = new TextView(this);
                    txtAlumno.setText(alumno.getNombre());
                    txtAlumno.setTextSize(20);
                    txtAlumno.setTextColor(Color.GREEN);
                    // Creado un Intent con la info


                    txtAlumno.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, VerAlumnoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelable("ALUMNO", alumno);
                            bundle.putInt("POSICION", posicion);
                            intent.putExtras(bundle);
                            startActivityForResult(intent, VER_ACTIVITY);
                        }
                    });

                    contenedor.addView(txtAlumno);
                }
            }
        }
        if (requestCode == VER_ACTIVITY && resultCode == RESULT_OK){
            if (data != null){
                int posicion = data.getExtras().getInt("POSICION");
                // la linea 82, no se ejecuta cuando le damos al boton eliminar, dado
                // que lo unico que enviamos es la posicion, por tanto, alumno es nullo
                Alumno alumno = data.getExtras().getParcelable("ALUMNO");
                if (alumno == null){

                    alumnosList.remove(posicion);
                }else{
                    alumnosList.get(posicion).setNombre(alumno.getNombre());
                    alumnosList.get(posicion).setApellidos(alumno.getApellidos());
                }


                repintarElementos();
            }
        }



    }

    private void repintarElementos() {

        contenedor.removeAllViews();

        for (int i = 0; i < alumnosList.size(); i++) {
            final Alumno alumno = alumnosList.get(i);
            // Desde 0 un TextView
            TextView txtAlumno = new TextView(this);
            txtAlumno.setText(alumno.getNombre());
            txtAlumno.setTextSize(20);
            txtAlumno.setTextColor(Color.GREEN);
            // Creado un Intent con la info

            final int finalI = i;

            txtAlumno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, VerAlumnoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("ALUMNO", alumno);
                    bundle.putInt("POSICION", finalI);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, VER_ACTIVITY);
                }
            });

            contenedor.addView(txtAlumno);
        }
    }
}