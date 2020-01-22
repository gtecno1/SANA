/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;
import java.util.Date;
import Segundario.bdc;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author back
 */
public class ModAuditoria extends javax.swing.JFrame {
DefaultTableModel model;
bdc cc = new bdc();
Connection cn = cc.Conectar();
Statement st = null;
String j,rf;
  String rol1,rol2,Ho,Hora,Hora2,Horaf,est,cedu,acti="ninguna",fech,nom,sa="0",busqe7,busqav7,repo7;    /**
     * Creates new form ModAuditoria
     */
    public ModAuditoria() {
         setUndecorated(true);
        initComponents();
        i();
          setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
         cargar("","","");
         Ac();
         selecto2();
         reloj();
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
       psd4.setString(4,"AUDITORIA");
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
    
 
     void Ac(){
           
        try{
    
              
     String SQL5="SELECT * FROM datos_usuario  WHERE estado <> 0  AND estado <> -2";       
      Statement st5 = (Statement) cn.createStatement();   
      ResultSet rsu =   st5.executeQuery(SQL5);
      while (rsu.next()){   
        est=rsu.getString("estado");   
         cedu=rsu.getString("cedula");  
          nom=rsu.getString("nombre");  
           rol1=rsu.getString("rol");
                        }
      
       Statement st2 = (Statement) cn.createStatement();
         String SQL4="SELECT * FROM actividad  WHERE idact LIKE '%" + est + "%' ";
          ResultSet rsu3 =    st2.executeQuery(SQL4);
       
         while (rsu3.next()){     
        
                 
   
                 busqe7=rsu3.getString("busqueda7"); 
                  busqav7=rsu3.getString("busquedaav7"); 
                  repo7=rsu3.getString("reporte7"); 
                  
              
                
                  
            if( busqe7.equals("1")){
            btguar.setEnabled(true);
            txtbusc.setEnabled(true);
            
                 }else{
            btguar.setEnabled(false);
             txtbusc.setEnabled(false);
                 }
           if(busqav7.equals("1")){
           jButton1.setEnabled(true);
           dat.setEnabled(true);
            lbpro.setEnabled(true);
               }else{
            jButton1.setEnabled(false);
             dat.setEnabled(false);
             lbpro.setEnabled(false);
              }
           if(repo7.equals("1")){
            jLabel12.setVisible(true);
            }else{
            jLabel12.setVisible(false);
            }
           
         }
       
           }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
    
       } 
     
     
     
     void cargar(String valor,String valor2,String valor3)
  {
     
    try
    {
      String[] titulos = {"CEDULA","NOMBRE","ROL","PROCESO","ACTIVIDAD","HORA","FECHA"};
      
      String[] registros = new String[7];
      this.model = new DefaultTableModel((Object[][])null, titulos);
      
      String cons = "select * from auditoria where  cedula LIKE '%" + valor + "%' AND proceso LIKE '%" + valor2 + "%' AND fecha LIKE '%" + valor3 + "%'"  ;
     
      Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(cons);
                
      while (rs.next())
      {
       
      //    registros[0] = rs.getString("idaudi");
        registros[0] = rs.getString("cedula");
        registros[1] = rs.getString("nombre");
        registros[2] = rs.getString("rol");
         registros[3] = rs.getString("proceso");
        registros[4] = rs.getString("actividad");
        registros[5] = rs.getString("hora");  
        registros[6] = rs.getString("fecha");
       
       
        this.model.addRow(registros);
      }
       String sql="SELECT count(*)  FROM auditoria where CONCAT (cedula) LIKE '%"+valor+"%' ";
 String sql2="SELECT count(*)  FROM auditoria where CONCAT (proceso) LIKE '%"+valor2+"%' ";
  
 
  
             Statement st0 = cn.createStatement();
               ResultSet rs0=st0.executeQuery(sql);
               while(rs0.next()){
                 
               lb1.setText(rs0.getString("count(*)"));
        
          
                   
               }
               Statement st1 = cn.createStatement();
               ResultSet rs1=st1.executeQuery(sql2);
               while(rs1.next()){
                 
                 lb1.setText(rs1.getString("count(*)"));
        
          
                   
               }
             
     tbauditar.setModel(this.model);
       
       
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }
     void cargar2(String valor)
  {
     
    try
    {
      String[] titulos = {"CEDULA","NOMBRE","ROL","PROCESO","ACTIVIDAD","HORA","FECHA"};
      
      String[] registros = new String[6];
      this.model = new DefaultTableModel((Object[][])null, titulos);
      
      String cons = "select * from auditoria where  cedula LIKE '%" + valor + "%'"  ;
     
      Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(cons);
                
      while (rs.next())
      {
       
      registros[0] = rs.getString("cedula");
        registros[1] = rs.getString("nombre");
        registros[2] = rs.getString("rol");
         registros[3] = rs.getString("proceso");
        registros[4] = rs.getString("actividad");
        registros[5] = rs.getString("hora");  
        registros[6] = rs.getString("fecha");
       
       
        this.model.addRow(registros);
      }
       String sql="SELECT count(*)  FROM auditoria where CONCAT (cedula) LIKE '%"+valor+"%' ";
 
  
             Statement st0 = cn.createStatement();
               ResultSet rs0=st0.executeQuery(sql);
               while(rs0.next()){
                 
               lb1.setText(rs0.getString("count(*)"));
        
          
                   
               }
              
             
     tbauditar.setModel(this.model);
       
       
    }
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
  }
     void data2(){
          Date date=dat.getDate();
 j= String.valueOf(date);
if(j.equals("null")){
    
   j=""; 
}
else{
     
String Captura=dat.getDateFormatString();
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyy");
 j=sdf.format(date);  
    
}
  rf=String.valueOf(lbpro.getSelectedItem());
cargar(txtbusc.getText(),rf,j);

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
        btguar = new javax.swing.JButton();
        txtbusc = new javax.swing.JTextField();
        lb1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbauditar = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbpro = new javax.swing.JComboBox();
        dat = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        btguar.setBackground(new java.awt.Color(51, 51, 51));
        btguar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btguar.setForeground(new java.awt.Color(-1,true));
        btguar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Find.png"))); // NOI18N
        btguar.setText("BUSCAR");
        btguar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btguarActionPerformed(evt);
            }
        });
        jPanel1.add(btguar);
        btguar.setBounds(60, 120, 140, 40);

