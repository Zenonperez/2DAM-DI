/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package principal;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobClientBuilder;
import com.mycompany.customcomponentejercicio.SwipeListener;
import data.DataAccess;
import dto.Intent;
import dto.Usuari;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import principal.JDialogos.DialogoCerrarSesion;
import principal.tablemodels.UsuariosTableModel;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

/**
 *
 * @author Ziku
 */
public class Main extends javax.swing.JFrame {

    private String textoOriginal;
    private Usuari user;
    private DataAccess da = new DataAccess();
    private EmbeddedMediaPlayerComponent mediaPlayer;
    private boolean isPlaying;
    private String video = "void";
    private String videoFilePath;
    private Login login;
    private ArrayList<Intent> intentos = new ArrayList<>();
    private ArrayList<com.mycompany.customcomponentejercicio.CustomComponentEjercicio> componenteIntentos = new ArrayList<>();
    private int idEjer;
    private String direccion;
    private Color verdePastel = new Color(205,255,205);
    private Color azulPastel = new Color(173,216,230);
    private Color amarilloPastel = new Color(255,255,205);
   
    
    public Main() {

        login = new Login(this, true, user, false);
        login.setVisible(true);
        if (login.EstasConectado()) {
            initComponents();

        } else {
            System.exit(0);
        }
        btn_Valorar.setBackground(Color.WHITE);
        btn_InformaciónIntentos.setBackground(Color.WHITE);
        btn_cerrarSesion.setBackground(Color.WHITE);
        tbl_Usuarios.setSelectionForeground(Color.BLACK);
        tbl_Usuarios.setSelectionBackground(verdePastel);
        Image icono = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/iconos/logoIcono.jpg"));
        setIconImage(icono);
        btn_ReproducirPausar.setEnabled(false);
        btn_SeleccionarUsuarios.setEnabled(false);
        btn_InformaciónIntentos.setEnabled(false);
        btn_Valorar.setEnabled(false);
        lbl_UsuarioConectado.setText(user.getNombre());
        leerIntentos();
        tbl_Usuarios.setModel(new UsuariosTableModel(da.getAllUsers()));
        mediaPlayer = new EmbeddedMediaPlayerComponent();
        pnl_ReproductorVideos.add(mediaPlayer, BorderLayout.CENTER);
        setMinimumSize(getPreferredSize());
        
        this.addComponentListener(new ComponentAdapter(){
           
            @Override
            public void componentResized(ComponentEvent e){
            Dimension tamanoActual = getSize();
            Dimension tamanoPreferido = getPreferredSize();
            
            
            if (tamanoActual.width < tamanoPreferido.width || tamanoActual.height < tamanoPreferido.height){
                setSize(tamanoPreferido);
                }
                }
        });
        
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                lbl_UsuarioConectado.setText(user.getNombre());
            }

        });
        

        

    }

    private void leerIntentos() {
        intentos = da.getAttemptsPendingReview();
        for (Intent intent : intentos) {
            com.mycompany.customcomponentejercicio.CustomComponentEjercicio pnl_customComponentEjercicio = new com.mycompany.customcomponentejercicio.CustomComponentEjercicio();
            pnl_customComponentEjercicio.setnombreEjercicio(intent.getNombreEjercicio());
            pnl_customComponentEjercicio.setnombreUsuario(intent.getNombreUsuario());
            pnl_customComponentEjercicio.setfechaIntento(intent.getTimestamp_Inicio());
            pnl_customComponentEjercicio.setIdEjercicio(intent.getId());
            pnl_customComponentEjercicio.setestadoIntento(1);
            pnl_IntentosPendientes.add(pnl_customComponentEjercicio);
            pnl_customComponentEjercicio.setFocusable(true);
            pnl_customComponentEjercicio.addSwipeListener(new SwipeListener() {
                @Override
                public void arrastrar(String dirrecion, int idEjercicio) {
                    direccion = dirrecion;
                    idEjer = idEjercicio;
                    if (direccion.equals("right")) {
                        Intent intentoPendiente = buscarIntento(idEjer);
                        
                        if (intentoPendiente != null) {
                            video = intentoPendiente.getVideofile();
                        }
                        reproducirVideo(video);
                    }
                    if (direccion.equals("left")){
                        btn_InformaciónIntentos.setEnabled(true);
                        btn_Valorar.setEnabled(true);
                    }
                    SeleccionarComponente(idEjer);
                }
            });
            componenteIntentos.add(pnl_customComponentEjercicio);
        }

    }
    
    
    
    public void SeleccionarComponente(int idEjer){

        for (com.mycompany.customcomponentejercicio.CustomComponentEjercicio componente : componenteIntentos){
            
            if(direccion.equals("right")){
                componente.setIntentoSeleccionado(false);
                componente.cambiarColor(Color.LIGHT_GRAY);
            }
                      
            
            if (componente.getIdEjercicio() == idEjer && direccion.equals("right")){
                componente.cambiarColor(amarilloPastel);
                componente.setIntentoSeleccionado(true);
                
            }
          
            if (componente.getIdEjercicio() == idEjer && direccion.equals("left")){ 
                componente.cambiarColor(verdePastel);
                if (componente.getIntentoSeleccionado()){
                   componente.mantenerColor(amarilloPastel);
                }
               
                
            }
            else{
                if (!componente.getIntentoSeleccionado()){
                componente.cambiarColor(Color.lightGray);
                componente.setIntentoSeleccionado(false);
                }
          
                       
            }
        }
        
    }

  
    public Intent SeleccionIntento(int idEjercicio) {
        for (Intent intent : intentos){
            if(intent.getId() == idEjercicio){
                return intent;
            }
           
        }
        return null;
    }
    public int SeleccionFilaUsuariosIntentos() {
        int seleccionado = tbl_Usuarios.convertRowIndexToModel(tbl_Usuarios.getSelectedRow());
        return seleccionado;
    }

    public void UsuarioConectado(Usuari user) {
        this.user = user;
    }
    
   

    public void reproducirVideo(String video) {
        this.video = video;
        //String connectionAzureStr = "secret";
        String connectionAzureStr = "secret";
        //String containerName = "videos";
        String containerName = "simulapvideoscontainer";
        BlobClient blobClient = new BlobClientBuilder().connectionString(connectionAzureStr)
                .blobName("uploaded_user_videos/" + video)
                .containerName(containerName)
                .buildClient();
        String tempDir = System.getProperty("java.io.tmpdir");
        String downloadPath = tempDir + File.separator + video;
        File existe = new File(downloadPath);
        if (!existe.exists()) {
            blobClient.downloadToFile(downloadPath);
        }
        mediaPlayer.mediaPlayer().media().play(downloadPath);
        isPlaying = true;
        btn_ReproducirPausar.setEnabled(true);
        btn_ReproducirPausar.setText("Pausar");

    }

    public int getIDIntento() {
        for (Intent intento: intentos){
            if (intento.getId()== idEjer){
                return intento.getId();
            }
        }
        return 0;
    }
    public Intent buscarIntento(int idEjer) {
        for (Intent intento : intentos) {
            if (intento.getId() == idEjer) {
                return intento;
            }
        }
        return null;
    }
    
    public void revertirAlValorar(){
        mediaPlayer.mediaPlayer().controls().stop();
        isPlaying = false;
        btn_ReproducirPausar.setText("Reproducir");
        btn_ReproducirPausar.setEnabled(false);
        btn_InformaciónIntentos.setEnabled(false);
        btn_Valorar.setEnabled(false);
    }
    
    public String buscarIDUsuario(int idEjer){
        for (Intent intento : intentos){
            if (intento.getId() == idEjer){
                return intento.getNombreUsuario();
            }
        }
        return null;
    }

    public int getIDUsuario() {
        return user.getId();
    }

    public void ActualizarCambiosIntentosPendientes() {
        componenteIntentos.clear();
        pnl_IntentosPendientes.removeAll();
        leerIntentos();
        repaint();
        
        
    }
    public void desconectarse() {
        setVisible(false);
        lbl_UsuarioConectado.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_panelPrincipal = new javax.swing.JPanel();
        btn_cerrarSesion = new javax.swing.JButton();
        pnl_ReproductorVideos = new javax.swing.JPanel();
        btn_ReproducirPausar = new javax.swing.JButton();
        lbl_Usuario = new javax.swing.JLabel();
        tab_IntentosUsuarios = new javax.swing.JTabbedPane();
        pnl_GestorIntentosPedientes = new javax.swing.JPanel();
        btn_InformaciónIntentos = new javax.swing.JButton();
        lbl_verVideo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnl_IntentosPendientes = new javax.swing.JPanel();
        btn_Valorar = new javax.swing.JButton();
        lbl_ActivarBotones = new javax.swing.JLabel();
        lbl_iconosDeslizar = new javax.swing.JLabel();
        lbl_IconoClick = new javax.swing.JLabel();
        pnl_Usuarios = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbl_Usuarios = new javax.swing.JTable();
        btn_SeleccionarUsuarios = new javax.swing.JButton();
        lbl_textoInfoTablaUsuarios = new javax.swing.JLabel();
        lbl_UsuarioConectado = new javax.swing.JLabel();
        albl_animado = new com.mycompany.animatedlabel.AnimatedLabel();
        lbl_nombrePrograma = new javax.swing.JLabel();
        lbl_logoImagen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Fitnow");
        setBackground(new java.awt.Color(51, 51, 51));
        setMinimumSize(new java.awt.Dimension(652, 810));
        setSize(new java.awt.Dimension(810, 651));
        getContentPane().setLayout(new javax.swing.OverlayLayout(getContentPane()));

        pnl_panelPrincipal.setBackground(new java.awt.Color(51, 51, 51));
        pnl_panelPrincipal.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        pnl_panelPrincipal.setMinimumSize(new java.awt.Dimension(810, 651));
        pnl_panelPrincipal.setPreferredSize(new java.awt.Dimension(810, 651));

        btn_cerrarSesion.setText("Cerrar Sesion");
        btn_cerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_cerrarSesionMouseReleased(evt);
            }
        });
        btn_cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarSesionActionPerformed(evt);
            }
        });

        pnl_ReproductorVideos.setBackground(new java.awt.Color(51, 51, 51));
        pnl_ReproductorVideos.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Video Reproductor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        pnl_ReproductorVideos.setForeground(new java.awt.Color(255, 255, 255));
        pnl_ReproductorVideos.setMinimumSize(new java.awt.Dimension(273, 201));
        pnl_ReproductorVideos.setPreferredSize(new java.awt.Dimension(273, 201));
        pnl_ReproductorVideos.setLayout(new java.awt.BorderLayout());

        btn_ReproducirPausar.setText("Reproducir");
        btn_ReproducirPausar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ReproducirPausarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ReproducirPausarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ReproducirPausarMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btn_ReproducirPausarMouseReleased(evt);
            }
        });
        btn_ReproducirPausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReproducirPausarActionPerformed(evt);
            }
        });

        lbl_Usuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_Usuario.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Usuario.setText("Usuario:");

        tab_IntentosUsuarios.setBackground(new java.awt.Color(153, 153, 153));
        tab_IntentosUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab_IntentosUsuariosMouseEntered(evt);
            }
        });

        pnl_GestorIntentosPedientes.setBackground(new java.awt.Color(51, 51, 51));
        pnl_GestorIntentosPedientes.setForeground(new java.awt.Color(255, 255, 255));
        pnl_GestorIntentosPedientes.setToolTipText("");

        btn_InformaciónIntentos.setText("Info");
        btn_InformaciónIntentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_InformaciónIntentosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_InformaciónIntentosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_InformaciónIntentosMouseExited(evt);
            }
        });
        btn_InformaciónIntentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InformaciónIntentosActionPerformed(evt);
            }
        });

        lbl_verVideo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_verVideo.setForeground(new java.awt.Color(255, 255, 204));
        lbl_verVideo.setText("Deslizar para ver video:");

        pnl_IntentosPendientes.setBackground(new java.awt.Color(51, 51, 51));
        pnl_IntentosPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnl_IntentosPendientes.setLayout(new javax.swing.BoxLayout(pnl_IntentosPendientes, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(pnl_IntentosPendientes);

        btn_Valorar.setText("Valorar");
        btn_Valorar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ValorarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ValorarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ValorarMouseExited(evt);
            }
        });
        btn_Valorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ValorarActionPerformed(evt);
            }
        });
        btn_Valorar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_ValorarKeyPressed(evt);
            }
        });

        lbl_ActivarBotones.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_ActivarBotones.setForeground(new java.awt.Color(204, 255, 204));
        lbl_ActivarBotones.setText("Activar Botones:");

        lbl_iconosDeslizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/deslizar.png"))); // NOI18N

        lbl_IconoClick.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/click.png"))); // NOI18N

        javax.swing.GroupLayout pnl_GestorIntentosPedientesLayout = new javax.swing.GroupLayout(pnl_GestorIntentosPedientes);
        pnl_GestorIntentosPedientes.setLayout(pnl_GestorIntentosPedientesLayout);
        pnl_GestorIntentosPedientesLayout.setHorizontalGroup(
            pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                                .addComponent(lbl_ActivarBotones)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_IconoClick))
                            .addComponent(btn_Valorar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                        .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                                .addComponent(lbl_verVideo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_iconosDeslizar))
                            .addComponent(btn_InformaciónIntentos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnl_GestorIntentosPedientesLayout.setVerticalGroup(
            pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_InformaciónIntentos)
                    .addComponent(btn_Valorar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addComponent(lbl_verVideo)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addGroup(pnl_GestorIntentosPedientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_IconoClick, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_iconosDeslizar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_GestorIntentosPedientesLayout.createSequentialGroup()
                        .addComponent(lbl_ActivarBotones)
                        .addGap(48, 48, 48))))
        );

        tab_IntentosUsuarios.addTab("Intentos Pendientes", pnl_GestorIntentosPedientes);

        pnl_Usuarios.setBackground(new java.awt.Color(51, 51, 51));
        pnl_Usuarios.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tbl_Usuarios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        tbl_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        tbl_Usuarios.setGridColor(new java.awt.Color(255, 255, 255));
        tbl_Usuarios.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tbl_UsuariosMouseMoved(evt);
            }
        });
        tbl_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_UsuariossMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_UsuariosMouseEntered(evt);
            }
        });
        jScrollPane6.setViewportView(tbl_Usuarios);

        btn_SeleccionarUsuarios.setText("Seleccionar");
        btn_SeleccionarUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SeleccionarUsuariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_SeleccionarUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_SeleccionarUsuariosMouseExited(evt);
            }
        });
        btn_SeleccionarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeleccionarUsuariossActionPerformed(evt);
            }
        });

        lbl_textoInfoTablaUsuarios.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_textoInfoTablaUsuarios.setForeground(new java.awt.Color(255, 255, 255));
        lbl_textoInfoTablaUsuarios.setText("Haz click en un usuario de la tabla");

        javax.swing.GroupLayout pnl_UsuariosLayout = new javax.swing.GroupLayout(pnl_Usuarios);
        pnl_Usuarios.setLayout(pnl_UsuariosLayout);
        pnl_UsuariosLayout.setHorizontalGroup(
            pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_SeleccionarUsuarios)
                        .addGap(15, 15, 15))))
            .addGroup(pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_textoInfoTablaUsuarios)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnl_UsuariosLayout.setVerticalGroup(
            pnl_UsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnl_UsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_SeleccionarUsuarios)
                .addGap(52, 52, 52)
                .addComponent(lbl_textoInfoTablaUsuarios)
                .addGap(142, 142, 142))
        );

        tab_IntentosUsuarios.addTab("Gestion de usuarios", pnl_Usuarios);

        lbl_UsuarioConectado.setForeground(new java.awt.Color(255, 255, 255));
        lbl_UsuarioConectado.setText("jLabel1");

        albl_animado.setForeground(new java.awt.Color(255, 255, 255));
        albl_animado.setAnimated(true);
        albl_animado.setAppendedText("ienvenido");
        albl_animado.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        lbl_nombrePrograma.setFont(new java.awt.Font("Visitor TT1 BRK", 1, 50)); // NOI18N
        lbl_nombrePrograma.setForeground(new java.awt.Color(255, 255, 255));
        lbl_nombrePrograma.setText("FITNOW");

        lbl_logoImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/FitnowSoloImagenMain.jpg"))); // NOI18N

        javax.swing.GroupLayout pnl_panelPrincipalLayout = new javax.swing.GroupLayout(pnl_panelPrincipal);
        pnl_panelPrincipal.setLayout(pnl_panelPrincipalLayout);
        pnl_panelPrincipalLayout.setHorizontalGroup(
            pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                                .addComponent(lbl_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_UsuarioConectado, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(albl_animado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_nombrePrograma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cerrarSesion)
                        .addGap(34, 34, 34))
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addComponent(tab_IntentosUsuarios)
                        .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(pnl_ReproductorVideos, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                                .addGap(16, 16, 16))
                            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_ReproducirPausar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_logoImagen))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        pnl_panelPrincipalLayout.setVerticalGroup(
            pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(albl_animado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_Usuario)
                            .addComponent(lbl_UsuarioConectado))
                        .addGap(23, 23, 23))
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cerrarSesion)
                            .addComponent(lbl_nombrePrograma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)))
                .addGroup(pnl_panelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tab_IntentosUsuarios)
                    .addGroup(pnl_panelPrincipalLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(pnl_ReproductorVideos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_ReproducirPausar)
                        .addGap(34, 34, 34)
                        .addComponent(lbl_logoImagen)
                        .addGap(35, 35, 35))))
        );

        getContentPane().add(pnl_panelPrincipal);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarSesionActionPerformed
        DialogoCerrarSesion cerrarSesion = new DialogoCerrarSesion(this, true);
        cerrarSesion.setVisible(true);
    }//GEN-LAST:event_btn_cerrarSesionActionPerformed

    private void btn_ReproducirPausarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarActionPerformed
        if (isPlaying) {
            mediaPlayer.mediaPlayer().controls().pause();
            isPlaying = false;
            btn_ReproducirPausar.setText("Reproducir");
        } else {
            mediaPlayer.mediaPlayer().controls().start();
            isPlaying = true;
            btn_ReproducirPausar.setText("Pausar");
        }

    }//GEN-LAST:event_btn_ReproducirPausarActionPerformed

    private void btn_SeleccionarUsuariossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariossActionPerformed
        InformacionUsuario informacionUsuario = new InformacionUsuario(this, true);
        informacionUsuario.setVisible(true);
    }//GEN-LAST:event_btn_SeleccionarUsuariossActionPerformed

    private void tbl_UsuariossMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_UsuariossMouseClicked
        if (tbl_Usuarios.convertRowIndexToModel(tbl_Usuarios.getSelectedRow()) != -1) {
            btn_SeleccionarUsuarios.setEnabled(true);
        }
    }//GEN-LAST:event_tbl_UsuariossMouseClicked

    private void btn_InformaciónIntentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosActionPerformed
        InformacionIntento informacionIntento = new InformacionIntento(this, true,idEjer);
        informacionIntento.setVisible(true);
    }//GEN-LAST:event_btn_InformaciónIntentosActionPerformed

    private void btn_ValorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ValorarActionPerformed
       ValoracionIntentoSinReview valoracionIntentosSinReview = new ValoracionIntentoSinReview(this, true);
       valoracionIntentosSinReview.setVisible(true);
       ActualizarCambiosIntentosPendientes();
    }//GEN-LAST:event_btn_ValorarActionPerformed

    private void btn_ValorarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_ValorarKeyPressed
    }//GEN-LAST:event_btn_ValorarKeyPressed

    private void btn_ValorarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ValorarMouseEntered
        if (btn_Valorar.isEnabled()){
            btn_Valorar.setBackground(azulPastel);
        }
    }//GEN-LAST:event_btn_ValorarMouseEntered

    private void btn_ValorarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ValorarMouseClicked
           btn_Valorar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_ValorarMouseClicked

    private void btn_ValorarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ValorarMouseExited
        if(btn_Valorar.isEnabled()){
            btn_Valorar.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_btn_ValorarMouseExited

    private void btn_InformaciónIntentosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosMouseEntered
        if (btn_InformaciónIntentos.isEnabled()){
            btn_InformaciónIntentos.setBackground(azulPastel);
        }
    }//GEN-LAST:event_btn_InformaciónIntentosMouseEntered

    private void btn_InformaciónIntentosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosMouseExited
        if(btn_InformaciónIntentos.isEnabled()){
            btn_InformaciónIntentos.setBackground(Color.WHITE);
        }
    }//GEN-LAST:event_btn_InformaciónIntentosMouseExited

    private void btn_InformaciónIntentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_InformaciónIntentosMouseClicked
            btn_InformaciónIntentos.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_InformaciónIntentosMouseClicked

    private void btn_cerrarSesionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMouseEntered
        btn_cerrarSesion.setBackground(azulPastel);
    }//GEN-LAST:event_btn_cerrarSesionMouseEntered

    private void btn_cerrarSesionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMouseExited
        btn_cerrarSesion.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_cerrarSesionMouseExited

    private void btn_cerrarSesionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMousePressed
        btn_cerrarSesion.setBackground(Color.LIGHT_GRAY);
        for (com.mycompany.customcomponentejercicio.CustomComponentEjercicio componente : componenteIntentos){
            componente.cambiarColor(Color.LIGHT_GRAY);
            componente.setIntentoSeleccionado(false);
        }
    }//GEN-LAST:event_btn_cerrarSesionMousePressed

    private void btn_cerrarSesionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cerrarSesionMouseReleased
        btn_cerrarSesion.setBackground(azulPastel);
    }//GEN-LAST:event_btn_cerrarSesionMouseReleased

    private void tab_IntentosUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_IntentosUsuariosMouseEntered
       
    }//GEN-LAST:event_tab_IntentosUsuariosMouseEntered

    private void tbl_UsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_UsuariosMouseEntered
        
    }//GEN-LAST:event_tbl_UsuariosMouseEntered

    private void tbl_UsuariosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_UsuariosMouseMoved

    }//GEN-LAST:event_tbl_UsuariosMouseMoved

    private void btn_ReproducirPausarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarMouseEntered
        btn_ReproducirPausar.setBackground(azulPastel);
    }//GEN-LAST:event_btn_ReproducirPausarMouseEntered

    private void btn_ReproducirPausarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarMouseExited
        btn_ReproducirPausar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btn_ReproducirPausarMouseExited

    private void btn_ReproducirPausarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarMouseClicked
        btn_ReproducirPausar.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_ReproducirPausarMouseClicked

    private void btn_ReproducirPausarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReproducirPausarMouseReleased
        btn_ReproducirPausar.setBackground(azulPastel);
    }//GEN-LAST:event_btn_ReproducirPausarMouseReleased

    private void btn_SeleccionarUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariosMouseClicked
        btn_SeleccionarUsuarios.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_btn_SeleccionarUsuariosMouseClicked

    private void btn_SeleccionarUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariosMouseEntered
        btn_SeleccionarUsuarios.setBackground(azulPastel);
    }//GEN-LAST:event_btn_SeleccionarUsuariosMouseEntered

    private void btn_SeleccionarUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SeleccionarUsuariosMouseExited
        btn_SeleccionarUsuarios.setBackground(Color.white);
    }//GEN-LAST:event_btn_SeleccionarUsuariosMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.animatedlabel.AnimatedLabel albl_animado;
    private javax.swing.JButton btn_InformaciónIntentos;
    private javax.swing.JButton btn_ReproducirPausar;
    private javax.swing.JButton btn_SeleccionarUsuarios;
    private javax.swing.JButton btn_Valorar;
    private javax.swing.JButton btn_cerrarSesion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lbl_ActivarBotones;
    private javax.swing.JLabel lbl_IconoClick;
    private javax.swing.JLabel lbl_Usuario;
    private javax.swing.JLabel lbl_UsuarioConectado;
    private javax.swing.JLabel lbl_iconosDeslizar;
    private javax.swing.JLabel lbl_logoImagen;
    private javax.swing.JLabel lbl_nombrePrograma;
    private javax.swing.JLabel lbl_textoInfoTablaUsuarios;
    private javax.swing.JLabel lbl_verVideo;
    private javax.swing.JPanel pnl_GestorIntentosPedientes;
    private javax.swing.JPanel pnl_IntentosPendientes;
    private javax.swing.JPanel pnl_ReproductorVideos;
    private javax.swing.JPanel pnl_Usuarios;
    private javax.swing.JPanel pnl_panelPrincipal;
    private javax.swing.JTabbedPane tab_IntentosUsuarios;
    private javax.swing.JTable tbl_Usuarios;
    // End of variables declaration//GEN-END:variables
}
