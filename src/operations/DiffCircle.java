package operations;

import formes.Forme;
import formes.Circle;

public class DiffCircle extends Operation {

    @Override
    public Forme apply(Forme forme1, Forme forme2) {
        if (!(forme1 instanceof Circle) || !(forme2 instanceof Circle)) {
            throw new IllegalArgumentException("The difference is only defined for circles.");
        }

        Circle circle1 = (Circle) forme1;
        Circle circle2 = (Circle) forme2;

        int cx1 = circle1.getCx();
        int cy1 = circle1.getCy();
        int radius1 = circle1.getRadius();
        
        int radius2 = circle2.getRadius();

        int newCx = cx1;
        int newCy = cy1;
        int newRadius = Math.abs(radius1 - radius2);

        return new Circle(newCx, newCy, newRadius);
    }
}
