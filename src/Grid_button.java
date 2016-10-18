import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Grid_button extends JButton {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int fX;
    private final int fY;

    public Grid_button(final int x, final int y) {
        fX= x;
        fY= y;
        //fModel= model;

        addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
            	boolean text = false;
            	if (TicTacToe.turn == 1 && getText()=="")
            	{
            		setText("X");
            		text=true;
            		
            	}
            	else if (getText()=="")
            	{
            		setText("O");
            		text = true;
            		
            	}
            	if (text){
	            	try {
						TicTacToe.user_vs_user(fX,fY);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	TicTacToe.turn = TicTacToe.turn*-1;
            	}
            }

        });

    }

}
