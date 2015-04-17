
public class Tableau {
	/**
	 * tableau des donnees (int => long)
	 */
	public long[] data;
	
	/**
	 * pas d'iteration des donnees (pour les tres grands ensembles)
	 */
	public int pas;
	
	public Tableau(long[] d, int p)
	{
		data = d;
		pas = p;
	}
}
