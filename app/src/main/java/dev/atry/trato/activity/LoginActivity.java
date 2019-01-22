package dev.atry.trato.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import dev.atry.trato.R;
import dev.atry.trato.presenter.AuthPresenter;
import dev.atry.trato.view.AuthView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AuthView {

    EditText email;
    EditText password;
    TextView textRegister;
    Button btnLogin;
    ProgressBar progressBar;
    boolean loadLogin;

    AuthPresenter authPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initPresenter();
        onAttachView();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        textRegister = (TextView) findViewById(R.id.textRegister);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        btnLogin.setOnClickListener(this);
        textRegister.setOnClickListener(this);

        FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();
        if (auth == null){
            Log.d("User", "User tidak ada");
        } else {
            Log.d("user", "User ada");
        }
    }

    public void initPresenter(){
        authPresenter = new AuthPresenter();
    }

    public void showProgressBar(boolean loadLogin){
        if (loadLogin){
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

        this.loadLogin = loadLogin;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                if (!loadLogin){
                    showProgressBar(true);
                    authPresenter.checkInputLogin(email.getText().toString(), password.getText().toString());
                }
                break;
            case R.id.textRegister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
                break;
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

    @Override
    public void onLogin(boolean login, String msg) {
        if (!login){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show();
        }

        showProgressBar(false);
    }

    @Override
    public void onRegister(boolean register, String msg) {
        if (!register){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        onDetachView();
        super.onDestroy();
    }
}
