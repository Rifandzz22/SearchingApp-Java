/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingapp;

import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 *
 * @author Khukuh
 */

//Kelas yang menghubungkan JTable dengan List Pasien
public class PasienTableModel extends AbstractTableModel{
    //List pasien
    List<Pasien> list = new ArrayList<>();
    
    //Array untuk kolom table nanti
    private final String[] HEADER = {"Nama","Dokter","Ruangan",};
    
    //Konstruktor yang berfungsi mengoper list pasien dari argumen ke list pasien
    //kelas ini
    public PasienTableModel(List<Pasien> arrayPasien){
        list = arrayPasien;
    }
    
    //default konstruktor
    public PasienTableModel(){
        
    }
    
    public void insert(Pasien pasien){
        list.add(pasien);
        fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
    }
    
    public void update(Pasien pasien, int index){
        list.set(index, pasien);
        fireTableRowsUpdated(index, index);
    }
    
    public void delete(int index){
        list.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return HEADER.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return list.get(rowIndex).getNama();
            case 1:
                return list.get(rowIndex).getDokter();
            case 2:
                return list.get(rowIndex).getRuangan();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0:
                return HEADER[0];
            case 1:
                return HEADER[1];
            case 2:
                return HEADER[2];
            default:
                return null;
        }
    }
    
}
