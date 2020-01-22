/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModRegUsua.java
 *
 * Created on 13-dic-2017, 12:39:45
 */
package Principal;

import Segundario.bdc;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author back
 */
public class ModRegUsua extends javax.swing.JFrame {
bdc cc = new bdc();
        Connection cn = cc.Conectar();
        int Ac,vali=0;
        
        
         String Ho,Hora,Hora2,Horaf,est,cedu,acti="ninguna",fech,nom,sa="0";  
        String cod,US,CO;
        int cancar=0,teln=0;
                ;
    /** Creates new form ModRegUsua */
    public ModRegUsua() {
         setUndecorated(true);
        initComponents();
        i();
          setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
        selecto();
        bloqueo();
       Ac();
       reloj();
        
        
    }
    
      public void salida(){
       dispose();
    }
     void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ImaPeq/logo.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
     private static final char[] CONSTS_HEX = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' }; 
     public static String encriptaEnMD5(String stringAEncriptar)  
     {        
         try        {     
             MessageDigest msgd = MessageDigest.getInstance("MD5");      
             byte[] bytes = msgd.digest(stringAEncriptar.getBytes());     
             StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length); 
             for (int i = 0; i < bytes.length; i++)    
             {            
                 int bajo = (int)(bytes[i] & 0x0f);  
             int alto = (int)((bytes[i] & 0xf0) >> 4);    
             strbCadenaMD5.append(CONSTS_HEX[alto]);      
             strbCadenaMD5.append(CONSTS_HEX[bajo]);           }  
             return strbCadenaMD5.toString();      
         } catch (NoSuchAlgorithmException e) {         
             return null;        }  
     }     
     public static String fechaActual1()
                {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyy");
        return formatoFecha.format(fecha);
                }
      
  void reloj()
  {
    Calendar car = Calendar.getInstance();
    
    String ap = String.valueOf(car.get(9));
    String mp;
   
    if (ap.equals("0")) {
      mp = "AM";
    } else {
      mp = "PM";
    }
   
    Hora = (car.get(10) + ":" + car.get(12) + ":" + car.get(13) + " " + mp);
  
  }
   void reloj2()
  {
    Calendar car = Calendar.getInstance();
    
    String ap = String.valueOf(car.get(9));
    String mp;
   
    if (ap.equals("0")) {
      mp = "AM";
    } else {
      mp = "PM";
    }
   
  
    Hora2 = (car.get(10) + ":" + car.get(12) + ":" + car.get(13) + " " + mp);
  }
   void fpa()
  {
 
    if(sa.equals("1")){
     Ho=Hora;   
    }else{
      reloj2();    
     Ho=Hora2;   
    }
   
    fech = fechaActual1();
    
    String SQL4 = "INSERT auditoria  (cedula,nombre,horaentrada,proceso,actividad,horasalida,fecha) VALUES (?,?,?,?,?,?,?)";
    try
    {
      PreparedStatement psd4 = this.cn.prepareStatement(SQL4);
       psd4.setString(1, cedu);
       psd4.setString(2,nom);
       psd4.setString(3, Ho);
       psd4.setString(4,"REGISTRO DE USUARIO");
       psd4.setString(5, acti); 
       reloj2();
       psd4.setString(6, Hora2);
       psd4.setString(7,fech);
      psd4.executeUpdate();
     
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Error PROCESO/ACTIVIDAD");
    }
  }
      void Ac(){
           
        try{
       
              
     String SQL5="SELECT * FROM datos_usuario  WHERE estado <> 0 AND estado <> -2 ";       
      Statement st5 = (Statement) cn.createStatement();   
      ResultSet rsu =   st5.executeQuery(SQL5);
      while (rsu.next()){   
        est=rsu.getString("estado"); 
        cedu=rsu.getString("cedula");  
          nom=rsu.getString("nombre");  
                        }
      
       
      
       
        
         }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
    
       }
    
  void borrar(){
      rdf.setSelected(false);
   rdma.setSelected(false);
   txtape.setText("");
     txtcorr.setText("");
    txtCedu.setText("");
    txtNom.setText("");
    boxco.setSelectedItem("--Seleccione--");
    txtTel.setText("");
    txtusu.setText("");
    txtCo.setText("");
      FF1.setText("No pose");
   FF2.setText("No pose");
   fotol1.setIcon(null);
    fotol1.setText("FOTO");
   txtpre.setText("");
   txtres.setText("");
    txtCo.setBackground(Color.WHITE); 
    txtTel.setBackground(Color.WHITE);
    
        
    }
     void bloqueo(){
         
          rdf.setEnabled(false);
   rdma.setEnabled(false);
   txtape.setEnabled(false);
     txtcorr.setEnabled(false);
    txtCedu.setEnabled(false);
    txtNom.setEnabled(false);
    boxco.setEnabled(false);
    txtTel.setEnabled(false);
    txtusu.setEnabled(false);
    txtCo.setEnabled(false);
       fotol1.setEnabled(false);  
       txtpre.setEnabled(false);  
   txtres.setEnabled(false);  
    }
      void desbloqueo(){
             rdf.setEnabled(true);
   rdma.setEnabled(true);
   txtape.setEnabled(true);
     txtcorr.setEnabled(true);
    txtCedu.setEnabled(true);
    txtNom.setEnabled(true);
    boxco.setEnabled(true);
    txtTel.setEnabled(true);
    txtusu.setEnabled(true);
    txtCo.setEnabled(true);
        fotol1.setEnabled(true); 
         txtpre.setEnabled(true);  
   txtres.setEnabled(true);  
    }
       void selecto() {
       
  
    try
    {
        
  
    String  SQL = "select * from codigo_persona";
      
   Statement st = this.cn.createStatement();
    ResultSet  rs = st.executeQuery(SQL);
      while (rs.next())
      {
          
      boxco.addItem( rs.getString("Descripcion"));
      }
             }     
     
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
      }
       
       void contraseña(){
         vali=0;
         String password="";
         byte  contNumero = 0, contLetraMay = 0, contLetraMin=0;
       //1 mayuscula, 1 minuscula, 1 numero minimo
        password = txtCo.getText();
       char clave;
      
       for (byte i = 0; i < password.length(); i++) {
                clave = password.charAt(i);
               String passValue = String.valueOf(clave);
                if (passValue.matches("[A-Z]")) {
                    contLetraMay++;
                } 
                if (passValue.matches("[a-z]")) {
                    contLetraMin++;
                } 
                if (passValue.matches("[0-9]")) {
                    contNumero++;
                }
             
        }
        if( contLetraMay!=0 && contLetraMin!=0 && contNumero!=0){
            vali=0;
      
     } else{
           vali=1;
             
        } 
 
 
    
       }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCedu = new javax.swing.JTextField();
        txtNom = new javax.swing.JTextField();
        txtape = new javax.swing.JTextField();
        txtTel = new javax.swing.JTextField();
        txtcorr = new javax.swing.JTextField();
        txtusu = new javax.swing.JTextField();
        txtCo = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn = new javax.swing.JButton();
        btg = new javax.swing.JButton();
        bte = new javax.swing.JButton();
        btel = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        rdma = new javax.swing.JRadioButton();
        rdf = new javax.swing.JRadioButton();
        boxco = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        fotol1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        FF1 = new javax.swing.JTextField();
        FF2 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtpre = new javax.swing.JTextField();
        txtres = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(-1,true));
        jPanel1.setLayout(null);

        txtCedu.setFont(new java.awt.Font("Dialog", 1, 18));
        jPanel1.add(txtCedu);
        txtCedu.setBounds(290, 80, 239, 35);

        txtNom.setFont(new java.awt.Font("Dialog", 1, 18));
        jPanel1.add(txtNom);
        txtNom.setBounds(210, 150, 200, 35);

        txtape.setFont(new java.awt.Font("Dialog", 1, 18));
        jPanel1.add(txtape);
        txtape.setBounds(420, 150, 200, 35);

        txtTel.setFont(new java.awt.Font("Dialog", 1, 18));
        txtTel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTelMouseClicked(evt);
            }
        });
        jPanel1.add(txtTel);
        txtTel.setBounds(190, 390, 288, 35);

        txtcorr.setFont(new java.awt.Font("Dialog", 1, 18));
        jPanel1.add(txtcorr);
        txtcorr.setBounds(500, 390, 190, 35);

        txtusu.setFont(new java.awt.Font("Dialog", 1, 18));
        jPanel1.add(txtusu);
        txtusu.setBounds(660, 80, 200, 35);

        txtCo.setFont(new java.awt.Font("Dialog", 1, 18));
        txtCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCoMouseClicked(evt);
            }
        });
        jPanel1.add(txtCo);
        txtCo.setBounds(660, 150, 200, 35);

        jLabel1.setBackground(new java.awt.Color(51, 102, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIÓN DE USUARIO");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 910, 44);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(null);

        btn.setBackground(new java.awt.Color(51, 51, 51));
        btn.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Add.png"))); // NOI18N
        btn.setText("Nuevo");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        jPanel3.add(btn);
        btn.setBounds(10, 10, 170, 60);

        btg.setBackground(new java.awt.Color(51, 51, 51));
        btg.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Save.png"))); // NOI18N
        btg.setText("Guardar");
        btg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgActionPerformed(evt);
            }
        });
        jPanel3.add(btg);
        btg.setBounds(10, 80, 170, 60);

        bte.setBackground(new java.awt.Color(51, 51, 51));
        bte.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        bte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Modify.png"))); // NOI18N
        bte.setText("Editar");
        bte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteActionPerformed(evt);
            }
        });
        jPanel3.add(bte);
        bte.setBounds(10, 150, 170, 60);

        btel.setBackground(new java.awt.Color(51, 51, 51));
        btel.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Delete.png"))); // NOI18N
        btel.setText("Eliminar");
        btel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btelActionPerformed(evt);
            }
        });
        jPanel3.add(btel);
        btel.setBounds(10, 220, 170, 60);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.2.png"))); // NOI18N
        jPanel3.add(jLabel12);
        jLabel12.setBounds(0, 250, 240, 110);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 60, 190, 290);

        rdma.setFont(new java.awt.Font("Dialog", 1, 14));
        rdma.setText("Masculino");
        rdma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdmaMouseClicked(evt);
            }
        });
        rdma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdmaActionPerformed(evt);
            }
        });
        jPanel1.add(rdma);
        rdma.setBounds(240, 240, 100, 27);

        rdf.setFont(new java.awt.Font("Dialog", 1, 14));
        rdf.setText("Femenino");
        rdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdfMouseClicked(evt);
            }
        });
        jPanel1.add(rdf);
        rdf.setBounds(240, 280, 100, 27);

        boxco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        boxco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--", "55" }));
        boxco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxcoActionPerformed(evt);
            }
        });
        jPanel1.add(boxco);
        boxco.setBounds(20, 390, 160, 30);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(128, 128, 128));

        fotol1.setBackground(new java.awt.Color(204, 204, 255));
        fotol1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fotol1.setText("FOTO");
        fotol1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fotol1.setOpaque(true);
        fotol1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fotol1MouseClicked(evt);
            }
        });
        fotol1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fotol1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fotol1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fotol1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(400, 190, 200, 148);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cedula");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(290, 50, 240, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(210, 120, 200, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Apellido");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(420, 120, 200, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Sexo");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(220, 210, 140, 30);

        jLabel8.setBackground(new java.awt.Color(-13408513,true));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Telefono");
        jLabel8.setOpaque(true);
        jPanel1.add(jLabel8);
        jLabel8.setBounds(10, 360, 480, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Contraseña");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(660, 120, 200, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Respuesta");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(670, 260, 200, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Usuario");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(660, 50, 200, 30);

        FF1.setForeground(new java.awt.Color(255, 255, 255));
        FF1.setBorder(null);
        jPanel1.add(FF1);
        FF1.setBounds(860, 10, 30, 14);

        FF2.setForeground(new java.awt.Color(255, 255, 255));
        FF2.setBorder(null);
        jPanel1.add(FF2);
        FF2.setBounds(800, 20, 30, 14);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 360, 480, 80);

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/exits.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7);
        jLabel7.setBounds(810, 420, 90, 80);

        txtpre.setFont(new java.awt.Font("Dialog", 1, 18));
        txtpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpreActionPerformed(evt);
            }
        });
        jPanel1.add(txtpre);
        txtpre.setBounds(650, 220, 230, 40);

        txtres.setFont(new java.awt.Font("Dialog", 1, 18));
        jPanel1.add(txtres);
        txtres.setBounds(650, 290, 230, 40);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Correo");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(500, 360, 190, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Crea Tu Pregunta");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(650, 190, 230, 30);

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/download.png"))); // NOI18N
        jLabel15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel15.setOpaque(true);
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel15);
        jLabel15.setBounds(710, 420, 90, 80);

        jLabel2.setBackground(new java.awt.Color(-13408513,true));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel2.setForeground(new java.awt.Color(-1,true));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SALIR");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(810, 390, 90, 20);

        jLabel16.setBackground(new java.awt.Color(-13408513,true));
        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel16.setForeground(new java.awt.Color(-1,true));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("MINIMIZAR");
        jLabel16.setOpaque(true);
        jPanel1.add(jLabel16);
        jLabel16.setBounds(710, 390, 90, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void boxcoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxcoActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_boxcoActionPerformed

private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
     desbloqueo();
     borrar();
     Ac=1;
}//GEN-LAST:event_btnActionPerformed

