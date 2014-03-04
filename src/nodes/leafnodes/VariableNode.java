package nodes.leafnodes;

import turtle.Turtle;

public class VariableNode extends LeafNode{ //TODO a function can be a variable node

    private double myValue;
    private Turtle myTurtle; 
    
    public VariableNode (Turtle turtle) {
        super(turtle);
        myTurtle = turtle;
    }
    
    public VariableNode (Turtle turtle, double value) {
        this(turtle);
        myValue = value;
    }

    @Override
    public double evaluate () {
        return myValue;
    }


}