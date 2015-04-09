
public class Chrono
{
	private long startTime;
	
	/**
	 * demarre le chrono (enregistre la date systeme en ms)
	 */
	public void startChrono()
	{
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * retourne le temps actuel du chrono (date systeme - date de demarrage en ms)
	 * @return temps du chrono en millisecondes
	 */
	public long getTime()
	{
		return System.currentTimeMillis() - startTime;
	}
}
