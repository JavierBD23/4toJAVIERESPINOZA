  
package proyecto4tobloque;

 
public class cliente {
    
    
    String Nit, nombre;
    int edad;
    char genero;
    public cliente (String Nit, String nombre, int edad, char genero){
        this.Nit=Nit;
        this.nombre=nombre;
        this.edad=edad;
        this.genero=genero;
    }

    public String getNit() {
        return Nit;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public char getGenero() {
        return genero;
    }
}
