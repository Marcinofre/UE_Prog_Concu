package pc;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class MatriceEntiere {
	
	private int[][] matrice;

	public MatriceEntiere(int lig, int col) {
		this.matrice = new int[lig][col];
	}
	
	/**
	 * Récupère la valeur d'un élément de la matrice aux coordonnées [`lig`, `col`] 
	 * @param lig
	 * @param col
	 * @return un entier
	 */
	public int getElem(int lig, int col) {
		return this.matrice[lig][col];
	}
	
	/**
	 * Insere une valeur `val` dans la matrice aux coordonnées [`lig`, `col`] 
	 * @param lig
	 * @param col
	 * @param val
	 */
	public void setElem(int lig, int col, int val) {
		this.matrice[lig][col] = val;
	}
	
	/**
	 * Renvoie le nombre de ligne de la matrice
	 * @return un entier
	 */
	public int nbLignes() {
		return this.matrice.length;
	}
	
	/**
	 * Renvoie le nombre de colonne de la matrice
	 * @return un entier
	 */
	public int nbColonnes() {
		return (this.matrice.length != 0) ? this.matrice[0].length : 0;
	}
	
	/**
	 * 
	 * @param fichier
	 * @return
	 * @throws IOException
	 */
	//Pas fini a revoir implementation -0_0-
	public static MatriceEntiere parseMatrix(File fichier) throws IOException {
		
		// Matrice à retourner
		MatriceEntiere mat = null;
		// ArrayList qui stock les valeurs entières contenue dans le fichier
		ArrayList<Integer> data = new ArrayList<>();
		try {
			// On initialise le scanner pour lire le fichier
			Scanner scanner = new Scanner(fichier);
			
			// On lit les deux première ligne pour récupérer les dimension de la matrice
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
			mat = new MatriceEntiere(lig,col);
			
			// On itère sur les élément du fichier en les ajoutant à data
			while(scanner.hasNextInt()) {
				data.add(scanner.nextInt());
			}
			// On ferme le fichier en lecture
			scanner.close();
			
			// On peuple la matrice
			for (int i = 0; i < mat.nbLignes(); i++)
				for (int j = 0; j < mat.nbColonnes(); j++)
					mat.setElem(i, j, data.removeFirst());
				
		} catch (IOException e){
			throw e;
		}
		return mat;
	}
	
	/**
	 * @param a
	 * @param b
	 * @return
	 * @throws TaillesNonConcordantesException
	 */
	public static MatriceEntiere ajoute(MatriceEntiere a, MatriceEntiere b) throws TaillesNonConcordantesException {
		
		// Si la taille des matrice ne concorde pas alors on lève une exception
		if( (a.nbLignes() != b.nbLignes()) || (a.nbColonnes() != b.nbColonnes()) ) {
			throw new TaillesNonConcordantesException("Les tailles des matrices sont inégales");
		}
		
		MatriceEntiere res = new MatriceEntiere(a.nbLignes(), b.nbColonnes());
		for(int[] ligne : matrice) {
			for (int i = 0; i < ligne.length; i++)
				res.
		}
	
			
		
		
		
		return res;
	}
	
	@Override
	public String toString() {
		String dimension = "" + this.nbLignes() + "\n" + this.nbColonnes();
		String matrice = "\n" + dimension;
		for (int[] i : this.matrice)
			for(int j : i)
				matrice += j + " ";
		return matrice; 
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof MatriceEntiere))
			return false;
		MatriceEntiere other = (MatriceEntiere) obj;
		return Arrays.deepEquals(this.matrice, other.matrice);
	}
	
	
	
	
	
	
}
