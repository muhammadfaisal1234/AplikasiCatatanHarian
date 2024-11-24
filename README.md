# UTSPBO2
 Muhammad Faisal - 2210010112
Aplikasi Catatan Harian
Aplikasi Catatan Harian adalah aplikasi berbasis Java yang memungkinkan pengguna untuk menyimpan, mengedit, menghapus, mengimpor, dan mengekspor catatan harian mereka. Aplikasi ini menyediakan antarmuka yang sederhana dengan fitur-fitur yang memudahkan pengguna dalam mengelola catatan harian mereka.

Keunggulan Aplikasi
Menyimpan Catatan: Pengguna dapat menambahkan catatan harian lengkap dengan judul dan isi.
Mengedit Catatan: Memungkinkan pengguna untuk mengedit catatan yang telah disimpan sebelumnya.
Menghapus Catatan: Pengguna dapat menghapus catatan yang tidak diperlukan lagi.
Mengimpor dan Mengekspor Catatan: Memungkinkan pengguna untuk mengimpor dan mengekspor catatan dari dan ke file teks.
Antarmuka Pengguna Sederhana: Dibangun dengan Java Swing, aplikasi ini memiliki antarmuka pengguna yang mudah digunakan dan intuitif.
Penyimpanan Catatan Persisten: Catatan yang disimpan akan tetap ada meskipun aplikasi ditutup, dan dapat diimpor kembali menggunakan file teks.

Pembuat Aplikasi
Muhammad Faisal - 2210010112 - UTS PBO2

Fitur
Aplikasi Catatan Harian menawarkan fitur-fitur berikut:

Menambah Catatan
Pengguna dapat menambahkan catatan baru dengan memasukkan judul dan isi catatan. Catatan akan disimpan dalam daftar yang dapat dikelola melalui antarmuka aplikasi.

Mengedit Catatan
Pengguna dapat mengedit catatan yang telah dipilih dari daftar catatan yang ada. Isi catatan yang baru dapat disimpan setelah diedit.

Menghapus Catatan
Catatan yang tidak lagi diperlukan dapat dihapus dari daftar catatan.

Mengimpor Catatan
Pengguna dapat mengimpor catatan dari file teks (.txt) yang berisi daftar judul dan isi catatan yang dipisahkan oleh tanda titik dua (":").

Mengekspor Catatan
Aplikasi memungkinkan pengguna untuk mengekspor catatan ke dalam file teks dengan format nama file yang unik berdasarkan timestamp.

Antarmuka Pengguna Sederhana dan Intuitif
Antarmuka pengguna yang menggunakan Java Swing untuk menampilkan catatan dan menyediakan kontrol untuk menambah, mengedit, menghapus, mengimpor, dan mengekspor catatan.

Cara Menjalankan
Clone repositori ini ke komputer Anda atau unduh sebagai file ZIP.
Buka proyek di IDE pilihan Anda (misalnya, IntelliJ atau Eclipse).
Pastikan JDK sudah terkonfigurasi dengan benar di IDE Anda.
Jalankan kelas UTSPBO2 untuk memulai aplikasi.
Demo
Berikut adalah cuplikan kode untuk beberapa fitur utama aplikasi ini:

1. Menambahkan Catatan Baru
Fungsi ini memungkinkan pengguna untuk menambahkan catatan dengan memasukkan judul dan isi catatan.

java
Salin kode
private void addNote() {
    String title = jTextField1.getText().trim();
    if (!title.isEmpty()) {
        String content = JOptionPane.showInputDialog(this, "Masukkan isi catatan untuk: " + title, "Isi Catatan", JOptionPane.PLAIN_MESSAGE);
        if (content != null) {
            listModel.addElement(title); 
            notes.add(content);         
            jTextField1.setText("");    
            saveNotes();                
            JOptionPane.showMessageDialog(this, "Catatan berhasil ditambahkan dengan isi.");
        } else {
            JOptionPane.showMessageDialog(this, "Isi catatan tidak ditambahkan.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Judul catatan tidak boleh kosong.");
    }
}
2. Mengedit Catatan
Fungsi ini memungkinkan pengguna untuk mengedit catatan yang telah dipilih.

java
Salin kode
private void editNote() {
    int selectedIndex = jList1.getSelectedIndex();
    if (selectedIndex != -1) {
        notes.set(selectedIndex, jTextArea1.getText());
        JOptionPane.showMessageDialog(this, "Catatan berhasil diperbarui.");
    } else {
        JOptionPane.showMessageDialog(this, "Pilih catatan terlebih dahulu.");
    }
}
3. Menghapus Catatan
Fungsi ini memungkinkan pengguna untuk menghapus catatan yang dipilih dari daftar.

java
Salin kode
private void deleteNote() {
    int selectedIndex = jList1.getSelectedIndex();
    if (selectedIndex != -1) {
        listModel.remove(selectedIndex);
        notes.remove(selectedIndex);
        jTextArea1.setText("");
        JOptionPane.showMessageDialog(this, "Catatan berhasil dihapus.");
    } else {
        JOptionPane.showMessageDialog(this, "Pilih catatan yang ingin dihapus.");
    }
}
4. Mengimpor Catatan dari File
Fungsi ini memungkinkan pengguna untuk mengimpor catatan dari file teks.

java
Salin kode
private void importNotes() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
    int option = fileChooser.showOpenDialog(this);
    if (option == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            listModel.clear();
            notes.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    listModel.addElement(parts[0]);
                    notes.add(parts[1]);
                    jTextArea1.append("Judul: " + parts[0] + "\n");
                    jTextArea1.append("Isi: " + parts[1] + "\n\n");
                }
            }
            JOptionPane.showMessageDialog(this, "Catatan berhasil diimpor.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengimpor catatan: " + ex.getMessage());
        }
    }
}
5. Mengekspor Catatan ke File
Fungsi ini memungkinkan pengguna untuk mengekspor catatan ke dalam file teks.

java
Salin kode
private void exportNotes() {
    String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
    String fileName = "notes_export_" + timestamp + ".txt";
    File file = new File(fileName);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (int i = 0; i < listModel.size(); i++) {
            writer.write(listModel.get(i) + ":" + notes.get(i));
            writer.newLine();
        }
        JOptionPane.showMessageDialog(this, "Catatan berhasil diekspor ke file: " + file.getAbsolutePath());
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "Gagal mengekspor catatan: " + ex.getMessage());
    }
}
Cara Menggunakan
Menambah Catatan: Masukkan judul catatan di kolom "Judul", kemudian klik tombol "Tambah". Setelah itu, masukkan isi catatan melalui dialog yang muncul.
Mengedit Catatan: Pilih catatan dari daftar, kemudian edit isi catatan pada area teks dan klik tombol "Ubah".
Menghapus Catatan: Pilih catatan dari daftar dan klik tombol "Hapus".
Mengimpor Catatan: Klik tombol "Impor" untuk memilih file teks yang berisi catatan yang ingin Anda impor.
Mengekspor Catatan: Klik tombol "Ekspor" untuk menyimpan catatan yang ada ke dalam file teks.
Instalasi
Pastikan Anda memiliki Java Development Kit (JDK) yang sudah terinstal di komputer Anda.
Unduh atau clone repositori ini.
Buka proyek di IDE favorit Anda, seperti IntelliJ IDEA atau Eclipse.
Jalankan aplikasi dengan mengeksekusi kelas UTSPBO2.
README ini menggambarkan aplikasi Anda dengan jelas dan memberikan instruksi yang jelas mengenai cara penggunaan aplikasi serta penjelasan fitur-fiturnya.



