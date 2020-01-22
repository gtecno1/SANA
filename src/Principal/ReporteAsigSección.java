/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author black
 */
public class ReporteAsigSección {
  
  
  private String id1;
   private String seccion1;
    private String cedula1;
     private String nombre1;
      private String apellido1;
       private String periodo1;
       
   //  private String sexo1;
   
     
 
    public ReporteAsigSección( String id1,String seccion1,String cedula1,String nombre1,String apellido1,String periodo1) {
      
     
      this.id1=id1;
  this.seccion1=seccion1;
    this.cedula1= cedula1;
      this.nombre1= nombre1;
       this.apellido1=apellido1;
        this.periodo1=periodo1;
       
    }

  

   public String getId1() {
        return id1;
    }
    
 public String getSeccion1() {
        return seccion1;
    }
  public String getCedula1() {
        return cedula1;
    }
   public String getNombre1() {
        return nombre1;
    }
    public String getApellido1() {
        return apellido1;
    }
     public String getPeriodo1() {
        return periodo1;
    }
  //  public String getSexo1() {
    //    return sexo1;
    //}

   
    

    

  
}
