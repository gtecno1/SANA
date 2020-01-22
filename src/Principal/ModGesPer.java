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
public class ModGesPer extends javax.swing.JFrame {
bdc cc = new bdc();
        Connection cn = cc.Conectar();
         String nue="0",gua="0",eli="0",edi="0",rol1,rol2;
       String j,g,fe3;
        String Ho,Hora,Hora2,Horaf,est,cedu,acti="ninguna",fech,nom,sa="0";  
       String  idr="",ids="",cod;
       int Ac,cor=0,conR, cor11=0;
    /**
     * Creates new form ModGesPer
     */
    public ModGesPer() {
          setUndecorated(true);
        initComponents();
        i();
         setLocationRelativeTo(null);
       this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        bloqueo();
         txtcerepre.setEnabled(false);
           spnest.setEnabled(false);
           jbna1.setEnabled(false);
            txtcerepre.setText("");
            jbna1.setSelectedItem("V");
             
              spnest.setValue(Integer.valueOf("1"));
       Ac();
        selecto2();
          selecto();
          
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
   //void suma()
 // {
  
   //     txtci.setText("");
     //   int numero=0;
       //  numero+=12;
               
       // String numeros=String.valueOf(numero);
        //String agregado="CE-";
       // txtci.setText(agregado+numeros);
     
    
   
 // }
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
       psd4.setString(4,"GESTIÓN DE ESTUDIANTES");
       psd4.setString(5, acti); 
       
       psd4.setString(6, Hora);
       psd4.setString(7,fech);
      psd4.executeUpdate();
     
    }
    catch (SQLException ex)
    {
      JOptionPane.showMessageDialog(null, "Error PROCESO/ACTIVIDAD");
    }
  } void selecto2() {
       
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
            rol1=rsu.getString("rol"); 
                        }
       Statement st2 = (Statement) cn.createStatement();
         String SQL4="SELECT * FROM actividad  WHERE idact LIKE '%" + est + "%' ";
          ResultSet rsu3 =    st2.executeQuery(SQL4);
       
         while (rsu3.next()){     
        
                  nue=rsu3.getString("nuevo"); 
                  gua=rsu3.getString("guardar"); 
                  edi=rsu3.getString("editar"); 
                  eli=rsu3.getString("elimiar"); 
             
                
                  
            if(nue.equals("1")){
            btn.setEnabled(true);
                 }else{
            btn.setEnabled(false);
                 }
           if(gua.equals("1")){
            btg.setEnabled(true);
               }else{
            btg.setEnabled(false);
              }
           if(edi.equals("1")){
            bte.setEnabled(true); 
            }else{
            bte.setEnabled(false);
            }
           
           if(eli.equals("1")){
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
     txtc.setText("");
    txtci.setText("");
    txtno.setText("");
    boxco.setSelectedItem("--Seleccione--");
   fechan.setDate(null);
   spedad.setValue(Integer.valueOf("1"));
   fotol1.setText("FOTO");
   fotol1.setIcon(null); 
    boxsec.setSelectedItem("--Seleccione--");
    txttel.setText("");
    txtfe.setText("");
       rut.setText("No posee");
   nombret.setText("No posee");
   jbna.setSelectedItem("V");
     data2();
    
        
    }
     void bloqueo(){
         btngenerar.setEnabled(false);
          rdf.setEnabled(false);
           spedad.setEnabled(false);
         fotol1.setEnabled(false); 
     fechan.setEnabled(false);     
   rdm.setEnabled(false);
   txtap.setEnabled(false);
     txtc.setEnabled(false);
    txtci.setEnabled(false);
    txtno.setEnabled(false);
    boxco.setEnabled(false);
   
    
    boxsec.setEnabled(false);
    txttel.setEnabled(false);
        año1.setEnabled(false);  
        año2.setEnabled(false);  
          rut.setEnabled(false);
   nombret.setEnabled(false);
   jbna.setEnabled(false);
    }
      void desbloqueo(){
           btngenerar.setEnabled(true);
             rdf.setEnabled(true);
              fotol1.setEnabled(true); 
   rdm.setEnabled(true);
   spedad.setEnabled(true);
    fechan.setEnabled(true);  
   txtap.setEnabled(true);
     txtc.setEnabled(true);
    txtci.setEnabled(true);
    txtno.setEnabled(true);
    boxco.setEnabled(true);
 
    
    boxsec.setEnabled(true);
    txttel.setEnabled(true);
      año1.setEnabled(true);  
        año2.setEnabled(true); 
        rut.setEnabled(true);
   nombret.setEnabled(true);
   jbna.setEnabled(true);
    }
      void selecto() {
       
  
    try
    {
        
    String sc;
      
   
      String SQL = "select * from seccion";
      
     Statement st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      while (rs.next())
      {
       
      boxsec.addItem( rs.getString("Descripcion"));
      }
       SQL = "select * from codigo_persona";
      
    st = this.cn.createStatement();
      rs = st.executeQuery(SQL);
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

  void fe(){
  fe3="";
  Date date=fechan.getDate();
  String Captura=fechan.getDateFormatString();
  SimpleDateFormat sdd=new SimpleDateFormat(Captura);
  String s=sdd.format(date);
  SimpleDateFormat sdf=new SimpleDateFormat("yyy-MM-dd");
  fe3=(sdf.format(date));
  }
        void generarcedula(){
              String Captura="",ced1="";
              
  String codn="";
  Date date=fechan.getDate();
Captura=fechan.getDateFormatString();
  SimpleDateFormat sdd=new SimpleDateFormat(Captura);
  String s=sdd.format(date);
  SimpleDateFormat sdf=new SimpleDateFormat("yy");
  
   codn=(sdf.format(date));
   
   cor11+=1;
             String  codn1=String.valueOf(cor11);
             if(codn.equals("") ||codn1.equals("")){
  JOptionPane.showMessageDialog(this,"Llene todos los campos");   
 }else{
             
         ced1=txtcerepre.getText();
       txtci.setText(ced1=codn1+codn+ced1);
             }
            /*
            String ss="",ss2="",Captura="";
  String codn="",codn1="";
  Date date=fechan.getDate();
Captura=fechan.getDateFormatString();
  SimpleDateFormat sdd=new SimpleDateFormat(Captura);
  String s=sdd.format(date);
  SimpleDateFormat sdf=new SimpleDateFormat("yy");
  
   codn=(sdf.format(date));
   codn1=String.valueOf(spedad.getValue());
 ss=txtno.getText();
  ss2= txtap.getText();
 if(ss.equals("")||ss2.equals("")||codn.equals("") ||codn1.equals("")){
  JOptionPane.showMessageDialog(this,"Llene todos los campos");   
 }else{
  int p=ss.length()+ss2.length();
  
   int resu=Integer.valueOf(codn)+Integer.valueOf(codn1)+p;
    txtci.setText("");
  txtci.setText(String.valueOf( resu));
 }
 * 
 */
  } 
     //   void conCeduRepre(){
      //      conR=Integer.valueOf(String.valueOf(spedad.getValue()));
      //     if(conR>1){
              
        //   }else{
          //    txtcerepre.setText("");
          //7 }
        //}
        void contRepr(){
         conR=Integer.valueOf(String.valueOf(spnest.getValue()));
          if(cor==conR){
           txtcerepre.setEnabled(false);
           spnest.setEnabled(false);
           jbna1.setEnabled(false);
            txtcerepre.setText("");
            jbna1.setSelectedItem("V");
             
              spnest.setValue(Integer.valueOf("1"));
               borrar();
                 bloqueo();
            cor=0;
            cor11=0;
          }else{
              borrar();  
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
        jPanel3 = new javax.swing.JPanel();
        rdm = new javax.swing.JRadioButton();
        txtci = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        año2 = new com.toedter.calendar.JYearChooser();
        año1 = new com.toedter.calendar.JYearChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtno = new javax.swing.JTextField();
        txtap = new javax.swing.JTextField();
        rdf = new javax.swing.JRadioButton();
        boxsec = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txttel = new javax.swing.JTextField();
        boxco = new javax.swing.JComboBox();
        txtc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        fotol1 = new javax.swing.JLabel();
        txtfe = new javax.swing.JLabel();
        spedad = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        rut = new javax.swing.JTextField();
        nombret = new javax.swing.JTextField();
        fechan = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jbna = new javax.swing.JComboBox();
        btngenerar = new javax.swing.JButton();
        txtcerepre = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jbna1 = new javax.swing.JComboBox();
        spnest = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btn = new javax.swing.JButton();
        btg = new javax.swing.JButton();
        bte = new javax.swing.JButton();
        btel = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

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
        rdm.setBounds(350, 270, 100, 30);

        txtci.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(txtci);
        txtci.setBounds(200, 70, 210, 35);

        jPanel8.setBackground(new java.awt.Color(-3355444,true));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("-");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(año1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(año2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(año1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(año2, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel8);
        jPanel8.setBounds(490, 30, 210, 30);

        jLabel14.setBackground(new java.awt.Color(-1,true));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Periodo");
        jLabel14.setOpaque(true);
        jPanel3.add(jLabel14);
        jLabel14.setBounds(490, 0, 210, 20);

        txtno.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(txtno);
        txtno.setBounds(140, 120, 320, 35);

        txtap.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(txtap);
        txtap.setBounds(140, 170, 320, 35);

        rdf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdf.setText("Femenino");
        rdf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rdfMouseClicked(evt);
            }
        });
        jPanel3.add(rdf);
        rdf.setBounds(350, 240, 93, 30);

        boxsec.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        boxsec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--" }));
        jPanel3.add(boxsec);
        boxsec.setBounds(140, 320, 300, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Cedula Repr:");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(10, 30, 120, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Apellido:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(50, 170, 78, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Nombre:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(50, 120, 79, 30);

        jLabel6.setBackground(new java.awt.Color(-6684673,true));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Fecha de nacimiento");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(70, 210, 190, 22);

        jLabel7.setBackground(new java.awt.Color(-6684673,true));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Sección");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(140, 290, 300, 22);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        txttel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        boxco.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        boxco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Seleccione--" }));

        txtc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel8.setBackground(new java.awt.Color(-1,true));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Correo");
        jLabel8.setOpaque(true);

        jLabel10.setBackground(new java.awt.Color(-1,true));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Telefono");
        jLabel10.setOpaque(true);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtc, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(boxco, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(jPanel2);
        jPanel2.setBounds(0, 370, 750, 120);

        jPanel6.setBackground(new java.awt.Color(128, 128, 128));

        fotol1.setBackground(new java.awt.Color(-3355393,true));
        fotol1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fotol1.setText("FOTO");
        fotol1.setOpaque(true);
        fotol1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fotol1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(fotol1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(fotol1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6);
        jPanel6.setBounds(490, 70, 220, 180);
        jPanel3.add(txtfe);
        txtfe.setBounds(678, 337, 60, 30);

        spedad.setFont(new java.awt.Font("Dialog", 1, 14));
        spedad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        jPanel3.add(spedad);
        spedad.setBounds(270, 240, 60, 30);

        jLabel11.setBackground(new java.awt.Color(-6684673,true));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Sexo");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(350, 210, 90, 22);

        rut.setEditable(false);
        rut.setForeground(new java.awt.Color(-13408513,true));
        rut.setBorder(null);
        rut.setOpaque(false);
        jPanel3.add(rut);
        rut.setBounds(550, 290, 0, 14);

        nombret.setEditable(false);
        nombret.setForeground(new java.awt.Color(-13408513,true));
        nombret.setBorder(null);
        nombret.setOpaque(false);
        jPanel3.add(nombret);
        nombret.setBounds(650, 290, 0, 14);

        fechan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel3.add(fechan);
        fechan.setBounds(110, 240, 120, 30);

        jLabel17.setBackground(new java.awt.Color(-6684673,true));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Edad");
        jPanel3.add(jLabel17);
        jLabel17.setBounds(270, 210, 50, 22);

        jbna.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbna.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "E" }));
        jPanel3.add(jbna);
        jbna.setBounds(140, 70, 50, 30);

        btngenerar.setBackground(new java.awt.Color(0, 0, 0));
        btngenerar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/Car key.png"))); // NOI18N
        btngenerar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btngenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarActionPerformed(evt);
            }
        });
        jPanel3.add(btngenerar);
        btngenerar.setBounds(420, 70, 40, 40);

        txtcerepre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel3.add(txtcerepre);
        txtcerepre.setBounds(200, 30, 210, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Cedula:");
        jPanel3.add(jLabel18);
        jLabel18.setBounds(60, 70, 69, 30);

        jbna1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbna1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "V", "E" }));
        jPanel3.add(jbna1);
        jbna1.setBounds(140, 30, 50, 30);

        spnest.setFont(new java.awt.Font("Dialog", 1, 14));
        spnest.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));
        jPanel3.add(spnest);
        spnest.setBounds(420, 30, 50, 30);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Nro Niños");
        jPanel3.add(jLabel19);
        jLabel19.setBounds(410, 7, 70, 20);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(210, 54, 710, 510);

        jPanel4.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIÓN DE ESTUDIANTES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel4);
        jPanel4.setBounds(0, 0, 970, 40);

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
        btn.setBounds(10, 10, 170, 60);

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
        btg.setBounds(10, 80, 170, 60);

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
        bte.setBounds(10, 150, 170, 60);

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
        btel.setBounds(10, 220, 170, 60);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Menu/1.2.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel5.add(jLabel3);
        jLabel3.setBounds(0, 310, 250, 140);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(10, 50, 190, 470);

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
        jLabel12.setBounds(820, 600, 100, 70);

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(-65536,true));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImaPeq/download.png"))); // NOI18N
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel9.setOpaque(true);
        jLabel9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9);
        jLabel9.setBounds(710, 600, 100, 70);

        jLabel15.setBackground(new java.awt.Color(-13408513,true));
        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel15.setForeground(new java.awt.Color(-1,true));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("MINIMIZAR");
        jLabel15.setOpaque(true);
        jPanel1.add(jLabel15);
        jLabel15.setBounds(710, 570, 100, 20);

        jLabel16.setBackground(new java.awt.Color(-13408513,true));
        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14));
        jLabel16.setForeground(new java.awt.Color(-1,true));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("SALIR");
        jLabel16.setOpaque(true);
        jPanel1.add(jLabel16);
        jLabel16.setBounds(820, 570, 100, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 927, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 671, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdmActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
     desbloqueo();
     borrar();
      txtcerepre.setEnabled(true);
           spnest.setEnabled(true);
           jbna1.setEnabled(true);
            txtcerepre.setText("");
            jbna1.setSelectedItem("V");
             
              spnest.setValue(Integer.valueOf("1"));
               cor11=0;
     Ac=1;
    }//GEN-LAST:event_btnActionPerformed

    private void btgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgActionPerformed
