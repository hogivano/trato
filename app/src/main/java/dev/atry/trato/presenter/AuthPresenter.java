package dev.atry.trato.presenter;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import dev.atry.trato.BasePresenter;
import dev.atry.trato.view.AuthView;


public class AuthPresenter implements BasePresenter<AuthView> {

    private FirebaseAuth auth;
    private Context context;
    private AuthView authView;
    private DocumentReference docRef;
    private FirebaseFirestore db;
    private FirebaseUser user;

    public void doLogin(String email, String pasword){
        auth.signInWithEmailAndPassword(email, pasword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    user = auth.getCurrentUser();
                    if (user.isEmailVerified()){
                        Log.d("User ", user.getUid());
                        checkUserOnDB(user);
                    } else {
                        authView.onLogin(false, "silahkan verifikasi email terlebih dahulu");
                    }
                } else {
                    Log.d("error in login : ", task.getException().toString());
                    authView.onLogin(false, task.getException().getLocalizedMessage());
                }
            }
        });
    }

    public void checkUserOnDB(FirebaseUser user){
        db.collection("Users").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        authView.onLogin(true, "document ada");
                    } else {
                        addNewDocument();
                    }
                } else {
                    Log.d("Task ", task.getException().getLocalizedMessage());
                }
            }
        });
        authView.onLogin(false, "Check Document");
    }

    public void checkInputLogin(String email, String password){
        if (email.isEmpty() || password.isEmpty()){
            authView.onLogin(false, "Email dan password tidak boleh kosong");
        } else {
            Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            if (regex.matcher(email).find()) {
                doLogin(email, password);
            } else {
                authView.onLogin(false, "format email salah");
            }
        }
    }

    public void checkInputRegister(String email, String password, String rePassword){
        if (email.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
            authView.onRegister(false, "Semua input harus diisi");
        } else {
            if (password.equals(rePassword)){
                Pattern regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                if (regex.matcher(email).find()){
                    doRegister(email, password, rePassword);
                } else {
                    authView.onRegister(false, "format email salah");
                }
            } else {
                authView.onRegister(false, "Password tidak sama!!");
            }
        }
    }


    public void doRegister(String email, String password, String rePassword){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    user = auth.getCurrentUser();
                    emailVerification(user);
                } else {
                    authView.onRegister(false, "Email sudah terdaftar silahkan login");
                }
            }
        });
    }

    public void emailVerification(FirebaseUser user){
        if(user.isEmailVerified()){
            authView.onRegister(true, "Email Sudah Terverifikasi");
        } else {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        authView.onRegister(true, "Berhasil");
                    } else {
                        authView.onRegister(false, "Gagal Verifikasi Email");
                    }
                }
            });
        }
    }

    public void addNewDocument(){
        Map<String, Object> users = new HashMap<>();
        users.put("email", user.getEmail());
        users.put("nama", "");
        users.put("jenis_kelamin", "");
        users.put("umur", 0);
        users.put("foto", "");
        db.collection("users").document(this.user.getUid()).set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                authView.onLogin(true, "upload_foto");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                authView.onLogin(false, "Writting New Document Error");
            }
        });
    }

    @Override
    public void onAttach(AuthView baseView) {
        authView = baseView;
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onDetach() {
        authView = null;
    }
}
