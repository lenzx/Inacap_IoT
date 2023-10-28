package cl.Inacap.inacap_aplicacionesmoviles.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.Inacap.inacap_aplicacionesmoviles.R;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Categoria;

public class CategoriaAdapter extends ArrayAdapter<Categoria> {
    private Context context;
    private List<Categoria> categorias;

    public CategoriaAdapter(Context context, List<Categoria> categorias) {
        super(context, 0, categorias);
        this.context = context;
        this.categorias = categorias;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.model_menu_lista, parent, false);
        }

        Categoria currentItem = categorias.get(position);

        TextView categoriaTextView = listItemView.findViewById(R.id.TXT_MENULIST_NOMBRE);
        TextView descripcionTextView = listItemView.findViewById(R.id.TXT_MENULIST_DESCRIPCION);
        ImageView imagenImageView = listItemView.findViewById(R.id.IMG_USUARIOLIST_IMAGEN);

        categoriaTextView.setText(currentItem.getNombre());
        descripcionTextView.setText(currentItem.getDescripcion());
        imagenImageView.setImageResource(currentItem.getImgCategoria());

        return listItemView;
    }
}
