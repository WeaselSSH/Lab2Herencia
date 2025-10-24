
package empresamain;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Empleado {
    protected String codigoEmp;
    protected String nombreEmp;
    protected Calendar fechaContratacion;
    protected double salarioBase;
    protected double horasTrabajadas;
    
    public Empleado(String codigoEmp, String nombreEmp,double salarioBase){
        this.codigoEmp=codigoEmp;
        this.nombreEmp=nombreEmp;
        this.fechaContratacion=Calendar.getInstance();
        this.salarioBase=salarioBase;
        this.horasTrabajadas=0;
    }
    
    public String getCodigoEmp() {
        return codigoEmp;
    }
    public String getNombreEmp() {
        return nombreEmp;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
    
    public void registrarHoras(double horas){
        this.horasTrabajadas += horas;
    }
    
    public double calcularPago(){
        double pagoProp = (salarioBase * (horasTrabajadas/160.00));
        double deduccion = pagoProp * 0.035;
        
        return pagoProp - deduccion;
    }
    public double getHorasTrabajadas() {
        return horasTrabajadas;
    }
    
    public String mostrarInfo(){
        SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");
        
        return "Codigo: "+ codigoEmp +
                "\nNombre: " + nombreEmp +
                "\nFecha de contratacion: " +  fechaFormateada.format(fechaContratacion.getTime());
    }  
}
