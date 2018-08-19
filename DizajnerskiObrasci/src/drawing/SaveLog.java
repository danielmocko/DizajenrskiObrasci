package drawing;

import java.io.*;

import javax.swing.JFileChooser;

public class SaveLog implements Strategy {

	private Frame frame = new Frame(); 
	
	public SaveLog(Frame frame) {
		this.frame=frame;
	}

	public void save(File file) {	
		BufferedWriter buffer = null;
		try {
			buffer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			
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
		finally{
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
