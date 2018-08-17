package drawing;

import java.io.*;

import javax.swing.JFileChooser;

public class SaveLog implements Strategy {

	Frame frame = new Frame(); 

	
	
	@Override
	public void save() {	
		String logString ;
		int size=frame.getDlmList().getSize();
		try {
			FileWriter fileWriter = new FileWriter("This PC:\\Dektop");
			try {
				for(int i=0;i<size;i++) {
					logString=frame.getDlmList().get(i);
					fileWriter.write(logString);
					//fileWriter.flush();
				}
			}
			catch(Exception error) {
				error.getStackTrace();
			}finally {
				fileWriter.close();
			}
		}catch(Exception err) {
			err.getStackTrace();
		}
	}
}
