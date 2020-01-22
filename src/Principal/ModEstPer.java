/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ModEstPer.java
 *
 * Created on 27-nov-2017, 8:45:23
 */
package Principal;

import Segundario.bdc;
import java.util.*;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
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
 * @author back
 */
public class ModEstPer extends javax.swing.JFrame {
    Timer contador;
  int a;
////////////////////////////////////////
String sexo,sec,cedu,cedur="0",n,p,t,se,resulta,nombrr,Apper,Secr;
int edad,altura,peso,cod,altura3,peso3,altura4,peso4,q;
String j,rf;
  String rol2,rol1,Ho,Hora,Hora2,Horaf,est,cedu2,acti="ninguna",fech,nom,sa="0",rufo,nofo,rufo2,nofo2,sel1,pro2,repo3;
/////////////////////////
bdc cc = new bdc();
Connection cn = cc.Conectar();
//////////////////////////////////////
DefaultTableModel model,model2;
//////////////////////////////

////////////////////////////////////////////////////
    /** Creates new form ModEstPer */
    public ModEstPer() {
         setUndecorated(true);
        initComponents();
        repo.setVisible(false);
        i();
         setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
        seleccion_seccion();
        carga_de_personas("");
        carga_de_personas2("");
        Ac();
       selecto2();
     
    }
     void i(){
    Image icon;
    icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/ImaPeq/logo.png"));
    
            
            
    setIconImage(icon);
    setVisible(true);
  }
/////////////////////////////////////////////////
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
      java.sql.PreparedStatement psd4 = this.cn.prepareStatement(SQL4);
       psd4.setString(1, cedu);
       psd4.setString(2,nom);
       psd4.setString(3, rol2);
       psd4.setString(4,"ESTADO NUTRICIONAL");
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
         cedu2=rsu.getString("cedula");  
          nom=rsu.getString("nombre");   
                        }
     
       Statement st2 = (Statement) cn.createStatement();
         String SQL4="SELECT * FROM actividad  WHERE idact LIKE '%" + est + "%' ";
          ResultSet rsu3 =    st2.executeQuery(SQL4);
       
         while (rsu3.next()){     
        
                 
   
                  sel1=rsu3.getString("seleccionar5"); 
                  pro2=rsu3.getString("procesar5"); 
                  repo3=rsu3.getString("reporte5"); 
                  
              
                
                  
            if(sel1.equals("1")){
           boxsec1.setEnabled(true);
                 }else{
           boxsec1.setEnabled(false);
                 }
           if(pro2.equals("1")){
            btpro.setEnabled(true);
               }else{
           btpro.setEnabled(false);
              }
           if(repo3.equals("1")){
            jLabel19.setEnabled(true); 
            jLabel23.setEnabled(true); 
            }else{
                jLabel23.setEnabled(false); 
                 jLabel23.setVisible(false);
              jLabel19.setVisible(false);
           jLabel19.setEnabled(false);
            }
           
          
         }
    
           }catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error al guardar entrada");
        }
    
       } 
     void Guardar12(){
     
String fecha,resultado;
 String ser=String.valueOf(boxsec1.getSelectedItem());
     edad = Integer.valueOf(String.valueOf(edj.getText()));
     peso = Integer.valueOf(String.valueOf(sppe.getValue()));
      altura = Integer.valueOf(String.valueOf(spal.getValue()));
      resultado= resulta;
     lbfe.setText(fechaActual1());
     fecha=lbfe.getText();
    String SQL1;
    PreparedStatement psd;
    String ced1="0";
    try{
       Statement st2 = (Statement) cn.createStatement();
         String SQL4="SELECT * FROM secqper  WHERE cedula LIKE '%" + cedu + "%' ";
          ResultSet rsu3 =    st2.executeQuery(SQL4);
       
         while (rsu3.next()){     
        
                 
   
                ced1=rsu3.getString("cedula"); 
                 
                      
          
         }
     }
      catch (SQLException ex)
      {
        JOptionPane.showMessageDialog(null, "Error Servicio");
      }
           
   
   int Ac=1;
    switch (Ac)
    {
    case 1: 
      
     
        if((edad==0) ){
        
          JOptionPane.showMessageDialog(null, "Por favor Introdusca datos");  
        }
        
        else{
            if(ced1.equals("0")){
      SQL1 = "INSERT INTO secqper (cedula,nombre,apellido,altura,peso,estado,seccion,sexof,edad,fecha) VALUES (?,?,?,?,?,?,?,?,?,?)";
      try
      {
          
        psd =   (PreparedStatement) cn.prepareStatement(SQL1);
        psd.setString(1,cedu);
        psd.setString(2, nombrr);
        psd.setString(3,  Apper);
         psd.setInt(4, altura);
        psd.setInt(5, peso);
        psd.setString(6, resultado);
         psd.setString(7, ser);
        psd.setString(8,  sexo);
        psd.setInt(9,edad);
       psd.setString(10,fecha);
       
        
        
        
        
        
              
               
       int up = psd.executeUpdate();
       
      }
            
      catch (SQLException ex)
      {
        JOptionPane.showMessageDialog(null, "Error Servicio");
      }
      }else{
                 SQL1 = "UPDATE  secqper set cedula=?,nombre=?,apellido=?,altura=?,peso=?,estado=?,seccion=?,sexof=?,edad=?,fecha=? WHERE cedula=? ";
      try
      {
          
        psd =   (PreparedStatement) cn.prepareStatement(SQL1);
        psd.setString(1,cedu);
        psd.setString(2, nombrr);
        psd.setString(3,  Apper);
         psd.setInt(4, altura);
        psd.setInt(5, peso);
        psd.setString(6, resultado);
         psd.setString(7, ser);
        psd.setString(8,  sexo);
        psd.setInt(9,edad);
         psd.setString(10,fecha);
       psd.setString(11,cedu);
       
        
        
        
        
        
              
               
       int up = psd.executeUpdate();
       
      }
            
      catch (SQLException ex)
      {
        JOptionPane.showMessageDialog(null, "Error Servicio");
      } 
            }
    }
        }
    }
    void Guardar(){
     
String fecha,resultado;

     edad = Integer.valueOf(String.valueOf(edj.getText()));
     peso = Integer.valueOf(String.valueOf(sppe.getValue()));
      altura = Integer.valueOf(String.valueOf(spal.getValue()));
      resultado= resulta;
     lbfe.setText(fechaActual1());
     fecha=lbfe.getText();
    String SQL1;
    PreparedStatement psd;
   
   int Ac=1;
    switch (Ac)
    {
    case 1: 
      
     
        if((edad==0) ){
        
          JOptionPane.showMessageDialog(null, "Por favor Introdusca datos");  
        }
        
        else{
      SQL1 = "INSERT INTO estado_altura_peso (cedula,peso,altura,edad,resultado,fecha) VALUES (?,?,?,?,?,?)";
      try
      {
        psd =   (PreparedStatement) cn.prepareStatement(SQL1);
        psd.setString(1,cedu);
        psd.setInt(2, peso);
        psd.setInt(3, altura);
        psd.setInt(4, edad);
        psd.setString(5, resultado);
        psd.setString(6, fecha);
       int up = psd.executeUpdate();
       
      }
      catch (SQLException ex)
      {
        JOptionPane.showMessageDialog(null, "Error Servicio");
      }
    }
        }
    }
  
 
 //////////////////////////////////////////       
        
    void Traer(){
  /*           
  String cod = txtce.getText();
    try
    {
      String SQL1 = "SELECT * FROM estado_altura_peso WHERE cedula=" + cod;
     Statement st = cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
     edad=Integer.valueOf(rs.getString("edad"));
     
      this.boxseg.setSelectedItem(rs.getString("seguro"));
      this.boxmar.setSelectedItem(rs.getString("marca"));
      this.boxmod.setText(rs.getString("modelo"));
      this.boxcol.setSelectedItem(rs.getString("color"));
      this.Spaño.setValue(Integer.valueOf(rs.getInt("año")));
      this.txtpla.setText(rs.getString("placa"));
      this.txtnom.setText(rs.getString("nombre"));
     
      this.AreLugA.setText(rs.getString("Laccidente"));
      this.AreD.setText(rs.getString("Ldestino"));
      this.AreF.setText(rs.getString("Fallas"));
      this.txtC.setText(rs.getString("codigo"));
      this.boxCho.setSelectedItem(rs.getString("chofer"));
      this.txtCosto.setText(String.valueOf(rs.getInt("costo")));
      this.fe2 = rs.getString("fecha");
      this.fe = rs.getString("fecha2");
    }
    catch (SQLException ex) {}
}                                    
**/
 }
    
    void Calculo_de_Desarrollo(){
      
     ///////////////////////////////////////////////////////////
        if(sexo.equals("Masculino")&&(edad==1)){
         //   (altura<=58) && (peso=>7))
      if( ((altura>=60) && (peso<=7))||((altura>=60) && (peso>=20)) ||((altura<=60) && (peso>=20)) ||((altura<=60) && (peso<=7))||((altura<=60) && (peso>=7))||((altura>=60) && (peso<20))){
          
          if((altura<60) && (peso<=7)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=60) && (peso<=7)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<60) && (peso>7)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=60) && (peso>=20)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<60) && (peso>=20)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=60) && (peso<20 && peso>7)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           } 
      } 
      
     
    
            
         //////////////////////////////////////////////////////////////////
             if(sexo.equals("Masculino")&&(edad==2)){
            if(((altura>=70) && (peso<=10))||((altura>=70) && (peso>=20)) ||((altura<=70) && (peso>=20)) || ((altura<=70) && (peso<=10))||((altura<=70) && (peso>=10))||((altura>=70) && (peso<20))){
          
            if((altura<70) && (peso<=10)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=70) && (peso<=10)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta); 
              
           }
         
           if((altura<70) && (peso>10)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=70) && (peso>=20)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<70) && (peso>=20)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=70) && (peso<20 && peso>10)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           } 
              
           } 
          ////////////////////////////////////////////////////////////////// 
           
             if(sexo.equals("Masculino")&&(edad==3)){
              if(((altura>=80) && (peso<=12))||((altura>=80) && (peso>=24)) ||((altura<=80) && (peso>=24)) || ((altura<=80) && (peso<=12))||((altura<=80) && (peso>=12))||((altura>=80) && (peso<24))){
        
         
          if((altura<80) && (peso<=12)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=80) && (peso<=12)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<80) && (peso>12)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=80) && (peso>=24)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<80) && (peso>=24)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=80) && (peso<24 && peso>12)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           
           } 
              
          
               }
              //////////////////////////////////////////////////////////////////
              if(sexo.equals("Masculino")&&(edad==4)){
                  if( ((altura>=90) && (peso<=14))||((altura>=90) && (peso>=24)) ||((altura<=90) && (peso>=24)) || ((altura<=90) && (peso<=14))||((altura<=90) && (peso>=14))||((altura>=90) && (peso<24))){
         
          
              
          if((altura<90) && (peso<=14)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=90) && (peso<=14)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<90) && (peso>14)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=90) && (peso>=24)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<90) && (peso>=24)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=90) && (peso<24 && peso>14)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           } 
              
          
              }
            //////////////////////////////////////////////////////////////////   
                 if(sexo.equals("Masculino")&&(edad==5)){
                  if(((altura>=96) && (peso<=16))||((altura>=96) && (peso>=24)) ||((altura<=96) && (peso>=24)) || ((altura<=96) && (peso<=16))||((altura<=96) && (peso>=16))||((altura>=96) && (peso<24))){
          
                   if((altura<96) && (peso<=16)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=96) && (peso<=16)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<96) && (peso>16)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=96) && (peso>=24)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<96) && (peso>=24)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=96) && (peso<24 && peso>16)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           } 
           
           
              
          
                   }
           //////////////////////////////////////////////////////////////////
                  if(sexo.equals("Masculino")&&(edad==6)){
                    if(  ((altura>=102) && (peso<=18))||((altura>=102) && (peso>=27)) ||((altura<=102) && (peso>=27)) || ((altura<=102) && (peso<=18))||((altura<=102) && (peso>=18))||((altura>=102) && (peso<27))){
        
         
           if((altura<102) && (peso<=18)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=102) && (peso<=18)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<102) && (peso>18)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=102) && (peso>=27)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<102) && (peso>=27)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=102) && (peso<27 && peso>18)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           
           }
                  
            
              
          
                     }
          //////////////////////////////////////////////////////////////////
                   if(sexo.equals("Masculino")&&(edad==7)){
                      if(((altura>=108) && (peso<=20))||((altura>=108) && (peso>=27)) ||((altura<=108) && (peso>=27)) || ((altura<=108) && (peso<=20))||((altura<=108) && (peso>20))||((altura>=108) && (peso<27))){
         
          
                if((altura<108) && (peso<=20)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=108) && (peso<=20)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<108) && (peso>20)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=108) && (peso>=27)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<108) && (peso>=27)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=108) && (peso<27 && peso>20)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           } 
              
          
                       }
        //////////////////////////////////////////////////////////////////
             if(sexo.equals("Masculino")&&(edad==8)){
                        if(((altura>=116) && (peso<=22))||((altura>=116) && (peso>=30)) ||((altura<=116) && (peso>=30)) || ((altura<=116) && (peso<=22))||((altura<=116) && (peso>=22))||((altura>=116) && (peso<30))){
             if((altura<116) && (peso<=22)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=116) && (peso<=22)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<116) && (peso>22)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=116) && (peso>=30)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<116) && (peso>=30)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=116) && (peso<30 && peso>22)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           } 
              
           
                          }
              //////////////////////////////////////////////////////////////////
             if(sexo.equals("Masculino")&&(edad==9)){            
                          if(((altura>=122) && (peso<=24))||((altura>=122) && (peso>=30)) ||((altura<=122) && (peso>=30)) || ((altura<=122) && (peso<=24))||((altura<=122) && (peso>=24))||((altura>=122) && (peso<30))){
          
         if((altura<122) && (peso<=24)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=122) && (peso<=24)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<122) && (peso>24)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=122) && (peso>=30)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<122) && (peso>=30)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=122) && (peso<30 && peso>24)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           } 
              
           
               }
              //////////////////////////////////////////////////////////////////
              if(sexo.equals("Masculino")&&(edad==10)){  
                            if(((altura>=128) && (peso<=26))||((altura>=128) && (peso>=38)) ||((altura<=128) && (peso>=38)) || ((altura<=128) && (peso<=26))||((altura<=128) && (peso>=26))||((altura>=128) && (peso<38))){
         
           
               if((altura<128) && (peso<=26)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=128) && (peso<=26)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<128) && (peso>26)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=128) && (peso>=38)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<128) && (peso>=38)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=128) && (peso<38 && peso>26)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           } 
              
                       
                             }
               //////////////////////////////////////////////////////////////////
               if(sexo.equals("Masculino")&&(edad==11)){  
        if( ((altura>=132) && (peso<=28))||((altura>=132) && (peso>=38)) ||((altura<=132) && (peso>=38)) || ((altura<=132) && (peso<=28))||((altura<=132) && (peso>=28))||((altura>=132) && (peso<38))){
                 if((altura<132) && (peso<=28)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=132) && (peso<=28)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<132) && (peso>28)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=132) && (peso>=38)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<132) && (peso>=38)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=132) && (peso<38 && peso>28)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
         
                     }
                //////////////////////////////////////////////////////////////////
                if(sexo.equals("Masculino")&&(edad==12)){  
  if(((altura>=135) && (peso<=30))||((altura>=135) && (peso>=38)) ||((altura<=135) && (peso>=38)) || ((altura<=135) && (peso<=30))||((altura<=135) && (peso>=30))||((altura>=135) && (peso<38))){
           if((altura<135) && (peso<=30)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=135) && (peso<=30)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<135) && (peso>30)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=135) && (peso>=38)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<135) && (peso>=38)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=135) && (peso<38 && peso>30)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
          
                                 }
                 //////////////////////////////////////////////////////////////////
                   
              if(sexo.equals("Masculino")&&(edad==13)){     
                if(((altura>=138) && (peso<=32))||((altura>=138) && (peso>=42)) ||((altura<=138) && (peso>=42)) || ((altura<=138) && (peso<=32))||((altura<=138) && (peso>=32))||((altura>=138) && (peso<42))){
           if((altura<138) && (peso<=32)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=138) && (peso<=32)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<138) && (peso>32)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=138) && (peso>=42)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<138) && (peso>=42)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=138) && (peso<42 && peso>32)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
           
                }  
               //////////////////////////////////////////////////////////////////
              if(sexo.equals("Masculino")&&(edad==14)){ 
                 if( ((altura>=140) && (peso<=34))||((altura>=140) && (peso>=42)) ||((altura<=140) && (peso>=42)) || ((altura<=140) && (peso<=34))||((altura<=140) && (peso>=34))||((altura>=140) && (peso<42))){
                     
           if((altura<140) && (peso<=34)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=140) && (peso<=34)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<140) && (peso>34)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=140) && (peso>=42)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<140) && (peso>=42)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=140) && (peso<42 && peso>34)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
            }
               //////////////////////////////////////////////////////////////////
               if(sexo.equals("Masculino")&&(edad==15)){ 
                 if(((altura>=146) && (peso<=37))||((altura>=146) && (peso>=48)) ||((altura<=146) && (peso>=48)) || ((altura<=146) && (peso<=37))||((altura<=146) && (peso>=37))||((altura>=146) && (peso<48))){
          
                     if((altura<146) && (peso<=37)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=146) && (peso<=37)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<146) && (peso>37)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=146) && (peso>=48)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<146) && (peso>=48)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=146) && (peso<48 && peso>37)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
          
           }
                //////////////////////////////////////////////////////////////////
               if(sexo.equals("Masculino")&&(edad==16)){ 
      if(((altura>=150) && (peso<=40))||((altura>=150) && (peso>=48)) ||((altura<=150) && (peso>=48)) || ((altura<=150) && (peso<=40))||((altura<=150) && (peso>=40))||((altura>=150) && (peso<48))){
          
               if((altura<150) && (peso<=40)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=150) && (peso<=40)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<150) && (peso>40)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=150) && (peso>=48)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<150) && (peso>=48)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=150) && (peso<48 && peso>40)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
           
              
           
                                         }
                //////////////////////////////////////////////////////////////////
                if(sexo.equals("Masculino")&&(edad==17)){ 
  if(((altura>=156) && (peso<=43))||((altura>=156) && (peso>=60)) ||((altura<=156) && (peso>=60)) || ((altura<=156) && (peso<=43))||((altura<=156) && (peso>=43))||((altura>=156) && (peso<60))){
          // resulta="Riesgo de Deficit"; 
           
               if((altura<156) && (peso<=43)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=156) && (peso<=43)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<156) && (peso>43)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=156) && (peso>=60)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<156) && (peso>=60)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=156) && (peso<60 && peso>43)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
         
                                          }
                 //////////////////////////////////////////////////////////////////
                 if(sexo.equals("Masculino")&&(edad==18)){ 
  if(((altura>=160) && (peso<=48))||((altura>=160) && (peso>=60)) ||((altura<=160) && (peso>=60)) || ((altura<=160) && (peso<=48))||((altura<=160) && (peso>=48))||((altura>=160) && (peso<60))){
        if((altura<160) && (peso<=48)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=160) && (peso<=48)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<160) && (peso>48)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=160) && (peso>=60)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<160) && (peso>=60)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=160) && (peso<60 && peso>48)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
          
                                             }
                  //////////////////////////////////////////////////////////////////
               ////////////////////////////////////////////////////////////////////////////////////////////
                if(sexo.equals("Femenino")&&(edad==1)){
         //   (altura<=58) && (peso=>7))
      if( ((altura>=60) && (peso<=7))||((altura>=60) && (peso>=20)) ||((altura<=60) && (peso>=20)) ||((altura<=60) && (peso<=7))||((altura<=60) && (peso>=7))||((altura>=45) && (peso<20))){
          
          if((altura<60) && (peso<=7)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=60) && (peso<=7)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<60) && (peso>7)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=60) && (peso>=20)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<60) && (peso>=20)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=60) && (peso<20 && peso>7)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           } 
      } 
      
     
    
            
         //////////////////////////////////////////////////////////////////
             if(sexo.equals("Femenino")&&(edad==2)){
            if(((altura>=69) && (peso<=9))||((altura>=69) && (peso>=20)) ||((altura<=69) && (peso>=20)) || ((altura<=69) && (peso<=9))||((altura<=69) && (peso>=9))||((altura>=69) && (peso<20))){
          
            if((altura<69) && (peso<=9)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=69) && (peso<=9)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta); 
              
           }
         
           if((altura<69) && (peso>9)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=69) && (peso>=20)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<69) && (peso>=20)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=69) && (peso<20 && peso>9)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           } 
              
           } 
          ////////////////////////////////////////////////////////////////// 
           
             if(sexo.equals("Femenino")&&(edad==3)){
              if(((altura>=79) && (peso<=11))||((altura>=79) && (peso>=24)) ||((altura<=79) && (peso>=24)) || ((altura<=79) && (peso<=11))||((altura<=79) && (peso>=11))||((altura>=79) && (peso<24))){
        
         
          if((altura<79) && (peso<=11)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=79) && (peso<=11)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<79) && (peso>11)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=79) && (peso>=24)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<79) && (peso>=24)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=79) && (peso<24 && peso>11)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           
           } 
              
          
               }
              //////////////////////////////////////////////////////////////////
              if(sexo.equals("Femenino")&&(edad==4)){
                  if( ((altura>=89) && (peso<=13))||((altura>=89) && (peso>=24)) ||((altura<=89) && (peso>=24)) || ((altura<=89) && (peso<=13))||((altura<=89) && (peso>=13))||((altura>=89) && (peso<24))){
         
          
              
          if((altura<89) && (peso<=13)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=89) && (peso<=13)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<89) && (peso>13)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=89) && (peso>=24)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<89) && (peso>=24)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=89) && (peso<24 && peso>13)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           } 
              
          
              }
            //////////////////////////////////////////////////////////////////   
                 if(sexo.equals("Femenino")&&(edad==5)){
                  if(((altura>=95) && (peso<=15))||((altura>=95) && (peso>=24)) ||((altura<=95) && (peso>=24)) || ((altura<=95) && (peso<=15))||((altura<=95) && (peso>=15))||((altura>=95) && (peso<24))){
          
                   if((altura<95) && (peso<=15)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=95) && (peso<=15)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<95) && (peso>15)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=95) && (peso>=24)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<95) && (peso>=24)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=95) && (peso<24 && peso>15)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           } 
           
           
              
          
                   }
           //////////////////////////////////////////////////////////////////
                  if(sexo.equals("Femenino")&&(edad==6)){
                    if(  ((altura>=101) && (peso<=17))||((altura>=101) && (peso>=27)) ||((altura<=101) && (peso>=27)) || ((altura<=101) && (peso<=17))||((altura<=101) && (peso>=17))||((altura>=101) && (peso<27))){
        
         
           if((altura<101) && (peso<=17)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=101) && (peso<=17)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<101) && (peso>17)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=101) && (peso>=27)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<101) && (peso>=27)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=101) && (peso<27 && peso>17)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           
           }
                  
            
              
          
                     }
          //////////////////////////////////////////////////////////////////
                   if(sexo.equals("Femenino")&&(edad==7)){
                      if(((altura>=107) && (peso<=19))||((altura>=107) && (peso>=27)) ||((altura<=107) && (peso>=27)) || ((altura<=107) && (peso<=19))||((altura<=107) && (peso>19))||((altura>=107) && (peso<27))){
         
          
                if((altura<107) && (peso<=19)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=107) && (peso<=19)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<107) && (peso>19)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=107) && (peso>=27)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<107) && (peso>=27)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=107) && (peso<27 && peso>19)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           } 
              
          
                       }
        //////////////////////////////////////////////////////////////////
             if(sexo.equals("Femenino")&&(edad==8)){
       if(((altura>=115) && (peso<=21))||((altura>=115) && (peso>=30)) ||((altura<=115) && (peso>=30)) || ((altura<=115) && (peso<=21))||((altura<=115) && (peso>=21))||((altura>=115) && (peso<30))){
             if((altura<115) && (peso<=21)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=115) && (peso<=21)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<115) && (peso>21)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=115) && (peso>=30)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<115) && (peso>=30)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=115) && (peso<30 && peso>21)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
         
           } 
              
           
                          }
              //////////////////////////////////////////////////////////////////
             if(sexo.equals("Femenino")&&(edad==9)){            
                          if(((altura>=121) && (peso<=23))||((altura>=121) && (peso>=30)) ||((altura<=121) && (peso>=30)) || ((altura<=121) && (peso<=23))||((altura<=121) && (peso>=23))||((altura>=121) && (peso<30))){
          
         if((altura<121) && (peso<=23)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=121) && (peso<=23)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<121) && (peso>23)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=121) && (peso>=30)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<121) && (peso>=30)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=121) && (peso<30 && peso>23)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           } 
              
           
               }
              //////////////////////////////////////////////////////////////////
              if(sexo.equals("Femenino")&&(edad==10)){  
                            if(((altura>=127) && (peso<=25))||((altura>=127) && (peso>=38)) ||((altura<=127) && (peso>=38)) || ((altura<=127) && (peso<=25))||((altura<=127) && (peso>=25))||((altura>=127) && (peso<38))){
         
           
               if((altura<127) && (peso<=25)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=127) && (peso<=25)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<127) && (peso>25)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=127) && (peso>=38)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<127) && (peso>=38)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=127) && (peso<38 && peso>25)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      }  
           } 
              
                       
                             }
               //////////////////////////////////////////////////////////////////
               if(sexo.equals("Femenino")&&(edad==11)){  
        if( ((altura>=131) && (peso<=27))||((altura>=131) && (peso>=38)) ||((altura<=131) && (peso>=38)) || ((altura<=131) && (peso<=27))||((altura<=131) && (peso>=27))||((altura>=131) && (peso<37))){
                 if((altura<131) && (peso<=27)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=131) && (peso<=27)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<131) && (peso>27)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=131) && (peso>=38)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<131) && (peso>=38)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=131) && (peso<38 && peso>27)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
         
                     }
                //////////////////////////////////////////////////////////////////
                if(sexo.equals("Femenino")&&(edad==12)){  
  if(((altura>=134) && (peso<=29))||((altura>=134) && (peso>=38)) ||((altura<=134) && (peso>=38)) || ((altura<=134) && (peso<=29))||((altura<=134) && (peso>=29))||((altura>=134) && (peso<38))){
           if((altura<134) && (peso<=29)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=134) && (peso<=29)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<134) && (peso>29)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=134) && (peso>=38)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<134) && (peso>=38)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=134) && (peso<38 && peso>29)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
          
                                 }
                 //////////////////////////////////////////////////////////////////
                   
              if(sexo.equals("Femenino")&&(edad==13)){     
                if(((altura>=137) && (peso<=31))||((altura>=137) && (peso>=42)) ||((altura<=137) && (peso>=42)) || ((altura<=137) && (peso<=31))||((altura<=137) && (peso>=31))||((altura>=137) && (peso<42))){
           if((altura<137) && (peso<=31)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=137) && (peso<=31)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<137) && (peso>31)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=137) && (peso>=42)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<137) && (peso>=42)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=137) && (peso<42 && peso>31)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
           
                }  
               //////////////////////////////////////////////////////////////////
              if(sexo.equals("Femenino")&&(edad==14)){ 
                 if( ((altura>=139) && (peso<=33))||((altura>=139) && (peso>=42)) ||((altura<=139) && (peso>=42)) || ((altura<=139) && (peso<=33))||((altura<=139) && (peso>=33))||((altura>=139) && (peso<42))){
                     
           if((altura<139) && (peso<=33)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=139) && (peso<=33)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<139) && (peso>33)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=139) && (peso>=42)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<139) && (peso>=42)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=139) && (peso<42 && peso>39)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
            }
               //////////////////////////////////////////////////////////////////
               if(sexo.equals("Femenino")&&(edad==15)){ 
                 if(((altura>=145) && (peso<=36))||((altura>=145) && (peso>=48)) ||((altura<=145) && (peso>=48)) || ((altura<=145) && (peso<=36))||((altura<=145) && (peso>=36))||((altura>=145) && (peso<48))){
          
                     if((altura<145) && (peso<=36)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=145) && (peso<=36)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<145) && (peso>36)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=145) && (peso>=48)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<145) && (peso>=48)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=145) && (peso<48 && peso>36)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
          
           }
                //////////////////////////////////////////////////////////////////
               if(sexo.equals("Femenino")&&(edad==16)){ 
      if(((altura>=149) && (peso<=39))||((altura>=149) && (peso>=48)) ||((altura<=149) && (peso>=48)) || ((altura<=149) && (peso<=39))||((altura<=149) && (peso>=39))||((altura>=149) && (peso<48))){
          
               if((altura<149) && (peso<=39)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=149) && (peso<=39)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<149) && (peso>39)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=149) && (peso>=48)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<149) && (peso>=48)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=149) && (peso<48 && peso>39)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
           
              
           
                                         }
                //////////////////////////////////////////////////////////////////
                if(sexo.equals("Femenino")&&(edad==17)){ 
  if(((altura>=155) && (peso<=42))||((altura>=155) && (peso>=60)) ||((altura<=155) && (peso>=60)) || ((altura<=155) && (peso<=42))||((altura<=155) && (peso>=42))||((altura>=155) && (peso<60))){
          // resulta="Riesgo de Deficit"; 
           
               if((altura<155) && (peso<=42)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=155) && (peso<=42)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<155) && (peso>42)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=155) && (peso>=60)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<155) && (peso>=60)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=155) && (peso<60 && peso>42)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
         
                                          }
                 //////////////////////////////////////////////////////////////////
                 if(sexo.equals("Femenino")&&(edad==18)){ 
  if(((altura>=159) && (peso<=47))||((altura>=159) && (peso>=60)) ||((altura<=159) && (peso>=60)) || ((altura<=159) && (peso<=47))||((altura<=159) && (peso>=47))||((altura>=159) && (peso<60))){
        if((altura<159) && (peso<=47)){
               Prolb.setText("Altura y  Peso Bajos ");
              resulta="Desarrollo en Deficit";
               lbresu.setText(resulta);  
              
           }
           if((altura>=159) && (peso<=47)){
               Prolb.setText("Altura Normal y Peso Bajo");
              resulta="Riesgo de Deficit";
               lbresu.setText(resulta);  
              
           }
         
           if((altura<159) && (peso>47)){
               Prolb.setText("Altura Baja y Peso Normal ");
                resulta="Riesgo de Deficit";
                lbresu.setText(resulta); 
           }
           if((altura>=159) && (peso>=60)){
               Prolb.setText("Altura Normal y Peso Excesivo");
                resulta="Desarrollo Irregular";
                lbresu.setText(resulta); 
           }
           if((altura<159) && (peso>=60)){
          Prolb.setText("Altura Baja y Peso Excesivo "); 
           resulta="Desarrollo Irregular";
           lbresu.setText(resulta); 
      } 
        
           if((altura>=159) && (peso<60 && peso>47)){
          Prolb.setText("Altura Normal y Peso Normal "); 
            resulta="Desarrollo Normal";
            lbresu.setText(resulta); 
      } 
           } 
              
          
                                             }
       }           
  /////////////////////////////////////////////  
 void seleccion_seccion() {
      
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
       
         }  catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }
     }
  ///////////////////////////////////////  
 void ancla_seccion(){
      
         try{
             
        
             
          t="";   
             
         //////////////////
          String tra= String.valueOf(boxsec1.getSelectedItem());
  
         
    
      String SQL4 = "select * from seccion where Descripcion LIKE '%" + tra + "%'";
      
      Statement st4 = this.cn.createStatement();
      ResultSet rs4 = st4.executeQuery(SQL4);
      while (rs4.next()) {
        
       sec = rs4.getString("idsec");
           } 
     
         
       
       carga_de_personas(sec); 
         carga_de_datos("fgfg");
       carga_de_personas2(tra);
      
      //////////////////////////
       
        SQL4 = "select * from profesor_seccion where idsecc="+sec;  
    Statement  st = this.cn.createStatement();
    ResultSet   rs = st.executeQuery(SQL4);
      while (rs.next())
      {      
      t=rs.getString("cedpro");
      }
       if(t.equals("")){
         lbpro.setText(""); 
      }else{
      ////////////////////////////////////
      SQL4 = "select * from profesor where cedula="+t;
         st = this.cn.createStatement();
      rs = st.executeQuery(SQL4);
      while (rs.next())
      {
          
      n=rs.getString("nombre");
       p=rs.getString("apellido");
       lbpro.setText(n+" "+p);
       
        rufo2=rs.getString("rufo");
       nofo2=rs.getString("nofo");
    String fu=rufo2;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
          foto1.setIcon(new ImageIcon(foto));
      }
      }
    
         }  catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }
            
}
 /////////////////////////////////////////////////////     
 void  carga_de_personas(String valor) {
       
       
        try{
            
           
              if(valor.equals("")){
               String [] titulos={"CEDULA","NOMBRE","APELLIDO","SECCIÒN"};
         
            model=new DefaultTableModel(null,titulos);  
              tbalu.setModel(model);
              }else{
            String [] titulos={"CEDULA","NOMBRE","APELLIDO","SECCIÒN"};
            String [] registros= new String[4];
            model=new DefaultTableModel(null,titulos); 
            
            String cons="select * from persona where seccion LIKE '%" + valor + "%'" ;
          
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
        registros[0]=rs.getString("cedula");                  
      registros[1] = rs.getString("nombre");
       registros[2]=rs.getString("apellido");
       //////////////////
     String t=rs.getString("seccion");
   String SQL = "select * from seccion where idsec LIKE '%" + t + "%'";  
     Statement  st1 = this.cn.createStatement();
     ResultSet rs1 = st1.executeQuery(SQL);
      while (rs1.next()) {
      registros[3] = rs1.getString("Descripcion");
           }
       
       ///////////////////////////////////
          
           
           model.addRow(registros);                
                    
                 } 
        tbalu.setModel(model);
          tbalu.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbalu.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbalu.getColumnModel().getColumn(2).setPreferredWidth(100);
             }
            }catch(Exception e){
                System.out.println(e.getMessage());
                 }
         
}
 /////////////////////////
 void  carga_de_datos(String valor) {
       
       
        try{
           
            String [] titulos={"EDAD","PESO","ALTURA","RESULTADO","FECHA"};
            String [] registros= new String[5];
            model=new DefaultTableModel(null,titulos);
            
            String cons="select * from estado_altura_peso where cedula  LIKE '%" + valor + "%'" ;
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
       registros[0]=rs.getString("edad");        
      registros[1] = rs.getString("peso");
       registros[2]=rs.getString("altura");  
      registros[3] = rs.getString("Resultado");
       registros[4] = rs.getString("fecha");
       
       model.addRow(registros);  
           } 
               
       tbpe.setModel(model);
         
             
            }catch(Exception e){
                System.out.println(e.getMessage());
                 }
        
}
 ////////
 void ancla_Max_eap()
  {
        q=0;
    try
    {
      String SQL2 = "SELECT MAX(idpeal)  FROM estado_altura_peso where cedula="+cedu;
      
      Statement st2 = null;
      st2 = this.cn.createStatement();
      ResultSet rs = st2.executeQuery(SQL2);
      while(rs.next()){ 
     q = rs.getInt("MAX(idpeal)");
     
      }
      
      
         } 
         
    catch (SQLException ex) {}
     }
 //////////
  void peso_altura_adquirido(){
    //  if(q==0) {
     //  peso4=0;
     //altura4=0;
      
     // }else{
      try{
          String cons="select * from estado_altura_peso where idpeal = "+q ;
            Statement st1= cn.createStatement();
            ResultSet rs1 = st1.executeQuery(cons);
         rs1.next(); 
               
      peso3=rs1.getInt("peso");
      altura3=rs1.getInt("altura");
      
      lbp.setText(String.valueOf(peso4=peso-peso3)+" "+"Kg");
     lba.setText(String.valueOf(altura4=altura-altura3)+" "+"Cm");
        
      
       }catch (SQLException ex) {}
           }
     // }
  ////////////////
  public class claseTimer implements ActionListener
  {
   public claseTimer() { 
   }
    
    public void actionPerformed(ActionEvent e)
    {
     
      if(a<=150){
           a+=1;
       if(a<=100){
           jLabel2.setText("Procesando");
           lbresu.setText("");
        File ruta = new File("C:/Archivos de programa/Sana/ImaGra/proc_2.gif");
            String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
            jLabel2.setIcon(new ImageIcon(foto)); 
            
      }
        if(a>140&& a<=150){
            jLabel2.setText("");
             lba.setText("");
             lbp.setText("");
             lba.setIcon(null);
             lbp.setIcon(null);
            Calculo_de_Desarrollo();
             peso_altura_adquirido(); 
            
             Formula_suprema();
                 
        if(sexo.equals("Masculino")&&  resulta.equals("Desarrollo en Deficit")){
            File ruta1 = new File("C:/Archivos de programa/Sana/ImaGra/nino_2.png");
            String c=(String.valueOf(ruta1));
            Image foto1= getToolkit().getImage(c);
            jLabel2.setIcon(new ImageIcon(foto1)); 
            
          
         
         ModEstPer.this.contador.stop(); 
        Guardar();
        Guardar12();
       carga_de_datos(cedu);
          
         } 
          if(sexo.equals("Masculino")&& ((resulta.equals("Desarrollo Normal"))||(resulta.equals("Desarrollo Regular"))||resulta.equals("Riesgo de Deficit")|| resulta.equals("Desarrollo Irregular"))){
            File ruta1 = new File("C:/Archivos de programa/Sana/ImaGra/nino.png");
            String c=(String.valueOf(ruta1));
            Image foto1= getToolkit().getImage(c);
            jLabel2.setIcon(new ImageIcon(foto1)); 
            
          
         
         ModEstPer.this.contador.stop(); 
        Guardar();
         Guardar12();
       carga_de_datos(cedu);
          
         } 
         if(sexo.equals("Femenino")&& resulta.equals("Desarrollo en Deficit")){
            File ruta1 = new File("C:/Archivos de programa/Sana/ImaGra/ninas_2.png");
            String c=(String.valueOf(ruta1));
            Image foto1= getToolkit().getImage(c);
            jLabel2.setIcon(new ImageIcon(foto1)); 
            
          
         
         ModEstPer.this.contador.stop(); 
        Guardar();
         Guardar12();
       carga_de_datos(cedu);
          
         } 
          if(sexo.equals("Femenino")&& ((resulta.equals("Desarrollo Normal"))||(resulta.equals("Desarrollo Regular")) ||resulta.equals("Riesgo de Deficit")|| resulta.equals("Desarrollo Irregular"))){
            File ruta1 = new File("C:/Archivos de programa/Sana/ImaGra/ninas.png");
            String c=(String.valueOf(ruta1));
            Image foto1= getToolkit().getImage(c);
            jLabel2.setIcon(new ImageIcon(foto1)); 
            
          
         
         ModEstPer.this.contador.stop(); 
        Guardar();
         Guardar12();
       carga_de_datos(cedu);
          
         } 
              
      }
            
       
      }
      }
  
    }
 
  
