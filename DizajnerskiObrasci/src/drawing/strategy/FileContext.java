package drawing.strategy;

import java.io.File;

import drawing.FileStrategy;

public class FileContext {

	private FileStrategy strategy;
	
	public void setFileStrategy(FileStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void createFile(File selectedFile) {
		strategy.saveFile(selectedFile);
	}
}
