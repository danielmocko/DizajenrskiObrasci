package drawing.command;

import drawing.mvc.Model;
import geometry.Shape;

public class CommandSelect implements Command{

	private Model model;
	private Shape shape;
	
	public CommandSelect(Model model,Shape shape) {
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
