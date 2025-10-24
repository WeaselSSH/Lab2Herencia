/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresamain;

import java.util.Calendar;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author cglm6
 */
public class Empresa {
    
    
    private Map<String, Empleado> empleados = new HashMap<>();


    
    public boolean registrarEmpleado(Empleado empleado) {
        if (empleados.containsKey(empleado.getCodigoEmp())) 
            return false;
        
        empleados.put(empleado.codigoEmp, empleado);
        return true;
    }

    public boolean registrarHoras(String codigoEmp, int horas) {
        Empleado emp = empleados.get(codigoEmp);
        if (emp == null) return false;
        emp.setHorasTrabajadas(emp.horasTrabajadas() + horas);
        return true;
        
    }
    
    public boolean registrarVenta(String codigoEmp, double monto) {
          Empleado emp = empleados.get(codigoEmp);
            if (emp instanceof EmpleadoVentas) {
              ((EmpleadoVentas) emp).registrarVenta(monto);
              return true;
             }
          return false;
      }
    
    public boolean finContrato(String codigoEmp, LocalDate nuevaFecha) {
            Empleado emp = empleados.get(codigoEmp);
            if (emp instanceof EmpleadoTemporal) {
                ((EmpleadoTemporal) emp).actualizarFechaContrato(nuevaFecha);
                return true;
            }
            return false;
    }

     public double calcularPago(String codigo) {
        Empleado emp = empleados.get(codigo);
        return (emp != null) ? emp.calcularPago() : 0;
    }
     
    int countEstandar = 0;
    int countTemporal = 0;
    int countVentas = 0;

    for (Empleado emp : empleados.values()) {
        String codigo = emp.getCodigoEmp();
        String info = String.format("  [%s] Nombre: %s, Horas: %.1f, Salario Base: %.2f", 
            codigo, emp.getNombreEmp(), emp.getHorasTrabajadas(), emp.getSalarioBase());

        if (emp instanceof EmpleadoVentas) {
            EmpleadoVentas empVent = (EmpleadoVentas) emp;
            double ventas = empVent.getVentasAcumuladas();
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


