package ch.zhaw;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeTest {
	@Test
	public void testAddWithLower() {
		Tree tree = new Tree(5);
		tree.add(4);
		Assert.assertEquals(4, tree.getLeft().getData());
	}

	@Test
	public void testAddWithHigher() {
		Tree tree = new Tree(5);
		tree.add(6);
		Assert.assertEquals(6, tree.getRight().getData());
	}

	@Test
	public void testAddWithEqual() {
		Tree tree = new Tree(5);
		tree.add(5);
		Assert.assertEquals(5, tree.getRight().getData());
	}

	@Test
	public void testAddWithHigherLower() {
		Tree tree = new Tree(5);
		tree.add(8);
		tree.add(4);
		Assert.assertEquals(4, tree.getLeft().getData());
	}

	@Test
	public void testAddWithHigherHigherLower() {
		Tree tree = new Tree(5);
		tree.add(8);
		tree.add(7);
		Assert.assertEquals(7, tree.getRight().getLeft().getData());
	}

	@Test
	public void testAddListWithHigherHigherLower() {
		Tree tree = new Tree(5);
		List<Integer> is = new ArrayList<Integer>();
		is.add(8);
		is.add(7);
		tree.add(is);
		Assert.assertEquals(7, tree.getRight().getLeft().getData());
	}

	@Test
	public void testRemoveNoChild() {
		Tree tree = new Tree(5);
		List<Integer> is = new ArrayList<Integer>();
		is.add(8);
		is.add(3);
		is.add(7);
		tree.add(is);
		tree.remove(7);
		Assert.assertEquals(3, tree.getLeft().getData(), 3);
		Assert.assertEquals(8, tree.getRight().getData(), 8);
		Assert.assertEquals(null, tree.getRight().getLeft());
		Assert.assertEquals(null, tree.getRight().getRight());
	}

	@Test
	public void testRemoveOneChild() {
		Tree tree = new Tree(5);
		List<Integer> is = new ArrayList<Integer>();
		is.add(8);
		is.add(3);
		is.add(7);
		tree.add(is);
		tree.remove(8);
		Assert.assertEquals(3, tree.getLeft().getData());
		Assert.assertEquals(7, tree.getRight().getData());
	}

	@Test
	public void testRemoveTwoChildren() {
		Tree tree = new Tree(15);
		List<Integer> is = new ArrayList<Integer>();
		is.add(5);
		is.add(16);
		is.add(3);
		is.add(12);
		is.add(20);
		is.add(10);
		is.add(13);
		is.add(18);
		is.add(23);
		is.add(6);
		is.add(7);
		tree.add(is);
		tree.remove(5);
		Assert.assertEquals("[15[6[3][12[10[7]][13]]][16[20[18][23]]]]", tree.toString());
		Assert.assertEquals(6, tree.getLeft().getData());
		Assert.assertEquals(3, tree.getLeft().getLeft().getData());
		Assert.assertEquals(12, tree.getLeft().getRight().getData());
		Assert.assertEquals(10, tree.getLeft().getRight().getLeft().getData());
		Assert.assertEquals(7, tree.getLeft().getRight().getLeft().getLeft().getData());
	}

	@Test
	public void testRemoveWithMultipleElements() {
		Tree tree = new Tree(5);
		List<Integer> is = new ArrayList<Integer>();
		is.add(2);
		is.add(1);
		is.add(3);
		is.add(4);
		is.add(15);
		is.add(12);
		is.add(10);
		is.add(9);
		is.add(13);
		is.add(13);
		is.add(13);
		is.add(13);
		is.add(13);
		tree.add(is);
		tree.remove(13);
		Assert.assertEquals(9, tree.getSize());
	}

	@Test
	public void testGetDepth() {
		Tree tree = new Tree(5);
		List<Integer> is = new ArrayList<Integer>();
		is.add(2);
		is.add(1);
		is.add(3);
		is.add(4);
		is.add(15);
		is.add(14);
		is.add(13);
		is.add(12);
		tree.add(is);
		Assert.assertEquals(4, tree.getDepth()-1);
	}

	@Test
	public void testGetSize() {
		Tree tree = new Tree(5);
		List<Integer> is = new ArrayList<Integer>();
		is.add(2);
		is.add(1);
		is.add(3);
		is.add(4);
		is.add(15);
		is.add(12);
		is.add(10);
		is.add(9);
		is.add(13);
		is.add(13);
		is.add(13);
		tree.add(is);
		//Assert.assertEquals("", tree.toString());
		Assert.assertEquals(12, tree.getSize());
	}

	@Test
	public void testContainsWhereElementExists() {
		Tree tree = new Tree(5);
		List<Integer> is = new ArrayList<Integer>();
		is.add(8);
		is.add(3);
		is.add(7);
		tree.add(is);
		Assert.assertTrue(tree.Contains(7));
	}

	@Test
	public void testContainsWhereElementDoesNotExist() {
		Tree tree = new Tree(5);
		List<Integer> is = new ArrayList<Integer>();
		is.add(8);
		is.add(3);
		is.add(7);
		tree.add(is);
		Assert.assertFalse(tree.Contains(9));
	}
}
