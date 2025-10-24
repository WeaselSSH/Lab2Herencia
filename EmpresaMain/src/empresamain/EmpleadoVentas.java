/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresamain;

import java.util.Calendar;

/**
 *
 * @author esteb
 */
public class EmpleadoVentas extends Empleado{
    private double ventasMensual[];
    private double tasaComision;
    
    public EmpleadoVentas(String codigoEmp, String nombreEmp, double salarioBase, double tasaComision){
        super(codigoEmp, nombreEmp, salarioBase);
        this.tasaComision = tasaComision;
        this.ventasMensual = new double[12];
    }
    
    public void registrarVentas(double monto){
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        ventasMensual[mesActual] += monto;
    }
    
    public double calcularComision(){
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        
        return ventasMensual[mesActual] * tasaComision;
    }
    
    public double calcularPago(){
        return super.calcularPago() + calcularComision();
    }
    
    public double ventasAnuales(){
        double total = 0.00;
        
        for(double venta: ventasMensual){
            total += venta;
        }
        return total;
    }
    
    public String mostrarInfo(){
        return super.mostrarInfo() +
                "\nVentas Anuales: " +  ventasAnuales();
    }
}
