package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread {

	private int idPratos;
	private Semaphore semaforo;
	private static int percentual;

	public ThreadCozinha(int idPratos, Semaphore semaforo) {
		this.idPratos = idPratos;
		this.semaforo = semaforo;

	}

	public void run() {

		
		cozinha();
		
		
		// ---------------Inicio da Seção Crítica----------
		try {
			semaforo.acquire();
			pratosProntos();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
		
		// ---------------Fim da Seção Crítica------------

	}

	public void cozinha() {

		int tempoSopa = (int) (Math.random() * 501) + 800;
		int tempoLazanha = (int) (Math.random() * 601) + 1200;
		percentual = 0;

		if (idPratos % 2 != 0) {

			System.out.println("Prato: " + idPratos + " Sopa de Cebola - Iniciou o cozimento..." + "\n");
			while (percentual < 100) {

				double tempoInicial = System.nanoTime();
				try {
					sleep(tempoSopa);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				double tempoFinal = System.nanoTime();
				double tempoTotal = tempoFinal - tempoInicial;
				tempoTotal = tempoTotal / Math.pow(10, 9);

				percentual += (int) tempoTotal / 0.1;

				if (percentual > 100) {
					percentual = 100;
					System.out.println("Percentual de cozimento --- " + percentual + "% do prato: " + idPratos + "\n");
					break;
				}

				System.out.println("Percentual de cozimento --- " + percentual + "% do prato: " + idPratos + "\n");

			}

		} else {

			System.out.println("Prato: " + idPratos + " Lasanha a Bolonhesa - Iniciou o Cozimento..." + "\n");
			while (percentual < 100) {
				double tempoInicial = System.nanoTime();
				try {
					sleep(tempoLazanha);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				double tempoFinal = System.nanoTime();
				double tempoTotal = tempoFinal - tempoInicial;

				tempoTotal = tempoTotal / Math.pow(10, 9);

				percentual += (int) tempoTotal / 0.1;

				if (percentual > 100) {
					percentual = 100;
					System.out.println("Percentual de cozimento --- " + percentual + "% do prato: " + idPratos + "\n");
					break;
				}

				System.out.println("Percentual de cozimento --- " + percentual + "% do prato: " + idPratos + "\n");
			}

		}

	}

	public void pratosProntos() {

		try {
			sleep(500);
			System.out.println("Prato: " + idPratos + " finalizado! Realizando entrega... \n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Prato " + idPratos + " entregue! \n");

	}

}
