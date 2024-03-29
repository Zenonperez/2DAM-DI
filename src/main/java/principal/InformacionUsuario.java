/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package principal;

import com.mycompany.customcomponentejercicio.SwipeListener;
import data.DataAccess;
import dto.Intent;
import dto.Review;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import principal.JDialogos.DialogoEliminacionReviewCompletada;
import principal.JDialogos.DialogoNoExisteReview;
import principal.JDialogos.DialogoNoHayReviewQueEliminar;

/**
 *InformacionUsuario sera la ventana que mostrara información de un usuario elegido en la tabla de usuarios.
 *Podremos ver los intentos que tiene usuario y podremos visualizarlos, revalorarlos y eliminar la review de estos.
 *Ademas de poder ver los videos de los intentos.
 * 
 * @author Zenon Perez
 */
public class InformacionUsuario extends javax.swing.JDialog {

    //Varaibles privadas de Main y DataAcess para poder acceder a sus metodos
    /**
     * Es la ventana parent de InformacionUusario y que usaremos para llamar algun metodo de esta.
     */
    private Main main;
    /**
     * Esta variable se usa para coger datos de la base de datos.
     */
    private DataAccess da = new DataAccess();
    
    //Declaramos listas para guardar los intentos de los usuarios y los componentes de esos intentos.
    /**
     * Es la lista que guardara los intentos del usuario selecionado en main.
     */
    private ArrayList<Intent> intentosUsuario = new ArrayList<>();
    /**
     * Es la lista que guardara los componentes personalizados de los intentos del usuario.
     */
    private ArrayList<com.mycompany.customcomponentejercicio.CustomComponentEjercicio> componenteIntentos = new ArrayList<>();
    
    //Declaramos otras variables globales del codigo
    /**
     * Es la variable que guarda el valor del indice de la tabla que pertenece el usuario seleccionado.
     */
    private int seleccion;
    /**
     *  Es la variable que guarda la dirrecion a la cual se ha arrastrado el componente.
     */
    private String direccion;
    /**
     * Es la varaible que guarda la idIntento del componente con el que se ha interactuado.
     */
    private int idIntento;
    /**
     * Es el color que usaremos para seleccionar un componente al hacer click.
     */
    private Color verdePastel = new Color(205, 255, 205);
    /**
     * Es el color con el que se pondran los botones u el componente al pasar el cursor encima.
     */
    private Color azulPastel = new Color(173, 216, 230);

    /**
     * Aqui se crea el nuevo form de InformacionUsuario
     * @param parent nos muestra de que esta ventana sale de un JFrame que en este caso es de Main.
     * @param modal nos dice si la ventana sera modal o no, en este caso lo es.
     */
    public InformacionUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        main = (Main) parent;
        //Se seleciona el usuario elegido en la tabla
        seleccion = main.SeleccionFilaUsuariosIntentos();
        //Iniciamos los componentes del Jdialog
        initComponents();
        //Desactivamos botones
        btn_EditarReview.setEnabled(false);
        btn_EliminarReview.setEnabled(false);
        btn_RevisarReview.setEnabled(false);
        //Ponemos los datos del usuario elegido en lbl
        String idS = String.valueOf(da.getAllUsers().get(seleccion).getId());
        lbl_IDRespuesta.setText(idS);
        lbl_NombreRespuesta.setText(da.getAllUsers().get(seleccion).getNombre());
        lbl_EmailRespuesta.setText(da.getAllUsers().get(seleccion).getEmail());
        //Conseguimos los intentos del usuario.
        conseguirIntentosUsuario();
        //Ponemos el borde de color blanco para destacar la ventana
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    /**
     * Metodo que consigue la id de una review de un intento.
     * @param idIntento id del intento que que se intenta obtener la review
     * @return la id de la review de un intento
     */
    public int getIDReview(int idIntento) {
        int idReview = da.getAttemptReview(idIntento).getId();
        return idReview;
    }

    /**
     * Metodo que consigue la id de un intento buscandolo con el metodo buscarIntentoUsuario usando la id del Ejercicio
     * @return devuelve la id de un intento.
     */
    public int getIDIntento() {
        Intent intentoUsuario = buscarIntentoUsuario(idIntento);
        return intentoUsuario.getId();
    }

    /**
     * Metodo qu devulve el nombre de un usuario utilizando el metodo buscarIntentoUsuario usando la id del Ejercicio
     * @return devuelve el nombre de un usuario vinculado a un ejericicio
     */
    public String getNombreUsuario() {
        Intent intentoUsuario = buscarIntentoUsuario(idIntento);
        return intentoUsuario.getNombreUsuario();
    }
    
