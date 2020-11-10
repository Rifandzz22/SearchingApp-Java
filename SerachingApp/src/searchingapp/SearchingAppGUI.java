/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingapp;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.PlainDocument;
import java.util.List;

/**
 *
 * @author Khukuh
 */
public class SearchingAppGUI extends JFrame implements KeyListener{
    JPanel panelSearch = new JPanel();
    JPanel panelTabel = new JPanel();
    JTextField tfSearch = new JTextField();
    JScrollPane spTabel;
    JTable tabel = new JTable();
    PasienTableModel tabmod = new PasienTableModel(Pasien.getArrayPasien());
    String[] searchType = {"Nama","Dokter","Ruangan"};
    JComboBox cbSearch = new JComboBox(searchType);
    JLabel searchBy = new JLabel("bedasarkan : ");
    JButton about = new JButton("Tentang");
    
    public SearchingAppGUI(){
        //Pengaturan JFrame
        setSize(500,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Searching Database Pasien");
//        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpAbout();
            }
        });
        
        //Pegaturan JTabel dan JScrollPane
        tabel.setModel(tabmod);
        spTabel = new JScrollPane(tabel);
        spTabel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        //Pengarturan TF pencarian
        tfSearch.setColumns(20);
        tfSearch.addKeyListener(this); //menambahkan tfSearch ke dalam 
                                        //KeyListener agar dapat mencari data
                                        //setiap memencet tombol
        PlainDocument doc = (PlainDocument) tfSearch.getDocument();
        doc.setDocumentFilter(new LetterFilter());
        
        //Pengaturan CB tipe pencarian
        cbSearch.setSelectedIndex(0);
        cbSearch.addItemListener(new ItemListener(){
            //Listener untuk mendapatkan nilai dari combobox apakah
            //nama, ruangan, atau dokter
            @Override
            public void itemStateChanged(ItemEvent e) {
                tabel.setModel(tabmod);
                if(cbSearch.getSelectedIndex()==2){
                    tfSearch.setText("");
                    PlainDocument doc = (PlainDocument) tfSearch.getDocument();
                    doc.setDocumentFilter(new IntFilter());
                }
                else{
                    tfSearch.setText("");
                    ((AbstractDocument) tfSearch.getDocument()).setDocumentFilter(new LetterFilter());
                }
            }
            
        });
        
        //Pengaturan JPanel pencarian
        panelSearch.add(tfSearch);
        panelSearch.add(searchBy);
        panelSearch.add(cbSearch);
        panelSearch.add(about);
        panelSearch.setBorder(BorderFactory.createEtchedBorder());
        panelSearch.setMaximumSize(new Dimension(500, tfSearch.getHeight()));
        panelSearch.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        //Pengarutan JPanel tabel
        panelTabel.setBorder(BorderFactory.createEtchedBorder());
        GroupLayout lotab = new GroupLayout(panelTabel);
        panelTabel.setLayout(lotab);
        lotab.setVerticalGroup(lotab.createSequentialGroup()
                .addGap(5)
                .addComponent(spTabel)
                .addGap(5));
        lotab.setHorizontalGroup(lotab.createSequentialGroup()
                .addGap(5)
                .addComponent(spTabel)
                .addGap(5));
        
        //Pengaturan lyaout JFrame
        GroupLayout lo = new GroupLayout(getContentPane());
        getContentPane().setLayout(lo);
        lo.setAutoCreateContainerGaps(true);
        lo.setAutoCreateGaps(true);
        lo.setHorizontalGroup(lo.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(panelSearch)
                .addComponent(panelTabel));
        lo.setVerticalGroup(lo.createSequentialGroup()
                .addComponent(panelSearch)
                .addComponent(panelTabel));
    }

    //Event yang terjadi apabila kita melepas tombol keyboard, bertujuan untuk
    //mengupdate list setiap kita mengetik
    @Override
    public void keyReleased(KeyEvent e) {
        //Syarat mengupate table hanya memencent tombol huruf kecil, kapital,
        //angka, backspace, dan enter
        if(e.getKeyChar()>='a'&&e.getKeyChar()<='z'||e.getKeyChar()>='A'&&e.getKeyChar()<='Z'
                ||e.getKeyCode()==KeyEvent.VK_BACK_SPACE||
                e.getKeyChar()>=KeyEvent.VK_0&&e.getKeyChar()<=KeyEvent.VK_9||
                e.getKeyChar()==KeyEvent.VK_ENTER){
            //mendapatkan index dari combobox
            int index = cbSearch.getSelectedIndex();
            //mendapatkan data pasien ke dalam sebuah lsit
            List<Pasien> list = Pasien.getArrayPasien();
            //mendapatkan kata pencarian
            String pattern = tfSearch.getText();
            //menyiapkan table model yang baru
            PasienTableModel newTab;
            switch(index){
                //apabila berdasarkan nama
                case 0:
                    //mengupdate list yang lama dengan yg baru
                    Pasien.updateList(list, pattern, index);
                    break;
                //apabila berdasarkan dokter
                case 1:
                    Pasien.updateList(list, pattern, index);
                    break;
                //apabila berdasarkan ruangan
                case 2:
                    Pasien.updateList(list, pattern, index);
                    break;
                default:
            }
            //menset tabel dengan model yang baru (dengan list yg baru)
            newTab = new PasienTableModel(list);
            tabel.setModel(newTab); 
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    
    public void popUpAbout(){
        JDialog aboutUs = new JDialog(this);
        aboutUs.setVisible(true);
        aboutUs.setLocationRelativeTo(null);
        aboutUs.setResizable(false);
        JLabel a1 = new JLabel("Ahmad Solahudin Rifandi");
        JLabel a2 = new JLabel("Afif Ma'arif");
        JLabel a3 = new JLabel("Samsul Arifin");
        JLabel a4 = new JLabel("Khukuh Prihatmikho");
        JLabel n1 = new JLabel("11190910000101");
        JLabel n2 = new JLabel("11190910000111");
        JLabel n3 = new JLabel("11190910000110");
        JLabel n4 = new JLabel("11190910000081");
        GroupLayout lo = new GroupLayout(aboutUs.getContentPane());
        lo.setAutoCreateContainerGaps(true);
        lo.setAutoCreateGaps(true);
        lo.setHorizontalGroup(lo.createSequentialGroup()
                .addGroup(lo.createParallelGroup()
                        .addComponent(a1)
                        .addComponent(n1))
                .addGroup(lo.createParallelGroup()
                        .addComponent(a2)
                        .addComponent(n2))
                .addGroup(lo.createParallelGroup()
                        .addComponent(a3)
                        .addComponent(n3))
                .addGroup(lo.createParallelGroup()
                        .addComponent(a3)
                        .addComponent(n3))
//                .addGroup(lo.createParallelGroup()
//                        .addComponent(a4)
//                        .addComponent(n4))
        );
        lo.setVerticalGroup(lo.createSequentialGroup()
                .addGroup(lo.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(a1)
                    .addComponent(a2)
                    .addComponent(a3)
//                    .addComponent(a4)
                )
                .addGroup(lo.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(n1)
                    .addComponent(n2)
                    .addComponent(n3)
//                    .addComponent(n4)
                ));
        aboutUs.getContentPane().setLayout(lo);
        aboutUs.pack();
    }
}
