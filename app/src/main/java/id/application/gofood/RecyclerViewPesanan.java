package id.application.gofood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RecyclerViewPesanan extends RecyclerView.Adapter<RecyclerViewPesanan.RecycHolder> {
    private final Context context;
    private final ArrayList<OrderActivity.DataSetPesanan> arrayList;
    private final TotalPriceCallback totalPriceCallback;

    // Constructor with callback for total price
    public RecyclerViewPesanan(Context context, ArrayList<OrderActivity.DataSetPesanan> arrayList, TotalPriceCallback totalPriceCallback) {
        this.context = context;
        this.arrayList = arrayList;
        this.totalPriceCallback = totalPriceCallback;
    }

    @NonNull
    @Override
    public RecycHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_pesan, parent, false);
        return new RecycHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycHolder holder, int position) {
        OrderActivity.DataSetPesanan data = arrayList.get(position);

        // Set nama pesanan
        holder.namaPesanan.setText(data.getNamaMakanan());

        // Format harga menjadi mata uang Rupiah
        String formattedPrice = NumberFormat.getCurrencyInstance(new Locale("id", "ID"))
                .format(data.getHarga());
        holder.hargaPesanan.setText(formattedPrice);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    // Method to calculate and update total price
    public void calculateTotalPrice() {
        double totalPrice = 0;
        for (OrderActivity.DataSetPesanan data : arrayList) {
            totalPrice += data.getHarga();  // Accumulate the total price
        }

        // Update the total price through the callback
        totalPriceCallback.onTotalPriceUpdated(totalPrice);
    }

    // ViewHolder class
    static class RecycHolder extends RecyclerView.ViewHolder {
        final TextView namaPesanan;
        final TextView hargaPesanan;

        public RecycHolder(@NonNull View itemView) {
            super(itemView);
            namaPesanan = itemView.findViewById(R.id.pesanan);
            hargaPesanan = itemView.findViewById(R.id.harga_pesanan);
        }
    }

    // Interface to callback total price
    public interface TotalPriceCallback {
        void onTotalPriceUpdated(double totalPrice);
    }
}
