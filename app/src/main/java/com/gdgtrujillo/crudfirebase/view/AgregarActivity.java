package com.gdgtrujillo.crudfirebase.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gdgtrujillo.crudfirebase.R;
import com.gdgtrujillo.crudfirebase.entidad.Contacto;
import com.gdgtrujillo.crudfirebase.global.Constante;
import com.gdgtrujillo.crudfirebase.global.ContactoRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AgregarActivity extends AppCompatActivity {

    EditText txtNombres;
    EditText txtApellidoPaterno;
    EditText txtApellidoMaterno;
    EditText txtTelefono;
    EditText txtEmail;
    EditText txtDireccion;
    Button btnAgregar;
    Button  btnCancelar;

    List<Contacto> listaContactos;

    FirebaseDatabase mDatabase;
    DatabaseReference mRefContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        listaContactos = ContactoRepository.getInstance().getContactos();

        // Instancia de views
        txtNombres = (EditText)findViewById(R.id.txtNombres);
        txtApellidoPaterno = (EditText)findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = (EditText)findViewById(R.id.txtApellidoMaterno);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        btnAgregar.setOnClickListener(agregarClickListener);
        btnCancelar.setOnClickListener(cancelarClickListener);

        init();
    }

    private void init(){
        //crear las instancias de Database de Firebase para poder consumir su servicio.
        mDatabase = FirebaseDatabase.getInstance();//Instanciamos el servicios
        mRefContacto = mDatabase.getReference(Constante.contacto);//apuntamos con que nodo vamos a trabajar
    }

    private Contacto crearContacto(){
        Contacto contacto = new Contacto();

        contacto.setNombres(txtNombres.getText().toString());
        contacto.setApellidoPaterno(txtApellidoPaterno.getText().toString());
        contacto.setApellidoMaterno(txtApellidoMaterno.getText().toString());
        contacto.setTelefono(txtTelefono.getText().toString());
        contacto.setEmail(txtEmail.getText().toString());
        contacto.setDireccion(txtDireccion.getText().toString());

        return contacto;

    }

    private View.OnClickListener agregarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Contacto contacto = crearContacto();
            //Trabajando con listas
            //listaContactos.add(contacto);
            //Guardando el registro en Firebase
            /*aquí estamos accediendo a la referencia, generando un id automatico, y pasandole como objecto.
            la clase contacto*/
            mRefContacto.push().setValue(contacto);
            Toast.makeText(AgregarActivity.this, "Usuario agregado correctamente!", Toast.LENGTH_SHORT).show();
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
