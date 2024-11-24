package id.application.gofood;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import id.application.gofood.*;
import id.application.gofood.fragment.Beranda;
import id.application.gofood.fragment.Explore;
import id.application.gofood.fragment.Profile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewactivity);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull android.view.MenuItem item) {
                Fragment selectedFragment = null;

                // Cek item yang dipilih
                if(item.getItemId() == R.id.nav_home){
                    selectedFragment = new Beranda();
                }else if(item.getItemId() == R.id.nav_explore){
                    selectedFragment = new Explore();
                }else if(item.getItemId() == R.id.nav_profile){
                    selectedFragment = new Profile();
                }

                if (selectedFragment != null) {
                    replaceFragment(selectedFragment);
                    return true;
                }
                return false;
            }
        });

        // Set default fragment saat pertama kali
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_home);
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout, fragment)
                .commit();
    }
}
