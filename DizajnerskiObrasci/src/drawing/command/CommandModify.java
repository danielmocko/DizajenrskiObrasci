package drawing.command;

import drawing.mvc.Model;
import geometry.Shape;

public class CommandModify implements Command {

	private Model model;
	private Shape oldShape;
	private Shape newShape;
	
	public CommandModify(Model model,Shape oldShape, Shape newShape) {
		this.model=model;
		this.oldShape=oldShape;
		this.newShape=newShape;
	}
	
	@Override
	public void execute() {
		int index = model.getIndex(oldShape);
		model.remove(oldShape);
		model.addOnIndex(newShape,index);
		model.selectObject(newShape);
	}

	@Override
	public void unexecute() {
		int index = model.getIndex(newShape);
		model.remove(newShape);
		model.addOnIndex(oldShape, index);
		model.selectObject(oldShape);
		
	}

}
