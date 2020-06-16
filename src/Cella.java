import java.util.ArrayList;

/**
 * La <i>classe</i> {@code Cella} rappresenta 
 * una singola cella del <i>mondo artificiale</i>.
 * 
 * @author Marco Scanu
 */
public class Cella {
	
	/**
	 * Questo valore {@code int} indica la riga della cella.
	 */
	private int riga;
	
	/**
	 * Questo valore {@code int} indica la colonna della cella.
	 */
	private int colonna;
	
	/**
	 * Questo valore {@code TipoCella} indica lo stato in cui 
	 * si trova attualmente la cella.
	 */
	private TipoCella tipo;
	
	/**
	 * Questa istanza della classe {@code Cella} indica 
	 * la cella in cui si trovava il <i>robot</i> prima di quella attuale.
	 */
	private Cella comeFrom;
	
	/**
	 * Questo {@code ArrayList} di celle, indice le celle adiacenti 
	 * alla posizione attuale del robot.
	 */
	private ArrayList<Cella> celleAdiacenti;
	
	/**
	 * Inizializza una nuova {@code Cella}, impostando i valori della <i>riga</i> e
	 * della <i>colonna</i> uguali a quelli passati come parametro e il <i>tipo</i>
	 * uguale a <i>VISITATA</i>.
	 * 
	 * @param riga
	 *            valore {@code int} della riga.
	 * @param colonna
	 *            valore {@code int} della cella.
	 */
	public Cella(int riga, int colonna) {
		this.setRiga(riga);
		this.setColonna(colonna);
		this.setTipo(TipoCella.VISITATA);
		this.setCelleAdiacenti(new ArrayList<Cella>(0));
	}
	
	/**
	 * Inizializza una nuova {@code Cella}, impostando i valori uguali ai campi
	 * della cella passata come parametro.
	 * 
	 * @param posizione
	 *            {@code Cella} da cui impostare i valori.
	 */
	public Cella(Cella posizione) {
		this.setRiga(posizione.getRiga());
		this.setColonna(posizione.getColonna());
		this.setTipo(posizione.getTipo());
		celleAdiacenti = new ArrayList<Cella>(0);
		this.setCelleAdiacenti(posizione.getCelleAdiacenti());
	}
	
	/**
	 * Inizializza una nuova {@code Cella}, impostando i valori della <i>riga</i> e
	 * della <i>colonna</i> uguali a quelli della {@code GridWorld.Coordinate} 
	 * passata come parametro e il <i>tipo</i> uguale a <i>VISITATA</i>.
	 * 
	 * @param posizione
	 *            {@code GridWorld.Coordinate} da cui impostare i valori.
	 */
	public Cella(GridWorld.Coordinate posizione) {
		this.setRiga(posizione.row);
		this.setColonna(posizione.col);
		this.setTipo(TipoCella.VISITATA);
		this.setCelleAdiacenti(new ArrayList<Cella>(0));
	}
	
	/**
	 * Questo metodo, imposta il valore della <i>riga</i> della {@code Cella}
	 * uguale a quello passato come parametro.
	 * 
	 * @param riga
	 *            valore {@code int} della riga.
	 */
	public void setRiga(int riga) {
		this.riga = riga;
	}
	
	/**
	 * Questo metodo, restituisce il valore della <i>riga</i> della {@code Cella}.
	 * 
	 * @return valore {@code int} della riga.
	 */
	public int getRiga() {
		return this.riga;
	}
	
	/**
	 * Questo metodo, imposta il valore della <i>colonna</i> della {@code Cella} 
	 * uguale a quello passato come parametro.
	 * 
	 * @param colonna
	 *            valore {@code int} della colonna.
	 */
	public void setColonna(int colonna) {
		this.colonna = colonna;
	}
	
	/**
	 * Questo metodo, restituisce il valore della <i>colonna</i> della {@code Cella}.
	 * 
	 * @return valore {@code int} della colonna.
	 */
	public int getColonna() {
		return this.colonna;
	}
	
