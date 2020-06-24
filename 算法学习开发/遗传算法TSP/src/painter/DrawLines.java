package painter;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import model.Point;

public class DrawLines {

    public static void drawLines(List<Point> points) {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final LineComponent lines = new LineComponent(500, 500);
        for (int i = 0; i < points.size(); i++) {
            lines.addLine(
                points.get(i).getX(),
                points.get(i).getY(),
                points.get((i+1)%points.size()).getX(),
                points.get((i+1)%points.size()).getY());
        }
        testFrame.getContentPane().add(lines, BorderLayout.CENTER);
        testFrame.pack();
        testFrame.setVisible(true);

    }
}
