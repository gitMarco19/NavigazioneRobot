/**
 * La <i>classe</i> {@code Main} contiene il metodo <i>main</i>.
 * 
 * @author Marco Scanu
 */
public class Main {
	
	/**
	 * Questo metodo esegue la <i>navigazione del robot</i>.
	 * 
	 * @param args
     *            vettore {@code String} che indica i parametri passati 
     *            tramite la linea di comando.
	 */
	public static void main(String[] args) {
		if (args.length == 3) {
			int dimensione = 0;
			dimensione = Integer.parseInt(args[0]);
			if (dimensione <= 0) {
				try {
					throw new Exception("La dimensione della griglia "
							+ "deve essere maggiore di zero.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			double ostacoli = 0.0;
			ostacoli = Double.parseDouble(args[1]);
			if (ostacoli <= 0 || ostacoli >= 1) {
				try {
					throw new  Exception("La densità degli ostacoli " 
							+ "deve essere compresa tra 0 e 1 estremi esclusi.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	
			long seme = 0;
			seme = Long.parseLong(args[2]);
			if (seme <= 0) {
				try {
					throw new Exception("Il seme deve essere maggiore di zero.");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			Robot aRobot = new Robot(dimensione, ostacoli, seme);
			
			if (aRobot.trovaPercorso())
				System.out.println("Percorso: " + aRobot.getPercorsoToString());
			else
				System.out.println("Nessun Percorso!");
			
		} else {
			try {
				throw new Exception("Parametri insufficienti");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
}
