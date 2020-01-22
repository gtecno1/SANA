/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on 30-nov-2017, 13:07:43
 */
package Principal;
import javax.swing.Timer;
import Segundario.bdc;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author back
 */
public class ModPriMenu extends javax.swing.JFrame {
 
  private Thread t;
  private Thread t1;
    private boolean activar= false;
    private boolean activar1 = false;
    private int segundo,minuto,centesima,segundo1,centesima1,minuto1;
    
 

bdc cc = new bdc();
 int co=30,jj2,SE;
        Connection cn = cc.Conectar();
 
  
      String jj,gesper,gespro,gessec,gesrol,auditoria1,resp,rest;
       String Ho,Hora,Hora2,Horaf,est,cedu,acti="INGRESO",fech,nom,sa="0",est2,con2,rol; 
    /** Creates new form NewJFrame */
    public ModPriMenu() {
         setUndecorated(true);
        initComponents();
        i();
         setLocationRelativeTo(null);
           empezar();
            empezar2();
              t.start(); 
           
    
         t1.start();  
     
        //   traer();
           
       
     //  this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
       mo();
       reloj();
      nombre();
      fpa();
      jPanel2.setVisible(false);
       
       
    }
  public void empezar(){
        activar = true;
        centesima =0;
        segundo = 0;
        minuto =0;
        
        t = new Thread(new Runnable() {
            @Override
            public void run() {
 
            while (activar){
 
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {}
 
              
               // lbmi.setText("|"+minuto+"||"+segundo+"|");
             
               
 
                centesima++;
                if (centesima>=99){
                    centesima = 0;
                    segundo++;
                }
                if (segundo>=60){
                    segundo = 0;
                    
                   
                
                    minuto++;
                   
                }
 
                if (minuto>=2){
                     cro();
                     minuto = 0;
                    
                  //   cronometro1 v= new cronometro1();
                 // v.activar1=false;
                   //  v.tt.stop();
                     
                }
                      
 
            }
 
            }
        });
 
    }
   public void empezar2(){
  
        activar1 = true;
        centesima1 =0;
        segundo1 = 0;
        minuto1 =0;
       
       
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
 
            while (activar1){
 
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {}
 
              
               // lbmi.setText("|"+minuto+"||"+segundo+"|");
             
               
 
                centesima1++;
                if (centesima1>=99){
                    centesima1 = 0;
                    segundo1++;
                }
                if (segundo1>=140){
                    segundo1 = 0;
                    
                   
                
                    minuto1++;
                     
                }
 
                if (minuto1>=1){
                     System.exit(0);
                     minuto1 = 0;
                    
                  //   cronometro1 v= new cronometro1();
                 // v.activar1=false;
                   //  v.tt.stop();
                        
                }
               
            }
 
            }
        });
 
    }
   
  void cro(){
       int resp2 = JOptionPane.showConfirmDialog(this, "¿Deseas continuar?", "Tiempo Expirado",JOptionPane.YES_NO_OPTION); 
                   
                  if (resp2==JOptionPane.YES_OPTION) {
     
    activar1 = false;
t1.stop();
empezar2();
t1.start();
       
    
 
      }else{
                      
                       try {
          
            sa = "1";
            acti = "SALIDA";
            String SQL, SQL2;
            PreparedStatement psd;
            SQL = "UPDATE datos_usuario set estado=? WHERE estado <> ? AND estado <> ?";

            psd = cn.prepareStatement(SQL);
            psd.setString(1, "0");
            psd.setString(2, "0");
            psd.setString(3, "-2");

            int f = psd.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL ");
        }
        fpa();
                       //ModRegUsua d= new  ModRegUsua();
                       //d.dispose();
                       System.exit(0);
       //   new  ModRegUsua().setVisible(false); 
       //  dispose();
        
       
        
         
            
         
     
         activar = false;
         activar1 = false;
t.stop();
t1.stop();
      } 
  }
    void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ImaPeq/logo.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
     void nombre()
  {
    try
    {
      Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery("SELECT * FROM datos_usuario  WHERE cedula ='"+cedu+"'   ");
      rs.last();
      
      int encontrado = rs.getRow();
      if (encontrado == 1)
      {
        String SQL = " SELECT * FROM datos_usuario WHERE cedula ='"+cedu+"'";
        
        Statement sv = null;
        sv = this.cn.createStatement();
        ResultSet rv = sv.executeQuery(SQL);
        rv.next();
        String nomz,apez,ruta1;
       
       nomz=rv.getString("nombre");
       apez=rv.getString("apellido");
       nomlb.setText(" USUARIO:"+" "+nomz+" "+apez);
       rol = rv.getString("rol");
        ruta1=rv.getString("rufo");
    
       
   
      
      String SQL1 ;
     
   
     SQL1 = "select * from rol_usuario where idrol  LIKE '%" + rol + "%'";
      
     Statement st1 = this.cn.createStatement();
      ResultSet rs1= st1.executeQuery(SQL1);
      while (rs1.next())
      {
    rol=rs1.getString("Descripcion");
     nomlb1.setText(" ROL:"+" "+rol);
     
      }
               
     
   
      
       
        
        
        File ruta = new File(ruta1);
           
            String d=(String.valueOf(ruta));
          
            Image foto= getToolkit().getImage(d);
            
            foto= foto.getScaledInstance(60, 60, Image.SCALE_DEFAULT);
          
           LBFOTO.setIcon(new ImageIcon(foto));
        }
      
      else
      {
        JOptionPane.showMessageDialog(null, "0", "ERROR", 0);
      }
      rs.close();
      st.close();
    }
    catch (Exception e) {}
  }
     public static String fechaActual1()
                {
        java.util.Date fecha = new java.util.Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyy");
        return formatoFecha.format(fecha);
                }
      
  void reloj()
  {
    Calendar car = Calendar.getInstance();
    
    String ap = String.valueOf(car.get(9));
    String h12 = String.valueOf(car.get(10));
    String mp;
   
    if (ap.equals("0")) {
      mp = "AM";
    } else {
      mp = "PM";
    }
   if (h12.equals("0")) {
      Ho=Hora = ("12" + ":" + car.get(12) + ":" + car.get(13) + " " + mp);
    } else {
     Ho=Hora = (car.get(10) + ":" + car.get(12) + ":" + car.get(13) + " " + mp);
    }
   
  
  }
   void reloj2()
  {
    Calendar car = Calendar.getInstance();
    String ap = String.valueOf(car.get(9));
     String h12 = String.valueOf(car.get(10));
    String mp;
   
    if (ap.equals("0")) {
      mp = "AM";
    } else {
      mp = "PM";
    }
    if (h12.equals("0")) {
     Hora2 = ("12" + ":" + car.get(12) + ":" + car.get(13) + " " + mp);
    } else {
     Hora2 = (car.get(10) + ":" + car.get(12) + ":" + car.get(13) + " " + mp);
    }
  
    
  }
   void fpa()
  {
 
    if(sa.equals("1")){
    
      reloj2();    
     Ho=Hora2;   
    }
   
    fech = fechaActual1();
    
    String SQL4 = "INSERT auditoria  (cedula,nombre,rol,proceso,actividad,hora,fecha) VALUES (?,?,?,?,?,?,?)";
    try
    {
      PreparedStatement psd4 = this.cn.prepareStatement(SQL4);
       psd4.setString(1, cedu);
       psd4.setString(2,nom);
       psd4.setString(3, rol);
       psd4.setString(4,"SISTEMA SANA");
       psd4.setString(5, acti); 
      
       psd4.setString(6, Ho);
       psd4.setString(7,fech);
      psd4.executeUpdate();
     
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Error PROCESO/ACTIVIDAD");
    }
  }
    
    void mo(){
       try{
       /*     String SQL51="SELECT * FROM datos_usuario  WHERE estado = 2 ";       
      Statement st51 = (Statement) cn.createStatement();   
      ResultSet rsu1 =   st51.executeQuery(SQL51);
       rsu1.last();
      
      int encontrado = rsu1.getRow();
      if (encontrado == 1)
      {
      JOptionPane.showMessageDialog(null, "Error de Estado");     
      }
            * 
            */
        String SQL5="SELECT * FROM datos_usuario  WHERE estado <> 0 AND estado <> -2  ";       
      Statement st5 = (Statement) cn.createStatement();   
      ResultSet rsu =   st5.executeQuery(SQL5);
      while (rsu.next()){   
        est=rsu.getString("estado");  
          cedu=rsu.getString("cedula");
          nom=rsu.getString("nombre");
                        }
            String SQL3="SELECT * FROM  proceso  WHERE idpro = "+est;
          Statement st3 = (Statement) cn.createStatement();
            ResultSet rsu2 =    st3.executeQuery(SQL3);
      
            while (rsu2.next()){     
      
         gesper=rsu2.getString("gesper");
         gespro=rsu2.getString("gespro");
         gessec=rsu2.getString("gessecc");
         gesrol=rsu2.getString("gesrol"); 
         est2=rsu2.getString("estn"); 
        con2=rsu2.getString("consultaest"); 
      auditoria1=rsu2.getString("auditoria"); 
       resp=rsu2.getString("respaldo"); 
      rest=rsu2.getString("restaurar"); 
       
         }
        
             
         if(gesper.equals("1")){
      btsnu1.setEnabled(true); 
      jMenuItem12.setEnabled(true);
       }else{
       jMenuItem12.setEnabled(false);
          btsnu1.setEnabled(false); 
       }
           if(gespro.equals("1")){
               jMenuItem14.setEnabled(true);
    btsnu2.setEnabled(true);    
       }else{
          jMenuItem14.setEnabled(false);
        btsnu2.setEnabled(false); 
       }
             if(gessec.equals("1")){
                 jMenuItem11.setEnabled(true);  
      btasc.setEnabled(true);    
       }else{
         jMenuItem11.setEnabled(false);  
         btasc.setEnabled(false); 
       }
               if(gesrol.equals("1")){
      jMenuItem6.setEnabled(true);    
       }else{
         jMenuItem6.setEnabled(false); 
       }
            if(est2.equals("1")){
                jMenuItem13.setEnabled(true); 
     btsnu.setEnabled(true);    
       }else{
        jMenuItem13.setEnabled(false); 
        btsnu.setEnabled(false); 
       }     
            if(con2.equals("1")){
     jMenuItem1.setEnabled(true);    
       }else{
       jMenuItem1.setEnabled(false); 
       }      
            
             if(auditoria1.equals("1")){
     jMenuItem8.setEnabled(true);    
       }else{
       jMenuItem8.setEnabled(false); 
       }      
      
             
              if(resp.equals("1")){
    jMenuItem3.setEnabled(true);    
       }else{
       jMenuItem3.setEnabled(false); 
       }      
            
             if(rest.equals("1")){
     jMenuItem4.setEnabled(true);    
       }else{
       jMenuItem4.setEnabled(false); 
       }      
      }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
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
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btsnu = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btsnu1 = new javax.swing.JButton();
        btsnu2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btasc = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        LBFOTO = new javax.swing.JLabel();
        nomlb = new javax.swing.JLabel();
        nomlb1 = new javax.swing.JLabel();
        lblhora = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(-1,true));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(-13408513,true));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel12.setForeground(new java.awt.Color(-1,true));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/process.png"))); // NOI18N
        jLabel12.setText("Gestión de Usuario");
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 12));
        jLabel13.setForeground(new java.awt.Color(-1,true));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/users.png"))); // NOI18N
        jLabel13.setText("Cambio de Usario");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(-3355444,true));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/back.png"))); // NOI18N
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.setOpaque(true);
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 350, 190, 190);

        jLabel10.setBackground(new java.awt.Color(-3355444,true));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/Folder Black User (1).png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.setOpaque(true);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10);
        jLabel10.setBounds(0, 460, 120, 80);

        btsnu.setFont(new java.awt.Font("Dialog", 1, 18));
        btsnu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.png"))); // NOI18N
        btsnu.setText("Estado Nutricional");
        btsnu.setContentAreaFilled(false);
        btsnu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsnu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btsnu.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/7.png"))); // NOI18N
        btsnu.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/11.png"))); // NOI18N
        btsnu.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btsnu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsnuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btsnuMouseEntered(evt);
            }
        });
        btsnu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsnuActionPerformed(evt);
            }
        });
        jPanel1.add(btsnu);
        btsnu.setBounds(10, 180, 250, 160);

        jLabel9.setBackground(new java.awt.Color(-13408513,true));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel9.setForeground(new java.awt.Color(-1,true));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SALIR");
        jLabel9.setOpaque(true);
        jPanel1.add(jLabel9);
        jLabel9.setBounds(810, 450, 100, 19);

        jLabel8.setBackground(new java.awt.Color(-13408513,true));
        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel8.setForeground(new java.awt.Color(-1,true));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("INICIO");
        jLabel8.setOpaque(true);
        jPanel1.add(jLabel8);
        jLabel8.setBounds(0, 430, 120, 19);

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(-65536,true));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/download.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel7.setOpaque(true);
        jLabel7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7);
        jLabel7.setBounds(700, 474, 100, 70);

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(-65536,true));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/exits.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel6.setOpaque(true);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6);
        jLabel6.setBounds(810, 475, 100, 70);

        btsnu1.setFont(new java.awt.Font("Dialog", 1, 18));
        btsnu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/4.png"))); // NOI18N
        btsnu1.setText("Gestiòn de Estudiantes");
        btsnu1.setContentAreaFilled(false);
        btsnu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsnu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btsnu1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/5.png"))); // NOI18N
        btsnu1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/13.png"))); // NOI18N
        btsnu1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btsnu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btsnu1MouseEntered(evt);
            }
        });
        btsnu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsnu1ActionPerformed(evt);
            }
        });
        jPanel1.add(btsnu1);
        btsnu1.setBounds(200, 340, 240, 190);

        btsnu2.setFont(new java.awt.Font("Dialog", 1, 18));
        btsnu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/3.png"))); // NOI18N
        btsnu2.setText("Gestión de Profesores");
        btsnu2.setContentAreaFilled(false);
        btsnu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btsnu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btsnu2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/6.png"))); // NOI18N
        btsnu2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/12.png"))); // NOI18N
        btsnu2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btsnu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btsnu2MouseEntered(evt);
            }
        });
        btsnu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btsnu2ActionPerformed(evt);
            }
        });
        jPanel1.add(btsnu2);
        btsnu2.setBounds(370, 170, 240, 174);

        jLabel11.setBackground(new java.awt.Color(-13408513,true));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel11.setForeground(new java.awt.Color(-1,true));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("MINIMIZAR");
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11);
        jLabel11.setBounds(700, 450, 100, 19);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/1.2.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(680, 420, 230, 120);

        btasc.setFont(new java.awt.Font("Dialog", 1, 18));
        btasc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/2.png"))); // NOI18N
        btasc.setText("Asignaciòn de Secciòn");
        btasc.setContentAreaFilled(false);
        btasc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btasc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btasc.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/10.png"))); // NOI18N
        btasc.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/14.png"))); // NOI18N
        btasc.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/14.png"))); // NOI18N
        btasc.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        btasc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btascMouseEntered(evt);
            }
        });
        btasc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btascActionPerformed(evt);
            }
        });
        jPanel1.add(btasc);
        btasc.setBounds(200, 0, 240, 160);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/1.3.png"))); // NOI18N
        jLabel3.setText("jLabel1");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(340, 420, 350, 120);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/1.2.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(0, 420, 350, 120);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/sana.png"))); // NOI18N
        jPanel1.add(jLabel4);
        jLabel4.setBounds(190, 130, 250, 260);

        jLabel5.setBackground(new java.awt.Color(-1,true));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 24));
        jLabel5.setForeground(new java.awt.Color(-16776961,true));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel5.setOpaque(true);
        jPanel1.add(jLabel5);
        jLabel5.setBounds(600, 60, 320, 330);

        LBFOTO.setBackground(new java.awt.Color(-8355712,true));
        LBFOTO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LBFOTO.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        LBFOTO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        LBFOTO.setOpaque(true);
        jPanel1.add(LBFOTO);
        LBFOTO.setBounds(0, 0, 190, 60);

        nomlb.setBackground(new java.awt.Color(-8355712,true));
        nomlb.setFont(new java.awt.Font("Dialog", 1, 12));
        nomlb.setForeground(new java.awt.Color(-1,true));
        nomlb.setText("jLabel7");
        nomlb.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nomlb.setOpaque(true);
        jPanel1.add(nomlb);
        nomlb.setBounds(0, 60, 190, 30);

        nomlb1.setBackground(new java.awt.Color(-8355712,true));
        nomlb1.setFont(new java.awt.Font("Dialog", 1, 18));
        nomlb1.setForeground(new java.awt.Color(-1,true));
        nomlb1.setText("jLabel7");
        nomlb1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nomlb1.setOpaque(true);
        jPanel1.add(nomlb1);
        nomlb1.setBounds(0, 90, 190, 30);

        lblhora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel1.add(lblhora);
        lblhora.setBounds(800, 4, 90, 30);
        jPanel1.add(jLabel15);
        jLabel15.setBounds(550, 30, 80, 30);

        jMenu1.setText("Archivo");

        jMenuItem12.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen1.png"))); // NOI18N
        jMenuItem12.setText("Gestión de Estudiantes");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuItem14.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen1.png"))); // NOI18N
        jMenuItem14.setText("Gestión de Profesores");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem14);

        jMenuItem11.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen3.png"))); // NOI18N
        jMenuItem11.setText("Asignación de Sección");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        jMenuItem13.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen4.png"))); // NOI18N
        jMenuItem13.setText("Estado Nutricional");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Consulta");

        jMenuItem1.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen5.png"))); // NOI18N
        jMenuItem1.setText("Consulta de Estudantes");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Seguridad");

        jMenuItem6.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen8.png"))); // NOI18N
        jMenuItem6.setText("Gestión de Rol");
        jMenuItem6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem6MouseClicked(evt);
            }
        });
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem7.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen7.png"))); // NOI18N
        jMenuItem7.setText("Gestión de Usuario");
        jMenuItem7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem7MouseClicked(evt);
            }
        });
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen6.png"))); // NOI18N
        jMenuItem8.setText("Auditorias");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem3.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/333.png"))); // NOI18N
        jMenuItem3.setText("Respaldo BD");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenuItem4.setBackground(new java.awt.Color(255, 255, 255));
        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/3434.png"))); // NOI18N
        jMenuItem4.setText("Restaurar BD");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ayuda");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem9.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem9.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen9.png"))); // NOI18N
        jMenuItem9.setText("Manual para Usuarios");
        jMenuItem9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem9MouseClicked(evt);
            }
        });
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem10.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem10.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen10.png"))); // NOI18N
        jMenuItem10.setText("Información");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuItem2.setBackground(new java.awt.Color(0, 0, 0));
        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jMenuItem2.setForeground(new java.awt.Color(255, 255, 255));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Imagen11.png"))); // NOI18N
        jMenuItem2.setText("Manual de alimentación");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 910, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btsnu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsnu2ActionPerformed
