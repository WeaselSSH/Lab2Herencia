/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresamain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import javax.swing.*;

/**
 *
 * @author esteb
 */
public class FrmCalcularPago extends BaseFrame {

    private Empresa empresa = new Empresa();

    private JPanel panelPrincipal, panelNorte, panelCentro;
    private JLabel lblTitulo, lblCodigo, lblNombre, lblTotalPagar;
    private JTextField txtCodigo, txtNombre, txtTotalPagar;
    private JButton btnCalcular;

    public FrmCalcularPago() {
        super("Calcular Pago Empleado", 600, 500);
        initComponents();
    }

    @Override
    public void initComponents() {
        panelPrincipal = crearPanelGradiente();
        panelPrincipal.setLayout(new BorderLayout());

        panelNorte = crearPanelTransparente();
        panelNorte.setPreferredSize(new Dimension(0, 80));
        panelNorte.setLayout(null);
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        panelCentro = crearPanelTransparente();
        panelCentro.setLayout(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        lblTitulo = crearLabel("Calcular Pago de Empleado", 0, 20, 600, 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));
        panelNorte.add(lblTitulo);

        lblCodigo = crearLabel("Código:", 150, 60, 120, 25);
        lblCodigo.setFont(lblCodigo.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblCodigo);

        txtCodigo = crearTextField("", 280, 60, 150, 25);
        panelCentro.add(txtCodigo);

        lblNombre = crearLabel("Nombre:", 150, 110, 120, 25);
        lblNombre.setFont(lblNombre.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblNombre);

        txtNombre = crearTextField("", 280, 110, 200, 25);
        txtNombre.setEnabled(false);
        panelCentro.add(txtNombre);

        lblTotalPagar = crearLabel("Total a Pagar:", 150, 160, 150, 25);
        lblTotalPagar.setFont(lblTotalPagar.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblTotalPagar);

        txtTotalPagar = crearTextField("", 280, 160, 150, 25);
        txtTotalPagar.setEnabled(false);
        panelCentro.add(txtTotalPagar);

        btnCalcular = crearBoton("Calcular Pago", 225, 250, 150, 45);
        btnCalcular.addActionListener(e -> {
            String codigo = txtCodigo.getText();
            String resultado = empresa.calcularPagoConNombre(codigo);

            if (resultado.equals("Empleado no encontrado")) {
                txtNombre.setText("");
                txtTotalPagar.setText("");
                JOptionPane.showMessageDialog(this, "Empleado no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String[] partes = resultado.replace("Empleado: ", "").split(" - Pago: ");
                txtNombre.setText(partes[0]);
                txtTotalPagar.setText(partes[1]);
            }
        });
        panelCentro.add(btnCalcular);

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmCalcularPago().setVisible(true);
    }
}
