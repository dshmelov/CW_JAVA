
import java.awt.*;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.*;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Dan
 */


class GraphPanel extends JComponent {

    int WIDTH = 0;
    int HEIGHT = 0;

    float X = 0;
    float Y = 0;

    int X_MIN = 0;
    int Y_MIN = 0;
    int X_MAX = 0;
    int Y_MAX = 0;

    
    ArrayList<Double> resList;

    GraphPanel(int WIDTH, int HEIGHT, float X, float Y, int X_MIN, int Y_MIN, int X_MAX, int Y_MAX, ArrayList<Double> resList) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.X = X;
        this.Y = Y;
        this.X_MIN = X_MIN;
        this.Y_MIN = Y_MIN;
        this.X_MAX = X_MAX;
        this.Y_MAX = Y_MAX;
        this.resList = resList;
    }

    @Override
    protected void paintComponent(Graphics G) {
        super.paintComponent(G);
        Graphics2D G2D = (Graphics2D) G;
        G2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        G2D.draw(new Line2D.Double(0, HEIGHT / 2, WIDTH, HEIGHT / 2));
        G2D.draw(new Line2D.Double(WIDTH / 2, 0, WIDTH / 2, HEIGHT));
        int step = 0;
        //int s = WIDTH / (X_MAX - X_MIN);
        for (int i = X_MIN; i <= X_MAX; i++) {
            if (i != 0) {
                G2D.drawString(String.valueOf(i), step, HEIGHT / 2 - 5);
                G2D.drawString("|", step, HEIGHT / 2 + 5);
            }
            step += WIDTH / (X_MAX - X_MIN);
        }

        step = 0;
        for (int i = Y_MIN; i <= Y_MAX; i++) {
            if (i != 0) {
                G2D.drawString(String.valueOf(-i), WIDTH / 2 + 10, step);
                G2D.drawString("—", WIDTH / 2 - 5, step);
            }

            step += HEIGHT / (Y_MAX - Y_MIN);
        }
        
    
        G2D.setPaint(Color.BLACK);
        for (int i = 0; i < resList.size() - 1; i++) {
            G2D.draw(new Line2D.Double(i, -resList.get(i) + HEIGHT / 2, i + 1, -resList.get(i + 1) + HEIGHT / 2));
        }
    }
}
