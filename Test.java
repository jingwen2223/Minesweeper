import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Test {
	public static void  main(String [] args) {
		Grid g = new Grid(10,10,25);
		for(int i = 0; i < g.getBombGrid().length ; i++) {
			for(int j = 0; j < g.getBombGrid()[0].length; j++) {
				String tf = "";
				tf =(g.getBombGrid()[i][j] ?  "T": "F");
				System.out.print("[" +tf +"]" );
			}
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i < g.getCountGrid().length ; i++) {
			for(int j = 0; j < g.getCountGrid()[0].length; j++) {
				System.out.print("[" +g.getCountGrid()[i][j] +"]" );
			}
			System.out.println();
		}

	}


}
