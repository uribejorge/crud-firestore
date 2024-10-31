package com.example.prueba_crud_firestore;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText etCedula ,etNombre, etEmail, etTelefono, etEdad, etDireccion;
    private RecyclerView recyclerView;
    private UsuarioAdapter usuarioAdapter;
    private List<Usuario> usuarioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicialización de Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Referencias a las vistas
        etCedula = findViewById(R.id.et_cedula);
        etNombre = findViewById(R.id.et_nombre);
        etEmail = findViewById(R.id.et_email);
        etTelefono = findViewById(R.id.et_telefono);
        etEdad = findViewById(R.id.et_edad);
        etDireccion = findViewById(R.id.et_direccion);
        recyclerView = findViewById(R.id.recyclerView);

        // Configuración del RecyclerView
        usuarioList = new ArrayList<>();
        usuarioAdapter = new UsuarioAdapter(usuarioList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(usuarioAdapter);

        // Leer usuarios al iniciar la aplicación
        leerUsuarios();
    }

    // Agregar un nuevo usuario
    public void agregarUsuario(View view) {
        String cedula = etCedula.getText().toString();
        String nombre = etNombre.getText().toString();
        String email = etEmail.getText().toString();
        String telefono = etTelefono.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String direccion = etDireccion.getText().toString();

        String id = db.collection("usuarios").document().getId();
        Usuario usuario = new Usuario(cedula, nombre, email, telefono, edad, direccion);

        db.collection("usuarios").document(cedula).set(usuario)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Usuario agregado", Toast.LENGTH_SHORT).show();
                    usuarioList.add(usuario);
                    usuarioAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al agregar usuario", Toast.LENGTH_SHORT).show());
    }

    // Leer usuarios de Firestore
    public void leerUsuarios() {

        db.collection("usuarios").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        usuarioList.clear();
                        for (DocumentSnapshot document : task.getResult()) {
                            Usuario usuario = document.toObject(Usuario.class);
                            usuarioList.add(usuario);
                        }
                        usuarioAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Error al leer usuarios", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Actualizar usuario
    public void actualizarUsuario(View view) {
        String cedula = etCedula.getText().toString();
        String nombre = etNombre.getText().toString();
        String email = etEmail.getText().toString();
        String telefono = etTelefono.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());
        String direccion = etDireccion.getText().toString();

        Usuario usuario = new Usuario(cedula, nombre, email, telefono, edad, direccion);

        db.collection("usuarios").document(cedula).set(usuario) // Actualizar con la cédula como ID
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show();
                    leerUsuarios();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al actualizar usuario", Toast.LENGTH_SHORT).show());
    }

    // Eliminar usuario
    public void eliminarUsuario(View view) {
        String cedula = etCedula.getText().toString();

        db.collection("usuarios").document(cedula).delete() // Usar cédula para eliminar
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    leerUsuarios();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Error al eliminar usuario", Toast.LENGTH_SHORT).show());
    }
}
