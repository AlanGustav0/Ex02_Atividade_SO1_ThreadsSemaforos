package view;

import java.util.concurrent.Semaphore;

import controller.ThreadCozinha;

public class Preparo {

	public static void main(String[] args) {

		int permissoes = 5;
		Semaphore semaforo = new Semaphore(permissoes);
		

		for (int idPratos = 0; idPratos < 5; idPratos++) {
			Thread tPratos = new ThreadCozinha(idPratos,semaforo);
			tPratos.start();
		}

	}

}
