package empresamain;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrmRegistrarHoras extends BaseFrame {

    private JPanel panelPrincipal, panelNorte, panelCentro;
    private JLabel lblTitulo, lblCodigo, lblHoras;
    private JTextField txtCodigo, txtHoras;
    private JButton btnRegistrar, btnCancelar;

    public FrmRegistrarHoras() {
        super("Registrar Horas", 670, 380);
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

        lblTitulo = crearLabel("Registro de Horas", 230, 20, 300, 40);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));
        panelNorte.add(lblTitulo);

        lblCodigo = crearLabel("Código Empleado:", 40, 60, 180, 25);
        lblCodigo.setFont(lblCodigo.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblCodigo);

        txtCodigo = crearTextField("", 220, 60, 100, 25);
        panelCentro.add(txtCodigo);

        lblHoras = crearLabel("Horas Trabajadas:", 40, 120, 180, 25);
        lblHoras.setFont(lblHoras.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblHoras);

        txtHoras = crearTextField("", 220, 120, 180, 25);
        panelCentro.add(txtHoras);
        
        btnRegistrar = crearBoton("Registrar", 180, 220, 120, 35);
        panelCentro.add(btnRegistrar);
        
        btnCancelar = crearBoton("Cancelar", 340, 220, 120, 35);
        panelCentro.add(btnCancelar);

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmRegistrarHoras().setVisible(true);
    }
}
