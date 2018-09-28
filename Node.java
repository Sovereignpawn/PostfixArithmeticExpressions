/**
 * The node interface declares the methods that all classes that implement the Node interface must contain. The node interface declares the outline for arithmetic, and traversal
 * of the tree that will be created in ExpressionTree
 */
package example;
public interface Node {
    public double evaluate();
    public String preOrderWalk();
    public String inOrderWalk();
    public String postOrderWalk();
}