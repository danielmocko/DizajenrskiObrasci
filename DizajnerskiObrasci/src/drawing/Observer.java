package drawing;

public interface Observer {
	public abstract void updateView(int numberSelectedObjects);
	public abstract void updateLog(String logList );
}