String Nac,Nac2,cedula12,rol,secc1,sexo1="0",nombre1,apellido1,edad1="0",telf,telf2,correo1="0",cedula1,codigo1,fe,altu,pes,resu="0",fech, foru="ninguna",fo="ninguna",ceco="";
      
fe();
 cedula12=txtcerepre.getText();
 Nac2=String.valueOf(jbna1.getSelectedItem());
 cedula1= txtci.getText();
 
 
 nombre1=txtno.getText();
 apellido1= txtap.getText();
  edad1 = String.valueOf(spedad.getValue());
 
    
     if(rdf.isSelected()){
         sexo1="Femenino";  
     } 
       if(rdm.isSelected()){
         sexo1="Masculino";   
     }
     correo1=txtc.getText();
       Nac =String.valueOf(jbna.getSelectedItem());
   codigo1 =String.valueOf(boxco.getSelectedItem());
  secc1 =String.valueOf(boxsec.getSelectedItem());
   telf= txttel.getText();
  
   txtfe.setText(fechaActual1());
   fe=txtfe.getText();
   foru=rut.getText();
   fo=nombret.getText();
  fech=txtfe.getText();
  
  
        String SQL,SQL2;
        
        

        
      

        PreparedStatement psd;
 if(cedula1.equals("")||nombre1.equals("")||apellido1.equals("")||sexo1.equals("0")||secc1.equals("--Seleccione--")||codigo1.equals("--Seleccione--")||telf.equals("")||fe3.equals("")){
   
     JOptionPane.showMessageDialog(this,"¡¡Por favor Verifique los Campos!!");
 } else{
    try{
String SQL4;
      
      
    
       
       SQL4 = "select * from seccion where Descripcion LIKE '%" + secc1 + "%'";
      
      Statement st4 = this.cn.createStatement();
      ResultSet rs4 = st4.executeQuery(SQL4);
      while (rs4.next()) {
       ids = rs4.getString("idsec");
           }    
        SQL4 = "select * from codigo_persona where Descripcion LIKE '%" + codigo1 + "%'";
      
       st4 = this.cn.createStatement();
       rs4 = st4.executeQuery(SQL4);
      while (rs4.next()) {
       codigo1 = rs4.getString("idcodigo");
           } 
      }  catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"error");

            }
      
        switch(Ac){
           
            case 1:

            try{
              String  SQL0 = "select * from persona where cedula LIKE '%" + cedula1 + "%'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQL0);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
           } 
        if(ceco.equals(cedula1)){
        JOptionPane.showMessageDialog(this,"Cedula Ya Existe");     
        }else{

                SQL="INSERT INTO persona(Nac,cedula,nombre,apellido,edad,sexo,seccion,codigo,telfono,correo,inicio,termino,rufo,nofo,fechana,fecha,nac2,cedurepr)VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                psd = cn.prepareStatement(SQL);
                psd.setString(1,Nac);
                psd.setString(2,cedula1);
                psd.setString(3,nombre1);
                psd.setString(4,apellido1);
                psd.setString(5,edad1);
                psd.setString(6,sexo1);
                 
                psd.setString(7,ids);
                psd.setString(8,codigo1);
                psd.setString(9,telf);
                psd.setString(10,correo1);
                psd.setString(11,String.valueOf(año1.getYear()));
                psd.setString(12,String.valueOf(año2.getYear()));
                psd.setString(13,foru);
                psd.setString(14,fo);
               psd.setString(15,fe3);
                psd.setString(16,fe);
                 psd.setString(17,Nac2);
                psd.setString(18,cedula12);
                 int m= psd.executeUpdate();
                  
               
                  
                

                if((m>0)){
                    acti="GUARDAR";
                    //lb1.setText(String.valueOf(cor+=1));  
                    fpa();
                  
                   contRepr();
                  
                    JOptionPane.showMessageDialog(null,"Guardo con exito");
                }
        }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"error");
            }
            

            break;
            case 2:
            
            try {

             
                SQL="UPDATE persona set Nac=?,cedula=?,nombre=?,apellido=?,edad=?,sexo=?,seccion=?,codigo=?,telfono=?,correo=?,inicio=?,termino=?,rufo=?,nofo=?,fechana=?,fecha=?,nac2=?,cedurepr=? WHERE cedula=? ";

                psd = cn.prepareStatement(SQL);
                psd.setString(1,Nac);
                psd.setString(2,cedula1);
                psd.setString(3,nombre1);
                psd.setString(4,apellido1);
                psd.setString(5,edad1);
                psd.setString(6,sexo1);
                 
                psd.setString(7,ids);
                psd.setString(8,codigo1);
                psd.setString(9,telf);
                psd.setString(10,correo1);
                psd.setString(11,String.valueOf(año1.getYear()));
                psd.setString(12,String.valueOf(año2.getYear()));
                psd.setString(13,foru);
                psd.setString(14,fo);
               psd.setString(15,fe3);
                psd.setString(16,fe);
                  psd.setString(17,Nac2);
                psd.setString(18,cedula12);
                  psd.setString(19,cod);
                int f=  psd.executeUpdate();
                
               
             
                 if((f>0)){
                      acti="ACTUALIZAR";
                    fpa();
                   borrar();
                   
                    bloqueo();
                     txtcerepre.setEnabled(false);
           spnest.setEnabled(false);
           jbna1.setEnabled(false);
            txtcerepre.setText("");
            jbna1.setSelectedItem("V");
             
              spnest.setValue(Integer.valueOf("1"));
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
Ac=2; String ceco="";
borrar();
  
            txtcerepre.setText("");
            jbna1.setSelectedItem("V");
             
              spnest.setValue(Integer.valueOf("1"));

   
   cod = JOptionPane.showInputDialog("Cedula:");
    if(cod.equals("")){
          JOptionPane.showMessageDialog(this, "Introdusca Cedula");
          bloqueo();
            txtcerepre.setEnabled(false);
           spnest.setEnabled(false);
           jbna1.setEnabled(false);
           
   }else{
    try
    {
          String  SQL0 = "select * from persona where cedula LIKE '%" + cod + "%'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQL0);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
           } 
        if(ceco.equals(cod)){
       
  String SQL1 = "SELECT * FROM persona WHERE cedula=" +cod;
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
               
      desbloqueo();
        txtcerepre.setEnabled(true);
           spnest.setEnabled(true);
           jbna1.setEnabled(true);
            
    jbna.setSelectedItem( rs.getString("Nac"));
      txtci.setText(rs.getString("cedula"));
       jbna1.setSelectedItem( rs.getString("nac2"));
     txtcerepre.setText(rs.getString("cedurepr"));
     txtno.setText(rs.getString("nombre"));
       txtap.setText(rs.getString("apellido"));
 spedad.setValue(Integer.valueOf(rs.getInt("edad")));
 
     String sex=rs.getString("sexo");
     
     if(sex.equals("Femenino")) {
         rdf.isSelected();
         rdf.setSelected(true);
     }else{
         rdm.isSelected();
         rdm.setSelected(true);
     }
      String se= rs.getString("seccion");
   
     String SQL = "select * from seccion where idsec =" + se  ; 
     Statement  st1 = this.cn.createStatement();
      ResultSet rs1 = st1.executeQuery(SQL);
      while (rs1.next())
      {
       
        
      boxsec.setSelectedItem( rs1.getString("Descripcion"));
      }
       txtc.setText(rs.getString("correo"));
         String co1=(rs.getString("codigo"));
         
       SQL = "select * from codigo_persona where idcodigo = " + co1 ;  
    st1 = this.cn.createStatement();
      rs1 = st1.executeQuery(SQL);
      while (rs1.next())
      {   
      boxco.setSelectedItem( rs1.getString("Descripcion"));
      }
      txttel.setText(rs.getString("telfono"));
       año1.setYear(rs.getInt("inicio"));
            año2.setYear(rs.getInt("termino"));
                nombret.setText(rs.getString("nofo"));
    rut.setText(rs.getString("rufo"));
    
    String fu=rut.getText();
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
           fotol1.setIcon(new ImageIcon(foto));
             fotol1.setIcon(new ImageIcon(foto));
            if(fu.equals("")||fu.equals("No posee")){
         fotol1.setIcon(null);
              fotol1.setText("FOTO");
        }else{
                 fotol1.setText("");  
              }
           fechan.setDate(rs.getDate("fechana"));
          
       txtfe.setText(rs.getString("fecha"));
    
        }else{
              JOptionPane.showMessageDialog(this, "Cedula No Existe");
        }
   
    
    }
    catch (SQLException ex) {}
    
    }
}//GEN-LAST:event_bteActionPerformed

