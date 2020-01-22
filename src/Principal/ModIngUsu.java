/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModIngUsu.java
 *
 * Created on 03-ene-2018, 9:38:57
 */
package Principal;

import Segundario.bdc;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author back
 */
public class ModIngUsu extends javax.swing.JFrame {
String SQL1,SQL4;
bdc cc = new bdc();
        Connection cn = cc.Conectar();
        int Ac,con,con2,vali;
        String cod,cedu,est,ro,usuario1,ruta1,fono,cedu2,cedu3,resp,resp2,pre,cotr,usu;
    /** Creates new form ModIngUsu */
    public ModIngUsu() {
          
        setUndecorated(true);
        initComponents();
        i();
         this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Bloquear();
        jPanel3.setVisible(false);
         jButton1.setVisible(false);
         jPanel5.setVisible(false);
         lbpre.setVisible(false);
         resputxt.setVisible(false);  
          jPanel6.setVisible(false);
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
      void limpiar(){
        txtusu.setText("");
        txtcon.setText("");
    }
     void Bloquear(){
        txtusu.setEnabled(false);
        txtcon.setEnabled(false);
    }
      void desbloquear(){
        txtusu.setEnabled(true);
        txtcon.setEnabled(true);
    }
      void guardar(){
   
 
      usuario1=txtusu.getText();
     
    
     PreparedStatement psd1,psd2,psd3,psd4;
       if((usuario1.isEmpty()))
       {
           limpiar();
           Bloquear();
           
         JOptionPane.showMessageDialog(null,"Por favor llene el campo!");
       }
       else
       {
         
         
       try
       {
     int encontrado;
        String SQL2="SELECT * FROM datos_usuario  WHERE usuario ='"+encriptaEnMD5(usuario1)+"'";
        Statement st2 = (Statement) cn.createStatement();
        ResultSet rs = st2.executeQuery("SELECT * FROM datos_usuario  WHERE usuario ='"+encriptaEnMD5(usuario1)+"'");
        rs.last();  
        encontrado =rs.getRow();
         if(encontrado!=1){
          JOptionPane.showMessageDialog(null,"Usuario no existe");     
         }
        ResultSet rsu =    st2.executeQuery(SQL2);
        while (rsu.next()){
        cedu=rsu.getString("idusu");   
        cedu3=rsu.getString("cedula");    
        est=rsu.getString("estado");  
         resp=rsu.getString("respuesta");  
          pre=rsu.getString("pregunta");  
        
        ro=rsu.getString("rol");    
          ruta1=rsu.getString("rufo");
 if(ruta1.equals("No pose")){
     
 }else{
    
     File ruta = new File(ruta1);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(90, 90, Image.SCALE_DEFAULT);
          lbfo.setIcon(new ImageIcon(foto));
 }
         }
        if(est.equals("-2")){
            JOptionPane.showMessageDialog(null,"Cambie Usuario y Contraseña"); 
            jPanel5.setVisible(true);
            con=3;
             jButton1.setVisible(false);  
  jButton2.setVisible(true);  
          
          
        }else{
        if(ro.equals("7")){
         JOptionPane.showMessageDialog(null,"NO ESTA AUTORIZADO");     
        }else{
         if(encontrado==1)
         { 
          jPanel1.setVisible(false);
              jPanel3.setVisible(true);
              //jButton2.setVisible(false);
              jButton1.setVisible(true);
         }
        
         } 
        }
         }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
       }
      }
      
      void guardar2(){
          try{
               String password=txtcon.getText();
            
            if((password.isEmpty()))
       {
           limpiar();
           Bloquear();
           
         JOptionPane.showMessageDialog(null,"Por favor llene el campo!");
       }  else{
            String SQL2="SELECT * FROM datos_usuario  WHERE usuario ='"+encriptaEnMD5(usuario1)+"' AND contraseña ='"+encriptaEnMD5(password)+"'";
        Statement st2 = (Statement) cn.createStatement();
        ResultSet rs = st2.executeQuery(SQL2);
       rs.last();
      int  encontrado2=rs.getRow();
       if(encontrado2!=1){
           
           con+=1;
           JOptionPane.showMessageDialog(null,"Contraseña Incorrecta");
           if(con==3){
             JOptionPane.showMessageDialog(null,"Cambie Usuario y Contraseña");      
                PreparedStatement psd;
             String SQL;
             SQL="UPDATE datos_usuario set estado=? WHERE idusu=?";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,"-2");
                psd.setString(2,cedu);
                 int f=  psd.executeUpdate();
                  
                  
          
                
           }
             
          
         }
       if(encontrado2==1)
         {
             PreparedStatement psd;
             String SQL;
             SQL="UPDATE datos_usuario set estado=? WHERE idusu=?";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,cedu);
                psd.setString(2,cedu);
                 int f=  psd.executeUpdate();
          
                  this.dispose();
         new cronometro1().setVisible(true);
        new  ModPriMenu().setVisible(true);  
       
         } 
        
         } 
      }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
       }  
 void contraseña(){
         vali=0;
         String password="";
         byte  contNumero = 0, contLetraMay = 0, contLetraMin=0;
       //1 mayuscula, 1 minuscula, 1 numero minimo
        password = contra2.getText();
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        txtcedula = new javax.swing.JTextField();
        resputxt = new javax.swing.JTextField();
        lbpre = new javax.swing.JLabel();
        lbpre1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtusu2 = new javax.swing.JTextField();
        lbpre2 = new javax.swing.JLabel();
        lbpre3 = new javax.swing.JLabel();
        contra2 = new javax.swing.JPasswordField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtcon = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbfo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtusu = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(0));
        jPanel2.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(-3355444,true));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(-65536,true));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/exits.png"))); // NOI18N
        jLabel1.setText("SALIR");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setOpaque(true);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1);
        jLabel1.setBounds(330, 220, 90, 80);

        jLabel7.setBackground(new java.awt.Color(51, 102, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(-1,true));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("USUARIO");
        jLabel7.setOpaque(true);
        jPanel2.add(jLabel7);
        jLabel7.setBounds(2, 1, 420, 30);

        jButton1.setBackground(new java.awt.Color(-13421773,true));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 12));
        jButton1.setForeground(new java.awt.Color(-1,true));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/accept.png"))); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(140, 220, 130, 61);

        jPanel5.setBackground(new java.awt.Color(-1,true));

        txtcedula.setFont(new java.awt.Font("Monospaced", 1, 18));
        txtcedula.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtcedula.setAutoscrolls(false);

        resputxt.setFont(new java.awt.Font("Monospaced", 1, 14));
        resputxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        resputxt.setAutoscrolls(false);
        resputxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resputxtActionPerformed(evt);
            }
        });

        lbpre.setFont(new java.awt.Font("Dialog", 1, 12));
        lbpre.setForeground(new java.awt.Color(-16776961,true));
        lbpre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbpre.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lbpre1.setFont(new java.awt.Font("Dialog", 1, 14));
        lbpre1.setForeground(new java.awt.Color(-13408513,true));
        lbpre1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbpre1.setText("CEDULA");
        lbpre1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setOpaque(false);
        jPanel6.setLayout(null);

        txtusu2.setFont(new java.awt.Font("Monospaced", 1, 18));
        jPanel6.add(txtusu2);
        txtusu2.setBounds(10, 46, 171, 28);

        lbpre2.setFont(new java.awt.Font("Dialog", 1, 14));
        lbpre2.setForeground(new java.awt.Color(-13408513,true));
        lbpre2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbpre2.setText("Nueva Contraseña");
        jPanel6.add(lbpre2);
        lbpre2.setBounds(10, 90, 171, 19);

        lbpre3.setFont(new java.awt.Font("Dialog", 1, 14));
        lbpre3.setForeground(new java.awt.Color(-13408513,true));
        lbpre3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbpre3.setText("Nuevo Usuario");
        jPanel6.add(lbpre3);
        lbpre3.setBounds(10, 11, 171, 35);

        contra2.setFont(new java.awt.Font("Monospaced", 1, 18));
        jPanel6.add(contra2);
        contra2.setBounds(10, 115, 170, 30);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbpre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcedula, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                        .addComponent(resputxt))
                    .addComponent(lbpre1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lbpre1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lbpre, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resputxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(0, 30, 420, 180);

        jPanel3.setBackground(new java.awt.Color(-1,true));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);
        jPanel3.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel8.setForeground(new java.awt.Color(-16776961,true));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Contraseña:");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(10, 0, 190, 30);

        txtcon.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtconMouseClicked(evt);
            }
        });
        jPanel3.add(txtcon);
        txtcon.setBounds(10, 30, 190, 31);

        jPanel2.add(jPanel3);
        jPanel3.setBounds(100, 140, 210, 70);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel3);
        jLabel3.setBounds(-6, 0, 500, 30);

        jPanel4.setBackground(new java.awt.Color(-8355712,true));

        lbfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/usuario.png"))); // NOI18N
        lbfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbfo, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(150, 40, 110, 90);

        jPanel1.setBackground(new java.awt.Color(-1,true));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel9.setForeground(new java.awt.Color(-16776961,true));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Usuario:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(10, 0, 190, 30);

        txtusu.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        txtusu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtusuMouseClicked(evt);
            }
        });
        txtusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuActionPerformed(evt);
            }
        });
        txtusu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusuKeyPressed(evt);
            }
        });
        jPanel1.add(txtusu);
        txtusu.setBounds(10, 30, 187, 31);

        jPanel2.add(jPanel1);
        jPanel1.setBounds(100, 140, 210, 70);

        jButton2.setBackground(new java.awt.Color(-13421773,true));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 12));
        jButton2.setForeground(new java.awt.Color(-1,true));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/next_1.png"))); // NOI18N
        jButton2.setText("Siguiente");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(140, 220, 130, 61);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.3.png"))); // NOI18N
        jPanel2.add(jLabel4);
        jLabel4.setBounds(0, 170, 360, 130);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtusuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtusuMouseClicked
