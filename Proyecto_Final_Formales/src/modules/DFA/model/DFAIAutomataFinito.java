package modules.DFA.model;

import java.util.ArrayList;

public interface DFAIAutomataFinito {
	
	public void agregarEstados(DFAEstado estado);
	
	public void agregarFunciones(DFAFuncionTransicion estado);
	
	public Object[] evaluarCadena(String cadena,  int tipoAutomata); 
	
	public DFAEstado seachEstado(String nameEstado);
	
	public ArrayList<DFAEstado> getEstados();
}