	/**
	 * Questo metodo, imposta il valore del <i>tipo</i> associato alla
	 * {@code Cella} uguale a quello passato come parametro.
	 * 
	 * @param tipo
	 *            valore di {@code TipoCella}
	 */
	public void setTipo(TipoCella tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Questo metodo, restituisce il valore del <i>tipo</i> 
	 * associato alla {@code Cella}.
	 * 
	 * @return valore di {@code TipoCella}
	 */
	public TipoCella getTipo() {
		return this.tipo;
	}
	
	/**
	 * Questo metodo, imposta il valore della {@code Cella} da cui è arrivato il
	 * <i>robot</i>, uguale a quella passata come parametro.
	 * 
	 * @param comeFrom
	 *            {@code Cella} di provenienza.
	 */
	public void setComeFrom(Cella comeFrom) {
		this.comeFrom = new Cella(comeFrom);
	}
	
	/**
	 * Questo metodo, restituisce la {@code Cella} di provenienza del <i>robot</i>.
	 * 
	 * @return {@code Cella} di provenienza.
	 */
	public Cella getComeFrom() {
		Cella aPosition = new Cella(this.comeFrom);
		return aPosition;
	}
	
	/**
	 * Questo metodo, inizializza un {@code ArrayList<Cella>} che conterrà le celle
	 * adiacenti alla {@code Cella} in questione, passate come parametro.
	 * 
	 * @param anArray
	 *            {@code ArrayList<Cella>} contenente le celle adiacenti.
	 */
	public void setCelleAdiacenti(ArrayList<Cella> anArray) {
		this.celleAdiacenti = new ArrayList<Cella>(0);
		this.celleAdiacenti.addAll(anArray);
	}
	
	/**
	 * Questo metodo, restituisce un {@code ArrayList<Cella>} contenente 
	 * le celle adiacenti alla {@code Cella} in questione.
	 * 
	 * @return l'ArrayList delle celle adiacenti.
	 */
	public ArrayList<Cella> getCelleAdiacenti() {
		ArrayList<Cella> anArray = new ArrayList<Cella>(0);
		anArray.addAll(this.celleAdiacenti);
		return anArray;
	}
	
	/**
	 * Questo metodo, indica se la {@code Cella} passata come parametro 
	 * si trova a <i>nord</i> rispetto ad un altra {@code Cella}.
	 * 
	 * @param aPosition
	 *            {@code Cella} da confrontare.
	 *            
	 * @return <i>true</i> se si trova a ovest, <i>false</i> altrimenti.
	 */
	public boolean isNord(Cella aPosition) {
		return (this.getRiga() < aPosition.getRiga());
	}
	
	/**
	 * Questo metodo, indica se la {@code Cella} passata come parametro 
	 * si trova a <i>sud</i> rispetto ad un altra {@code Cella}.
	 * 
	 * @param aPosition
	 *            {@code Cella} da confrontare.
	 *            
	 * @return <i>true</i> se si trova a ovest, <i>false</i> altrimenti.
	 */
	public boolean isSud(Cella aPosition) {
		return (this.getRiga() > aPosition.getRiga());
	}
	
	/**
	 * Questo metodo, indica se la {@code Cella} passata come parametro 
	 * si trova a <i>est</i> rispetto ad un altra {@code Cella}.
	 * 
	 * @param aPosition
	 *            {@code Cella} da confrontare.
	 *            
	 * @return <i>true</i> se si trova a est, <i>false</i> altrimenti.
	 */
	public boolean isEst(Cella aPosition) {
		return (this.getColonna() > aPosition.getColonna());
	}
	
	/**
	 * Questo metodo, indica se la {@code Cella} passata come parametro 
	 * si trova a <i>ovest</i> rispetto ad un altra {@code Cella}.
	 * 
	 * @param aPosition
	 *            {@code Cella} da confrontare.
	 *            
	 * @return <i>true</i> se si trova a ovest, <i>false</i> altrimenti.
	 */
	public boolean isOvest(Cella aPosition) {
		return (this.getColonna() < aPosition.getColonna());
	}
	
	/**
	 * Questo metodo serve per confrontare se due celle 
	 * hanno le stesse coordinate.
	 * 
	 * @param anObject
	 *            L'oggetto con cui confrontare la {@code Cella}.
	 *
	 * @return <i>true</i> se la riga e la colonna corrispondono, altrimenti
	 *         <i>false</i>.
	 */
	@Override
	public boolean equals(Object anObject) {
		if (anObject == null || this == null)
			return false;
		Cella aPosition = (Cella) anObject;
		if (this.getRiga() == aPosition.getRiga() 
				&& this.getColonna() == aPosition.getColonna())
			return true;
		else
			return false;
	}
	
	/**
	 * Questo metodo restituisce la {@code Cella} sottoforma di {@code String}.
	 * 
	 * @return la stringa che rappresenta la {@code Cella}
	 */
	@Override
	public String toString() {
		return "(" + this.getRiga() + ", " + this.getColonna() + ")";
	}
}
