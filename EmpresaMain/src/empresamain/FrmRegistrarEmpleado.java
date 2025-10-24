package empresamain;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FrmRegistrarEmpleado extends BaseFrame {

    private JPanel panelPrincipal, panelNorte, panelCentro;
    private JLabel lblTitulo, lblCodigo, lblNombre, lblSalario, lblFechaContratacion, lblFinContrato, lblComision, lblTipo;
    private JTextField txtCodigo, txtNombre, txtSalario, txtComision;
    private JDateChooser fechaContratacion, fechaFinContrato;
    private JButton btnCancelar, btnRegistrar;
    private JComboBox<String> cmbTipo;

    public FrmRegistrarEmpleado() {
        super("Registrar Empleados", 840, 640);
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

        lblTitulo = crearLabel("Registro de Empleados", 310, 20, 300, 40);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 24f));
        panelNorte.add(lblTitulo);

        lblCodigo = crearLabel("Código: ", 80, 60, 150, 25);
        lblCodigo.setFont(lblCodigo.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblCodigo);

        txtCodigo = crearTextField("", 170, 60, 90, 25);
        panelCentro.add(txtCodigo);

        lblNombre = crearLabel("Nombre: ", 80, 110, 150, 25);
        lblNombre.setFont(lblCodigo.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblNombre);

        txtNombre = crearTextField("", 170, 110, 290, 25);
        panelCentro.add(txtNombre);

        lblSalario = crearLabel("Salario Base: ", 80, 160, 150, 25);
        lblSalario.setFont(lblSalario.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblSalario);

        txtSalario = crearTextField("", 220, 160, 240, 25);
        panelCentro.add(txtSalario);

        lblFechaContratacion = crearLabel("Fecha Contratación: ", 80, 220, 190, 25);
        lblFechaContratacion.setFont(lblFechaContratacion.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblFechaContratacion);

        fechaContratacion = new JDateChooser();
        fechaContratacion.setDateFormatString("yyyy-MM-dd");
        fechaContratacion.setBounds(270, 220, 190, 25);
        panelCentro.add(fechaContratacion);

        lblTipo = crearLabel("Tipo de Empleado: ", 80, 270, 190, 25);
        lblTipo.setFont(lblTipo.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblTipo);

        cmbTipo = new JComboBox<>(new String[]{"Estandar", "Temporal", "Ventas"});
        cmbTipo.setBounds(270, 270, 190, 25);
        panelCentro.add(cmbTipo);

        lblFinContrato = crearLabel("Fin de Contrato: ", 80, 320, 190, 25);
        lblFinContrato.setFont(lblFinContrato.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblFinContrato);

        fechaFinContrato = new JDateChooser();
        fechaFinContrato.setDateFormatString("yyyy-MM-dd");
        fechaFinContrato.setBounds(230, 320, 190, 25);
        panelCentro.add(fechaFinContrato);

        lblComision = crearLabel("Comisión: ", 80, 360, 190, 25);
        lblComision.setFont(lblComision.getFont().deriveFont(Font.BOLD, 18f));
        panelCentro.add(lblComision);

        txtComision = crearTextField("", 220, 360, 200, 25);
        panelCentro.add(txtComision);

        btnRegistrar = crearBoton("Registrar Empleado", 240, 430, 150, 45);
        panelCentro.add(btnRegistrar);

        btnCancelar = crearBoton("Cancelar", 420, 430, 150, 45);
        panelCentro.add(btnCancelar);

        cmbTipo.addActionListener(e -> {
            String tipo = (String) cmbTipo.getSelectedItem();
            boolean temporal = tipo.equals("Temporal");
            boolean ventas = tipo.equals("Ventas");
            lblFinContrato.setVisible(temporal);
            fechaFinContrato.setVisible(temporal);
            lblComision.setVisible(ventas);
            txtComision.setVisible(ventas);
        });

        lblFinContrato.setVisible(false);
        fechaFinContrato.setVisible(false);
        lblComision.setVisible(false);
        txtComision.setVisible(false);

        btnRegistrar.addActionListener(e -> onRegistrar());
        btnCancelar.addActionListener(e -> {
            new MenuPrincipal().setVisible(true);
            dispose();
        });

        setContentPane(panelPrincipal);
    }

    private void onRegistrar() {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String sSalario = txtSalario.getText().trim();
        if (codigo.isEmpty() || nombre.isEmpty() || sSalario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa código, nombre y salario.");
            return;
        }
        double salario;
        try {
            salario = Double.parseDouble(sSalario);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salario inválido.");
            return;
        }
        String tipo = (String) cmbTipo.getSelectedItem();
        Empleado emp;
        if (tipo.equals("Temporal")) {
            if (fechaFinContrato.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Selecciona la fecha de fin de contrato.");
                return;
            }
            Calendar fin = Calendar.getInstance();
            fin.setTime(fechaFinContrato.getDate());
            emp = new EmpleadoTemporal(codigo, nombre, salario, fin);
        } else if (tipo.equals("Ventas")) {
            String sCom = txtComision.getText().trim();
            if (sCom.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingresa la comisión.");
                return;
            }
            double com;
            try {
                com = Double.parseDouble(sCom);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Comisión inválida.");
                return;
            }
            emp = new EmpleadoVentas(codigo, nombre, salario, com);
        } else {
            emp = new Empleado(codigo, nombre, salario);
        }
        boolean ok = Empresa.getEmpresa().registrarEmpleado(emp);
        if (!ok) {
            JOptionPane.showMessageDialog(this, "Ya existe un empleado con ese código.");
            return;
        }
        JOptionPane.showMessageDialog(this, "Empleado registrado.");
        limpiar();
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtSalario.setText("");
        txtComision.setText("");
        fechaContratacion.setDate(null);
        fechaFinContrato.setDate(null);
        cmbTipo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new FrmRegistrarEmpleado().setVisible(true);
    }
}
