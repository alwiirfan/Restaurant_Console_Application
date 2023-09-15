package submission.project.core.models.repositories.repository;

import submission.project.core.models.entities.Produk;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProdukRepository {

    void add(Produk produk) throws SQLException;
    List<Produk> getAll() throws SQLException;
    Optional<Produk> getById(String id) throws SQLException;
    void update(Produk produk) throws SQLException;
    void delete(String id) throws SQLException;
}