private void btgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgActionPerformed
String ced,rol="7",nom,ape,sex="",code,tel,corr="",usu,con,est="0", foru,fo,ceco="0";
 
        foru=FF2.getText();
  fo=FF1.getText();
  
ced=txtCedu.getText();   
nom=txtNom.getText();
ape=txtape.getText();

     if(rdf.isSelected()){
         sex="Femenino";  
     } 
       if(rdma.isSelected()){
         sex="Masculino";   
     }
 corr=txtcorr.getText();
 code=String.valueOf(boxco.getSelectedItem());
 tel=txtTel.getText();
 usu=txtusu.getText();
 con=txtCo.getText();
 String descripcion=con;

    char[] arrayChar= descripcion.toCharArray();
    for(int i=0; i< arrayChar.length; i++){
    arrayChar[i]='0';
    cancar=descripcion.length();
}
 String descri=tel;
 
    char[] arrayCha= descri.toCharArray();
    for(int i=0; i< arrayCha.length; i++){
    arrayCha[i]='0';
    teln=descri.length();
}
    contraseña();
  
        String SQL;
        
        

        
      

        PreparedStatement psd;
 if(ced.equals("")||nom.equals("")||ape.equals("")||sex.equals("")||code.equals("--Seleccione--")||tel.equals("")){
     JOptionPane.showMessageDialog(this,"¡¡Por favor Verifique los Campos!!");
    
    
 } else{
     if(cancar<8||(teln<7 || teln >7 )||vali==1){
           if(cancar<8){
    txtCo.setBackground(Color.red); 
    JOptionPane.showMessageDialog(this,"Su Contraseña es Muy Corta");
  }
      if(teln<7 || teln >7 ){
    txtTel.setBackground(Color.red); 
    JOptionPane.showMessageDialog(this,"Numero de Telefono Errado");
  }
     if(vali==1 ){
    JOptionPane.showMessageDialog(this,"La contraseña debe de tener Nuemeros y letras Mayusculas");       
  }
     }else{     
         
        switch(Ac){
           
            case 1:

            try{
                          String  SQL0 = "select * from datos_usuario where cedula LIKE '%" + ced + "%'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQL0);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
           } 
        if(ceco.equals(ced)){
        JOptionPane.showMessageDialog(this,"Cedula Ya Existe");     
        }else{

                SQL="INSERT INTO datos_usuario(cedula,nombre,apellido,sexo,codigo,telefono,correo,usuario,contraseña,estado,rol,rufo,nofo,pregunta,respuesta)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,ced);
                psd.setString(2,nom);
                psd.setString(3,ape);
                psd.setString(4,sex);
                psd.setString(5,code);
                 
                psd.setString(6,tel);
                psd.setString(7,corr);
                psd.setString(8,encriptaEnMD5(usu));
                psd.setString(9,encriptaEnMD5(con));
               
                psd.setString(10,est);
                 psd.setString(11,rol);
                  psd.setString(12,foru);
                 psd.setString(13,fo);
                  psd.setString(14,txtpre.getText() );
                 psd.setString(15, txtres.getText());
                  
   
                 int m= psd.executeUpdate();
                  
               
                  
                

                if((m>0)){
                   borrar();
                    bloqueo();
                    JOptionPane.showMessageDialog(null,"Guardo con exito");
                }
        }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
            

            break;
            case 2:
            
            try {

             
                SQL="UPDATE datos_usuario set cedula=?,nombre=?,apellido=?,sexo=?,codigo=?,telefono=?,correo=?,usuario=?,contraseña=?,rufo=?,nofo=?,pregunta=?,respuesta=? WHERE cedula=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,ced);
                psd.setString(2,nom);
                psd.setString(3,ape);
                psd.setString(4,sex);
                psd.setString(5,code);
                 
                psd.setString(6,tel);
                psd.setString(7,corr);
                psd.setString(8,encriptaEnMD5(usu));
                psd.setString(9,encriptaEnMD5(con));
                 psd.setString(10,foru);
                 psd.setString(11,fo);
                  psd.setString(12,txtpre.getText() );
                 psd.setString(13, txtres.getText());
                psd.setString(14,cod);
               
               
                
                int f=  psd.executeUpdate();
                
               
             
                 if((f>0)){
                   borrar();
                    bloqueo();
                    JOptionPane.showMessageDialog(null,"Guardo con exito");

                }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }

            break;

        }
        }
 }
}//GEN-LAST:event_btgActionPerformed

