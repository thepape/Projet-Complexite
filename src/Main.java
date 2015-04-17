import java.util.Scanner;


public class Main {
	
	/**
	 * progression de l'estimation de temps en pourcent
	 */
	public static int progression=-1;
	
	/**
	 * incremente et affiche la progression d'un algorithme iteratif
	 * @param i iteration en cours
	 * @param n nombre total d'iterations prevues
	 */
	public static void afficherProgression(String entete, int i, int n)
	{
		int pourcent = Math.round(((float) i / (float) n)*100);
		
		if(pourcent > Main.progression)
		{
			Main.progression = pourcent;
			System.out.println(entete+" "+pourcent+"%");
		}
	}
	
	/**
	 * algorithme de detection d'intersection "ToutesLespaires"
	 * @param t tableau de rectangles
	 * @return nombres de paires de rectangles differentes qui se coupent
	 */
	public static int ToutesLesPaires(Rectangle[] t)
	{
		//int n = 0;
		int k = 0;
		
		for(int i = 0; i < t.length; i++)
		{
			for(int j = i+1; j < t.length; j++)
			{
				boolean interX = ((t[i].getX1() > t[j].getX1() && t[i].getX1() < t[j].getX2()) || (t[j].getX1() > t[i].getX1() && t[j].getX1() < t[i].getX2()));
				boolean interY = ((t[i].getY1() > t[j].getY1() && t[i].getY1() < t[j].getY2()) || (t[j].getY1() > t[i].getY1() && t[j].getY1() < t[i].getY2()));
				
				if(interX && interY)
				{
					k++;
					//System.out.println(t[i].getId()+"-"+t[j].getId());
					
				}
				//n++;
			}
			
		}
		//System.out.println(n);
		return k;
	}
	
	/**
	 * algorithme de detection d'intersection "ToutesLespaires" avec un affichage de progression en pourcent
	 * @param t tableau de rectangles
	 * @return nombres de paires de rectangles differentes qui se coupent
	 */
	public static int ToutesLesPairesAvecProgression(Rectangle[] t)
	{
		//int n = 0;
		int k = 0;
		
		for(int i = 0; i < t.length; i++)
		{
			Main.afficherProgression("TouteslesPaires :",i, t.length);
			for(int j = i+1; j < t.length; j++)
			{
				boolean interX = ((t[i].getX1() > t[j].getX1() && t[i].getX1() < t[j].getX2()) || (t[j].getX1() > t[i].getX1() && t[j].getX1() < t[i].getX2()));
				boolean interY = ((t[i].getY1() > t[j].getY1() && t[i].getY1() < t[j].getY2()) || (t[j].getY1() > t[i].getY1() && t[j].getY1() < t[i].getY2()));
				
				if(interX && interY)
				{
					k++;
					//System.out.println(t[i].getId()+"-"+t[j].getId());
					
				}
				//n++;
			}
			//System.out.println("touteslespaires:"+(float) i / t.length * 100+" %");
			
		}
		Main.afficherProgression("TouteslesPaires :",t.length, t.length);
		//System.out.println(n);
		return k;
	}
	
	/**
	 * fonction de tri d'un tableau de rectangles en fonction de x1
	 * @param t tableau de rectangles
	 * @param premier premier element de l'intervale de tri (0 au debut)
	 * @param dernier dernier element de l'intervale de tri (t.length-1 au debut)
	 */
	public static void TriRapide(Rectangle[] t,int premier, int dernier)
	{
		if(premier < dernier)
		{
			int pivot = (int) Math.round(Math.random()*(dernier-premier)) + premier;
			pivot = Partitionner(t, premier, dernier, pivot);
			TriRapide(t, premier, pivot-1);
			TriRapide(t, pivot+1, dernier);
		}
	}
	
	/**
	 * parcours un tableau de rectangles et imprime leurs ID
	 * @param t tableau de rectangles
	 */
	public static void parcourirTableau(Rectangle[] t)
	{
		for(int i = 0; i < t.length;i++)
		{
			System.out.println(t[i].getId());
		}
	}
	
	private static int Partitionner(Rectangle[] t, int premier, int dernier, int pivot)
	{
		//echanger t[pivot] et t[premier]
		Rectangle tmp = t[pivot];
		t[pivot] = t[premier];
		t[premier] = tmp;
		
		int j = premier;
		
		for(int i=premier; i<dernier; i++)
		{
			if(t[i].getX1() <= t[dernier].getX1())
			{
				//echanger t[i] et t[j]
				Rectangle tmp2 = t[i];
				t[i] = t[j];
				t[j] = tmp2;
				j=j+1;
			}
		}
		
		//echanger t[dernier] et t[j]
		Rectangle tmp3 = t[dernier];
		t[dernier] = t[j];
		t[j] = tmp3;
		
		return j;
	}
	
