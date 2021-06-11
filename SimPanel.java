import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class SimPanel extends JPanel {
    final private int PANEL_WIDTH = 500;
    final private int PANEL_HEIGHT = 500;
    private int rows;
    private int columns;

    public SimPanel(int aRows, int aCols) {
        rows = aRows;
        columns = aCols;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setFocusable(true);
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        System.out.println(getWidth() + ", " + getHeight());
        for(int r=0; r<rows; ++r) {
            g2.drawLine(0, r*(getHeight()/rows), getWidth(), r*(getHeight()/rows));
        }
        for(int c=0; c<columns; ++c) {
            g2.drawLine(c*(getWidth()/columns), 0, c*(getWidth()/columns), getHeight());
        }
    }

}
