package id.application.gofood;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.application.gofood.*;

public class OrderActivity extends AppCompatActivity implements RecyclerViewPesanan.TotalPriceCallback {
    private TextView totalPriceTextView;
    private int id,harga = 0;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

        // Initialize the total price TextView
        totalPriceTextView = findViewById(R.id.total_harga);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            id = bundle.getInt("id");
            harga = bundle.getInt("harga");
            title = bundle.getString("title");
        }
        // Call method to list pesanan
        list_pesanan();
    }

    private void list_pesanan() {
        RecyclerView recyclerPesanan = findViewById(R.id.recycler_pesanan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerPesanan.setLayoutManager(linearLayoutManager);

        // Create the order data
        ArrayList<DataSetPesanan> array = new ArrayList<>();
        array.add(new DataSetPesanan(id, title, harga));

        // Create the adapter with the total price callback
        RecyclerViewPesanan adapter = new RecyclerViewPesanan(this, array, this);
        recyclerPesanan.setAdapter(adapter);

        // After setting the adapter, calculate total price
        adapter.calculateTotalPrice();
    }

    // This method will be called by the adapter to update the total price
    @Override
    public void onTotalPriceUpdated(double totalPrice) {
        // Format and display the total price
        @SuppressLint("DefaultLocale") String formattedPrice = String.format("Rp%,.2f", totalPrice);
        totalPriceTextView.setText(formattedPrice);
    }

    // Data model for the orders
    public static class DataSetPesanan {
        private final int id;
        private final String nama_makanan;
        private final int harga;

        public DataSetPesanan(int id, String nama_makanan, int harga) {
            this.id = id;
            this.nama_makanan = nama_makanan;
            this.harga = harga;
        }

        public String getNamaMakanan() {
            return nama_makanan;
        }

        public int getId() {
            return id;
        }

        public int getHarga() {
            return harga;
        }
    }
}
