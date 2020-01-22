/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import Segundario.bdc;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author Gtecno
 */
public class ModAsiSecc extends javax.swing.JFrame {
bdc cc = new bdc();
        Connection cn = cc.Conectar();
         DefaultTableModel model;
         
           String e,t,j,Ho,Hora,Hora2,Horaf,est,cedu,acti="ninguna",fech,nom,sa="0",rufo,nofo,rufoc,nofoc,repo3,rol2,rol1;  
           String nue3="0",gua3="0",eli3="0",edi3="0";
        
         String s,c;
         String  idr="",ids="",cod;
         int Ac;
         
       
      
  
        
    /**
     * Creates new form ModGesPer
     */
    public ModAsiSecc() { 
        setUndecorated(true);
        initComponents();
        i();
        setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
       bloqueo();
       Ac();
       selecto();  
       selecto2();
       cargarusuarios() ;
       data2();
    }
     void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ImaPeq/logo.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
     void data2(){
          int date=año1.getYear();
          año2.setYear(date+1);
//jLabel9.setText(String.valueOf(date));

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
    String h12 = String.valueOf(car.get(10));
    String mp;
   
    if (ap.equals("0")) {
      mp = "AM";
    } else {
      mp = "PM";
    }
   if (h12.equals("0")) {
      Hora = ("12" + ":" + car.get(12) + ":" + car.get(13) + " " + mp);
    } else {
    Hora = (car.get(10) + ":" + car.get(12) + ":" + car.get(13) + " " + mp);
    }
   
  
  }
   void fpa()
  {
 
    
     reloj();    
        
    
   
    fech = fechaActual1();
    
    String SQL4 = "INSERT auditoria  (cedula,nombre,rol,proceso,actividad,hora,fecha) VALUES (?,?,?,?,?,?,?)";
    try
    {
      PreparedStatement psd4 = this.cn.prepareStatement(SQL4);
       psd4.setString(1, cedu);
       psd4.setString(2,nom);
       psd4.setString(3, rol2);
       psd4.setString(4,"ASIGNACIÓN DE SECCIÓN");
       psd4.setString(5, acti); 
       
       psd4.setString(6, Hora);
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
    
              
     String SQL5="SELECT * FROM datos_usuario  WHERE estado <> 0 AND estado <> -2";       
      Statement st5 = (Statement) cn.createStatement();   
      ResultSet rsu =   st5.executeQuery(SQL5);
      while (rsu.next()){   
        est=rsu.getString("estado");   
         cedu=rsu.getString("cedula");  
          nom=rsu.getString("nombre");   
           rol1=rsu.getString("rol");              }
       Statement st2 = (Statement) cn.createStatement();
         String SQL4="SELECT * FROM actividad  WHERE idact LIKE '%" + est + "%' ";
          ResultSet rsu3 =    st2.executeQuery(SQL4);
       
         while (rsu3.next()){     
        
                 
   
                  nue3=rsu3.getString("nuevo3"); 
                  gua3=rsu3.getString("guardar3"); 
                  edi3=rsu3.getString("editar3"); 
                  eli3=rsu3.getString("eliminar3"); 
                  repo3=rsu3.getString("reporte3"); 
                
                  
            if(nue3.equals("1")){
            btn.setEnabled(true);
                 }else{
            btn.setEnabled(false);
                 }
           if(gua3.equals("1")){
            btg.setEnabled(true);
               }else{
            btg.setEnabled(false);
              }
           if(edi3.equals("1")){
            bte.setEnabled(true); 
            }else{
            bte.setEnabled(false);
            }
           
           if(eli3.equals("1")){
            btel.setEnabled(true);
            
        }else{
            btel.setEnabled(false);
        } 
           if(repo3.equals("1")){
           jLabel10.setEnabled(true);
            
        }else{
           jLabel10.setEnabled(false);
           jLabel10.setVisible(false);
        } 
         }
       
           }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
    
       }
     void selecto2() {
       
    try
    {
      
      String SQL ;
   
     SQL = "select * from rol_usuario where idrol="+rol1;
      
     Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      while (rs.next())
      {
       
       rol2=rs.getString("Descripcion");
      }
          }     
     
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
      }
    void borrar(){
     
        boxsec1.setSelectedItem("--Seleccione--");
        boxpro.setSelectedItem("--Seleccione--");
       lbcedu.setText("");
       txtfe.setText("");
    data2();
        
    }
     void bloqueo(){
         jPanel2.setEnabled(false);
          boxsec1.setEnabled(false);
           boxpro.setEnabled(false);
          tblasig.setEnabled(false);
        año1.setEnabled(false);  
        año2.setEnabled(false);  
             
   lbcedu.setEnabled(false);
   
    }
      void desbloqueo(){
          jPanel2.setEnabled(true);      
    boxsec1.setEnabled(true);
    boxpro.setEnabled(true);           
   lbcedu.setEnabled(true);
   año1.setEnabled(true);  
        año2.setEnabled(true);  
    }
         
     
    void selecto() {
       
  
    try
    {
        
    
      String SQL ;
   
     SQL = "select * from seccion";
      
     Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      while (rs.next())
      {
       
      boxsec1.addItem( rs.getString("Descripcion"));
      }
       SQL = "select * from profesor";
      String n,p,t;
    st = this.cn.createStatement();
      rs = st.executeQuery(SQL);
      while (rs.next())
      {
          
    
      boxpro.addItem(  rs.getString("cedula"));
      }
             }     
     
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
      }
 void cargarusuarios() {
       
       
        try{
            String se1,ce1;
            String [] titulos={"ID","SECCÒN","CEDULA","NOMBRE","APELLIDO","PERIODO"};
            String [] registros= new String[6];
            model=new DefaultTableModel(null,titulos);
            
            String cons="select * from profesor_seccion ";
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                registros[0]=rs.getString("id");
                se1=rs.getString("idsecc");
                 e=rs.getString("inicio");
                 t=rs.getString("termino");
                
               
      
         data2();
                  try{
       
      String SQL4 = "select * from seccion where idsec LIKE '%" + se1 + "%'";
      
      Statement st4 = this.cn.createStatement();
      ResultSet rs4 = st4.executeQuery(SQL4);
      while (rs4.next()) {
      registros[1] = rs4.getString("Descripcion");
           } 
            ce1=registros[2]=rs.getString("cedpro");
                           
       SQL4 = "select * from profesor where cedula LIKE '%" + ce1 + "%'";
       st4 = this.cn.createStatement();
      rs4 = st4.executeQuery(SQL4);
      while (rs4.next()) {
      registros[3] = rs4.getString("nombre");
       registros[4] = rs4.getString("apellido");
        registros[5] = (e+"-"+t);
      
   
       
           } 
      
         }  catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            } 
      
    
                             
                 
              
                model.addRow(registros);      
                }
         tblasig.setModel(model);
          tblasig.getColumnModel().getColumn(0).setPreferredWidth(30);
            tblasig.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblasig.getColumnModel().getColumn(2).setPreferredWidth(100);
             
            }catch(Exception e){
                System.out.println(e.getMessage());
                 }
}

  
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtfe = new javax.swing.JLabel();
        boxsec1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        año2 = new com.toedter.calendar.JYearChooser();
        año1 = new com.toedter.calendar.JYearChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        foto1 = new javax.swing.JLabel();
        lbcedu = new javax.swing.JLabel();
        boxpro = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        ruta2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btn = new javax.swing.JButton();
        btg = new javax.swing.JButton();
        bte = new javax.swing.JButton();
        btel = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblasig = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Asignada a:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(340, 190, 110, 30);
        jPanel3.add(txtfe);
        txtfe.setBounds(678, 337, 60, 30);

        boxsec1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        boxsec1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--" }));
        jPanel3.add(boxsec1);
        boxsec1.setBounds(70, 190, 260, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Periodo");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(90, 40, 210, 30);

        jPanel2.setBackground(new java.awt.Color(-3355444,true));
        jPanel2.setLayout(null);
        jPanel2.add(año2);
        año2.setBounds(140, 0, 70, 30);
        jPanel2.add(año1);
        año1.setBounds(0, 0, 70, 30);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("-");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(64, 1, 80, 30);

        jPanel3.add(jPanel2);
        jPanel2.setBounds(90, 80, 210, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Secciòn");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(70, 160, 260, 22);

        jPanel6.setBackground(new java.awt.Color(-8355712,true));

        foto1.setFont(new java.awt.Font("Dialog", 1, 14));
        foto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/usuario.png"))); // NOI18N
        foto1.setText("foto");
        foto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        foto1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lbcedu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbcedu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbcedu.setText("NOMBRE");

        boxpro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        boxpro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--" }));
        boxpro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boxproMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boxproMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boxproMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                boxproMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                boxproMouseReleased(evt);
            }
        });
        boxpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxproActionPerformed(evt);
            }
        });
        boxpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                boxproKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                boxproKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(-1,true));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cedula");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(boxpro, 0, 220, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 37, Short.MAX_VALUE))
                    .addComponent(foto1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbcedu, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbcedu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxpro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jPanel3.add(jPanel6);
        jPanel6.setBounds(450, 0, 310, 260);
        jPanel3.add(ruta2);
        ruta2.setBounds(390, 10, 0, 0);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(210, 60, 755, 240);

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ASIGNACIÓN DE SECCIÓN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 0, 1025, 50);

        jLabel9.setBackground(new java.awt.Color(-13408513,true));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel9.setForeground(new java.awt.Color(-1,true));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SALIR");
        jLabel9.setOpaque(true);
        jPanel1.add(jLabel9);
        jLabel9.setBounds(860, 460, 100, 19);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setLayout(null);

        btn.setBackground(new java.awt.Color(51, 51, 51));
        btn.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btn.setForeground(new java.awt.Color(-1,true));
        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Add.png"))); // NOI18N
        btn.setText("Nuevo");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });
        jPanel5.add(btn);
        btn.setBounds(10, 20, 170, 60);

        btg.setBackground(new java.awt.Color(51, 51, 51));
        btg.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btg.setForeground(new java.awt.Color(-1,true));
        btg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Save.png"))); // NOI18N
        btg.setText("Guardar");
        btg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgActionPerformed(evt);
            }
        });
        jPanel5.add(btg);
        btg.setBounds(10, 90, 170, 60);

        bte.setBackground(new java.awt.Color(51, 51, 51));
        bte.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        bte.setForeground(new java.awt.Color(-1,true));
        bte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Modify.png"))); // NOI18N
        bte.setText("Editar");
        bte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteActionPerformed(evt);
            }
        });
        jPanel5.add(bte);
        bte.setBounds(10, 160, 170, 60);

        btel.setBackground(new java.awt.Color(51, 51, 51));
        btel.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btel.setForeground(new java.awt.Color(-1,true));
        btel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Delete.png"))); // NOI18N
        btel.setText("Eliminar");
        btel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btelActionPerformed(evt);
            }
        });
        jPanel5.add(btel);
        btel.setBounds(10, 230, 170, 60);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.2.png"))); // NOI18N
        jPanel5.add(jLabel2);
        jLabel2.setBounds(0, 280, 250, 110);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 60, 190, 320);

        tblasig.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblasig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblasigMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblasig);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(210, 310, 755, 140);

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(-65536,true));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/exits.png"))); // NOI18N
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
        jLabel7.setBounds(860, 490, 100, 70);

        jLabel10.setBackground(new java.awt.Color(204, 204, 204));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(-65536,true));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/print.png"))); // NOI18N
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel10.setOpaque(true);
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel10);
        jLabel10.setBounds(30, 490, 100, 70);

        jLabel11.setBackground(new java.awt.Color(-13408513,true));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel11.setForeground(new java.awt.Color(-1,true));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("REPORTE");
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11);
        jLabel11.setBounds(30, 460, 100, 19);

        jLabel12.setBackground(new java.awt.Color(-13408513,true));
        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel12.setForeground(new java.awt.Color(-1,true));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("MINIMIZAR");
        jLabel12.setOpaque(true);
        jPanel1.add(jLabel12);
        jLabel12.setBounds(750, 460, 100, 19);

        jLabel13.setBackground(new java.awt.Color(204, 204, 204));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(-65536,true));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/download.png"))); // NOI18N
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel13.setOpaque(true);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel13);
        jLabel13.setBounds(750, 490, 100, 70);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 559, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
     desbloqueo();
     tblasig.setEnabled(false);
            
     borrar();
     Ac=1;
    }//GEN-LAST:event_btnActionPerformed

    private void btgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgActionPerformed
