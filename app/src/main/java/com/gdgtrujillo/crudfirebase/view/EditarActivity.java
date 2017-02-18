package com.gdgtrujillo.crudfirebase.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gdgtrujillo.crudfirebase.R;
import com.gdgtrujillo.crudfirebase.entidad.Contacto;
import com.gdgtrujillo.crudfirebase.global.Constante;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombres;
    EditText txtApellidoPaterno;
    EditText txtApellidoMaterno;
    EditText txtTelefono;
    EditText txtEmail;
    EditText txtDireccion;
    Button  btnEditar;
    Button  btnCancelar;

    int position;
    List<Contacto> listaContactos;
    Contacto contacto;

    FirebaseDatabase mDatabase;
    DatabaseReference mRefContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        // Obteniendo la posicion del contacto de la listaContactos
        Intent intent = getIntent();
        //position = intent.getIntExtra("EXTRA_POSITION", 0);

        // Obteniendo el contacto de la listaContactos
        //listaContactos = ContactoRepository.getInstance().getContactos();
        //contacto = listaContactos.get(position);
        if(getIntent().getExtras()!=null){
            contacto = new Contacto();
            contacto.key = getIntent().getStringExtra("CONTACTO_KEY");
            contacto.nombres = getIntent().getStringExtra("CONTACTO_NOMBRE");
            contacto.apellidoPaterno = getIntent().getStringExtra("CONTACTO_APEPATERNO");
            contacto.apellidoMaterno = getIntent().getStringExtra("CONTACTO_APEMATERNO");
            contacto.telefono = getIntent().getStringExtra("CONTACTO_TELEFONO");
            contacto.email = getIntent().getStringExtra("CONTACTO_EMAIL");
            contacto.direccion = getIntent().getStringExtra("CONTACTO_DIRECCION");
        }

        // Instancia de views
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtApellidoPaterno = (EditText)findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = (EditText)findViewById(R.id.txtApellidoMaterno);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        // Seteando views
        txtNombres.setText(contacto.getNombres());
        txtApellidoPaterno.setText(contacto.getApellidoPaterno());
        txtApellidoMaterno.setText(contacto.getApellidoMaterno());
        txtTelefono.setText(contacto.getTelefono());
        txtEmail.setText(contacto.getEmail());
        txtDireccion.setText(contacto.getDireccion());

        // Asignando eventos a los botones
        btnEditar.setOnClickListener(editarClickListener);
        btnCancelar.setOnClickListener(cancelarClickListener);

        init();
    }

    private void init(){
        //crear las instancias de Database de Firebase para poder consumir su servicio.
        mDatabase = FirebaseDatabase.getInstance();//Instanciamos el servicios
        mRefContacto = mDatabase.getReference(Constante.contacto);//apuntamos con que nodo vamos a trabajar
    }

    private View.OnClickListener editarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            contacto.setNombres(txtNombres.getText().toString());
            contacto.setApellidoPaterno(txtApellidoPaterno.getText().toString());
            contacto.setApellidoMaterno(txtApellidoMaterno.getText().toString());
            contacto.setTelefono(txtTelefono.getText().toString());
            contacto.setEmail(txtEmail.getText().toString());
            contacto.setDireccion(txtDireccion.getText().toString());

            //listaContactos.set(position, contacto);
            mRefContacto.child(contacto.key).setValue(contacto);
            Toast.makeText(EditarActivity.this, "Datos editados correctamente!", Toast.LENGTH_SHORT).show();
            finish();
        }
    };

    private View.OnClickListener cancelarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
