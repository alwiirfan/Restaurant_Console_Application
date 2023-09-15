package submission.project.core.controllers;

import submission.project.core.helpers.HandleExit;
import submission.project.core.models.entities.Cabang;
import submission.project.core.services.serviceImpl.CabangServiceImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class CabangController {

    private final CabangServiceImpl cabangService;

    public CabangController(CabangServiceImpl cabangService) {
        this.cabangService = cabangService;
    }

    public void tambahCabang() throws SQLException{

        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Cabang: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Cabang: ");
        String namaCabang = scanner.nextLine();

        Cabang cabang = new Cabang(id, namaCabang);
        cabangService.tambahCabang(cabang);

        System.out.println("\n");

        boolean shouldExit = HandleExit.promptToExit(scanner);
        if (!shouldExit){
            System.exit(0);
        }

    }

    public void semuaCabang() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("Daftar Cabang:");
        cabangService.semuaCabang().forEach(cabang -> {
            System.out.println(" ________________________________ ");
            System.out.println("ID: " + cabang.getId());
            System.out.println("Nama Cabang: " + cabang.getNamaCabang());
            System.out.println(" ________________________________ ");
        });

        System.out.println("\n");

        boolean shouldExit = HandleExit.promptToExit(scanner);
        if (!shouldExit){
            System.exit(0);
        }
    }

    public void cabangBerdasarkanID() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Cabang: ");
        String id = scanner.nextLine();

        Optional<Cabang> cabang = cabangService.cabangByID(id);
        if (cabang.isPresent()){
            System.out.println(" ________________________________ ");
            System.out.println("ID: " + cabang.get().getId());
            System.out.println("Nama Cabang: " + cabang.get().getNamaCabang());
            System.out.println(" ________________________________ ");
        } else {
            System.out.println("Cabang dengan ID tersebut tidak di temukan");
        }

        System.out.println("\n");

        boolean shouldExit = HandleExit.promptToExit(scanner);
        if (!shouldExit){
            System.exit(0);
        }

    }

    public void updateCabang() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Cabang yang ingin di perbarui: ");
        String id = scanner.nextLine();

        Optional<Cabang> cabangExist = cabangService.cabangByID(id);
        if (cabangExist.isPresent()){
            System.out.print("Masukkan Nama Cabang Baru: ");
            String namaCabangBaru = scanner.nextLine();

            Cabang cabang = new Cabang(id, namaCabangBaru);
            cabangService.updateCabang(cabang);
        }

        System.out.println("\n");

        boolean shouldExit = HandleExit.promptToExit(scanner);
        if (!shouldExit){
            System.exit(0);
        }

    }

    public void hapusCabang() throws SQLException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Masukkan ID Cabang yang ingin di hapus: ");
        String id = scanner.nextLine();

        Optional<Cabang> cabangExist = cabangService.cabangByID(id);
        if (cabangExist.isPresent()){
            cabangService.deleteCabang(id);
        } else {
            System.out.println("Cabang dengan ID " +  id + " tidak di temukan");
        }

        System.out.println("\n");

        boolean shouldExit = HandleExit.promptToExit(scanner);
        if (!shouldExit){
            System.exit(0);
        }

    }
}