private void bteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteActionPerformed
Ac=2;
borrar();

String ceco="0";
   
   //cod = JOptionPane.showInputDialog("Cedula:");
   US=JOptionPane.showInputDialog("Usuario:");
   CO=JOptionPane.showInputDialog("Contraseña:");
    if(US.equals("")||CO.equals("")){
         JOptionPane.showMessageDialog(this, "Intentelo de nuevo");
         bloqueo();

    }else{
    try
    {
        String  SQLr = "select * from datos_usuario where usuario= '"+ encriptaEnMD5(US)+"' AND  contraseña='"+ encriptaEnMD5(CO)+"'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQLr);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
       
           } 
        if(ceco.equals("0")){
          JOptionPane.showMessageDialog(null, "Usuario no Existe");
          
        }else{
  String SQL1 = "SELECT * FROM datos_usuario WHERE usuario= '"+ encriptaEnMD5(US)+"' AND  contraseña='"+ encriptaEnMD5(CO)+"'";
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
         desbloqueo();      
      
  cod=rs.getString("cedula");
      txtCedu.setText(rs.getString("cedula"));
     
     txtNom.setText(rs.getString("nombre"));
       txtape.setText(rs.getString("apellido"));
 
     String sex=rs.getString("sexo");
     
     if(sex.equals("Femenino")) {
         rdf.isSelected();
         rdf.setSelected(true);
     }else{
         rdma.isSelected();
         rdma.setSelected(true);
     }
     
   
    
       txtcorr.setText(rs.getString("correo"));
         boxco.setSelectedItem(rs.getString("codigo"));
        
      txtTel.setText(rs.getString("telefono"));
      txtusu.setText(US);
      txtCo.setText(CO);
       
         FF2.setText(rs.getString("rufo"));
           String ft=FF2.getText();
    FF1.setText(rs.getString("nofo"));
    String fu=FF2.getText();
     File ruta = new File(fu);
     
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
           fotol1.setIcon(new ImageIcon(foto));
            if(ft.equals("")||ft.equals("No pose")){
         fotol1.setIcon(null);
              fotol1.setText("FOTO");
        }else{
                 fotol1.setText("");  
              }
       txtpre.setText(rs.getString("pregunta"));
       txtres.setText(rs.getString("respuesta"));
 
    
   
        }
    }
    catch (SQLException ex) {}
    
    }
}//GEN-LAST:event_bteActionPerformed

