package cl.Inacap.inacap_aplicacionesmoviles.dao;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

public class UsuariosDao {
    private final DatabaseReference databaseReference;

    public UsuariosDao(FirebaseDatabase database) {
        this.databaseReference = database.getReference("users");
    }

    public void create(Usuario user) {

        DatabaseReference userRef = this.databaseReference.push();
        user.setId(userRef.getKey());
        userRef.setValue(user);
    }
    public Usuario getById(int id) {
        DataSnapshot dataSnapshot = databaseReference.child(String.valueOf(id)).get().getResult();
        return dataSnapshot.getValue(Usuario.class);
    }
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();

        DataSnapshot dataSnapshot = databaseReference.get().getResult();
        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
            Usuario usuario = childDataSnapshot.getValue(Usuario.class);
            usuarios.add(usuario);
        }

        return usuarios;
    }
    public void update(Usuario user) {
        databaseReference.child(user.getId()).setValue(user);
    }
    public void delete(Usuario user) {
        databaseReference.child(user.getId()).removeValue();
    }
}
