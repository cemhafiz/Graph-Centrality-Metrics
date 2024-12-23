public class QueueElement {
	private String data;
	private int priority;
	
	public QueueElement(String data, int priority) {
		this.data = data;
		this.priority = priority;
	}
	
	public int getPriority(){
		return priority;
	}
	
	public String getData(){
		return data;
	}
}