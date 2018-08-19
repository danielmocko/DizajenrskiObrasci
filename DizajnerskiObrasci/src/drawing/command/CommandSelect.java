package drawing.command;

import drawing.DrawingModel;
import geometry.Shape;

public class CommandSelect implements Command{

	private DrawingModel model;
	private Shape shape;
	
	public CommandSelect(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	@Override
	public void execute() {
		model.selectObject(shape);
	}

	@Override
	public void unexecute() {
		model.diselectObject(shape);
	}

}
