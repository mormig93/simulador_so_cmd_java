
public class NodoL 
{
	private String nombreArch;
	private NodoL sig;
	
	NodoL()
	{
		nombreArch = " ";
		sig = null;
	}

	NodoL(String nombreArch)
	{
		this.nombreArch = nombreArch;
	}
	
	public String getNombreArch() {
		return nombreArch;
	}

	public void setNombreArch(String nombreArch) {
		this.nombreArch = nombreArch;
	}

	public NodoL getSig() {
		return sig;
	}

	public void setSig(NodoL sig) {
		this.sig = sig;
	}
}