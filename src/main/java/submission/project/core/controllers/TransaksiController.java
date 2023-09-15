package submission.project.core.controllers;

import submission.project.core.helpers.HandleExit;
import submission.project.core.models.entities.Transaksi;
import submission.project.core.models.entities.enums.TipeTransaksi;
import submission.project.core.services.serviceImpl.TransaksiServiceImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class TransaksiController {

    private final TransaksiServiceImpl transaksiService;

    public TransaksiController(TransaksiServiceImpl transaksiService) {
        this.transaksiService = transaksiService;
    }

    void tambahTransaksi() {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){

            System.out.println(" ________________________________ ");
            System.out.println("|     Pilih Tipe Transaksi:      |");
            System.out.println("| 1. EAT_IN                      |");
            System.out.println("| 2. TAKE_AWAY                   |");
            System.out.println("| 3. ONLINE                      |");
            System.out.println("| 4. EXIT                        |");
            System.out.println(" ________________________________ ");
            System.out.print("Masukkan Pilihan (1-4): ");
            int pilihanTipeTransaksi = scanner.nextInt();
            scanner.nextLine();

            if (pilihanTipeTransaksi == 4){
                System.out.println("\n");

                boolean shouldExist = HandleExit.promptToExit(scanner);
                if (!shouldExist){
                    System.exit(0);
                }
            }

            TipeTransaksi tipeTransaksi = null;

            switch (pilihanTipeTransaksi){
                case 1 -> tipeTransaksi = TipeTransaksi.EAT_IN;
                case 2 -> tipeTransaksi = TipeTransaksi.TAKE_AWAY;
                case 3 -> tipeTransaksi = TipeTransaksi.ONLINE;
                default -> System.out.println("Pilihan Tipe Transaksi tidak valid");
            }

            String noStruk = UUID.randomUUID().toString();

            System.out.print("Masukkan ID Produk: ");
            String idProduk = scanner.nextLine();

            System.out.print("Masukkan Jumlah Produk: ");
            int jumlahProduk = scanner.nextInt();

            Transaksi transaksi = new Transaksi();
            transaksi.setNoStruk(noStruk);
            transaksi.setIdProduk(idProduk);
            transaksi.setTipeTransaksi(tipeTransaksi);
            transaksi.setJumlahProduk(jumlahProduk);
            transaksi.setTanggalTransaksi(new Date());

            try {
                transaksiService.tambahTransaksi(transaksi);
                System.out.println("Transaksi berhasi di tambahkan");

                System.out.println("\n");

                System.out.println(" ________________________________________________ ");
                System.out.println("Data Transaksi: ");
                System.out.println("No Struk: " + transaksi.getNoStruk());
                System.out.println("ID Produk: " + transaksi.getIdProduk());
                System.out.println("Tipe Transaksi: " + transaksi.getTipeTransaksi());
                System.out.println("Jumlah Produk: " + transaksi.getJumlahProduk());
                System.out.println("Total Harga Produk: " + transaksi.getTotalPenjualan());
                System.out.println("Tanggal Transaksi: " + transaksi.getTanggalTransaksi());
                System.out.println(" ________________________________________________ ");
            } catch (SQLException exception){
                System.out.println("Gagal menambah Transakasi: " + exception.getMessage());
            }

        }

    }

    public void semuaTransaksi() throws SQLException{

        Scanner scanner = new Scanner(System.in);

        System.out.println("Daftar Transaksi: ");
        transaksiService.semuaTransaksi().forEach(
                transaksi -> {
                    System.out.println(" ________________________________________________ ");
                    System.out.println("No Struk: " + transaksi.getNoStruk());
                    System.out.println("ID Produk: " + transaksi.getIdProduk());
                    System.out.println("Tipe Transaksi: " + transaksi.getTipeTransaksi());
                    System.out.println("Jumlah Produk: " + transaksi.getJumlahProduk());
                    System.out.println("Total Harga Produk: " + transaksi.getTotalPenjualan());
                    System.out.println("Tanggal Transaksi: " + transaksi.getTanggalTransaksi());
                    System.out.println(" ________________________________________________ ");
                }
        );

        System.out.println("\n");

        boolean shouldExist = HandleExit.promptToExit(scanner);
        if (!shouldExist){
            System.exit(0);
        }

    }

    public void rekapByTipeTransaksi(){

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){

            System.out.println(" ________________________________ ");
            System.out.println("|     Pilih Tipe Transaksi:      |");
            System.out.println("| 1. EAT_IN                      |");
            System.out.println("| 2. TAKE_AWAY                   |");
            System.out.println("| 3. ONLINE                      |");
            System.out.println("| 4. EXIT                        |");
            System.out.println(" ________________________________ ");
            System.out.print("Masukkan Pilihan (1-4): ");
            int pilihanTipeTransaksi = scanner.nextInt();
            scanner.nextLine();

            if (pilihanTipeTransaksi == 4){
                System.out.println("\n");

                boolean shouldExist = HandleExit.promptToExit(scanner);
                if (!shouldExist){
                    System.exit(0);
                }
            }

            TipeTransaksi tipeTransaksi = null;

            switch (pilihanTipeTransaksi){
                case 1 -> tipeTransaksi = TipeTransaksi.EAT_IN;
                case 2 -> tipeTransaksi = TipeTransaksi.TAKE_AWAY;
                case 3 -> tipeTransaksi = TipeTransaksi.ONLINE;
                default -> System.out.println("Pilihan Tipe Transaksi tidak valid");
            }

            if (tipeTransaksi != null){
                try {
                    Map<String, Double> rekap = transaksiService.rekapByTipeTransaksi();
                    if (rekap.isEmpty()){
                        System.out.println("Belum ada data rekap total penjualan untuk tipe transaksi " + tipeTransaksi);
                    } else {
                        System.out.println("Rekap total penjualan untuk tipe transaksi " + tipeTransaksi + ":");
                        for (Map.Entry<String, Double> entry : rekap.entrySet()){
                            String tipeTransaksiKey = entry.getKey();
                            Double totalPenjualan = entry.getValue();
                            System.out.println(tipeTransaksiKey + ": " + totalPenjualan);
                        }
                    }
                } catch (SQLException exception){
                    System.out.println("Gagal mengambil rekap total penjualan: " + exception.getMessage());
                }
            }

        }

        System.out.println("\n");

        boolean shouldExist = HandleExit.promptToExit(scanner);
        if (!shouldExist){
            System.exit(0);
        }

    }

    public void getAllRekapByTipeTransaksi(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Daftar Rekap Berdasarkan Tipe Transaksi: ");
        try {
            Map<String, Double> rekap = transaksiService.rekapByTipeTransaksi();
            if (rekap.isEmpty()){
                System.out.println("Belum ada rekap total penjualan berdasarkan tipe transaksi");
            } else {
                for (Map.Entry<String, Double> entry : rekap.entrySet()){
                    String tipeTransaksi = entry.getKey();
                    Double totalPenjualan = entry.getValue();
                    System.out.println("______________________________");
                    System.out.println(tipeTransaksi + ": " + totalPenjualan);
                    System.out.println("______________________________");
                }
            }
        } catch (SQLException exception){
            System.out.println("Gagal mengambil rekap total penjualan: " + exception.getMessage());
        }

        System.out.println("\n");

        boolean shouldExist = HandleExit.promptToExit(scanner);
        if (!shouldExist){
            System.exit(0);
        }

    }
}
