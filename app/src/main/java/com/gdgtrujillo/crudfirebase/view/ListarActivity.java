package com.gdgtrujillo.crudfirebase.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gdgtrujillo.crudfirebase.R;
import com.gdgtrujillo.crudfirebase.adapter.ContactoAdapter;
import com.gdgtrujillo.crudfirebase.entidad.Contacto;
import com.gdgtrujillo.crudfirebase.global.ContactoRepository;

import java.util.List;

public class ListarActivity extends AppCompatActivity {

    ListView lvListaContactos;
    FloatingActionButton btnAgregar;

    ContactoAdapter contactoAdapter;
    List<Contacto> listaContactos;

    @Override
    protected void onResume() {
        super.onResume();

        contactoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listaContactos = ContactoRepository.getInstance().getContactos();

        // Instancia del ListView y FloatingButton
        lvListaContactos = (ListView) findViewById(R.id.lvListaContactos);
        btnAgregar = (FloatingActionButton) findViewById(R.id.btnAgregar);

        // Inicializar el adaptador con la fuente de datos.
        contactoAdapter = new ContactoAdapter(getApplicationContext(), listaContactos);

        //Relacionando la lista con el adaptador
        lvListaContactos.setAdapter(contactoAdapter);

        lvListaContactos.setOnItemClickListener(itemClickListener);
        btnAgregar.setOnClickListener(botonClickListener);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ListarActivity.this, EditarActivity.class);
            //Log.i("usuario", listaContactos.get(position).getNombres());
            intent.putExtra("EXTRA_POSITION", position);
            startActivity(intent);
        }
    };

    private View.OnClickListener botonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ListarActivity.this, AgregarActivity.class);
            startActivity(intent);
        }
    };
}
