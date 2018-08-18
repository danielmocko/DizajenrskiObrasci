package drawing.command;

import drawing.DrawingModel;
import geometry.Shape;

public class CommandModify implements Command {

	private DrawingModel model;
	private Shape oldShape;
	private Shape newShape;
	
	public CommandModify(DrawingModel model,Shape oldShape, Shape newShape) {
		this.model=model;
		this.oldShape=oldShape;
		this.newShape=newShape;
	}
	
	@Override
	public void execute() {
		model.remove(oldShape);
		model.add(newShape);
	}

	@Override
	public void unexecute() {
		model.remove(newShape);
		model.add(oldShape);
	}

}
