package submission.project.core.services.serviceImpl;

import submission.project.core.models.entities.Produk;
import submission.project.core.models.repositories.repositoryImpl.ProdukRepositoryImpl;
import submission.project.core.services.service.ProdukService;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;

public class ProdukServiceImpl implements ProdukService {

    private final ProdukRepositoryImpl produkRepository;

    public ProdukServiceImpl(ProdukRepositoryImpl produkRepository) {
        this.produkRepository = produkRepository;
    }

    @Override
    public void tambahProduk(Produk produk) throws SQLException {

        if (produk.getId() == null || produk.getId().isEmpty() || produk.getId().isBlank()){
            throw new IllegalArgumentException("ID Produk tidak boleh kosong");
        } else if (produk.getIdCabang() == null || produk.getIdCabang().isEmpty() || produk.getIdCabang().isBlank()) {
            throw new IllegalArgumentException("ID Cabang tidak boleh kosong");
        } else if (produk.getNamaProduk() == null || produk.getNamaProduk().isEmpty() || produk.getNamaProduk().isBlank()){
            throw new IllegalArgumentException("Nama Produk tidak boleh kosong");
        } else if (produk.getJumlahProduk() <= 0){
            throw new IllegalArgumentException("Jumlah Produk tidak boleh kosong");
        } else if (produk.getHarga() <= 0) {
            throw new IllegalArgumentException("Harga Produk tidak boleh kosong");
        }

        produkRepository.add(produk);
    }

    @Override
    public List<Produk> semuaProduk() throws SQLException {
        return produkRepository.getAll();
    }

    @Override
    public Optional<Produk> produkById(String id) throws SQLException {
        return produkRepository.getById(id);
    }

    @Override
    public void updateProduk(Produk produk) throws SQLException {

        if (produk.getId() == null || produk.getId().isEmpty() || produk.getId().isBlank()){
            throw new IllegalArgumentException("ID Produk tidak boleh kosong");
        } else if (produk.getIdCabang() == null || produk.getIdCabang().isEmpty() || produk.getIdCabang().isBlank()) {
            throw new IllegalArgumentException("ID Cabang tidak boleh kosong");
        } else if (produk.getNamaProduk() == null || produk.getNamaProduk().isEmpty() || produk.getNamaProduk().isBlank()){
            throw new IllegalArgumentException("Nama Produk tidak boleh kosong");
        } else if (produk.getJumlahProduk() <= 0){
            throw new IllegalArgumentException("Jumlah Produk tidak boleh kosong");
        } else if (produk.getHarga() <= 0) {
            throw new IllegalArgumentException("Harga Produk tidak boleh kosong");
        }

        Optional<Produk> produkExist = produkRepository.getById(produk.getId());
        if (produkExist.isPresent()){
            produkRepository.update(produk);
        } else {
            throw new IllegalArgumentException("Produk dengan ID tersebut tidak di temukan");
        }

    }

    @Override
    public void deleteProduk(String id) throws SQLException {
        if (id == null || id.isEmpty() || id.isBlank()){
            throw new IllegalArgumentException("ID Produk tidak boleh kosong");
        }

        Optional<Produk> produkExist = produkRepository.getById(id);
        if (produkExist.isPresent()){
            produkRepository.delete(id);
        } else {
            throw new IllegalArgumentException("Produk dengan ID tersebut tidak di temukan");
        }

    }
}
