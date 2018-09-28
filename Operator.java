/**
 * Operator is an abstract class that contains a single method that will be implemented dynamically for the 4 classes that will adopt its functionality with respect
 * to the operator being created. Operator provides for 4 "subclasses" who's evaluate methods perform the arithmetic on the values supplied to the methods, with a toString
 * method that will aid in the representation of the walks when the tree will be constructed and traversed.
 */
package example;

public abstract class Operator {
	   abstract public double evaluate(double x, double y);
	}

	class AddOperator extends Operator {
	    public double evaluate(double d1, double d2) {
	        return d1 + d2;
	    }
	    
	    public String toString() {
	        return "+";
	    }
	}
	   
	class MulOperator extends Operator {
	    public double evaluate(double d1, double d2) {
	        return d1 * d2;
	    }
	   
	    public String toString() {
	        return "*";
	    }
	}
	   
	class SubOperator extends Operator {
	    public double evaluate(double d1, double d2) {
	        return d1 - d2;
	    }
	    
	    public String toString() {
	        return "-";
	    }
	}
	   
	class DivOperator extends Operator {
	    public double evaluate(double d1, double d2) {
	        return d1 / d2;
	    }
	    
	    public String toString() {
	        return "/";
	    }
	}
	
	
	

