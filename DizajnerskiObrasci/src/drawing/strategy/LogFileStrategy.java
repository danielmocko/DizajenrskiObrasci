package drawing.strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import drawing.FileStrategy;
import drawing.Frame;

public class LogFileStrategy implements FileStrategy {
	
	private Frame frame;
	
	public LogFileStrategy(Frame frame) {
		this.frame = frame;
	}
	
	public void saveFile(File selectedFile) {
		
		BufferedWriter buffer = null;
		
		try {
			
			buffer = new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath()));
		}
		
		catch(IOException e) {
			
			JOptionPane.showMessageDialog(null, "Neispravan format fajla", "Obaveštenje", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
		
		try {
			
			
			String logString="";
			int size=frame.getDlmList().getSize();

			for(int i=0;i<size;i++) {
				logString=frame.getDlmList().get(i);
				buffer.write(logString);
				buffer.newLine();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			
			buffer.close();
		}
		
		catch(IOException e) {
			
			JOptionPane.showMessageDialog(null, "Neispravan format fajla", "Obaveštenje", JOptionPane.WARNING_MESSAGE);
			e.printStackTrace();
		}
	}
}
