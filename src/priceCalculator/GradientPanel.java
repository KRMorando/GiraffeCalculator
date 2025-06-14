package priceCalculator;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GradientPanel extends JPanel {

    private Color startColor;
    private Color endColor;

    public GradientPanel(Color startColor, Color endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // �⺻ �׸��� ����

        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();

        // ������ �Ʒ��� ���� �׶��̼�
        GradientPaint gp = new GradientPaint(0, 0, startColor, 0, height, endColor);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}