    /**
     * Metodo que devuelve la id de un ejercicio usando el metodo buscarIntentoUsuario usando la id del Ejercicio.
     * @return devuelve la id del Ejercicio dado por parametros en el otro metodo mencionado.
     */
    public int getIDEjercicio() {
        Intent intentoUsuario = buscarIntentoUsuario(idIntento);
        return intentoUsuario.getIdEjercicio();
    }

    /**
     *Metodo que carga los intentos del usuario en el panel usando los componentes personalizados de customCoponentEjercicio que representan los intentos del usuario.
     *A estos componentes se les dara la funcionalidad de seleccionarlos para realizar gestiones de los intentos o ver los videos de estos. 
     */
    public void conseguirIntentosUsuario() {
        //Primero se busca los intentos del usuario en la base de datos
        intentosUsuario = da.getAttemptsPerUser(da.getAllUsers().get(seleccion));

        //Luego por cada intento se crea un componente a medida de ese intento.
        for (Intent intent : intentosUsuario) {
            com.mycompany.customcomponentejercicio.CustomComponentEjercicio pnl_customComponentEjercicio = new com.mycompany.customcomponentejercicio.CustomComponentEjercicio();
            pnl_customComponentEjercicio.setnombreEjercicio(intent.getNombreEjercicio());
            pnl_customComponentEjercicio.setnombreUsuario(intent.getNombreUsuario());
            pnl_customComponentEjercicio.setfechaIntento(intent.getTimestamp_Inicio());
            pnl_customComponentEjercicio.setIdEjercicio(intent.getId());
            int idreview = getIDReview(intent.getId());
            if (idreview == 0) {
                pnl_customComponentEjercicio.setestadoIntento(1);
            } else {
                pnl_customComponentEjercicio.setestadoIntento(2);
            }
            //Se agrega cada customEjercicio al panel
            pnl_ejerciciosUsuario.add(pnl_customComponentEjercicio);
            pnl_customComponentEjercicio.setFocusable(true);
            componenteIntentos.add(pnl_customComponentEjercicio);
            //Se le agrega al customComponent los listeners de cuando se arrastra o se hace click
            pnl_customComponentEjercicio.addSwipeListener(new SwipeListener() {
                /**
                 * Metodo que listener que si se arrastra a la derecha un componente reproducira el video del componente y si se hace click se podra interactuar con las reviews del componente usando los botones.
                 * @param dirrecion determina si el componente personalizado a sido arrastrado o pulsado lo cual hara una cosa u otra. 
                 * @param idIntento es la id del ejercicio a la cual pertenece el componente con el que interactuemos.
                 */
                @Override
                public void arrastrar(String dirrecion, int idIntento) {
                    direccion = dirrecion;
                    InformacionUsuario.this.idIntento = idIntento;
                    String videoFile = "";
                    //Si se mueve a la derecha reproducira un video,
                    if (direccion.equals("right")) {
                        //Esta variable guardara el intento del usuario al cual pertenece este ejercicio
                        Intent intentoUsuario = buscarIntentoUsuario(InformacionUsuario.this.idIntento);
                        //Mientras no sea nulo reproducira el video
                        if (intentoUsuario != null) {
                            videoFile = intentoUsuario.getVideofile();
                        }
                        
                        main.reproducirVideo(videoFile);
                        dispose();
                    }
                    //Si se mueve a la izquierda o es tan solo un click se activaran los botones para gestionar las reviews del intento
                    if (direccion.equals("left")) {                       
                        btn_RevisarReview.setEnabled(true);
                        btn_EliminarReview.setEnabled(true);
                        btn_EditarReview.setEnabled(true);
                    }
                    //Independientemente de lo que se haga al interactuar se cambiara de color el componente con este metodo
                    SeleccionarComponente(InformacionUsuario.this.idIntento);
                }
            });

        }

    }
    /**
     * Metodo que implementa la usabilidad del componente personalizado cambiandolo de color a amarillo para decir que es el componente que reproduce el video y a verde si se ha seleccionado.
     *  Si se deja de usar cambiara a su color por defecto que es el gris clarito.
     * @param idIntento para buscar al componente que coincida con la id del ejercicio 
     */
    public void SeleccionarComponente(int idIntento) {
        //Se usa la lista donde hemos guardado los componentes para buscar el que coincida y cambiarlo de color.
        for (com.mycompany.customcomponentejercicio.CustomComponentEjercicio componente : componenteIntentos) {

            if (componente.getIdEjercicio() == idIntento && direccion.equals("left")) {
                componente.cambiarColor(verdePastel);
            } else {
                componente.cambiarColor(Color.lightGray);
            }
        }
    }
    /**
     * Metodo que incializa la nueva ventana que mostrara los datos de una review de un ejercicio.
     */
    private void IniciarRevisionReview() {
        RevisionReview revisionReview = new RevisionReview(this, true);
        revisionReview.setVisible(true);
    }
    /**
     * Metodo que mostrara un dialogo diciendo que la review no exite.
     */
    private void noReview() {
        DialogoNoExisteReview dialogoNoExisteReview = new DialogoNoExisteReview(this,true);
        dialogoNoExisteReview.setVisible(true);
    }

