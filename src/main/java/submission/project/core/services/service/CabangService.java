package submission.project.core.services.service;

import submission.project.core.models.entities.Cabang;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CabangService {

    void tambahCabang(Cabang cabang) throws SQLException;
    List<Cabang> semuaCabang() throws SQLException;
    Optional<Cabang> cabangByID(String id) throws  SQLException;
    void updateCabang(Cabang cabang) throws SQLException;
    void deleteCabang(String id) throws SQLException;
}
