package empresamain;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BaseFrame extends JFrame {

    public BaseFrame(String titulo, int width, int height) {
        super(titulo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
    }

    protected void initComponents() {

    }

    protected JLabel crearLabel(String texto, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(lbl.getFont().deriveFont(Font.PLAIN, 16f));
        lbl.setBounds(x, y, w, h);
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    protected JTextField crearTextField(String texto, int x, int y, int w, int h) {
        JTextField tf = new JTextField(texto);
        tf.setBounds(x, y, w, h);
        tf.setBackground(Color.decode("#1A2332"));
        tf.setForeground(Color.decode("#E6EDF7"));
        tf.setCaretColor(Color.decode("#E6EDF7"));
        tf.setSelectionColor(Color.decode("#334155"));
        tf.setSelectedTextColor(Color.decode("#E6EDF7"));
        tf.setBorder(BorderFactory.createLineBorder(Color.decode("#374151")));
        tf.setFont(tf.getFont().deriveFont(Font.PLAIN, 14f));
        return tf;
    }

    protected JButton crearBoton(String texto, int x, int y, int w, int h) {
        JButton btn = new JButton(texto);
        btn.setBounds(x, y, w, h);
        btn.setBackground(Color.decode("#244FC0"));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(Color.decode("#2B3B63")));
        btn.setFocusPainted(false);
        return btn;
    }

    protected JPanel crearPanelGradiente() {
        return crearPanelGradiente(new Color(0x050607), new Color(0x0F1115));
    }

    protected JPanel crearPanelGradiente(Color start, Color end) {
        JPanel p = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();

                /*rellena el panel desde el punto (0,0) que es la esquina superior izquierda
                hasta el final (getwidth, getheight).
                
                en otras palabras, el degradado va diagonal
                 */
                g2.setPaint(new GradientPaint(0, 0, start, getWidth(), getHeight(), end));
                g2.fillRect(0, 0, getWidth(), getHeight()); //crea el rectangulo con las dimensiones de las coordenadas
                g2.dispose();
            }
        };
        return p;
    }

    protected JPanel crearPanelTransparente() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        p.setLayout(null);
        return p;
    }

}
