package com.example.almacenamientodedatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Create_Fragment extends DialogFragment {
    Button btn_agregar;
    EditText Nombre, Apellido, Rut, Correo, Cargo;
    private FirebaseFirestore mfirestore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create_, container, false);
        mfirestore = FirebaseFirestore.getInstance();

        Nombre = v.findViewById(R.id.nombreP);
        Apellido = v.findViewById(R.id.apellidoP);
        Rut = v.findViewById(R.id.rutP);
        Correo = v.findViewById(R.id.correoP);
        Cargo = v.findViewById(R.id.cargoP);
        btn_agregar = v.findViewById(R.id.btn_agregar);

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombrePer = Nombre.getText().toString().trim();
                String apellidoPer = Apellido.getText().toString().trim();
                String rutPer = Rut.getText().toString().trim();
                String correoPer =Correo.getText().toString().trim();
                String cargoPer= Cargo.getText().toString().trim();

                if (nombrePer.isEmpty() && apellidoPer.isEmpty() && rutPer.isEmpty()&& correoPer.isEmpty() && cargoPer.isEmpty()) {
                    Toast.makeText(getContext(), "Rellenar Campos Solicitados", Toast.LENGTH_SHORT).show();
                }else {
                    postPersonal(nombrePer,apellidoPer,rutPer,correoPer,cargoPer);
                }

            }
        });
        return v;
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
                Toast.makeText(getContext(),"Personal Añadido Exitosamente",Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(),"Error al ingresar los datos",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
