package com.example.almacenamientodedatos.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.almacenamientodedatos.R;
import com.example.almacenamientodedatos.modelo.Personal;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Adapter extends FirestoreRecyclerAdapter<Personal, Adapter.ViewHolder> {
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;

    public Adapter(@NonNull FirestoreRecyclerOptions<Personal> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Personal Personal) {

        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        viewHolder.Nombre.setText(Personal.getNombre());
        viewHolder.Apellido.setText(Personal.getApellido());
        viewHolder.Rut.setText(Personal.getRut());
        viewHolder.Correo.setText(Personal.getCorreo());
        viewHolder.Cargo.setText(Personal.getCargo());

        viewHolder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePet(id);
            }
        });
    }

    private void deletePet(String id) {
        mFirestore.collection("Personal").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado Correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al Elminar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_personal, parent, false);
        return new ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Nombre, Apellido, Rut, Correo, Cargo;
        ImageView btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Nombre = itemView.findViewById(R.id.nombreP);
            Apellido = itemView.findViewById(R.id.apellidoP);
            Rut = itemView.findViewById(R.id.rutP);
            Correo= itemView.findViewById(R.id.correoP);
            Cargo= itemView.findViewById(R.id.cargoP);
            btn_delete = itemView.findViewById(R.id.btn_eliminar);


        }
    }
}