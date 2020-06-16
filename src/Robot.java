import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La <i>classe</i> {@code Robot} rappresenta un <i>robot</i> che può spostarsi 
 * nel <i>mondo artificiale</i> ad esso associato.
 * 
 * @author Marco Scanu
 */
public class Robot {
	
	/**
	 * Questa istanza della classe {@code Mondo} genera un <i>mondo artificiale</i>
	 * composto da una griglia di celle all'interno della quale viene simulata la
	 * <i>navigazione di un robot</i>.
	 */
	private Mondo aWorld;
	
	/**
	 * Questa istanza della classe {@code Cella} indica la posizione attuale del <i>robot</i>.
	 */
	private Cella aPosition;
	
	/**
	 * {@code ArrayList<Cella>} che indica il percorso compiuto dal <i>robot</i>.
	 */
	private ArrayList<Cella> percorso;
	
	/**
	 * Inizializza un nuovo {@code Robot} e il <i>mondo artificiale</i> a cui è associato 
	 * tramite i valori passati come parametro della <i>dimensione</i> della griglia, 
	 * numero maggiore di zero, la <i>densità degli ostacoli</i>, numero compreso tra zero e uno, 
	 * e il <i>seme</i>, numero maggiore di zero.
	 * 
	 * @param dimensione
	 *            valore {@code int} che indica la dimensione della griglia.
	 * @param ostacoli
	 *            valore {@code double} che indica la densità degli ostacoli.
	 * @param seme
	 *            valore {@code long} per la generazione casuale degli ostacoli.
	 */
	public Robot(int dimensione, double ostacoli, long seme) {
		this.aWorld = new Mondo(dimensione, ostacoli, seme);
		this.aPosition = new Cella(this.aWorld.getCurrentCell());
		this.percorso = new ArrayList<Cella>(0);
	}
	
	/**
	 * Questo metodo restituisce un oggetto di tipo {@code Cella} 
	 * che indica la posizione attuale del <i>robot</i>.
	 * 
	 * @return la posizione attuale del <i>robot</i>.
	 */
	public Cella getPosizione() {
		return new Cella(this.aPosition);
	}
	
	/**
	 * Questo metodo restituisce un <i>ArrayList<Cella></i> che indica 
	 * il percorso compiuto dal <i>robot</i> per arrivare all'obiettivo.
	 * 
	 * @return il percorso del <i>robot</i>.
	 */
	public ArrayList<Cella> getPercorso() {
		ArrayList<Cella> aPath = new ArrayList<Cella>(0);
		aPath.addAll(this.percorso);
		return aPath;
	}
	
	/**
	 * Questo metodo permette lo spostamento del <i>robot</i>. Restituisce un
	 * {@code boolean} che indica se il <i>robot</i> si è spostato o no.
	 * 
	 * @param direzione
	 *            indica la direzione in cui si deve spostare il <i>robot</i>.
	 * 
	 * @return <i>true</i> se il robot si è spostato, altrimenti <i>false</i>.
	 */
	public boolean spostaRobot(GridWorld.Direction direzione) {
		if (this.aWorld.moveToAdjacentCell(direzione)) {
			this.aPosition = new Cella(this.aWorld.getCurrentCell());
			return true;
		}
		return false;
	}
	
	/**
	 * Questo metodo restituisce una stringa che rappresenta il <i>percorso</i> 
	 * costituito dalle coordinate di ogni cella attraversata dal <i>robot</i> 
	 * per arrivare all'obiettivo.
	 * Se il <i>robot</i> non si è spostato restituisce la stringa vuota.
	 * 
	 * @return il percorso eseguito dal <i>robot</i>.
	 */
	public String getPercorsoToString() {
		String percorso = "";
		for (Cella cella : this.percorso)
			percorso += cella.toString() + " ";
		return percorso;
	}
	
	/**
	 * Questo metodo, restituisce un valore {@code boolean} per sapere se il
	 * percorso è stato trovato oppure no.
	 * 
	 * @return <i>true</i> se il percorso è stato trovato, <i>false</i> altrimenti.
	 */
	public boolean trovaPercorso() {
		if (this.aWorld.visitaMondo())
			return (this.costruisciPercorso(this.aWorld.getMatrixWorld(), 
						this.aWorld.getCellaFinale()));
		else
			return false;
	}
	
	/**
	 * Questo metodo, restituisce un valore {@code boolean} e ha il compito di
	 * ricostruire il percorso minimo, a ritroso, partendo dalla cella obiettivo
	 * fino a quella iniziale.
	 * 
	 * @param matrixWorld
	 *            griglia che rappresenta il <i>mondo artificiale</i>.
	 * @param finale
	 *            {@code Cella} obiettivo.
	 * 
	 * @return <i>true</i> se il percorso costruito è minimo, <i>false</i>
	 *         altrimenti.
	 */
	private boolean costruisciPercorso(Cella[][] matrixWorld, 
			Cella finale) {
	    int riga = finale.getRiga();
	    int colonna = finale.getColonna();
	    
	    if (finale.equals(matrixWorld[0][0])) {
	    	this.percorso.add(finale);
	    	if (this.aWorld.targetReached())
				return true;
	    } else {
	    	do {
	    		this.percorso.add(0, new Cella(riga, colonna));
		    	int tmpR = matrixWorld[riga][colonna].getComeFrom().getRiga();
		    	int tmpC = matrixWorld[riga][colonna].getComeFrom().getColonna();
		        riga = tmpR;
		        colonna = tmpC;
		    } while (!this.percorso.get(0).equals(new Cella(0, 0)));
	    
		    for (Cella tmp : this.percorso) {
				if (tmp.isNord(this.aPosition))
					this.spostaRobot(GridWorld.Direction.NORTH);
				else if (tmp.isSud(this.aPosition))
			    	this.spostaRobot(GridWorld.Direction.SOUTH);
			    else if (tmp.isEst(this.aPosition))
			    	this.spostaRobot(GridWorld.Direction.EAST);
			    else if (tmp.isOvest(this.aPosition))
			    	this.spostaRobot(GridWorld.Direction.WEST);
			}
		
			if (this.aWorld.targetReached()) {
				return (this.percorso.size() == 
						(this.aWorld.getMinimumDistanceToTarget() + 1));
			}
	    }
		return false;
	}
}
