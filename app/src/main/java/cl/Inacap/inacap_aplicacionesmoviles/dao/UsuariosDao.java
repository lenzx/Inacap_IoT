package cl.Inacap.inacap_aplicacionesmoviles.dao;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    public void update(Usuario user) {
        databaseReference.child(user.getId()).setValue(user);
    }
    public void delete(Usuario user) {
        databaseReference.child(user.getId()).removeValue();
    }
}
