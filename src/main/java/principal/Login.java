/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import at.favre.lib.crypto.bcrypt.BCrypt;
import data.DataAccess;
import dto.Usuari;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import principal.JDialogos.DialogoAccesoDenegadoContraseña;
import principal.JDialogos.DialogoAccesoDenegadoUsuario;
import principal.JDialogos.DialogoBienvenida;

/**
 *Login sera la ventana que aparecera en primer lugar y en ultimo lugar en el programa, esta ventana es la puerta del progrma.
 *Para entrar hay que meter el mail del instructor junto sus credenciales 
 * @author Zenon Perez
 */
public class Login extends javax.swing.JDialog {

    //Declaramos las variables globales del programa
    /**
     * Es la variable que dira si estamos conectados o no, en cuanto el usuario entre en el programa cambiara a true.
     */
    private boolean conectado = false;
    /**
     * Esta variable se usa para coger o enviar los datos a la base de datos.
     */
    private DataAccess da = new DataAccess();
    /**
     * Variable que representa al usuario que se va a conectar.
     */
    private Usuari user;
    /**
     * Variable que representa al parent de esta ventana y que usaremos para coger algun metodo de este.
     */
    private Main main;
    /**
     * Booleano que nos indica si ya nos habiamos conectado antes o no.
     */
    private boolean segundaConexion;
    /**
     * Dialogo el cual nos da la bienvenida al entrar.
     */
    private DialogoBienvenida dialogoBienvenida;

