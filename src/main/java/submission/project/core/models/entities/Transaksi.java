package submission.project.core.models.entities;

import submission.project.core.models.entities.enums.TipeTransaksi;

import java.util.Date;

public class Transaksi {

    private String noStruk;
    private String idProduk;
    private TipeTransaksi tipeTransaksi;
    private int jumlahProduk;
    private double totalPenjualan;
    private Date tanggalTransaksi;

    public Transaksi() {
    }

    public Transaksi(String noStruk, String idProduk, TipeTransaksi tipeTransaksi,int jumlahProduk , double totalPenjualan, Date tanggalTransaksi) {
        this.noStruk = noStruk;
        this.idProduk = idProduk;
        this.tipeTransaksi = tipeTransaksi;
        this.jumlahProduk = jumlahProduk;
        this.totalPenjualan = totalPenjualan;
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getNoStruk() {
        return noStruk;
    }

    public void setNoStruk(String noStruk) {
        this.noStruk = noStruk;
    }

    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public TipeTransaksi getTipeTransaksi() {
        return tipeTransaksi;
    }

    public void setTipeTransaksi(TipeTransaksi tipeTransaksi) {
        this.tipeTransaksi = tipeTransaksi;
    }

    public int getJumlahProduk() {
        return jumlahProduk;
    }

    public void setJumlahProduk(int jumlahProduk) {
        this.jumlahProduk = jumlahProduk;
    }

    public double getTotalPenjualan() {
        return totalPenjualan;
    }

    public void setTotalPenjualan(double totalPenjualan) {
        this.totalPenjualan = totalPenjualan;
    }

    public Date getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(Date tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }
}