String secc2,cedula1,cedula2="0",secc1="0";
        

  
  
 cedula1= String.valueOf(boxpro.getSelectedItem());
  lbcedu.getText();
 secc2=String.valueOf(boxsec1.getSelectedItem());
 

  
   txtfe.setText(fechaActual1());
 
  
  
        String SQL;
        
       try{
       
       String SQL3 = "select * from profesor_seccion where cedpro LIKE '%" + cedula1 + "%' OR idsecc LIKE '%" + secc2 + "%'";
      
      Statement st4 = this.cn.createStatement();
      ResultSet rs4 = st4.executeQuery(SQL3);
      while (rs4.next()) {
       cedula2=  rs4.getString("cedpro");
        secc1=  rs4.getString("idsecc");
           } 
      
         }  catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            } 

        
      

        PreparedStatement psd;
       
 if(secc2.equals("--Seleccione--")||cedula1.equals("--Seleccione--")){
     JOptionPane.showMessageDialog(this,"¡¡Por favor Verifique los Campos!!");
 } else{
  
     
            
        
       String SQL4;
      
     try{
       
       SQL4 = "select * from seccion where Descripcion LIKE '%" + secc2 + "%'";
      
      Statement st4 = this.cn.createStatement();
      ResultSet rs4 = st4.executeQuery(SQL4);
      while (rs4.next()) {
       ids = rs4.getString("idsec");
           } 
      
         }  catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }
       
      
        switch(Ac){
           
            case 1:
 if(cedula2.equals("0")||secc1.equals("0")){
        try{

                SQL="INSERT INTO profesor_seccion(idsecc,cedpro,inicio,termino)VALUES (?,?,?,?)";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,ids);
                psd.setString(2,cedula1);
                psd.setString(3,String.valueOf(año1.getYear()));
               psd.setString(4,String.valueOf(año2.getYear()));
               
             
               
                 int m= psd.executeUpdate();
                  
               
                  
                

                if((m>0)){
                    acti=("GUARDAR");
                    fpa();
                    cargarusuarios();
                   borrar();
                    bloqueo();
                    JOptionPane.showMessageDialog(null,"Guardo con exito");
                }
        
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            
        
}
        }else{
   JOptionPane.showMessageDialog(null,"sección asignada");  
}
   
            break;
            case 2:
            
            try {

             
                SQL="UPDATE profesor_seccion set idsecc=?,cedpro=?,inicio=?,termino=? WHERE id=? ";

                psd = cn.prepareStatement(SQL);
                 psd.setString(1,ids);
                psd.setString(2,cedula1);
                psd.setString(3,String.valueOf(año1.getYear()));
               psd.setString(4,String.valueOf(año2.getYear()));
                 psd.setString(5,cod);
               
               
   
              
                
                int f=  psd.executeUpdate();
                
               
               
                 if((f>0)){
                      acti=("ACTUALIZAR");
                    fpa();
                     cargarusuarios() ;
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
    
 acti="GUARDAR";
 fpa();
    }//GEN-LAST:event_btgActionPerformed

private void bteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteActionPerformed
Ac=2;
borrar();
desbloqueo();
tblasig.setEnabled(true);
            

}//GEN-LAST:event_bteActionPerformed