//dispose();
    new ModRegPro().setVisible(true);
}//GEN-LAST:event_btsnu2ActionPerformed

private void btsnu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsnu2MouseEntered
   File   ruta2 = new File("C:/Archivos de programa/Sana/Menu/of4.gif");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
          jLabel5.setIcon(new ImageIcon(foto2)); 
    
}//GEN-LAST:event_btsnu2MouseEntered

private void btsnu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsnu1MouseEntered
 File   ruta2 = new File("C:/Archivos de programa/Sana/Menu/of1.gif");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
          jLabel5.setIcon(new ImageIcon(foto2)); 
   // TODO add your handling code here:
}//GEN-LAST:event_btsnu1MouseEntered

private void btascMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btascMouseEntered
File   ruta2 = new File("C:/Archivos de programa/Sana/Menu/z.gif");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
          jLabel5.setIcon(new ImageIcon(foto2)); 
   // TODO add your handling code here:
}//GEN-LAST:event_btascMouseEntered

private void btsnuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsnuMouseClicked
// TODO add your handling code here:
}//GEN-LAST:event_btsnuMouseClicked

private void btsnuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsnuMouseEntered
File   ruta2 = new File("C:/Archivos de programa/Sana/Menu/of5.gif");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
          jLabel5.setIcon(new ImageIcon(foto2)); 
    // TODO add your handling code here:
}//GEN-LAST:event_btsnuMouseEntered

