package aiclass;

public class TreeState extends State {

	private TreeState parent;
	
	public TreeState(TreeState parent, Object value) {
		super(value);
		this.parent = parent;
	}
	
	public TreeState getParent() {
		return parent;
	}
}
