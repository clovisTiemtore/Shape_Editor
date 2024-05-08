package operations;

import formes.Forme;
import formes.Circle;

public class InterCircle extends Operation {

    @Override
    public Forme apply(Forme forme1, Forme forme2) {
        if (!(forme1 instanceof Circle) || !(forme2 instanceof Circle)) {
            throw new IllegalArgumentException("Intersection is only defined for circles.");
        }
        
        Circle circle1 = (Circle) forme1;
        Circle circle2 = (Circle) forme2;
        
        int cx = Math.max(circle1.getCx() - circle1.getRadius(), circle2.getCx() - circle2.getRadius());
        int cy = Math.max(circle1.getCy() - circle1.getRadius(), circle2.getCy() - circle2.getRadius());
        int rayon = Math.min(circle1.getCx() + circle1.getRadius(), circle2.getCx() + circle2.getRadius()) - cx;

        return new Circle(cx, cy, rayon);
    }
}