private void btelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btelActionPerformed
Ac=3;
 borrar();
tblasig.setEnabled(true);
}//GEN-LAST:event_btelActionPerformed

private void boxproMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxproMouseClicked
 
}//GEN-LAST:event_boxproMouseClicked

private void boxproKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxproKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_boxproKeyPressed

private void boxproKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_boxproKeyReleased
// TODO add your handling code here:
}//GEN-LAST:event_boxproKeyReleased

private void boxproMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxproMouseEntered
 
    // TODO add your handling code here:
}//GEN-LAST:event_boxproMouseEntered

private void boxproMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxproMousePressed
// TODO add your handling code here:
}//GEN-LAST:event_boxproMousePressed

private void boxproMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxproMouseReleased
// TODO add your handling code here:
}//GEN-LAST:event_boxproMouseReleased

private void boxproMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boxproMouseExited
// TODO add your handling code here:
}//GEN-LAST:event_boxproMouseExited

private void boxproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxproActionPerformed
String cod2 = String.valueOf(boxpro.getSelectedItem());
    String n,p;
   
    try
    {
       
        if(cod2.equals ("--Seleccione--")){
          lbcedu.setText(""); 
           File ruta = new File("C://Archivos de programa//Sana//ImaGra//usuario.png");
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto1.setIcon(new ImageIcon(foto));
             foto1.setText("foto");
        }else{
  String SQL1 = "SELECT * FROM profesor WHERE cedula=" +cod2;
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
              
     n=rs.getString("nombre");
      p=rs.getString("apellido");
      
        lbcedu.setText(n+" "+p);
      
      p=rs.getString("apellido");
    
       rufo=rs.getString("rufo");
   nofo=rs.getString("nofo");
    String fu=rufo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
           foto1.setIcon(new ImageIcon(foto));
            foto1.setText("");
           
            if(rufo.equals("")){
              ruta = new File("C://Archivos de programa//Sana//ImaGra//usuario.png");
           d=(String.valueOf(ruta));
             foto= getToolkit().getImage(d);
             foto1.setIcon(new ImageIcon(foto));
              foto1.setText("foto");
        }
           
      }
      }catch (SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error SQL no se Eliminaron");
        }// TODO add your handling code here:
}//GEN-LAST:event_boxproActionPerformed

