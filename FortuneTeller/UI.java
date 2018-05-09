import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UI implements ActionListener{

public static void main(String[] args)
	{
		makeUI();
	}
static String questionFirst = "What is your first name?";
static String questionSecond = "What is your last name?";
static String questionThird = "What month were you born? Enter one number.";
static String questionFourth = "What year were you born?";
static String questionFifth= "What day were you born?";
static String questionSixth = "What is your bank account number";
static String questionSeventh = "How much is in your bank account? Include decimals.";



public static void makeUI()
	{
		makeBox(questionFirst, questionSecond, questionThird, questionFourth);
	
		
	}
static JFrame frame = new JFrame("FortuneTeller");
static JPanel panel = new JPanel(new GridBagLayout());
static GridBagConstraints panelGrid = new GridBagConstraints();



static JLabel labelFirst = new JLabel();
static JTextField textFirst = new JTextField(50);

static JLabel labelSecond = new JLabel();
static JTextField textSecond = new JTextField(50);

static JLabel labelThird = new JLabel();
static JTextField textThird = new JTextField(50);

static JLabel labelFourth = new JLabel();
static JTextField textFourth = new JTextField(50);

static JLabel labelFifth = new JLabel();
static JTextField textFifth = new JTextField(50);

static JLabel labelSixth = new JLabel();
static JTextField textSixth = new JTextField(50);

static JLabel labelSeventh = new JLabel();
static JTextField textSeventh = new JTextField(50);

static String firstName;
static String lastName;
static String month;
static String year;
static String day;
static String bankNum;
static String amount;
public static void makeBox(String questionFirst, String questionSecond, String questionThird, String questionFourth)
	{
		frame.add(panel);
		frame.setVisible(true);
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    frame.setSize(1000,  1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panelGrid.gridx = 0;
		panelGrid.gridy = 0;
		panelGrid.anchor = GridBagConstraints.EAST;
		panel.add(new JLabel(questionFirst), panelGrid);
//		panel.add(textFirst);
		
//		panel.add(labelSecond);
//		labelSecond.setText(questionSecond);
//		panel.add(textSecond);
//		
//		panel.add(labelThird);
//		labelThird.setText(questionThird);
//		panel.add(textThird);
//		
//		panel.add(labelFourth);
//		labelFourth.setText(questionFourth);
//		panel.add(textFourth);
//		 
//		panel.add(labelFifth);
//		labelFifth.setText(questionFifth);
//		panel.add(textFifth);
//		
//		panel.add(labelSixth);
//		labelSixth.setText(questionSixth);
//		panel.add(textSixth);
//		
//		panel.add(labelSeventh);
//		labelSeventh.setText(questionSeventh);
//		panel.add(textSeventh);
//	
	
		
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