private void btsnuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsnuActionPerformed
//dispose();
    new ModEstPer().setVisible(true);
}//GEN-LAST:event_btsnuActionPerformed

private void btascActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btascActionPerformed
//dispose();
    new ModAsiSecc().setVisible(true);// TODO add your handling code here:
}//GEN-LAST:event_btascActionPerformed

private void btsnu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btsnu1ActionPerformed
//dispose();
    new ModGesPer().setVisible(true);// TODO add your handling code here:
}//GEN-LAST:event_btsnu1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
  //dispose();
        new ModGesRol().setVisible(true);      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
int resp = JOptionPane.showConfirmDialog(this, "¿Deseas Salir?", "Salida de Sistema", 0);
      if (resp == 0) {
     
   
          try {
           
            sa = "1";
            acti = "SALIDA";
            String SQL, SQL2;
            PreparedStatement psd;
            SQL = "UPDATE datos_usuario set estado=? WHERE estado <>? AND estado <>? ";

            psd = cn.prepareStatement(SQL);
            psd.setString(1, "0");
            psd.setString(2, "0");
             psd.setString(3, "-2");

            int f = psd.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL ");
        }
        fpa();
        System.exit(0);
      }
    
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
    //dispose();
        new ModAuditoria().setVisible(true);      // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem7MouseClicked
         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7MouseClicked

    private void jMenuItem6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem6MouseClicked
     //dispose();
     //   new ModGesRol().setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6MouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
     //dispose();
        new ModRegUsua().setVisible(true);     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//dispose();
        new ModConEst().setVisible(true);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel7MouseClicked

