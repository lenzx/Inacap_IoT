package cl.Inacap.inacap_aplicacionesmoviles.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.Inacap.inacap_aplicacionesmoviles.R;
import cl.Inacap.inacap_aplicacionesmoviles.modelo.Usuario;

public class UsuariosAdapter extends ArrayAdapter<Usuario> {
    private Context context;
    private List<Usuario> usuarios;
    private OnItemClickListener clickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        clickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(Usuario usuario);
    }

    public UsuariosAdapter(Context context, List<Usuario> usuarios) {
        super(context, 0, usuarios);
        this.context = context;
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.lista_usuario, parent, false);
        }

        Usuario currentItem = usuarios.get(position);

        TextView nombreTextView = listItemView.findViewById(R.id.TXT_USUARIOLIST_NOMBRE);
        TextView apellidoTextView = listItemView.findViewById(R.id.TXT_USUARIO_APELLIDO);
        TextView rutTextView = listItemView.findViewById(R.id.TXT_USUARIOLIST_RUT);
        TextView correoTextView = listItemView.findViewById(R.id.TXT_USURIOLIST_CORREO);

        nombreTextView.setText(currentItem.getNombre());
        apellidoTextView.setText(currentItem.getApellido());
        rutTextView.setText(currentItem.getRut());
        correoTextView.setText(currentItem.getCorreo());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(currentItem);
                }
            }
        });

        return listItemView;
    }
}