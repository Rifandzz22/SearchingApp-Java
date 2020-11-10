/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingapp;

import java.util.*;

/**
 *
 * @author Khukuh
 */

//Entitias pasien
public class Pasien {
    private String nama;
    private String dokter;
    private int ruangan;
    
    private static final String[][] data = {{"Fandi","Dr. Wahid Gufron Sp. B","12"},
        {"Samsul","Dr. Michelle Regina Sudjadi","05"},
        {"Afif","Dr. Purbo Prawiro Budiharjo","48"},
        {"Afif Samsul","Dr. Rizki Rina Furi","12"},
        {"Faruq","Dr. Saddam Haykal","16"},
        {"Michael","Dr. Steffiany","10"},
        {"Miqdad","Dr. Syarifah Yasmin Hudrina","41"},
        {"Aleng","Dr. Talita Clarissa Sinatra","10"},
        {"Syahid","Dr. Boyke","76"},
        {"Reza","Dr. Stone","35"},
        {"Ahmad","Dr. H Sopyan Hadi Sp.B, FINACS","17"},
        {"Adin","Dr. Dr Hasroni Fathurrahman Sp.U","02"},
        {"Bintang","Drg. Bramadita Satya Sp.BM","19"},
        {"Fachri","Dr. Karina Sidabutar Sp.U","56"},
        {"Omar","Dr. Akhada Maulana Sp.U","33"},
        {"Andi Rahman","Dra. A Kasandra Putranto S.Psi, Psikolog","11"},
        {"Junas","Dr. Akbar Novan Dwi Saputra Sp. OG","65"},
        {"Dzaki","Dr. Canggih Naluri Fitriyasa Sp. OG","62"},
        {"Raffi","Dr. Xone","97"},
        {"Daffa","Dr. Imam","84"}}; //Matriks 2D yang berisi data pasien
    
    //Method search berupa fungsi yang mengembalikan true atau false 
    //apakah terdapat pencarian yang diinginkan pada parameter txt
    public static boolean search(String txt, String pat) 
    { 
        int M = pat.length(); 
        int N = txt.length();
        boolean found = false;
        int lenTxt=0;
  
        /* A loop to slide pat one by one */
        try {
            for (int i = 0; i < M; i++) {
            if(txt.charAt(i)==pat.charAt(i)){
                lenTxt++;
            }
        }
        } catch (StringIndexOutOfBoundsException e) {
            found=false;
        }
        if(lenTxt==M){
            found=true;
        }
        return found;
    }
    
    //Method yang berfunsi mengupdate list pasien dengan pencarian yang 
    //diinginkan
    public static void updateList(List<Pasien> list,String pat, int type){
        List<Pasien> updateList = new ArrayList<>();
        if(!pat.isEmpty()){
            if(type==0){
                for(int i=0; i<list.size(); i++){
                    String strNama = list.get(i).getNama().toLowerCase();
                    String strPat = pat.toLowerCase();
                    if(search(strNama, strPat)){
                        updateList.add(list.get(i));
                    }
                }
                list.clear();
                list.addAll(updateList);
            }
            if(type==1){
                for(int i=0; i<list.size(); i++){
                    String strNama = list.get(i).getDokter().toLowerCase();
                    String strPat = pat.toLowerCase();
                    if(search(strNama, strPat)){
                        updateList.add(list.get(i));
                    }
                }
                list.clear();
                list.addAll(updateList);
            }
            if(type==2){
                for(int i=0; i<list.size(); i++){
                    String strNama = String.valueOf(list.get(i).getRuangan()).toLowerCase();
                    String strPat = pat.toLowerCase();
                    if(search(strNama, strPat)){
                        updateList.add(list.get(i));
                    }
                }
                list.clear();
                list.addAll(updateList);
            }
        }
        
    }
    
    //Method yang menginisialisasi data pasien dan mengembalikannya
    //dalam bentuk list
    public static List<Pasien> getArrayPasien(){
        List arrayPasien = new ArrayList<Pasien>();
        for(int i=0; i<data.length; i++){
            Pasien pasien = new Pasien();
            pasien.nama = data[i][0];
            pasien.dokter = data[i][1];
            pasien.ruangan = Integer.parseInt(data[i][2]);
            arrayPasien.add(pasien);
        }
        return arrayPasien;
    }
    
    //Sisanya adalah setter dan getter atribut pasien
    public String getNama(){
        return nama;
    }
    
    public void setNama(String pNama){
        nama = pNama;
    }
    
    public String getDokter(){
        return dokter;
    }
    
    public void setDokter(String pDokter){
        dokter = pDokter;
    }
    
    public int getRuangan(){
        return ruangan;
    }
    
    public void setRuangan(int pRuangan){
        ruangan = pRuangan;
    }
}