desbloquear();// TODO add your handling code here:
}//GEN-LAST:event_txtusuMouseClicked

private void txtusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtusuActionPerformed

private void txtusuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_txtusuKeyPressed

private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
dispose();
}//GEN-LAST:event_jLabel1MouseClicked

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 guardar2();
    if(con>=3){
         jPanel5.setVisible(true);
    jButton1.setVisible(false);  
  jButton2.setVisible(true);  
}
   // TODO add your handling code here:
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   File ruta = new File("C://Program Files//Sana//ImaPeq//accept2.png");
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
    if(con>=3){
        con2=0; 
    cedu2=txtcedula.getText();
                   if(cedu2.equals(cedu3)&& con2==0){
                    
            
         lbpre1.setIcon(new ImageIcon(foto));
                     lbpre.setVisible(true);
                       resputxt.setVisible(true);
                       lbpre.setText(pre);
                        txtcedula.setEnabled(false); 
                     con2=1;
                   } 
                   resp2=resputxt.getText();
                   if((resp2.equals(resp))&& (con2==1)){
                     
                      lbpre.setIcon(new ImageIcon(foto));
                       resputxt.setEnabled(false); 
                       jPanel6.setVisible(true);
                       con2=2;
                   }
                   usu=txtusu2.getText();
                     cotr=contra2.getText();
                  
                   if((usu.isEmpty()|| cotr.isEmpty())||(con2!=2)){
                   }else{
                       try{
                           int  cancar=0;
                           String descripcion=cotr;
 
    char[] arrayChar= descripcion.toCharArray();
    for(int i=0; i< arrayChar.length; i++){
    arrayChar[i]='0';
 
         cancar=descripcion.length();
         
}
    contraseña();
     if(vali==1 ){
    JOptionPane.showMessageDialog(this,"La contraseña debe de tener Nuemeros y letras Mayusculas");       
  }else{
     if(cancar<8){
    
    JOptionPane.showMessageDialog(this,"Su Contraseña es Muy Corta");
  }else{
                         PreparedStatement psd;
             String SQL;
             SQL="UPDATE datos_usuario set estado=?,usuario=?,contraseña=? WHERE idusu=?";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,"0");
                 psd.setString(2,encriptaEnMD5(usu));
                  psd.setString(3,encriptaEnMD5(cotr));
                psd.setString(4,cedu);
                 int f=  psd.executeUpdate();
                  
                    lbpre3.setIcon(new ImageIcon(foto));
                     lbpre2.setIcon(new ImageIcon(foto)); 
                       txtusu2.setEnabled(false);
                    contra2.setEnabled(false);
                       con2=3;
                        JOptionPane.showMessageDialog(null,"PROCESO EXITOSO");
                        this.dispose();
     } }}catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
                   }         
}else{
  guardar();    
}
  // TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

private void txtconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtconMouseClicked
desbloquear();// TODO add your handling code here:
}//GEN-LAST:event_txtconMouseClicked

private void resputxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resputxtActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_resputxtActionPerformed

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
            java.util.logging.Logger.getLogger(ModIngUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModIngUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModIngUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModIngUsu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
               
                new ModIngUsu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField contra2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbfo;
    private javax.swing.JLabel lbpre;
    private javax.swing.JLabel lbpre1;
    private javax.swing.JLabel lbpre2;
    private javax.swing.JLabel lbpre3;
    private javax.swing.JTextField resputxt;
    private javax.swing.JTextField txtcedula;
    private javax.swing.JPasswordField txtcon;
    private javax.swing.JTextField txtusu;
    private javax.swing.JTextField txtusu2;
    // End of variables declaration//GEN-END:variables
}
