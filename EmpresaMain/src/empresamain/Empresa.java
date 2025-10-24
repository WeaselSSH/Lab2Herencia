/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresamain;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author cglm6
 */
public class Empresa {

    private static final Empresa empresa = new Empresa();
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

    public String generarReportesTexto() {
        int countEstandar = 0, countTemporal = 0, countVentas = 0;
        String reporteEstandar = "";
        String reporteTemporal = "";
        String reporteVentas = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Empleado emp : empleados) {
            String info = "  [" + emp.getCodigoEmp() + "] Nombre: " + emp.getNombreEmp()
                    + ", Horas: " + String.format("%.1f", emp.getHorasTrabajadas())
                    + ", Salario Base: " + String.format("%.2f", emp.getSalarioBase());
            if (emp instanceof EmpleadoVentas) {
                EmpleadoVentas ev = (EmpleadoVentas) emp;
                double ventas = ev.ventasAnuales();
                double comision = ev.calcularComision();
                info += ", Ventas Acum.: " + String.format("%.2f", ventas)
                        + ", Comisión: " + String.format("%.2f", comision);
                reporteVentas += info + "\n";
                countVentas++;
            } else if (emp instanceof EmpleadoTemporal) {
                EmpleadoTemporal et = (EmpleadoTemporal) emp;
                String fin = et.getFechaFin() != null ? sdf.format(et.getFechaFin().getTime()) : "N/A";
                info += ", Fin Contrato: " + fin;
                reporteTemporal += info + "\n";
                countTemporal++;
            } else {
                reporteEstandar += info + "\n";
                countEstandar++;
            }
        }

        String out = "";
        out += "=== Empleados Estandar (" + countEstandar + ") ===\n";
        out += reporteEstandar + "\n";
        out += "=== Empleados Temporales (" + countTemporal + ") ===\n";
        out += reporteTemporal + "\n";
        out += "=== Empleados de Ventas (" + countVentas + ") ===\n";
        out += reporteVentas;
        return out;
    }

    private Empleado buscarEmpleado(String codigoEmp) {
        for (Empleado emp : empleados) {
            if (emp.getCodigoEmp().equals(codigoEmp)) {
                return emp;
            }
        }
        return null;
    }

    public static Empresa getEmpresa() {
        return empresa;
    }
}
