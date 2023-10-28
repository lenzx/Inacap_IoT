package cl.Inacap.inacap_aplicacionesmoviles.fragmento;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cl.Inacap.inacap_aplicacionesmoviles.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetallerUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetallerUsuario extends Fragment {

    private String userId;
    private String nombre;
    private String apellido;
    private String rut;
    private String correo;


    public static DetallerUsuario newInstance(String id, String nombre, String apellido, String rut, String correo) {
        DetallerUsuario fragment = new DetallerUsuario();
        Bundle args = new Bundle();
        args.putString("user_id", id);
        args.putString("user_nombre", nombre);
        args.putString("user_apellido", apellido);
        args.putString("user_rut", rut);
        args.putString("user_correo", correo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getString("user_id");
            nombre = getArguments().getString("user_nombre");
            apellido = getArguments().getString("user_apellido");
            rut = getArguments().getString("user_rut");
            correo = getArguments().getString("user_correo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detaller_usuario, container, false);

        // Aquí puedes acceder a los elementos de la vista y mostrar detalles del usuario
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView userIdTextView = view.findViewById(R.id.txtUserId);
        TextView nombreTextView = view.findViewById(R.id.txtNombre);
        TextView apellidoTextView = view.findViewById(R.id.txtApellido);
        TextView rutTextView = view.findViewById(R.id.txtRut);
        TextView correoTextView = view.findViewById(R.id.txtCorreo);
        userIdTextView.setText(userId);
        nombreTextView.setText(nombre);
        apellidoTextView.setText(apellido);
        rutTextView.setText(rut);
        correoTextView.setText(correo);

        // Agrega más elementos y lógica para mostrar detalles del usuario según el ID

        return view;
    }
}