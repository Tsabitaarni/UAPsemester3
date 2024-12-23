Deskripsi

Food Ordering App adalah aplikasi desktop berbasis Java Swing yang dirancang untuk mengelola item makanan dan melakukan pemesanan. Aplikasi ini memungkinkan pengguna (admin) untuk:

Masuk dengan kredensial yang telah ditentukan.

Melihat, menambah, mengedit, dan menghapus item makanan.

Melakukan pemesanan makanan.

Menghitung total harga pesanan.

Fitur

Login:

Admin dapat masuk menggunakan kredensial yang telah ditentukan (Username: admin, Password: admin123).

Kredensial yang salah akan menampilkan pesan kesalahan.

Dashboard Admin:

Menampilkan daftar item makanan dalam tabel, termasuk gambar untuk setiap item.

Menyediakan fungsi untuk menambah, mengedit, dan menghapus item makanan.

Memungkinkan pemesanan dengan memilih item makanan dan menentukan jumlahnya.

Menghitung dan menampilkan total harga pesanan.

Manajemen Makanan:

Menambah item makanan baru dengan nama, harga, dan jalur gambar.

Mengedit item makanan yang ada.

Menghapus item makanan yang tidak diperlukan.

Manajemen Pemesanan:

Menambahkan item ke pesanan dengan menentukan jumlah.

Menghitung total harga untuk semua item dalam pesanan.

Image Renderer:

Menampilkan gambar item makanan dalam tabel.

Secara otomatis menyesuaikan ukuran gambar agar sesuai dengan baris tabel.

Teknologi yang Digunakan

Java: Bahasa pemrograman untuk aplikasi ini.

Swing: Untuk desain GUI dan interaksi pengguna.

JTable: Untuk menampilkan data makanan dan pesanan dalam format tabel.

JFileChooser: Untuk memilih dan memuat file gambar.

Instalasi dan Penggunaan

Prasyarat:

Pastikan Java Development Kit (JDK) sudah terinstal.

Gunakan IDE apa pun (misalnya, IntelliJ IDEA, Eclipse, NetBeans) atau editor teks dengan dukungan Java.

Persiapan:

Salin kode sumber ke dalam proyek Java di IDE pilihan Anda.

Kompilasi dan jalankan aplikasi mulai dari kelas FoodOrderingApp.

Kredensial Login:

Username: admin

Password: admin123

Penggunaan:

Setelah berhasil login, Dashboard Admin akan terbuka.

Gunakan tombol untuk mengelola item makanan dan melakukan pemesanan.

Struktur Kode

FoodOrderingApp: Kelas utama yang memulai aplikasi.

LoginFrame: Menangani fungsi login.

AdminDashboard: Menyediakan dashboard utama untuk mengelola item makanan dan pesanan.

FoodItem: Merepresentasikan item makanan dengan nama, harga, dan jalur gambar.

ImageRenderer: Renderer sel tabel kustom untuk menampilkan gambar dalam tabel makanan.

Cara Kerja

Login:

Dimulai dengan LoginFrame.

Memvalidasi kredensial dan membuka AdminDashboard jika berhasil.

Manajemen Makanan:

Tambah: Membuka dialog untuk memasukkan detail makanan dan menyimpan item baru.

Edit: Memodifikasi detail item makanan yang dipilih.

Hapus: Menghapus item makanan yang dipilih dari daftar.

Manajemen Pemesanan:

Pilih item makanan dan tentukan jumlah untuk menambahkannya ke tabel pesanan.

Hitung total harga pesanan menggunakan tombol "Calculate Total".

Tangkapan Layar

Layar Login:

Kolom input untuk username dan password.

Tombol login untuk validasi.

Dashboard Admin:

Dua tabel: satu untuk item makanan dan satu lagi untuk pesanan.

Tombol untuk mengelola item makanan dan pesanan.