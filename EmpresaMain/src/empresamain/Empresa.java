/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresamain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author cglm6
 */
public class Empresa {

    private ArrayList<Empleado> empleados = new ArrayList<>();

    public boolean registrarEmpleado(Empleado empleado) {
        for (Empleado emp : empleados) {
            if (emp.getCodigoEmp().equals(empleado.getCodigoEmp())) {
                return false;
            }
        }
        empleados.add(empleado);
        return true;
    }

    public boolean registrarHoras(String codigoEmp, int horas) {
        Empleado emp = buscarEmpleado(codigoEmp);
        if (emp == null) {
            return false;
        }
        emp.registrarHoras(horas);
        return true;
    }

    // Registrar una venta (solo para empleados de ventas)
    public boolean registrarVenta(String codigoEmp, double monto) {
        Empleado emp = buscarEmpleado(codigoEmp);
        if (emp instanceof EmpleadoVentas) {
            ((EmpleadoVentas) emp).registrarVentas(monto);
            return true;
        }
        return false;
    }

    public boolean finContrato(String codigoEmp, LocalDate nuevaFecha) {
        Empleado emp = buscarEmpleado(codigoEmp);
        if (emp instanceof EmpleadoTemporal) {
            Calendar cal = Calendar.getInstance();
            cal.set(nuevaFecha.getYear(), nuevaFecha.getMonthValue() - 1, nuevaFecha.getDayOfMonth());
            ((EmpleadoTemporal) emp).actualizarFecha(cal);
            return true;
        }
        return false;
    }

    public String calcularPagoConNombre(String codigoEmp) {
        Empleado emp = buscarEmpleado(codigoEmp);
        if (emp != null) {
            return "Empleado: " + emp.getNombreEmp() + " - Pago: " + emp.calcularPago();
        }
        return "Empleado no encontrado";
    }

    public void generarReportes() {
        int countEstandar = 0, countTemporal = 0, countVentas = 0;
        StringBuilder reporteEstandar = new StringBuilder();
        StringBuilder reporteTemporal = new StringBuilder();
        StringBuilder reporteVentas = new StringBuilder();

        for (Empleado emp : empleados) {
            String info = String.format("  [%s] Nombre: %s, Horas: %.1f, Salario Base: %.2f",
                    emp.getCodigoEmp(), emp.getNombreEmp(), emp.getHorasTrabajadas(), emp.getSalarioBase());

            if (emp instanceof EmpleadoVentas) {
                EmpleadoVentas empVent = (EmpleadoVentas) emp;
                double ventas = empVent.ventasAnuales();
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

        System.out.println("=== Empleados Estandar (" + countEstandar + ") ===");
        System.out.println(reporteEstandar);
        System.out.println("=== Empleados Temporales (" + countTemporal + ") ===");
        System.out.println(reporteTemporal);
        System.out.println("=== Empleados de Ventas (" + countVentas + ") ===");
        System.out.println(reporteVentas);
    }

    private Empleado buscarEmpleado(String codigoEmp) {
        for (Empleado emp : empleados) {
            if (emp.getCodigoEmp().equals(codigoEmp)) {
                return emp;
            }
        }
        return null;
    }
}
