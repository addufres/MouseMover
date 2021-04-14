package mover;

import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.Container;
import java.awt.AWTException;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.Robot;

public class MouseMover {
	
    int sleep;
    int moveX;
    static int moveTimes;
    static int currentMove;
    static Robot robo;
    static JFrame frm;
    static JTextArea txt;
    
    static {
        MouseMover.moveTimes = 3;
        MouseMover.currentMove = 0;
        MouseMover.frm = null;
        MouseMover.txt = null;
    }
    
    public MouseMover() {
        this.sleep = 500;
        this.moveX = 5;
    }

	public static void main(String[] args) {
        final int mx = 892;
        final int mi = 888;
        try {
            MouseMover.robo = new Robot();
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
        System.out.println("Mx " + mx + "  Min " + mi);
        (MouseMover.frm = new JFrame("Mouse Mover")).setSize(350, 150);
        MouseMover.frm.setVisible(true);
        MouseMover.frm.setDefaultCloseOperation(3);
        MouseMover.txt = new JTextArea("Loading ...", 3, 20);
        final Container cnt = MouseMover.frm.getContentPane();
        cnt.setLayout(null);
        cnt.add(MouseMover.txt);
        MouseMover.txt.setBounds(15, 15, 300, 75);
        moveIt(MouseMover.currentMove, MouseMover.robo, MouseMover.txt, MouseMover.moveTimes);
	}
	
    public static void moveIt(int currentMove, final Robot robo, final JTextArea txt, final int moveTimes) {
        PointerInfo poi = MouseInfo.getPointerInfo();
        Point where = null;
        while (true) {
            poi = MouseInfo.getPointerInfo();
            where = poi.getLocation();
            int x = (int)where.getX();
            int y = (int)where.getY();
            currentMove = intRotate(currentMove, moveTimes, 0, 1);
            if (currentMove % 4 == 0) {
                x = (int)(where.getX() + 5.0);
            }
            else if (currentMove % 4 == 1) {
                x = (int)(where.getX() - 5.0);
            }
            else if (currentMove % 4 == 2) {
                y = (int)(where.getY() + 5.0);
            }
            else if (currentMove % 4 == 3) {
                y = (int)(where.getY() - 5.0);
            }
            final String s1 = "Mouse Position Currently \nx: " + x + "\n" + "y: " + y;
            System.out.println(String.valueOf(s1) + "\n" + "**************************************");
            txt.setText(s1);
            robo.mouseMove(x, y);
            sleeper(8000);
        }
    }
    
    public static int intRotate(final int currentMove, final int max, final int min, final int moveBy) {
        int rotate = currentMove + moveBy;
        if (rotate < min) {
            rotate = min;
        }
        if (rotate > max) {
            rotate = min;
        }
        return rotate;
    }
    
    public static void sleeper(final int s) {
        try {
            Thread.sleep(s);
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

}
