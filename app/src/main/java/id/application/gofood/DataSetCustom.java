package id.application.gofood;

public class DataSetCustom {
    private String alamat;
    private String gambar;
    private int harga;
    private int id;
    private String nama_makanan;
    private String tanggal;
    private String type;

    // Konstruktor tanpa argumen (dibutuhkan oleh Firebase)
    public DataSetCustom() {
    }

    // Konstruktor dengan argumen
    public DataSetCustom(String alamat, String gambar, int harga, int id, String nama_makanan, String tanggal, String type) {
        this.alamat = alamat;
        this.gambar = gambar;
        this.harga = harga;
        this.id = id;
        this.nama_makanan = nama_makanan;
        this.tanggal = tanggal;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // Getter dan Setter
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}