	/**
	 * algorithme de detection d'intersections "Balayage"
	 * @param t tableau de rectangles tries sur x1
	 * @return nombre de paires de rectangles differentes qui se coupent
	 */
	public static int Balayage(Rectangle[] t)
	{
		int k = 0;
		TriRapide(t, 0, t.length-1);
		
		//pour chaque rectangle R dans la liste triee
		for(int i=0; i < t.length; i++)
		{
			int j=i+1;
			
			//on cherche le premier rectangle D qui ne coupe pas R en x
			while(j < t.length && (t[j].getX1() < t[i].getX2()))
			{
				boolean interY = ((t[i].getY1() > t[j].getY1() && t[i].getY1() < t[j].getY2()) || (t[j].getY1() > t[i].getY1() && t[j].getY1() < t[i].getY2()));
				
				if(interY)
				{
					k++;
					//System.out.println(t[i].getId()+"-"+t[l].getId());
					
				}
				
				j=j+1;
			}
		}
		
		return k;
	}
	
	public static int BalayageAvecProgression(Rectangle[] t)
	{
		int k = 0;
		TriRapide(t, 0, t.length-1);
		
		//pour chaque rectangle R dans la liste triee
		for(int i=0; i < t.length; i++)
		{
			Main.afficherProgression("Balayage :",i, t.length);
			int j=i+1;
			
			//on cherche le premier rectangle D qui ne coupe pas R en x
			while(j < t.length && (t[j].getX1() < t[i].getX2()))
			{
				boolean interY = ((t[i].getY1() > t[j].getY1() && t[i].getY1() < t[j].getY2()) || (t[j].getY1() > t[i].getY1() && t[j].getY1() < t[i].getY2()));
				
				if(interY)
				{
					k++;
					//System.out.println(t[i].getId()+"-"+t[l].getId());
					
				}
				
				j=j+1;
			}
			//System.out.println("balayage:"+(float) i / t.length * 100 +" %");
			
		}
		Main.afficherProgression("Balayage :",t.length, t.length);
		return k;
	}
	
	/**
	 * genere un jeu de test de n rectangles aux coordonnees aleatoirement choisie sur l'intervale [0,n]
	 * @param n nombre de rectangles a generer
	 * @return tableau de rectangles
	 */
	public static Rectangle[] genererJeu1(int n)
	{
		Rectangle[] res = new Rectangle[n];
		
		for(int i =0; i <n; i++)
		{
			int x1 = (int) Math.round(Math.random()*n);
			int y1 = (int) Math.round(Math.random()*n);
			
			int x2 = (int) Math.round(Math.random()*n);
			int y2 = (int) Math.round(Math.random()*n);
			
			res[i] = new Rectangle(x1,y1,x2,y2);
		}
		
		return res;
		
	}
	
	/**
	 * genere un jeu de test de n rectangles de longeurs (1,1) et aux coordonnees (x1,y1) aleatoirement choisie sur l'intervale [0,n]
	 * @param n nombre de rectangles a generer
	 * @return tableau de rectangles
	 */
	public static Rectangle[] genererJeu2(int n)
	{
		Rectangle[] res = new Rectangle[n];
		
		for(int i =0; i <n; i++)
		{
			int x1 = (int) Math.round(Math.random()*n);
			int y1 = (int) Math.round(Math.random()*n);
			
			int x2 = x1+1;
			int y2 = y1+1;
			
			res[i] = new Rectangle(x1,y1,x2,y2);
		}
		
		return res;
		
	}
	
	/**
	 * genere un jeu de test de n rectangles aux coordonnees aleatoirement choisie sur l'intervale [0,racine de n]
	 * @param n nombre de rectangles a generer
	 * @return tableau de rectangles
	 */
	public static Rectangle[] genererJeu3(int n)
	{
		Rectangle[] res = new Rectangle[n];
		
		for(int i =0; i <n; i++)
		{
			int x1 = (int) Math.round(Math.random()*Math.sqrt(n));
			int y1 = (int) Math.round(Math.random()*Math.sqrt(n));
			
			int x2 = (int) Math.round(Math.random()*Math.sqrt(n));
			int y2 = (int) Math.round(Math.random()*Math.sqrt(n));
			
			res[i] = new Rectangle(x1,y1,x2,y2);
		}
		
		return res;
		
	}
	
