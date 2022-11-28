package com.example.almacenamientodedatos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreatePersonalActivity extends AppCompatActivity {

    Button btn_agregar;
    EditText Nombre, Apellido, Rut,Correo,Cargo;
    private FirebaseFirestore mfirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_personal);

        this.setTitle("Nuevo Personal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mfirestore = FirebaseFirestore.getInstance();

        Nombre = findViewById(R.id.nombreP);
        Apellido = findViewById(R.id.apellidoP);
        Rut = findViewById(R.id.rutP);
        Correo = findViewById(R.id.correoP);
        Cargo = findViewById(R.id.cargoP);
        btn_agregar = findViewById(R.id.btn_agregar);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombrePer = Nombre.getText().toString().trim();
                String apellidoPer = Apellido.getText().toString().trim();
                String rutPer = Rut.getText().toString().trim();
                String correoPer =Correo.getText().toString().trim();
                String cargoPer= Cargo.getText().toString().trim();

                if (nombrePer.isEmpty() && apellidoPer.isEmpty() && rutPer.isEmpty()&& correoPer.isEmpty() && cargoPer.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Rellenar Campos Solicitados", Toast.LENGTH_SHORT).show();
                }else {
                    postPersonal(nombrePer,apellidoPer,rutPer,correoPer,cargoPer);
                }

            }
        });
    }


    private void postPersonal(String nombrePer, String apellidoPer, String rutPer,String correoPer, String cargoPer) {
        Map<String, Object> map = new HashMap<>();
        map.put("Nombre", nombrePer);
        map.put("Apellido", apellidoPer);
        map.put("Rut", rutPer);
        map.put("Correo",correoPer);
        map.put("Cargo", cargoPer);

        mfirestore.collection("Personal").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(),"Personal Añadido Exitosamente",Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Error al ingresar los datos",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}