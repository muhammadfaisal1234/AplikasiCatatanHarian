import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class UTSPBO2 extends javax.swing.JFrame {

    private DefaultListModel<String> listModel;
    private ArrayList<String> notes;

    public UTSPBO2() {
        initComponents();
        initCatatan();
    }
    
    // Inisialisasi komponen GUI terkait catatan
    private void initCatatan() {
        listModel = new DefaultListModel<>();
        jList1.setModel(listModel);
        notes = new ArrayList<>();

        // Menambahkan aksi untuk tombol
        jButton1.addActionListener(e -> addNote());
        jButton2.addActionListener(e -> editNote());
        jButton3.addActionListener(e -> deleteNote());
        jButton4.addActionListener(e -> importNotes());
        jButton5.addActionListener(e -> exportNotes());
        jList1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displaySelectedNote();
            }
        });
    }
    
      private void saveNotes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("notes.txt"))) {
            for (int i = 0; i < listModel.size(); i++) {
                writer.write(listModel.get(i) + ":" + notes.get(i));
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan catatan: " + ex.getMessage());
        }
    }

   // Menambahkan catatan baru
private void addNote() {
    // Mendapatkan judul catatan dari jTextField1
    String title = jTextField1.getText().trim();

    // Validasi: Judul tidak boleh kosong
    if (!title.isEmpty()) {
        // Menampilkan dialog untuk meminta isi catatan
        String content = JOptionPane.showInputDialog(this, 
            "Masukkan isi catatan untuk: " + title, 
            "Isi Catatan", 
            JOptionPane.PLAIN_MESSAGE);

        // Jika pengguna tidak membatalkan dialog
        if (content != null) {
            listModel.addElement(title); // Tambahkan judul ke listModel
            notes.add(content);         // Simpan isi catatan ke daftar notes
            jTextField1.setText("");    // Reset input judul
            saveNotes();                // Simpan catatan setelah penambahan
            JOptionPane.showMessageDialog(this, 
                "Catatan berhasil ditambahkan dengan isi.");
        } else {
            JOptionPane.showMessageDialog(this, 
                "Isi catatan tidak ditambahkan.");
        }
    } else {
        JOptionPane.showMessageDialog(this, 
            "Judul catatan tidak boleh kosong.");
    }
}


    // Mengedit catatan yang dipilih
    private void editNote() {
        int selectedIndex = jList1.getSelectedIndex();
        if (selectedIndex != -1) {
            notes.set(selectedIndex, jTextArea1.getText());
            JOptionPane.showMessageDialog(this, "Catatan berhasil diperbarui.");
        } else {
            JOptionPane.showMessageDialog(this, "Pilih catatan terlebih dahulu.");
        }
    }

    // Menghapus catatan yang dipilih
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

    // Mengimpor catatan dari file .txt
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

            // Bersihkan area teks atau elemen tampilan lainnya
            jTextArea1.setText("");

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    // Menambahkan judul ke listModel
                    listModel.addElement(parts[0]);

                    // Menambahkan isi catatan ke list notes
                    notes.add(parts[1]);

                    // Menampilkan catatan di JTextArea
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


    // Mengekspor catatan ke file .txt dengan nama unik
private void exportNotes() {
    // Membuat nama file berdasarkan timestamp
    String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
    String fileName = "notes_export_" + timestamp + ".txt";
    File file = new File(fileName);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        // Menulis setiap catatan dari listModel dan notes
        for (int i = 0; i < listModel.size(); i++) {
            writer.write(listModel.get(i) + ":" + notes.get(i));
            writer.newLine();
        }
        // Pesan keberhasilan
        JOptionPane.showMessageDialog(this, "Catatan berhasil diekspor ke file: " + file.getAbsolutePath());
    } catch (IOException ex) {
        // Menampilkan pesan kesalahan jika gagal menulis file
        JOptionPane.showMessageDialog(this, "Gagal mengekspor catatan: " + ex.getMessage());
    }
}


    
    

    // Menampilkan konten catatan yang dipilih
    private void displaySelectedNote() {
        int selectedIndex = jList1.getSelectedIndex();
        if (selectedIndex != -1) {
            jTextArea1.setText(notes.get(selectedIndex));
        } else {
            jTextArea1.setText("");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aplikasi Catatan Harian", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Tambah");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Ubah");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Hapus");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("Impor");

        jScrollPane2.setViewportView(jList1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Judul");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("Ekspor");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Masukkan Catatan");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(14, 14, 14)
                                        .addComponent(jTextField1))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(0, 37, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UTSPBO2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UTSPBO2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UTSPBO2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UTSPBO2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UTSPBO2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
