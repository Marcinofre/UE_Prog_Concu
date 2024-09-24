package pc.countwords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordCount implements Runnable {

	// Attribut 
	private String fichier;
	private int total = 0;
	
	
	
	
	/**
	 * Constructeur instanciant un objet WordCount
	 * @param fichier Un nom de fichier
	 */
	public WordCount(String fichier) {
		this.fichier = fichier;
	}
	
	/**
	 * Compte les mots d'un fichier
	 * @param filename un fichier
	 * @return
	 * @throws IOException Si le fichier n'existe pas ou n'as pas pu etre ouvert
	 */
	public void countWords(String filename) throws IOException {
		
		// Lance le time
		long startTime = System.currentTimeMillis();
		
		// Lit le fichier
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			int total = 0;
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				total += line.split("\\s+").length;
			}
			System.out.println("Time for file "+filename+" : "+(System.currentTimeMillis()-startTime) + " ms for "+ total + " words");
			this.total = total;
		}
	} 
	
	
	
	
	@Override
	public void run(){
		
		try {
			this.countWords(this.fichier);
		} catch (IOException e) {
			System.out.println("Erreur dans la lecture du fichier " + this.fichier);
			e.printStackTrace();
		}
	}

	
	
	
	public static void main(String[] args) {
		
		// Start le timer
		long startTime = System.currentTimeMillis();
		
		// Instancie le tableau resultat
		int [] wordCount = new int[args.length];
		
		// Instanciation des Runnables et des threads
		List<Runnable> taches = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();
		
		// Instanction des Runnables
		for (int i = 0; i < args.length; i++) {
			taches.add((Runnable) new WordCount(args[i])); 
		}
		
		
		// Instanciation des Rubnnales en Thread
		for (Runnable i: taches) {
			threads.add(new Thread(i));
			/*
			try {
				tache.join();
			} catch (InterruptedException e) {
				System.out.println("------------- °՞(ᗒᗣᗕ)՞° -------------");
				e.printStackTrace();
			}*/	
		}
		
		// On démarre les threads
		for (Thread thread: threads) {
			thread.start();
		}
		
		boolean enCours = true;
		int lastAlive = 0;
		while(enCours) {
			for (int i = lastAlive; i < threads.size(); i++) {
				if (threads.get(i).isAlive()) {
					enCours = true;
					break;
				}else {
					enCours = false;
					lastAlive++;
				}
			}
		}
			
			
		
		int total = 0;
		int i = 0;
		for (Runnable count : taches ) {
			total += ((WordCount)count).total;
			wordCount[i++] = ((WordCount)count).total;
		}
		
		System.out.println("Word count:" + Arrays.toString(wordCount));
		System.out.println("Total word count:" + total);
		System.out.println("Total time "+(System.currentTimeMillis()-startTime) + " ms");
	}
}
