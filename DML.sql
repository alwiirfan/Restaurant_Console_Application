## PRODUK

# untuk menambahkan produk:
SQL = INSERT INTO produk (id, id_cabang, nama_produk, jumlah, harga) VALUES (?, ?, ?, ?, ?);

# untuk GET ALL produk:
SQL = SELECT * FROM produk;

# untuk GET produk BY ID:
SQL = SELECT * FROM produk WHERE id = ?;

# untuk UPDATE produk:
SQL = = UPDATE produk SET id_cabang = ?, nama_produk = ?, jumlah = ?, harga = ? WHERE id = ?;

# untuk menghapus produk berdasarkan ID:
SQL = DELETE FROM produk WHERE id = ?;


## CABANG

# untuk menambahkan cabang:
SQL = INSERT INTO cabang (id, nama_cabang) VALUES (?, ?);

# untuk GET ALL ccabang:
SQL = SELECT * FROM cabang;

# untuk GET cabang BY ID:
SQL = SELECT * FROM cabang WHERE id = ?;

# untuk UPDATE cabang:
SQL = UPDATE cabang SET nama_cabang = ? WHERE id = ?;

# untuk menghapus cabang berdasarkan ID:
SQL = DELETE FROM cabang WHERE id = ?;


## TRANSAKSI

# untuk melakukan transaksi:
    # untuk GET produk BY ID:
    SQL = SELECT * FROM produk WHERE id = ? FOR UPDATE;
    # untuk UPDATE jumlah produk:
    SQL = UPDATE produk SET jumlah = ? WHERE id = ?;
    # untuk memasukkan data transaksi:
    SQL = INSERT INTO transaksi (no_struk, id_produk, id_cabang, tipe_transaksi, jumlah, total_penjualan, tanggal_transaksi) VALUES (?, ?, ?, ?, ?, ?, ?);

# untuk GET ALL transaksi:
SQL = SELECT * FROM transaksi;

# untuk GET ALL detail transaksi:
SQL = SELECT t.tanggal_transaksi, t.no_struk, t.tipe_transaksi, t.jumlah, t.total_penjualan, c.id AS cabang_id, c.nama_cabang, p.id AS produk_id, p.nama_produk, p.harga
      FROM transaksi AS t
      JOIN cabang AS c ON t.id_cabang = c.id
      JOIN produk AS p ON t.id_produk = p.id;

# untuk menghapus semua transaksi:
SQL = DELETE FROM transaksi;