package aiclass;

import java.util.ArrayList;
import java.util.List;

public class State {

	private Object data;
	
	private List<State> connections = new ArrayList<State>();
	
	public State(Object value) {
		data = value;
	}
	
	public int intValue() {
		return (Integer)data;
	}
	
	public Object value() {
		return data;
	}
	
	public State connect(State state) {
		connections.add(state);
		return this;
	}
	
	public List<State> connections() {
		return connections;
	}
}