void Formula_suprema(){
   if(resulta.equals("Desarrollo Normal")){
          if(altura4==0&& peso4==0){
 //resulta="Desarrollo Normal";
   //     lbresu.setText("Desarrollo Normal");
  lba.setIcon(null);
       lbp.setIcon(null);   
        
} 
          if(altura4>0 && peso4>0){
//resulta="Desarrollo Normal";
 File ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
             String g=(String.valueOf(ruta2));
           Image foto2= getToolkit().getImage(g);
           lba.setIcon(new ImageIcon(foto2));
           
             File   ruta3 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta3));
         Image    foto3= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto3)); 
   //  lbresu.setText("Desarrollo Normal");
        
}
          if(0>altura4 && 0>peso4){
 //resulta="Riesgo de Deficit";
  File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
           String a=(String.valueOf(ruta2));
          Image foto1= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto1)); 
            
            File ruta3 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
             String g=(String.valueOf(ruta3));
           Image foto2= getToolkit().getImage(g);
           lba.setIcon(new ImageIcon(foto2));
  //   lbresu.setText("Riesgo de Deficit");
        
}
          if(altura4<0&& peso4>0){
//resulta="Desarrollo Regular";//||(altura4>=0)&&(peso4<=0)
 File ruta3 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
             String g=(String.valueOf(ruta3));
           Image foto2= getToolkit().getImage(g);
           lba.setIcon(new ImageIcon(foto2));
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta2));
           foto2= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto2)); 
    // lbresu.setText("Desarrollo Regular");
        
}
           if(altura4>0&&peso4<0){
//resulta="Desarrollo Regular";
 File ruta3 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
             String g=(String.valueOf(ruta3));
           Image foto2= getToolkit().getImage(g);
           lba.setIcon(new ImageIcon(foto2));
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
           String a=(String.valueOf(ruta2));
           foto2= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto2)); 
    // lbresu.setText("Desarrollo Regular");
        
}
            if(altura4==0&&peso4<0){
//resulta="Riesgo de Deficit";
 
           lba.setIcon(null);
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto2)); 
     //lbresu.setText("Riesgo de Deficit");
        
}
             if(altura4==0&&peso4>0){
//resulta="Desarrollo Normal";
 
           lba.setIcon(null);
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto2)); 
  //   lbresu.setText("Desarrollo Normal");
        
}
             ////////////////
                        if(altura4<0&&peso4==0){
//resulta="Riesgo de Deficit";
 
           lbp.setIcon(null);
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
           lba.setIcon(new ImageIcon(foto2)); 
  //   lbresu.setText("Riesgo de Deficit");
        
}
             if(altura4>0&&peso4==0){
//resulta="Desarrollo Normal";
 
           lbp.setIcon(null);
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
           lba.setIcon(new ImageIcon(foto2)); 
     lbresu.setText("Desarrollo Normal");
        
}
   }
    if((resulta.equals("Riesgo de Deficit")|| resulta.equals("Desarrollo Irregular")|| resulta.equals("Desarrollo en Deficit"))){
      if(altura4==0&&peso4==0){
 //resulta="Riesgo de Deficit";
   //  lbresu.setText("Riesgo de Deficit");
      lba.setIcon(null);
       lbp.setIcon(null);   
}  
      if(altura4>0 && peso4>0){
//resulta="Desarrollo Normal";
 File ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
             String g=(String.valueOf(ruta2));
           Image foto2= getToolkit().getImage(g);
           lba.setIcon(new ImageIcon(foto2));
           
             File   ruta3 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta3));
         Image    foto3= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto3)); 
  //   lbresu.setText("Desarrollo Normal");
        
}
      if(0>altura4 && 0>peso4){
// resulta="Riesgo de Deficit";
  File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
           String a=(String.valueOf(ruta2));
          Image foto1= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto1)); 
            
            File ruta3 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
             String g=(String.valueOf(ruta3));
           Image foto2= getToolkit().getImage(g);
           lba.setIcon(new ImageIcon(foto2));
  //   lbresu.setText("Riesgo de Deficit");
        
}
      if(altura4<0 && peso4>0){
 //resulta="Riesgo de Deficit";
  File ruta3 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
             String g=(String.valueOf(ruta3));
           Image foto2= getToolkit().getImage(g);
           lba.setIcon(new ImageIcon(foto2));
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta2));
           foto2= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto2));
   //   lbresu.setText("Riesgo de Deficit");
        
}
      if(altura4>0 && peso4<0){
 //resulta="Riesgo de Deficit";
  File ruta3 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
             String g=(String.valueOf(ruta3));
           Image foto2= getToolkit().getImage(g);
           lba.setIcon(new ImageIcon(foto2));
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta2));
           foto2= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto2));
   //   lbresu.setText("Riesgo de Deficit");
        
}
      
      //////////////////////////
       if(altura4==0&&peso4<0){
//resulta="Riesgo de Deficit";
 
           lba.setIcon(null);
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto2)); 
  //   lbresu.setText("Riesgo de Deficit");
        
}
             if(altura4==0&&peso4>0){
//resulta="Riesgo de Deficit";
 
           lba.setIcon(null);
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
           lbp.setIcon(new ImageIcon(foto2)); 
  //   lbresu.setText("Riesgo de Deficit");
        
}
             ////////////////
                        if(altura4<0&&peso4==0){
//resulta="Riesgo de Deficit";
 
           lbp.setIcon(null);
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/bajo.png");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
           lba.setIcon(new ImageIcon(foto2)); 
  //   lbresu.setText("Riesgo de Deficit");
        
}
             if(altura4>0&&peso4==0){
//resulta="Riesgo de Deficit";
 
           lbp.setIcon(null);
             File   ruta2 = new File("C:/Archivos de programa/Sana/ImaPeq/subida.png");
           String a=(String.valueOf(ruta2));
          Image foto2= getToolkit().getImage(a);
           lba.setIcon(new ImageIcon(foto2)); 
  //   lbresu.setText("Riesgo de Deficit");
        
}
    }
  }
      void  carga_de_personas2(String valor) {
       
       
        try{
            
           
            
             
          String [] titulos={"CEDULA","NOMBRE","APELLIDO","SEXO","EDAD","ALTURA","PESO","ESTADO","SECCION","FECHA"};
            String [] registros= new String[10];
           model2=new DefaultTableModel(null,titulos); 
            
            String cons="select * from secqper where seccion LIKE '%" + valor + "%'" ;
          
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
        registros[0]=rs.getString("cedula");                  
      registros[1] = rs.getString("nombre");
       registros[2]=rs.getString("apellido");
       registros[3]=rs.getString("sexof");                  
      registros[4] = rs.getString("edad");
       registros[5]=rs.getString("altura");
        registros[6]=rs.getString("peso");                  
      registros[7] = rs.getString("estado");
      
       registros[8]=rs.getString("seccion");
         registros[9]=rs.getString("fecha");
       
       //////////////////
  
       ///////////////////////////////////
          
           
          model2.addRow(registros);                
                    
                 } 
        repo.setModel(model2);
          repo.getColumnModel().getColumn(0).setPreferredWidth(30);
            repo.getColumnModel().getColumn(1).setPreferredWidth(100);
           repo.getColumnModel().getColumn(2).setPreferredWidth(100);
             
            }catch(Exception e){
                System.out.println(e.getMessage());
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
        Prolb = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        repo = new javax.swing.JTable();
        lbresu = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbp = new javax.swing.JLabel();
        lba = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        sppe = new javax.swing.JSpinner();
        spal = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btpro = new javax.swing.JButton();
        edj = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbalu = new javax.swing.JTable();
        boxsec1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        lbpro = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbpe = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        foto2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        foto1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbfe = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(-1,true));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(-1,true));
        jPanel2.setLayout(null);

        Prolb.setBackground(new java.awt.Color(-13408513,true));
        Prolb.setFont(new java.awt.Font("Dialog", 1, 12));
        Prolb.setForeground(new java.awt.Color(-1,true));
        Prolb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Prolb.setText("DETALLES");
        Prolb.setOpaque(true);
        jPanel2.add(Prolb);
        Prolb.setBounds(290, 280, 200, 30);

        jLabel10.setBackground(new java.awt.Color(-13408513,true));
        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel10.setForeground(new java.awt.Color(-1,true));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("ESTADO");
        jLabel10.setOpaque(true);
        jPanel2.add(jLabel10);
        jLabel10.setBounds(0, 0, 490, 20);

        repo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        repo.setOpaque(false);
        jPanel2.add(repo);
        repo.setBounds(0, 0, 0, 0);

        lbresu.setBackground(new java.awt.Color(-13408513,true));
        lbresu.setFont(new java.awt.Font("Dialog", 1, 18));
        lbresu.setForeground(new java.awt.Color(-1,true));
        lbresu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbresu.setText("RESULTADO");
        lbresu.setOpaque(true);
        jPanel2.add(lbresu);
        lbresu.setBounds(0, 280, 200, 30);

        jLabel11.setBackground(new java.awt.Color(-13408513,true));
        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel11.setForeground(new java.awt.Color(-1,true));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("PROGRESO");
        jLabel11.setOpaque(true);
        jPanel2.add(jLabel11);
        jLabel11.setBounds(0, 120, 130, 30);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel2.setForeground(new java.awt.Color(-16711681,true));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/proc_1.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        jPanel2.add(jLabel2);
        jLabel2.setBounds(0, 0, 490, 320);

        lbp.setFont(new java.awt.Font("Dialog", 1, 18));
        jPanel2.add(lbp);
        lbp.setBounds(10, 70, 110, 50);

        lba.setFont(new java.awt.Font("Dialog", 1, 18));
        jPanel2.add(lba);
        lba.setBounds(10, 150, 110, 60);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(540, 50, 490, 320);

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
        jLabel8.setBounds(810, 550, 100, 70);

        jLabel16.setBackground(new java.awt.Color(-13408513,true));
        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel16.setForeground(new java.awt.Color(-1,true));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("MINIMIZAR");
        jLabel16.setOpaque(true);
        jPanel1.add(jLabel16);
        jLabel16.setBounds(810, 520, 100, 19);

        jLabel17.setBackground(new java.awt.Color(-13408513,true));
        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel17.setForeground(new java.awt.Color(-1,true));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("SALIR");
        jLabel17.setOpaque(true);
        jPanel1.add(jLabel17);
        jLabel17.setBounds(920, 520, 100, 19);

        jPanel3.setBackground(new java.awt.Color(-3355444,true));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("DATOS ACTUALES"));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(-1,true));
        jLabel12.setText(" Cm.");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(-1,true));
        jLabel15.setText(" Kg.");

        sppe.setFont(new java.awt.Font("Dialog", 1, 14));
        sppe.setModel(new javax.swing.SpinnerNumberModel(0, 0, 300, 1));

        spal.setFont(new java.awt.Font("Dialog", 1, 14));
        spal.setModel(new javax.swing.SpinnerNumberModel(0, 0, 300, 1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(-1,true));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Edad");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(-1,true));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Altura");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(-1,true));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Peso");

        btpro.setBackground(new java.awt.Color(51, 51, 51));
        btpro.setFont(new java.awt.Font("Dialog", 1, 14));
        btpro.setForeground(new java.awt.Color(-1,true));
        btpro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Chart_Pie.png"))); // NOI18N
        btpro.setText("Procesar");
        btpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btproActionPerformed(evt);
            }
        });

        edj.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        edj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btpro)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(edj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(sppe, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel12)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sppe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edj, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btpro, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(550, 380, 470, 114);

        jPanel4.setBackground(new java.awt.Color(-3355444,true));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "INFORMACIÒN DE ESTUDIANTES"));
        jPanel4.setLayout(null);

        tbalu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbalu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbaluMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbalu);

        jPanel4.add(jScrollPane1);
        jScrollPane1.setBounds(6, 124, 510, 170);

        boxsec1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        boxsec1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--" }));
        boxsec1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxsec1ActionPerformed(evt);
            }
        });
        jPanel4.add(boxsec1);
        boxsec1.setBounds(16, 60, 200, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(-1,true));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Secciòn");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(21, 27, 190, 22);

        lbpro.setFont(new java.awt.Font("Dialog", 1, 14));
        lbpro.setForeground(new java.awt.Color(-1,true));
        lbpro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbpro.setText("pro");
        jPanel4.add(lbpro);
        lbpro.setBounds(240, 100, 170, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(-1,true));
        jLabel4.setText("Profesor Encargado");
        jPanel4.add(jLabel4);
        jLabel4.setBounds(260, 10, 137, 30);

        jPanel6.setBackground(new java.awt.Color(-8355712,true));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Seguimiento de Estudiante"));

        tbpe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbpe);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel6);
        jPanel6.setBounds(6, 300, 435, 150);

        jPanel7.setBackground(new java.awt.Color(-8355712,true));

        foto2.setBackground(new java.awt.Color(128, 128, 128));
        foto2.setFont(new java.awt.Font("Dialog", 1, 18));
        foto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/Usuario1.png"))); // NOI18N
        foto2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foto2, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foto2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel7);
        jPanel7.setBounds(440, 300, 80, 150);

        jPanel8.setBackground(new java.awt.Color(-8355712,true));

        foto1.setFont(new java.awt.Font("Dialog", 1, 12));
        foto1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/Usuario1.png"))); // NOI18N
        foto1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        foto1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(foto1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foto1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel8);
        jPanel8.setBounds(260, 40, 260, 60);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 50, 520, 460);

        jPanel5.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ESTADO NUTRICIONAL");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1030, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5);
        jPanel5.setBounds(0, 0, 1030, 40);

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/exits.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.setOpaque(true);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9);
        jLabel9.setBounds(920, 550, 100, 70);

        lbfe.setForeground(new java.awt.Color(-986896,true));
        jPanel1.add(lbfe);
        lbfe.setBounds(975, 525, 0, 0);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.2.png"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(720, 530, 370, 160);

        jLabel22.setBackground(new java.awt.Color(-13408513,true));
        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel22.setForeground(new java.awt.Color(-1,true));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("REPORTE GENERAL");
        jLabel22.setOpaque(true);
        jPanel1.add(jLabel22);
        jLabel22.setBounds(130, 520, 150, 19);

        jLabel23.setBackground(new java.awt.Color(204, 204, 204));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(-65536,true));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/print.png"))); // NOI18N
        jLabel23.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel23.setOpaque(true);
        jLabel23.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel23);
        jLabel23.setBounds(150, 550, 100, 70);

        jLabel18.setBackground(new java.awt.Color(-13408513,true));
        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel18.setForeground(new java.awt.Color(-1,true));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("REPORTE");
        jLabel18.setOpaque(true);
        jPanel1.add(jLabel18);
        jLabel18.setBounds(10, 520, 100, 19);

        jLabel19.setBackground(new java.awt.Color(204, 204, 204));
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(-65536,true));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaGra/print.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel19.setOpaque(true);
        jLabel19.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel19);
        jLabel19.setBounds(10, 550, 100, 70);

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.2.png"))); // NOI18N
        jLabel20.setText("jLabel7");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(400, 480, 360, 250);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.2.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(180, 480, 350, 250);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.2.png"))); // NOI18N
        jLabel21.setText("jLabel7");
        jPanel1.add(jLabel21);
        jLabel21.setBounds(0, 470, 350, 250);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1032, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btproActionPerformed
  edad = Integer.valueOf(edj.getText());
        altura = Integer.valueOf(String.valueOf(spal.getValue()));
         peso = Integer.valueOf(String.valueOf(sppe.getValue()));
        File ruta1 = new File("C:/Archivos de programa/Sana/ImaGra/proc_1.png");
            String c=(String.valueOf(ruta1));
            Image foto1= getToolkit().getImage(c);
            jLabel2.setIcon(new ImageIcon(foto1));
            lbp.setText("");
             lba.setText("");
             lbp.setIcon(null);
             lba.setIcon(null);
            
         int resp = JOptionPane.showConfirmDialog(this, "¿Sus datos son correctos?", "Procesar Dato", 0);
         
      if (resp == 0) {
       
   
    ancla_Max_eap(); 
    
  a=0;
   
    contador = new Timer(50, new claseTimer());
    contador.start();
   
       } 
         else {
       
      }
         ///////////////////////
     

