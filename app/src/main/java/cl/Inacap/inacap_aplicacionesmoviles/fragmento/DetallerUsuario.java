package cl.Inacap.inacap_aplicacionesmoviles.fragmento;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import cl.Inacap.inacap_aplicacionesmoviles.R;
import cl.Inacap.inacap_aplicacionesmoviles.dao.UsuariosDao;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetallerUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetallerUsuario extends Fragment {

    private Usuario usuario;
    TextView userIdTextView ;
    TextView nombreTextView;
    TextView apellidoTextView ;
    TextView rutTextView ;
    TextView correoTextView ;

    public static DetallerUsuario newInstance(Usuario usuario) {
        DetallerUsuario fragment = new DetallerUsuario();
        fragment.usuario = usuario;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detaller_usuario, container, false);

        userIdTextView = view.findViewById(R.id.txtUserId);
        nombreTextView = view.findViewById(R.id.txtNombre);
        apellidoTextView = view.findViewById(R.id.txtApellido);
        rutTextView = view.findViewById(R.id.txtRut);
        correoTextView = view.findViewById(R.id.txtCorreo);

        userIdTextView.setText(usuario.getId());
        nombreTextView.setText(usuario.getNombre());
        apellidoTextView.setText(usuario.getApellido());
        rutTextView.setText(usuario.getRut());
        correoTextView.setText(usuario.getCorreo());

        return view;
    }

    private void actualizarUsuario() {
        UsuariosDao usuariosDao = new UsuariosDao(FirebaseDatabase.getInstance());
        String nuevoNombre = nombreTextView.getText().toString();
        String nuevoApellido = apellidoTextView.getText().toString();
        String nuevoRut = rutTextView.getText().toString();
        String nuevoCorreo = correoTextView.getText().toString();

        usuario.setNombre(nuevoNombre);
        usuario.setApellido(nuevoApellido);
        usuario.setRut(nuevoRut);
        usuario.setCorreo(nuevoCorreo);

        usuariosDao.update(usuario, new UsuariosDao.OnUsuarioActualizadoCallback() {
            @Override
            public void onUsuarioActualizado(Usuario usuarioActualizado) {
                // El usuario se ha actualizado con éxito
                Toast.makeText(requireContext(), "Usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(DatabaseError databaseError) {
                // Ocurrió un error al actualizar el usuario
                Toast.makeText(requireContext(), "Error al actualizar el usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Función para eliminar el usuario
    private void eliminarUsuario() {
        UsuariosDao usuariosDao = new UsuariosDao(FirebaseDatabase.getInstance());

        usuariosDao.delete(usuario, new UsuariosDao.OnUsuarioEliminadoCallback() {
            @Override
            public void onUsuarioEliminado(Usuario usuarioEliminado) {
                // El usuario se ha eliminado con éxito
                Toast.makeText(requireContext(), "Usuario eliminado correctamente", Toast.LENGTH_SHORT).show();

                // Cierra el fragmento después de la eliminación
                requireActivity().getSupportFragmentManager().popBackStack();
            }

            @Override
            public void onError(DatabaseError databaseError) {
                // Ocurrió un error al eliminar el usuario
                Toast.makeText(requireContext(), "Error al eliminar el usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

}