        txtbusc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(txtbusc);
        txtbusc.setBounds(40, 80, 180, 30);

        lb1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lb1);
        lb1.setBounds(500, 460, 180, 14);

        jPanel2.setBackground(new java.awt.Color(128, 128, 128));

        tbauditar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tbauditar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbauditar);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 190, 900, 230);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        lbpro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbpro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--", "--Todos--", "GESTIÓN DE ESTUDIANTES", "GESTIÓN DE PROFESORES", "GESTIÓN DE ROL", "ASIGNACIÓN DE SECCIÓN", "AUDITORIA", "CONSULTA DE ESTUDIANTES", "ESTADO NUTRICIONAL", "SISTEMA SANA" }));

        dat.setDateFormatString("dd-MM-yyy");
        dat.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(-1,true));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Find.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("FECHA");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PROCESO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbpro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbpro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dat, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(260, 80, 600, 80);

        jLabel2.setBackground(new java.awt.Color(51, 102, 255));
        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(-1,true));
        jLabel2.setText("NUMERO DE REGISTROS");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(500, 430, 180, 19);

        jLabel1.setBackground(new java.awt.Color(-13408513,true));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(-1,true));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BUSQUEDA AVANZADA");
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(260, 50, 600, 30);

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/exits.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3);
        jLabel3.setBounds(800, 460, 100, 70);

        jLabel4.setBackground(new java.awt.Color(51, 102, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(-1,true));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("    AUDITORIA DE USUARIO");
        jLabel4.setOpaque(true);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(-6, -6, 920, 50);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CEDULA");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(40, 50, 180, 22);

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(-65536,true));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/download.png"))); // NOI18N
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel8.setOpaque(true);
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel8);
        jLabel8.setBounds(690, 460, 100, 70);

        jLabel9.setBackground(new java.awt.Color(-13408513,true));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel9.setForeground(new java.awt.Color(-1,true));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("MINIMIZAR");
        jLabel9.setOpaque(true);
        jPanel1.add(jLabel9);
        jLabel9.setBounds(690, 430, 100, 19);

        jLabel10.setBackground(new java.awt.Color(-13408513,true));
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel10.setForeground(new java.awt.Color(-1,true));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("SALIR");
        jLabel10.setOpaque(true);
        jPanel1.add(jLabel10);
        jLabel10.setBounds(800, 430, 100, 19);

        jLabel11.setBackground(new java.awt.Color(-13408513,true));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel11.setForeground(new java.awt.Color(-1,true));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("REPORTE");
        jLabel11.setOpaque(true);
        jPanel1.add(jLabel11);
        jLabel11.setBounds(10, 430, 100, 19);

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(-65536,true));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/print.png"))); // NOI18N
        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel12.setOpaque(true);
        jLabel12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel12);
        jLabel12.setBounds(10, 460, 100, 70);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btguarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btguarActionPerformed
