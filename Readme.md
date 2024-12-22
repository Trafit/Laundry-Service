# Sistem Manajemen Laundry (Washiland)

## Deskripsi Aplikasi
Aplikasi Sistem Manajemen Laundry (Washiland) adalah sebuah program berbasis Java dengan antarmuka grafis (GUI) yang dirancang untuk membantu pengelolaan usaha laundry. Aplikasi ini memungkinkan pengguna untuk memasukkan informasi pelanggan, menghitung harga total berdasarkan berat dan jenis laundry, memberikan diskon, serta menyimpan data dalam tabel. Pengguna dapat menyimpan, mengedit, menghapus, dan mereset catatan laundry. Aplikasi ini juga menghasilkan file HTML yang berisi semua bukti laundry dan menyertakan gambar untuk setiap catatan.

## Fitur Utama
1. **Input Data Pelanggan**
    - Nama pelanggan
    - Jenis laundry (Clothes/Bedding/Others)
    - Berat cucian (dalam kilogram)
    - Diskon (dalam persen)
    - Path gambar (opsional)

2. **Perhitungan Otomatis**
    - Kalkulasi total biaya berdasarkan jenis dan berat cucian
    - Perhitungan estimasi waktu pengerjaan
    - Penerapan diskon (termasuk kode diskon spesial "hahaha" untuk 10%)

3. **Manajemen Data**
    - Penyimpanan data transaksi dalam tabel
    - Fitur edit data transaksi
    - Fitur hapus data transaksi
    - Reset form input

4. **Generasi Bukti Transaksi**
    - Pembuatan receipt dalam format HTML
    - Penyimpanan gambar terkait (jika ada)
    - Tampilan rapi dengan format terstruktur

## Requirement

- **Java 8 atau lebih tinggi**
- **Library Swing**: Untuk antarmuka pengguna grafis.
- **JDK**: Diperlukan untuk menjalankan program Java.

## Instalasi

Untuk menjalankan Aplikasi Layanan Laundry:
1. Pastikan Java 8 atau lebih tinggi sudah terinstal.
2. Unduh kode sumber (`Laundry.java`).
3. Kompilasi file Java menggunakan IDE atau command line:
    ```bash
    javac Laundry.java
    ```
4. Jalankan program:
    ```bash
    java Laundry
    ```

## Cara Penggunaan
1. Jalankan aplikasi
2. Isi form dengan data pelanggan
3. Klik "Calculate Total Price" untuk menghitung biaya
4. Gunakan tombol Save/Edit/Delete sesuai kebutuhan
5. Cek file receipt.html untuk melihat bukti transaksi

## Spesifikasi Teknis

### Harga per Kilogram
- Clothes (Pakaian): Rp 5.000/kg
- Bedding (Sprei/Selimut): Rp 8.000/kg
- Others (Lainnya): Rp 6.000/kg

### Komponen GUI
1. **Panel Utama**
    - Warna latar: RGB(240, 248, 255)
    - Ukuran frame: 800x900 piksel

2. **Form Panel**
    - Warna latar: RGB(224, 255, 255)
    - Ukuran: 800x450 piksel
    - Border dengan judul "Laundry Information"

3. **Tabel Data**
    - Header berwarna: RGB(0, 102, 204)
    - Font header: Arial Bold 14pt
    - Font isi: Arial Regular 14pt
    - Tinggi baris: 25 piksel

### Method Utama
1. `main(String[] args)`
    - Method utama untuk menjalankan aplikasi
    - Inisialisasi komponen GUI
    - Setup event listener

2. `calculateTotalPrice(String laundryType, double weight)`
    - Menghitung total harga berdasarkan jenis dan berat cucian
    - Parameter: tipe laundry dan berat
    - Return: total harga

3. `getPricePerKilogram(String laundryType)`
    - Menentukan harga per kilogram berdasarkan jenis laundry
    - Parameter: tipe laundry
    - Return: harga per kilogram

4. `saveToFile(DefaultTableModel tableModel)`
    - Menyimpan data transaksi ke file HTML
    - Membuat format receipt yang terstruktur
    - Menyertakan gambar jika tersedia

## Fungsi Tombol
1. **Calculate Total Price**
    - Menghitung total biaya
    - Validasi input (nama dan berat harus diisi)
    - Menampilkan pesan error jika input tidak valid

2. **Reset**
    - Mengosongkan semua field input
    - Mereset label total harga dan estimasi waktu

3. **Save**
    - Menyimpan transaksi ke tabel
    - Membuat receipt HTML
    - Validasi perhitungan harus dilakukan sebelum menyimpan

4. **Delete**
    - Menghapus baris terpilih dari tabel
    - Memperbarui file receipt

5. **Edit**
    - Memuat data dari baris terpilih ke form
    - Menghapus baris yang akan diedit
    - Memungkinkan modifikasi data

## Validasi Input
1. **Nama Pelanggan**
    - Tidak boleh kosong
    - Dilakukan trim untuk menghilangkan spasi di awal dan akhir

2. **Berat Cucian**
    - Harus berupa angka positif
    - Tidak boleh kosong
    - Format: desimal dengan pemisah titik

3. **Diskon**
    - Opsional
    - Harus berupa angka positif jika diisi
    - Kode spesial "hahaha" untuk diskon 10%

## Format File Output
File receipt.html berisi:
- Header dengan judul "Laundry Receipts"
- Informasi untuk setiap transaksi:
    - Nama pelanggan
    - Jenis laundry
    - Berat cucian
    - Total harga
    - Estimasi waktu
    - Gambar (jika ada)
- Pemisah antar transaksi dengan garis horizontal

## Keunggulan Program
1. Antarmuka yang user-friendly dengan warna yang menarik
2. Validasi input yang komprehensif
3. Penyimpanan data dalam format HTML yang rapi
4. Fitur pengelolaan data yang lengkap
5. Perhitungan otomatis dengan dukungan diskon
6. Mendukung penyimpanan gambar terkait transaksi