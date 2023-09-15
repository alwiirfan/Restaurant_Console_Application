package submission.project.core.services.serviceImpl;

import submission.project.core.models.entities.Cabang;
import submission.project.core.models.repositories.repositoryImpl.CabangRepositoryImpl;
import submission.project.core.services.service.CabangService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CabangServiceImpl implements CabangService {

    private final CabangRepositoryImpl cabangRepository;

    public CabangServiceImpl(CabangRepositoryImpl cabangRepository) {
        this.cabangRepository = cabangRepository;
    }

    @Override
    public void tambahCabang(Cabang cabang) throws SQLException {

        if (cabang.getId() == null || cabang.getId().isEmpty() || cabang.getId().isBlank()){
            throw new IllegalArgumentException("ID Cabang tidak boleh kosong");
        } else if (cabang.getNamaCabang() == null || cabang.getNamaCabang().isEmpty() || cabang.getNamaCabang().isBlank()) {
            throw new IllegalArgumentException("Nama Cabang tidak boleh kosong");
        }

        cabangRepository.add(cabang);
    }

    @Override
    public List<Cabang> semuaCabang() throws SQLException {
        return cabangRepository.getAll();
    }

    @Override
    public Optional<Cabang> cabangByID(String id) throws SQLException {
        return cabangRepository.getById(id);
    }

    @Override
    public void updateCabang(Cabang cabang) throws SQLException {
        if (cabang.getId() == null || cabang.getId().isEmpty() || cabang.getId().isBlank()) {
            throw new IllegalArgumentException("ID Cabang tidak boleh kosong");
        } else if (cabang.getNamaCabang() == null || cabang.getNamaCabang().isEmpty() || cabang.getNamaCabang().isBlank()) {
            throw new IllegalArgumentException("Nama Cabang tidak boleh kosong");
        }

        Optional<Cabang> cabangExist = cabangRepository.getById(cabang.getId());
        if (cabangExist.isPresent()){
            cabangRepository.update(cabang);
        } else {
            throw new IllegalArgumentException("Cabang dengan ID tersebut tidak di temukan");
        }
    }

    @Override
    public void deleteCabang(String id) throws SQLException {
        if (id == null || id.isEmpty() || id.isBlank()) {
            throw new IllegalArgumentException("ID Cabang tidak boleh kosong");
        }

        Optional<Cabang> existingCabang = cabangRepository.getById(id);
        if (existingCabang.isPresent()) {
            cabangRepository.delete(id);
        } else {
            throw new IllegalArgumentException("Cabang dengan ID tersebut tidak ditemukan");
        }
    }
}
