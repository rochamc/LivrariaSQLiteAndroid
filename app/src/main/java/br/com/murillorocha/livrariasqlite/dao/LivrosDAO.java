package br.com.murillorocha.livrariasqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.murillorocha.livrariasqlite.model.Livro;

/**
 * Created by rm30654 on 05/09/2016.
 */
public class LivrosDAO  {

    private final String TABELA_LIVROS = "livros";
    private final String KEY_ID = "id";
    private final String KEY_TITULO = "titulo";
    private final String KEY_AUTOR = "autor";

    private final String[] COLUNAS = {KEY_ID, KEY_AUTOR, KEY_TITULO};

    private MySQLiteHelper dbHelper;

    public LivrosDAO(Context context){
        dbHelper = new MySQLiteHelper(context);
    }

    public void add(Livro livro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(KEY_TITULO,livro.getTitulo());
        valores.put(KEY_AUTOR,livro.getAutor());

        db.insert(TABELA_LIVROS, null, valores);

        db.close();
    }

    public Livro get(int id){
        Livro livro = new Livro();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(TABELA_LIVROS,
                COLUNAS,
                " id = ? ",
                new String[]{String.valueOf(id)},
                null, //group by
                null, //having
                null, // order by
                null  //limit
        );

        if(cursor != null){
            cursor.moveToFirst();
            livro.setId(cursor.getInt(0));
            livro.setAutor(cursor.getString(1));
            livro.setTitulo(cursor.getString(2));
        }

        return livro;
    }

    public List<Livro> getAll(){
        List<Livro> livros = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + TABELA_LIVROS;
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do {
                Livro livro = new Livro();
                livro.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                livro.setTitulo(cursor.getString(cursor.getColumnIndex(KEY_TITULO)));
                livro.setAutor(cursor.getString(cursor.getColumnIndex(KEY_AUTOR)));
                livros.add(livro);
            }while(cursor.moveToNext());
        }
        return livros;
    }

    public void update(Livro livro){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(KEY_TITULO,livro.getTitulo());
        valores.put(KEY_AUTOR,livro.getAutor());

        db.update(TABELA_LIVROS,
                valores,
                KEY_ID + " = ? ",
                new String[]{String.valueOf(livro.getId())});

        db.close();
    }

    public void delete(Livro livro){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABELA_LIVROS,
            KEY_ID + " = ? ",
                new String[]{String.valueOf(livro.getId())});

        db.close();
    }
}
