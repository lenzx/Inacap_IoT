package cl.Inacap.inacap_aplicacionesmoviles.dao;

import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

public class UsuariosDao {
    private final DatabaseReference databaseReference;

    public UsuariosDao(FirebaseDatabase database) {
        this.databaseReference = database.getReference("users");
    }

    public void create(Usuario user, final OnUsuarioCreadoCallback callback) {
        DatabaseReference userRef = this.databaseReference.push();
        user.setId(userRef.getKey());
        userRef.setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callback.onUsuarioCreado(user);
                } else {
                    callback.onError(databaseError);
                }
            }
        });
    }

    public Usuario getById(String id, final OnUsuarioDataReceivedById callback) {
        DatabaseReference userRef = databaseReference.child(id);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                callback.onDataReceived(usuario);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError);
            }
        });

        return null;
    }

    public void getAll2(final OnUsuariosDataReceived callback) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Usuario> usuarios = new ArrayList<>();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    Usuario usuario = childDataSnapshot.getValue(Usuario.class);
                    usuarios.add(usuario);
                }
                callback.onDataReceived(usuarios);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError);
            }
        });
    }

    public void update(Usuario user, final OnUsuarioActualizadoCallback callback) {
        DatabaseReference userRef = databaseReference.child(user.getId());
        userRef.setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callback.onUsuarioActualizado(user);
                } else {
                    callback.onError(databaseError);
                }
            }
        });
    }

    public void delete(Usuario user, final OnUsuarioEliminadoCallback callback) {
        DatabaseReference userRef = databaseReference.child(user.getId());
        userRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    callback.onUsuarioEliminado(user);
                } else {
                    callback.onError(databaseError);
                }
            }
        });
    }

    public interface OnUsuariosDataReceived {
        void onDataReceived(List<Usuario> usuarios);
        void onError(DatabaseError databaseError);
    }

    public interface OnUsuarioCreadoCallback {
        void onUsuarioCreado(Usuario usuario);
        void onError(DatabaseError databaseError);

    }

    public interface OnUsuarioActualizadoCallback {
        void onUsuarioActualizado(Usuario usuario);
        void onError(DatabaseError databaseError);
    }

    public interface OnUsuarioEliminadoCallback {
        void onUsuarioEliminado(Usuario usuario);
        void onError(DatabaseError databaseError);
    }

    public interface OnUsuarioDataReceivedById {
        void onDataReceived(Usuario usuario);
        void onError(DatabaseError databaseError);
    }
}
