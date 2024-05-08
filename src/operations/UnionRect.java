package operations;

import formes.Forme;
import formes.Rectangle;

public class UnionRect extends Operation {

    @Override
    public Forme apply(Forme forme1, Forme forme2) {
        if (!(forme1 instanceof Rectangle) || !(forme2 instanceof Rectangle)) {
            throw new IllegalArgumentException("The union is only defined for rectangles.");
        }

        Rectangle rect1 = (Rectangle) forme1;
        Rectangle rect2 = (Rectangle) forme2;

        int x1 = Math.min(rect1.getX1(), rect2.getX1());
        int y1 = Math.min(rect1.getY1(), rect2.getY1());
        int x2 = Math.max(rect1.getX2(), rect2.getX2());
        int y2 = Math.max(rect1.getY2(), rect2.getY2());

        x1 -= Math.min(rect1.getX1(), rect2.getX1());
        y1 -= Math.min(rect1.getY1(), rect2.getY1());
        x2 -= Math.min(rect1.getX1(), rect2.getX1());
        y2 -= Math.min(rect1.getY1(), rect2.getY1());

        return new Rectangle(x1, y1, x2, y2);
    }
}
