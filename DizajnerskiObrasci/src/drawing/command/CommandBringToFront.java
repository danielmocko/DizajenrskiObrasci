package drawing.command;

import drawing.DrawingModel;
import geometry.Shape;

public class CommandBringToFront implements Command {

	private DrawingModel model;
	
	public CommandBringToFront(DrawingModel model) {
		this.model=model;
	}

	public void execute() {
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=0;i<length;i++) {
				if(model.getShapes().get(i).isSelected()) {
					if( i < length) {
						Shape current = model.getShapes().get(i);
						for(int j=i+1;j<length;j++) {
							Shape start = model.getShapes().get(j);
							model.change(j-1,start);
						}
						model.change(length-1, current);
						model.addToLogList("Bringed to front --->"+current);
						
						return;
					}
				}	
			}
		}
	}

	public void unexecute() {
		int length = model.getShapes().size();
		if(length>1) {
			for(int i=length-1;i>=0;i--) {
				if(model.getShapes().get(i).isSelected()) {
					if( i != 0) {
						Shape current = model.getShapes().get(i);

						for(int j=i-1;j>=0;j--) {
							Shape start = model.getShapes().get(j);
							model.change(j+1,start);
						}
						model.change(0, current);
						model.addToLogList("Bringed to back --->"+current);
						
						return;
					}
				}
			}
		}
	}
	
	
}
