package submission.project.core.models.repositories.repositoryImpl;

import submission.project.core.models.entities.Cabang;
import submission.project.core.models.repositories.repository.CabangRepository;
import submission.project.core.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CabangRepositoryImpl implements CabangRepository {

    @Override
    public void add(Cabang cabang) throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO cabang (id, nama_cabang) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cabang.getId());
            preparedStatement.setString(2, cabang.getNamaCabang());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0){
                System.out.println("Cabang berhasi di tambahkan");
            } else {
                System.out.println("Cabang gagal di tambahkan");
            }
        }

        connection.close();
    }

    @Override
    public List<Cabang> getAll() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        List<Cabang> cabangs = new ArrayList<>();
        String sql = "SELECT * FROM cabang";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Cabang cabang = new Cabang();
                cabang.setId(resultSet.getString("id"));
                cabang.setNamaCabang(resultSet.getString("nama_cabang"));
                cabangs.add(cabang);
            }
            resultSet.close();
        }

        connection.close();
        return cabangs;
    }

    @Override
    public Optional<Cabang> getById(String id) throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "SELECT * FROM cabang WHERE id = ?";
        Cabang cabang = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    cabang = new Cabang();
                    cabang.setId(resultSet.getString("id"));
                    cabang.setNamaCabang(resultSet.getString("nama_cabang"));
                }
            }
        }

        connection.close();
        return Optional.ofNullable(cabang);
    }

    @Override
    public void update(Cabang cabang) throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "UPDATE cabang SET nama_cabang = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cabang.getNamaCabang());
            preparedStatement.setString(2, cabang.getId());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0){
                System.out.println("Cabang berhasil di perbarui");
            } else {
                System.out.println("Cabang gagal di perbarui");
            }
        }

        connection.close();
    }

    @Override
    public void delete(String id) throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "DELETE FROM cabang WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0){
                System.out.println("Cabang dengan ID " + id + " berhasil di hapus");
            } else {
                System.out.println("Cabang gagal di hapus");
            }
        }

        connection.close();
    }
}
