package com.gdgtrujillo.crudfirebase.global;

import com.gdgtrujillo.crudfirebase.entidad.Contacto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brianbritto on 09/02/17.
 */

public class ContactoRepository {
    private static ContactoRepository repositorio = new ContactoRepository();
    List<Contacto> contactos = new ArrayList<>();

    public static ContactoRepository getInstance() {
        return repositorio;
    }

    private ContactoRepository(){
        saveContacto(new Contacto("Brian", "Britto", "Juro", "926298867", "brianbrittojuro@gmail.com", "Av. HackSpace 007"));
        saveContacto(new Contacto("Richard", "Oruna", "Rodriguez", "4421210", "ricoru@gmail.com", "Av. Wando 1000"));
    }

    private void saveContacto(Contacto contacto) {
        contactos.add(contacto);
    }

    public List<Contacto> getContactos() {
        return contactos;
    }
}
