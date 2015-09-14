package Utils;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.prodotto;

public class popolaTabellaProdotti extends AbstractTableModel {
	List<String[]> lista=new ArrayList<String[]>();

	@Override
	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int riga, int colonna) {
		Object risultato=lista.get(riga)[colonna];
		return lista;
	}

}
