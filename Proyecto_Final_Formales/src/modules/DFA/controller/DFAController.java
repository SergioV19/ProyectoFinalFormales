package modules.DFA.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modules.DFA.model.DFASImple;
import modules.DFA.model.DFAEstado;
import modules.DFA.model.DFAFuncionTransicion;
import modules.DFA.model.DFAIAutomataFinito;
import modules.DFA.view.DFAMyFrame;

public class DFAController implements ActionListener {

	private DFAIAutomataFinito iAutomata;
	private DFAMyFrame view;

	public DFAController() {
		iAutomata = new DFASImple();
		view = new DFAMyFrame(this, iAutomata.getEstados());
	}

	@Override
	public void actionPerformed(ActionEvent even) {
		switch (even.getActionCommand()) {
		case "ADD_ALFABETO": {
			String alfabeto = view.getAlfabeto();
			if (!alfabeto.isEmpty()) {
				System.out.println(alfabeto);
			} else {
				view.showDialog("El alfabeto esta vacio");
			}
			break;
		}
		case "ADD_ESTADO": {
			DFAEstado estado = view.getEstado();
			if (estado == null) {
				return;
			}
			System.out.println("Agregado: _" + estado.toString());
			iAutomata.agregarEstados(estado);
			view.fillEstadosInicialesCheck(iAutomata.getEstados());
			view.showDialog("Agregado estado correctamente");
			view.updateGrafo(iAutomata.getEstados());
			break;
		}
		case "ADD_FUNCION": {
			if (view.getTextFuncion().isEmpty()) {
				return;
			}
			char text = view.getTextFuncion().toCharArray()[0];

			if (text == ' ') {
				view.showDialog("No hay letra en el la funci�n. Intentelo de nuevo.");
			}
			DFAEstado estadoIni = iAutomata.seachEstado(view.getEstadoInitFuncion());
			DFAEstado estadoFin = iAutomata.seachEstado(view.getEstadoFinishFuncion());
			if (estadoIni == null || estadoFin == null) {
				return;
			}
            DFAFuncionTransicion funcionTransicion = new DFAFuncionTransicion(text, estadoIni, estadoFin);
			iAutomata.agregarFunciones(funcionTransicion);
			view.showDialog("Agregada Funcion correctamente");
			view.updateGrafo(iAutomata.getEstados());
			break;
		}
		case "COMPARAR": {
			String text = view.getComprar();
			if (!text.isEmpty()) {
				
				Object[] resultado = iAutomata.evaluarCadena(text, 1);

				if (resultado == null) {
					view.showDialog("Llene primero el automata. No se puede comparar.");
					return;
				}

				if ((boolean) resultado[0]) {
					view.showDialog("Estados recorridos: " + resultado[1].toString() + ", pertenece al alfabeto");
				} else {
					view.showDialog("Estados recorridos: " + resultado[1].toString() + ", NO pertenece al alfabeto");
				}

			} else {
				view.showDialog("No se compraran textos vacios");
			}

			break;
		}

		}

	}
	
	public DFAIAutomataFinito getiAutomata() {
		return iAutomata;
	}
}
