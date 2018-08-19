package drawing;

public interface Observer {
	public abstract void updateView(int numberSelectedObjects,int flag,int numberObjects);
	public abstract void updateLog(String logList );
}
