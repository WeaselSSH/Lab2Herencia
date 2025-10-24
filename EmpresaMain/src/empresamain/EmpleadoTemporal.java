/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresamain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author esteb
 */
public class EmpleadoTemporal extends Empleado{
    private Calendar fechaFin;
    
    public EmpleadoTemporal(String codigoEmp, String nombreEmp, double salarioBase, Calendar fechaFin){
        super(codigoEmp, nombreEmp, salarioBase);
        this.fechaFin = fechaFin;
    }
    
    public double calcularPago(){
        Calendar hoy = Calendar.getInstance();
        if(hoy.compareTo(fechaFin) <= 0)
            return super.calcularPago();
        return 0;
    }
    
    public void actualizarFecha(Calendar fechaNueva){
        this.fechaFin = fechaNueva;
    }
    
    public Calendar getFechaFin() {
        return fechaFin;
    }
    
    public String mostrarInfo(){
        SimpleDateFormat fechaFormateada = new SimpleDateFormat("dd/MM/yyyy");
        return super.mostrarInfo() + 
                "\nFecha Fin del contrato: " + fechaFormateada.format(fechaFin.getTime());
    }
}
