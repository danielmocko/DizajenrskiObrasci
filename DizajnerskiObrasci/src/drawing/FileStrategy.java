package drawing;

import java.io.File;

public class FileStrategy {
	
	private Strategy strategy;
	
	public void setFileStrategy(Strategy strategy) {
		this.strategy = strategy;
	}

	public void createFile(File selectedFile) {
		strategy.save(selectedFile);
	}
}
