package drawing.command;

import drawing.DrawingModel;
import geometry.Shape;

public class CommandRemove implements Command{

	private DrawingModel model;
	private Shape shape;
	private int index;
	
	
	public CommandRemove(DrawingModel model, Shape shape,int index) {
		this.model=model;
		this.shape=shape;
		this.index=index;
	}
	
	
	@Override
	public void execute() {
		model.remove(shape);
		
	}

	@Override
	public void unexecute() {
		for(int i=0;i<model.getShapes().size();i++) {
			if(i==index) {
				for(int j=model.getShapes().size()-1;j>=index;j--) {
					if(j==index) {
						Shape current = model.getShapes().get(j);
						model.getShapes().add(j+1, current);
						model.change(index, shape);
						return;
					}else {
						Shape current = model.getShapes().get(j);
						model.getShapes().add(j+1,current);
						
					}
				}
			}
		}
		model.add(shape);
	}

}
