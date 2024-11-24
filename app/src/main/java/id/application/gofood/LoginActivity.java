package id.application.gofood;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private AppCompatButton btn_login;
    private TextInputEditText input_email, input_pass;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);

        // Inisialisasi ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        input_email = findViewById(R.id.input_email);
        input_pass = findViewById(R.id.input_password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(view -> {
            String email = String.valueOf(input_email.getText());
            String pass = String.valueOf(input_pass.getText());

            login_with_firebase(email, pass);
        });

        TextView reg = findViewById(R.id.text_register);
        reg.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void login_with_firebase(String email, String pass) {
        if (TextUtils.isEmpty(email)) {
            input_email.setError("Email wajib diisi");
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            input_pass.setError("Password wajib diisi");
            return;
        }

        // Tampilkan ProgressDialog
        progressDialog.show();

        // Firebase authentication logic
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    // Sembunyikan ProgressDialog setelah selesai
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        // Login berhasil, pindah ke activity berikutnya
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish(); // Tutup login activity
                    } else {
                        // Jika login gagal
                        input_pass.setError("Login gagal, periksa email dan password Anda.");
                    }
                });
    }
}
