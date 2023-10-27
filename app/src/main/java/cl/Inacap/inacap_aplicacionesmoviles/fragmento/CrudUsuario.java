package cl.Inacap.inacap_aplicacionesmoviles.fragmento;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import cl.Inacap.inacap_aplicacionesmoviles.R;
import cl.Inacap.inacap_aplicacionesmoviles.Usuarios;
import cl.Inacap.inacap_aplicacionesmoviles.dao.UsuariosDao;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

public class CrudUsuario extends Fragment {

    EditText nombre, apellido, rut, correo;
    Button btn;
    UsuariosDao UsuariosDao = new UsuariosDao(FirebaseDatabase.getInstance());
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nombre = (EditText) view.findViewById(R.id.TXT_FRAGMENTUSUARIO_NUEVOUSUARIO);
        apellido = (EditText) view.findViewById(R.id.TXT_FRAGMENTUSUARIO_APELLIDO);
        rut = (EditText) view.findViewById(R.id.TXT_FRAGMENTOUSUARIO_RUT);
        correo = (EditText) view.findViewById(R.id.TXT_FRAGMENTOUSUARIO_CORREO);

        btn= (Button) view.findViewById(R.id.BTN_FRAGMENTOUSUARIO_GUARDAR);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUsuario(nombre.getText().toString(), apellido.getText().toString(), rut.getText().toString(), correo.getText().toString(), UsuariosDao);
            }
        });
    }

    public void createUsuario(String nombre, String apellido, String rut, String correo, UsuariosDao usuarioDAO) {
        Usuario usuario = new Usuario(nombre, apellido, rut, correo);
        usuarioDAO.create(usuario);
    }


}