package operations;

import formes.Forme;
import formes.Rectangle;

public class DiffRect extends Operation {
    
    @Override
    public Forme apply(Forme forme1, Forme forme2) {
        if (!(forme1 instanceof Rectangle) || !(forme2 instanceof Rectangle)) {
            throw new IllegalArgumentException("The difference is only defined for rectangles.");
        }

        Rectangle rect1 = (Rectangle) forme1;
        Rectangle rect2 = (Rectangle) forme2;

        int x1 = rect1.getX1();
        int y1 = rect1.getY1();
        int x2 = rect1.getX2();
        int y2 = rect1.getY2();

        if (rect1.getX1() < rect2.getX2() && rect1.getX2() > rect2.getX1() &&
            rect1.getY1() < rect2.getY2() && rect1.getY2() > rect2.getY1()) {
            if (rect1.getX1() < rect2.getX1()) {
                x2 = rect2.getX1();
            }
            if (rect1.getX2() > rect2.getX2()) {
                x1 = rect2.getX2();
            }
            if (rect1.getY1() < rect2.getY1()) {
                y2 = rect2.getY1();
            }
            if (rect1.getY2() > rect2.getY2()) {
                y1 = rect2.getY2();
            }
        }
        return new Rectangle(x1, y1, x2, y2);
    }
}