private void tblasigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblasigMouseClicked
 
    int i = tblasig.getSelectedRow();  
      cod=(String) tblasig.getValueAt(i,0); 
       s=(String) tblasig.getValueAt(i,1); 
       c=(String) tblasig.getValueAt(i,2); 
         boxsec1.setSelectedItem(s);
        boxpro.setSelectedItem(c);
      año1.setYear(Integer.valueOf(e));
      año2.setYear(Integer.valueOf(t)); 
     try
        {
    String SQL1 = "SELECT * FROM profesor WHERE cedula=" +c;
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
      rufoc=rs.getString("rufo");
       File ruta = new File(rufoc);
          String k=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(k);
             foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
           foto1.setIcon(new ImageIcon(foto));
            foto1.setText("");
         }
     
        catch (SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error SQL no se Eliminaron");
        }
      if(rufo.equals("")||rufo.equals("No posee")){
           File    ruta = new File("C://Archivos de programa//Sana//ImaGra//usuario.png");
          String  d=(String.valueOf(ruta));
           Image  foto= getToolkit().getImage(d);
             foto1.setIcon(new ImageIcon(foto));
              foto1.setText("foto");
        }
     if(Ac==3){
      
   String d,v;
           d=String.valueOf(boxsec1.getSelectedItem());
           v=String.valueOf(boxpro.getSelectedItem());

   if((d.equals("--Seleccione--"))&& (v.equals("--Seleccione--"))){
   
   }else{
  
     int resp = JOptionPane.showConfirmDialog(this, "Deseas Eliminarlo", "Eliminar Dato", 0);
      if (resp == 0) {
        try
        {
          PreparedStatement psd = null;
        String  SQL = " DELETE FROM profesor_seccion WHERE id ='" + cod + "'";
          psd = this.cn.prepareStatement(SQL);
          psd.execute();
          psd.close();
           acti=("ELIMINAR");
                    fpa();
          borrar();
          tblasig.setEnabled(false);
          cargarusuarios();
         bloqueo();
           
          JOptionPane.showMessageDialog(this, "REGISTRO ELIMINADO");
        }
        catch (SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error SQL no se Eliminaron");
        }
      } else {
       
      }
       }
     }
 
              
  
     
      
      
   
    // TODO add your handling code here:
}//GEN-LAST:event_tblasigMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
      
            dispose();
           // sa = "1";
           // acti = "SALIDA";
      //   new ModPriMenu().setVisible(true);  
       // fpa();   
    }//GEN-LAST:event_jLabel7MouseClicked

