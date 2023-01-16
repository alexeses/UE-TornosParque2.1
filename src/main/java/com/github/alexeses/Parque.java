package com.github.alexeses;

import java.util.concurrent.Semaphore;

public class Parque {
    private static int cuenta = 0;
    private static Semaphore semaphore = new Semaphore(1);

    public static void main(String[] args) {
        // Crear los hilos para cada torno
        Thread torno1 = new Torno(1);
        Thread torno2 = new Torno(2);
        Thread torno3 = new Torno(3);

        // Iniciar los hilos
        torno1.start();
        torno2.start();
        torno3.start();
    }

    // Clase aux que representa un torno
    private static class Torno extends Thread {
        private int numero;

        public Torno(int numero) {
            this.numero = numero;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // Simular el evento de una persona entrando al parque
                Contador.incrementar();
                System.out.println("Torno " + numero + ": Entrada de un visitante. Total: " + cuenta);
                // Simular un retardo entre eventos
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Clase aux que representa el contador de visitantes
    private static class Contador {
        public static void incrementar() {
            try {
                semaphore.acquire();
                cuenta++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
