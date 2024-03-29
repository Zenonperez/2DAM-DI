/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import at.favre.lib.crypto.bcrypt.BCrypt;
import data.DataAccess;
import dto.Usuari;
import java.awt.Color;
import principal.JDialogos.DialogoProfesorRegistrado;
import principal.JDialogos.DialogoRegistrarFaltaContrasena;
import principal.JDialogos.DialogoRegistrarFaltaMail;
import principal.JDialogos.DialogoRegistrarFaltaNombre;

/**
 * Register es una ventana no obligatoria dentro de este proyecto pero nos permitira registrar un nuevo intructor en la base de datos.
 * 
 * @author Zenon Perez
 */
public class Register extends javax.swing.JDialog {
    //Declaramos la variable global azulpastel para usarlo para implementar la usabilidad en los botones.
    /**
     * Color el cual usaremos para cambiar el color de los botones cuando el cursor pase encima de estos.
     */
    private Color azulPastel = new Color(173, 216, 230);

    /**
     * Aqui se crea el nuevo form de Register
     * @param parent nos muestra que esta ventana sale de un JDialog que en este caso sale de "Login".
     * @param modal nos dice si se trata de una ventana modal o no, en este caso es modal.
     */
    public Register(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        //Se inician todos los componentes
        initComponents();
    }

    /**
     * Metodo que nos permite validar los campos a la hora de registrar un usuario.
     * @return si los campos estan correctamente rellenados devolvera true, en caso de que algun campo no se correcto devolvera false y mostrara un JDialog diciendo porque no es correcto.
     */
    private boolean ValidarCampos() {
        String nombre = txt_ponerNombre.getText();
        String email = txt_ponerCorreo.getText();
        char[] contraseña = txt_contraseña.getPassword();
        if (nombre == null || "".equals(nombre)) {
            DialogoRegistrarFaltaNombre dialogoRegistrarFaltaNombre = new DialogoRegistrarFaltaNombre(this, true);
            dialogoRegistrarFaltaNombre.setVisible(true);
            return false;
        } else if (email == null || "".equals(email)) {
            DialogoRegistrarFaltaMail dialogoRegistrarFaltaMail = new DialogoRegistrarFaltaMail(this,true);
            dialogoRegistrarFaltaMail.setVisible(true);
            return false;
        } else if (contraseña.length <= 0) { //Le faltan unos ajustes
            DialogoRegistrarFaltaContrasena dialogoRegistrarFaltaContrasena = new DialogoRegistrarFaltaContrasena(this, true);
            dialogoRegistrarFaltaContrasena.setVisible(true);
            return false;
        } else {
            return true;
        }
    }

