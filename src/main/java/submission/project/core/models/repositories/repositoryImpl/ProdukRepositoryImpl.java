package submission.project.core.models.repositories.repositoryImpl;

import submission.project.core.models.entities.Produk;
import submission.project.core.models.repositories.repository.ProdukRepository;
import submission.project.core.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdukRepositoryImpl implements ProdukRepository {

    @Override
    public void add(Produk produk) throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "INSERT INTO produk (id, id_cabang, nama_produk, jumlah, harga) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, produk.getId());
            preparedStatement.setString(2, produk.getIdCabang());
            preparedStatement.setString(3, produk.getNamaProduk());
            preparedStatement.setInt(4, produk.getJumlahProduk());
            preparedStatement.setDouble(5, produk.getHarga());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Produk Berhasil Di Tambahkan");
            } else {
                System.out.println("Gagal Menambah Produk");
            }
        }

        connection.close();
    }

    @Override
    public List<Produk> getAll() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        List<Produk> produks = new ArrayList<>();
        String sql = "SELECT * FROM produk";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Produk produk = new Produk();
                produk.setId(resultSet.getString("id"));
                produk.setIdCabang(resultSet.getString("id_cabang"));
                produk.setNamaProduk(resultSet.getString("nama_produk"));
                produk.setJumlahProduk(resultSet.getInt("jumlah"));
                produk.setHarga(resultSet.getDouble("harga"));
                produks.add(produk);
            }
            resultSet.close();
        }

        connection.close();
        return produks;
    }

    @Override
    public Optional<Produk> getById(String id) throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "SELECT * FROM produk WHERE id = ?";
        Produk produk = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    produk = new Produk();
                    produk.setId(resultSet.getString("id"));
                    produk.setIdCabang(resultSet.getString("id_cabang"));
                    produk.setNamaProduk(resultSet.getString("nama_produk"));
                    produk.setJumlahProduk(resultSet.getInt("jumlah"));
                    produk.setHarga(resultSet.getDouble("harga"));
                }
            }
        }

        connection.close();
        return Optional.ofNullable(produk);
    }

    @Override
    public void update(Produk produk) throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "UPDATE produk SET id_cabang = ?, nama_produk = ?, jumlah = ?, harga = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, produk.getIdCabang());
            preparedStatement.setString(2, produk.getNamaProduk());
            preparedStatement.setInt(3, produk.getJumlahProduk());
            preparedStatement.setDouble(4, produk.getHarga());
            preparedStatement.setString(5, produk.getId());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0){
                System.out.println("Produk berhasil di perbarui");
            } else {
                System.out.println("Produk gagal di perbarui");
            }
        }

        connection.close();
    }

    @Override
    public void delete(String id) throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "DELETE FROM produk WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected > 0){
                System.out.println("Produk dengan ID " + id + " berhasil di hapus");
            } else {
                System.out.println("Produk gagal di hapus");
            }
        }

        connection.close();
    }
}
