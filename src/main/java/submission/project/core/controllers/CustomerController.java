package submission.project.core.controllers;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerController {

    private final ProdukController produkController;
    private final CabangController cabangController;
    private final TransaksiController transaksiController;

    public CustomerController(ProdukController produkController, CabangController cabangController, TransaksiController transaksiController) {
        this.produkController = produkController;
        this.cabangController = cabangController;
        this.transaksiController = transaksiController;
    }

    public void showMenuProduk() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){

            System.out.println(" ________________________________ ");
            System.out.println("|          Menu Produk :         |");
            System.out.println("| 1. Tambah Produk               |");
            System.out.println("| 2. Semua Produk                |");
            System.out.println("| 3. Produk Berdasarkan ID       |");
            System.out.println("| 4. Update Produk               |");
            System.out.println("| 5. Hapus Produk                |");
            System.out.println("| 6. Kembali                     |");
            System.out.println(" ________________________________ ");

            System.out.println("Pilih Angka (1-6)");
            System.out.println("\n");

            System.out.print("Masukkan Pilihan : ");
            int preferred = scanner.nextInt();
            scanner.nextLine();

            switch (preferred) {
                case 1 -> produkController.tambahProduk();
                case 2 -> produkController.semuaProduk();
                case 3 -> produkController.produkBerdasarkanID();
                case 4 -> produkController.updateProduk();
                case 5 -> produkController.hapusProduk();
                case 6 -> exit = true;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void showMenuCabang() throws SQLException{
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){

            System.out.println(" ________________________________ ");
            System.out.println("|          Menu Cabang :         |");
            System.out.println("| 1. Tambah Cabang               |");
            System.out.println("| 2. Semua Cabang                |");
            System.out.println("| 3. Cabang Berdasarkan ID       |");
            System.out.println("| 4. Update Cabang               |");
            System.out.println("| 5. Hapus Cabang                |");
            System.out.println("| 6. Kembali                     |");
            System.out.println(" ________________________________ ");

            System.out.println("Pilih Angka (1-6)");
            System.out.println("\n");

            System.out.print("Masukkan Pilihan : ");
            int preferred = scanner.nextInt();
            scanner.nextLine();

            switch (preferred){
                case 1 -> cabangController.tambahCabang();
                case 2 -> cabangController.semuaCabang();
                case 3 -> cabangController.cabangBerdasarkanID();
                case 4 -> cabangController.updateCabang();
                case 5 -> cabangController.hapusCabang();
                case 6 -> exit = true;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
    }

    public void showMenuTransaksi() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {

            System.out.println(" ___________________________________ ");
            System.out.println("|          Menu Transaksi :         |");
            System.out.println("| 1. Lakukan Transaksi              |");
            System.out.println("| 2. Semua Transaksi                |");
            System.out.println("| 3. Informasi Semua Transaksi      |");
            System.out.println("| 4. Semua Rekap By Tipe Transaksi  |");
            System.out.println("| 5. Menghapus Semua Transaksi      |");
            System.out.println("| 6. Kembali                        |");
            System.out.println(" ___________________________________ ");

            System.out.println("Pilih Angka (1-5)");
            System.out.println("\n");

            System.out.print("Masukkan Pilihan : ");
            int preferred = scanner.nextInt();
            scanner.nextLine();

            switch (preferred) {
                case 1 -> transaksiController.tambahTransaksi();
                case 2 -> transaksiController.semuaTransaksi();
                case 3 -> transaksiController.getAllTransaksiInfo();
                case 4 -> transaksiController.getAllRekapByTipeTransaksi();
                case 5 -> transaksiController.hapusSemuaTransaksi();
                case 6 -> exit = true;
                default -> System.out.println("Pilihan tidak valid.");
            }
        }
        System.out.println("Debug: Menu ditampilkan.");

    }
}