private void btelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btelActionPerformed
borrar(); String ceco="0";
bloqueo();
   
  US=JOptionPane.showInputDialog("Usuario:");
   CO=JOptionPane.showInputDialog("Contraseña:");
    if(US.equals("")||CO.equals("")){
         JOptionPane.showMessageDialog(this, "Intentelo de nuevo");
         borrar();
bloqueo();
    }else{
    try
    {
        String  SQLr = "select * from datos_usuario where usuario= '"+ encriptaEnMD5(US)+"' AND  contraseña='"+ encriptaEnMD5(CO)+"'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQLr);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
        bloqueo();
           } 
        if(ceco.equals("0")){
          JOptionPane.showMessageDialog(null, "Usuario no Existe");
          
        }else{
     String SQL1 = "SELECT * FROM datos_usuario WHERE usuario= '"+ encriptaEnMD5(US)+"' AND  contraseña='"+ encriptaEnMD5(CO)+"'";
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
      bloqueo();          
      
 
      txtCedu.setText(rs.getString("cedula"));
     txtNom.setText(rs.getString("nombre"));
       txtape.setText(rs.getString("apellido"));
 
     String sex=rs.getString("sexo");
     
     if(sex.equals("Femenino")) {
         rdf.isSelected();
         rdf.setSelected(true);
     }else{
         rdma.isSelected();
         rdma.setSelected(true);
     }
     
   
    
       txtcorr.setText(rs.getString("correo"));
         boxco.setSelectedItem(rs.getString("codigo"));
        
        txtTel.setText(rs.getString("telefono"));
      txtusu.setText(US);
      txtCo.setText(CO);
          FF2.setText(rs.getString("rufo"));
           String ft=FF2.getText();
    FF1.setText(rs.getString("nofo"));
    String fu=FF2.getText();
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
             
              if(ft.equals("")||ft.equals("No pose")){
         fotol1.setIcon(null);
              fotol1.setText("FOTO");
        }else{
                 fotol1.setText("");  
              }
           fotol1.setIcon(new ImageIcon(foto));
         txtpre.setText(rs.getString("pregunta"));
       txtres.setText(rs.getString("respuesta"));
    
     bloqueo();
      
     
   
     int resp = JOptionPane.showConfirmDialog(this, "Deseas Eliminarlo", "Eliminar Dato", 0);
      if (resp == 0) {
       
          PreparedStatement psd = null;
        String  SQL = " DELETE FROM datos_usuario WHERE cedula ='" +txtCedu.getText() + "'";
          psd = this.cn.prepareStatement(SQL);
          psd.execute();
          psd.close();
          borrar();
         bloqueo();
          JOptionPane.showMessageDialog(this, "REGISTRO ELIMINADO");
        
        
      } else {
      borrar();
         bloqueo(); // JOptionPane.showMessageDialog(null, "No existe");
      }}}catch (SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error SQL no se Eliminaron");
        }}
}//GEN-LAST:event_btelActionPerformed

