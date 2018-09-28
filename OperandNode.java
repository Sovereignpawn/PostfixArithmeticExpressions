/**
 * OperandNode accepts a double which represents leaves attached to OperatorNodes. The walks for these leaf nodes simply return the String representation of the single attribute
 * of the node which is its numeric value which is popped from a stack holding many values in ExpressionTree. 
 */
package example;
public class OperandNode implements Node {
    private double value;
    
    public OperandNode(double value) {
        this.value = value;
    }
    
	public double evaluate() {
        return value;
    }
    
    public String preOrderWalk() {
        return String.valueOf(value);
    }

    public String inOrderWalk() {
        return String.valueOf(value);
    }

    public String postOrderWalk() {
        return String.valueOf(value);
    }

}