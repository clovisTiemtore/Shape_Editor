package formes;

public class Circle extends Forme {
    private int cx, cy, radius;

    public Circle(int cx, int cy, int radius) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
    }

    public int getCx() {
        return cx;
    }

    public int getCy() {
        return cy;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public boolean belong(int x, int y) {
        return ((Math.pow((x - cx) / (double) radius, 2) + Math.pow((y - cy) / (double) radius, 2)) <= 1);
    }

    @Override
    public void moove(int dx, int dy) {
        cx += dx;
        cy += dy;
    }

    @Override
    public void resize(int kx, int ky) {
        radius = (int) (radius * kx);
    }

    @Override
    public String paint() {
        StringBuilder sb = new StringBuilder();
        for (int i = cy - radius; i <= cy + radius; i++) {
            for (int j = cx - radius; j <= cx + radius; j++) {
                if (belong(j, i)) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Cercle:" + cx + "," + cy + "," + radius;
    }

    public static Circle parse(String str) {
        String[] parts = str.split(":")[1].split(",");
        int cx = Integer.parseInt(parts[0]);
        int cy = Integer.parseInt(parts[1]);
        int radius = Integer.parseInt(parts[2]);
        return new Circle(cx, cy, radius);
    }
}
