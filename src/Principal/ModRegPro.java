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
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Gtecno
 */
public class ModRegPro extends javax.swing.JFrame {
bdc cc = new bdc();
        Connection cn = cc.Conectar();
         String nue2="0",gua2="0",eli2="0",edi2="0";
      
         String rol1,rol2,Ho,Hora,Hora2,Horaf,est,cedu,acti="ninguna",fech,nom,sa="0",rufo="No posee",nofo="No posee";  
         
       
       String  idr="",ids="",cod;
       int Ac;
    /**
     * Creates new form ModGesPer
     */
     
    public ModRegPro() {
         setUndecorated(true);
        initComponents();
        i();
         setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bloqueo();
       Ac();  
        selecto2();
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
       psd4.setString(4,"GESTIÓN DE PROFESORES");
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
           rol1=rsu.getString("rol");                }
       Statement st2 = (Statement) cn.createStatement();
         String SQL4="SELECT * FROM actividad  WHERE idact LIKE '%" + est + "%' ";
          ResultSet rsu3 =    st2.executeQuery(SQL4);
       
         while (rsu3.next()){     
        
                
                  nue2=rsu3.getString("nuevo2"); 
                  gua2=rsu3.getString("guardar2"); 
                  edi2=rsu3.getString("editar2"); 
                  eli2=rsu3.getString("eliminar2"); 
   
                  
            if(nue2.equals("1")){
            btn.setEnabled(true);
                 }else{
            btn.setEnabled(false);
                 }
           if(gua2.equals("1")){
            btg.setEnabled(true);
               }else{
            btg.setEnabled(false);
              }
           if(edi2.equals("1")){
            bte.setEnabled(true); 
            }else{
            bte.setEnabled(false);
            }
           
           if(eli2.equals("1")){
            btel.setEnabled(true);
            
        }else{
            btel.setEnabled(false);
        } 
         }
       
        
         }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
    
       }
    
    void borrar(){
      rdf.setSelected(false);
   rdm.setSelected(false);
   txtap.setText("");
  
    txtci.setText("");
    txtno.setText("");
   
    txtfe.setText("");
    foto12.setIcon(null);
       foto12.setText("FOTO"); 
    }
     void bloqueo(){
         
          rdf.setEnabled(false);
          
          
             
   rdm.setEnabled(false);
   txtap.setEnabled(false);
    
    txtci.setEnabled(false);
    txtno.setEnabled(false);
  foto12.setEnabled(false);
    }
      void desbloqueo(){
             rdf.setEnabled(true);
   rdm.setEnabled(true);
  
   
   txtap.setEnabled(true);
   
    txtci.setEnabled(true);
    txtno.setEnabled(true);
    foto12.setEnabled(true);
        
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
        jPanel3 = new javax.swing.JPanel();
        rdm = new javax.swing.JRadioButton();
        txtci = new javax.swing.JTextField();
        txtno = new javax.swing.JTextField();
        txtap = new javax.swing.JTextField();
        rdf = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        foto12 = new javax.swing.JLabel();
        txtfe = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btn = new javax.swing.JButton();
        btg = new javax.swing.JButton();
        bte = new javax.swing.JButton();
        btel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(null);

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
        jPanel3.add(rdm);
        rdm.setBounds(190, 210, 100, 30);

        txtci.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(txtci);
        txtci.setBounds(100, 20, 300, 35);

        txtno.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(txtno);
        txtno.setBounds(100, 70, 300, 35);

        txtap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(txtap);
        txtap.setBounds(100, 110, 300, 35);

        rdf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdf.setText("Femenino");
        rdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdfMouseClicked(evt);
            }
        });
        jPanel3.add(rdf);
        rdf.setBounds(190, 180, 93, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Cedula:");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(20, 30, 69, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Apellido:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 120, 78, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Nombre:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(10, 70, 79, 22);

        jPanel6.setBackground(new java.awt.Color(128, 128, 128));

        jPanel2.setBackground(new java.awt.Color(128, 128, 128));

        foto12.setBackground(new java.awt.Color(204, 204, 255));
        foto12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto12.setText("FOTO");
        foto12.setOpaque(true);
        foto12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foto12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(foto12, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foto12, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6);
        jPanel6.setBounds(410, 20, 200, 130);
        jPanel3.add(txtfe);
        txtfe.setBounds(678, 337, 60, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("Sexo");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(220, 150, 44, 22);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(200, 50, 630, 248);

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIÓN DE PROFESORES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 0, 830, 40);

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
        btn.setBounds(0, 10, 170, 60);

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
        btg.setBounds(0, 80, 170, 60);

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
        bte.setBounds(0, 150, 170, 60);

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
        btel.setBounds(0, 220, 170, 60);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.2.png"))); // NOI18N
        jPanel5.add(jLabel3);
        jLabel3.setBounds(0, 200, 240, 110);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 50, 170, 310);

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/exits.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6);
        jLabel6.setBounds(730, 340, 100, 75);

        jLabel7.setBackground(new java.awt.Color(204, 204, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/download.png"))); // NOI18N
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel7);
        jLabel7.setBounds(610, 340, 107, 75);

        jLabel12.setBackground(new java.awt.Color(-13408513,true));
        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel12.setForeground(new java.awt.Color(-1,true));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("SALIR");
        jLabel12.setOpaque(true);
        jPanel1.add(jLabel12);
        jLabel12.setBounds(730, 310, 100, 19);

        jLabel13.setBackground(new java.awt.Color(-13408513,true));
        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel13.setForeground(new java.awt.Color(-1,true));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("MINIMIZAR");
        jLabel13.setOpaque(true);
        jPanel1.add(jLabel13);
        jLabel13.setBounds(610, 310, 110, 19);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdmActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
     desbloqueo();
     borrar();
     Ac=1;
    }//GEN-LAST:event_btnActionPerformed

    private void btgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgActionPerformed
String sexo1="0",nombre1,apellido1,edad1="0",telf,correo1="0",cedula1,codigo1,fe,fech,ceco="0";
        

 
 cedula1= txtci.getText();
 
 
 nombre1=txtno.getText();
 apellido1= txtap.getText();
 
 
    
     if(rdf.isSelected()){
         sexo1="Femenino";  
     } 
       if(rdm.isSelected()){
         sexo1="Masculino";   
     }
    
  
   txtfe.setText(fechaActual1());
   fe=txtfe.getText();
  fech=txtfe.getText();
  
  
        String SQL,SQL2;
        
        

        
      

        PreparedStatement psd;
 if(cedula1.equals("")||nombre1.equals("")||apellido1.equals("")||sexo1.equals("0")){
     JOptionPane.showMessageDialog(this,"¡¡Por favor Verifique los Campos!!");
 } else{
  
     
       
        
       
      
        switch(Ac){
           
            case 1:

            try{
                  String  SQL0 = "select * from profesor where cedula LIKE '%" + cedula1 + "%'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQL0);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
           } 
        if(ceco.equals(cedula1)){
        JOptionPane.showMessageDialog(this,"Cedula Ya Existe");     
        }else{

                SQL="INSERT INTO profesor(cedula,nombre,apellido,sexo,nofo,rufo)VALUES (?,?,?,?,?,?)";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,cedula1);
                psd.setString(2,nombre1);
                psd.setString(3,apellido1);
                     psd.setString(4,sexo1);
                psd.setString(5,nofo);
          
                psd.setString(6,rufo);
                 
                
                
                 int m= psd.executeUpdate();
                  
               
                  
                

                if((m>0)){
                    acti="GUARDAR";
                    fpa();
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

             
                SQL="UPDATE profesor set cedula=?,nombre=?,apellido=?,sexo=?,nofo=?,rufo=? WHERE cedula=? ";

                psd = cn.prepareStatement(SQL);
                 psd.setString(1,cedula1);
                psd.setString(2,nombre1);
                psd.setString(3,apellido1);
               
                psd.setString(4,sexo1);
                  psd.setString(5,nofo);
          
                psd.setString(6,rufo);
                 
              
                  psd.setString(7,cod);
                int f=  psd.executeUpdate();
                
               
               
                 if((f>0)){
                     acti="ACTUALIZAR";
                    fpa();
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
    }//GEN-LAST:event_btgActionPerformed

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

private void bteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteActionPerformed
Ac=2;
borrar();
String ceco="";

   
   cod = JOptionPane.showInputDialog("Cedula:");
   if(cod.equals("")){
         JOptionPane.showMessageDialog(this, "Introdusca Cedula");
          bloqueo();
    }else{
    try
    {
         String  SQLr = "select * from profesor where cedula LIKE '%" + cod + "%'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQLr);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
           } 
        if(ceco.equals(cod)){
  String SQL1 = "SELECT * FROM profesor WHERE cedula=" +cod;
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
       desbloqueo();         
      
 
      txtci.setText(rs.getString("cedula"));
     txtno.setText(rs.getString("nombre"));
       txtap.setText(rs.getString("apellido"));
 
 
     String sex=rs.getString("sexo");
     
     if(sex.equals("Femenino")) {
         rdf.isSelected();
         rdf.setSelected(true);
     }else{
         rdm.isSelected();
         rdm.setSelected(true);
     }
   
     rufo=rs.getString("rufo");
   nofo=rs.getString("nofo");
    String fu=rufo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
           foto12.setIcon(new ImageIcon(foto));
              if(rufo.equals("")||rufo.equals("No posee")){
         foto12.setIcon(null);
              foto12.setText("FOTO");
        }{
                   foto12.setText("");
              }
        }else{
            JOptionPane.showMessageDialog(this, "Cedula No Existe");
        }
    }
    catch (SQLException ex) {}
    
   }
}//GEN-LAST:event_bteActionPerformed

private void btelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btelActionPerformed
borrar(); String ceco="";

   
   cod = JOptionPane.showInputDialog("Cedula a Eliminar:");
    if(cod.equals("")){
         JOptionPane.showMessageDialog(this, "Introdusca Cedula");
    }else{
    try
    {
          String  SQLr = "select * from profesor where cedula LIKE '%" + cod + "%'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQLr);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
        bloqueo();
           } 
        if(ceco.equals(cod)){
      String SQL1 = "SELECT * FROM profesor WHERE cedula=" +cod;
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
               
       bloqueo();
     
      txtci.setText(rs.getString("cedula"));
     txtno.setText(rs.getString("nombre"));
       txtap.setText(rs.getString("apellido"));
  
     String sex=rs.getString("sexo");
     
     if(sex.equals("Femenino")) {
         rdf.isSelected();
         rdf.setSelected(true);
     }else{
         rdm.isSelected();
         rdm.setSelected(true);
     }
    
     bloqueo();
       rufo=rs.getString("rufo");
   nofo=rs.getString("nofo");
    String fu=rufo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
           foto12.setIcon(new ImageIcon(foto));
       foto12.setIcon(new ImageIcon(foto));
      
              if(rufo.equals("")||rufo.equals("No posee")){
         foto12.setIcon(null);
              foto12.setText("FOTO");
        }else{
                   foto12.setText("");
              }
     
  
     int resp = JOptionPane.showConfirmDialog(this, "Deseas Eliminarlo", "Eliminar Dato", 0);
      if (resp == 0) {
       
          PreparedStatement psd = null;
        String  SQL = " DELETE FROM profesor WHERE cedula ='" + cod + "'";
          psd = this.cn.prepareStatement(SQL);
          psd.execute();
          psd.close();
            acti="ELIMINAR";
                    fpa();
          borrar();
         bloqueo();
          JOptionPane.showMessageDialog(this, "REGISTRO ELIMINADO");
        
      } else {
        borrar();
         bloqueo();
      }
        }  else {
        JOptionPane.showMessageDialog(null, "Cedula No Existe");
      }
        } catch (SQLException ex) {}
    }
}//GEN-LAST:event_btelActionPerformed

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
         dispose();
           // sa = "1";
         //   acti = "SALIDA";
         //new ModPriMenu().setVisible(true);  
        //fpa();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6MouseClicked

private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel7MouseClicked

private void foto12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foto12MouseClicked
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
          nofo=String.valueOf(rus);
            rufo=String.valueOf(file);
            rus =  rufo;

            //de cierto modo necesitamos tener la imagen para ello debemos conocer la ruta de dicha imagen
            Image foto = getToolkit().getImage(rus);
            //Le damos dimension a nuestro label que tendra la imagen
            foto = foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            //Imprimimos la imagen en el label
            foto12.setIcon(new ImageIcon(foto));
             foto12.setText(""); 
        }// TODO add your handling code here:
}//GEN-LAST:event_foto12MouseClicked

        
      

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
            java.util.logging.Logger.getLogger(ModRegPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModRegPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModRegPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModRegPro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModRegPro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bte;
    private javax.swing.JButton btel;
    private javax.swing.JButton btg;
    private javax.swing.JButton btn;
    private javax.swing.JLabel foto12;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton rdf;
    private javax.swing.JRadioButton rdm;
    private javax.swing.JTextField txtap;
    private javax.swing.JTextField txtci;
    private javax.swing.JLabel txtfe;
    private javax.swing.JTextField txtno;
    // End of variables declaration//GEN-END:variables
}
