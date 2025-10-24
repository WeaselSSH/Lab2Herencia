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
        if (empleados.containsKey(empleado.codigoEmp)) 
            return false;
        
        empleados.put(empleado.codigoEmp, empleado);
        return true;
    }

    public boolean registrarHoras(String codigoEmp, int horas) {
        Empleado emp = empleados.get(Empleado.codigoEmp);
        if (emp == null) return false;
        emp.setHorasTrabajadas(emp.getHorasTrabajadas() + horas);
        return true;
        
    }
    
    public boolean registrarVenta(String codigoEmp, double monto) {
          Empleado emp = empleados.get(Empleado.codigoEmp);
          if (emp instanceof EmpleadoVent) {
              ((EmpleadoVent) emp).registrarVenta(monto);
              return true;
          }
          return false;
      }
    
    public boolean FinContrato(String codigo, localDate nuevaFecha) {
          Empleado emp = empleados.get(Empleado.codigoEmp);
          if (emp instanceof EmpleadoTemp) {
              ((EmpleadoTemp) emp).actualizarFechaContrato(nuevaFecha);
              return true;
          }
          return false;
      }

     public double calcularPago(String codigo) {
        Empleado emp = empleados.get(codigo);
        return (emp != null) ? emp.calcularPago() : 0;
    }






}


