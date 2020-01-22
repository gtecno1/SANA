/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Segundario.bdc;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author back
 */
public class ModConEst extends javax.swing.JFrame {
    bdc cc = new bdc();
        Connection cn = cc.Conectar();
DefaultTableModel model;
  String rol2,rol1,Ho,Hora,Hora2,Horaf,est,cedu,acti="ninguna",fech,nom,sa="0",sexo="",bu1,bu2,rep3;  
    /**
     * Creates new form ModConEst
     */
    public ModConEst() { 
        setUndecorated(true);
        initComponents();
        i();
        setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
       selecto();
        Ac();
        selecto2();
        cargar("","","");
      
      
    }
     void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ImaPeq/logo.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
      public static String fechaActual1()
                {
        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyy");
        return formatoFecha.format(fecha);
                }
      void selecto() {
       
  
    try
    {
        
    String sc;
      String SQL ;
   
     SQL = "select * from seccion";
      
     Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      while (rs.next())
      {
       
      boxsec.addItem( rs.getString("Descripcion"));
      }
      
      
   
     
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
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
       psd4.setString(4,"CONSULTA DE ESTUDIANTES");
       psd4.setString(5, acti); 
       
       psd4.setString(6, Hora);
       psd4.setString(7,fech);
      psd4.executeUpdate();
     
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Error PROCESO/ACTIVIDAD");
    }
  }void selecto2() {
       
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
     void Ac(){
           
        try{
    
              
     String SQL5="SELECT * FROM datos_usuario  WHERE estado <> 0 AND estado <> -2";       
      Statement st5 = (Statement) cn.createStatement();   
      ResultSet rsu =   st5.executeQuery(SQL5);
      while (rsu.next()){   
        est=rsu.getString("estado");   
         cedu=rsu.getString("cedula");  
          nom=rsu.getString("nombre");   
            rol1=rsu.getString("rol");                 }
       Statement st2 = (Statement) cn.createStatement();
         String SQL4="SELECT * FROM actividad  WHERE idact LIKE '%" + est + "%' ";
          ResultSet rsu3 =    st2.executeQuery(SQL4);
       
         while (rsu3.next()){     
        
                 
   
                 bu1=rsu3.getString("busqueda6"); 
                  bu2=rsu3.getString("busquedaav6"); 
                  rep3=rsu3.getString("reporte6"); 
                 
              
                
                  
            if(bu1.equals("1")){
          Txtb.setEnabled(true);
           
                 }else{
            Txtb.setEnabled(false);
                 }
           if(bu2.equals("1")){
               rdm.setEnabled(true);
               rdf.setEnabled(true);
           boxsec.setEnabled(true);
               }else{
                rdm.setEnabled(false);
                rdf.setEnabled(false);
           boxsec.setEnabled(false);
              }
           if(rep3.equals("1")){
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
     void cargar33(){
         
         
     }
    
  void cargar(String valor,String valor2,String valor3)
  {
    try
    {
        
        
      String[] titulos = {"CEDULA","NOMBRE","APELLIDO","EDAD","FECHA DE NACIMIENTO","SEXO","SECCÓN","CORREO","PERIODO I","PERIODO F"};
      
      String[] registros = new String[10];
      this.model = new DefaultTableModel((Object[][])null, titulos);
      
      String cons = "select * from persona where  cedula LIKE '%" + valor + "%' AND sexo LIKE '%" + valor2 +"%' AND seccion LIKE '%" + valor3 +"%' "  ;
     
      Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(cons);
                
      while (rs.next())
      {
        registros[0] = rs.getString("cedula");
        registros[1] = rs.getString("nombre");
        registros[2] = rs.getString("apellido");
        registros[3] = rs.getString("edad");
        registros[4] = rs.getString("fechana");
        registros[5] = rs.getString("sexo");
        
      String  registros1 = rs.getString("seccion");
        String SQL4 = "select * from seccion where idsec LIKE '%" +  registros1  + "%'";
      
      Statement st4 = this.cn.createStatement();
      ResultSet rs4 = st4.executeQuery(SQL4);
      while (rs4.next()) {
      registros[6] = rs4.getString("Descripcion");
           } 
        registros[7] = rs.getString("correo");
    
        registros[8] = rs.getString("inicio");
        registros[9] = rs.getString("termino");
       
        this.model.addRow(registros);
      }
      String sql="SELECT count(*)  FROM persona where CONCAT (cedula,sexo,seccion) LIKE '%"+valor+"%'  AND sexo LIKE '%"+valor2+"%' AND seccion LIKE '%"+valor3+"%' ";
   
  
            
             Statement st0 = cn.createStatement();
               ResultSet rs0=st0.executeQuery(sql);
               while(rs0.next()){
                 
                  lbcont.setText(rs0.getString("count(*)"));
        
          
                   
               }
              
      this.tbestud.setModel(this.model);
      
      
      this.tbestud.getColumnModel().getColumn(0).setPreferredWidth(50);
      this.tbestud.getColumnModel().getColumn(1).setPreferredWidth(100);
      this.tbestud.getColumnModel().getColumn(2).setPreferredWidth(100);
    }
    catch (Exception e)
    {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbestud = new javax.swing.JTable();
        Txtb = new javax.swing.JTextField();
        btb = new javax.swing.JButton();
        lbcont = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbcont1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        rdf = new javax.swing.JRadioButton();
        rdm = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        boxsec = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTA DE ESTUDANTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 841, 40);

        tbestud.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbestud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbestud);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 197, 821, 193);
        jPanel1.add(Txtb);
        Txtb.setBounds(30, 100, 180, 30);

        btb.setBackground(new java.awt.Color(51, 51, 51));
        btb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btb.setForeground(new java.awt.Color(-1,true));
        btb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Find.png"))); // NOI18N
        btb.setText("Buscar");
        btb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbActionPerformed(evt);
            }
        });
        jPanel1.add(btb);
        btb.setBounds(70, 140, 109, 36);

        lbcont.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbcont.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lbcont);
        lbcont.setBounds(420, 420, 184, 29);

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
        jLabel7.setBounds(730, 430, 90, 80);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CEDULA");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 72, 180, 30);

