//import javax.swing.event.AncestorListener;
import java.awt.*;    
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MineSweeperGame extends JFrame implements ActionListener{  
	
	JLabel rowLabel = null;
    JLabel columnLabel = null;
    JLabel bombLabel = null;
    JLabel CountAtLocation =null;
    JTextField rowField = null; 
    JTextField columnFiled = null;
    JTextField numBombFiled = null;
    JButton startButton;
    JButton playButton;
    JButton launchButton;
    JButton [][] gridButton;
    JFrame customFrame = null;
    JFrame gameFrame = null;
    boolean reStart;
    int count;
    GridBagConstraints layoutConst;
	Icon icon = new ImageIcon("bomb.PNG");
	Grid mineSweeper = null;
	boolean end;
	
    MineSweeperGame(){ 
    	count=0;
    	end = false;
    	reStart = false;
    	
	
    	gameFrame= new JFrame();
    	layoutConst = null;
	    
	    rowLabel = new JLabel("Enter row: ");
	    columnLabel =new JLabel("Enter Column: ");
	    bombLabel = new JLabel("Number of bomb: ");
	    
	    rowField = new JTextField(15);
	    columnFiled = new JTextField(15);
	    numBombFiled = new JTextField(15);
	    rowField.setEditable(true);
	    columnFiled.setEditable(true);
	    numBombFiled.setEditable(true);
	    
	    customFrame = new JFrame("MineSweeper Customaizie Window");
	    customFrame.setLayout(new GridBagLayout());
    
	    playButton = new JButton("Play Default");
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 3;
	    layoutConst.insets = new Insets(20, 10, 10, 10);
	    customFrame.add(playButton, layoutConst);
	    
	    startButton = new JButton("Custom Game");
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 3;
	    layoutConst.insets = new Insets(20, 10, 10, 10);
	    customFrame.add(startButton, layoutConst);
	    playButton.addActionListener(this);
	    startButton.addActionListener(this);

   
	    //Set up the game Frame
	    customFrame.setSize(500,500);
	    customFrame.setVisible(true);
	    customFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
    }  

	
	
	public void actionPerformed(ActionEvent event){ 

	    JButton sourceEvent = (JButton) event.getSource();

	   if (sourceEvent == playButton) {
		   mineSweeper = new Grid(); 
	
		   gameFrame.setSize(500,500);
		   gameFrame.setVisible(true);
		   gameFrame.setTitle("MineSweeper");
		   
		   customFrame.dispose(); //close the opening window
		   gameFrame.setLayout(new GridLayout(mineSweeper.getNumRows(),mineSweeper.getNumColumns()));
		   gridButton = new JButton[mineSweeper.getNumRows()][mineSweeper.getNumColumns()];
		   for(int k=0;k<mineSweeper.getNumRows();k++) {
	            for(int j=0;j<mineSweeper.getNumColumns();j++) {
	            	gridButton[k][j] = new JButton();
	            	gameFrame.add(gridButton[k][j]);
	            	gridButton[k][j].addActionListener(this);
	            }
            } 
		   	gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		}
	   
	   		//set up the customization for the game, set row and column and number of bomb
		   if (sourceEvent == startButton) {
			   addFields(layoutConst);
			  
		   }
		   if(sourceEvent == launchButton) {
			   mineSweeper = new Grid(Integer.parseInt(rowField.getText()),
					   Integer.parseInt(columnFiled.getText()),
					   Integer.parseInt(numBombFiled.getText())); 
				
			   gameFrame.setSize(500,500);
			   gameFrame.setVisible(true);
			   gameFrame.setTitle("MineSweeper");
			   
			   customFrame.dispose(); //.dispose() is to close the opening window
			   gameFrame.setLayout(new GridLayout(mineSweeper.getNumRows(),mineSweeper.getNumColumns()));
			   gridButton = new JButton[mineSweeper.getNumRows()][mineSweeper.getNumColumns()];
			   for(int k=0;k<mineSweeper.getNumRows();k++) {
		            for(int j=0;j<mineSweeper.getNumColumns();j++) {
		            	gridButton[k][j] = new JButton();
		            	gameFrame.add(gridButton[k][j]);
		            	gridButton[k][j].addActionListener(this);
		            }
	            } 
			   	gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   }
		   if(mineSweeper != null) {
		   for (int row = 0; row < mineSweeper.getNumRows(); row++) {
				for (int column = 0; column < mineSweeper.getNumColumns(); column++) {
				    if(event.getSource()==gridButton[row][column]){ 
				        				//gameButtons[row][column] was clicked
				       if(mineSweeper.isBombAtLocation(row, column)) {
			            	 gridButton[row][column].setIcon(icon);
			            	 int yOrN = JOptionPane.showConfirmDialog(null,
			            			 "You Lost! Want to try again?", "Boom!!", JOptionPane.YES_NO_OPTION);
			            	 	if(yOrN == JOptionPane.YES_OPTION){
			            	 			reStart = true;
			            	 				
			            	 			gameFrame.dispose();
			            	 			end = true;
			            	 		}
		 					
			            	 	else{
					 					reStart = false;
					 					end = true;
					 					gameFrame.dispose();
					 					JOptionPane.showMessageDialog(null, "Thanks for playing!");
			            	 		}
				       }
			             else if(mineSweeper.isBombAtLocation(row, column)==false) {
				            	 
				            	 gridButton[row][column].setText(String.valueOf(mineSweeper.getCountAtLocation(row, column)));
				            	 gridButton[row][column].setEnabled(false);
				            	 count++;
				            	 System.out.println("Click "+count);
				            	if(count==mineSweeper.getNumRows()*mineSweeper.getNumColumns()-mineSweeper.getNumBombs()) {
			            		 JOptionPane.showMessageDialog(null, "Congraduation, you found all the bombs!");
			            		 int yOrN = JOptionPane.showConfirmDialog(null,
				            			 "Have fun? Want to play another game?", null,JOptionPane.YES_NO_OPTION);
				            	 	if(yOrN == JOptionPane.YES_OPTION){
				            	 			reStart = true;
				            	 				
				            	 			gameFrame.dispose();
				            	 			end = true;
				            	 		}
			 					
				            	 	else{
						 					reStart = false;
						 					end = true;
						 					gameFrame.dispose();
						 					JOptionPane.showMessageDialog(null, "Thanks for playing!");
						 					
				            	 		}
			            		 
			            	 }

				            	
				            }
			             
			            	 
			            	
			             
				       }
					
				   
				}
	   		}

		   }

	}
	private void addFields(GridBagConstraints layoutConst) {
		
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 0;
	    layoutConst.insets = new Insets(10,10,10,10);
	    customFrame.add(rowLabel,layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 1;
	    layoutConst.insets = new Insets(10,10,10,10);
	    customFrame.add(columnLabel,layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 0;
	    layoutConst.gridy = 2;
	    layoutConst.insets = new Insets(10,10,10,10);
	    customFrame.add(bombLabel,layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 0;
	    layoutConst.insets = new Insets(10, 10, 10, 10);
	    customFrame.add(rowField, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 0;
	    layoutConst.insets = new Insets(10, 10, 10, 10);
	    customFrame.add(rowField, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 1;
	    layoutConst.insets = new Insets(10, 10, 10, 10);
	    customFrame.add(columnFiled, layoutConst);
	    
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 2;
	    layoutConst.insets = new Insets(10, 10, 10, 10);
	    customFrame.add(numBombFiled, layoutConst);
	   
	    customFrame.remove(playButton);
	    
	    launchButton = new JButton("Launch");
	    layoutConst = new GridBagConstraints();
	    layoutConst.gridx = 1;
	    layoutConst.gridy = 3;
	    layoutConst.insets = new Insets(10, 10, 100, 100);
	    customFrame.add(launchButton, layoutConst);
	    launchButton.addActionListener(this);
		customFrame.revalidate(); //recalculate the layout since new component is added
		customFrame.repaint();
		customFrame.remove(startButton);
	}

	public static void main(String[] args) {
		boolean play = true;
		while(play) {
		MineSweeperGame msg =  new MineSweeperGame();  			
			while (!msg.end) {
				play = msg.reStart;
			}
		}
	}   
}  