private void rdmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdmaActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_rdmaActionPerformed

private void rdmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdmaMouseClicked
 if(rdma.isSelected()){
        
          rdf.setSelected(false);
     }// TODO add your handling code here:
}//GEN-LAST:event_rdmaMouseClicked

private void rdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdfMouseClicked
if(rdf.isSelected()){
         
        rdma.setSelected(false);  
     } // TODO add your handling code here:
}//GEN-LAST:event_rdfMouseClicked

    private void fotol1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fotol1MouseClicked
// TODO add your handling code here:

        //Creamos nuestra variable archivo en la cual podremos usar todos los metodos de la clase jFileChooser
        JFileChooser archivo = new JFileChooser();
        //Si deseamos crear filtros para la selecion de archivos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)", "jpg", "jpeg");
        //Si deseas que se muestre primero los filtros usa la linea q esta abajo de esta.
        //archivo.setFileFilter(filtro);
        // Agregamos el Filtro pero cuidado se mostrara despues de todos los archivos
        archivo.addChoosableFileFilter(filtro);
        // Colocamos titulo a nuestra ventana de Seleccion
        archivo.setDialogTitle("Abrir Archivo");
        //Si deseamos que muestre una carpeta predetermina usa la siguiente linea
        File ruta = new File("C:/xampp/htdocs/www/imagen");

        //Le implementamos a nuestro ventana de seleccion
        archivo.setCurrentDirectory(ruta);
        //Abrimos nuestra Ventana de Selccion
        int ventana = archivo.showOpenDialog(null);
        //hacemos comparacion en caso de aprete el boton abrir
        if (ventana == JFileChooser.APPROVE_OPTION) {
            //Obtenemos la ruta de nuestra imagen seleccionada
            File file = archivo.getSelectedFile();
            //Lo imprimimos en una caja de texto para ver su ruta
            String rus;
            rus = file.getName();
           FF1.setText(String.valueOf(rus));
            FF2.setText(String.valueOf(file));
            rus = FF2.getText();

            //de cierto modo necesitamos tener la imagen para ello debemos conocer la ruta de dicha imagen
            Image foto = getToolkit().getImage(rus);
            //Le damos dimension a nuestro label que tendra la imagen
            foto = foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            //Imprimimos la imagen en el label
            fotol1.setIcon(new ImageIcon(foto));
             fotol1.setText("");
        }
    }//GEN-LAST:event_fotol1MouseClicked

    private void fotol1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fotol1KeyPressed

   }//GEN-LAST:event_fotol1KeyPressed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        dispose();
        //sa = "1";
        //acti = "SALIDA";
        //new ModPriMenu().setVisible(true);
        //fpa();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel15MouseClicked

