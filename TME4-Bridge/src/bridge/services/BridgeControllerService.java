package bridge.services;

public interface BridgeControllerService {
	public boolean canEnter();
	public boolean canLeave();
	public boolean canLeaveIn();
	public boolean canLeaveOut();
	public void control();
}