private void btelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btelActionPerformed
borrar();
 
            txtcerepre.setText("");
            jbna1.setSelectedItem("V");
             
              spnest.setValue(Integer.valueOf("1"));
String ceco="";

   
   cod = JOptionPane.showInputDialog("Cedula a Eliminar:");
   if(cod.equals("")){
          JOptionPane.showMessageDialog(this, "Introdusca Cedula");
   }else{
    try
    {
          String  SQLr = "select * from persona where cedula LIKE '%" + cod + "%'";
      
     Statement   st41 = this.cn.createStatement();
    ResultSet   rs41 = st41.executeQuery(SQLr);
      while (rs41.next()) {
       ceco = rs41.getString("cedula");
           } 
        if(ceco.equals(cod)){
      String SQL1 = "SELECT * FROM persona WHERE cedula=" +cod;
   Statement  st = this.cn.createStatement();
      ResultSet rs = st.executeQuery(SQL1);
      rs.next();
               
      
      jbna.setSelectedItem( rs.getString("Nac"));
      txtci.setText(rs.getString("cedula"));
       jbna1.setSelectedItem( rs.getString("nac2"));
     txtcerepre.setText(rs.getString("cedurepr"));
     txtno.setText(rs.getString("nombre"));
       txtap.setText(rs.getString("apellido"));
   spedad.setValue(Integer.valueOf(rs.getInt("edad")));
     String sex=rs.getString("sexo");
     
     if(sex.equals("Femenino")) {
         rdf.isSelected();
         rdf.setSelected(true);
     }else{
         rdm.isSelected();
         rdm.setSelected(true);
     }
      String se= rs.getString("seccion");
   
      String SQL = "select * from seccion where idsec =" + se  ; 
       Statement st1 = this.cn.createStatement();
       ResultSet rs1 = st1.executeQuery(SQL);
      while (rs1.next())
      {
       
        
      boxsec.setSelectedItem( rs1.getString("Descripcion"));
      }
       txtc.setText(rs.getString("correo"));
         String co1=(rs.getString("codigo"));
         
       SQL = "select * from codigo_persona where idcodigo = " + co1 ;  
    st1 = this.cn.createStatement();
      rs1 = st1.executeQuery(SQL);
      while (rs1.next())
      {   
      boxco.setSelectedItem( rs1.getString("Descripcion"));
      }
      año1.setYear(rs.getInt("inicio"));
            año2.setYear(rs.getInt("termino"));
      txttel.setText(rs.getString("telfono"));
          nombret.setText(rs.getString("nofo"));
    rut.setText(rs.getString("rufo"));
    String fu=rut.getText();
     File ruta = new File(fu);
          String d=(String.valueOf(ruta));
            Image foto= getToolkit().getImage(d);
             foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
           fotol1.setIcon(new ImageIcon(foto));
            if(fu.equals("")||fu.equals("No posee")){
         fotol1.setIcon(null);
              fotol1.setText("FOTO");
        }else{
                 fotol1.setText("");  
              }
            fechan.setDate(rs.getDate("fechana"));
       txtfe.setText(rs.getString("fecha"));
        
     bloqueo();
       txtcerepre.setEnabled(false);
           spnest.setEnabled(false);
           jbna1.setEnabled(false);
           
     
   
      int resp = JOptionPane.showConfirmDialog(this, "Deseas Eliminarlo", "Eliminar Dato", 0);
      if (resp == 0) {
        try
        {
          PreparedStatement psd = null;
        String  SQL0 = " DELETE FROM persona WHERE cedula ='" + cod + "'";
          psd = this.cn.prepareStatement(SQL0);
          psd.execute();
          psd.close();
           acti="ELIMINAR";
                    fpa();
          borrar();
         bloqueo();
           txtcerepre.setEnabled(false);
           spnest.setEnabled(false);
           jbna1.setEnabled(false);
            txtcerepre.setText("");
            jbna1.setSelectedItem("V");
             
              spnest.setValue(Integer.valueOf("1"));
          JOptionPane.showMessageDialog(this, "REGISTRO ELIMINADO");
        }
        
        catch (SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error SQL no se Eliminaron");
        }
      
      } else {
        borrar();
         bloqueo();
           txtcerepre.setEnabled(false);
           spnest.setEnabled(false);
           jbna1.setEnabled(false);
            txtcerepre.setText("");
            jbna1.setSelectedItem("V");
             
              spnest.setValue(Integer.valueOf("1"));//JOptionPane.showMessageDialog(null, "No existe");
      }
       }else{
              JOptionPane.showMessageDialog(this, "Cedula No Existe");
        }
      }
    catch (SQLException ex) {}
   
   }

}//GEN-LAST:event_btelActionPerformed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
       dispose();
           // sa = "1";
            //acti = "SALIDA";
        // new ModPriMenu().setVisible(true);  
       // fpa();   
    }//GEN-LAST:event_jLabel12MouseClicked

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
           nombret.setText(String.valueOf(rus));
            rut.setText(String.valueOf(file));
            rus = rut.getText();

            //de cierto modo necesitamos tener la imagen para ello debemos conocer la ruta de dicha imagen
            Image foto = getToolkit().getImage(rus);
            //Le damos dimension a nuestro label que tendra la imagen
            foto = foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            //Imprimimos la imagen en el label
            
            fotol1.setIcon(new ImageIcon(foto));
             fotol1.setText("");// TODO add your handling code here:
        }
}//GEN-LAST:event_fotol1MouseClicked

