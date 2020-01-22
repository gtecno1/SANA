/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModGesRol.java
 *
 * Created on 03-ene-2018, 10:42:39
 */
package Principal;

import Segundario.bdc;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author back
 */
public class ModGesRol extends javax.swing.JFrame {
  int Ac;
    bdc cc = new bdc();
Connection cn = cc.Conectar();
String ro3;
   String nu="0",gu="0",ed="0",el="0";
    String Ho,Hora,Hora2,Horaf,est,cedu,cedu2,acti="ninguna",fech,nom,sa="0"; 
String rol1,rol2,rol,cedula,idrol,idpro,idact;
 String nue="0",gua="0",eli="0",edi="0",nue2="0",gua2="0",eli2="0",edi2="0",nue3="0",gua3="0",eli3="0",repo3="0",edi3="0",nue4="0",gua4="0",eli4="0",edi4="0",proce5="0",selecc5="0",repo5="0",busqe6="0",busqav6="0",repo6="0",busqe7="0",busqav7="0",repo7="0";
 String gesper,gespro,gessec,gesrol,estn,conse;
  DefaultTableModel model;
String idus,idus2;


 
String q="0",w="0",e="0",r="0"   , t="0",y="0",u="0",i="0"  ,o="0",p="0",a="0",s="0"  ,d="0",f="0",g="0",h="0"   ,j="0",k="0",l="0",z="0";
   

/** Creates new form ModGesRol */
    public ModGesRol() {
          setUndecorated(true);
        initComponents();
        i();
         setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       Ac();
        
       cargarusuarios();
       selecto();
       selecto4();
       Bloqueo();
        txtr.setVisible(false);
           jButton1.setVisible(false);  
       limpiar();
    
    }
     void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ImaPeq/logo.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
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
       psd4.setString(4,"GESTIÓN DE ROL");
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
   void selecto4() {
       
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
         cedu2=rsu.getString("cedula");   
          nom=rsu.getString("nombre");   
           rol1=rsu.getString("rol");              }
       Statement st2 = (Statement) cn.createStatement();
         String SQL4="SELECT * FROM actividad  WHERE idact LIKE '%" + est + "%' ";
          ResultSet rsu3 =    st2.executeQuery(SQL4);
       
         while (rsu3.next()){     
        
                
             
                  nu=rsu3.getString("nuevo4"); 
                  gu=rsu3.getString("guardar4"); 
                  ed=rsu3.getString("editar4"); 
                  el=rsu3.getString("eliminar4"); 
                  
                  
            if(nu.equals("1")){
            btn.setEnabled(true);
                 }else{
            btn.setEnabled(false);
                 }
           if(gu.equals("1")){
            btg.setEnabled(true);
               }else{
            btg.setEnabled(false);
              }
           if(ed.equals("1")){
            bte.setEnabled(true); 
            }else{
            bte.setEnabled(false);
            }
           
           if(el.equals("1")){
            btel.setEnabled(true);
            
        }else{
            btel.setEnabled(false);
        } 
         }
       
           }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
    
       }
 void limpiar(){
 boxrol.setSelectedItem("--Seleccione--");
        chperso1.setSelected(false);
   
        chsecc.setSelected(false);
  
        chprofesor.setSelected(false);
    
        chrol.setSelected(false);
  
        nuevo1.setSelected(false);
 
        guardar1.setSelected(false);
  
         editar1.setSelected(false);

     eliminar1.setSelected(false);
        nuevo2.setSelected(false);
    
        guardar2.setSelected(false);
    
         editar2.setSelected(false);
     
         eliminar2.setSelected(false);
  
        nuevo3.setSelected(false);
      
        guardar3.setSelected(false);

         editar3.setSelected(false);
   
         eliminar3.setSelected(false);
         rep3.setSelected(false);
          nuevo4.setSelected(false);
      
        guardar4.setSelected(false);

         editar4.setSelected(false);
   
         eliminar4.setSelected(false);
         
         est5.setSelected(false);
         proc5.setSelected(false);
         selec5.setSelected(false);
         rep5.setSelected(false);
         cest6.setSelected(false);
         busq6.setSelected(false);
         busqa6.setSelected(false);
         rep6.setSelected(false);
         cest7.setSelected(false);
         busq7.setSelected(false);
         busqa7.setSelected(false);
         rep7.setSelected(false);
         resp.setSelected(false);
         rest.setSelected(false);
 }
 void Bloqueo(){
 boxrol.setEnabled(false);
       chperso1.setEnabled(false);
   
        chsecc.setEnabled(false);
  
        chprofesor.setEnabled(false);
    
        chrol.setEnabled(false);
  
        nuevo1.setEnabled(false);
 
        guardar1.setEnabled(false);
  
         editar1.setEnabled(false);

     eliminar1.setEnabled(false);
        nuevo2.setEnabled(false);
    
        guardar2.setEnabled(false);
    
         editar2.setEnabled(false);
     
         eliminar2.setEnabled(false);
  
        nuevo3.setEnabled(false);
      
        guardar3.setEnabled(false);

         editar3.setEnabled(false);
   
         eliminar3.setEnabled(false);
          rep3.setEnabled(false);
          nuevo4.setEnabled(false);
      
        guardar4.setEnabled(false);

         editar4.setEnabled(false);
   
         eliminar4.setEnabled(false);
         
         est5.setEnabled(false);
         proc5.setEnabled(false);
         selec5.setEnabled(false);
         rep5.setEnabled(false);
         cest6.setEnabled(false);
         busq6.setEnabled(false);
         busqa6.setEnabled(false);
         rep6.setEnabled(false);
          cest7.setEnabled(false);
          busq7.setEnabled(false);
         busqa7.setEnabled(false);
         rep7.setEnabled(false);
         resp.setEnabled(false);
         rest.setEnabled(false);
             
 }
  void desbloqueo(){
 boxrol.setEnabled(true);
        chperso1.setEnabled(true);
   
        chsecc.setEnabled(true);
  
        chprofesor.setEnabled(true);
    
        chrol.setEnabled(true);
  
        nuevo1.setEnabled(true);
 
        guardar1.setEnabled(true);
  
         editar1.setEnabled(true);

     eliminar1.setEnabled(true);
        nuevo2.setEnabled(true);
    
        guardar2.setEnabled(true);
    
         editar2.setEnabled(true);
     
         eliminar2.setEnabled(true);
  
        nuevo3.setEnabled(true);
      
        guardar3.setEnabled(true);

         editar3.setEnabled(true);
   
         eliminar3.setEnabled(true);
          rep3.setEnabled(true);
          nuevo4.setEnabled(true);
      
        guardar4.setEnabled(true);

         editar4.setEnabled(true);
   
         eliminar4.setEnabled(true);
          est5.setEnabled(true);
         proc5.setEnabled(true);
         selec5.setEnabled(true);
         rep5.setEnabled(true);
         cest6.setEnabled(true);
         busq6.setEnabled(true);
         busqa6.setEnabled(true);
         rep6.setEnabled(true);
          cest7.setEnabled(true);
          busq7.setEnabled(true);
         busqa7.setEnabled(true);
         rep7.setEnabled(true);
         resp.setEnabled(true);
         rest.setEnabled(true);
 }
            
