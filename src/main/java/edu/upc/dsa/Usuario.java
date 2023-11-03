package edu.upc.dsa;

import edu.upc.dsa.Partida;
import edu.upc.dsa.util.idGenerator;

import java.util.HashMap;

/*
    Defino la clase usuario que será el jugador.
    Tendrá un identificador de tipo String, un mail para el registro tipo String,
    un nombre tipo String también, una contraseña también String y una lista de
    partidas que se implementará con un HashMap.
 */
public class Usuario {

    String id;
    String mail;
    String name;
    String pass;
    HashMap<String, Partida> partidas;

    public Usuario(String mail, String nanme, String pass) {
        this.setId(idGenerator.getId());
        this.setMail(mail);
        this.setPass(pass);
        this.setName(name);
        this.setPartidas();
    }

    /*
        Creación de los getters i setters para obtener y crear todos
        los datos de los usuarios.
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public HashMap<String, Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas() {
        this.partidas = new HashMap<>();
    }

    // Añado la partida actual a las partidas realizadas.
    public void addPartida(String id, Partida partida){
        this.partidas.put(id, partida);
    }
}
