package drawing.command;

import drawing.DrawingModel;

public class CommandSelect implements Command{

	private DrawingModel model;
	private int index;
	
	public CommandSelect(DrawingModel model,int index) {
		this.model=model;
		this.index=index;
	}
	
	@Override
	public void execute() {
		model.selectObject(index);
	}

	@Override
	public void unexecute() {
		model.diselectObject(index);
	}

}
