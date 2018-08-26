package drawing.command;

import drawing.mvc.Model;
import geometry.Shape;

public class CommandBringToFront implements Command {

	private Model model;
	private Shape shape;
	private int index;
	
	public CommandBringToFront(Model model,Shape shape, int index) {
		this.model=model;
		this.shape=shape;
		this.index=index;
	}

	public void execute() {
	
		for(int j=index+1;j<model.getShapes().size();j++) {
			Shape start = model.getShapes().get(j);
			model.change(j-1,start);
		}
		model.change(model.getShapes().size()-1, shape);
	}

	public void unexecute() {
		int size =model.getShapes().size()-1;
		Shape current = model.getShapes().get(size);
		for(int j=size-1;j>=0;j--) {
			if(j==index) {
				Shape next = model.getShapes().get(j);
				model.change(j+1, next);
				model.change(index, current);
				return;
			}
			else {
				Shape next = model.getShapes().get(j);
				model.change(j+1, next);
			}
		}
	}
}
