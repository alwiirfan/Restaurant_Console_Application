package submission.project.core.services.service;

import submission.project.core.models.entities.Transaksi;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface TransaksiService {

    void tambahTransaksi(Transaksi transaksi) throws SQLException;
    List<Transaksi> semuaTransaksi() throws SQLException;

    Map<String, Double> rekapByTipeTransaksi() throws SQLException;
}
