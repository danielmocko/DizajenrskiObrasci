package drawing.strategy;

import java.io.File;

public class FileContext {

	private Strategy strategy;
	
	public void setFileStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void createFile(File selectedFile) {
		strategy.saveFile(selectedFile);
	}
}
