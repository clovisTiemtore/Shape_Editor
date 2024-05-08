package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ShapeView extends JFrame {

    private ShapePanel shapePanel;
    private JButton restoreButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton unionButton;
    private JButton interButton;
    private JButton diffButton;
    private JButton circleButton;
    private JButton rectButton;

    public ShapeView() {
        setTitle("Shape Editor");
        setSize(950, 700);

        shapePanel = new ShapePanel();

        ImageIcon unionIcon = new ImageIcon("images/union.png");
        ImageIcon interIcon = new ImageIcon("images/inter.png");
        ImageIcon diffIcon = new ImageIcon("images/diff.png");
        ImageIcon circleIcon = new ImageIcon("images/circle.png");
        ImageIcon rectIcon = new ImageIcon("images/rectangle.png");
        ImageIcon deleteIcon = new ImageIcon("images/delete.png");
        ImageIcon saveIcon = new ImageIcon("images/save.png");
        ImageIcon restoreIcon = new ImageIcon("images/restore.png");

        unionButton = new JButton(unionIcon);
        interButton = new JButton(interIcon);
        diffButton = new JButton(diffIcon);
        circleButton = new JButton(circleIcon);
        rectButton = new JButton(rectIcon);
        deleteButton = new JButton(deleteIcon);
        saveButton = new JButton(saveIcon);
        restoreButton = new JButton(restoreIcon);

        circleButton.setPreferredSize(new Dimension(50, 50));
        rectButton.setPreferredSize(new Dimension(50, 50));
        unionButton.setPreferredSize(new Dimension(50, 50));
        interButton.setPreferredSize(new Dimension(50, 50));
        diffButton.setPreferredSize(new Dimension(50, 50));
        saveButton.setPreferredSize(new Dimension(50, 50));
        restoreButton.setPreferredSize(new Dimension(50, 50));
        deleteButton.setPreferredSize(new Dimension(50, 50));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(circleButton);
        buttonPanel.add(rectButton);
        buttonPanel.add(unionButton);
        buttonPanel.add(interButton);
        buttonPanel.add(diffButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(restoreButton);

        circleButton.addActionListener(e -> shapePanel.setShapeType(ShapeType.CIRCLE));
        rectButton.addActionListener(e -> shapePanel.setShapeType(ShapeType.RECTANGLE));
        deleteButton.addActionListener(e -> shapePanel.deleteSelectedShape());

        add(shapePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ShapeView::new);
    }

    enum ShapeType {
        CIRCLE, RECTANGLE
    }

    public class ShapePanel extends JPanel {
        private ShapeType shapeType;
        private List<Shape> shapes;
        private Point[] points;
        private Shape selectedShape;

        public ShapePanel() {
            setPreferredSize(new Dimension(850, 650));
            setBackground(Color.WHITE);

            shapeType = null;
            points = new Point[2];
            shapes = new ArrayList<>();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    selectedShape = getSelectedShape(e.getPoint());
                    if (selectedShape == null) {
                        points[0] = e.getPoint();
                        points[1] = e.getPoint();
                    }
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (selectedShape == null) {
                        if (shapeType != null) {
                            points[1] = e.getPoint();
                            switch (shapeType) {
                                case CIRCLE:
                                    int radius = (int) Math.sqrt(Math.pow(points[1].x - points[0].x, 2)
                                            + Math.pow(points[1].y - points[0].y, 2));
                                    shapes.add(new Circle(points[0], radius));
                                    break;
                                case RECTANGLE:
                                    shapes.add(new rectangle(points[0], points[1]));
                                    break;
                            }
                            shapeType = null;
                            points = new Point[2];
                            repaint();
                        }
                    }
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (selectedShape == null) {
                        points[1] = e.getPoint();
                        repaint();
                    } else {
                        Point p = e.getPoint();
                        selectedShape.move(p.x - points[0].x, p.y - points[0].y);
                        points[0] = p;
                        repaint();
                    }
                }
            });
        }

        public void setShapeType(ShapeType type) {
            this.shapeType = type;
        }

        public void deleteSelectedShape() {
            if (selectedShape != null) {
                shapes.remove(selectedShape);
                selectedShape = null;
                repaint();
            }
        }

        private Shape getSelectedShape(Point p) {
            for (Shape shape : shapes) {
                if (shape.contains(p)) {
                    return shape;
                }
            }
            return null;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.GRAY);
            for (Shape shape : shapes) {
                shape.draw(g);
            }

            if (shapeType != null) {
                g.setColor(Color.LIGHT_GRAY);
                switch (shapeType) {
                    case CIRCLE:
                        if (points[0] != null && points[1] != null) {
                            int radius = (int) Math.sqrt(Math.pow(points[1].x - points[0].x, 2)
                                    + Math.pow(points[1].y - points[0].y, 2));
                            g.fillOval(points[0].x - radius, points[0].y - radius, radius * 2, radius * 2);
                        }
                        break;
                    case RECTANGLE:
                        if (points[0] != null && points[1] != null) {
                            g.fillRect(Math.min(points[0].x, points[1].x), Math.min(points[0].y, points[1].y),
                                    Math.abs(points[0].x - points[1].x), Math.abs(points[0].y - points[1].y));
                        }
                        break;
                }
            }
        }
    }

    public abstract class Shape {
        protected ShapeType type;

        public abstract void draw(Graphics g);

        public abstract boolean contains(Point p);

        public abstract void move(int dx, int dy);

    }

    public class Circle extends Shape {
        private Point center;
        private int radius;

        public Circle(Point center, int radius) {
            this.center = center;
            this.radius = radius;
            type = ShapeType.CIRCLE;
        }

        @Override
        public void draw(Graphics g) {
            g.fillOval(center.x - radius, center.y - radius, 2 * radius, 2 * radius);
        }

        @Override
        public boolean contains(Point p) {
            return p.distance(center) <= radius;
        }

        @Override
        public void move(int dx, int dy) {
            center.translate(dx, dy);
        }

    }

    public class rectangle extends Shape {
        private Point topLeft;
        private Point bottomRight;

        public rectangle(Point p1, Point p2) {
            topLeft = new Point(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y));
            bottomRight = new Point(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y));
            type = ShapeType.RECTANGLE;
        }

        @Override
        public void draw(Graphics g) {
            g.fillRect(topLeft.x, topLeft.y, bottomRight.x - topLeft.x, bottomRight.y - topLeft.y);
        }

        @Override
        public boolean contains(Point p) {
            return p.x >= topLeft.x && p.x <= bottomRight.x && p.y >= topLeft.y && p.y <= bottomRight.y;
        }

        @Override
        public void move(int dx, int dy) {
            topLeft.translate(dx, dy);
            bottomRight.translate(dx, dy);
        }

    }
}