private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
jPanel2.setVisible(true);// TODO add your handling code here:
}//GEN-LAST:event_jLabel10MouseClicked

private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
 int resp = JOptionPane.showConfirmDialog(this, "¿Deseas Cambiar de Usuario?", "Cambio de Usuario", 0);
      if (resp == 0) {
     
    try {
            dispose();
            sa = "1";
            acti = "SALIDA";
            String SQL, SQL2;
            PreparedStatement psd;
            SQL = "UPDATE datos_usuario set estado=? WHERE estado <> ? AND estado <> ?";

            psd = cn.prepareStatement(SQL);
            psd.setString(1, "0");
            psd.setString(2, "0");
               psd.setString(3, "-2");

            int f = psd.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error SQL ");
        }
        fpa();
            activar = false;
         activar1 = false;
t.stop();
t1.stop();
    ModIngUsu g= new ModIngUsu();
 g.setVisible(true);
 
      }else{
          
      }
       
// TODO add your handling code here:
}//GEN-LAST:event_jLabel13MouseClicked

private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
SE=1;
  
    //new ModRegUsua().setVisible(true); // TODO add your handling code here:
}//GEN-LAST:event_jLabel12MouseClicked

private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
jPanel2.setVisible(false);// TODO add your handling code here:
}//GEN-LAST:event_jLabel14MouseClicked

    private void jMenuItem9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem9MouseClicked
     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9MouseClicked

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
new  ModAsiSecc().setVisible(true);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
new  ModGesPer().setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
new  ModEstPer().setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
new  ModRegPro().setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
   new  ModInfo().setVisible(true);       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
 try
    {
      Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:\\Archivos de programa\\Sana\\sana/Manual.pdf");
    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:/Manual.pdf");
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(this.rootPane, "Error al Abrir el Archivo", "ERROR", 0);
    }          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
     try
    {
      Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:\\Archivos de programa\\Sana\\sana/alp.pdf");
    
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(this.rootPane, "Error al Abrir el Archivo", "ERROR", 0);
    }     // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
