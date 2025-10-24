package empresamain;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrmActualizarContrato extends BaseFrame {

    private JPanel panelPrincipal, panelNorte, panelCentro;
    private JLabel lblTitulo, lblCodigo, lblFecha;
    private JTextField txtCodigo;
    private JDateChooser dateChooser;
    private JButton btnActualizar, btnCancelar;

    public FrmActualizarContrato() {
        super("VIctini Empresa", 670, 360);
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

        lblTitulo = crearLabel("Actualizar Contrato", 220, 12, 300, 36);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));
        panelNorte.add(lblTitulo);

        lblCodigo = crearLabel("Código Empleado:", 40, 40, 200, 25);
        lblCodigo.setFont(lblCodigo.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblCodigo);

        txtCodigo = crearTextField("", 230, 40, 180, 28);
        panelCentro.add(txtCodigo);

        lblFecha = crearLabel("Nueva Fecha Fin:", 40, 100, 200, 25);
        lblFecha.setFont(lblFecha.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblFecha);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(230, 100, 180, 28);
        panelCentro.add(dateChooser);

        btnActualizar = crearBoton("Actualizar", 180, 200, 120, 35);
        panelCentro.add(btnActualizar);

        btnCancelar = crearBoton("Cancelar", 340, 200, 120, 35);
        panelCentro.add(btnCancelar);

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmActualizarContrato().setVisible(true);
    }
}