private void txtpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpreActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtpreActionPerformed

private void txtCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCoMouseClicked
if(cancar<8){
   txtCo.setBackground(Color.WHITE);   
}
    // TODO add your handling code here:
}//GEN-LAST:event_txtCoMouseClicked

private void txtTelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTelMouseClicked
txtTel.setBackground(Color.WHITE); // TODO add your handling code here:
}//GEN-LAST:event_txtTelMouseClicked

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
            java.util.logging.Logger.getLogger(ModRegUsua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModRegUsua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModRegUsua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModRegUsua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModRegUsua().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FF1;
    private javax.swing.JTextField FF2;
    private javax.swing.JComboBox boxco;
    private javax.swing.JButton bte;
    private javax.swing.JButton btel;
    private javax.swing.JButton btg;
    private javax.swing.JButton btn;
    private javax.swing.JLabel fotol1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton rdf;
    private javax.swing.JRadioButton rdma;
    private javax.swing.JTextField txtCedu;
    private javax.swing.JTextField txtCo;
    private javax.swing.JTextField txtNom;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtape;
    private javax.swing.JTextField txtcorr;
    private javax.swing.JTextField txtpre;
    private javax.swing.JTextField txtres;
    private javax.swing.JTextField txtusu;
    // End of variables declaration//GEN-END:variables
}
