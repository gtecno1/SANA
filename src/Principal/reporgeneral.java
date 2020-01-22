/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author black
 */
public class reporgeneral {
  
    private String cedula1;
    private String nombre1;
    private String apellido1;
    private String edad1;
    private String sexo1;
     private String seccion1;
     
   private String altura1;
   private String peso1;
      private String resultado1;
       private String fecha1;
     // private String fecha1;
    public reporgeneral(String cedula1,String nombre1,String apellido1,String sexo1,String edad1 ,String altura1 ,String peso1,String resultado1,String seccion1,String fecha1) {
      this.cedula1 = cedula1;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.edad1 = edad1;
       this.sexo1 = sexo1;
        this.seccion1 = seccion1;
        this.peso1 = peso1;
         this.altura1 = altura1;
        this.resultado1 = resultado1;
        this.fecha1=fecha1;
      //  this.fecha1 = fecha1;
    }

    public String getApellido1() {
        return apellido1;
    }
public String getCedula1() {
        return cedula1;
    }
 

    public String getEdad1() {
        return edad1;
    }

    public String getNombre1() {
        return nombre1;
    }

    public String getSeccion1() {
        return seccion1;
    }

    public String getSexo1() {
      return sexo1;
    }

    

      public String getPeso1() {
        return peso1;
    }
     public String getAltura1() {
        return altura1;
    }

    public String getResultado1() {
        return resultado1;
    }

     public String getFecha1() {
        return fecha1;
    }

    

  
}
