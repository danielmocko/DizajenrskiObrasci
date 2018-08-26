package drawing.command;

import drawing.mvc.Model;
import geometry.Shape;

public class CommandBringToBack implements Command {

	private Model model;
	private Shape shape;
	private int index;

	public CommandBringToBack(Model model,Shape shape,int index) {
		this.model=model;
		this.shape=shape;
		this.index=index;
	}

	@Override
	public void execute() {
		for(int j=index-1;j>=0;j--) {
			Shape start = model.getShapes().get(j);
			model.change(j+1,start);
		}
		model.change(0, shape);
	}

	@Override
	public void unexecute() {
		Shape current = model.getShapes().get(0);
		for(int j=1;j<model.getShapes().size();j++) {
			if(j==index) {
				Shape next = model.getShapes().get(j);
				model.change(j-1, next);
				model.change(index, current);
				return;
			}
			else {
				Shape next = model.getShapes().get(j);
				model.change(j-1, next);
			}
		}	
	}
}

