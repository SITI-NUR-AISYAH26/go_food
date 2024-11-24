package id.application.gofood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class RecyclerViewHorizontal extends RecyclerView.Adapter<RecyclerViewHorizontal.RecHolder> {

    private final Context context;
    private final ArrayList<DataSetCustom> array;

    public RecyclerViewHorizontal(Context context, ArrayList<DataSetCustom> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public RecHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_horizontal, parent, false);
        return new RecHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecHolder holder, int position) {
        DataSetCustom data = array.get(position);

        holder.nama.setText(data.getNama_makanan());

        // Format harga ke format mata uang
        String hargaFormatted = NumberFormat.getCurrencyInstance(new Locale("id", "ID"))
                .format((long) data.getHarga());
        holder.harga.setText(hargaFormatted);

        holder.alamat.setText(data.getAlamat());

        // Memuat gambar berdasarkan tipe
        if (data.getGambar() != null) {
            // Jika gambar URL tersedia, gunakan Glide untuk memuatnya
            Glide.with(context)
                    .load(data.getGambar())
                    .placeholder(R.drawable.loading) // Placeholder saat gambar dimuat
                    .error(R.drawable.error) // Gambar default jika gagal memuat
                    .into(holder.gambar);
        } else {
            // Jika tidak ada URL, gunakan resource ID
            Glide.with(context)
                    .load(data.getGambar())
                    .placeholder(R.drawable.loading) // Placeholder saat gambar dimuat
                    .error(R.drawable.error) // Gambar default jika gagal memuat
                    .into(holder.gambar);
        }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public static class RecHolder extends RecyclerView.ViewHolder {
        ImageView gambar;
        TextView nama;
        TextView harga;
        TextView alamat;
        TextView tanggal;

        public RecHolder(@NonNull View itemView) {
            super(itemView);
            gambar = itemView.findViewById(R.id.gambar);
            nama = itemView.findViewById(R.id.nama_makanan);
            harga = itemView.findViewById(R.id.harga_makanan);
            alamat = itemView.findViewById(R.id.alamat);
            tanggal = itemView.findViewById(R.id.tanggal_pesanan);
        }
    }
}
