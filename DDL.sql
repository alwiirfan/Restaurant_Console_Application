# untuk membuat DATABASE:
CREATE DATABASE warung_makan;

# untuk membuat TABLE produk:
CREATE TABLE produk(
    id VARCHAR(10) NOT NULL,
    id_cabang VARCHAR(10) DEFAULT NULL,
    nama_produk VARCHAR(100) NOT NULL,
    jumlah INT NOT NULL,
    harga DECIMAL(10,2) DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_produk_cabang FOREIGN KEY (id_cabang) REFERENCES cabang (id)
);

# untuk membuat TABLE cabang:
CREATE TABLE cabang(
    id VARCHAR(10) NOT NULL,
    nama_cabang VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

# untuk membuat TABLE transaksi:
CREATE TABLE transaksi(
    no_struk VARCHAR(100) NOT NULL,
    id_produk VARCHAR(10) DEFAULT NULL,
    id_cabang VARCHAR(10) DEFAULT NULL,
    tipe_transaksi ENUM ('EAT_IN', 'TAKE_AWAY', 'ONLINE') DEFAULT NULL,
    jumlah INT DEFAULT NULL,
    total_penjualan DECIMAL(10,2) DEFAULT NULL,
    tanggal_transaksi DATE NOT NULL,
    PRIMARY KEY (no_struk),
    CONSTRAINT fk_produk_transaksi FOREIGN KEY (id_produk) REFERENCES produk (id),
    CONSTRAINT fk_cabang_transaksi FOREIGN KEY (id_cabang) REFERENCES cabang (id)
);