package drawing.observer;

public interface Observable {
	public void addObserver(Observer addObserver);
	public void notifyMenu();
	public void notifyLog();
}
