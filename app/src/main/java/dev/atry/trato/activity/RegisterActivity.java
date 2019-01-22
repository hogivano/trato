package dev.atry.trato.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import dev.atry.trato.R;
import dev.atry.trato.presenter.AuthPresenter;
import dev.atry.trato.view.AuthView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, AuthView {
    EditText email, password, rePassword;
    Button btnRegister;
    TextView textLogin;
    AuthPresenter authPresenter;

    ProgressBar progressBar;
    boolean cekLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        intiPresenter();
        onAttachView();

        rePassword = (EditText) findViewById(R.id.rePassword);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        textLogin = (TextView) findViewById(R.id.textLogin);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        cekLoad = false;

        btnRegister.setOnClickListener(this);
        textLogin.setOnClickListener(this);
    }

    @Override
    public boolean isDestroyed() {
        onDetachView();
        return super.isDestroyed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                if (!cekLoad) {
                    showProgressBar(true);
                    authPresenter.checkInputRegister(email.getText().toString(), password.getText().toString(), rePassword.getText().toString());
                }
                break;
            case R.id.textLogin:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void onLogin(boolean login, String msg) {

    }

    @Override
    public void onRegister(boolean register, String msg) {
        showProgressBar(false);
        if (register){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAttachView() {
        authPresenter.onAttach(this);
    }

    @Override
    public void onDetachView() {
        authPresenter.onDetach();
    }

    public void intiPresenter(){
        authPresenter = new AuthPresenter();
    }

    public void showProgressBar(boolean cekLoad){
        if (!cekLoad){
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
        }

        this.cekLoad = cekLoad;
    }
}
