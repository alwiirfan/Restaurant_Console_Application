package submission.project.core.services.service;

import submission.project.core.models.entities.Produk;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProdukService {

    void tambahProduk(Produk produk) throws SQLException;
    List<Produk> semuaProduk() throws SQLException;
    Optional<Produk> produkById(String id) throws SQLException;
    void updateProduk(Produk produk) throws SQLException;
    void deleteProduk(String id) throws SQLException;

}