private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
List lista= new ArrayList();
for(int i=0;i<tblasig.getRowCount();i++){    
  ReporteAsigSección Estudiantes= new ReporteAsigSección   (tblasig.getValueAt(i,0).toString(),tblasig.getValueAt(i,1).toString(),tblasig.getValueAt(i,2).toString(),tblasig.getValueAt(i,3).toString(),tblasig.getValueAt(i,4).toString(),tblasig.getValueAt(i,5).toString());  
lista.add(Estudiantes);
}
        try{
            
             String fileJasper = "C:\\Archivos de programa\\Sana\\sana\\reportSec.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(fileJasper);
            Map parametro=new HashMap();
            parametro.put("cedula1",boxpro.getSelectedItem());
           
            JasperPrint Jprint= JasperFillManager.fillReport(reporte,parametro,new JRBeanCollectionDataSource(lista));
           
            JasperViewer.viewReport(Jprint,false);
            
        } catch (JRException ex) {
            Logger.getLogger(ModAsiSecc.class.getName()).log(Level.SEVERE, null, ex);
        }// TODO add your handling code here:
}//GEN-LAST:event_jLabel10MouseClicked

private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel13MouseClicked

        
      

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
            java.util.logging.Logger.getLogger(ModAsiSecc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModAsiSecc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModAsiSecc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModAsiSecc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModAsiSecc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser año1;
    private com.toedter.calendar.JYearChooser año2;
    private javax.swing.JComboBox boxpro;
    private javax.swing.JComboBox boxsec1;
    private javax.swing.JButton bte;
    private javax.swing.JButton btel;
    private javax.swing.JButton btg;
    private javax.swing.JButton btn;
    private javax.swing.JLabel foto1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbcedu;
    private javax.swing.JLabel ruta2;
    private javax.swing.JTable tblasig;
    private javax.swing.JLabel txtfe;
    // End of variables declaration//GEN-END:variables
}
