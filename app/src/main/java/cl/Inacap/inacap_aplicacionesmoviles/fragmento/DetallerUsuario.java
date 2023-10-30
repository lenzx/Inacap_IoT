package cl.Inacap.inacap_aplicacionesmoviles.fragmento;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import cl.Inacap.inacap_aplicacionesmoviles.R;
import cl.Inacap.inacap_aplicacionesmoviles.dao.UsuariosDao;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

public class DetallerUsuario extends Fragment {

    private TextView nombreTextView, apellidoTextView, rutTextView, correoTextView;
    private Button eliminarButton, guardarButton;
    private Usuario usuario;

    public static DetallerUsuario newInstance(Usuario usuario) {
        DetallerUsuario fragment = new DetallerUsuario();
        fragment.usuario = usuario;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detaller_usuario, container, false);

        nombreTextView = view.findViewById(R.id.txtNombre);
        apellidoTextView = view.findViewById(R.id.txtApellido);
        rutTextView = view.findViewById(R.id.txtRut);
        correoTextView = view.findViewById(R.id.txtCorreo);
        eliminarButton = view.findViewById(R.id.btnEliminar);
        guardarButton = view.findViewById(R.id.btnGuardar);

        nombreTextView.setText(usuario.getNombre());
        apellidoTextView.setText(usuario.getApellido());
        rutTextView.setText(usuario.getRut());
        correoTextView.setText(usuario.getCorreo());

        // Agregar evento al botón "Eliminar"
        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Eliminar el usuario
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
        });

        return view;
    }

}