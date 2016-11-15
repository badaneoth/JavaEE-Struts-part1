package ili.jai.model;

/**
 * 
 * @author leberre
 *
 */
public interface Promotion extends Persistable {

	/**
	 * Retourne l'année de la promotion. Il s'agit de 
	 * l'année d'obtention du diplôme, soit 2016 pour l'année
	 * universitaire 2015/2016.
	 * 
	 * Le master ILI a été créé en 2004, il ne peut pas y avoir
	 * de promo avant 2005.
	 * 
	 * @return l'année d'obtention du diplôme.
	 */
	int getAnnee();
	
	/**
	 * Retourne le nom de la promotion, qui correspond au
	 * nom de <a href="https://wiki.eclipse.org/Simultaneous_Release">release d'Eclipse</a>.
	 * 
	 * @return 
	 */
	String getNom();
}
