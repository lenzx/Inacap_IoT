package cl.Inacap.inacap_aplicacionesmoviles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cl.Inacap.inacap_aplicacionesmoviles.adaptador.CategoriaAdapter;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Categoria;

public class Menu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listaView;
    List<String> lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listaView = (ListView) findViewById(R.id.LIST_MENU_OPCIONES);
        List<Categoria> listaObjetos = new ArrayList<Categoria>();
        listaObjetos.add(new Categoria("Habitaciones","Habitaciones Disponibles del hotel ",R.drawable.habitaciones));
        listaObjetos.add(new Categoria("Comida","Comidas Disponibles del hotel",R.drawable.comidas));
        listaObjetos.add(new Categoria("Usuarios","Usuarios del hotel",R.drawable.usuarios));

        CategoriaAdapter adaptador = new CategoriaAdapter(this, listaObjetos);
        listaView.setAdapter(adaptador);
        listaView.setOnItemClickListener(this);



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CategoriaAdapter adaptador = (CategoriaAdapter) listaView.getAdapter();
        Categoria categoria = adaptador.getItem(position);
        //            case "Habitaciones":
        //                Intent intent = new Intent(this, HabitacionesActivity.class);
        //                startActivity(intent);
        //                break;
        //            case "Comida":
        //                Intent intent2 = new Intent(this, ComidasActivity.class);
        //                startActivity(intent2);
        //                break;
        if (categoria.getNombre().equals("Usuarios")) {
            Intent intent3 = new Intent(this, Usuarios.class);
            startActivity(intent3);
        }
    }

}