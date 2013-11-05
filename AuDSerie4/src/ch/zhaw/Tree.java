package ch.zhaw;

import java.util.List;

public class Tree {
	private Tree parent;
	private Tree left;
	private Tree right;
	private int data;

	public Tree(int data) {
		this.data = data;
	}

	public void add(int i) {
		add(new Tree(i));
	}

	public void add(Tree toAdd) {
		if (toAdd == null) return;

		Tree tree = toAdd;
		Tree parent = this;
		Tree subtree = this;

		while (subtree != null) {
			parent = subtree;
			if (tree.getData() < subtree.getData()) {
				subtree = subtree.getLeft();
			} else {
				subtree = subtree.getRight();
			}
		}

		if (tree.getData() < parent.getData()) {
			parent.setLeft(tree);
		} else {
			parent.setRight(tree);
		}
		tree.setParent(parent);
	}

	public void add(List<Integer> is) {
		for (Integer i : is) {
			add(i);
		}
	}

	public void remove(int i) {
		if (i == getData()) {
			return;
		}

		Tree toDelete = this;
		while (toDelete != null) {
			if (i == toDelete.getData()) {
				Tree parent = toDelete.getParent();

				// If element to delete has no children, simply remove it.
				if (toDelete.getLeft() == null && toDelete.getRight() == null) {
					parent.removeNoChild(toDelete);
				}

				// If element to delete has one child, replace element with the child.
				if ((toDelete.getLeft() == null && toDelete.getRight() != null) ||
						(toDelete.getLeft() != null && toDelete.getRight() == null)) {
					parent.removeOneChild(toDelete);
				}

				// If element to delete has two children, search one to replace it.
				if (toDelete.getLeft() != null && toDelete.getRight() != null) {
					parent.removeTwoChildren(toDelete);
				}
			}

			if (i < toDelete.getData()) {
				toDelete = toDelete.getLeft();
			} else {
				toDelete = toDelete.getRight();
			}
		}
	}

	public Tree getLeft() {
		return left;
	}

	public Tree getRight() {
		return right;
	}

	public int getData() {
		return data;
	}

	public void setLeft(Tree left) {
		this.left = left;
	}

	public void setRight(Tree right) {
		this.right = right;
	}

	public void setParent(Tree parent) {
		this.parent = parent;
	}

	public Tree getParent() {
		return parent;
	}

	public String toString() {
		StringBuffer s = new StringBuffer("[" + data);
		if (getLeft() != null) {
			s.append(getLeft());
		}
		if (getRight() != null) {
			s.append(getRight());
		}
		return s.append("]").toString();
	}

	private void removeNoChild(Tree tree) {
		if (getLeft() == tree) {
			setLeft(null);
		} else if (getRight() == tree) {
			setRight(null);
		}
	}

	private void removeOneChild(Tree toDelete) {
		Tree subtree;
		if (toDelete.getLeft() != null) {
			subtree = toDelete.getLeft();
		} else {
			subtree = toDelete.getRight();
		}

		Tree parent = toDelete.getParent();
		if (parent.getLeft() == toDelete) {
			setLeft(subtree);
		} else {
			setRight(subtree);
		}
		subtree.setParent(this);
	}

	private void removeTwoChildren(Tree toDelete) {
		// Find replacement node
		Tree replacement = null;
		Tree subtree = toDelete.getRight();
		while (subtree != null) {
			replacement = subtree;
			if (subtree.getLeft() != null) {
				subtree = subtree.getLeft();
			} else {
				break;
			}
		}

		// Set child of replacement on the replacement parent.
		Tree replacementParent = replacement.getParent();
		replacementParent.setLeft(null);
		replacementParent.setRight(null);
		replacementParent.add(replacement.getLeft());
		replacementParent.add(replacement.getRight());

		// Set children of the toDelete tree on the replacement
		replacement.setLeft(toDelete.getLeft());
		replacement.setRight(toDelete.getRight());

		// Add the replacment on the parent/ch/zhaw/Tree.java:147 of the toDelete node.
		Tree parent = toDelete.getParent();
		if (parent.getLeft() == toDelete) {
			parent.setLeft(replacement);
		} else {
			parent.setRight(replacement);
		}
	}

	public int getDepth() {
		return 1 + Math.max(
				getLeft() == null ? 0 : getLeft().getDepth(),
				getRight() == null ? 0 : getRight().getDepth());
	}

	public int getSize() {
		return 1
				+ (getLeft() == null ? 0 : getLeft().getSize())
				+ (getRight() == null ? 0 : getRight().getSize());
	}

	public boolean Contains(int i) {
		if (i == getData()) return true;
		if (getLeft() == null && getRight() == null) return false;
		return (getLeft() == null ? false : getLeft().Contains(i))
				|| (getRight() == null ? false : getRight().Contains(i));
	}

	/*
	Löschen:
		Element 0 Kinder: Trivial
		Element 1 Kind: Kind mit Parent verbinden.
		Element 2 Kinder: Suche das linkeste/kleinste Element aus dem rechten Teilbaum. (Tree.rightSubtree().minElement();)
	 */
}
