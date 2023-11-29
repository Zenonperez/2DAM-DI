/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.Timer;


/**
 * @author Ziku
 */
public class AnimatedLabel extends JLabel implements Serializable {
    
    private String text;
    private String appendedText;
    private int delay;
    private boolean animated;
   
    
    
    public AnimatedLabel(){
        jbInit();
    }
    
    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
//        if (animated){
//            empezarAnimacion();
//        } else {
//            pararAnimacion();
//            super.setText(text);
//        }
    }

    public String getAppendedText() {
        return appendedText;
    }

    public void setAppendedText(String appendedText) {
        this.appendedText = appendedText;
        
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isAnimated() {
        return animated;
    }

    public void setAnimated(boolean animated) {
        this.animated = animated;
//        if (animated) {
//            empezarAnimacion();
//        } else {
//            pararAnimacion();
//        }
    }
    
    private void jbInit(){
        setText("AnimatedLabel");
        setAppendedText("xxx");
        setDelay(400);
        setAnimated(false);
//        incializarTimer();
    }
    
//    private void incializarTimer(){
//        String copia = getText();
//        animacion = new Timer(delay, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                animarTexto(copia);
//            }
//            });
//    }

//    private void animarTexto(String copia){
//       
//        String animar = getText();
//        animar += getAppendedText().charAt(indiceActual);
//        setText(animar);
//        indiceActual++;
//        if (indiceActual >= appendedText.length()){
//            indiceActual = 0;
//            animar = copia;
//        }
//    }
        
            
        
        
       
    
//    
//    private void empezarAnimacion(){
//        if (animacion != null){
//        animacion.start();
//    }
//    }
//    private void pararAnimacion(){
//        if (animacion != null){
//        animacion.stop();
//    }
//    }
//    
//    private void fusionar(){
//        setText(text + appendedText);
//    }
//   
//    
}