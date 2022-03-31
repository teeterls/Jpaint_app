# Jpaint_app
Java Apps Development Final Project

This project consists of a simple Paint-like drawing application developed in Java.
By running the app you will be able to start drawing on the canvas with the tools provided (color, brush thickness, text font, eraser, etc), as well as select part of the canvas, hide or erase.
The list of available shapes to paint is as follows: arc, straight line, circle, oval, square, rectangle, rectangle with rounded vertices, triangle, arrow and star.
Free drawing option is also included.

The operation is as follows:
In order to draw/paint any figure you will first have to select the color and the necessary attributes (e.g. thickness for the brush or font for the text), if they are not initialized, nothing will be painted when the buttons are pressed. The fill and selection are optional at the user's discretion. When you select a group of shapes you have the option to change the fill or color of the shapes.
In addition, in order to paint on the canvas you must press on the initial and final positions, the latter being equal to or smaller than the initial position (i.e., both positions will form a horizontal, vertical downward or diagonal downward line).
If this is not taken into account the figure will not be painted, except for the free stroke which will always be painted because it will follow the direction of the cursor.

As for hiding, showing or deleting groups, you will have to indicate the color of the group first. If you want to modify any type of drawn figure (e.g. square), you will have to select its name from the available list.
If you try to modify figures or groups that have not been previously drawn, the program will do nothing. 
However, if you try to execute any option without the necessary attributes selected, the program will generate an exception. 

Finally, the text and object files corresponding to the drawing will be located in the files folder. 
The executable .jar file is also included.

