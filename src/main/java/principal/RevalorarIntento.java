/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import data.DataAccess;
import dto.Review;
import java.awt.Color;
import javax.swing.BorderFactory;
import principal.JDialogos.DialogoErrorNumerosValoracion;
import principal.JDialogos.DialogoErrorRangoNumerosValoracion;
import principal.JDialogos.DialogoRevaloracionCompletada;

/**
 * RevalorarIntento es una ventana la cual nos permite visualizar y modificar la valoracion de un intento ya evaluado.
 * @author Zenon Perez
 */
public class RevalorarIntento extends javax.swing.JDialog {

    //Declaramos a las varables globales de la clase.
    /**
     * Varaible con la que tendremos acceso a enviar o recibir datos de la base de datos.
     */
    private DataAccess da = new DataAccess();
    /**
     * Variable la cual es el parent de esta ventana con la que utilizaremos algun metodo de este.
     */
    private InformacionUsuario infoUsuario;
    /**
     * Objeto que consiste en la review de un intento.
     */
    Review review;
    /**
     * Variable que determina la nota de una review.
     */
    private int valoracion;
    /**
     * Color el cual usaremos para cambair el color de los botones cuando el cursor pase por encima.
     */
    private Color azulPastel = new Color(173, 216, 230);
    

    /**
     * Aqui se crea el nuevo form de RevalorarIntento.
     * @param parent nos muestra que esta ventana sale de un JDialog que en este caso sale de "InformacionUsuario".
     * @param modal nos dice si se trata de una vetana modal o no modal, en este caso es modal.
     */
    public RevalorarIntento(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        infoUsuario = (InformacionUsuario) parent;
        //Obtenemos la review del intento selecionado en la ventana anterior.
        review = da.getAttemptReview(infoUsuario.getIDIntento());
        //Iniciamos los componentes.
        initComponents();
        
        //Obtenemos el intento a partir de la review y ponemos los datos dentor de los lbls.
        int IdIntento = review.getIdIntent();
        String IdIntentoS = String.valueOf(IdIntento);
        lbl_idIntento.setText(IdIntentoS);
        lbl_nombreUsuario.setText(infoUsuario.getNombreUsuario());
        String valoracio = String.valueOf(review.getValoracion());
        txa_ValoracionRespuesta.setText(valoracio);
        txa_ComentarioRespuesta.setText(review.getComentario());
        //Ponemos los bordes de la ventana de color blancos para que destaque.
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    /**
     * Metodo que inicializa el form y sus componentes.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Principal = new javax.swing.JPanel();
        btn_Enviar = new javax.swing.JButton();
        lbl_ValoracionEnunciado = new javax.swing.JLabel();
        txa_ValoracionRespuesta = new javax.swing.JTextField();
        lbl_Comentario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_ComentarioRespuesta = new javax.swing.JTextArea();
        lbl_IDintentoEnunciado = new javax.swing.JLabel();
        lbl_IDUsuarioEnunciado = new javax.swing.JLabel();
        lbl_idIntento = new javax.swing.JLabel();
        lbl_nombreUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Review");
        setResizable(false);
        setSize(new java.awt.Dimension(400, 300));

        pnl_Principal.setBackground(new java.awt.Color(51, 51, 51));
        pnl_Principal.setLayout(null);

        btn_Enviar.setText("Enviar");
        btn_Enviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_EnviarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_EnviarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_EnviarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_EnviarMouseReleased(evt);
            }
        });
        btn_Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EnviarActionPerformed(evt);
            }
        });
        pnl_Principal.add(btn_Enviar);
        btn_Enviar.setBounds(260, 280, 72, 23);

        lbl_ValoracionEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_ValoracionEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ValoracionEnunciado.setText("Valoración:");
        pnl_Principal.add(lbl_ValoracionEnunciado);
        lbl_ValoracionEnunciado.setBounds(40, 90, 120, 20);

        txa_ValoracionRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txa_ValoracionRespuestaActionPerformed(evt);
            }
        });
        pnl_Principal.add(txa_ValoracionRespuesta);
        txa_ValoracionRespuesta.setBounds(150, 90, 30, 30);

        lbl_Comentario.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_Comentario.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Comentario.setText("Comentario:");
        pnl_Principal.add(lbl_Comentario);
        lbl_Comentario.setBounds(40, 140, 130, 26);

        txa_ComentarioRespuesta.setLineWrap(true);
        jScrollPane1.setViewportView(txa_ComentarioRespuesta);

        pnl_Principal.add(jScrollPane1);
        jScrollPane1.setBounds(40, 170, 290, 90);

        lbl_IDintentoEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_IDintentoEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDintentoEnunciado.setText("ID del intento:");
        pnl_Principal.add(lbl_IDintentoEnunciado);
        lbl_IDintentoEnunciado.setBounds(30, 40, 87, 17);

        lbl_IDUsuarioEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_IDUsuarioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDUsuarioEnunciado.setText("Nombre del Usuario:");
        pnl_Principal.add(lbl_IDUsuarioEnunciado);
        lbl_IDUsuarioEnunciado.setBounds(170, 40, 125, 17);

        lbl_idIntento.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_idIntento.setForeground(new java.awt.Color(255, 255, 255));
        lbl_idIntento.setText("jLabel1");
        pnl_Principal.add(lbl_idIntento);
        lbl_idIntento.setBounds(120, 40, 40, 16);

        lbl_nombreUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombreUsuario.setText("jLabel2");
        pnl_Principal.add(lbl_nombreUsuario);
        lbl_nombreUsuario.setBounds(300, 40, 60, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txa_ValoracionRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txa_ValoracionRespuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txa_ValoracionRespuestaActionPerformed

    /**
     * Metodo que se activa al pulsar el boton "btn_Enviar" cuya funcion es enviar la nueva valoracion del intento.
     */
    private void btn_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EnviarActionPerformed
        try {
            valoracion = Integer.parseInt(txa_ValoracionRespuesta.getText());
            review.setValoracion(valoracion);
            review.setComentario(txa_ComentarioRespuesta.getText());
        }   catch (NumberFormatException ex) {
            DialogoErrorNumerosValoracion dialogoErrorNumerosValoracion = new DialogoErrorNumerosValoracion(this,true);
            dialogoErrorNumerosValoracion.setVisible(true);
            valoracion = -1;
        }
        if (valoracion < 0 || valoracion > 10) {
            DialogoErrorRangoNumerosValoracion dialogoErrorRangoNumerosValoracion = new DialogoErrorRangoNumerosValoracion(this,true);
            dialogoErrorRangoNumerosValoracion.setVisible(true);
        } else {
            da.updateReview(review);
        }
        //Tras enviar la valoracion actualizamos los intenots del usuario y mostramos una ventana de dialogo confirmandonos del cambio.
        infoUsuario.ActualizarIntentosUsuario();
        DialogoRevaloracionCompletada dialogoRevaloracionCompletada = new DialogoRevaloracionCompletada(this,true);
        dialogoRevaloracionCompletada.setVisible(true);
        dispose();

    }//GEN-LAST:event_btn_EnviarActionPerformed
    /**
     * Metodo que se activara al pulsar boton "btn_Enviar" que hara que se ponga de color gris claro.
     */
    private void btn_EnviarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EnviarMouseClicked
        btn_Enviar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_EnviarMouseClicked
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_Enviar" que hara que se ponga de color azul.
     */
    private void btn_EnviarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EnviarMouseEntered
        btn_Enviar.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EnviarMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_Enviar" que hara que se ponga de color blanco.
     */
    private void btn_EnviarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EnviarMouseExited
        btn_Enviar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_EnviarMouseExited
    /**
     * Metodo que se activara al soltar el "btn_Enviar" que hara que se ponga de color azul.
     */
    private void btn_EnviarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EnviarMouseReleased
        btn_Enviar.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EnviarMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Enviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Comentario;
    private javax.swing.JLabel lbl_IDUsuarioEnunciado;
    private javax.swing.JLabel lbl_IDintentoEnunciado;
    private javax.swing.JLabel lbl_ValoracionEnunciado;
    private javax.swing.JLabel lbl_idIntento;
    private javax.swing.JLabel lbl_nombreUsuario;
    private javax.swing.JPanel pnl_Principal;
    private javax.swing.JTextArea txa_ComentarioRespuesta;
    private javax.swing.JTextField txa_ValoracionRespuesta;
    // End of variables declaration//GEN-END:variables
}
