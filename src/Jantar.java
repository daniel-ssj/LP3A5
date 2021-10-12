public class Jantar {
    public static void main(String[] args) {
        final Filosofos[] filosofos = new Filosofos[5];
        final Object[] hashis = new Object[5];

        for (int i = 0; i < hashis.length; i++) {
            hashis[i] = new Object();
        }

        for (int i = 0; i < filosofos.length; i++) {
            Object hashiEsquerda = hashis[i];
            Object hashiDireita = hashis[(i + 1) % hashis.length];

            if (i == filosofos.length - 1) {
                filosofos[i] = new Filosofos(hashiDireita, hashiEsquerda); 
            } else {
                filosofos[i] = new Filosofos(hashiEsquerda, hashiDireita);
            }
            
            Thread t = new Thread(filosofos[i], "Philosopher " + (i + 1));
            t.start();
        }
    }       
}

class Filosofos extends Thread {
    private Object hashiEsquerda;
    private Object hashiDireita;

    public Filosofos(Object hashiEsquerda, Object hashiDireita) {
        this.hashiEsquerda = hashiEsquerda;
        this.hashiDireita = hashiDireita;
    }

	public void run() {
        System.out.println(Thread.currentThread().getName() + " " + "pensando");
		synchronized(hashiEsquerda) {
            System.out.println(Thread.currentThread().getName() + " " + "pegou hashi esquerdo");
			synchronized(hashiDireita) {
				System.out.println(Thread.currentThread().getName() + " " + "pegou hashi direito");
                System.out.println(Thread.currentThread().getName() + " " + "soltou hashi direito");
			}
            System.out.println(Thread.currentThread().getName() + " " + "soltou hashi esquerdo. pensando de novo");
		}
	}
}