    /**
     *Metodo que inicializa el form y sus componentes.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Register = new javax.swing.JPanel();
        lbl_nombreRegis = new javax.swing.JLabel();
        txt_ponerNombre = new javax.swing.JTextField();
        lbl_correoElectronicoRegis = new javax.swing.JLabel();
        txt_ponerCorreo = new javax.swing.JTextField();
        lbl_contraseñaRegis = new javax.swing.JLabel();
        txt_contraseña = new javax.swing.JPasswordField();
        btn_registrarse = new javax.swing.JButton();
        lbl_icono = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registrar nuevo usuario");
        setResizable(false);
        setSize(new java.awt.Dimension(443, 376));

        pnl_Register.setBackground(new java.awt.Color(51, 51, 51));
        pnl_Register.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        pnl_Register.setForeground(new java.awt.Color(255, 255, 255));

        lbl_nombreRegis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_nombreRegis.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombreRegis.setText("Nombre");

        txt_ponerNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ponerNombreActionPerformed(evt);
            }
        });

        lbl_correoElectronicoRegis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_correoElectronicoRegis.setForeground(new java.awt.Color(255, 255, 255));
        lbl_correoElectronicoRegis.setText("Correo Electrónico");

        txt_ponerCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ponerCorreoActionPerformed(evt);
            }
        });

        lbl_contraseñaRegis.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_contraseñaRegis.setForeground(new java.awt.Color(255, 255, 255));
        lbl_contraseñaRegis.setText("Contraseña");

        txt_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_contraseñaActionPerformed(evt);
            }
        });

        btn_registrarse.setText("Registrarse");
        btn_registrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_registrarseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_registrarseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_registrarseMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_registrarseMouseReleased(evt);
            }
        });
        btn_registrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarseActionPerformed(evt);
            }
        });

        lbl_icono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Fitnow.jpg"))); // NOI18N

        javax.swing.GroupLayout pnl_RegisterLayout = new javax.swing.GroupLayout(pnl_Register);
        pnl_Register.setLayout(pnl_RegisterLayout);
        pnl_RegisterLayout.setHorizontalGroup(
            pnl_RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_RegisterLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnl_RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_contraseña)
                    .addComponent(lbl_contraseñaRegis)
                    .addComponent(lbl_nombreRegis)
                    .addComponent(lbl_correoElectronicoRegis)
                    .addComponent(txt_ponerCorreo)
                    .addComponent(txt_ponerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(lbl_icono)
                .addGap(24, 24, 24))
            .addGroup(pnl_RegisterLayout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(btn_registrarse)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnl_RegisterLayout.setVerticalGroup(
            pnl_RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_RegisterLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnl_RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_RegisterLayout.createSequentialGroup()
                        .addComponent(lbl_nombreRegis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ponerNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_correoElectronicoRegis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ponerCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_contraseñaRegis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_icono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btn_registrarse)
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Register, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_Register, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_ponerNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ponerNombreActionPerformed

    }//GEN-LAST:event_txt_ponerNombreActionPerformed

    /**
     * Metodo que se activara al pulsar el boton "btn_registrarse" donde realizar todos los tramites para crear al nuevo intructor usuario en la base de datos.
     */
    private void btn_registrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarseActionPerformed
        if (ValidarCampos()) {
            //Crea al usuario y pone todos sus datos sacandolos de los campos.
            Usuari nuevoUsuario = new Usuari();
            nuevoUsuario.setNombre(txt_ponerNombre.getText());
            nuevoUsuario.setEmail(txt_ponerCorreo.getText());
            String contraseñaZifrada = BCrypt.withDefaults().hashToString(12, txt_contraseña.getPassword());
            nuevoUsuario.setPasswordHash(contraseñaZifrada);
            nuevoUsuario.setInstructor(true);

            //Con el dataAcess agregamos al nuevo usuario
            DataAccess da = new DataAccess();
            int idNuevoUsuario = da.registerUser(nuevoUsuario);
            nuevoUsuario.setId(idNuevoUsuario);
            //Mostramos un dialogo de que se ha creado correctamente al usuario
            DialogoProfesorRegistrado dialogoProfesorRegistrado = new DialogoProfesorRegistrado(this, true);
            dialogoProfesorRegistrado.setVisible(true);
            setVisible(false);
        }
    }//GEN-LAST:event_btn_registrarseActionPerformed

    private void txt_ponerCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ponerCorreoActionPerformed
    }//GEN-LAST:event_txt_ponerCorreoActionPerformed

    private void txt_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_contraseñaActionPerformed
    }//GEN-LAST:event_txt_contraseñaActionPerformed

    /**
     * Metodo que se activara al pulsar boton "btn_registrarse" que hara que se ponga de color gris claro.
     */
    private void btn_registrarseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrarseMouseClicked
        btn_registrarse.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_registrarseMouseClicked
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_registrarse" que hara que se ponga de color azul.
     */
    private void btn_registrarseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrarseMouseEntered
        btn_registrarse.setBackground(azulPastel);
    }//GEN-LAST:event_btn_registrarseMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_registrarse" que hara que se ponga de color blanco.
     */
    private void btn_registrarseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrarseMouseExited
        btn_registrarse.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_registrarseMouseExited
    /**
     * Metodo que se activara al soltar el "btn_registrarse" que hara que se ponga de color azul.
     */
    private void btn_registrarseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_registrarseMouseReleased
        btn_registrarse.setBackground(azulPastel);
    }//GEN-LAST:event_btn_registrarseMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_registrarse;
    private javax.swing.JLabel lbl_contraseñaRegis;
    private javax.swing.JLabel lbl_correoElectronicoRegis;
    private javax.swing.JLabel lbl_icono;
    private javax.swing.JLabel lbl_nombreRegis;
    private javax.swing.JPanel pnl_Register;
    private javax.swing.JPasswordField txt_contraseña;
    private javax.swing.JTextField txt_ponerCorreo;
    private javax.swing.JTextField txt_ponerNombre;
    // End of variables declaration//GEN-END:variables
}
