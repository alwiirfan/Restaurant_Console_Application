# Final Console Application 
## Mengunduh Repository

Unduh repository ke dalam komputer menggunakan `git clone`. Url repository dapat di lihat pada repository yang di inginkan.

## Cara untuk menjalankan Aplikasi 

* Install semua `Dependency` dan `Plugin` yang ada pada File `pom.xml`. 
* Buatlah `Database`. Lalu isi `Database` dengan `Table` menggunakan perintah yang ada pada File `DDL.sql`.
* Lakukan Set up pada file `application.properties`.

#### Contoh:

```
database.driver=com.mysql.cj.jdbc.Driver
database.url=jdbc:mysql://localhost:3306/nama_database?serverTimezone=Asia/Jakarta
database.username=username
database.password=password
```

* Lakukan Build lalu Compile Package untuk menjadi File `.jar` di dalam Folder `target` menggunakan perintah:
```
mvn clean compile package assembly:single
```

* Lakukan Eksekusi File `.jar` yang ada tambahan `-jar-with-dependencies.jar` di dalam Folder `target` menggunakan perintah: `java -jar target/nama_file-jar-with-dependencies.jar`.

#### Contoh:

```
java -jar target/restaurant-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Cara untuk menambahkan Produk 

* Setelah melakukan eksekusi menggunakan perintah: `java -jar target/restaurant-1.0-SNAPSHOT-jar-with-dependencies.jar`. Lalu pilih `PRODUK`.
* Lanjutkan dengan memilih `Tambah Produk`.
* Pastikan anda melakukan `get all` pada `CABANG` unutuk melihat `CABANG` yang sudah ada.
* Lalu isi data yang perlukan.

#### Contoh:

```
Masukkan ID Produk: 01-001
Masukkan ID Cabang: 0203
Masukkan Nama Produk: Nasi Putih
Masukkan Jumlah Produk: 1
Masukkan Harga: 5000
```

## Cara untuk melakukan Transaksi

* Setelah melakukan eksekusi menggunakan perintah: `java -jar target/restaurant-1.0-SNAPSHOT-jar-with-dependencies.jar`. Lalu pilih `TRANSAKSI`.
* Lanjutkan dengan memilih `Lakukan Transaksi`.
* Pastikan anda melakukan `get all` pada `PRODUK` untuk melihat `PRODUK` yang sudah ada.
* Pastikan anda melakukan `get all` pada `CABANG` untuk melihat `CABANG` yang sudah ada.
* Lalu isi data yang di perlukan.

#### Contoh:

```
Pilih Tipe Transaksi: EAT_IN/TAKE_AWAY/ONLINE
Masukkan ID Produk: 01-001
Masukkan ID Cabang: 0203
Masukkan Jumlah Produk: 4
```

* Pastikan ID `PRODUK` dan ID `CABANG` yang anda masukkan sudah ada di dalam TABLE `PRODUK` dan `CABANG`.