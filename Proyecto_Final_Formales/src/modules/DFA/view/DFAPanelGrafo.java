package modules.DFA.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import modules.DFA.model.DFAEstado;
import modules.DFA.model.DFAFuncionTransicion;

public class DFAPanelGrafo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<DFAEstado> estados;

	public DFAPanelGrafo(ArrayList<DFAEstado> arrayList) {
		this.estados = arrayList;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		for (DFAEstado estado : estados) {
			for (DFAFuncionTransicion t : estado.getTranciociones()) {
				if (t.getEstadoInicial() == t.getEstadoFinal()) {
						g.drawOval(estado.getX(), estado.getY()+25, 50, 50);
						g.drawString(String.valueOf(t.getLetter()), estado.getX()+25,estado.getY()+90);
				}else if (t.getEstadoInicial().getX() < t.getEstadoFinal().getX()) {
					g.drawString(""+t.getLetter(), estado.getX()+75,estado.getY());
					g.drawLine(t.getEstadoInicial().getX()+25, t.getEstadoInicial().getY()+25, t.getEstadoFinal().getX()+25, t.getEstadoFinal().getY()+25);
					g.drawLine(t.getEstadoFinal().getX()-5, t.getEstadoInicial().getY()+35, t.getEstadoFinal().getX(), t.getEstadoFinal().getY()+25);
					g.drawLine(t.getEstadoFinal().getX()-5, t.getEstadoInicial().getY()+15, t.getEstadoFinal().getX(), t.getEstadoFinal().getY()+25);
				}else if (t.getEstadoInicial().getX() > t.getEstadoFinal().getX()) {
					g.drawString(String.valueOf(t.getLetter()), estado.getX()-25,estado.getY());
					g.drawLine(t.getEstadoInicial().getX()+25, t.getEstadoInicial().getY()+25, t.getEstadoFinal().getX()+50, t.getEstadoFinal().getY()+25);
					g.drawLine(t.getEstadoFinal().getX()+55, t.getEstadoFinal().getY()+35, t.getEstadoFinal().getX()+50, t.getEstadoFinal().getY()+25);
					g.drawLine(t.getEstadoFinal().getX()+55, t.getEstadoFinal().getY()+15, t.getEstadoFinal().getX()+50, t.getEstadoFinal().getY()+25);
				}
			}
			
			if (estado.isInitial()) {
				g.setColor(Color.CYAN);
			}else if (estado.isFinal()) {
				g.setColor(Color.ORANGE);
			}else {
				g.setColor(Color.YELLOW);
				
			}
			g.fillOval(estado.getX(), estado.getY(), 50, 50);
			g.setColor(Color.BLACK);
			g.drawString(estado.getNombre(), (estado.getX())+20, estado.getY()+25);
		}
	}
	
	public void update(ArrayList<DFAEstado> estados) {
		this.estados = estados;
		revalidate();
		repaint();
	}

}
