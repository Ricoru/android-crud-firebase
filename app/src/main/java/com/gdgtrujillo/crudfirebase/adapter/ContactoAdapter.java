package com.gdgtrujillo.crudfirebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gdgtrujillo.crudfirebase.R;
import com.gdgtrujillo.crudfirebase.entidad.Contacto;

import java.util.List;

/**
 * Adaptador de leads
 */
public class ContactoAdapter extends ArrayAdapter<Contacto> {
    public ContactoAdapter(Context context, List<Contacto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.vista_contacto,
                    parent,
                    false);
        }

        // Referencias UI.
        TextView nombreContacto = (TextView) convertView.findViewById(R.id.nombreContacto);
        TextView telefonoContacto = (TextView) convertView.findViewById(R.id.telefonoContacto);

        // Lead actual.
        Contacto contacto = getItem(position);

        // Setup.
        nombreContacto.setText(contacto.getNombres() + " " + contacto.getApellidoPaterno());
        telefonoContacto.setText(contacto.getTelefono());

        return convertView;
    }
}