    /**
     * Metodo que actualiza el estado de los intentos del usuario.
     * Se activa cuando suele haber una modificacion.
     */
    public void ActualizarIntentosUsuario() {
        pnl_ejerciciosUsuario.removeAll();
        conseguirIntentosUsuario();
        dispose();
    }
    
    /**
     * Metodo que busca un intento especifico de un usuario.
     * @param idIntento se utiliza este parametro para buscar el intento que coincida con esta id.
     * @return devuelve el intento encontrado en la lista de intentos si lo encuentra, en caso contrario devulve null.
     */
    public Intent buscarIntentoUsuario(int idIntento) {
        for (Intent intento : intentosUsuario) {
            int id = intento.getId();
            if (id == idIntento) {
                return intento;
            }
        }
        return null;
    }

    /**
     * Metodo que incializa los componentes del JDialog para que se puedan ver.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_Principal = new javax.swing.JPanel();
        btn_EditarReview = new javax.swing.JButton();
        btn_EliminarReview = new javax.swing.JButton();
        lbl_idEnunciado = new javax.swing.JLabel();
        lbl_verVideo = new javax.swing.JLabel();
        lbl_NombreEnunciado = new javax.swing.JLabel();
        lbl_activarBotones = new javax.swing.JLabel();
        lbl_EmailEnunciado = new javax.swing.JLabel();
        lbl_IDRespuesta = new javax.swing.JLabel();
        lbl_NombreRespuesta = new javax.swing.JLabel();
        lbl_EmailRespuesta = new javax.swing.JLabel();
        btn_RevisarReview = new javax.swing.JButton();
        lbl_iconoDeslizar = new javax.swing.JLabel();
        lbl_iconoClick = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnl_ejerciciosUsuario = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informacion del Usuario");
        setResizable(false);
        setSize(new java.awt.Dimension(476, 571));

        pnl_Principal.setBackground(new java.awt.Color(51, 51, 51));
        pnl_Principal.setPreferredSize(new java.awt.Dimension(476, 571));
        pnl_Principal.setLayout(null);

        btn_EditarReview.setText("Editar Review");
        btn_EditarReview.setToolTipText("");
        btn_EditarReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_EditarReviewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_EditarReviewMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_EditarReviewMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_EditarReviewMouseReleased(evt);
            }
        });
        btn_EditarReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditarReviewActionPerformed(evt);
            }
        });
        pnl_Principal.add(btn_EditarReview);
        btn_EditarReview.setBounds(180, 470, 120, 23);

        btn_EliminarReview.setText("Eliminar Review");
        btn_EliminarReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_EliminarReviewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_EliminarReviewMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_EliminarReviewMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_EliminarReviewMouseReleased(evt);
            }
        });
        btn_EliminarReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EliminarReviewActionPerformed(evt);
            }
        });
        pnl_Principal.add(btn_EliminarReview);
        btn_EliminarReview.setBounds(30, 470, 130, 23);

        lbl_idEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_idEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_idEnunciado.setText("ID:");
        pnl_Principal.add(lbl_idEnunciado);
        lbl_idEnunciado.setBounds(40, 60, 100, 20);

        lbl_verVideo.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_verVideo.setForeground(new java.awt.Color(255, 255, 204));
        lbl_verVideo.setText("Ver Video ");
        pnl_Principal.add(lbl_verVideo);
        lbl_verVideo.setBounds(290, 520, 70, 17);

        lbl_NombreEnunciado.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 18)); // NOI18N
        lbl_NombreEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NombreEnunciado.setText("INFORMACION DEL USUARIO:");
        pnl_Principal.add(lbl_NombreEnunciado);
        lbl_NombreEnunciado.setBounds(40, 20, 280, 20);

        lbl_activarBotones.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_activarBotones.setForeground(new java.awt.Color(204, 255, 204));
        lbl_activarBotones.setText("Activar botones");
        pnl_Principal.add(lbl_activarBotones);
        lbl_activarBotones.setBounds(30, 520, 100, 17);

        lbl_EmailEnunciado.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_EmailEnunciado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_EmailEnunciado.setText("Email:");
        pnl_Principal.add(lbl_EmailEnunciado);
        lbl_EmailEnunciado.setBounds(40, 100, 70, 20);

        lbl_IDRespuesta.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_IDRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_IDRespuesta.setText("jLabel5");
        pnl_Principal.add(lbl_IDRespuesta);
        lbl_IDRespuesta.setBounds(110, 60, 140, 20);

        lbl_NombreRespuesta.setFont(new java.awt.Font("Visitor TT1 BRK", 0, 18)); // NOI18N
        lbl_NombreRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_NombreRespuesta.setText("jLabel6");
        pnl_Principal.add(lbl_NombreRespuesta);
        lbl_NombreRespuesta.setBounds(320, 20, 140, 20);

        lbl_EmailRespuesta.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        lbl_EmailRespuesta.setForeground(new java.awt.Color(255, 255, 255));
        lbl_EmailRespuesta.setText("jLabel7");
        pnl_Principal.add(lbl_EmailRespuesta);
        lbl_EmailRespuesta.setBounds(110, 100, 210, 20);

        btn_RevisarReview.setText("Revisar Review");
        btn_RevisarReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_RevisarReviewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_RevisarReviewMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_RevisarReviewMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_RevisarReviewMouseReleased(evt);
            }
        });
        btn_RevisarReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RevisarReviewActionPerformed(evt);
            }
        });
        pnl_Principal.add(btn_RevisarReview);
        btn_RevisarReview.setBounds(317, 470, 130, 23);

        lbl_iconoDeslizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/deslizar.png"))); // NOI18N
        lbl_iconoDeslizar.setText("jLabel1");
        pnl_Principal.add(lbl_iconoDeslizar);
        lbl_iconoDeslizar.setBounds(360, 500, 50, 50);

        lbl_iconoClick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/click.png"))); // NOI18N
        lbl_iconoClick.setText("jLabel2");
        pnl_Principal.add(lbl_iconoClick);
        lbl_iconoClick.setBounds(130, 500, 40, 50);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBar(null);

        pnl_ejerciciosUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnl_ejerciciosUsuario.setPreferredSize(new java.awt.Dimension(380, 316));
        pnl_ejerciciosUsuario.setLayout(new javax.swing.BoxLayout(pnl_ejerciciosUsuario, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(pnl_ejerciciosUsuario);

        pnl_Principal.add(jScrollPane1);
        jScrollPane1.setBounds(40, 150, 400, 290);

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

    /**
     * Metodo que se activa al pulsar el boton " btn_RevisarReview" que lo que hara sera abrir una ventana para revisar la review de un intento.
     * Si no existe ninguna review saldra un dialogo que dira que no exite.
     * @param evt Action event del boton que se activa al pulsar el boton.
     */
    private void btn_RevisarReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RevisarReviewActionPerformed
        //Buscara primero la id de la review buscando primero el id del intento.
        Intent intentoUsuario = buscarIntentoUsuario(idIntento);
        int id = intentoUsuario.getId();
        if (getIDReview(id) != 0) {
            IniciarRevisionReview();
        } else {
            noReview();
        }
    }//GEN-LAST:event_btn_RevisarReviewActionPerformed
    /**
     * Metodo que se activa al pulsar el boton "btn_EditarReview" que abrira una ventana para modificar la review del intento seleccionado.
     * Si no existe ninguna review saldra un dialogo que dira que no exite.
     * @param evt Action event del boton que se activa al pulsar el boton.
     */
    private void btn_EditarReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditarReviewActionPerformed
        //Buscara primero la id de la review buscando primero el id del intento.
        Intent intentoUsuario = buscarIntentoUsuario(idIntento);
        int id = intentoUsuario.getId();
        if (getIDReview(id) != 0) {
            RevalorarIntento revalorarIntento = new RevalorarIntento(this, true);
            revalorarIntento.setVisible(true);
        } else {
            DialogoNoExisteReview dialogoNoExisteReview = new DialogoNoExisteReview(this,true);
            dialogoNoExisteReview.setVisible(true);
        }
    }//GEN-LAST:event_btn_EditarReviewActionPerformed
    /**
     *  Metodo que se activa al pulsar el boton "btn_EliminarRevie" este eliminara la review de un intento.
     *  Si no existe ninguna review saldra un dialogo que dira que no exite.
     * @param evt Action event del boton que se activa al pulsar el boton.
     */
    private void btn_EliminarReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EliminarReviewActionPerformed
        //Buscara primero la id de la review buscando primero el id del intento.
        Intent intentoUsuario = buscarIntentoUsuario(idIntento);
        int id = intentoUsuario.getId();
        Review eliminarReview = da.getAttemptReview(id);
        if (eliminarReview.getId() != 0){
        da.dropReview(eliminarReview.getId());
        ActualizarIntentosUsuario();
        main.ActualizarCambiosIntentosPendientes();
            DialogoEliminacionReviewCompletada eliminacionReviewCompletada = new DialogoEliminacionReviewCompletada(this,true);
            eliminacionReviewCompletada.setVisible(true);
        }else{
            DialogoNoHayReviewQueEliminar dialogoNoHayReviewQueEliminar = new DialogoNoHayReviewQueEliminar(this, true);
            dialogoNoHayReviewQueEliminar.setVisible(true);
        }
        
        

    }//GEN-LAST:event_btn_EliminarReviewActionPerformed
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_EliminarReview" que hara que se ponga de color azul.
     */
    private void btn_EliminarReviewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarReviewMouseEntered
        btn_EliminarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EliminarReviewMouseEntered
    /**
     * Metodo que se activara al pulsar boton "btn_EliminarReview" que hara que se ponga de color gris claro.
     */
    private void btn_EliminarReviewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarReviewMousePressed
        btn_EliminarReview.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_EliminarReviewMousePressed
    /**
     * Metodo que se activara al soltar el "btn_EliminarReview" que hara que se ponga de color azul.
     */
    private void btn_EliminarReviewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarReviewMouseReleased
        btn_EliminarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EliminarReviewMouseReleased
    /**
     * Metodo que se activara al salir el raton del "btn_EliminarReview" que hara que se ponga de color blanco.
     */
    private void btn_EliminarReviewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EliminarReviewMouseExited
        btn_EliminarReview.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_EliminarReviewMouseExited
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_EditarReview" que hara que se ponga de color azul.
     */
    private void btn_EditarReviewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EditarReviewMouseEntered
        btn_EditarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EditarReviewMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_EditarReview" que hara que se ponga de color blanco.
     */
    private void btn_EditarReviewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EditarReviewMouseExited
        btn_EditarReview.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_EditarReviewMouseExited
    /**
     * Metodo que se activara al pulsar boton "btn_EditarReview" que hara que se ponga de color gris claro.
     */
    private void btn_EditarReviewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EditarReviewMousePressed
        btn_EditarReview.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_EditarReviewMousePressed
    /**
     * Metodo que se activara al soltar el "btn_EditarReview" que hara que se ponga de color azul.
     */
    private void btn_EditarReviewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_EditarReviewMouseReleased
        btn_EditarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_EditarReviewMouseReleased
    /**
     * Metodo que se activara al pasar el raton por encima del boton "btn_RevisarReview" que hara que se ponga de color azul.
     */
    private void btn_RevisarReviewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RevisarReviewMouseEntered
        btn_RevisarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_RevisarReviewMouseEntered
    /**
     * Metodo que se activara al salir el raton del "btn_RevisarReview" que hara que se ponga de color blanco.
     */
    private void btn_RevisarReviewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RevisarReviewMouseExited
        btn_RevisarReview.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_RevisarReviewMouseExited
    /**
     * Metodo que se activara al pulsar boton "btn_RevisarReview" que hara que se ponga de color gris claro.
     */
    private void btn_RevisarReviewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RevisarReviewMousePressed
        btn_RevisarReview.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_RevisarReviewMousePressed
    /**
     * Metodo que se activara al soltar el "btn_RevisarReview" que hara que se ponga de color azul.
     */
    private void btn_RevisarReviewMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_RevisarReviewMouseReleased
        btn_RevisarReview.setBackground(azulPastel);
    }//GEN-LAST:event_btn_RevisarReviewMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_EditarReview;
    private javax.swing.JButton btn_EliminarReview;
    private javax.swing.JButton btn_RevisarReview;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_EmailEnunciado;
    private javax.swing.JLabel lbl_EmailRespuesta;
    private javax.swing.JLabel lbl_IDRespuesta;
    private javax.swing.JLabel lbl_NombreEnunciado;
    private javax.swing.JLabel lbl_NombreRespuesta;
    private javax.swing.JLabel lbl_activarBotones;
    private javax.swing.JLabel lbl_iconoClick;
    private javax.swing.JLabel lbl_iconoDeslizar;
    private javax.swing.JLabel lbl_idEnunciado;
    private javax.swing.JLabel lbl_verVideo;
    private javax.swing.JPanel pnl_Principal;
    private javax.swing.JPanel pnl_ejerciciosUsuario;
    // End of variables declaration//GEN-END:variables
}
