package submission.project.core.models.repositories.repository;

import submission.project.core.models.entities.Cabang;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CabangRepository {

    void add(Cabang cabang) throws SQLException;
    List<Cabang> getAll() throws SQLException;
    Optional<Cabang> getById(String id) throws SQLException;
    void update(Cabang cabang) throws SQLException;
    void delete(String id) throws SQLException;

}