	/**
	 * estime le temps d'execution de l'algorithme passe en parametre
	 * @param algo algo dont le temps est a estimer (1=touteslespaires - 2=balayage)
	 * @param numjeu numero du type de jeu de test a generer (1-2-3)
	 * @param n nombre de rectangles a generer
	 * @return tableau de donnees representatif du temps en millisecondes en fonction du nombre de rectangles
	 */
	public static Tableau estimerTempsAlgo(int algo,int numjeu, int n)
	{
		if(algo < 1 || algo > 2)
		{
			return null;
		}
		
		if(numjeu < 1 || numjeu > 3)
		{
			return null;
		}
		
		int pas=1;
		
		if(n > 100)
		{
			pas = (int) Math.floor((float) n / 100);
		}
		
		long[] res = new long[n+1];
		
		for(int i=0;i< n+1;i+=pas)
		{
			Rectangle[] jeu = null;
			
			if(numjeu == 1)
				jeu = Main.genererJeu1(i);
			if(numjeu == 2)
				jeu = Main.genererJeu2(i);
			if(numjeu == 3)
				jeu = Main.genererJeu3(i);
			
			Chrono c = new Chrono();
			
			if(algo == 1)
			{
				c.startChrono();
				Main.ToutesLesPaires(jeu);
				res[i] = c.getTime();
			}
			else
			{
				c.startChrono();
				Main.Balayage(jeu);
				res[i] = c.getTime();
			}
			
			Main.afficherProgression("",i, n);
		}
		
		return new Tableau(res, pas);
	}
	
	/**
	 * estime le nombre de k paires en fonction de l'algorithme passe en parametre
	 * @param algo algo dont le nombre k est a estimer (1=touteslespaires - 2=balayage)
	 * @param numjeu numero du type de jeu de test a generer (1-2-3)
	 * @param n nombre de rectangles a generer
	 * @return tableau de donnees representatif de k en fonction du nombre de rectangles
	 */
	public static Tableau estimerNombrePaires(int algo,int numjeu, int n)
	{
		if(algo < 1 || algo > 2)
		{
			return null;
		}
		
		if(numjeu < 1 || numjeu > 3)
		{
			return null;
		}
		
		int pas=1;
		
		if(n > 100)
		{
			pas = (int) Math.floor((float) n / 100);
		}
		
		long[] res = new long[n+1];
		
		for(int i=0;i< n+1;i+=pas)
		{
			Rectangle[] jeu = null;
			
			if(numjeu == 1)
				jeu = Main.genererJeu1(i);
			if(numjeu == 2)
				jeu = Main.genererJeu2(i);
			if(numjeu == 3)
				jeu = Main.genererJeu3(i);
			
			Chrono c = new Chrono();
			
			if(algo == 1)
			{
				res[i] = Main.ToutesLesPaires(jeu);
			}
			else
			{
				res[i] = Main.Balayage(jeu);
			}
			
			Main.afficherProgression("",i, n);
		}
		
		return new Tableau(res, pas);
	}
	
	/**
	 * execute ToutesLesPaires puis Balayage pour un ensemble de n rectangles
	 * @param jeu numero du jeu de test a generer
	 * @param n nombre de rectangles
	 */
	public static void comparerTemps(int jeu, int n)
	{
		Rectangle[] tjeu = null;
		
		if(jeu == 1)
			tjeu = Main.genererJeu1(n);
		if(jeu == 2)
			tjeu = Main.genererJeu2(n);
		if(jeu == 3)
			tjeu = Main.genererJeu3(n);
		if(jeu < 1 || jeu > 3)
			return;
		
		Chrono c = new Chrono();
		c.startChrono();
		int nbp1 = Main.ToutesLesPairesAvecProgression(tjeu);
		long ttp = c.getTime();
		
		Main.progression=-1;
		
		c = new Chrono();
		c.startChrono();
		Main.BalayageAvecProgression(tjeu);
		long b = c.getTime();
		
		float nlogn = ((float) Math.log(n) / (float) Math.log(2))*(n);
		
		System.out.println("Pour n="+n+" dans le jeu "+jeu+", "+nbp1+" paires de rectangles se coupent. TEMPS: Balayage="+b+" ms; ToutesLesPaires="+ttp+" ms;n.log(n)="+nlogn+"; n^2="+Math.pow(n, 2));
	}
	
