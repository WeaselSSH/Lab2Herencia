package empresamain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrmRegistrarVentas extends BaseFrame {

    private JPanel panelPrincipal, panelNorte, panelCentro;
    private JLabel lblTitulo, lblCodigo, lblMonto;
    private JTextField txtCodigo, txtMonto;
    private JButton btnRegistrar, btnCancelar;

    public FrmRegistrarVentas() {
        super("Registrar Ventas", 650, 340);
        initComponents();
    }

    @Override
    public void initComponents() {
        panelPrincipal = crearPanelGradiente();
        panelPrincipal.setLayout(new BorderLayout());

        panelNorte = crearPanelTransparente();
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        panelCentro = crearPanelTransparente();
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        lblTitulo = crearLabel("Registrar Ventas", 230, 12, 520, 36);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 22f));
        panelNorte.add(lblTitulo);

        lblCodigo = crearLabel("Código Empleado:", 60, 40, 180, 25);
        lblCodigo.setFont(lblCodigo.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblCodigo);

        txtCodigo = crearTextField("", 240, 40, 100, 28);
        panelCentro.add(txtCodigo);

        lblMonto = crearLabel("Monto de Venta:", 60, 90, 180, 25);
        lblMonto.setFont(lblMonto.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblMonto);

        txtMonto = crearTextField("", 240, 90, 160, 28);
        panelCentro.add(txtMonto);

        btnRegistrar = crearBoton("Registrar", 180, 170, 120, 35);
        panelCentro.add(btnRegistrar);

        btnCancelar = crearBoton("Cancelar", 340, 170, 120, 35);
        panelCentro.add(btnCancelar);

        btnRegistrar.addActionListener(e -> onRegistrar());
        btnCancelar.addActionListener(e -> {
            new MenuPrincipal().setVisible(true);
            dispose();
        });

        setContentPane(panelPrincipal);
    }

    private void onRegistrar() {
        String codigo = txtCodigo.getText().trim();
        String sMonto = txtMonto.getText().trim();
        if (codigo.isEmpty() || sMonto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa código y monto.");
            return;
        }
        double monto;
        try {
            monto = Double.parseDouble(sMonto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monto inválido.");
            return;
        }
        boolean ok = Empresa.getEmpresa().registrarVenta(codigo, monto);
        if (!ok) {
            JOptionPane.showMessageDialog(this, "Empleado no encontrado o no es de ventas.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Venta registrada.");
        new MenuPrincipal().setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new FrmRegistrarVentas().setVisible(true);
    }
}
