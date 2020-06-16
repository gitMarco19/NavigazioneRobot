import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * La <i>classe</i> {@code Mondo} <i>eredita</i> dalla <i>classe</i> {@code GridWorld} 
 * perciò un'istanza di questa classe genera un <i>mondo artificiale</i> composto da 
 * una griglia di celle all'interno della quale simulare la navigazione di un <i>robot</i>.
 * 
 * @author Marco Scanu
 */
public class Mondo extends GridWorld {

	/**
	 * Questa istanza della classe {@code Cella} indica 
	 * la cella iniziale del <i>mondo artificiale</i>.
	 */
	private Cella iniziale;
	
	/**
	 * Questa istanza della classe {@code Cella} indica 
	 * la cella obiettvo del <i>mondo artificiale</i>.
	 */
	private Cella finale;
	
	/**
	 * <i>Array bidimensionale</i> di tipo {@code Cella} 
	 * che rappresenta il <i>mondo artificiale</i> sottoforma di griglia.
	 */
	private Cella[][] matrixWorld;
	
	/**
	 * Inizializza un <i>mondo artificiale</i> con la <i>dimensione</i>,
	 * numero maggiore di zero, la <i>densità degli ostacoli</i>, numero 
	 * compreso tra zero e uno, e il <i>seme</i>, numero maggiore di zero.
	 * 
	 * @param dimensione
	 *            valore {@code int} che indica la dimensione della griglia.
	 * @param ostacoli
	 *            valore {@code double} che indica la densità degli ostacoli.
	 * @param seme
	 *            valore {@code long} per la generazione casuale degli ostacoli.
	 */
	public Mondo(int dimensione, double ostacoli, long seme) {
		super(dimensione, ostacoli, seme);
		this.iniziale = new Cella(0 ,0);
		this.finale = new Cella(dimensione - 1, dimensione -1);
		this.costruisciMondo(dimensione);
	}
	
	/**
	 * Questo metodo restituisce un <i>Array bidimensionale</i> 
	 * di tipo {@code Cella} che rappresenta il <i>mondo artificiale</i>.
	 * 
	 * @return griglia del mondo artificiale.
	 */
	public Cella[][] getMatrixWorld() {
		return matrixWorld;
	}
	
	/**
	 * Questo metodo, restituisce una {@code Cella}
	 * che rappresenta la <i>cella iniziale</i>.
	 * 
	 * @return la <i>cella iniziale</i>.
	 */
	public Cella getCellaIniziale() {
		return (new Cella(this.iniziale));
	}
	
	/**
	 * Questo metodo, restituisce una {@code Cella}
	 * che rappresenta l'<i>obiettivo</i>.
	 * 
	 * @return la <i>cella obiettivo</i>.
	 */
	public Cella getCellaFinale() {
		return (new Cella(this.finale));
	}
	
	/**
	 * Questo metodo implementa un algoritmo che permette di individuare 
	 * il <i>percorso minimo</i> tra la cella iniziale e la cella obiettivo.
	 * 
	 * @return <i>true</i> se è stato individuato un percorso, <i>false</i> altrimenti.
	 */
	public boolean visitaMondo() {
		ArrayList<Cella> openSet = new ArrayList<Cella>(0);
		
	    int i = iniziale.getRiga();
	    int j = iniziale.getColonna();
	    this.matrixWorld[i][j].setComeFrom(new Cella(i, j));
	    this.matrixWorld[i][j].setTipo(TipoCella.VISITATA);
	    openSet.add(this.matrixWorld[i][j]);
	    
	    while (!openSet.isEmpty()) {
	    	Cella cellaCorrente = new Cella(openSet.get(0));
	        openSet.remove(0);  
	        
	        i = cellaCorrente.getRiga();
	        j = cellaCorrente.getColonna();
	        
	        if (cellaCorrente.equals(finale))
				return true;
	        
	        ArrayList<Cella> celleAdiacenti = 
	        		cellaCorrente.getCelleAdiacenti();
	        
	        if (i != this.iniziale.getRiga()) {
				if (this.matrixWorld[i - 1][j].getTipo() != TipoCella.BLOCCATA)
					celleAdiacenti.add(this.matrixWorld[i - 1][j]);
			}
	        if (i != finale.getRiga()) {
				if (this.matrixWorld[i + 1][j].getTipo() != TipoCella.BLOCCATA)
					celleAdiacenti.add(this.matrixWorld[i + 1][j]);
			}
			if (j != this.iniziale.getColonna()) {
				if (this.matrixWorld[i][j - 1].getTipo() != TipoCella.BLOCCATA)
					celleAdiacenti.add(this.matrixWorld[i][j - 1]);
			}
			if (j != this.finale.getColonna()) {
				if (this.matrixWorld[i][j + 1].getTipo() != TipoCella.BLOCCATA)
					celleAdiacenti.add(this.matrixWorld[i][j + 1]);
			}
			
			for (Cella vicino : celleAdiacenti) {
				if (vicino.getTipo() == TipoCella.NON_VISITATA) {
					i = vicino.getRiga();
					j = vicino.getColonna();
					vicino.setTipo(TipoCella.VISITATA);
					vicino.setComeFrom(cellaCorrente);
					this.matrixWorld[i][j] = vicino;
					
					if (vicino.equals(this.finale))
						return true;
					
					openSet.add(vicino);
				}
			}
		}
	    return false;
	}
	
	/**
	 * Questo metodo, realizza la matrice che rappresenta il <i>mondo
	 * artificiale</i> sottoforma di griglia bidimensionale.
	 * 
	 * @param dimensione
	 *            valore {@code int} che indica la dimensione della griglia.
	 */
	private void costruisciMondo(int dimensione) {
		File aFile = new File("mondo.txt");
		try {
			aFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PrintStream tmpOut = new PrintStream(System.out);
		PrintStream outFile = null;
		try {
			outFile = new PrintStream(aFile);
			System.setOut(outFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.print();
		
		System.setOut(new PrintStream(tmpOut));
		
		this.matrixWorld = new Cella[dimensione][dimensione];
		Scanner aScanner = null;
		try {
			aScanner = new Scanner(aFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int x = 0; aScanner.hasNextLine(); x++) {
			String aString = aScanner.nextLine();
			for (int i = 1; i < aString.length() -1; i++) {
				int y = (i - 1);
				this.matrixWorld[x][y] = new Cella(x, y);
				this.matrixWorld[x][y].setComeFrom(new Cella(-1, -1));
				
				if (aString.charAt(i) == ' ')
					this.matrixWorld[x][y].setTipo(TipoCella.NON_VISITATA);
				else
					this.matrixWorld[x][y].setTipo(TipoCella.BLOCCATA);
			}
		}
		aScanner.close();
		outFile.close();
		aFile.delete();
	}
}
