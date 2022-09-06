public class NodoArbol {

	private String nombre;
	private NodoArbol hi;
	private NodoArbol hd;
	private NodoArbol padre;
	private NodoL p;
	
	NodoArbol(){
		nombre = "";
		hi = null;
		hd = null;
		padre = null;
		p = null;
	}
	
	NodoArbol(String nombre){
		this.nombre = nombre;
	}
	
	NodoArbol(String nombre,NodoArbol hi,NodoArbol hd,NodoArbol padre,NodoL p){
		this.nombre = nombre;
		this.hi = hi;
		this.hd = hd;
		this.padre = padre;
		this.p = p;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public NodoArbol getHi() {
		return hi;
	}

	public void setHi(NodoArbol hi) {
		this.hi = hi;
	}

	public NodoArbol getHd() {
		return hd;
	}

	public void setHd(NodoArbol hd) {
		this.hd = hd;
	}

	public NodoArbol getPadre() {
		return padre;
	}

	public void setPadre(NodoArbol padre) {
		this.padre = padre;
	}

	public NodoL getP() {
		return p;
	}

	public void setP(NodoL p) {
		this.p = p;
	}

}
