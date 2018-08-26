package drawing.command;

import drawing.mvc.Model;
import geometry.Shape;

public class CommandDiselect implements Command{

	private Model model;
	private Shape shape;
	
	public CommandDiselect(Model model, Shape shape) {
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
