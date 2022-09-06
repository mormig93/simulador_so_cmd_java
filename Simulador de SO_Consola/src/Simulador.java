import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simulador {

	NodoArbol raiz;
	
	Simulador(){
		raiz = null;
	}
	
	Simulador(NodoArbol raiz, NodoL p){
		this.raiz = raiz;
	}
	
	public void Simulacion(NodoArbol nuevo) throws IOException{
		
		raiz = nuevo;
		NodoArbol prompt = new NodoArbol();
		prompt = raiz;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cd = "cd",quit = "quit",md = "md",cdpadre = "cd..",dir = "dir",dirS = "dir /s";
		String create = "create",dirA = "dir /all",rd = "rd",delete = "delete";
		int i=0;
		
			System.out.print(prompt.getNombre()+">");
			
			while(i == 0){
				
				String tecla = br.readLine();
				String s_cmd[]= tecla.split(" ");
				
				//AQUI ES PARA AGREGAR
				if(s_cmd[0].equals(md)){
					if(s_cmd.length == 2){
						if(prompt.getHi() == null){
							NodoArbol aux = new NodoArbol(s_cmd[1]);
							prompt.setHi(aux);
							aux.setPadre(prompt);
							System.out.println("EL DIRECTORIO SE CREO CORRECTAMENTE\n");
							System.out.print(prompt.getNombre()+">");
						}
						else if(prompt.getHd() == null){
							if(!s_cmd[1].equals(prompt.getHi().getNombre())){
								NodoArbol aux2 = new NodoArbol(s_cmd[1]);
								prompt.setHd(aux2);
								aux2.setPadre(prompt);
								System.out.println("EL DIRECTORIO SE CREO CORRECTAMENTE\n");
								System.out.print(prompt.getNombre()+">");
							}
							else{
								System.out.println("EL NOMBRE YA EXISTE\n");
								System.out.print(prompt.getNombre()+">");
							}
						}
						else{
							System.out.println("ASEGURESE QUE HA INTRODUCIDO UN COMANDO O UN NOMBRE DE DIRECTORIO CORRECTO\n");
							System.out.print(prompt.getNombre()+">");
						}
					}
					else{
						System.out.println("EL DIRECTORIO NO SE CREO, EL FORMATO NO ES CORRECTO\n");
						System.out.print(prompt.getNombre()+">");
					}
				}
				
				//AQUI ES PARA MOVERME A LOS DESCENDIENTES
				else if(s_cmd[0].equals(cd)){
					if(s_cmd.length == 2){
						if(prompt.getHi() != null && s_cmd[1].equals(prompt.getHi().getNombre())){
							prompt = prompt.getHi();
							System.out.print("\n"+prompt.getNombre()+">");
						}
						else if(prompt.getHd() != null && s_cmd[1].equals(prompt.getHd().getNombre())){
							prompt = prompt.getHd();
							System.out.print("\n"+prompt.getNombre()+">");
						}
						else{
							System.out.println("ESTE DIRECTORIO NO EXISTE\n");
							System.out.print(prompt.getNombre()+">");
						}
					}
					else{
						System.out.println("ASEGURESE QUE HA INTRODUCIDO EL COMANDO O NOMBRE DEL DIRECTORIO CORRECTO\n");
						System.out.print(prompt.getNombre()+">");
					}
				}
				
				//METODO MOVER AL PADRE
				else if(tecla.equals(cdpadre)){
					if(prompt.getPadre() != null){
						prompt = prompt.getPadre();
						System.out.print("\n"+prompt.getNombre()+">");
					}
					else{
					    System.out.println("NO HAY DIRECTORIO ANTERIOR\n");
					    System.out.print(prompt.getNombre()+">");
					}
				}
				
				//AQUI SE ELIMINA EL DIRECTORIO
				else if(s_cmd[0].equals(rd)){
					if(s_cmd.length == 2){
						if(prompt.getHi() != null && prompt.getHi().getNombre().equals(s_cmd[1])){
							if(prompt.getHi().getHi() == null && prompt.getHi().getHd() == null && prompt.getHi().getP() == null){
								prompt.setHi(null);
								System.out.println("EL DIRECTORIO SE ELIMINO CORRECTAMENTE\n");
								System.out.print(prompt.getNombre()+">");
							}
							else{
								System.out.println("NO SE PUEDE ELIMINAR, ESTE DIRECTORIO CONTIENE INFORMACION\n");
								System.out.print(prompt.getNombre()+">");
							}
						}
						else if(prompt.getHd() != null && prompt.getHd().getNombre().equals(s_cmd[1])){
							if(prompt.getHd().getHi() == null && prompt.getHd().getHd() == null && prompt.getHd().getP() == null){
								prompt.setHd(null);
								System.out.println("EL DIRECTORIO SE ELIMINO CORRECTAMENTE\n");
								System.out.print(prompt.getNombre()+">");
							}
							else{
								System.out.println("NO SE PUEDE ELIMINAR, ESTE DIRECTORIO CONTIENE INFORMACION\n");
								System.out.print(prompt.getNombre()+">");
							}
						}
						else{
							System.out.println("EL DIRECTORIO ESPECIFICADO NO EXISTE\n");
							System.out.print(prompt.getNombre()+">");
						}
					}
					else if(s_cmd.length == 3 && s_cmd[2].equals("/s")){
						Rds(prompt, s_cmd[1]);
						System.out.print(prompt.getNombre()+">");
					}
					else{
						System.out.println("ASEGURESE QUE HA INTRODUCIDO EL COMANDO CORRECTO\n");
						System.out.print(prompt.getNombre()+">");
					}
				}
				
				//AQUI MUESTRA EL CONTENIDO DEL DIRECTORIO ACTUAL
				else if(tecla.equals(dir)){
					if(prompt != null){
						if(prompt.getHi() != null && prompt.getHd() != null && prompt.getP() != null){
							System.out.println();
							System.out.println("<DIR>\t\t"+prompt.getHi().getNombre());
							System.out.println("<DIR>\t\t"+prompt.getHd().getNombre());
							imprimirL(prompt);
							System.out.print("\n"+prompt.getNombre()+">");
						}
						else if(prompt.getHi() != null && prompt.getHd() != null && prompt.getP() == null){
							System.out.println();
							System.out.println("<DIR>\t\t"+prompt.getHi().getNombre());
							System.out.println("<DIR>\t\t"+prompt.getHd().getNombre()+"\n");
							System.out.print(prompt.getNombre()+">");
						}
						else if(prompt.getHi() == null && prompt.getHd() == null && prompt.getP() != null){
							System.out.println();
							imprimirL(prompt);
							System.out.print("\n"+prompt.getNombre()+">");
						}
						else if(prompt.getHi() != null && prompt.getHd() == null && prompt.getP() != null){
							System.out.println();
							System.out.println("<DIR>\t\t"+prompt.getHi().getNombre());
							imprimirL(prompt);
							System.out.print(prompt.getNombre()+">");
						}
						else if(prompt.getHi() != null && prompt.getHd() == null && prompt.getP() == null){
							System.out.println();
							System.out.println("<DIR>\t\t"+prompt.getHi().getNombre()+"\n");
							System.out.print(prompt.getNombre()+">");
						}
						else{
							System.out.println("NO HAY NADA QUE MOSTRAR\n");
							System.out.print(prompt.getNombre()+">");
						}
					}
				}
				
				//AQUI IMPRIME DESDE EL DIRECTORIO ACTUAL
				else if(tecla.equals(dirS)){
					dirS(prompt);
					System.out.print("\n"+prompt.getNombre()+">");
				}
				
				//AQUI IMPRIME DESDE LA RAIZ
				else if(tecla.equals(dirA)){
					dirAll();
					System.out.print("\n"+prompt.getNombre()+">");
				}
				
				//AQUI SE CREA EL ARCHIVO
				else if(s_cmd[0].equals(create)){
					String n[]= s_cmd[1].split("\\.");
					if(n.length == 2 ){
						NodoL aux = new NodoL(s_cmd[1]);
						Create(prompt, aux);
					}
					else{
						System.out.println("EL FORMATO PARA CREAR EL ARCHIVO NO ES EL CORRECTO\n");
						System.out.print(prompt.getNombre()+">");
					}
				}
				
				//AQUI SE ELIMINAN LOS ARCHIVOS
				else if(s_cmd[0].equals(delete)){
					String verif[] = s_cmd[1].split("\\.");
					if(s_cmd.length == 2 && verif.length == 2){
						NodoL aux = prompt.getP(), aux2 = prompt.getP();
						if(prompt.getP() != null){
							if(prompt.getP().getNombreArch().equals(s_cmd[1])){
								prompt.setP(prompt.getP().getSig());
								aux2.setSig(null);
								System.out.println("EL ARCHIVO SE HA ELIMINADO CON EXITO\n");
								System.out.print(prompt.getNombre()+">");
							}
							else{
								while(aux.getSig() != null && !(aux.getSig().getNombreArch().equals(s_cmd[1]))){
									aux = aux.getSig();
								}
								if (aux.getSig() != null){
									aux2 = aux.getSig();
									aux.setSig(aux2.getSig());
									aux2.setSig(null);
									System.out.println("EL ARCHIVO SE HA ELIMINADO CON EXITO\n");
									System.out.print(prompt.getNombre()+">");
								}
								else{
									System.out.println("EL ARCHIVO NO EXISTE\n");
									System.out.print(prompt.getNombre()+">");
								}
							}
						}
						else{
							System.out.println("ESTE DIRECTORIO NO CONTINE ARCHIVOS\n");
							System.out.print(prompt.getNombre()+">");
						}
					}
					else if(s_cmd.length == 3 && s_cmd[2].equals("/all")){
						if(raiz.getP() != null && raiz.getP().getNombreArch().equals(s_cmd[1])){
							NodoL aux = raiz.getP();
							raiz.setP(prompt.getP().getSig());
							aux.setSig(null);
						}
						deleteAll(raiz, s_cmd[1]);
						System.out.println("LOS ARCHIVOS SE BORRARON CON EXITO\n");
						System.out.print(prompt.getNombre()+">");
					}
					else{
						System.out.println("ASEGURESE QUE EL COMANDO INGRESADO ES EL CORRECTO\n");
						System.out.print(prompt.getNombre()+">");
					}
				}
				
				//AQUI ES PARA SALIR DE LA SIMULACION
				else if(tecla.equals(quit)){
					i++;
				}
				
				//AQUI PARA CUANDO EL COMANDO NO ES NINGUNO DE LOS ANTERIORES
				else{
						System.out.println("''"+tecla+"'' NO SE RECONOCE COMO UN COMANDO INTERNO Ó EXTERNO\n");
						System.out.print(prompt.getNombre()+">");
				}
			}
	}
	
	private void dirS(NodoArbol prompt) {
			if(prompt != null) {
				if(prompt != raiz)
        		System.out.println(prompt.getNombre());
        		dirS(prompt.getHi());
        		dirS(prompt.getHd());
        		if(prompt.getP() != null){
        			imprimirL(prompt);
        		}
        	}
    }
	
	public void dirAll() {
        if(raiz == null){
            System.out.println("ARBOL VACIO\n");
        }
        else{
            dirS(raiz);
        }
	}
	
	public void deleteAll(NodoArbol prompt, String clave){
		NodoL aux = prompt.getP(), aux2 = prompt.getP();
		if(prompt != null){
			if(prompt.getP() != null){
				if(prompt.getP().getNombreArch().equals(clave)){
					prompt.setP(prompt.getP().getSig());
					aux2.setSig(null);
				}
				else{
					while(aux.getSig() != null && !(aux.getSig().getNombreArch().equals(clave))){
						aux = aux.getSig();
					}
					if (aux.getSig() != null){
						aux2 = aux.getSig();
						aux.setSig(aux2.getSig());
						aux2.setSig(null);
					}
					
				}
			}
			else{
					deleteAll(prompt.getHi(), clave);
					deleteAll(prompt.getHd(), clave);
			}
		}
	}
	
	public void Rds(NodoArbol prompt, String clave){
		NodoArbol aux = prompt;
		if(raiz!=null)
		{
			if(prompt.getHi().getNombre().equals(clave))
			{
				aux.setHi(null);
				System.out.println("SE ELIMINO CORRECTAMENTE\n");
				System.out.print(prompt.getNombre()+">");
			}
			else if(prompt.getHd().getNombre().equals(clave))
			{
				aux.setHd(null);
				System.out.println("SE ELIMINO CORRECTAMENTE\n");
				System.out.print(prompt.getNombre()+">");
			}
			else{
				System.out.println("NO HAY SUBDIRECTORIO CON ESE NOMBRE\n");
				System.out.print(prompt.getNombre()+">");
			}
		}
	}
	
	public void Create(NodoArbol prompt, NodoL nuevo)
	{
		NodoL aux = prompt.getP();
		if(prompt.getP() == null)
		{
			prompt.setP(nuevo);
			System.out.println("EL ARCHIVO SE CREO CORRECTAMENTE\n");
			System.out.print(prompt.getNombre()+">");
		}
		else
		{
			if(nuevo.getNombreArch().equals(aux.getNombreArch())){
				System.out.println("ESTE ARCHIVO YA EXISTE\n");
				System.out.print(prompt.getNombre()+">");
			}
			else{
				while(aux.getSig() != null){
					aux = aux.getSig();
				}
				nuevo.setSig(aux.getSig());
				aux.setSig(nuevo);
				System.out.println("EL ARCHIVO SE CREO CORRECTAMENTE\n");
				System.out.print(prompt.getNombre()+">");
			}
		}
	}
	
	public void imprimirL(NodoArbol prompt)
	{
		NodoL aux = prompt.getP();
		if(prompt.getP() != null)
		{
			while(aux != null)
			{
				System.out.println("ARCHIVO:\t"+aux.getNombreArch());
				aux = aux.getSig();
			}
		}
	}

}