void selecto() {
       
    try
    {
      
      String SQL ;
   
     SQL = "select * from rol_usuario";
      
     Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      while (rs.next())
      {
       
      boxrol.addItem( rs.getString("Descripcion"));
      }
          }     
     
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
      }
void selecto2() {
       
    try
    {
      
      String SQL ;
      String ro2=String.valueOf(boxrol.getSelectedItem());
   
     SQL = "select * from rol_usuario where Descripcion  LIKE '%" + ro2 + "%'";
      
     Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      while (rs.next())
      {
       
     ro3=rs.getString("idrol");
      }
          }     
     
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
      }
void selecto3() {
       
    try
    {
      
      String SQL ;
      String ro2=String.valueOf(boxrol.getSelectedItem());
   
     SQL = "select * from rol_usuario where idrol  LIKE '%" + rol + "%'";
      
     Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      while (rs.next())
      {
     boxrol.setSelectedItem( rs.getString("Descripcion"));   
     
      }
          }     
     
    catch (Exception e)
    {
      System.out.println(e.getMessage());
    }
      }
void Marcado2(){
    try{
          String SQL,SQL2,SQL3,SQL4;  
           SQL = " SELECT * FROM datos_usuario WHERE idusu = " +idus;
           SQL2 = " SELECT * FROM proceso WHERE idpro = " +idus;
           SQL3 = " SELECT * FROM actividad WHERE idact = " + idus;
           
        Statement  st = cn.createStatement();
         ResultSet rs = st.executeQuery(SQL);
         rs.next();
         Statement  st1 = cn.createStatement();
         ResultSet rs2 = st1.executeQuery(SQL2);
         rs2.next();  
         st1 = cn.createStatement();
         ResultSet rs3 = st1.executeQuery(SQL3);
         rs3.next();
          rol=rs.getString("rol"); 
           q=rs2.getString("gesper"); 
            e=rs2.getString("gessecc"); 
             w=rs2.getString("gespro");  
              r=rs2.getString("gesrol"); 
               t=rs2.getString("estn");  
              y=rs2.getString("consultaest"); 
               u=rs2.getString("auditoria"); 
               i=rs2.getString("respaldo"); 
               o=rs2.getString("restaurar"); 
             
              nue=rs3.getString("nuevo"); 
                gua=rs3.getString("guardar"); 
                 edi=rs3.getString("editar"); 
                  eli=rs3.getString("elimiar"); 
             
                  nue2=rs3.getString("nuevo2"); 
                gua2=rs3.getString("guardar2"); 
                 edi2=rs3.getString("editar2"); 
                  eli2=rs3.getString("eliminar2"); 
              
                  nue3=rs3.getString("nuevo3"); 
                gua3=rs3.getString("guardar3"); 
                 edi3=rs3.getString("editar3"); 
                  eli3=rs3.getString("eliminar3");
                   repo3=rs3.getString("reporte3");
                     nue4=rs3.getString("nuevo4"); 
                gua4=rs3.getString("guardar4"); 
                 edi4=rs3.getString("editar4"); 
                  eli4=rs3.getString("eliminar4"); 
                proce5=rs3.getString("procesar5"); 
               selecc5=rs3.getString("seleccionar5"); 
               repo5=rs3.getString("reporte5"); 
               busqe6=rs3.getString("busqueda6");
               busqav6=rs3.getString("busquedaav6");
               repo6=rs3.getString("reporte6");  
                 busqe7=rs3.getString("busqueda7");
               busqav7=rs3.getString("busquedaav7");
               repo7=rs3.getString("reporte7");  
        ////////////////////////
       int rol1 = Integer.valueOf(rol); 
       
      if( rol1!=7 ){ 
          selecto3(); 
      if(q.equals("1")){
      
          chperso1.setSelected(true);
        }else{
       chperso1.setSelected(false);
        }
      
      if(e.equals("1")){
        chsecc.setSelected(true);
        }else{
        chsecc.setSelected(false);
        }  
      if(w.equals("1")){
       chprofesor.setSelected(true);
        }else{
        chprofesor.setSelected(false);
        }  
      if(r.equals("1")){
       chrol.setSelected(true);
        }else{
        chrol.setSelected(false);
        }  
       if(t.equals("1")){
       est5.setSelected(true);
        }else{
        est5.setSelected(false);
        }  
        if(y.equals("1")){
       cest6.setSelected(true);
        }else{
       cest6.setSelected(false);
        } 
         if(u.equals("1")){
       cest7.setSelected(true);
        }else{
       cest7.setSelected(false);
        } 
          if(i.equals("1")){
      resp.setSelected(true);
        }else{
      resp.setSelected(false);
        } 
         if(o.equals("1")){
       rest.setSelected(true);
        }else{
       rest.setSelected(false);
        } 
      //////////////
       if(nue.equals("1")){
        nuevo1.setSelected(true);
        }else{
        nuevo1.setSelected(false);
        }  
      if(gua.equals("1")){
        guardar1.setSelected(true);  
        }else{
        guardar1.setSelected(false);
        }  
      if(edi.equals("1")){
      editar1.setSelected(true);
        }else{
         editar1.setSelected(false);
        }  
      if(eli.equals("1")){
      eliminar1.setSelected(true);
        }else{
         eliminar1.setSelected(false);
        }  
      
       if(nue2.equals("1")){
        nuevo2.setSelected(true);
        }else{
        nuevo2.setSelected(false);
        }  
      if(gua2.equals("1")){
        guardar2.setSelected(true);
        }else{
        guardar2.setSelected(false);
        }  
      if(edi2.equals("1")){
      editar2.setSelected(true);
        }else{
         editar2.setSelected(false);
        }  
      if(eli2.equals("1")){
      eliminar2.setSelected(true);
        }else{
         eliminar2.setSelected(false);
        }  
      
       if(nue3.equals("1")){
        nuevo3.setSelected(true);
        }else{
        nuevo3.setSelected(false);
        }  
      if(gua3.equals("1")){
        guardar3.setSelected(true);
        }else{
        guardar3.setSelected(false);
        }  
      if(edi3.equals("1")){
      editar3.setSelected(true);
        }else{
         editar3.setSelected(false);
        }  
      if(eli3.equals("1")){
      eliminar3.setSelected(true);
        }else{
         eliminar3.setSelected(false);
        }  
       if(repo3.equals("1")){
      rep3.setSelected(true);
        }else{
        rep3.setSelected(false);
        }  
       if(nue4.equals("1")){
        nuevo4.setSelected(true);
        }else{
        nuevo4.setSelected(false);
        }  
      if(gua4.equals("1")){
        guardar4.setSelected(true);
        }else{
        guardar4.setSelected(false);
        }  
      if(edi4.equals("1")){
      editar4.setSelected(true);
        }else{
         editar4.setSelected(false);
        }  
      if(eli4.equals("1")){
      eliminar4.setSelected(true);
        }else{
         eliminar4.setSelected(false);
        }
           
       if(proce5.equals("1")){
      proc5.setSelected(true);
        }else{
         proc5.setSelected(false);
        }  
        if( selecc5.equals("1")){
      selec5.setSelected(true);
        }else{
        selec5.setSelected(false);
        }  
         if( repo5.equals("1")){
     rep5.setSelected(true);
        }else{
       rep5.setSelected(false);
        }  
     if(busqe6.equals("1")){
     busq6.setSelected(true);
        }else{
      busq6.setSelected(false);
        }  
      if(busqav6.equals("1")){
     busqa6.setSelected(true);
        }else{
      busqa6.setSelected(false);
        } 
      if(repo6.equals("1")){
     rep6.setSelected(true);
        }else{
      rep6.setSelected(false);
        } 
       if(busqe7.equals("1")){
     busq7.setSelected(true);
        }else{
      busq7.setSelected(false);
        }  
      if(busqav7.equals("1")){
     busqa7.setSelected(true);
        }else{
      busqa7.setSelected(false);
        } 
      if(repo7.equals("1")){
     rep7.setSelected(true);
        }else{
      rep7.setSelected(false);
        } 
      }else{
         // Bloqueo();
      // JOptionPane.showMessageDialog(this,"Ya Posees rol");   
      }
      if(Ac==3){
        Bloqueo();
       int resp = JOptionPane.showConfirmDialog(this, "Deseas Eliminarlo", "Eliminar Dato", 0);
      if (resp == 0) {
        try
        {
          
             
          PreparedStatement psd = null;
          PreparedStatement psd2 = null; 
          PreparedStatement psd3 = null;
            SQL="UPDATE datos_usuario set rol=? WHERE cedula=? ";

                psd = cn.prepareStatement(SQL);
                 psd.setString(1,"7");
                psd.setString(2,cedu);
               
                psd.executeUpdate();  
          SQL2 = " DELETE FROM proceso WHERE idpro ='" +idus+ "'";
          SQL3 = " DELETE FROM actividad WHERE idact ='" +idus+ "'";
           SQL4 = " DELETE FROM usu_rol_pro_act WHERE cedula ='" +cedu+ "'";
          psd = this.cn.prepareStatement(SQL2);
          psd2 = this.cn.prepareStatement(SQL3);
          psd3 = this.cn.prepareStatement(SQL4);
          psd.execute();
          psd.close();
          psd2.execute();
          psd2.close();
           psd3.execute();
          psd3.close();
         limpiar();
       Bloqueo();
         acti="ELIMINAR";
                    fpa();
        cargarusuarios();
          JOptionPane.showMessageDialog(this, "REGISTRO ELIMINADO");
        }
        catch (SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error SQL no se Eliminaron");
        }
      } else {
       
      }
    
   
      }
         
       }catch (SQLException ex) {
          
        }    
   
     
   
    
}
  void  Marcado(){
      
    if (chperso1.isSelected()){
     q="1";   
    }else{
        q="0";
    }
     if (nuevo1.isSelected()){
     nue="1";   
    }else{
        nue="0";
    }
      if (guardar1.isSelected()){
     gua="1";   
    }
       if (editar1.isSelected()){
     edi="1";   
    }else{
        edi="0";
    }
        if (eliminar1.isSelected()){
     eli="1";   
    }else{
        eli="0";
    }
    /////////////
    if (chprofesor.isSelected()){
     w="1";   
    }else{
        w="0";
    }
     if (nuevo2.isSelected()){
     nue2="1";   
    }else{
        nue2="0";
    }
      if (guardar2.isSelected()){
     gua2="1";   
    }else{
        gua2="0";
    }
       if (editar2.isSelected()){
     edi2="1";   
    }else{
        edi2="0";
    }
        if (eliminar2.isSelected()){
     eli2="1";   
    }else{
        eli2="0";
    }
   ////////////
    if (chsecc.isSelected()){
     e="1";   
    }else{
        e="0";
    }
     if (nuevo3.isSelected()){
     nue3="1";   
    }else{
        nue3="0";
    }
      if (guardar3.isSelected()){
     gua3="1";   
    }else{
        gua3="0";
    }
       if (editar3.isSelected()){
     edi3="1";   
    }else{
        edi3="0";
    }
        if (eliminar3.isSelected()){
     eli3="1";   
    }else{
        eli3="0";
    }
         if (rep3.isSelected()){
     repo3="1";   
    }else{
        repo3="0";
    }
        ///////
    if (chrol.isSelected()){
     r="1";   
    }else{
        r="0";
    }
     if (nuevo4.isSelected()){
     nue4="1";   
    }else{
        nue4="0";
    }
      if (guardar4.isSelected()){
     gua4="1";   
    }else{
        gua4="0";
    }
       if (editar4.isSelected()){
     edi4="1";   
    }else{
        edi4="0";
    }
        if (eliminar4.isSelected()){
     eli4="1";   
    }else{
        eli4="0";
    }
        ///////////////////////////////////////////////////////////
         if (est5.isSelected()){
     t="1";   
    }else{
        t="0";
    }
         if (proc5.isSelected()){
     proce5="1";   
    }else{
       proce5="0";
    }
          if (selec5.isSelected()){
      selecc5="1";   
    }else{
        selecc5="0";
    }
           if (rep5.isSelected()){
      repo5="1";   
    }else{
        repo5="0";
    }
           /////////////////////////////////////////////////
    if (cest6.isSelected()){
      y="1";   
    }else{
        y="0";
    }       
             if (busq6.isSelected()){
       busqe6="1";   
    }else{
         busqe6="0";
    }
    if (busqa6.isSelected()){
       busqav6="1";   
    }else{
        busqav6="0";
    }  
    if (rep6.isSelected()){
      repo6="1";   
    }else{
        repo6="0";
    }   
    ///////////////////////////////
     if (cest7.isSelected()){
      u="1";   
    }else{
        u="0";
    }       
             if (busq7.isSelected()){
       busqe7="1";   
    }else{
         busqe7="0";
    }
    if (busqa7.isSelected()){
       busqav7="1";   
    }else{
        busqav7="0";
    }  
    if (rep7.isSelected()){
      repo7="1";   
    }else{
        repo7="0";
    }  
     if (resp.isSelected()){
       i="1";   
    }else{
       i="0";
    }  
    if (rest.isSelected()){
     o="1";   
    }else{
       o="0";
    }  
  }
  
    void cargarusuarios() {
       
       
        try{
            String [] titulos={"CEDULA","NOMBRE","APELLIDO","CARGO"};
            String [] registros= new String[4];
            model=new DefaultTableModel(null,titulos);
            
            String cons="select * from datos_usuario";
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
                registros[0]=rs.getString("cedula");
                 registros[1]=rs.getString("nombre");
                  registros[2]=rs.getString("apellido");
                   String n3=rs.getString("rol");
                   
           String    cons2="select * from rol_usuario where idrol= "+ n3;
          Statement     st2= cn.createStatement();
           ResultSet  rs2 = st2.executeQuery(cons2);
            while(rs2.next()){
           
                   registros[3]=rs2.getString("Descripcion");
     
                }
                             
                 
              
                model.addRow(registros);      
                }
          tbusuarios.setModel(model);
          tbusuarios.getColumnModel().getColumn(0).setPreferredWidth(30);
           tbusuarios.getColumnModel().getColumn(1).setPreferredWidth(100);
             tbusuarios.getColumnModel().getColumn(2).setPreferredWidth(100);
             
            }catch(Exception e){
                System.out.println(e.getMessage());
                 }
}
     void cargo(){
       String caN =String.valueOf(boxrol.getSelectedItem());
       if(caN.equals("Nuevo")){
           txtr.setVisible(true);
           jButton1.setVisible(true);
       }
       else{
         txtr.setVisible(false);
           jButton1.setVisible(false);    
       }
       
       
    }   
      void aceptar(){
        String v=txtr.getText();
      PreparedStatement  psd3;
           String SQL3="INSERT INTO rol_usuario(Descripcion) VALUES (?)  ";
try{
     psd3 = cn.prepareStatement(SQL3);
     psd3.setString(1,v);
       
           
          
      
           int n=psd3.executeUpdate();
           
           if(n>0){
        
     boxrol.addItem(v);
       txtr.setVisible(false);
       txtr.setText("");
      jButton1.setVisible(false);
       
           }
          
} catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar cargo");
        
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        chrol = new javax.swing.JCheckBox();
        chsecc = new javax.swing.JCheckBox();
        chprofesor = new javax.swing.JCheckBox();
        est5 = new javax.swing.JCheckBox();
        nuevo1 = new javax.swing.JCheckBox();
        editar1 = new javax.swing.JCheckBox();
        guardar1 = new javax.swing.JCheckBox();
        eliminar1 = new javax.swing.JCheckBox();
        nuevo2 = new javax.swing.JCheckBox();
        guardar2 = new javax.swing.JCheckBox();
        editar2 = new javax.swing.JCheckBox();
        eliminar2 = new javax.swing.JCheckBox();
        nuevo3 = new javax.swing.JCheckBox();
        guardar3 = new javax.swing.JCheckBox();
        editar3 = new javax.swing.JCheckBox();
        eliminar3 = new javax.swing.JCheckBox();
        nuevo4 = new javax.swing.JCheckBox();
        guardar4 = new javax.swing.JCheckBox();
        editar4 = new javax.swing.JCheckBox();
        eliminar4 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        chperso1 = new javax.swing.JCheckBox();
        proc5 = new javax.swing.JCheckBox();
        selec5 = new javax.swing.JCheckBox();
        rep5 = new javax.swing.JCheckBox();
        cest6 = new javax.swing.JCheckBox();
        busq6 = new javax.swing.JCheckBox();
        busqa6 = new javax.swing.JCheckBox();
        rep6 = new javax.swing.JCheckBox();
        rep3 = new javax.swing.JCheckBox();
        cest7 = new javax.swing.JCheckBox();
        busq7 = new javax.swing.JCheckBox();
        busqa7 = new javax.swing.JCheckBox();
        rep7 = new javax.swing.JCheckBox();
        rest = new javax.swing.JCheckBox();
        resp = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbusuarios = new javax.swing.JTable();
        jlestado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        boxrol = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtr = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btel = new javax.swing.JButton();
        bte = new javax.swing.JButton();
        btg = new javax.swing.JButton();
        btn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(null);

        chrol.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chrol.setText("Gestión de rol");
        jPanel2.add(chrol);
        chrol.setBounds(560, 50, 120, 25);

        chsecc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chsecc.setText("Asignación de sección");
        jPanel2.add(chsecc);
        chsecc.setBounds(380, 50, 175, 23);

        chprofesor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chprofesor.setText("Gestión de Profesores");
        jPanel2.add(chprofesor);
        chprofesor.setBounds(190, 50, 177, 25);

        est5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        est5.setText("Estado Nutricional");
        jPanel2.add(est5);
        est5.setBounds(10, 210, 151, 25);

        nuevo1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nuevo1.setText("nuevo");
        jPanel2.add(nuevo1);
        nuevo1.setBounds(10, 80, 90, 20);

        editar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editar1.setText("editar");
        jPanel2.add(editar1);
        editar1.setBounds(10, 120, 90, 25);

        guardar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        guardar1.setText("guardar");
        jPanel2.add(guardar1);
        guardar1.setBounds(10, 100, 90, 25);

        eliminar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar1.setText("eliminar");
        jPanel2.add(eliminar1);
        eliminar1.setBounds(10, 140, 79, 25);

        nuevo2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nuevo2.setText("nuevo");
        jPanel2.add(nuevo2);
        nuevo2.setBounds(190, 80, 69, 20);

        guardar2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        guardar2.setText("guardar");
        jPanel2.add(guardar2);
        guardar2.setBounds(190, 100, 81, 20);

        editar2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editar2.setText("editar");
        jPanel2.add(editar2);
        editar2.setBounds(190, 120, 65, 20);

        eliminar2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar2.setText("eliminar");
        jPanel2.add(eliminar2);
        eliminar2.setBounds(190, 140, 79, 25);

        nuevo3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nuevo3.setText("nuevo");
        jPanel2.add(nuevo3);
        nuevo3.setBounds(380, 80, 69, 20);

        guardar3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        guardar3.setText("guardar");
        jPanel2.add(guardar3);
        guardar3.setBounds(380, 100, 81, 20);

        editar3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editar3.setText("editar");
        jPanel2.add(editar3);
        editar3.setBounds(380, 120, 65, 20);

        eliminar3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar3.setText("eliminar");
        jPanel2.add(eliminar3);
        eliminar3.setBounds(380, 140, 79, 20);

        nuevo4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nuevo4.setText("nuevo");
        jPanel2.add(nuevo4);
        nuevo4.setBounds(560, 80, 80, 25);

        guardar4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        guardar4.setText("guardar");
        jPanel2.add(guardar4);
        guardar4.setBounds(560, 100, 90, 20);

        editar4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        editar4.setText("editar");
        jPanel2.add(editar4);
        editar4.setBounds(560, 120, 80, 20);

        eliminar4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar4.setText("eliminar");
        jPanel2.add(eliminar4);
        eliminar4.setBounds(560, 140, 90, 20);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Window_App_Splitscreen_3Columns.png"))); // NOI18N
        jPanel2.add(jLabel7);
        jLabel7.setBounds(480, -10, 40, 50);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Gear.png"))); // NOI18N
        jLabel4.setText("PROCESOS Y ACTIVIDADES");
        jLabel4.setOpaque(true);
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 0, 670, 32);

        chperso1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        chperso1.setText("Gestión de Personas");
        jPanel2.add(chperso1);
        chperso1.setBounds(10, 50, 170, 25);

        proc5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        proc5.setText("Procesar");
        jPanel2.add(proc5);
        proc5.setBounds(10, 240, 90, 20);

        selec5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        selec5.setText("Selección");
        jPanel2.add(selec5);
        selec5.setBounds(10, 260, 90, 20);

        rep5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rep5.setText("Reporte");
        jPanel2.add(rep5);
        rep5.setBounds(10, 280, 90, 20);

        cest6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cest6.setText("Consulta de Estudiantes");
        jPanel2.add(cest6);
        cest6.setBounds(190, 210, 200, 25);

        busq6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        busq6.setText("Busqueda");
        jPanel2.add(busq6);
        busq6.setBounds(190, 240, 100, 20);

        busqa6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        busqa6.setText("Busqueda Avanzada");
        jPanel2.add(busqa6);
        busqa6.setBounds(190, 260, 180, 20);

        rep6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rep6.setText("Reporte");
        jPanel2.add(rep6);
        rep6.setBounds(190, 280, 90, 20);

        rep3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rep3.setText("reporte");
        jPanel2.add(rep3);
        rep3.setBounds(380, 160, 77, 20);

        cest7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cest7.setText("Auditoria");
        jPanel2.add(cest7);
        cest7.setBounds(400, 210, 120, 25);

        busq7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        busq7.setText("Busqueda");
        jPanel2.add(busq7);
        busq7.setBounds(400, 240, 100, 20);

        busqa7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        busqa7.setText("Busqueda Avanzada");
        jPanel2.add(busqa7);
        busqa7.setBounds(400, 260, 180, 20);

        rep7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rep7.setText("Reporte");
        jPanel2.add(rep7);
        rep7.setBounds(400, 280, 90, 20);

        rest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rest.setText("Restaurar");
        jPanel2.add(rest);
        rest.setBounds(580, 240, 100, 25);

        resp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        resp.setText("Respaldo");
        jPanel2.add(resp);
        resp.setBounds(580, 210, 90, 25);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(219, 246, 690, 320);

        jLabel1.setBackground(new java.awt.Color(51, 102, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(-1,true));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIÒN DE ROL");
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 920, 40);

        jPanel3.setBackground(new java.awt.Color(128, 128, 128));

        tbusuarios.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tbusuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbusuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbusuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbusuarios);

        jlestado.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/User.png"))); // NOI18N
        jLabel3.setText("USUARIOS");
        jLabel3.setOpaque(true);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jlestado, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jlestado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 53, 900, 187);

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setLayout(null);

        boxrol.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        boxrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--" }));
        boxrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxrolActionPerformed(evt);
            }
        });
        jPanel4.add(boxrol);
        boxrol.setBounds(10, 120, 180, 30);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Chart_Bar_3D_Descending.png"))); // NOI18N
        jLabel5.setText("ROL");
        jLabel5.setOpaque(true);
        jPanel4.add(jLabel5);
        jLabel5.setBounds(10, 0, 180, 31);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/1.2.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jPanel4.add(jLabel6);
        jLabel6.setBounds(-10, 210, 210, 110);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);
        jButton1.setBounds(50, 80, 100, 30);

        txtr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel4.add(txtr);
        txtr.setBounds(10, 40, 180, 30);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 246, 199, 320);

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        btel.setBackground(new java.awt.Color(51, 51, 51));
        btel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btel.setForeground(new java.awt.Color(-1,true));
        btel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Delete.png"))); // NOI18N
        btel.setText("ELIMINAR");
        btel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btelActionPerformed(evt);
            }
        });

        bte.setBackground(new java.awt.Color(51, 51, 51));
        bte.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bte.setForeground(new java.awt.Color(-1,true));
        bte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Modify.png"))); // NOI18N
        bte.setText("EDITAR");
        bte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bte.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bteActionPerformed(evt);
            }
        });

        btg.setBackground(new java.awt.Color(51, 51, 51));
        btg.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btg.setForeground(new java.awt.Color(-1,true));
        btg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Save.png"))); // NOI18N
        btg.setText("GUARDAR");
        btg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btg.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgActionPerformed(evt);
            }
        });

        btn.setBackground(new java.awt.Color(51, 51, 51));
        btn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn.setForeground(new java.awt.Color(-1,true));
        btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Add.png"))); // NOI18N
        btn.setText("NUEVO");
        btn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btg, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bte, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btel, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bte)
                    .addComponent(btel)
                    .addComponent(btn)
                    .addComponent(btg))
                .addContainerGap())
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 570, 680, 85);

        jLabel12.setBackground(new java.awt.Color(204, 204, 204));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(-65536,true));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/exits.png"))); // NOI18N
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
        jLabel12.setBounds(810, 600, 100, 70);

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
        jLabel13.setBounds(700, 600, 100, 70);

        jLabel14.setBackground(new java.awt.Color(-13408513,true));
        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel14.setForeground(new java.awt.Color(-1,true));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("MINIMIZAR");
        jLabel14.setOpaque(true);
        jPanel1.add(jLabel14);
        jLabel14.setBounds(700, 570, 100, 19);

        jLabel9.setBackground(new java.awt.Color(-13408513,true));
        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel9.setForeground(new java.awt.Color(-1,true));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("SALIR");
        jLabel9.setOpaque(true);
        jPanel1.add(jLabel9);
        jLabel9.setBounds(810, 570, 100, 19);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgActionPerformed
