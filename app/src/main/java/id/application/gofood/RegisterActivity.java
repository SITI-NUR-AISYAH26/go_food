package id.application.gofood;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private AppCompatButton btn_register;
    private TextInputEditText input_email, input_pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        input_email = findViewById(R.id.input_email);
        input_pass = findViewById(R.id.input_password);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btn_register = findViewById(R.id.btn_daftar);
        btn_register.setOnClickListener(view -> {
            String email = String.valueOf(input_email.getText());
            String pass = String.valueOf(input_pass.getText());

            register_firebase(email, pass);
        });
    }

    private void register_firebase(String email, String pass) {
        if (TextUtils.isEmpty(email)) {
            input_email.setError("Email wajib diisi");
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            input_pass.setError("Password wajib diisi");
            return;
        }

        // Start the registration process
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Registration successful
                        FirebaseUser user = mAuth.getCurrentUser();

                        // Optionally, you can display a success message or proceed to another activity
                        Toast.makeText(RegisterActivity.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();

                        // Redirect user to LoginActivity (or HomeActivity)
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish(); // Close RegisterActivity
                    } else {
                        // Registration failed
                        Toast.makeText(RegisterActivity.this, "Pendaftaran gagal: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
