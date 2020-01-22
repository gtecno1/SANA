/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author black
 */
public class ReporteEstudiantes {
  
    private String cedula1;
    private String nombre1;
    private String apellido1;
    private String edad1;
     private String sexo1;
      private String seccion1;
      private String correo1;
       private String fechan1;
        private String fechan2;
         private String fechan3;
   
    public ReporteEstudiantes(String cedula1, String nombre1, String apellido1, String edad1,String fechan1, String sexo1, String seccion1,String correo1,String fechan2,String fechan3) {
      this.cedula1 = cedula1;
        this.nombre1 = nombre1;
        this.apellido1 = apellido1;
        this.edad1 = edad1;
        this.sexo1 = sexo1;
        this.fechan1 = fechan1;
        this.seccion1 = seccion1;
        this.correo1 = correo1;
    }

    public String getApellido1() {
        return apellido1;
    }
public String getCedula1() {
        return cedula1;
    }
    public String getCorreo1() {
        return correo1;
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

    public String getFechan1() {
        return fechan1;
    }

    public String getFechan2() {
        return fechan2;
    }

    public String getFechan3() {
        return fechan3;
    }

   

    

  
}