//nue,gua,eli,edi,nue2,gua2,eli2,edi2,nue3,gua3,eli3,edi3,nue4,gua4,eli4,edi4;
  selecto2();      
 rol=ro3;
Marcado();
 String ca=String.valueOf(boxrol.getSelectedItem());
 

 
  
        String SQL,SQL2,SQL3,SQL4;
        
      

        PreparedStatement psd;
 
      
        switch(Ac){
           
            case 1:

            try{
          if(ca.equals("--Seleccione--")||ca.equals("Ninguno")  ||cedu.isEmpty()){
    JOptionPane.showMessageDialog(null,"Haga una Selección");  
}else{         
      SQL="UPDATE datos_usuario set rol=? WHERE cedula=? ";

                psd = cn.prepareStatement(SQL);
                 psd.setString(1,rol);
                psd.setString(2,cedu);
               
                int f=  psd.executeUpdate();
                
               
          

                SQL2="INSERT INTO proceso (idpro,gesper,gessecc,gespro,gesrol,estn,consultaest,auditoria,respaldo,restaurar)VALUES (?,?,?,?,?,?,?,?,?,?)";
                psd = cn.prepareStatement(SQL2);
                
                  psd.setString(1,idus);
                psd.setString(2,q);
                psd.setString(3,e);
                psd.setString(4,w);
                psd.setString(5,r);
                psd.setString(6,t);
                psd.setString(7,y);
                 psd.setString(8,u);
               psd.setString(9,i);
                 psd.setString(10,o);
                 
          
                 int m= psd.executeUpdate();
                  
                SQL3="INSERT INTO actividad(idact,nuevo,guardar,editar,elimiar,nuevo2,guardar2,editar2,eliminar2,nuevo3,guardar3,editar3,eliminar3,reporte3,nuevo4,guardar4,editar4,eliminar4,procesar5,seleccionar5,reporte5,busqueda6,busquedaav6,reporte6,busqueda7,busquedaav7,reporte7)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                psd = cn.prepareStatement(SQL3);
                 psd.setString(1,idus);
                psd.setString(2,nue);
                psd.setString(3,gua);
                psd.setString(4,edi);
                psd.setString(5,eli);
                
                psd.setString(6,nue2);
                psd.setString(7,gua2);
                psd.setString(8,edi2);
                psd.setString(9,eli2);
                
                psd.setString(10,nue3);
                psd.setString(11,gua3);
                psd.setString(12,edi3);
                psd.setString(13,eli3);
                 psd.setString(14,repo3);
                
                  psd.setString(15,nue4);
                psd.setString(16,gua4);
                psd.setString(17,edi4);
                psd.setString(18,eli4);
                
                  psd.setString(19,proce5);
                psd.setString(20,selecc5);
                psd.setString(21,repo5);
                
                psd.setString(22,busqe6);
                 psd.setString(23,busqav6);
                psd.setString(24,repo6);
                 psd.setString(25,busqe7);
                 psd.setString(26,busqav7);
                psd.setString(27,repo7);
                
              
                 int d= psd.executeUpdate();
                  
                  
                  SQL4="INSERT INTO usu_rol_pro_act(cedula,idrol2,idpro2,idact2)VALUES (?,?,?,?)";
                psd = cn.prepareStatement(SQL4);
                 psd.setString(1,cedu);
                psd.setString(2,rol);
                psd.setString(3,idus);
                psd.setString(4,idus);
                
              
                 int s= psd.executeUpdate();
                  
                  
                   

                if((d>0)){
                    acti="GUARDAR";
                    fpa();
                     limpiar();
                   Bloqueo();
                    cargarusuarios();
                    JOptionPane.showMessageDialog(null,"Guardo con exito");
                }
          }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
            

            break;
            case 2:
            
            try {
 if(ca.equals("--Seleccione--")||ca.equals("Ninguno")  ||cedu.isEmpty()){
    JOptionPane.showMessageDialog(null,"Haga una Selección");  
}else{ 
             
                SQL="UPDATE datos_usuario set rol=? WHERE cedula=? ";

                psd = cn.prepareStatement(SQL);
                 psd.setString(1,rol);
                psd.setString(2,cedu);
               
                int f=  psd.executeUpdate();
                
               
             
                 if((f>0)){
                 //  borrar();
                  //  bloqueo();
                  //  JOptionPane.showMessageDialog(null,"Guardo con exito");

                }
                 SQL2="UPDATE proceso set idpro=?,gesper=?,gessecc=?,gespro=?,gesrol=?,estn=?,consultaest=?,auditoria=?,respaldo=?,restaurar=? WHERE idpro=? ";
                psd = cn.prepareStatement(SQL2);
                
                psd.setString(1,idus);
                psd.setString(2,q);
                psd.setString(3,e);
                psd.setString(4,w);
                psd.setString(5,r);
                 psd.setString(6,t);
                psd.setString(7,y);
                 psd.setString(8,u);
                  psd.setString(9,i);
                 psd.setString(10,o);
                psd.setString(11,idus2);
               
                 
          
                 int m= psd.executeUpdate();
                  
                SQL3="UPDATE actividad set idact=?,nuevo=?,guardar=?,editar=?,elimiar=?,nuevo2=?,guardar2=?,editar2=?,eliminar2=?,nuevo3=?,guardar3=?,editar3=?,eliminar3=?,reporte3=?,nuevo4=?,guardar4=?,editar4=?,eliminar4=?,procesar5=?,seleccionar5=?,reporte5=?,busqueda6=?,busquedaav6=?,reporte6=?,busqueda7=?,busquedaav7=?,reporte7=? where idact=? ";
                psd = cn.prepareStatement(SQL3);
                    psd.setString(1,idus);
                psd.setString(2,nue);
                psd.setString(3,gua);
                psd.setString(4,edi);
                psd.setString(5,eli);
                
                psd.setString(6,nue2);
                psd.setString(7,gua2);
                psd.setString(8,edi2);
                psd.setString(9,eli2);
                
                psd.setString(10,nue3);
                psd.setString(11,gua3);
                psd.setString(12,edi3);
                psd.setString(13,eli3);
                 psd.setString(14,repo3);
                
                  psd.setString(15,nue4);
                psd.setString(16,gua4);
                psd.setString(17,edi4);
                psd.setString(18,eli4);
                
                  psd.setString(19,proce5);
                psd.setString(20,selecc5);
                psd.setString(21,repo5);
                
                psd.setString(22,busqe6);
                 psd.setString(23,busqav6);
                psd.setString(24,repo6);
                  psd.setString(25,busqe7);
                 psd.setString(26,busqav7);
                psd.setString(27,repo7);
               psd.setString(28,idus2);  
               
               
                 int d= psd.executeUpdate();
              
                 SQL4="UPDATE usu_rol_pro_act set cedula=?,idrol2=?,idpro2=?,idact2=? where cedula=? ";
                psd = cn.prepareStatement(SQL4);
                 psd.setString(1,cedu);
                psd.setString(2,rol);
                psd.setString(3,idus);
                psd.setString(4,idus);
                 psd.setString(5,cedu);
              
                 int s= psd.executeUpdate();
                     
                  
                if((d>0)){
                    acti="ACTUALIZAR";
                    fpa();
                    limpiar();
                   Bloqueo();
                    cargarusuarios();
                    JOptionPane.showMessageDialog(null,"Guardo con exito");
                }
            }
            }
            catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }

            break;

        }
     
      
    
}//GEN-LAST:event_btgActionPerformed

