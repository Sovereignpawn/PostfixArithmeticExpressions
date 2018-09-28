/**
 * OperatorNode is the starting point for the creation of a new Node in the tree. Assignments for the values and left/right values begin in this class when new OperatorNode is 
 * called. From here Operator is assigned depending on the specific type of Operator is passed to the constructor, and the left and right Nodes that are first represented in 
 * OperandNode. Traversal and evaluation for the tree begins in this class, and it is also here that the 3Address instructions are generated with each call to the constructor
 * (each new node initialization). 
 */
package example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OperatorNode implements Node {
	private static int count = 0;
    Node left, right;
    Operator operator;
   
    public OperatorNode(Operator operator, Node right,
                        Node left){
        this.operator = operator;
        this.left = left;
        this.right = right;
        
        File writer = new File("threeAddr.txt");
			PrintWriter fileWriter;
			try {
				fileWriter = new PrintWriter(new FileWriter(writer, true));
				
				if(operator.toString().equals("*")) {
					fileWriter.append(("Mul " + "R" + count + " " + left.evaluate() + " " + right.evaluate()));
					count++;
					fileWriter.append(System.lineSeparator());
					fileWriter.close();
				}
				if(operator.toString().equals("/")) {
					fileWriter.append(("Div " + "R" + count + " " + left.evaluate() + " " + right.evaluate()));
					count++;
					fileWriter.append(System.lineSeparator());
					fileWriter.close();
				}
				if(operator.toString().equals("+")) {
					fileWriter.append(("Add " + "R" + count + " " + left.evaluate() + " " + right.evaluate()));
					count++;
					fileWriter.append(System.lineSeparator());
					fileWriter.close();
				}
				if(operator.toString().equals("-")) {
					fileWriter.append(("Sub " + "R" + count + " " + left.evaluate() + " " + right.evaluate()));
					count++;
					fileWriter.append(System.lineSeparator());
					fileWriter.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
    }
    
    public double evaluate() {
        double leftValue = left.evaluate();
        double rightValue = right.evaluate();
        return operator.evaluate(leftValue, rightValue);
    } 
    
    public String preOrderWalk() {
        String leftValue = left.preOrderWalk();
        String rightValue = right.preOrderWalk();
        return "" + operator + " " + leftValue + " "
                  + rightValue;
    }

    public String inOrderWalk() {
        String leftValue = left.inOrderWalk();
        String rightValue = right.inOrderWalk();
        return "(" + leftValue + " " + operator + " "
                   + rightValue + ")";
    }

    public String postOrderWalk() {
        String leftValue = left.postOrderWalk();
        String rightValue = right.postOrderWalk();
        return leftValue + " " + rightValue + " " + operator;
    }

}

   