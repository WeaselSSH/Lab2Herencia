/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresamain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List; 

/**
 *
 * @author cglm6
 */
public class Empresa {
    
    
    private List<Empleado> empleados = new ArrayList<>();

    private Empleado buscarEmpleadoPorCodigoInterno(String codigo) {
            for (Empleado emp : this.empleados) {
                if (emp.getCodigoEmp().equals(codigo)) {
                    return emp;
                }
            }
            return null;
    }
    
    public boolean registrarEmpleado(Empleado empleado) {
            String codigo = empleado.getCodigoEmp();

            if (buscarEmpleadoPorCodigoInterno(codigo) != null) { 
                return false;
            }

            empleados.add(empleado);
            return true;
    }

    public boolean registrarHoras(String codigoEmp, int horas) {
        Empleado emp = buscarEmpleadoPorCodigoInterno(codigoEmp);
        if (emp == null) return false;
        emp.getHorasTrabajadas(emp.horasTrabajadas + horas);
        return true;
        
    }
    
    public boolean registrarVenta(String codigoEmp, double monto) {
          Empleado emp = buscarEmpleadoPorCodigoInterno(codigoEmp);
            if (emp instanceof EmpleadoVentas empleadoVentas) {
              empleadoVentas.registrarVenta(codigoEmp, monto);
              return true;
             }
          return false;
      }
    
    public boolean finContrato(String codigoEmp, Calendar nuevaFecha) {
            Empleado emp = buscarEmpleadoPorCodigoInterno(codigoEmp);
            if (emp instanceof EmpleadoTemporal empleadoTemporal) {
                empleadoTemporal.actualizarFecha(nuevaFecha);
                return true;
            }
            return false;
    }

     public double calcularPago(String codigo) {
        Empleado emp = buscarEmpleadoPorCodigoInterno(codigo); 
        return (emp != null) ? emp.calcularPago() : 0;
    }
    
    public Empleado buscarEmpleadoPorCodigo(String codigo){
        
        return buscarEmpleadoPorCodigoInterno(codigo);
    }
     
    int countEstandar = 0;
    int countTemporal = 0;
    int countVentas = 0;
    
    
    public void Reporte() {
        
        for (Empleado emp : empleados) { 
            String codigo = emp.getCodigoEmp();
            String info = String.format("  [%s] Nombre: %s, Horas: %.1f, Salario Base: %.2f", 
               codigo, emp.getNombreEmp(), emp.getHorasTrabajadas(), emp.getSalarioBase());

            if (emp instanceof EmpleadoVentas empVent) {
                double ventas = empVent.getVentasMensual();
                double comision = empVent.calcularComision(); 
                info += String.format(", Ventas Acum.: %.2f, Comisión: %.2f", ventas, comision);
                reporteVentas.append(info).append("\n");
                countVentas++;

                 } else if (emp instanceof EmpleadoTemporal) {
                    EmpleadoTemporal empTemp = (EmpleadoTemporal) emp;
                    info += String.format(", Fin Contrato: %s", empTemp.getFechaFin().toString());
                    reporteTemporal.append(info).append("\n");
                    countTemporal++;

                } else {
                    reporteEstandar.append(info).append("\n");
                    countEstandar++;
                }
        } 
    }







}