private void tbusuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbusuariosMouseClicked
 int i = tbusuarios.getSelectedRow();  
      cedu=(String) tbusuarios.getValueAt(i,0); 
     String rol4="";  
        try{
           
       String    cons2="select * from datos_usuario where cedula= "+ cedu;
          Statement     st2= cn.createStatement();
           ResultSet  rs2 = st2.executeQuery(cons2);
            while(rs2.next()){
           
                   idus=rs2.getString("idusu");
                    idus2=rs2.getString("idusu");
                     rol4=rs2.getString("rol");
     
                }
            Bloqueo();
            limpiar();
            if(Ac==1 && rol4.equals("7")){
              desbloqueo();  
              limpiar();
            }
            
            if(Ac==2 || Ac==3){
             desbloqueo(); 
              limpiar();
                 Marcado2(); 
            }
             if( rol4.equals("7")&& Ac!=1 ){
            
                 Bloqueo();
                  limpiar();
             
            }
           
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }
    
}//GEN-LAST:event_tbusuariosMouseClicked

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
Ac=1; 
limpiar();
jlestado.setText("NUEVO REGISTRO");
File archivo= new File("C:\\Archivos de programa\\Sana\\ImaPeq\\Add.png");
String d=(String.valueOf(archivo));
Image foto= getToolkit().getImage(d);
jlestado.setIcon(new ImageIcon(foto));
    }//GEN-LAST:event_btnActionPerformed

    private void bteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bteActionPerformed
