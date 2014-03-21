#slogo

##SLogo Design Document

###Introduction

We believe that model-view-controller would provide the optimal user interface software pattern for this project; however, at this moment we will just group code into either front end or back end. Our goal is to code an interface that allows the user to make calls - both user-defined and provided by SLogo - in order to control the movements of a turtle on screen. 


###Front End

The frontend is simply the graphical user interface onto which the user will type an input and be displayed an output. The GUI will display multiple components. For instance, there will exist a command line where the user will type. There will also exist a display grid where the turtle will move. Moreover we can implement an error checker, which can display logical and syntactical errors. 

###Back End

The backend is responsible for logic and other necessary components not visible to the user but critical to correct functionality of our IDE. 
One of the most integral components of the backend is the code parser. In order to parse the code we decided to first translate all of the user inputed code into a single string which we iterate through creating nodes where necessary (ie at methods, variables, etc.).  We also opted to use a syntax tree to hold all of these nodes.

###Packages

####View Package: 
######Class Panel: has a submit button, text field, label to display command history (uses jswing - can use JFrame to update frame), pop-up window to display error, and a canvas

Instance variable: myParser
Instance variable: myTurtlle (initially set by default to lie in the center of the canvas)
- void drawTrailLine(oldXPos, old YPos, newXpos, newYpos): draw a trail line from 
  previous x and y coordinate of the turtle and the new x and y coordinate of the turtle
- void rotateTurtle()

####View: 5 packages: view, turtle_graphics, preferences,functionStorage, and main. view.menuComponent is a sub-package of the view package
	Talal, Chad, and Viju


####Controller:
	Benson and Talal


####Model: 3 main packages: model, nodes, and turtle; within package nodes, there are sub-packages of different types of nodes

#####Package turtle:

######Class Turtle extends AbstractModel implements ITurtle: (Benson)

######interface ITurtle: (Benson)

######abstract class AbstractModel: (Benson)

#####Package model:

######Class Parser: 

	Constructor(s):
	public Parser(List<Turtle> turtles, String commands, String language)
	
	Public Methods:
	public boolean isValid(): return true if the commands are valid syntax
	public double doParse(): call this method in class Model; return value to display in the view
	public AbstractNode createTree(): create an Abstract Syntax Tree; return the root
	public double traverseTree(AbstractNode root): traverse an Abstract Syntax tree; return value to display in the view
	public List<String> getVariables: return a list of variables
	
	
######Class Model:

	Constructor(s):
	public Model()
	
	Public Methods:
	public List<Turtle> getTurtles()
	public List<String> getVariables()
	public double processCommands(List<Turtle> turtles, String commands, String language): process commands and return value to display
	
	
######Class CommandFinder: (Benson)

######Class CommandReader: (Benson)

#####Package nodes:
 	
######abstract class AbstractNode: 
	
	Constructor(s):
	public AbstractNode(List<Turtle>)
	
	Public Methods:
	public List<Turtle> getTurtles()
	public void setTurtles(List<Turtle> turtles)
	public AbstractNode getLeftNode()
	public AbstractNode getRightNode()
	public AbstractNode getParent()
	public void setLeftNode(AbstractNode node)
	public void setRightNode(AbstractNode node)
	public void setParent(AbstractNode node)
	public void addChild(AbstractNode node)
	public boolean hasChild()
	public List<AbstractNode> getChildren()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChilren()
	public boolean hasOneConditionOneBlock(): return true when one condition node and one block node are current node's child nodes
	public boolean hasTwoBlockNodes(): return true when two block nodes are current node's child nodes
	public boolean isAlreadyDeclared(): (for function nodes and variable nodes) return true if the function or variable has already been declared
	public void setCurrentValue()
	public String getName(): (for function nodes and variable nodes) return the name of the function or variable
	
	public double evaluate(): return the value of subtree


######class BlockNode:

	Constructor(s):
	public BlockNode(List<Turtle>)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()



######class FunctionNode:

	Constructor(s):
	public FunctionNode(List<Turtle>)
	
	Public Methods:
	public void setName(String name): set function name
	public String getName()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()


######class MakeNode:

	Constructor(s):
	public MakeNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	

######class VariableNode:

	Constructor(s):
	public VariableNode(List<Turtle> turtles, String variabelName)
	public VariableNode(List<Turtle> turtles, String variabelName, double value)
	public VariableNode(List<Turtle> turtles, String variableName, double startingValue, double endingValue, double increment)
	
	Public Methods:
	public void setCurrentValue(double value)
	public void setIncrement(double value)
	public String getName(): return variable name
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()


######class NodeFactory: (Benson)

#####Package nodes.booleannodes: (Benson)

#####Package nodes.commandnodes: (Benson)

#####Package nodes.controlnodes:

######class ConditionNode:

	Constructor(s):
	public ConditionNode(List<Turtle> turtles) 
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class DoTimesNode:

	Constructor(s):
	public DoTimesNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	public boolean hasTwoBlockNodes()
	
	
######class ForNode:

	Constructor(s):
	public ForNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	public boolean hasTwoBlockNodes()
	
	
######class IfElseNode:

	Constructor(s):
	public IfElseNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	
	
######class IfNode:

	Constructor(s):
	public IfNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	public boolean hasOneConditionOneBlock()
	
	
######class RepeatNode:

	Constructor(s):
	public RepeatNode(List<Turtle> turtles)
	
	Public Methods:
	public double evaluate()
	public boolean allowsTwoChildren()
	public boolean allowsMoreThanTwoChildren()
	public boolean hasOneConditionOneBlock()
	
	
#####Package nodes.displaynodes: (Benson)

#####Package nodes.leafnodes: (Benson)

#####Package nodes.mathnodes: (Benson)

#####Package nodes.querynodes: (Benson)

###Example Code


		



###Sub-teams


Viju, Talal, and Chad worked on developing the View and Controller (probably) package which will contain the front-end user interface classes.

Tara and Benson worked on developing the back-end user packages, which include turtle, parse, and nodes.
		
###Why?

We chose to not use JGame for front end because there is a lot of code that is suited for a specific implementation of a game. Also, it might be hard to translate among coordinate systems and retain the information.  Debugging would be very difficult because there would also be a lot of internal game engine code with which we are unfamiliar with. We stuck with Swing and AWT mainly because those were the classes discussed in lecture and talked about within a larger contex of graphics design. 

###Screenshot
https://f.cloud.github.com/assets/4603228/2229071/7ba8d964-9ae6-11e3-9816-5d384c2d8a83.png

###UML Design
Located in the root directory of the master branch.

https://github.com/duke-compsci308-spring2014/slogo_team01/blob/master/UML%20for%20Model.pdf
