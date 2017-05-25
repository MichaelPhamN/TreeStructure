import java.util.Arrays;

public class BinaryTree<T extends Comparable<T>> {
	private static class Node<T extends Comparable<T>> {
		public T data;
		public Node<T> left, right;
		public void add(T d) {
			int comp = d.compareTo(data);
			if (comp == 0) {
				// We already have that thing
				return;
			} else if (comp < 0) {
				// d is smaller than data
				// go to the left
				if (left == null) {
					// We hit bottom
					left = new Node<>();
					left.data = d;
				} else {
					left.add(d);
				}
				return;
			} else {
				// d is greater than data
				// go to the right
				if (right == null) {
					// Hit bottom on the right
					right = new Node<>();
					right.data = d;
				} else {
					right.add(d);
				}
				return;
			}
		}
		
		public boolean contains(T d) {
			int comp = d.compareTo(data);
			if (comp == 0) {
				// We already have that thing
				return true;
			} else if (comp < 0) {
				if (left == null) return false;
				return left.contains(d);
			} else {
				// comp > 0
				if (right == null) return false;
				return right.contains(d);				
			}
		}
		
		public void print(int indent) {
			if (right != null) right.print(indent+1);
			char[] spaces = new char[2*indent];
			Arrays.fill(spaces, ' ');
			System.out.println(new String(spaces) + data);
			if (left != null) left.print(indent+1);
		}
		public int size() {
			// Implement size recursively
			// Include size of current node
			int total = 1; // current node
			// Add the size of the left
			if (left != null) total = total + left.size();
			// Add the size of the right
			if (right != null) total = total + right.size();			
			// Return the total
			return total;
		}
		
		public Node<T> findAndDelete(T d) {
			// Replace this subtree with a
			// subtree that does not have d in it
			// Return the new tree without d
			int comp = d.compareTo(data);
			if (comp == 0) {
				return delete();
			} else if (comp < 0) {
				// if left == null, d isn't in the tree
				if (left == null) return this;
				// if d is in the tree, must be on the left
				left = left.findAndDelete(d);
				return this;
			} else {
				if (right == null) return this;
				right = right.findAndDelete(d);
				return this;
			}
		}
		
		public Node<T> delete() {
			// Delete the current node
			// Return the new tree without the current node
			if (left == null) {
				return right;
			}
			if (right == null) {
				return left;
			}
			// Two children
			// Find successor
			Node<T> successor = right; // Right isn't null
			// If no children replace right
			if (successor.left == null) {
				data = successor.data;
				right = successor.right;
				return this;
			}
			// Else we will replace successor parent's left
			Node<T> succParent = this;
			while (successor.left != null) {
				succParent = successor;
				successor = successor.left; // Keep going left
			}
			succParent.left = successor.right;
			data = successor.data;
			return this;
		}
		
		public int depth() {
			// Implement the depth method
			// Depth of a single node is 1
			// The depth of the tree is the depth
			// of its deepest node
			// The depth of a node is the number of
			// steps it is from the root (plus one)
			int leftDepth = 0;
			if (left != null) leftDepth = left.depth();
			int rightDepth = 0;
			if (right != null) rightDepth = right.depth();
			return 1 + Math.max(leftDepth, rightDepth);
		}
		
		private boolean isValid(T min, T max) {
			// Make sure this subtree is constrained
			// by min and max (open interval)
			// If min is null, treat as negative infinity
			// If max is null, treat as positive infinity
			if (min != null) {
				if (min.compareTo(data) >= 0) {
					// Min is bigger than allowed
					return false; // Not valid
				}
			}
			if (max != null) {
				if (max.compareTo(data) <= 0) {
					// Max is smaller than allowed
					return false; // Not valid
				}
			}
			if (left != null) {
				if (!left.isValid(min, data)) return false;
			}
			if (right != null) {
				if (!right.isValid(data, max)) return false;
			}
			return true;
		}
		
		public static Node<String> badTree() {
			Node<String> root = new Node<>();
			root.data = "M";
			root.left = new Node<>();
			root.left.data = "G";
			root.left.left = new Node<>();
			root.left.left.data = "A";
			root.left.right = new Node<>();
			root.left.right.data = "K";
			root.left.right.left = new Node<>();
			root.left.right.left.data = "D";
			root.left.right.right = new Node<>();
			root.left.right.right.data = "L";
			root.right = new Node<>();
			root.right.data = "T";			
			return root;
		}
	}
	
	private Node<T> root;
	
	public BinaryTree() {
		root = null;
	}
	
	public void add(T data) {
		if (root == null) {
			root = new Node<>();
			root.data = data;
		} else {
			root.add(data);
		}
	}
	public boolean contains(T data) {
		if (root == null) return false;
		return root.contains(data);
	}
	
	public void print() {
		if (root != null) root.print(0);
	}
	public int size() {
		if (root == null) return 0;
		return root.size();
	}
	public void delete(T data) {
		if (root != null)
		root = root.findAndDelete(data);
	}
	public int depth() {
		if (root == null) return 0;
		return root.depth();
	}
	public boolean isValid() {
		// A node is valid if everything to the left
		// of it is less than it and everything
		// to the right of it is greater than it
		// You should probably use an additional
		// recursive method that keeps track of a
		// range!
		if (root == null) return true;
		return root.isValid(null, null);
	}
	
	public static BinaryTree<String> badTree() {
		BinaryTree<String> t = new BinaryTree<>();
		t.root = Node.badTree();
		return t;
	}
	
}