jLabel5.setIcon(null);


// TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

            int opcion = JOptionPane.showConfirmDialog(null, "¿Quieres generar respaldo de base de datos SANA?", "Mensaje del sistema", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    Process p = Runtime
                            .getRuntime()
                            .exec("mysqldump --opt --password= --user=root --databases bdsana");

                    InputStream is = p.getInputStream();
                    Date date = new Date();
                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                    File f = new File("C:/bdsana");
                    f.mkdirs();
                    FileOutputStream fos = new FileOutputStream("C:/bdsana".concat(File.separator).concat("bdsana").concat("_").concat(df.format(date)).concat(".sql"));
                    byte[] buffer = new byte[1000];

                    int leido = is.read(buffer);
                    while (leido > 0) {
                        fos.write(buffer, 0, leido);
                        leido = is.read(buffer);
                    }

                    fos.close();
                    JOptionPane.showMessageDialog(null, "RESPALDO COMPLETO", "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed

            JFileChooser fc = new JFileChooser("C:/bdsana");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("SQL", "sql");
            fc.setFileFilter(filtro);
            int respuesta = fc.showOpenDialog(null);
            if (respuesta == JFileChooser.APPROVE_OPTION) {
                File f = fc.getSelectedFile();
                try {
                    Process p = Runtime.getRuntime().exec("mysql --user=root --password= --database  bdsana");
                    OutputStream outputStream = p.getOutputStream();

                    byte[] buffer = new byte[1000];
                    FileInputStream fis = new FileInputStream(f);
                    int leido = fis.read(buffer);

                    while (leido > 0) {
                        outputStream.write(buffer, 0, leido);
                        leido = fis.read(buffer);
                    }
                    outputStream.flush();
                    JOptionPane.showMessageDialog(null, "RESTAURACIÓN COMPLETA", "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    Logger.getLogger(ModPriMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

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
            java.util.logging.Logger.getLogger(ModPriMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModPriMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModPriMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModPriMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModPriMenu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBFOTO;
    private javax.swing.JButton btasc;
    private javax.swing.JButton btsnu;
    private javax.swing.JButton btsnu1;
    private javax.swing.JButton btsnu2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblhora;
    private javax.swing.JLabel nomlb;
    private javax.swing.JLabel nomlb1;
    // End of variables declaration//GEN-END:variables
}
