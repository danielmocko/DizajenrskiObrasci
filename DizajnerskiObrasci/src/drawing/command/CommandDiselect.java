package drawing.command;

import drawing.DrawingModel;

public class CommandDiselect implements Command{

	private DrawingModel model;
	private int index;
	
	public CommandDiselect(DrawingModel model, int index) {
		this.model=model;
		this.index=index;
	}
	
	@Override
	public void execute() {
		model.diselectObject(index);
		
	}

	@Override
	public void unexecute() {
		model.selectObject(index);
	}

}
