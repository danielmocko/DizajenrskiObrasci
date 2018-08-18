package drawing;

import geometry.Shape;

public class CommandRemove implements Command{

	private DrawingModel model;
	private Shape shape;
	
	
	public CommandRemove(DrawingModel model, Shape shape) {
		this.model=model;
		this.shape=shape;
	}
	
	
	@Override
	public void execute() {
		model.remove(shape);
		
	}

	@Override
	public void unexecute() {
		model.add(shape);
	}

}
