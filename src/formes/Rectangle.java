package formes;

public class Rectangle extends Forme {
    private int x1, y1, x2, y2;

    public Rectangle(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    @Override
    public boolean belong(int x, int y) {
        return (x >= x1 && x <= x2 && y >= y1 && y <= y2);
    }

    @Override
    public void moove(int dx, int dy) {
        x1 += dx;
        y1 += dy;
        x2 += dx;
        y2 += dy;
    }

    @Override
    public void resize(int kx, int ky) {
        x2 = (int) (x1 + (x2 - x1) * kx);
        y2 = (int) (y1 + (y2 - y1) * ky);
    }

    @Override
    public String paint() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < y2; i++) {
            for (int j = 0; j < x2; j++) {
                if (i >= y1 && i <= y2 && j >= x1 && j <= x2) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }sb.append("\n");
        }return sb.toString();
    }
}