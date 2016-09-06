package br.com.murillorocha.livrariasqlite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private final String PREF_NAME = "LIVRARIA";

    private TextInputLayout tilLogin;
    private TextInputLayout tilPassword;
    private CheckBox ckManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilLogin = (TextInputLayout) findViewById(R.id.tilLogin);
        tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        ckManterConectado = (CheckBox) findViewById(R.id.ckManterConectado);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        if(sp.getBoolean("manterConectado",false)) {
            proximaTela(sp.getString("nomeUsuario",""));
        }

    }

    public void doLogin(View v){
        String usuario = tilLogin.getEditText().getText().toString();
        String senha = tilPassword.getEditText().getText().toString();
        if(usuario.equals("fiap") &&
                senha.equals("123")) {
            SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("manterConectado",ckManterConectado.isChecked());
            editor.putString("nomeUsuario", usuario);

            editor.commit();

            proximaTela(usuario);
        }else{
            Toast.makeText(this,"Usuario ou senha invalido",Toast.LENGTH_LONG).show();
        }
    }

    private void proximaTela(String usuario) {
        Intent telaPrincipal = new Intent(this, MainActivity.class);
        telaPrincipal.putExtra("nome", usuario);
        startActivity(telaPrincipal);
        finish();
    }
}
