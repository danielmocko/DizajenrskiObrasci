package drawing;

import geometry.Shape;

public class CommandAdd implements Command {
	
	private DrawingModel model;
	private Shape shape;
	
	public CommandAdd(DrawingModel model,Shape shape) {
		this.model=model;
		this.shape=shape;
	}

	@Override
	public void execute() {
		model.add(shape);
	}

	@Override
	public void unexecute() {
		model.remove(shape);
	}
}
