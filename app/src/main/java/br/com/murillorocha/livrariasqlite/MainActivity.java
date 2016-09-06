package br.com.murillorocha.livrariasqlite;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import br.com.murillorocha.livrariasqlite.dao.LivrosDAO;
import br.com.murillorocha.livrariasqlite.model.Livro;

public class MainActivity extends AppCompatActivity {

    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        testeDB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void testeDB() {

        LivrosDAO dao = new LivrosDAO(this);
        SharedPreferences sp = getSharedPreferences("LIVRARIA",MODE_PRIVATE);

        if(!sp.getBoolean("JAINSERIU",false)) {
            dao.add(new Livro("Google Android", "Ricardo Lechetta"));
            dao.add(new Livro("Crepúsculo", "Stephenie Meyer"));
            dao.add(new Livro("50 Tons de Cinza", "E. L. James"));

            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("JAINSERIU", true);

            editor.commit();
        }

        List<Livro> livros = dao.getAll();

        for(Livro l : livros) {
            Log.i("LIVRO", l.getTitulo());
        }

    }

}
