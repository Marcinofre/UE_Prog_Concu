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
	 * 
	 * @param b une matrice
	 * @return
	 * @throws TaillesNonConcordantesException
	 */
	public MatriceEntiere ajoute(MatriceEntiere b) throws TaillesNonConcordantesException {
		
		// Si la taille des matrice ne concorde pas alors on lève une exception
		if( (this.nbLignes() != b.nbLignes()) || (this.nbColonnes() != b.nbColonnes()) ) {
			throw new TaillesNonConcordantesException("Les tailles des matrices sont inégales");
		}
		
		// On instancie une nouvelle MatriceEntiere qui contiendra le résultat
		MatriceEntiere res = new MatriceEntiere(this.nbLignes(), b.nbColonnes());
		
		// On procède à l'addition des deux matrices
		for(int i = 0; i < res.nbLignes(); i++ ) {
			for (int j = 0; j < res.nbColonnes() ; j++) {
				int value = this.getElem(i, j) + b.getElem(i, j);
				res.setElem(i, j, value);
			} 
		}
		return res;
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 * @throws TaillesNonConcordantesException
	 */
	public MatriceEntiere produit(MatriceEntiere b) throws TaillesNonConcordantesException {
		
		if (this.nbColonnes() != b.nbLignes()) {
			throw new TaillesNonConcordantesException("La taille de la colonne de la matrice n'est pas égale à la taille de la ligne de la seconde matrice");
		}
		
		MatriceEntiere res = new MatriceEntiere(this.nbLignes(), b.nbColonnes());
		
		for(int i = 0; i < res.nbLignes(); i++) {
			for (int j = 0; j < res.nbColonnes(); j++) {
				
				// On somme a(i,k)*b(k,j)
				int val = 0;
				for (int k = 0; k < this.nbColonnes(); k++)
					val += this.getElem(i, k) * b.getElem(k, j);
				
				// On insere la nouvelle valeur dans la case approprié
				res.setElem(i, j, val);
			}
		}
		return res;
	}
	
	public MatriceEntiere transposee() {
		
		// On intervertie les colonnes et les lignes de la matrice initiale dans la nouvelle matrice
		MatriceEntiere res = new MatriceEntiere(this.nbColonnes(), this.nbLignes());
		
		//On copie le contenue a(i,j) dans b(j,i)
		for (int i = 0; i < this.nbLignes(); i++)
			for (int j = 0; j < this.nbColonnes(); j++)
				res.setElem(j, i, this.getElem(i, j));
		
		return res;
	}
	
	public MatriceEntiere produitParScalaire(int scalaire) {
		
		// Instanciation de la matrice résultante
		MatriceEntiere res = new MatriceEntiere(this.nbLignes(), this.nbColonnes());  
		
		// On multiplie chacune des cases de la matrice par un scalaire
		for (int i = 0; i < this.nbLignes(); i++)
			for (int j = 0; j < this.nbColonnes(); j++)
				res.setElem(i, j, this.getElem(i, j) * scalaire);
		
		return res;
	}
	
	@Override
	public String toString() {
		String dimension = "" + this.nbLignes() + "\n" + this.nbColonnes();
		String matrice = dimension;
		
		for (int[] i : this.matrice) {
			matrice += "\n";
			
			for(int j : i)
				matrice += j + " ";
		}
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

	
	public char[] toTest() {
		return "leader";
	}

	
	
	
	
	
	
	
}
