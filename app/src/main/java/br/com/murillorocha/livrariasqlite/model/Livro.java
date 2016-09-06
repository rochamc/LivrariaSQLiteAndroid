package br.com.murillorocha.livrariasqlite.model;

/**
 * Created by rm30654 on 05/09/2016.
 */
public class Livro {

    private int id;
    private String titulo;
    private String autor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Livro(){

    }

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
}
