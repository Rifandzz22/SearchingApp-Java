/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingapp;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
/*
Class yang berguna menyaring tfSearch agar tidak bisa mengetik
huruf dalam kotak pencarian
*/
/**
 *
 * @author Khukuh
 */
public class IntFilter extends DocumentFilter{
    //Mengetes apakah string yg diinput adalah angka atau huruf
    public boolean test(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    //Mengecek setiap kata yang berasal dari Document#insertString yang dimasukan 
    //ke dalam tfSearch
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException{
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);
        if (test(sb.toString())) {
            super.insertString(fb, offset, string, attr);
            System.out.println(getClass().getName()+"in insertString: "+string);
        } else {
            
        }
    }
    
    //Mengecek kata yang dimasukkan dari ketikan keyboard atau copy-paste yg dimasukkan 
    //ke dalam tfSearch
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
        AttributeSet attrs) throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);
        if (test(sb.toString())) {
           super.replace(fb, offset, length, text, attrs);
           System.out.println(getClass().getName()+"in replace: "+text);
        } else {
           // warn the user and don't allow the insert
        }

    }
    
    //Mengecek kata apabila ada yang terhapus dari tfSearch
    @Override
    public void remove(FilterBypass fb, int offset, int length)
             throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);

        if (sb.toString().length()==0) {
            super.replace(fb, offset, length, "", null);
            System.out.println(getClass().getName()+"in remove");
        } else {
            if(test(sb.toString())){
                super.remove(fb, offset, length);
            }
        }
    }
}