    /**
     * Aqui se crea el nuevo form de Login.
     * @param parent nos muestra de que esta ventana sale de un JFrame que en este caso es de Main.
     * @param modal nos dice si la ventana es modal o no, en este caso es una ventana modal.
     * @param user este parametro indica que usuario se va a conectar a la aplicacion, dependera de que mail y credenciales se utilizara al entrar a la aplicacion.
     * @param segundaConexion este parametro empezara siendo false y una vez conectemos`pasara a estar true. Cuya funcion es unicamente mostrar el main una vez volvamos a conectarnos.
     */
    public Login(java.awt.Frame parent, boolean modal, Usuari user, boolean segundaConexion) {
        super(parent, modal);
        main = (Main) parent;
        this.user = user;
        //Se inicializan los componentes del JDialog
        initComponents();
        //Se pone como icono de ventana la imagen "logoIcono".
        Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/logoIcono.jpg"));
        setIconImage(icono);
        btn_IniciarSesion.setBackground(Color.white);
        conectado = false;
        this.segundaConexion = segundaConexion;
        //Si cerramos la ventana se cerrara la aplicacion.
        this.addWindowListener(new WindowAdapter() {
            /**
             *Metodo que cerrara la aplicacion si se cierra la ventana.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        
        
        });

    }

    /**
     * Metodo que devuelve un bool comprobando si estamos conectados o no. 
     * Este metodo se enviara al main, el cual si es true se activara.
     * @return devuelve un booleano dependiendo de si se a podido conectar o no.
     */
    public boolean EstasConectado() {
        return conectado;
    }

    /**
     *Metodo que inicializa todos los componentes de "Login".
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_colorVentana = new javax.swing.JPanel();
        lbl_TextoIniciarSesion = new javax.swing.JLabel();
        lbl_CorreoElectronico = new javax.swing.JLabel();
        txt_PonerMail = new javax.swing.JTextField();
        lbl_Contraseña = new javax.swing.JLabel();
        btn_IniciarSesion = new javax.swing.JToggleButton();
        txt_PonerContraseña = new javax.swing.JPasswordField();
        lbl_clickParaRegistrar = new javax.swing.JLabel();
        lbl_logoPrograma = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setBackground(new java.awt.Color(51, 51, 51));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(null);
        setResizable(false);
        setSize(new java.awt.Dimension(539, 310));

        pnl_colorVentana.setBackground(new java.awt.Color(51, 51, 51));

        lbl_TextoIniciarSesion.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbl_TextoIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TextoIniciarSesion.setText("Inicio de Sesión");

        lbl_CorreoElectronico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_CorreoElectronico.setForeground(new java.awt.Color(255, 255, 255));
        lbl_CorreoElectronico.setText("Correo Electrónico:");

        lbl_Contraseña.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_Contraseña.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Contraseña.setText("Contraseña:");

        btn_IniciarSesion.setText("Iniciar Sesion");
        btn_IniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_IniciarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_IniciarSesionMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_IniciarSesionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_IniciarSesionMouseReleased(evt);
            }
        });
        btn_IniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_IniciarSesionActionPerformed(evt);
            }
        });
        btn_IniciarSesion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                btn_IniciarSesionKeyReleased(evt);
            }
        });

        txt_PonerContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_PonerContraseñaActionPerformed(evt);
            }
        });

        lbl_clickParaRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        lbl_clickParaRegistrar.setText("Nuevo Profesor? Pulsa aqui para registrarte.");
        lbl_clickParaRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_clickParaRegistrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbl_clickParaRegistrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbl_clickParaRegistrarMouseExited(evt);
            }
        });

        lbl_logoPrograma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Fitnow.jpg"))); // NOI18N
        lbl_logoPrograma.setText("jLabel1");

        javax.swing.GroupLayout pnl_colorVentanaLayout = new javax.swing.GroupLayout(pnl_colorVentana);
        pnl_colorVentana.setLayout(pnl_colorVentanaLayout);
        pnl_colorVentanaLayout.setHorizontalGroup(
            pnl_colorVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_colorVentanaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnl_colorVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_colorVentanaLayout.createSequentialGroup()
                        .addComponent(lbl_clickParaRegistrar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnl_colorVentanaLayout.createSequentialGroup()
                        .addGroup(pnl_colorVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_colorVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbl_TextoIniciarSesion)
                                .addComponent(lbl_Contraseña)
                                .addComponent(lbl_CorreoElectronico)
                                .addComponent(txt_PonerMail)
                                .addComponent(txt_PonerContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnl_colorVentanaLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(btn_IniciarSesion)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(lbl_logoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );
        pnl_colorVentanaLayout.setVerticalGroup(
            pnl_colorVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_colorVentanaLayout.createSequentialGroup()
                .addGroup(pnl_colorVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_colorVentanaLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lbl_TextoIniciarSesion)
                        .addGap(45, 45, 45)
                        .addComponent(lbl_CorreoElectronico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_PonerMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_Contraseña)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_PonerContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btn_IniciarSesion))
                    .addGroup(pnl_colorVentanaLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lbl_logoPrograma, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lbl_clickParaRegistrar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_colorVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnl_colorVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo que al sacar el raton del label "lbl_clickParaRegistrar" se pondra de color blanco.
     */
    private void lbl_clickParaRegistrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_clickParaRegistrarMouseExited
        lbl_clickParaRegistrar.setForeground(Color.white);
    }//GEN-LAST:event_lbl_clickParaRegistrarMouseExited
    /**
     * Metodo que al poner el raton encima del label "lbl_clickParaRegistrar" se pondra de color cyan.
     */
    private void lbl_clickParaRegistrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_clickParaRegistrarMouseEntered
        lbl_clickParaRegistrar.setForeground(Color.cyan);
    }//GEN-LAST:event_lbl_clickParaRegistrarMouseEntered
    /**
     * Metodo que al hacer click en el label "lbl_clickParaRegistrar" nos abrira una ventana JDialog para poder registrar a un instructor.
     */
    private void lbl_clickParaRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_clickParaRegistrarMouseClicked
        Register registrarse = new Register(this, true);
        registrarse.setVisible(true);
    }//GEN-LAST:event_lbl_clickParaRegistrarMouseClicked

    private void txt_PonerContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_PonerContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_PonerContraseñaActionPerformed
    
