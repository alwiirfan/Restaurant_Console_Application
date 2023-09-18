package submission.project.core.models.repositories.repositoryImpl;

import submission.project.core.models.entities.Cabang;
import submission.project.core.models.entities.Produk;
import submission.project.core.models.entities.Transaksi;
import submission.project.core.models.entities.enums.TipeTransaksi;
import submission.project.core.models.repositories.repository.TransaksiRepository;
import submission.project.core.util.ConnectionUtil;

import java.sql.*;
import java.sql.Date;
import java.util.*;


public class TransaksiRepositoryImpl implements TransaksiRepository {

    @Override
    public void add(Transaksi transaksi) throws SQLException {

        Connection connection = ConnectionUtil.getDataSource().getConnection();
        connection.setAutoCommit(false);

        String sqlSelectProduk = "SELECT * FROM produk WHERE id = ? FOR UPDATE";

        try (PreparedStatement selectProdukStatement = connection.prepareStatement(sqlSelectProduk)) {
            selectProdukStatement.setString(1, transaksi.getIdProduk());

            try (ResultSet resultSet = selectProdukStatement.executeQuery()) {
                if (resultSet.next()){
                    Produk produk = new Produk();
                    produk.setId(resultSet.getString("id"));
                    produk.setIdCabang(resultSet.getString("id_cabang"));
                    produk.setNamaProduk(resultSet.getString("nama_produk"));
                    produk.setJumlahProduk(resultSet.getInt("jumlah"));
                    produk.setHarga(resultSet.getDouble("harga"));

                    int stokSekarang = produk.getJumlahProduk();
                    int jumlahProdukDiBeli = transaksi.getJumlahProduk();

                    if (stokSekarang >= jumlahProdukDiBeli){

                        int stokBaru = stokSekarang - jumlahProdukDiBeli;

                        double totalPenjualan = jumlahProdukDiBeli * produk.getHarga();

                        String sqlUpdateProduk = "UPDATE produk SET jumlah = ? WHERE id = ?";
                        try (PreparedStatement updateProdukStatement = connection.prepareStatement(sqlUpdateProduk)) {
                            updateProdukStatement.setInt(1, stokBaru);
                            updateProdukStatement.setString(2, transaksi.getIdProduk());
                            updateProdukStatement.executeUpdate();
                        }

                        String sqlInsertTransaksi = "INSERT INTO transaksi (no_struk, id_produk, id_cabang, tipe_transaksi, jumlah, total_penjualan, tanggal_transaksi) VALUES (?, ?, ?, ?, ?, ?, ?)";

                        try (PreparedStatement insertTransaksiStatement = connection.prepareStatement(sqlInsertTransaksi)) {
                            insertTransaksiStatement.setString(1, UUID.randomUUID().toString());
                            insertTransaksiStatement.setString(2, transaksi.getIdProduk());
                            insertTransaksiStatement.setString(3, transaksi.getIdCabang());
                            insertTransaksiStatement.setString(4, transaksi.getTipeTransaksi().toString());
                            insertTransaksiStatement.setInt(5, transaksi.getJumlahProduk());
                            insertTransaksiStatement.setDouble(6, totalPenjualan);
                            insertTransaksiStatement.setDate(7, new Date(transaksi.getTanggalTransaksi().getTime()));
                            insertTransaksiStatement.executeUpdate();
                        }

                        connection.commit();
                    } else {
                        connection.rollback();
                        throw new SQLException("Stok produk tidak mencukupi.");
                    }
                } else {
                    throw new SQLException("Produk tidak di temukan.");
                }
            }
        }

    }

    @Override
    public List<Transaksi> getAllTransaksi() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        List<Transaksi> transaksiList = new ArrayList<>();

        String sql = "SELECT * FROM transaksi";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Transaksi transaksi = new Transaksi();
                transaksi.setNoStruk(resultSet.getString("no_struk"));
                transaksi.setIdProduk(resultSet.getString("id_produk"));
                transaksi.setIdCabang(resultSet.getString("id_cabang"));

                // mengambil tipe transaksi
                String tipeTransaksiStr = resultSet.getString("tipe_transaksi");
                TipeTransaksi tipeTransaksi = TipeTransaksi.valueOf(tipeTransaksiStr);
                transaksi.setTipeTransaksi(tipeTransaksi);

                transaksi.setJumlahProduk(resultSet.getInt("jumlah"));
                transaksi.setTotalPenjualan(resultSet.getDouble("total_penjualan"));
                transaksi.setTanggalTransaksi(resultSet.getDate("tanggal_transaksi"));
                transaksiList.add(transaksi);
            }
            resultSet.close();
        }

        connection.close();
        return transaksiList;
    }

    @Override
    public List<Transaksi> getAllTransaksiInfo() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        List<Transaksi> transaksiList = new ArrayList<>();

        String sql = """
        SELECT t.tanggal_transaksi, t.no_struk, t.tipe_transaksi, t.jumlah, t.total_penjualan, c.id AS cabang_id, c.nama_cabang, p.id AS produk_id, p.nama_produk, p.harga FROM transaksi AS t
        JOIN cabang AS c ON t.id_cabang = c.id
        JOIN produk AS p ON t.id_produk = p.id
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Transaksi transaksi = new Transaksi();
                transaksi.setTanggalTransaksi(resultSet.getDate("tanggal_transaksi"));
                transaksi.setNoStruk(resultSet.getString("no_struk"));

                String tipeTransaksiStr = resultSet.getString("tipe_transaksi");
                TipeTransaksi tipeTransaksi = TipeTransaksi.valueOf(tipeTransaksiStr);
                transaksi.setTipeTransaksi(tipeTransaksi);

                transaksi.setJumlahProduk(resultSet.getInt("jumlah"));
                transaksi.setTotalPenjualan(resultSet.getDouble("total_penjualan"));

                Produk produk = new Produk();
                produk.setId(resultSet.getString("produk_id"));
                produk.setNamaProduk(resultSet.getString("nama_produk"));
                produk.setHarga(resultSet.getDouble("harga"));
                transaksi.setProduk(produk);

                Cabang cabang = new Cabang();
                cabang.setId(resultSet.getString("cabang_id"));
                cabang.setNamaCabang(resultSet.getString("nama_cabang"));
                transaksi.setCabang(cabang);

                transaksiList.add(transaksi);
            }
            resultSet.close();
        }

        connection.close();
        return transaksiList;
    }

    @Override
    public Map<String, Double> rekapByTipeTransaksi() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        Map<String, Double> rekap = new HashMap<>();

        String sql = "SELECT tipe_transaksi, SUM(total_penjualan) AS total FROM transaksi GROUP BY tipe_transaksi";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    String tipeTransaksi = resultSet.getString("tipe_transaksi");
                    double totalPenjualan = resultSet.getDouble("total");
                    rekap.put(tipeTransaksi, totalPenjualan);
                }
            }
        }

        return rekap;
    }

    @Override
    public void hapusSemuaTransakasi() throws SQLException {
        Connection connection = ConnectionUtil.getDataSource().getConnection();
        String sql = "DELETE FROM transaksi";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Semua transaksi berhasil dihapus");
        }

        connection.close();
    }
}
