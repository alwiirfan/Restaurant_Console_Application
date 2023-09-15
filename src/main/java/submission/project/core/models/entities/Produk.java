package submission.project.core.models.entities;

public class Produk {

    private String id;
    private String idCabang;
    private String namaProduk;
    private int jumlahProduk;
    private double harga;

    public Produk() {
    }

    public Produk(String id, String idCabang, String namaProduk, int jumlah, double harga) {
        this.id = id;
        this.idCabang = idCabang;
        this.namaProduk = namaProduk;
        this.jumlahProduk = jumlah;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCabang() {
        return idCabang;
    }

    public void setIdCabang(String idCabang) {
        this.idCabang = idCabang;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getJumlahProduk() {
        return jumlahProduk;
    }

    public void setJumlahProduk(int jumlah) {
        this.jumlahProduk = jumlah;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }
}
