/**
 * ExpressionTree class initializes the GUI that accepts a postfix expression, contains the algorithm that converts the supplied postfix expression into a 
 * prefix expression with stacks through creating and traversing a tree, sets a label in the GUI to the fully parenthesized, correct prefix expression.
 * ExpressionTree contains the core functionalities and extends into all other supplementary classes throughout the proejct.
 */
package example;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ExpressionTree extends JFrame implements ActionListener{
	
	JFrame frame = new JFrame();
	private JLabel instructions = new JLabel("Enter Postfix Expression", SwingConstants.LEFT);
	private JLabel result = new JLabel("Infix Expression", SwingConstants.LEFT);
	private static JTextField expressionToSend = new JTextField(20);
	private JButton evaluate = new JButton("Construct Tree");
	private static JTextField answer = new JTextField(20);
	private JPanel topPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private JPanel middlePanel = new JPanel();
	private static char offender;

	ExpressionTree(){
		
		super("Three Address Generator");
		topPanel = new JPanel(new GridLayout(0, 2));
		bottomPanel = new JPanel(new GridLayout(0, 3));
		middlePanel = new JPanel(new FlowLayout());
		setLayout(new GridLayout(3, 1));
		evaluate.addActionListener(this);
		answer.setEditable(false);
		add(topPanel);
		add(middlePanel);
		add(bottomPanel);
		topPanel.add(instructions);
		topPanel.add(expressionToSend);
		middlePanel.add(evaluate);
		bottomPanel.add(result);
		bottomPanel.add(answer);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) throws RuntimeException {
		String expression = expressionToSend.getText();
			try
			{
				if(invalidValue(expression)) {
					JOptionPane.showMessageDialog(frame, "Invalid Token " + offender, "ERROR", JOptionPane.ERROR_MESSAGE);
					throw new RuntimeException();
				}
			}
			
			catch(RuntimeException invalidToken) {
			invalidToken.printStackTrace();	
			System.exit(0);
			}
		
	    Node result = createTree(expression);
	    answer.setText(result.inOrderWalk());
	    
	    }
		
	public static boolean invalidValue(String expression) {
		for(int i = 0; i < expression.length(); i++) {
			if(!isOperator(expression.charAt(i)) && !isInteger(expression.substring(i, i + 1))) {
				offender = expression.charAt(i);
				return true;
			}
		}
		return false;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    }
	    catch(NumberFormatException e) { 
	        return false; 
	    }
	    catch(NullPointerException e) {
	        return false;
	    }
	    
	    return true;
	}
	

	public static void main(String[] args) {
		
		ExpressionTree gui = new ExpressionTree();
		gui.setVisible(true);
		gui.setSize(600, 200); 
			
		
	  }
	
	  public static boolean isOperator(char string) {
		  if(string == '+' || string == '-' || string == '*' || string == '/') {
			  return true;
		  }
		  return false;
	 }
	  
	  public static Node createTree(String expression) {
		  Stack <Double> operand = new Stack <Double> ();
		  Stack <Node> operator = new Stack <Node> ();
		  Node tree;
		  
		  for(int i = 0; i < expression.length() ; i++) {
			  if(isOperator(expression.charAt(i))) {
				
				
				  if(expression.charAt(i) == '*') {
					  try {
						  
						  if(operand.size() >= 3 && operator.size() == 1 ) {
							  tree = new OperatorNode(new MulOperator(), operator.pop(), new OperandNode(operand.pop()));
							  operator.push(tree);
							  continue;
						  }
						  
							  tree = new OperatorNode(new MulOperator(), new OperandNode(operand.pop()),  new OperandNode(operand.pop()));
							  operator.push(tree);
							  
					   }
					   
					  catch(EmptyStackException weNeedToPopANode) {
						  
						  
						  try {
							  
							  operand.push(Double.parseDouble(expression.substring(i-1, i)));
							  tree = new OperatorNode(new MulOperator(), new OperandNode(operand.peek()),  operator.peek());
							  operator.push(tree);  	
							  
						  }
						  
						  catch(NumberFormatException weNeedTwoNodes) {
							
							  try
							  {
							
								  tree = new OperatorNode(new MulOperator(), operator.pop(),  operator.peek());
								  operator.push(tree);
								  
							  }
							 catch(EmptyStackException weNeedtoPopANumber) {
								 
								 
								 if(!operand.isEmpty() || !operator.isEmpty()) {			 
								 tree = new OperatorNode(new MulOperator(), operator.pop(),  new OperandNode(operand.peek()));
								 operator.push(tree);
								 }
						
							 }
						  }
					  }
							  
				  }	  
					  
				   else if(expression.charAt(i) == '/') {
					   try {
							    if(operand.size() == 1 && operator.size() == 1) {
									  tree = new OperatorNode(new DivOperator(), operator.pop(), new OperandNode(operand.pop()));
									  operator.push(tree);
									  continue;
								  }
						  tree = new OperatorNode(new DivOperator(), new OperandNode(operand.pop()),  new OperandNode(operand.pop()));
						  operator.push(tree);
						   }
						 
						  catch(EmptyStackException weNeedToPopANode) {
							  
							 
							  try {
								 
								  operand.push(Double.parseDouble(expression.substring(i-1, i)));
								  tree = new OperatorNode(new DivOperator(), new OperandNode(operand.peek()),  operator.peek());
								  operator.push(tree);
								  
							  }
							  
							  catch(NumberFormatException weNeedTwoNodes) {
								
								  try
								  {
									
									  tree = new OperatorNode(new DivOperator(), operator.pop(),  operator.peek());
									  operator.push(tree);
									  
								  }
								 catch(EmptyStackException weNeedtoPopANumber) {
									
									 
									 if(!operand.isEmpty() || !operator.isEmpty()) {			 
									 tree = new OperatorNode(new DivOperator(), operator.pop(),  new OperandNode(operand.peek()));
									 operator.push(tree);
									 }
								
								 }
							  }
						  }
				  }
				  
				   else if(expression.charAt(i) == '+') {
					   try {
						   
							  if(operand.size() == 1 && operator.size() == 1) {
								  tree = new OperatorNode(new AddOperator(), operator.pop(), new OperandNode(operand.pop()));
								  operator.push(tree);
								  continue;
							  }
							  
							  tree = new OperatorNode(new AddOperator(), new OperandNode(operand.pop()),  new OperandNode(operand.pop()));
							  operator.push(tree);
						  
						   }
						
						   
						  catch(EmptyStackException weNeedToPopANode) {
							   
							  try {
								  
								  operand.push(Double.parseDouble(expression.substring(i-1, i)));
								  tree = new OperatorNode(new AddOperator(), new OperandNode(operand.peek()),  operator.peek());
								  operator.push(tree);
								  	
							  }
							  
							  catch(NumberFormatException weNeedTwoNodes) {
								  
								  try
								  {
									  tree = new OperatorNode(new AddOperator(), operator.pop(),  operator.peek());
									  operator.push(tree);
									  
								  }
								 catch(EmptyStackException weNeedtoPopANumber) { 
									
									 if(!operand.isEmpty() || !operator.isEmpty()) {			 
									 tree = new OperatorNode(new AddOperator(), operator.pop(),  new OperandNode(operand.peek()));
									 operator.push(tree);
									
									 }
								
								 }
							  }
						  }
				   }
				   
				   else if(expression.charAt(i) == '-'){
					   try {
						   
							  if(operand.size() == 1 && operator.size() == 1) {
								  tree = new OperatorNode(new SubOperator(), operator.pop(), new OperandNode(operand.pop()));
								  operator.push(tree);
								  continue;
							  }
						  tree = new OperatorNode(new SubOperator(), new OperandNode(operand.pop()),  new OperandNode(operand.pop()));
						  operator.push(tree);
						 
						   }
						   
						  catch(EmptyStackException weNeedToPopANode) {
							  
							  try {
								  
								  operand.push(Double.parseDouble(expression.substring(i-1, i)));
								  tree = new OperatorNode(new SubOperator(), new OperandNode(operand.peek()),  operator.peek());
								  operator.push(tree);

							  }
							  
							  catch(NumberFormatException weNeedTwoNodes) {
								  try
								  {
									  
									  tree = new OperatorNode(new SubOperator(), operator.pop(),  operator.peek());
									  operator.push(tree);
									  
								  }
								 catch(EmptyStackException weNeedtoPopANumber) {
									 
									 if(!operand.isEmpty() || !operator.isEmpty()) {			 
									 tree = new OperatorNode(new SubOperator(), operator.pop(),  new OperandNode(operand.peek()));
									 operator.push(tree);
									 }
							
								 }
							  }
						  }
				   }
			  }
			  
			  else {
				  
				 operand.push(Double.parseDouble(expression.substring(i, i + 1)));
				 continue;
				 
			  }
			  
		  }
		  
		  return operator.peek();
		  
	  }	
}