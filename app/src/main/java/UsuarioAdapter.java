package com.example.prueba_crud_firestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> usuarioList;

    public UsuarioAdapter(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflamos el layout para el item de usuario
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        // Vinculamos cada objeto Usuario con el ViewHolder
        Usuario usuario = usuarioList.get(position);
        holder.bind(usuario);
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView et_cedula, et_nombre, et_email, et_telefono, et_edad, et_direccion;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            // Asignamos cada TextView con su ID en el layout
            et_cedula = itemView.findViewById(R.id.et_cedula);
            et_nombre = itemView.findViewById(R.id.et_nombre);
            et_email = itemView.findViewById(R.id.et_email);
            et_telefono = itemView.findViewById(R.id.et_telefono);
            et_edad = itemView.findViewById(R.id.et_edad);
            et_direccion = itemView.findViewById(R.id.et_direccion);
        }

        public void bind(Usuario usuario) {
            // Llenamos los TextViews con la información del usuario
            et_cedula.setText(usuario.getCedula());
            et_nombre.setText(usuario.getNombre());
            et_email.setText(usuario.getEmail());
            et_telefono.setText(usuario.getTelefono());
            et_edad.setText(String.valueOf(usuario.getEdad()));
            et_direccion.setText(usuario.getDireccion());
        }
    }
}

