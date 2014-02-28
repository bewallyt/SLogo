package nodes.commandnodes;

import nodes.AbstractNode;
import turtle.Turtle;

public class ForwardNode extends AbstractNode {

	private Turtle myTurtle;

	public ForwardNode(Turtle turtle) {
		super(turtle);
		myTurtle = turtle;
	}

	public void setTurtle(Turtle turtle) {
		myTurtle = turtle;
	}

	/**
	 * Updated by Benson. Not tested Yet. Feel free to change.
	 */
	//TODO
	public void action() {
		double distance = this.getLeftNode().evaluate();
		double angle = myTurtle.getAngle();
		myTurtle.updatePosition(distance*Math.cos(angle*(Math.PI/180)), distance*Math.sin(angle*(Math.PI/180)));
	}

	@Override
	public double evaluate() {
		return 0;
	}

	@Override
	public boolean allowsTwoChildren() {
		return false;
	}

	@Override
	public boolean allowsMoreThanTwoChildren() {
		return false;
	}

}
