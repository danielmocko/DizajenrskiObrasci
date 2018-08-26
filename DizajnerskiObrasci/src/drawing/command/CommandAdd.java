package drawing.command;

import drawing.mvc.Model;
import geometry.Shape;

public class CommandAdd implements Command {
	
	private Model model;
	private Shape shape;
	
	public CommandAdd(Model model,Shape shape) {
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
