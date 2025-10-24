package empresamain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FrmVerReportes extends BaseFrame {

    private JPanel panelPrincipal, panelNorte, panelCentro;
    private JLabel lblTitulo;
    private JTextArea txtReporte;
    private JScrollPane spReporte;
    private JButton btnCerrar;

    public FrmVerReportes() {
        super("VIctini Empresa", 670, 500);
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

        lblTitulo = crearLabel("Reporte de Empleados", 220, 12, 300, 36);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 22f));
        panelNorte.add(lblTitulo);

        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        txtReporte.setLineWrap(true);
        txtReporte.setWrapStyleWord(true);
        txtReporte.setBackground(Color.decode("#0F1115"));
        txtReporte.setForeground(Color.decode("#E6EDF7"));
        txtReporte.setCaretColor(Color.decode("#E6EDF7"));
        txtReporte.setSelectionColor(Color.decode("#334155"));
        txtReporte.setSelectedTextColor(Color.decode("#E6EDF7"));
        txtReporte.setFont(txtReporte.getFont().deriveFont(Font.PLAIN, 14f));
        txtReporte.setBorder(BorderFactory.createLineBorder(java.awt.Color.decode("#374151")));

        spReporte = new JScrollPane(txtReporte);
        spReporte.setBounds(30, 40, 600, 260);
        panelCentro.add(spReporte);

        btnCerrar = crearBoton("Cerrar", 275, 335, 120, 35);
        panelCentro.add(btnCerrar);

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        FrmVerReportes frm = new FrmVerReportes();
        frm.setVisible(true);
    }
}
