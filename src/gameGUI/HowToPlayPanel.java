package gameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Scott Richards on 07-May-17.
 */
public class HowToPlayPanel extends JPanel
{

	private MainMenuFrame theFrame;
	private GameMenuFrame theOtherFrame;
	private JButton       btnBack;

	public HowToPlayPanel(GameMenuFrame frame)
	{
		theOtherFrame = frame;

		setOpaque(false);
		setFocusable(false);
		theFrame.setLocationRelativeTo(null);
		setVisible(true);
		initPanel();
	}

	public HowToPlayPanel(MainMenuFrame frame)
	{
		theFrame = frame;

		setOpaque(false);
		setFocusable(false);
		theFrame.setLocationRelativeTo(null);
		setVisible(true);
		initPanel();
	}

	private void initPanel()
	{

		Box box = Box.createVerticalBox();
		add(box);
		btnBack = new JButton("BACK");
		btnBack.setMaximumSize(new Dimension(150, 50));
		btnBack.setMinimumSize(new Dimension(150, 50));
		btnBack.setPreferredSize(new Dimension(150, 50));

		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				theFrame.openMainMenu();
			}
		});

		JPanel pnlBackButton = new JPanel();
		pnlBackButton.setFocusable(false);
		pnlBackButton.setOpaque(false);
		//btnBack.setAlignmentX(1000);
		//pnlBackButton.setLayout(null);
		pnlBackButton.add(btnBack);
		//pnlBackButton.setLocation(10,10);
		//pnlBackButton.setVisible(true);
		//pnlBackButton.setHorizontalAlignment(SwingConstants.CENTER);
		box.add(Box.createVerticalGlue());
		box.add(pnlBackButton);

		JPanel instructions = new JPanel();
		String sep = System.lineSeparator();
		//String s = "How to play: <br> Test";
		String str = "<html><br><br>How to play: <br>"
				+ "The aim of the game is to collect kiwis and trap the predators. To move around the playing map, <br>"
				+ "use the w,a,s,d keys on your keyboard. To move onto the next level you need to trap all of the predators <br>"
				+ "OR collect all the kiwis and trap 80% of the predators.<br><br>" + "Different tiles:<br>"
				+ "•	If you land on a kiwi, you can count the kiwi and learn a new fact.<br>"
				+ "•	If you land on a predator, you must trap that predator using tools from your inventory <br>"
				+ "     and you will also learn a new fact<br>"
				+ "•	If you land on a tool, you can collect the tool and save it to your inventory.<br>"
				+ "•	If you land on food or drink, you can collect the corresponding item to gain energy and <br>"
				+ "boost your stamina levels. <br>"
				+ "•	If you land on a hazard, you will either die or your trap will become broken and can only <br>"
				+ "be fixed using the screwdriver.<br> "
				+ "•	If you land on fauna, you can press the examine button to examine what type of fauna it is.<br> "
				+ "•	To move onto the next level, you must find the door and answer a question correctly. <br>"
				+ "     The question will be based on the facts you discovered in that level.<br><br>"
				+ "Make sure to be constantly checking your stamina levels! Enjoy!</html>";
		JLabel label = new JLabel(str);
		label.setSize(500, 500);
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.white);
		instructions.add(label, BorderLayout.CENTER);
		instructions.setOpaque(false);
		instructions.setLocation(10, 10);
		box.add(instructions);

	}
}
