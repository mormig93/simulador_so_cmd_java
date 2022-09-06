import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Simulador a = new Simulador();
		NodoArbol r = new NodoArbol("c:/");
		
		a.Simulacion(r);
	}

}
