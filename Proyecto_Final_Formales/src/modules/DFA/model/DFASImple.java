package modules.DFA.model;

import java.util.ArrayList;

public class DFASImple implements DFAIAutomataFinito{

	private ArrayList<DFAEstado> estados;

	public DFASImple() {
		estados = new ArrayList<DFAEstado>();
	}

	@Override
	public void agregarEstados(DFAEstado estado) {
		estados.add(estado);
		System.out.println(estado.toString());
	}

	@Override
	public void agregarFunciones(DFAFuncionTransicion funcion) {
		for (DFAEstado estado : estados) {
			if (estado.equals(funcion.getEstadoInicial())) {
						estado.addTansactyion(funcion);
			}
		}
	}

	@Override
	public void agregarAlfabeto(String alfabeto) {
	}

	@Override
	public DFAEstado seachEstado(String nameEstado) {
		for (DFAEstado estado : estados) {
			if (estado.getNombre().equals(nameEstado)) {
				return estado;
			}
		}
		return null;
	}

	@Override
	public Object[] evaluarCadena(String cadena, int tipoAutomata) {
		int posicion = 0;
		char[] letras = cadena.toCharArray();
		ArrayList<String> nombre = new ArrayList<>();
		
		for (DFAEstado estado : estados) {
			if (estado.isInitial()) {
				if(tipoAutomata == 1) {
					return new Object[] {evaluarCadenaAux(letras, estado, posicion, nombre), nombre};
				}else {
				}
				
			}
		}
		return null;
	}

	private boolean evaluarCadenaAux(char[] letras, DFAEstado estado, int posicion, ArrayList<String> nombres) {
		if (estado.isFinal()) {
			if(posicion == letras.length) {
				nombres.add(estado.getNombre());
				return true;
			}else {
				for (DFAFuncionTransicion transaction : estado.getTranciociones()) {
					if (transaction.getLetter() == letras[posicion]) {
						nombres.add(estado.getNombre());
						int aux = posicion + 1;
						return evaluarCadenaAux(letras, transaction.getEstadoFinal(), aux, nombres);
					}
				}
			}
		}else{
			if(posicion < letras.length) {
				for (DFAFuncionTransicion transaction : estado.getTranciociones()) {
					if (transaction.getLetter() == letras[posicion]) {
						nombres.add(estado.getNombre());
						int aux = posicion + 1;
						return evaluarCadenaAux(letras, transaction.getEstadoFinal(), aux, nombres);
					}
				}
				
			}
		}
		nombres.add(estado.getNombre());
		return false;
	}

	@Override
	public ArrayList<DFAEstado> getEstados() {
	return estados;
	}
}
