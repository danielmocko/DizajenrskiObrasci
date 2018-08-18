package drawing.command;

import drawing.DrawingModel;
import geometry.Shape;

public class CommandToBack implements Command{

	private DrawingModel model;
	
	public CommandToBack(DrawingModel model) {
		this.model=model;
	}
	
	@Override
	public void execute() {	
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=length-1;i>=0;i--) {
				if(model.getShapes().get(i).isSelected()) {
					if( i-1 >= 0) {
						Shape current = model.getShapes().get(i);
						Shape next = model.getShapes().get(i-1);
						model.change(i,next);
						model.change(i-1, current);
						model.addToLogList("Moved one position to back --->"+current);
						return;
					}
				}
			}
		}
	}

	@Override
	public void unexecute() {
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=0;i<length;i++) {
				if(model.getShapes().get(i).isSelected()) {
					if( i+1 < length) {
						Shape current = model.getShapes().get(i);
						Shape next = model.getShapes().get(i+1);
						model.change(i,next);
						model.change(i+1, current);
						model.addToLogList("Moved one position to front --->"+current);
						
						return;
					}
				}
			}
		}
	}
}