///////////////////////
   
    

}//GEN-LAST:event_btproActionPerformed

private void boxsec1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxsec1ActionPerformed
 se=String.valueOf(boxsec1.getSelectedItem());
//////////////////////////
     edj.setText(""); 
   if(se.equals("--Seleccione--")){
       
      lbpro.setText(""); 
      edj.setText(""); 
          File    ruta = new File("C://Archivos de programa//Sana//ImaGra//Usuario1.png");
         String  d=(String.valueOf(ruta));
         Image  foto= getToolkit().getImage(d);
             foto1.setIcon(new ImageIcon(foto));
             
             
             
       }
    
   ancla_seccion();
     //carga_de_personas(sec);
     //carga_de_datos("");
   
  
   
    
 
    ///////////////////////////////////
}//GEN-LAST:event_boxsec1ActionPerformed

private void tbaluMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbaluMouseClicked
int edad2=0;
rufo="";
sppe.setValue(0);
spal.setValue(0);
lbresu.setText("RESULTADO");
Prolb.setText("DETALLES");
lbp.setIcon(null);
lbp.setText("");
lba.setIcon(null);
lba.setText("");
 File ruta1 = new File("C:/Archivos de programa/Sana/ImaGra/proc_1.png");
            String c=(String.valueOf(ruta1));
            Image foto1= getToolkit().getImage(c);
            jLabel2.setIcon(new ImageIcon(foto1));
    int i = tbalu.getSelectedRow();  
     cedur= cedu=(String) tbalu.getValueAt(i,0); 
      carga_de_datos(cedu);
      /////////////////////////////
      try{
       String cons="select * from persona where cedula LIKE '%" + cedu + "%'" ;
            Statement st= cn.createStatement();
            ResultSet rs = st.executeQuery(cons);
            while(rs.next()){
               sexo=rs.getString("sexo");
                edad=rs.getInt("edad");
               nombrr=rs.getString("nombre");
               Apper=rs.getString("apellido");
              
               
               edj.setText( String.valueOf(Integer.valueOf(edad)));
                   rufo=rs.getString("rufo");
   nofo=rs.getString("nofo");
    String fu=rufo;
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
          foto2.setIcon(new ImageIcon(foto));
              if(rufo.equals("")||rufo.equals("No posee")){
               ruta = new File("C://Archivos de programa//Sana//ImaGra//Usuario1.png");
            d=(String.valueOf(ruta));
             foto= getToolkit().getImage(d);
             foto2.setIcon(new ImageIcon(foto));
             
        }
                String con="select * from estado_altura_peso where cedula LIKE '%" + cedu + "%'" ;
            Statement st1= cn.createStatement();
            ResultSet rs1 = st1.executeQuery(con);
            while(rs1.next()){
             
                edad2=rs1.getInt("edad");
               edj.setText( String.valueOf(Integer.valueOf(edad2)));
               
               if(edad2>=edad && edad2!=0 ){
                edj.setText( String.valueOf(Integer.valueOf(edad2)));
               } else{
                  edj.setText( String.valueOf(Integer.valueOf(edad)));
               }   
               }
            
            
            }
               
             }catch(Exception e){
                System.out.println(e.getMessage());
                 }
      ////////////////////////////////
                 
      
}//GEN-LAST:event_tbaluMouseClicked

