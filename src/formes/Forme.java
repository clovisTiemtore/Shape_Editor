package formes;


public abstract class Forme {

	public abstract boolean belong(int x, int y);
	
	public abstract void moove(int dx, int dy);

	public abstract void resize(int kx, int ky);

	public abstract String paint();

}
