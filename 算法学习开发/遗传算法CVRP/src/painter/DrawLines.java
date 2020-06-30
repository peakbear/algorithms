package painter;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import model.Point;

public class DrawLines {

    public static void drawLines(List<Point> points) {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        final LineComponent lines = new LineComponent(500, 500);

        List<Point> pointsCopy = new ArrayList<>();
        pointsCopy.add(new Point(0, 0, 0));
        pointsCopy.addAll(points);

        for (int i = 0; i < pointsCopy.size(); i++) {
            lines.addLine(
                pointsCopy.get(i).getX(),
                pointsCopy.get(i).getY(),
                pointsCopy.get((i+1)%pointsCopy.size()).getX(),
                pointsCopy.get((i+1)%pointsCopy.size()).getY());
        }
        testFrame.getContentPane().add(lines, BorderLayout.CENTER);
        testFrame.pack();
        testFrame.setVisible(true);

    }
}