data2();
rf=String.valueOf(lbpro.getSelectedItem());
if(rf.equals("--Seleccione--")||rf.equals("--Todos--")){
            rf="";
        }
cargar(txtbusc.getText(),rf,j); 
acti="BUSQUEDA";
fpa();
// TODO add your handling code here:
    }//GEN-LAST:event_btguarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
data2();
rf=String.valueOf(lbpro.getSelectedItem());
 if(rf.equals("--Seleccione--")||rf.equals("--Todos--")){
            rf="";
        }
cargar(txtbusc.getText(),rf,j);
acti="BUSQUEDA AVANZADA";    
fpa();
 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
     dispose();
           // sa = "1";
         //   acti = "SALIDA";
        // new ModPriMenu().setVisible(true);  
        //fpa();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel8MouseClicked

private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
List lista= new ArrayList();
for(int i=0;i<tbauditar.getRowCount();i++){    
  ReporteAuditoria Estudiantes= new ReporteAuditoria   (tbauditar.getValueAt(i,0).toString(),tbauditar.getValueAt(i,1).toString(),tbauditar.getValueAt(i,2).toString(),tbauditar.getValueAt(i,3).toString(),tbauditar.getValueAt(i,4).toString(),tbauditar.getValueAt(i,5).toString(),tbauditar.getValueAt(i,6).toString());  
lista.add(Estudiantes);
}
        try{
            
             String fileJasper = "C:\\Archivos de programa\\Sana\\sana\\reportAuditoria.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(fileJasper);
            Map parametro=new HashMap();
            parametro.put("cedula1",txtbusc.getText());
            JasperPrint Jprint= JasperFillManager.fillReport(reporte,parametro,new JRBeanCollectionDataSource(lista));
           
            JasperViewer.viewReport(Jprint,false);
            
        } catch (JRException ex) {
            Logger.getLogger(ModConEst.class.getName()).log(Level.SEVERE, null, ex);
        }// TODO add your handling code here:
}//GEN-LAST:event_jLabel12MouseClicked

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
            java.util.logging.Logger.getLogger(ModAuditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModAuditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModAuditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModAuditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModAuditoria().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btguar;
    private com.toedter.calendar.JDateChooser dat;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb1;
    private javax.swing.JComboBox lbpro;
    private javax.swing.JTable tbauditar;
    private javax.swing.JTextField txtbusc;
    // End of variables declaration//GEN-END:variables
}
