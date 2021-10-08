package modules.DFA.model;

public class DFAFuncionTransicion {

	private char letter;
	private DFAEstado estadoInicial;
	private DFAEstado estadoFinal;
	
	public DFAFuncionTransicion(char letter,DFAEstado estadoInicial, DFAEstado estadoFinal) {
		this.letter = letter;
		this.estadoInicial = estadoInicial;
		this.estadoFinal = estadoFinal;
	}
	
	public char getLetter() {
		return letter;
	}
	public void setLetter(char letter) {
		this.letter = letter;
	}
	public DFAEstado getEstadoInicial() {
		return estadoInicial;
	}
	public void setEstadoInicial(DFAEstado estadoInicial) {
		this.estadoInicial = estadoInicial;
	}
	public DFAEstado getEstadoFinal() {
		return estadoFinal;
	}
	public void setEstadoFinal(DFAEstado estadoFinal) {
		this.estadoFinal = estadoFinal;
	}
	
	@Override
	public String toString() {
		return "FuncionTransicion [letter=" + letter + ", estadoInicial=" + estadoInicial + ", estadoFinal="
				+ estadoFinal + "]";
	}
	
}
