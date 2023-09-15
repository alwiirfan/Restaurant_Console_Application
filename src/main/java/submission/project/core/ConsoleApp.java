package submission.project.core;

import submission.project.core.controllers.CustomerController;
import submission.project.core.util.CustomerUtil;

import java.sql.SQLException;
import java.util.Scanner;

public class ConsoleApp {

    private static final CustomerController customerUtil = CustomerUtil.getCustomerController();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CustomerController customerController = customerUtil;

        System.out.println("Welcome to our Restaurant");
        boolean exit = false;

        while (!exit){
            System.out.println(" ____________________________ ");
            System.out.println("|         Pilih Menu :       |");
            System.out.println("|   1. PRODUK                |");
            System.out.println("|   2. CABANG                |");
            System.out.println("|   3. TRANSAKSI             |");
            System.out.println("|   4. KELUAR                |");
            System.out.println(" ____________________________ ");

            System.out.println("Pilih Angka (1-4)");
            System.out.println("\n");

            System.out.print("Masukkan Pilihan : ");
            int preferred = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (preferred) {
                    case 1 -> customerController.showMenuProduk();
                    case 2 -> customerController.showMenuCabang();
                    case 3 -> customerController.showMenuTransaksi();
                    case 4 -> exit = true;
                    default -> System.out.println("Pilihan Tidak Valid.");
                }
            } catch (SQLException exception){
                exception.printStackTrace();
            }
        }

        scanner.close();
    }
}
