package drawing;

public interface Observable {
	public void addObserver(Observer addObserver);
	public void notifyObserver();
}
