package submission.project.core.models.repositories.repository;

import submission.project.core.models.entities.Transaksi;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TransaksiRepository {

    void add(Transaksi transaksi) throws SQLException;
    List<Transaksi> getAllTransaksi() throws SQLException;
    List<Transaksi> getAllTransaksiInfo() throws SQLException;

    Map<String, Double> rekapByTipeTransaksi() throws SQLException;

}
