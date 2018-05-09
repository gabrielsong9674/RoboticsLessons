import java.awt.Dimension;
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
		makeBox(questionFirst, questionSecond);
	
		
	}
static JFrame frame = new JFrame();
static JPanel panel = new JPanel();

static JLabel labelFirst = new JLabel();
static JTextField textFirst = new JTextField(50);
static JButton buttonFirst = new JButton();

static JLabel labelSecond = new JLabel();
static JTextField textSecond = new JTextField(50);
static JButton buttonSecond = new JButton();

static JLabel labelThird = new JLabel();
static JTextField texThird = new JTextField(50);
static JButton buttonThird = new JButton();

static JLabel labelFourth = new JLabel();
static JTextField textFourth = new JTextField();
static JButton  buttonFourt = new JButton();

static JLabel labelFifth = new JLabel();
static JTextField textFifth = new JTextField();
static JButton buttonFifth = new JButton();



static String firstName;
static String lastName;
public static void makeBox(String questionFirst, String questionSecond)
	{
		frame.add(panel);
		frame.setVisible(true);
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    frame.setSize(1000,  400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(labelFirst);
		labelFirst.setText(questionFirst);
		panel.add(textFirst);
		panel.add(buttonFirst);
		buttonFirst.setText("Submit");
		panel.add(labelSecond);
		labelSecond.setText(questionSecond);
		panel.add(textSecond);
		panel.add(buttonSecond);
		buttonSecond.setText("Submit");
	
buttonFirst.addActionListener(new ActionListener()
		{
public void actionPerformed(ActionEvent arg0) 
	{
		firstName = textFirst.getText();
		System.out.println(firstName);
	}
});

buttonSecond.addActionListener(new ActionListener()
	{
public void actionPerformed(ActionEvent arg0)
{
		lastName = textSecond.getText();
		System.out.println(lastName);
	}
});
		
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}