private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
this.setExtendedState(ICONIFIED);// TODO add your handling code here:
}//GEN-LAST:event_jLabel9MouseClicked

    private void btngenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarActionPerformed
 generarcedula();        // TODO add your handling code here:
    }//GEN-LAST:event_btngenerarActionPerformed

        
      

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
            java.util.logging.Logger.getLogger(ModGesPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModGesPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModGesPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModGesPer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModGesPer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JYearChooser año1;
    private com.toedter.calendar.JYearChooser año2;
    private javax.swing.JComboBox boxco;
    private javax.swing.JComboBox boxsec;
    private javax.swing.JButton bte;
    private javax.swing.JButton btel;
    private javax.swing.JButton btg;
    private javax.swing.JButton btn;
    private javax.swing.JButton btngenerar;
    private com.toedter.calendar.JDateChooser fechan;
    private javax.swing.JLabel fotol1;
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
    private javax.swing.JComboBox jbna;
    private javax.swing.JComboBox jbna1;
    private javax.swing.JTextField nombret;
    private javax.swing.JRadioButton rdf;
    private javax.swing.JRadioButton rdm;
    private javax.swing.JTextField rut;
    private javax.swing.JSpinner spedad;
    private javax.swing.JSpinner spnest;
    private javax.swing.JTextField txtap;
    private javax.swing.JTextField txtc;
    private javax.swing.JTextField txtcerepre;
    private javax.swing.JTextField txtci;
    private javax.swing.JLabel txtfe;
    private javax.swing.JTextField txtno;
    private javax.swing.JTextField txttel;
    // End of variables declaration//GEN-END:variables
}
