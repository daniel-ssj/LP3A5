import java.util.concurrent.Semaphore;

public class Main {
	public volatile static int produtos = 0;
	
	public static void main(String[] args) {
		FilaProdutos fp = new FilaProdutos();
		Produtor p1 = new Produtor(fp);
		Consumidor c1 = new Consumidor(fp);
	}
}

class Produtor extends Thread {
	FilaProdutos f;
     
    public Produtor(FilaProdutos f){
        this.f=f;
        this.setName("Produtor");
        this.start();
    }
	public void run() {
		for(int i = 0; i <= 20; i++) {
			f.produzir(i);
		}
	}
}

class Consumidor extends Thread {
	FilaProdutos f;
     
    Consumidor(FilaProdutos f){
		this.f=f;
        this.setName("Consumidor");
        this.start();
    }

	public void run() {
		for(int i = 0; i <= 20; i++) {
			f.consumir();
		}
	}
}

class FilaProdutos {
    static Semaphore semProd = new Semaphore(1);
    static Semaphore semCon = new Semaphore(0);
     
    int numProduto;
     
    public void produzir(int numProduto){
        try {
            semProd.acquire();
            this.numProduto=numProduto;
            System.out.println(Thread.currentThread().getName() + " produzindo produto " + numProduto);
            semCon.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
     
    public void consumir(){
        try {
            semCon.acquire();
            System.out.println(Thread.currentThread().getName()+ " consumindo produto " + numProduto);
            semProd.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
    }
}
