package com.gdgtrujillo.crudfirebase.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gdgtrujillo.crudfirebase.R;
import com.gdgtrujillo.crudfirebase.adapter.ContactoAdapter;
import com.gdgtrujillo.crudfirebase.entidad.Contacto;
import com.gdgtrujillo.crudfirebase.global.Constante;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {

    ListView lvListaContactos;
    FloatingActionButton btnAgregar;

    ContactoAdapter contactoAdapter;
    List<Contacto> listaContactos;

    FirebaseDatabase mDatabase;
    DatabaseReference mContactos;
    ValueEventListener mValueListeneroContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        //listaContactos = ContactoRepository.getInstance().getContactos();

        // Instancia del ListView y FloatingButton
        lvListaContactos = (ListView) findViewById(R.id.lvListaContactos);
        btnAgregar = (FloatingActionButton) findViewById(R.id.btnAgregar);

        // Inicializar el adaptador con la fuente de datos.
        //contactoAdapter = new ContactoAdapter(getApplicationContext(), listaContactos);

        //Relacionando la lista con el adaptador
        //lvListaContactos.setAdapter(contactoAdapter);

        lvListaContactos.setOnItemClickListener(itemClickListener);
        btnAgregar.setOnClickListener(botonClickListener);

        init();
    }

    private void init(){
        mDatabase = FirebaseDatabase.getInstance();
        mContactos = mDatabase.getReference(Constante.contacto);

        //init lista contactos
        listaContactos = new ArrayList<Contacto>();
        // Inicializar el adaptador con la fuente de datos.
        contactoAdapter = new ContactoAdapter(getApplicationContext(), listaContactos);
        //Relacionando la lista con el adaptador
        lvListaContactos.setAdapter(contactoAdapter);

        loadData();
    }

    private void loadData(){
        mValueListeneroContactos = mContactos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){ //si existen datos, hay que obtener la información
                    listaContactos.clear();//limpiamos la data
                    for (DataSnapshot snapshotContacto : dataSnapshot.getChildren()) {
                        Contacto contacto = snapshotContacto.getValue(Contacto.class);
                        contacto.key= snapshotContacto.getKey();
                        //le decimos a Firebase que el q objeto en JSON lo parse a un objecto Contacto
                        //Log.i("INFO","contacto key "+ snapshotContacto.getKey());
                        listaContactos.add(contacto);
                    }
                    contactoAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Error de tipo conexión, errores no existe, etc.
                Log.e("ERROR", databaseError.toString());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //contactoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContactos.removeEventListener(mValueListeneroContactos);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ListarActivity.this, EditarActivity.class);
            Contacto contacto = listaContactos.get(position);
            //Log.i("usuario", listaContactos.get(position).getNombres());
            //intent.putExtra("EXTRA_POSITION", position);
            intent.putExtra("CONTACTO_KEY", contacto.key );
            intent.putExtra("CONTACTO_NOMBRE", contacto.nombres );
            intent.putExtra("CONTACTO_APEPATERNO", contacto.apellidoPaterno );
            intent.putExtra("CONTACTO_APEMATERNO", contacto.apellidoMaterno );
            intent.putExtra("CONTACTO_TELEFONO", contacto.telefono );
            intent.putExtra("CONTACTO_EMAIL", contacto.email );
            intent.putExtra("CONTACTO_DIRECCION", contacto.direccion );
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
