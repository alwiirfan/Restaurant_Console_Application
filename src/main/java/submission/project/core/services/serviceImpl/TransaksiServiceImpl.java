package submission.project.core.services.serviceImpl;

import submission.project.core.models.entities.Cabang;
import submission.project.core.models.entities.Produk;
import submission.project.core.models.entities.Transaksi;
import submission.project.core.models.repositories.repositoryImpl.CabangRepositoryImpl;
import submission.project.core.models.repositories.repositoryImpl.ProdukRepositoryImpl;
import submission.project.core.models.repositories.repositoryImpl.TransaksiRepositoryImpl;
import submission.project.core.services.service.TransaksiService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TransaksiServiceImpl implements TransaksiService {

    private final TransaksiRepositoryImpl transaksiRepository;
    private final ProdukRepositoryImpl produkRepository;
    private final CabangRepositoryImpl cabangRepository;

    public TransaksiServiceImpl(TransaksiRepositoryImpl transaksiRepository, ProdukRepositoryImpl produkRepository, CabangRepositoryImpl cabangRepository) {
        this.transaksiRepository = transaksiRepository;
        this.produkRepository = produkRepository;
        this.cabangRepository = cabangRepository;
    }

    @Override
    public void tambahTransaksi(Transaksi transaksi) throws SQLException {

        if (transaksi.getIdProduk() == null || transaksi.getIdProduk().isEmpty() || transaksi.getIdProduk().isBlank()){
            throw new IllegalArgumentException("ID Product tidak boleh kosong");
        } else if (transaksi.getIdCabang() == null || transaksi.getIdCabang().isEmpty() || transaksi.getIdCabang().isBlank()) {
            throw new IllegalArgumentException("ID Cabang tidak boleh kosong");
        } else if (transaksi.getTipeTransaksi() == null) {
            throw new IllegalArgumentException("Tipe Transaksi tidak boleh kosong");
        } else if (transaksi.getJumlahProduk() <= 0) {
            throw new IllegalArgumentException("Jumlah Produk tidak boleh kosong");
        }

        Optional<Produk> optionalProduk = produkRepository.getById(transaksi.getIdProduk());
        if (optionalProduk.isPresent()){
            Produk produk = optionalProduk.get();
            double hargaProduk = produk.getHarga();
            int jumlahProduk = transaksi.getJumlahProduk();
            double totalPenjualan = hargaProduk * jumlahProduk;

            transaksi.setTotalPenjualan(totalPenjualan);

            transaksiRepository.add(transaksi);
        } else {
            throw new SQLException("Produk tidak di temukan.");
        }

        Optional<Cabang> optionalCabang = cabangRepository.getById(transaksi.getIdCabang());
        if (optionalCabang.isPresent()){

            Cabang cabang = optionalCabang.get();
            String idCabang = cabang.getId();

            transaksi.setIdCabang(idCabang);

            transaksiRepository.add(transaksi);
        } else {
            throw new SQLException("Cabang tidak di temukan");
        }
    }

    @Override
    public List<Transaksi> semuaTransaksi() throws SQLException {
        return transaksiRepository.getAllTransaksi();
    }

    @Override
    public List<Transaksi> getAllTransaksiInfo() throws SQLException {
        return transaksiRepository.getAllTransaksiInfo();
    }

    @Override
    public Map<String, Double> rekapByTipeTransaksi() throws SQLException {
        return transaksiRepository.rekapByTipeTransaksi();
    }

    @Override
    public void hapusSemuaTransaksi() throws SQLException {
        transaksiRepository.hapusSemuaTransakasi();
    }
}
