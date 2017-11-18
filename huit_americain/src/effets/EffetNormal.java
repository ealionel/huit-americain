package effets;

/**
 * Effet standard d'une carte sans effet. Fait passer au tour suivant.
 * @author Lionel EA
 *
 */

public class EffetNormal implements Effet{
	public void appliquer() {
		System.out.println("EFFET NORMAL");
	}
}