private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
//File ruta = new File("C:/Archivos de programa/Sana/ImaGra/pro_nu2.gif");
  //          String d=(String.valueOf(ruta));
    //        Image foto= getToolkit().getImage(d);
      //      jLabel2.setIcon(new ImageIcon(foto));
}//GEN-LAST:event_jLabel2MouseClicked

private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
//File ruta = new File("C:/Archivos de programa/Sana/ImaGra/pro_nu2.gif");
  //          String d=(String.valueOf(ruta));
    //        Image foto= getToolkit().getImage(d);
      //      jLabel2.setIcon(new ImageIcon(foto));// TODO add your handling code here:
}//GEN-LAST:event_jLabel2MousePressed

private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
/*File ruta = new File("C:/Archivos de programa/Sana/ImaGra/ninas.png");
            String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
            jLabel2.setIcon(new ImageIcon(foto));
     * 
     */
}//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        dispose();
       // sa = "1";
        //acti = "SALIDA";
        //new ModPriMenu().setVisible(true);
        //fpa();        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel8MouseClicked

private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
 if(cedur.equals("0")){
       JOptionPane.showMessageDialog(null, "Por favor seleccione estudiante!!"); 
       } else{
List lista= new ArrayList();
for(int i=0;i<tbpe.getRowCount();i++){    
  ReporteEstadoEstudiantes1 Estudiantes= new ReporteEstadoEstudiantes1   (tbpe.getValueAt(i,0).toString(),tbpe.getValueAt(i,1).toString(),tbpe.getValueAt(i,2).toString(),tbpe.getValueAt(i,3).toString(),tbpe.getValueAt(i,4).toString());  
lista.add(Estudiantes);
}
String ser=String.valueOf(boxsec1.getSelectedItem());
 if(sexo.equals("Masculino")){
 
        try{
            
             String fileJasper = "C:\\Archivos de programa\\Sana\\sana\\reportM.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(fileJasper);
            Map parametro=new HashMap();
            parametro.put("cedula1",cedu);
             parametro.put("seccr1",ser);
              parametro.put("nombr1",nombrr);
               parametro.put("apellr",Apper);
             parametro.put("sexo1",sexo);
              parametro.put("edad1",edad);
            JasperPrint Jprint= JasperFillManager.fillReport(reporte,parametro,new JRBeanCollectionDataSource(lista));
           
            JasperViewer.viewReport(Jprint,false);
            
        } catch (JRException ex) {
            Logger.getLogger(ModEstPer.class.getName()).log(Level.SEVERE, null, ex);
        }
 }else{
   try{
            
             String fileJasper = "C:\\Archivos de programa\\Sana\\sana\\reportF.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(fileJasper);
            Map parametro=new HashMap();
           parametro.put("cedula1",cedu);
             parametro.put("seccr1",ser);
              parametro.put("nombr1",nombrr);
               parametro.put("apellr",Apper);
             parametro.put("sexo1",sexo);
              parametro.put("edad1",edad);
            JasperPrint Jprint= JasperFillManager.fillReport(reporte,parametro,new JRBeanCollectionDataSource(lista));
           
            JasperViewer.viewReport(Jprint,false);
            
        } catch (JRException ex) {
            Logger.getLogger(ModEstPer.class.getName()).log(Level.SEVERE, null, ex);
        }   
 }
  }// TODO add your handling code here:
}//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
 String tra="";
     tra= String.valueOf(boxsec1.getSelectedItem());
    carga_de_personas2(tra);
     String ser=String.valueOf(boxsec1.getSelectedItem());
      
