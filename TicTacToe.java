import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class TicTacToe {
	
    private static Grid_button[][] t = new Grid_button[3][3];
    static int turn = 1;
    private static JLabel result = new JLabel (); 
    private static int c1;
    private static int c2;
    private static String user1 ;
    private static String user2 ;
    private static Board grid = new Board();
    static JButton user;
    static JButton computer;
    static JButton user_vs_ai;
    static JButton cpu_vs_ai;
    static Timer timer ;
    
	//
    public static void make_button_unclickable () {
    	for (int i =0 ;i<3;i++) {
    		for (int j=0 ; j<3 ;j++) {
    			t[i][j].setBorderPainted( false );
    			t[i][j].setFocusPainted( false );
    		}
    	}
    }
    
	//
    public static void make_button_clickable (){
    	for (int i =0 ;i<3;i++) {
    		for (int j=0 ; j<3 ;j++) {
    			t[i][j].setBorderPainted( true );
    			t[i][j].setFocusPainted( true );
    			t[i][j].setText("");
    			t[i][j].setForeground(Color.BLACK);
    		}
    	}
    	grid = new Board();
    }
    
    
	//
    // WHEN GRID BUTTON IS CLICKED
	public static void user_vs_user (int x1 , int y1 ) throws InterruptedException  {
		if (c1 == 1 && c2 == 1 ) {
			if (turn == 1) {
				grid.move(x1, y1, 'X');
				result.setText(user2 + "'s TURN");
			}
			else {
				grid.move(x1, y1, 'O');
				result.setText(user1 + "'s TURN");
			}	
			if (grid.status()==1) {
				if (turn == -1)
					result.setText(user2 + " WINS");
				else 
					result.setText(user1 + " WINS");
				user.setForeground(Color.BLACK);
				make_button_unclickable ();
				return;
			}
			else if (grid.status()==3) {
				result.setText("IT'S A TIE");
				user.setForeground(Color.BLACK);
				make_button_unclickable ();
				return;
			}
		}
		else if (c1 == 1 && c2 == 0) {
			grid.move(x1, y1, 'X');
			if (grid.status()==1) {
				if (turn == -1)
					result.setText("COMPUTER WINS");
				else 
					result.setText(user1 + " WINS");
				computer.setForeground(Color.BLACK);
				make_button_unclickable ();
				return;
			}
			else if (grid.status()==3) {
				result.setText("IT'S A TIE");
				computer.setForeground(Color.BLACK);
				make_button_unclickable ();
				return;
			}
			Random s = new Random();
			int rx = s.nextInt(3);
			int ry = s.nextInt(3);
			while (!grid.move(rx, ry, 'O')){
				rx = s.nextInt(3);
				ry = s.nextInt(3);
			}
			t[rx][ry].setText("O");
			turn = turn*-1;
			if (grid.status()==1) {
				if (turn == -1)
					result.setText("COMPUTER WINS");
				else 
					result.setText(user1 + " WINS");
				computer.setForeground(Color.BLACK);
				make_button_unclickable ();
				return;
			}
			else if (grid.status()==3) {
				result.setText("IT'S A TIE");
				computer.setForeground(Color.BLACK);
				make_button_unclickable ();
				return;
			}
			
		}
		else if (c1==0 && c2==1) {
			//while (grid.status()==2){
			result.setText("COMPUTER'S TURN");
				timer = new Timer (1000,new ActionListener(){
					
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if (turn == 1){
							Random s = new Random();
							int rx = s.nextInt(3);
							int ry = s.nextInt(3);
							while (!grid.move(rx, ry, 'X')){
								rx = s.nextInt(3);
								ry = s.nextInt(3);
							}
							t[rx][ry].setText("X");
							turn = turn*-1;
							result.setText("AI'S TURN");
							if (grid.status()==1) {
								if (turn == -1)
									result.setText("COMPUTER WINS");
								else 
									result.setText("AI WINS");
								timer.stop();
								cpu_vs_ai.setForeground(Color.BLACK);
								make_button_unclickable ();
								return;
							}
							else if (grid.status()==3) {
								result.setText("IT'S A TIE");
								cpu_vs_ai.setForeground(Color.BLACK);
								make_button_unclickable ();
								timer.stop();
								return;
							}
						}	
						// code for AI
						else {
							ArrayList<Integer> cord = new ArrayList<>();
							cord = grid.minimax('O');
							t[cord.get(0)][cord.get(1)].setText("O");
							turn = turn*-1;
							if (grid.status()==1) {
								if (turn == -1)
									result.setText("COMPUTER WINS");
								else 
									result.setText("AI WINS");
								timer.stop();
								cpu_vs_ai.setForeground(Color.BLACK);
								make_button_unclickable ();
								return;
							}
							else if (grid.status()==3) {
								timer.stop();
								result.setText("IT'S A TIE");
								cpu_vs_ai.setForeground(Color.BLACK);
								make_button_unclickable ();
								return;
							}
							result.setText("COMPUTER'S TURN");
						}	
						
					}
				});
				timer.start();			
		}
		else if (c1==0 && c2 == 0) {
			grid.move(x1, y1, 'X');
			if (grid.status()==1) {
				if (turn == 1)
					result.setText(user1 + " WINS");
				else 
					result.setText("AI WINS");
				user_vs_ai.setForeground(Color.BLACK);
				make_button_unclickable ();
				return;
			}
			else if (grid.status()==3) {
				result.setText("IT'S A TIE");
				make_button_unclickable ();
				user_vs_ai.setForeground(Color.BLACK);
				return;
			}
			ArrayList<Integer> cord = new ArrayList<>();
			cord = grid.minimax('O');
			t[cord.get(0)][cord.get(1)].setText("O");
			turn = turn*-1;
			if (grid.status()==1) {
				if (turn == 1)
					result.setText(user1 + " WINS");
				else 
					result.setText("AI WINS");
				make_button_unclickable ();
				user_vs_ai.setForeground(Color.BLACK);
				return;
			}
			else if (grid.status()==3) {
				result.setText("IT's A TIE");
				make_button_unclickable ();
				user_vs_ai.setForeground(Color.BLACK);
				return;
			}
		}
	}
	
	// WINDOW TO TAKE USER NAMEpublic static void user_names () {
		JFrame frame3 = new JFrame();
		frame3.setDefaultCloseperation(JFrame.DISPOSE_ON_CLOSE);
		frame3.setSize(300,200);
		Container user_name1 = new JPanel();
		
		if (c1 == 1 && c2 ==1)
			user_name1.setLayout(new GridLayout(2, 1));
		else
			user_name1.setLayout(new GridLayout(1, 1));
		
		user_name1.setPreferredSize(new Dimension (10,10));
		Box user_box1 = Box.createHorizontalBox();
		JLabel username1 = new JLabel("user1 ");
		JTextField textField1 = new JTextField(20);
		
		textField1.setMaximumSize( textField1.getPreferredSize() );
		user_name1.add(username1);
		user_name1.add(textField1);
		user_box1.add(user_name1);
		
		JLabel username2 = new JLabel("user2 ");
		JTextField textField2 = new JTextField(20);
		textField2.setMaximumSize( textField2.getPreferredSize() );
		
		if (c1==1 && c2==1){
			textField2.setPreferredSize(new Dimension (25,5));
			user_name1.add(username2);
			user_name1.add(textField2);
			user_box1.add(user_name1);
		}

		JButton ok = new JButton("OK");
		Box button_box = Box.createHorizontalBox();
		button_box.add(Box.createHorizontalStrut(100));
		button_box.add(ok);
		ok.addActionListener(new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				user1 = textField1.getText();

				if (c1==1 && c2 ==1)
					user2 = textField2.getText();
				
				if ((c1 !=1 || c2!=1) && !textField1.getText().equals("")) {
					result.setText(user1 + "'s turn");
					frame3.dispose();
				}
				else if (!textField1.getText().equals("") && !textField2.getText().equals("")){
					result.setText(user1 + "'s turn");
					frame3.dispose();
				}
				else {
					JFrame parent = new JFrame();
					JOptionPane.showMessageDialog(parent, "ENTER TEXT");
				}
			}
		});

	    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,user_box1, button_box){   
	    		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private final int location = 80;
			{
		        setDividerLocation( location );
		    }
		    @Override
		    public int getDividerLocation() {
		        return location ;
		    }
		    @Override
		    public int getLastDividerLocation() {
		        return location ;
		    }
	    };
	    frame3.add(splitPane);
		frame3.setVisible(true);
	}
	
	//
	//ADD NEW WINDOW WHEN START BUTTON IS CLICKED
	public static void add_new_window () {
		JFrame frame2 = new JFrame("GAME");
		Box menu_box = Box.createVerticalBox();
	    menu_box.add(Box.createVerticalStrut(25));
	    menu_box.add(Box.createVerticalStrut(25));
	    
	    user = new JButton("USER1 vs USER2");
	    user.setMaximumSize(new Dimension(Integer.MAX_VALUE, user.getMinimumSize().height));
	    user.setSize(50, 50);
		user.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c1=1;
				c2=1;
				turn = 1;
				user.setForeground(Color.GRAY);
				user_names();
				make_button_clickable ();

			}
		});

	    menu_box.add(user);
	    
	    computer = new JButton("USER vs CPU");
	    computer.setMaximumSize(new Dimension(Integer.MAX_VALUE, computer.getMinimumSize().height));
	    computer.setSize(50, 50);
		computer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c1=1;
				c2=0;
				computer.setForeground(Color.GRAY);
				user_names();
				make_button_clickable ();
				turn = 1;
			}
		});

	    menu_box.add(computer);
	    cpu_vs_ai = new JButton("CPU vs AI");
	    cpu_vs_ai.setMaximumSize(new Dimension(Integer.MAX_VALUE, cpu_vs_ai.getMinimumSize().height));
	    cpu_vs_ai.setSize(50, 50);
	    menu_box.add(cpu_vs_ai);
		cpu_vs_ai.addActionListener(new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c1=0;
				c2=1;
				//user_names();
				cpu_vs_ai.setForeground(Color.GRAY);
				make_button_clickable ();
				turn = 1;
				try {
					user_vs_user(0, 0);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	    user_vs_ai = new JButton("USER vs AI");
	    user_vs_ai.setMaximumSize(new Dimension(Integer.MAX_VALUE, user_vs_ai.getMinimumSize().height));
	    user_vs_ai.setSize(50, 50);
		user_vs_ai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				c1=0;
				c2=0;
				user_vs_ai.setForeground(Color.GRAY);
				turn = 1;
				user_names();
				make_button_clickable ();
				//user_vs_user(0, 0);
			}
		});

	    menu_box.add(user_vs_ai);
	    menu_box.add(Box.createVerticalStrut(50));
	    JButton exit = new JButton("EXIT");
	    exit.setMaximumSize(new Dimension(Integer.MAX_VALUE, exit.getMinimumSize().height));
	    exit.setPreferredSize(new Dimension (50,50));
	    menu_box.add(exit);
		exit.addActionListener(new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					frame2.dispose();
			}
		});

	    Container grid_panel = new JPanel();
	    grid_panel.setLayout(new GridLayout(3, 3));
	    Box grid_box = Box.createVerticalBox();
	    for(int r = 0; r < 3; r++) {
	        for(int c = 0; c < 3; c++) {
	            t[r][c] = new Grid_button(r,c);
	            grid_panel.add(t[r][c]);
	        }
	        grid_box.add(grid_panel);
	    }

	    TitledBorder title;
	    title = BorderFactory.createTitledBorder("OUTPUT");
	    title.setTitleJustification(TitledBorder.CENTER);
        
        Container result_panel = new JPanel();
        result_panel.setBackground(Color.WHITE);
        ((JComponent) result_panel).setBorder(title);
        result_panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, result_panel.getMinimumSize().height));
        result_panel.setPreferredSize(new Dimension (50,50));
        result.setText("SELECT AN OPTION");
        make_button_unclickable();
        grid_box.add(Box.createVerticalStrut(50));
        result.add(Box.createHorizontalStrut(25));
        result_panel.add(result);
        grid_box.add(result_panel);
        grid_box.add(Box.createVerticalStrut(50));
	    
	    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,menu_box, grid_box);
	    frame2.add(splitPane);
		frame2.setSize(500,400);
		frame2.setVisible(true);
	}

	// MAIN FUNCTION AND HOME FRAME	
	public static void main(String[] args) {
		// menu frame
		JFrame frame = new JFrame ();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		// add box to frame
		Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 18);
	    Box box = Box.createVerticalBox();
	    box.add(Box.createVerticalStrut(25));
	    JLabel label1 = new JLabel ("TIC TAC TOE");
	    label1.setFont(myFont);
	    box.add(Box.createVerticalStrut(25));
	    label1.setAlignmentX(Component.CENTER_ALIGNMENT);
	    box.add(label1);
	    box.add(Box.createVerticalStrut(25));
	    JButton start = new JButton("START GAME");
		start.addActionListener(new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				add_new_window();
				frame.dispose();
				
			}
		});

	    start.setAlignmentX(Component.CENTER_ALIGNMENT);
	    box.add(start);
	    box.add(Box.createVerticalStrut(25));
	    JButton end = new JButton("EXIT");
		end.addActionListener(new ActionListener()  {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
				
			}
		});

	    end.setAlignmentX(Component.CENTER_ALIGNMENT);
	    box.add(end);
	    frame.add(box);
		frame.setVisible(true);
	}

	public static void setcolor(int i1, int i2, int j1, int j2, int k1, int k2) {
		// TODO Auto-generated method stub
		t[i1][i2].setForeground(Color.RED);
		t[j1][j2].setForeground(Color.RED);
		t[k1][k2].setForeground(Color.RED);
	}
}