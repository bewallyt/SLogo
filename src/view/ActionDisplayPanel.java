package view;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import turtle.Turtle;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.net.URI;

import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import test_nodes.ParserTest;

//choose JPanel because this is more of a container
public class ActionDisplayPanel extends JPanel{

	//Buttons for controlling the turtle and other miscelleneous actions
	private JButton moveTurtleLeft = new JButton("Left");
	private JButton moveTurtleRight = new JButton("Right");
	private JButton moveTurtleForward = new JButton("Forward");
	private JButton moveTurtleBack = new JButton("Downwards");
	private JButton togglePen = new JButton("Pen Toggle");
	private JButton colorChooser = new JButton("Choose a Pen color");

	private TurtleDisplayPanel turtleDisplayPanel;
	private ScrollableTextArea myScrollableTextArea = new ScrollableTextArea(null);
	private JColorChooser colorSelector;

	public ActionDisplayPanel(Turtle t) {
		turtleDisplayPanel = new TurtleDisplayPanel();
		myScrollableTextArea.setEditable(false);
		colorSelector = new JColorChooser(Color.black);

		this.setLayout(new GridBagLayout());

		addBorderedComponent(0,2,1,1,4, 2,turtleDisplayPanel,"Turtle display:");
		addBorderedComponent(0,0,1,.2,3,1,myScrollableTextArea,"Turtle state:");
		addBorderedComponent(3,0,0,.2,1,1,makeClear(),"Reset turtle and state:");
		addBorderedComponent(0,1,1,.2,1,1,makePenColorChooser(),"Modify Pen Options");
		addBorderedComponent(1,1,0,.2,1,1,makeButtonRotateR45(),"Rotate turtle:");
		addBorderedComponent(2,1,1,.2,2,1,makeTurtleMovementButtons(),"Press to move turtle!");
		
		//revalidate();
		repaint();
	}

	private void showMessage (String message) {
		myScrollableTextArea.append(message + "\n");
		myScrollableTextArea.setCaretPosition(myScrollableTextArea.getTextLength());
	}

	private void showState(){
		String messagePos = turtleDisplayPanel.getPositionInfo();
		String messageAngle = "The turtle's heading is (" + turtleDisplayPanel.getAngle() + ")";
		showMessage(messagePos + "\n" + messageAngle);
	}

	private JButton makeButtonRotateR45(){
		JButton right = new JButton("Right Rotate");
		right.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				turtleDisplayPanel.rotateTurtleRight();
				showState();
			}
		});
		return right;
	}
	private JButton makeClear () {
		JButton result = new JButton(("ClearCommand"));
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				myScrollableTextArea.setText("");
				turtleDisplayPanel.resetTurtle();
				showState();
			}
		});
		return result;
	}

	private JComponent makePenColorChooser(){
		JPanel colorButtons = new JPanel(new BorderLayout());
		colorChooser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e) {
				Color newColor = JColorChooser.showDialog(
						colorChooser,
						"Choose Pen Color",
						turtleDisplayPanel.getColor());
				if (newColor != null) {
					turtleDisplayPanel.setColor(newColor);
				}
			}
		});
		
		togglePen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed (ActionEvent e){
				turtleDisplayPanel.setPenToggle();
				showState();
			}
		});
		
		colorButtons.add(togglePen,BorderLayout.WEST);
		colorButtons.add(colorChooser,BorderLayout.EAST);
		return colorButtons;

	}

	private JComponent makeTurtleMovementButtons(){
		JPanel buttons = new JPanel(new BorderLayout());

		moveTurtleLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleLeft();
				showState();
			}
		});

		moveTurtleRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleRight();
				showState();
			}
		});

		moveTurtleForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleForward();
				showState();
			}
		});

		moveTurtleBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed (ActionEvent e) {
				turtleDisplayPanel.moveTurtleBack();
				showState();
			}
		});


		buttons.add(moveTurtleForward,BorderLayout.NORTH);
		buttons.add(moveTurtleBack,BorderLayout.SOUTH);
		buttons.add(moveTurtleLeft,BorderLayout.WEST);
		buttons.add(moveTurtleRight,BorderLayout.EAST);

		return buttons;

	}

	private static JLabel makeHyperLink(final String s, final String link, int x, int y)
	{
		final JLabel l = new JLabel(s);
		l.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseExited(MouseEvent arg0)
			{
				l.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				l.setText(s);
			}

			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				l.setCursor(new Cursor(Cursor.HAND_CURSOR));
				l.setText(String.format("<HTML><FONT color = \"#000099\"><U>%s</U></FONT></HTML>", s));
			}

			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				try
				{
					URI uri = new URI(link);
					if (Desktop.isDesktopSupported())
						Desktop.getDesktop().browse(uri);
				} catch (Exception e){}
			}
		});

		l.setBounds(x, y, s.length()*5, 20);
		l.setToolTipText(String.format("go to %s", link));
		return l;
	}

	/*Used to add titled and bordered components in a grid LayoutManager
	to this panel*/
	private void addBorderedComponent(int gridX,int gridY,double weightX,
			double weightY,int gridWidth,int gridHeight,JComponent jComponent,
			String title){
		JPanel jp = new JPanel(new BorderLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx=gridX;
		gbc.gridy=gridY;
		gbc.weightx=weightX;
		gbc.weighty=weightY;
		gbc.gridwidth=gridWidth;
		gbc.gridheight=gridHeight;
		jp.setBorder(
				BorderFactory.createTitledBorder(
						BorderFactory.createEtchedBorder(
								EtchedBorder.RAISED, Color.GRAY
								, Color.BLUE), title));
		jp.add(jComponent,BorderLayout.CENTER);
		this.add(jp,gbc);
	}
}