Ac=2;  
limpiar();
desbloqueo();
jlestado.setText("EDITAR REGISTRO");
File archivo= new File("C:\\Archivos de programa\\Sana\\ImaPeq\\Modify.png");
String d=(String.valueOf(archivo));
Image foto= getToolkit().getImage(d);
jlestado.setIcon(new ImageIcon(foto));
    }//GEN-LAST:event_bteActionPerformed

    private void btelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btelActionPerformed
Ac=3;  
limpiar();
Bloqueo();
jlestado.setText("ELIMINAR REGISTRO");
File archivo= new File("C:\\Archivos de programa\\Sana\\ImaPeq\\Delete.png");
String d=(String.valueOf(archivo));
Image foto= getToolkit().getImage(d);
jlestado.setIcon(new ImageIcon(foto));
// TODO add your handling code here:
    }//GEN-LAST:event_btelActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
         dispose();
           // sa = "1";
          //  acti = "SALIDA";
        // new ModPriMenu().setVisible(true);  
        //fpa();   
    }//GEN-LAST:event_jLabel12MouseClicked

private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel13MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
aceptar();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void boxrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxrolActionPerformed
cargo();        // TODO add your handling code here:
    }//GEN-LAST:event_boxrolActionPerformed

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
            java.util.logging.Logger.getLogger(ModGesRol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModGesRol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModGesRol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModGesRol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModGesRol().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox boxrol;
    private javax.swing.JButton bte;
    private javax.swing.JButton btel;
    private javax.swing.JButton btg;
    private javax.swing.JButton btn;
    private javax.swing.JCheckBox busq6;
    private javax.swing.JCheckBox busq7;
    private javax.swing.JCheckBox busqa6;
    private javax.swing.JCheckBox busqa7;
    private javax.swing.JCheckBox cest6;
    private javax.swing.JCheckBox cest7;
    private javax.swing.JCheckBox chperso1;
    private javax.swing.JCheckBox chprofesor;
    private javax.swing.JCheckBox chrol;
    private javax.swing.JCheckBox chsecc;
    private javax.swing.JCheckBox editar1;
    private javax.swing.JCheckBox editar2;
    private javax.swing.JCheckBox editar3;
    private javax.swing.JCheckBox editar4;
    private javax.swing.JCheckBox eliminar1;
    private javax.swing.JCheckBox eliminar2;
    private javax.swing.JCheckBox eliminar3;
    private javax.swing.JCheckBox eliminar4;
    private javax.swing.JCheckBox est5;
    private javax.swing.JCheckBox guardar1;
    private javax.swing.JCheckBox guardar2;
    private javax.swing.JCheckBox guardar3;
    private javax.swing.JCheckBox guardar4;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlestado;
    private javax.swing.JCheckBox nuevo1;
    private javax.swing.JCheckBox nuevo2;
    private javax.swing.JCheckBox nuevo3;
    private javax.swing.JCheckBox nuevo4;
    private javax.swing.JCheckBox proc5;
    private javax.swing.JCheckBox rep3;
    private javax.swing.JCheckBox rep5;
    private javax.swing.JCheckBox rep6;
    private javax.swing.JCheckBox rep7;
    private javax.swing.JCheckBox resp;
    private javax.swing.JCheckBox rest;
    private javax.swing.JCheckBox selec5;
    private javax.swing.JTable tbusuarios;
    private javax.swing.JTextField txtr;
    // End of variables declaration//GEN-END:variables
}
