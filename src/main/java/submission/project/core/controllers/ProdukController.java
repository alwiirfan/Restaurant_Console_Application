package submission.project.core.controllers;

import submission.project.core.helpers.HandleExit;
import submission.project.core.models.entities.Produk;
import submission.project.core.services.service.ProdukService;
import submission.project.core.services.serviceImpl.ProdukServiceImpl;
import submission.project.core.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ProdukController {

    private final ProdukServiceImpl produkService;

    public ProdukController(ProdukServiceImpl produkService) {
        this.produkService = produkService;
    }

    public void tambahProduk() throws SQLException{

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Produk: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan ID Cabang: ");
        String idCabang = scanner.nextLine();
        System.out.print("Masukkan Nama Produk: ");
        String namaProduk = scanner.nextLine();
        System.out.print("Masukkan Jumlah Produk: ");
        int jumlahProduk = scanner.nextInt();
        System.out.print("Masukkan Harga Produk: ");
        double hargaProduk = scanner.nextDouble();

        scanner.nextLine();

        Produk produk = new Produk(id ,idCabang, namaProduk, jumlahProduk, hargaProduk);
        produkService.tambahProduk(produk);

        System.out.println("\n");

        boolean shouldExist = HandleExit.promptToExit(scanner);
        if (!shouldExist){
            System.exit(0);
        }
    }

    public void semuaProduk() throws SQLException{

        Scanner scanner = new Scanner(System.in);

        System.out.println("Daftar Produk:");
        produkService.semuaProduk().forEach(
                produk -> {
                    System.out.println(" ___________________________________ ");
                    System.out.println("ID: " + produk.getId());
                    System.out.println("ID Cabang: " + produk.getIdCabang());
                    System.out.println("Nama Produk: " + produk.getNamaProduk());
                    System.out.println("Jumlah Produk: " + produk.getJumlahProduk());
                    System.out.println("Harga Produk: " + produk.getHarga());
                    System.out.println(" ___________________________________ ");
                }
        );

        System.out.println("\n");

        boolean shouldExist = HandleExit.promptToExit(scanner);
        if (!shouldExist){
            System.exit(0);
        }
    }

    public void produkBerdasarkanID() throws SQLException{

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Produk: ");
        String id = scanner.nextLine();

        Optional<Produk> produk = produkService.produkById(id);
        if (produk.isPresent()){
            System.out.println(" ___________________________________ ");
            System.out.println("ID: " + produk.get().getId());
            System.out.println("ID Cabang: " + produk.get().getIdCabang());
            System.out.println("Nama Produk: " + produk.get().getNamaProduk());
            System.out.println("Jumlah Produk: " + produk.get().getJumlahProduk());
            System.out.println("Harga Produk: " + produk.get().getHarga());
            System.out.println(" ___________________________________ ");
        }

        System.out.println("\n");

        boolean shouldExist = HandleExit.promptToExit(scanner);
        if (!shouldExist){
            System.exit(0);
        }
    }

    public void updateProduk() throws SQLException{

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Produk yang ingin di perbarui: ");
        String id = scanner.nextLine();

        Optional<Produk> produkExist = produkService.produkById(id);
        if (produkExist.isPresent()){
            System.out.print("Masukkan ID Cabang Baru: ");
            String idCabangBaru = scanner.nextLine();
            System.out.print("Masukkan Nama Produk Baru: ");
            String namaProdukBaru = scanner.nextLine();
            System.out.print("Masukkan Jumlah Produk Baru: ");
            int jumlahProdukBaru = scanner.nextInt();
            System.out.print("Masukkan Harga Produk Baru: ");
            double hargaProdukBaru = scanner.nextDouble();

            scanner.nextLine();

            Produk produk = new Produk(id, idCabangBaru, namaProdukBaru, jumlahProdukBaru, hargaProdukBaru);
            produkService.updateProduk(produk);
        }

        System.out.println("\n");

        boolean shouldExist = HandleExit.promptToExit(scanner);
        if (!shouldExist){
            System.exit(0);
        }
    }

    public void hapusProduk() throws SQLException{

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Produk yang ingin di hapus: ");
        String id = scanner.nextLine();

        Optional<Produk> produkExist = produkService.produkById(id);
        if (produkExist.isPresent()){
            produkService.deleteProduk(id);
        } else {
            System.out.println("Produk dengan ID " + id + " tidak di temukan");
        }

        System.out.println("\n");

        boolean shouldExist = HandleExit.promptToExit(scanner);
        if (!shouldExist){
            System.exit(0);
        }
    }
}
