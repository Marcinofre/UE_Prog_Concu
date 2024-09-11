package pc;

public class MatriceEntiere {
	
	private int[][] matrice;

	public MatriceEntiere(int lig, int col) {
		//Peut-être qu'il faudra tester si l'utilisateur ne mets pas de valeur comme 0 ou négatif
		this.matrice = new int[lig][col];
	}
	
	public int getElem(int lig, int col) {
		return this.matrice[lig][col];
	}
	
	public void setElem(int lig, int col, int val) {
		this.matrice[lig][col] = val;
	}
	
	public int nbLignes() {
		return this.matrice.length;
	}
	
	public int nbColonnes() {
		return (this.matrice.length != 0) ? this.matrice[0].length : 0;
	}
	
	
	
	
	
	
	
	
}
