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
import principal.JDialogos.DialogoValoracionCompletada;

/**
 * ValoracionIntentosSinReview es una pantalla que permite valorar los intentos que no tienen review.
 * @author Zenon Perez
 */
public class ValoracionIntentoSinReview extends javax.swing.JDialog {
    //Declaramos las variables globales de la clase.
    /**
     * Varaible con la que tendremos acceso a enviar o recibir datos de la base de datos.
     */
    private DataAccess da = new DataAccess();  
    /**
     * Variable la cual es el parent de esta ventana con la que utilizaremos algun metodo de este.
     */
    Main main;
    /**
     * Color el cual utilizaremos para pintar los botones cuando el cursor pase por encima de estos.
     */
    private Color azulPastel = new Color(173, 216, 230);
    
    
    /**
     * Aqui se crea el form de ValoracionIntentosSinReview.
     * @param parent nos muestra que esta ventana sale de un JFrame que en este caso sale de "Main"
     * @param modal nos dice si es una ventana modal o no, en este caso es modal.
     */
    public ValoracionIntentoSinReview(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        main = (Main) parent;
        //Iniciamos los componentes de la ventana.
        initComponents();
        int idIntento = main.getIDIntento();
        String idIntentoS = String.valueOf(idIntento);
        lbl_IDIntento.setText(idIntentoS);
        lbl_idUsuario.setText(main.buscarIDUsuario(idIntento));
        //Resaltamos los bordes de la ventana para que destaque más
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        
     
    }

   /**
     * Metodo que inicializa el form y sus componentes.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Principal = new javax.swing.JPanel();
        lbl_ValoracionEnunciado = new javax.swing.JLabel();
        txa_ValoracionRespuesta = new javax.swing.JTextField();
        lbl_Comentario = new javax.swing.JLabel();
        btn_Enviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_ComentarioRespuesta = new javax.swing.JTextArea();
        lbl_IDintentoEnunciado = new javax.swing.JLabel();
        lbl_IDUsuarioEnunciado = new javax.swing.JLabel();
        lbl_IDIntento = new javax.swing.JLabel();
        lbl_idUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Review");
        setPreferredSize(new java.awt.Dimension(442, 366));
        setResizable(false);
        setSize(new java.awt.Dimension(442, 366));

        pnl_Principal.setBackground(new java.awt.Color(51, 51, 51));
        pnl_Principal.setMinimumSize(new java.awt.Dimension(442, 366));

        lbl_ValoracionEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_ValoracionEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_ValoracionEnunciado.setText("Valoración:");

        txa_ValoracionRespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txa_ValoracionRespuestaActionPerformed(evt);
            }
        });

        lbl_Comentario.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lbl_Comentario.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Comentario.setText("Comentario:");

        btn_Enviar.setText("Enviar");
        btn_Enviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_EnviarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_EnviarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_EnviarMousePressed(evt);
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

        txa_ComentarioRespuesta.setLineWrap(true);
        jScrollPane1.setViewportView(txa_ComentarioRespuesta);

        lbl_IDintentoEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_IDintentoEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDintentoEnunciado.setText("ID del intento:");

        lbl_IDUsuarioEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_IDUsuarioEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDUsuarioEnunciado.setText("Nombre del Usuario:");

        lbl_IDIntento.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_IDIntento.setForeground(new java.awt.Color(255, 255, 255));

        lbl_idUsuario.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_idUsuario.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_PrincipalLayout = new javax.swing.GroupLayout(pnl_Principal);
        pnl_Principal.setLayout(pnl_PrincipalLayout);
        pnl_PrincipalLayout.setHorizontalGroup(
            pnl_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_PrincipalLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnl_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_Comentario)
                    .addGroup(pnl_PrincipalLayout.createSequentialGroup()
                        .addComponent(lbl_IDintentoEnunciado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_IDIntento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(lbl_IDUsuarioEnunciado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnl_PrincipalLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(btn_Enviar))
                    .addGroup(pnl_PrincipalLayout.createSequentialGroup()
                        .addComponent(lbl_ValoracionEnunciado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txa_ValoracionRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnl_PrincipalLayout.setVerticalGroup(
            pnl_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_PrincipalLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(pnl_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_IDintentoEnunciado)
                    .addComponent(lbl_IDIntento, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_IDUsuarioEnunciado)
                    .addComponent(lbl_idUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnl_PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_ValoracionEnunciado)
                    .addComponent(txa_ValoracionRespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_Comentario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_Enviar)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txa_ValoracionRespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txa_ValoracionRespuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txa_ValoracionRespuestaActionPerformed

    /**
     * Metodo que se activa al pulsar el boton "btn_Enviar" cuya funcion es crear una review del intento seleccionado y valorandolo.
     */
    private void btn_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EnviarActionPerformed
        Review review = new Review();
        int valoracion = 0;
        //Se revisan que los datos de la review sean correctos de lo contrario saltara su respectivo dialogo de error.
        try{
        valoracion =Integer.parseInt(txa_ValoracionRespuesta.getText());
        review.setValoracion(valoracion);
        } catch (NumberFormatException ex){
            DialogoErrorNumerosValoracion dialogoErrorNumerosValoracion = new DialogoErrorNumerosValoracion(this,true);
            dialogoErrorNumerosValoracion.setVisible(true);
            valoracion = -1;
        }
        review.setComentario(txa_ComentarioRespuesta.getText());    
        
        review.setIdIntent(main.getIDIntento());
        review.setIdReviewer(main.getIDUsuario());
        if (valoracion < 0 || valoracion > 10){
            DialogoErrorRangoNumerosValoracion dialogoErrorRangoNumerosValoracion = new DialogoErrorRangoNumerosValoracion(this,true);
            dialogoErrorRangoNumerosValoracion.setVisible(true);
        }
        else{
            //Al estar todo correcto se guarda la review en la base de datos
            da.insertReview(review);  
            DialogoValoracionCompletada dialogoValoracionCompletada = new DialogoValoracionCompletada(this, true);
            dialogoValoracionCompletada.setVisible(true);
            //Se revierte toda seleccion hecha en la pantalla principal de la aplicacion.
            main.revertirAlValorar();
        }

        dispose();
       
      
    }//GEN-LAST:event_btn_EnviarActionPerformed
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
     * Metodo que se activara al pulsar boton "btn_Enviar" que hara que se ponga de color gris claro.
     */
    private void btn_EnviarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EnviarMousePressed
        btn_Enviar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_EnviarMousePressed
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
    private javax.swing.JLabel lbl_IDIntento;
    private javax.swing.JLabel lbl_IDUsuarioEnunciado;
    private javax.swing.JLabel lbl_IDintentoEnunciado;
    private javax.swing.JLabel lbl_ValoracionEnunciado;
    private javax.swing.JLabel lbl_idUsuario;
    private javax.swing.JPanel pnl_Principal;
    private javax.swing.JTextArea txa_ComentarioRespuesta;
    private javax.swing.JTextField txa_ValoracionRespuesta;
    // End of variables declaration//GEN-END:variables
}