	public static void main(String[] args)
	{
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\nProjet de complexite algorithmique L3 MIAGE\nBESSON-BURTEAUX-CHABOISSIER-PAPELIER-SCHWEITZER\n\n");
		System.out.println("Vous souhaitez :\n -1 Comparer le temps d'execution entre Balayage et ToutesLesPaires pour un n"
				+ "\n -2 Obtenir le nombre de paires de rectangles qui se coupent pour un n"
				+ "\n -3 Tracer la courbe de complexité d'un algo en fonction de n (peu etre très long !)"
				+ "\n -4 Tracer la courbe du nombre de paires de rectangles qui se coupent en fonction de n");
		int choix = sc.nextInt();
		
		if(choix == 1)
		{
			System.out.println("\nChoisissez un type de generation de jeu de test : \n -1 coordonnees tirees dans [0,n]\n -2 x1,y1 dans [0,n], x2-x1=y2-y1=1\n -3 coordonnees tirees dans [0,Vn]");
			int typeJeu = sc.nextInt();
			
			if(typeJeu < 1 || typeJeu > 3)
			{
				System.out.println("Exception in thread \"main\" java.lang.ThugLifeException\n\t at Main.main(Main.java:330");
				System.exit(0);
			}
			
			System.out.println("\nChoississez le nombre n : ");
			int n = sc.nextInt();
			
			Main.comparerTemps(typeJeu, n);
		}
		else if(choix == 2)
		{
			System.out.println("Choisissez un algorithme : \n -1 ToutesLesPaires\n -2 Balayage");
			int algo = sc.nextInt();
			
			System.out.println("\nChoisissez un type de generation de jeu de test : \n -1 coordonnees tirees dans [0,n]\n -2 x1,y1 dans [0,n], x2-x1=y2-y1=1\n -3 coordonnees tirees dans [0,Vn]");
			int jeu = sc.nextInt();
			
			if(jeu < 1 || jeu > 3)
			{
				System.out.println("Exception in thread \"main\" java.lang.ThugLifeException\n\t at Main.main(Main.java:330");
				System.exit(0);
			}
			
			System.out.println("\nChoississez le nombre n : ");
			int n = sc.nextInt();
			
			Rectangle[] tjeu = null;
			
			if(jeu == 1)
				tjeu = Main.genererJeu1(n);
			if(jeu == 2)
				tjeu = Main.genererJeu2(n);
			if(jeu == 3)
				tjeu = Main.genererJeu3(n);
			if(jeu < 1 || jeu > 3)
				return;
			
			if(algo == 1)
			{
				Chrono c = new Chrono();
				c.startChrono();
				long k = Main.ToutesLesPairesAvecProgression(tjeu);
				long temps = c.getTime();
				System.out.println("Pour n="+n+" dans le jeu "+jeu+", "+k+" paires de rectangles se coupent. ALGO : ToutesLesPaires. TEMPS : "+temps+" ms");
			}
			else if(algo == 2)
			{
				Chrono c = new Chrono();
				c.startChrono();
				long k = Main.BalayageAvecProgression(tjeu);
				long temps = c.getTime();
				System.out.println("Pour n="+n+" dans le jeu "+jeu+", "+k+" paires de rectangles se coupent. ALGO : Balayage. TEMPS : "+temps+" ms");
			}
			else
			{
				System.exit(0);
			}
		}
		else if(choix == 3)
		{
			System.out.println("Choisissez un algorithme : \n -1 ToutesLesPaires\n -2 Balayage");
			int algo = sc.nextInt();
			System.out.println("\nChoisissez un type de generation de jeu de test : \n -1 coordonnees tirees dans [0,n]\n -2 x1,y1 dans [0,n], x2-x1=y2-y1=1\n -3 coordonnees tirees dans [0,Vn]");
			int typeJeu = sc.nextInt();
			System.out.println("\nChoississez le nombre n : ");
			int n = sc.nextInt();
			
			Tableau t = Main.estimerTempsAlgo(algo,typeJeu, n);
			
			String algoN = "ToutesLesPaires";
			if(algo == 2)
				algoN = "Balayage";
			
			Afficheur a = new Afficheur(t,true,"Temps de "+algoN+" en fonction de n (jeu "+typeJeu+")","t(ms)");
			
			a.show();
		}
		else if(choix == 4)
		{
			System.out.println("Choisissez un algorithme : \n -1 ToutesLesPaires\n -2 Balayage");
			int algo = sc.nextInt();
			System.out.println("\nChoisissez un type de generation de jeu de test : \n -1 coordonnees tirees dans [0,n]\n -2 x1,y1 dans [0,n], x2-x1=y2-y1=1\n -3 coordonnees tirees dans [0,Vn]");
			int typeJeu = sc.nextInt();
			System.out.println("\nChoississez le nombre n : ");
			int n = sc.nextInt();
			
			Tableau t = Main.estimerNombrePaires(algo,typeJeu, n);
			
			Afficheur a = new Afficheur(t,false,"Nombre de paires de rectangles en fonction de n (jeu "+typeJeu+")","k paires");
			
			a.show();
		}
		else
		{
			System.exit(0);
		}
	}

}
