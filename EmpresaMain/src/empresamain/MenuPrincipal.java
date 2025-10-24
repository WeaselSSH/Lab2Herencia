package empresamain;

import java.awt.*;
import javax.swing.*;

public class MenuPrincipal extends BaseFrame {

    private JPanel panelPrincipal, panelNorte, panelCentro;
    private JLabel lblTitulo;
    private JButton btnRegistrarEmpleado, btnRegistrarHoras, btnRegistrarVentas, btnActualizarContrato,
            btnCalcularPago, btnVerReportes, btnSalir;

    public MenuPrincipal() {
        super("Victini Empresa", 870, 680);
        initComponents();
    }

    @Override
    public void initComponents() {
        panelPrincipal = crearPanelGradiente();
        panelPrincipal.setLayout(new BorderLayout());

        panelNorte = crearPanelTransparente();
        panelNorte.setPreferredSize(new Dimension(0, 55));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        panelCentro = crearPanelTransparente();
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        lblTitulo = crearLabel("Menu Principal", 335, 20, 220, 40);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 26f));
        panelNorte.add(lblTitulo);

        btnRegistrarEmpleado = crearBoton("Registrar Empleado", 290, 40, 270, 55);
        panelCentro.add(btnRegistrarEmpleado);

        btnRegistrarHoras = crearBoton("Registrar Horas", 290, 115, 270, 55);
        panelCentro.add(btnRegistrarHoras);

        btnRegistrarVentas = crearBoton("Registrar Ventas", 290, 190, 270, 55);
        panelCentro.add(btnRegistrarVentas);

        btnActualizarContrato = crearBoton("Actualizar Contrato", 290, 265, 270, 55);
        panelCentro.add(btnActualizarContrato);

        btnCalcularPago = crearBoton("Calcular Pago", 290, 340, 270, 55);
        panelCentro.add(btnCalcularPago);

        btnVerReportes = crearBoton("Ver Reportes", 290, 415, 270, 55);
        panelCentro.add(btnVerReportes);

        btnSalir = crearBoton("Salir", 290, 495, 270, 55);
        panelCentro.add(btnSalir);

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}
