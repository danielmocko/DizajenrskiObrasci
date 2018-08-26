package drawing.strategy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import drawing.mvc.Frame;

public class LogStrategy implements Strategy {

	private Frame frame;

	public LogStrategy(Frame frame) {
		this.frame = frame;
	}

	public void saveFile(File selectedFile) {
		BufferedWriter buffer=null;
		
		try{
			buffer =  new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath()));
			 
			String logString="";
			int size=frame.getDlmList().getSize();

			for(int i=0;i<size;i++) {
				logString=frame.getDlmList().get(i);
				buffer.write(logString);
				buffer.newLine();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	

	}
}