    /**
     * Metodo que al pulsar el boton de inciar sesion el programa intentara inciar la sesion de un usario e ir a la pantalla principal de esta.
     * Si no se pone un correo correcto saltar un mensaje de error en forma de dialigo diciendo que el usuario no existe.
     * Si la contraseña no es correcta saltara un mensaje de error en forma de dialogo diciendo que la contraseña no es correcta.
     * Si todo esta correcto podremos conectarnos correctamente al menu principal de la aplicacion.
     */
    private void btn_IniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_IniciarSesionActionPerformed
        //Buscamos al usaurio que coincida con el mail
        user = da.getUser(txt_PonerMail.getText());
        try {
            if (user != null) {
                //Verificamos la contraseña
                char[] contraseñaParaVerificar = txt_PonerContraseña.getPassword();
                String contraseñaEnBaseDatos = user.getPasswordHash();
                var resultado = BCrypt.verifyer().verify(contraseñaParaVerificar, contraseñaEnBaseDatos);
                //Si el resultado es verified nos muestra un dialogo de bienvenida y nos llevara al menu principal, el main.
                if (resultado.verified) {
                    dialogoBienvenida = new DialogoBienvenida(this, true, user);
                    dialogoBienvenida.setVisible(true);
                    dispose();
                    main.UsuarioConectado(user);
                    conectado = true;
                    if(segundaConexion){
                        main.setVisible(true);
                    }
                    //Si la contrasenya es incorrecta nos saltara un dialogo de erorr
                } else {
                    DialogoAccesoDenegadoContraseña accesoDenegadoContraseña = new DialogoAccesoDenegadoContraseña(this,true);
                    accesoDenegadoContraseña.setVisible(true);
                }

            }
            //Si el usuario no exite nos saltara un dialogo de error.
        } catch (NullPointerException ex) {
            DialogoAccesoDenegadoUsuario accesoDenegadoUsuario = new DialogoAccesoDenegadoUsuario(this, conectado);
            accesoDenegadoUsuario.setVisible(true);
        }
    }//GEN-LAST:event_btn_IniciarSesionActionPerformed

    private void btn_IniciarSesionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_IniciarSesionKeyReleased

    }//GEN-LAST:event_btn_IniciarSesionKeyReleased
    /**
     * Metodo que se activara al soltar el "btn_IniciarSesion" que hara que se ponga de color azul.
     */
    private void btn_IniciarSesionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_IniciarSesionMouseReleased
        Color azulPastel = new Color(173,216,230); 
        btn_IniciarSesion.setBackground(azulPastel);
    }//GEN-LAST:event_btn_IniciarSesionMouseReleased
    /**
     * Metodo que se activara al pulsar boton "btn_IniciarSesionMouse" que hara que se ponga de color gris claro.
     */
    private void btn_IniciarSesionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_IniciarSesionMousePressed
        btn_IniciarSesion.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_IniciarSesionMousePressed
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_IniciarSesion" que hara que se ponga de color azul.
     */
    private void btn_IniciarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_IniciarSesionMouseEntered
        Color azulPastel = new Color(173,216,230); 
        btn_IniciarSesion.setBackground(azulPastel);
    }//GEN-LAST:event_btn_IniciarSesionMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_IniciarSesion" que hara que se ponga de color blanco.
     */
    private void btn_IniciarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_IniciarSesionMouseExited
        btn_IniciarSesion.setBackground(Color.white);
    }//GEN-LAST:event_btn_IniciarSesionMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_IniciarSesion;
    private javax.swing.JLabel lbl_Contraseña;
    private javax.swing.JLabel lbl_CorreoElectronico;
    private javax.swing.JLabel lbl_TextoIniciarSesion;
    private javax.swing.JLabel lbl_clickParaRegistrar;
    private javax.swing.JLabel lbl_logoPrograma;
    private javax.swing.JPanel pnl_colorVentana;
    private javax.swing.JPasswordField txt_PonerContraseña;
    private javax.swing.JTextField txt_PonerMail;
    // End of variables declaration//GEN-END:variables
}
