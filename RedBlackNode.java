package cs383;

public class RedBlackNode {
	private int key;
	private boolean isBlack;
	private boolean isNil;
	private RedBlackTree parent;
	private RedBlackTree left;
	private RedBlackTree right;
	
	public RedBlackNode(int key){
		this.key = key;
		isBlack = false;
	}
	
	public RedBlackNode(boolean isNil) {
		this.isNil = true;
	}

	public int getKey() {
		return key;
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public boolean isNil() {
		return isNil;
	}

	public RedBlackTree getParent() {
		return parent;
	}

	public void setParent(RedBlackTree parent) {
		this.parent = parent;
	}

	public RedBlackTree getLeft() {
		return left;
	}

	public void setLeft(RedBlackTree left) {
		this.left = left;
	}

	public RedBlackTree getRight() {
		return right;
	}

	public void setRight(RedBlackTree right) {
		this.right = right;
	}

	@Override
	public String toString() {
		if (isNil == true) {
			return "NIL";
		}
		String result = key + " ";
		if (isBlack == true) {
			result += "[b]";
		} else {
			result +="[r]";
		}
		return result;
	}
}
