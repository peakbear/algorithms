package painter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import javax.swing.JComponent;

class LineComponent extends JComponent {

    ArrayList<Double> lines;

    LineComponent(int width, int height) {
        super();
        setPreferredSize(new Dimension(width,height));
        lines = new ArrayList<Line2D.Double>();
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        lines.add(new Line2D.Double(x1, y1, x2, y2));
        repaint();
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        Dimension d = getPreferredSize();
        g.setColor(Color.black);
        for (Line2D.Double line : lines) {
            g.drawLine(
                (int)line.getX1(),
                (int)line.getY1(),
                (int)line.getX2(),
                (int)line.getY2()
            );
        }
    }
}
