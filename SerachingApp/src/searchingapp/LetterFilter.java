/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchingapp;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Khukuh
 */
public class LetterFilter extends DocumentFilter{

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (length > 0) {
            fb.remove(offset, length);
        }
        insertString(fb, offset, text, attrs);
        //System.out.println(getClass().getName()+" : "+text);
    }

    //Mengecek setiap ftSearch.getText()[n-1] apakah huruf atau bukan, jika ya,
    //hapus karakter tersebut dan masukan string yg baru
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        StringBuffer buffer = new StringBuffer(string);
        for(int i=buffer.length()-1; i>=0; i--){
            char a = buffer.charAt(i);
            if(!Character.isLetter(a)&&a!=' '){
                buffer.deleteCharAt(i);
            }
        }
        super.insertString(fb, offset, buffer.toString(), attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length); //To change body of generated methods, choose Tools | Templates.
    }
    
}
