package modules.DFA.model;

import java.util.ArrayList;


public class DFAEstado {

	private String nombre;
	private boolean isInitial;
	private boolean isFinal;
	private ArrayList<DFAFuncionTransicion> tranciociones;
	private int x;
	private int y;
	
	public DFAEstado(String nombre, boolean isInitial, boolean isFinal, int x, int y){
		this.nombre = nombre;
		this.isInitial = isInitial;
		this.isFinal = isFinal;
		this.tranciociones = new ArrayList<DFAFuncionTransicion>();
		this.x = x;
		this.y = y;
	}
	
	public void addTansactyion(DFAFuncionTransicion transaccion) {
		this.tranciociones.add(transaccion);	
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isInitial() {
		return isInitial;
	}
	public void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}
	public boolean isFinal() {
		return isFinal;
	}
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	public ArrayList<DFAFuncionTransicion> getTranciociones() {
		return tranciociones;
	}
	public void setTranciociones(ArrayList<DFAFuncionTransicion> tranciociones) {
		this.tranciociones = tranciociones;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Estado [nombre=" + nombre + ", isInitial=" + isInitial + ", isFinal=" + isFinal + ", tranciociones="
				+ tranciociones.toString() + "]";
	}
	
	
}