List lista= new ArrayList();
for(int i=0;i<repo.getRowCount();i++){    
 reporgeneral Estudiantes= new reporgeneral  (repo.getValueAt(i,0).toString(),repo.getValueAt(i,1).toString(),repo.getValueAt(i,2).toString(),repo.getValueAt(i,3).toString(),repo.getValueAt(i,4).toString(),repo.getValueAt(i,5).toString(),repo.getValueAt(i,6).toString(),repo.getValueAt(i,7).toString(),repo.getValueAt(i,8).toString(),repo.getValueAt(i,9).toString());  
lista.add(Estudiantes);
}

 
 
        try{
            
             String fileJasper = "C:\\Archivos de programa\\Sana\\sana\\reportMG.jrxml";
            JasperReport reporte = JasperCompileManager.compileReport(fileJasper);
            Map parametro=new HashMap();
           // parametro.put("cedula1",cedu);
             parametro.put("seccr1",ser);
             // parametro.put("nombr1",nombrr);
              // parametro.put("apellr",Apper);
            // parametro.put("sexo1",sexo);
             // parametro.put("edad1",edad);
            JasperPrint Jprint= JasperFillManager.fillReport(reporte,parametro,new JRBeanCollectionDataSource(lista));
           
            JasperViewer.viewReport(Jprint,false);
            
        } catch (JRException ex) {
            Logger.getLogger(ModEstPer.class.getName()).log(Level.SEVERE, null, ex);
        
        }
          // TODO add your handling code here:
    }//GEN-LAST:event_jLabel23MouseClicked

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
            java.util.logging.Logger.getLogger(ModEstPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModEstPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModEstPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModEstPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ModEstPer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Prolb;
    private javax.swing.JComboBox boxsec1;
    private javax.swing.JButton btpro;
    private javax.swing.JLabel edj;
    private javax.swing.JLabel foto1;
    private javax.swing.JLabel foto2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lba;
    private javax.swing.JLabel lbfe;
    private javax.swing.JLabel lbp;
    private javax.swing.JLabel lbpro;
    private javax.swing.JLabel lbresu;
    private javax.swing.JTable repo;
    private javax.swing.JSpinner spal;
    private javax.swing.JSpinner sppe;
    private javax.swing.JTable tbalu;
    private javax.swing.JTable tbpe;
    // End of variables declaration//GEN-END:variables
}
