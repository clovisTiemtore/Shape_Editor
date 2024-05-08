package operations;

import formes.*;

public class UnionCircle extends Operation {

    @Override
    public Forme apply(Forme forme1, Forme forme2) {
        if (!(forme1 instanceof Circle) || !(forme2 instanceof Circle)) {
            throw new IllegalArgumentException("Union is only defined for circles.");
        }

        Circle circle1 = (Circle) forme1;
        Circle circle2 = (Circle) forme2;

        int cx = (circle1.getCx() + circle2.getCx()) / 2;
        int cy = (circle1.getCy() + circle2.getCy()) / 2;
        int rayon = (int) Math.sqrt(Math.pow(circle1.getCx() - circle2.getCx(), 2) + Math.pow(circle1.getCy() - circle2.getCy(), 2))
                + Math.max(circle1.getRadius(), circle2.getRadius());

        return new Circle(cx, cy, rayon);
    }
}