        lbcont1.setBackground(new java.awt.Color(-13408513,true));
        lbcont1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbcont1.setForeground(new java.awt.Color(-1,true));
        lbcont1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbcont1.setText("NUMERO DE REGISTROS");
        lbcont1.setToolTipText("");
        lbcont1.setOpaque(true);
        jPanel1.add(lbcont1);
        lbcont1.setBounds(420, 400, 184, 20);

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
        jLabel10.setBounds(10, 440, 100, 70);

        jLabel11.setBackground(new java.awt.Color(-13408513,true));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel11.setForeground(new java.awt.Color(-1,true));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("REPORTE");
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11);
        jLabel11.setBounds(10, 410, 100, 19);

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/download.png"))); // NOI18N
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8);
        jLabel8.setBounds(630, 430, 90, 80);

        jLabel3.setBackground(new java.awt.Color(-13408513,true));
        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel3.setForeground(new java.awt.Color(-1,true));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("MINIMIZAR");
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(630, 400, 90, 20);

        jLabel4.setBackground(new java.awt.Color(-13408513,true));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel4.setForeground(new java.awt.Color(-1,true));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SALIR");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(730, 400, 90, 20);

        jPanel3.setBackground(new java.awt.Color(-3355444,true));

        jLabel12.setBackground(new java.awt.Color(-6684673,true));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Sexo");

        rdf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdf.setText("Femenino");
        rdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdfMouseClicked(evt);
            }
        });

        rdm.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdm.setText("Masculino");
        rdm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdmMouseClicked(evt);
            }
        });
        rdm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdmActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(-6684673,true));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Sección");

        boxsec.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        boxsec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--" }));
        boxsec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxsecActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(-13408513,true));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(-1,true));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("BUSQUEDA AVANZADA");
        jLabel5.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxsec, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdf)
                    .addComponent(rdm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(8, 8, 8)
                        .addComponent(boxsec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdm, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(270, 70, 560, 120);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbActionPerformed
 String m="",f="",d="";
 if(rdf.isSelected()|| rdm.isSelected()){
        if(rdf.isSelected()){
         sexo="Femenino";  
     } 
       if(rdm.isSelected()){
         sexo="Masculino";   
     }
 }else{sexo="";  }
       
        String sc=  String.valueOf(boxsec.getSelectedItem());
        if(sc.equals("--Seleccione--")){
            sc="";
        }else{
    try
    {
        
   
      String SQL ;
   
     SQL = "select * from seccion where Descripcion LIKE '%" +  sc + "%'";
      
     Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      while (rs.next())
      {
       
     sc = rs.getString("idsec");
      }
      
      
   
     
    }catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
        }
        cargar(Txtb.getText(),sexo,sc);        // TODO add your handling code here:
    }//GEN-LAST:event_btbActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        dispose();
       // sa = "1";
      //  acti = "SALIDA";
      //  new ModPriMenu().setVisible(true);
       // fpa();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
List lista= new ArrayList();
for(int i=0;i<tbestud.getRowCount();i++){    
  ReporteEstudiantes Estudiantes= new ReporteEstudiantes   (tbestud.getValueAt(i,0).toString(),tbestud.getValueAt(i,1).toString(),tbestud.getValueAt(i,2).toString(),tbestud.getValueAt(i,3).toString(),tbestud.getValueAt(i,4).toString(),tbestud.getValueAt(i,5).toString(),tbestud.getValueAt(i,6).toString(),tbestud.getValueAt(i,7).toString(),tbestud.getValueAt(i,8).toString(),tbestud.getValueAt(i,9).toString());  
lista.add(Estudiantes);
}
        try{
            
             String fileJasper = "C:\\Archivos de programa\\Sana\\sana\\report2.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(fileJasper);
            Map parametro=new HashMap();
            parametro.put("Cedula1",Txtb.getText());
            JasperPrint Jprint= JasperFillManager.fillReport(reporte,parametro,new JRBeanCollectionDataSource(lista));
           
            JasperViewer.viewReport(Jprint,false);
            
        } catch (JRException ex) {
            Logger.getLogger(ModConEst.class.getName()).log(Level.SEVERE, null, ex);
        }// TODO add your handling code here:
}//GEN-LAST:event_jLabel10MouseClicked

private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel8MouseClicked

private void rdfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdfMouseClicked
 if(rdf.isSelected()){
         
        rdm.setSelected(false);  
     } 
              // TODO add your handling code here:
}//GEN-LAST:event_rdfMouseClicked

private void rdmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rdmMouseClicked
 if(rdm.isSelected()){
        
          rdf.setSelected(false);
     }         // TODO add your handling code here:
}//GEN-LAST:event_rdmMouseClicked

private void rdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdmActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_rdmActionPerformed

private void boxsecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxsecActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_boxsecActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModConEst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModConEst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModConEst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModConEst.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModConEst().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Txtb;
    private javax.swing.JComboBox boxsec;
    private javax.swing.JButton btb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbcont;
    private javax.swing.JLabel lbcont1;
    private javax.swing.JRadioButton rdf;
    private javax.swing.JRadioButton rdm;
    private javax.swing.JTable tbestud;
    // End of variables declaration//GEN-END:variables
}
