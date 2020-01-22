/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author black
 */
public class ReporteAuditoria {
  
  
  private String proceso1;
   private String actividad1;
    private String cedula1;
     private String nombre1;
      private String rol1;
      private String hora1;
       private String fecha1;
       
   //  private String sexo1;
   
     
 
    public ReporteAuditoria( String cedula1,String nombre1,String rol1,String proceso1,String actividad1,String hora1,String fecha1) {
      
     
      this.proceso1=proceso1;
  this.actividad1=actividad1;
    this.cedula1= cedula1;
      this.nombre1= nombre1;
       this.rol1=rol1;
        this.fecha1=fecha1;
         this.hora1=hora1;
       
    }

  

   public String getProceso1() {
        return proceso1;
    }
    
 public String getActividad1() {
        return actividad1;
    }
  public String getCedula1() {
        return cedula1;
    }
   public String getNombre1() {
        return nombre1;
    }
    public String getRol1() {
        return rol1;
    }
     public String getFecha1() {
        return fecha1;
    }
      public String getHora1() {
        return hora1;
    }
  //  public String getSexo1() {
    //    return sexo1;
    //}

   
    

    

  
}
