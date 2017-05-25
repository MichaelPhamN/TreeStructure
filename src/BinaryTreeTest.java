
public class BinaryTreeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String[] ex1 = {"M", "G", "A", "C", "N"};
		String[] ex4 = {"M", "G", "A", "C", "N"};
//		String[] ex2 = {"A", "B", "C", "D"};
//		String[] ex3 = {"B", "A", "M", "N", "J", "K", "G", "C"};
//		String[] names = {"Martin", "Grover", "Alice", "Charlize", "Hoover", "Zeke", "Zorro", "Morton", "Zach", "Nathan", "Zaria"};
		BinaryTree<String> tree = new BinaryTree<>();
		for (String name : ex4) {
			tree.add(name);
		}
		tree.print();
//		System.out.println(tree.depth());
//		
//		for (String name : ex4) {
//			tree.delete(name);
//			tree.print();
//			System.out.println(tree.isValid());
//			System.out.println("---------------------------------------");
//		}
		
//		BinaryTree<String> badTree = BinaryTree.badTree();
//		badTree.print();
//		System.out.println(badTree.isValid());
	}

}
