package cl.Inacap.inacap_aplicacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import cl.Inacap.inacap_aplicacionesmoviles.dao.UsuariosDao;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

public class AgregarUsuario extends AppCompatActivity {

    EditText nombre, apellido, rut, correo;
    Button btn;
    UsuariosDao usuariosDao = new UsuariosDao(FirebaseDatabase.getInstance());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_crud_usuario);
        nombre = findViewById(R.id.TXT_FRAGMENTUSUARIO_NUEVOUSUARIO);
        apellido = findViewById(R.id.TXT_FRAGMENTUSUARIO_APELLIDO);
        rut = findViewById(R.id.TXT_FRAGMENTOUSUARIO_RUT);
        correo = findViewById(R.id.TXT_FRAGMENTOUSUARIO_CORREO);

        btn = findViewById(R.id.BTN_FRAGMENTOUSUARIO_GUARDAR);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUsuario(nombre.getText().toString(), apellido.getText().toString(), rut.getText().toString(), correo.getText().toString(), usuariosDao);
            }
        });
    }

    public void createUsuario(String nombre, String apellido, String rut, String correo, UsuariosDao usuarioDAO) {
        Usuario usuario = new Usuario(nombre, apellido, rut, correo);
        usuarioDAO.create(usuario, new UsuariosDao.OnUsuarioCreadoCallback() {
            @Override
            public void onUsuarioCreado(Usuario usuario) {
                finish();
            }

            @Override
            public void onError(DatabaseError databaseError) {

            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }
}