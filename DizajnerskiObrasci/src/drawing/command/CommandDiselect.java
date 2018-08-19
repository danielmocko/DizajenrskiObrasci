package drawing.command;

import drawing.DrawingModel;
import geometry.Shape;

public class CommandDiselect implements Command{

	private DrawingModel model;
	private Shape shape;
	
	public CommandDiselect(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	@Override
	public void execute() {
		model.diselectObject(shape);
	}

	@Override
	public void unexecute() {
		model.selectObject(shape);
	}
}
