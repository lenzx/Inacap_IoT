package cl.Inacap.inacap_aplicacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import cl.Inacap.inacap_aplicacionesmoviles.adaptador.UsuariosAdapter;
import cl.Inacap.inacap_aplicacionesmoviles.dao.UsuariosDao;
import cl.Inacap.inacap_aplicacionesmoviles.fragmento.DetallerUsuario;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

public class Usuarios extends AppCompatActivity {

    ListView listaView;
    UsuariosDao usuariosDao;
    UsuariosAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        listaView = (ListView) findViewById(R.id.LIST_USUARIOS_LISTA);

        usuariosDao = new UsuariosDao(FirebaseDatabase.getInstance());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.botonSecondFloting);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Usuarios.this, AgregarUsuario.class);
                startActivity(intent);
            }
        });

        usuariosDao.getAll2(new UsuariosDao.OnUsuariosDataReceived() {
            @Override
            public void onDataReceived(List<Usuario> usuarios) {
                adaptador = new UsuariosAdapter(Usuarios.this, usuarios);
                listaView.setAdapter(adaptador);
                adaptador.setOnItemClickListener(new UsuariosAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Usuario usuario) {
                        DetallerUsuario detalleFragment = DetallerUsuario.newInstance(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getRut(), usuario.getCorreo());

                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragment_container, detalleFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                });

            }

            @Override
            public void onError(DatabaseError databaseError) {
            }
        });
    }
}