import java.io.*;
import java.util.*;

/*
A skip list is a data structure that allows for fast search within an ordered sequence of elements.
It consists of multiple layers of the sequence. The first layer is simply a sorted linked list of the
elements. Each subsequent layer is a linked list of a subsequence of the previous layer, where some
number of elements from the previous layer are skipped for each link of the next layer.
*/

public class bug8 {
	private static class Node {
		private ArrayList<Node> next;
		private ArrayList<Node> prev;
		private int value;

		public Node(int value) {
			next = new ArrayList<Node>();
			prev = new ArrayList<Node>();
			this.value = value;
		}

		public int distanceTo(Node other, int level) {
			Node curr = this;
			int count = 0;
			while (curr != other) {
				++count;
				curr = curr.next(level);
			}
			return count;
		}

		public int getValue() {
			return value;
		}

		public Node next(int level) {
			return next.get(level);
		}

		public Node prev(int level) {
			return prev.get(level);
		}

		public int getMaxLevel() {
			return next.isEmpty()?prev.size()-1:next.size()-1;
		}

		public void insertAfter(Node toAdd, int level) {
			if (level < next.size()) {
				Node currNext = next.get(level);
				currNext.prev.set(level, toAdd);
				toAdd.next.add(currNext);
			} else 
				next.add(null);

			next.set(level, toAdd);
			toAdd.prev.add(this);
		}

		public void insertBefore(Node toAdd, int level) {
			prev(level).insertAfter(toAdd, level);
		}

		public void delete(int level) {
			if (next.size() > level)
				next.remove(level);
			if (prev.size() > level)
				prev.remove(level);
		}

		public void remove(int level) {
			Node currNext = next.get(level);
			Node currPrev = prev.get(level);

			currPrev.next.set(level, currNext);
			currNext.prev.set(level, currPrev);
		}
	}

	private static class SkipList {
		private static final int MIN_DIST = 2;
		private static final int MAX_DIST = 4;
		private Node head, tail;

		public SkipList(int min, int max) {
			head = new Node(min);
			tail = new Node(max);
			head.insertAfter(tail, 0);
		}

		public void insert(int value) {
			List<Node> floors = findFloors(value);
			ListIterator<Node> itr = floors.listIterator();
			int level = 0;
			Node ins = new Node(value);
			Node curr = itr.next();
			while (true) {
				curr.insertAfter(ins, level);
				if (!itr.hasNext()) break;
				curr = itr.next();
				if (curr.distanceTo(curr.next(level+1), level) <= MAX_DIST)
					return;
				ins = curr.next(level).next(level);
				++level;
			}

			if (head.distanceTo(tail, level) > MAX_DIST) {
				head.insertAfter(tail, level+1);
				head.insertAfter(head.next(level).next(level), level+1);
			}
		}

		public void delete(int value) {
			Node found = findFloors(value).get(0);
			if (found.getValue() != value) return;
			for (int level=0; level<found.getMaxLevel(); level++) {
				if (found.distanceTo(found.next(level+1), level) != MIN_DIST)
					found.insertAfter(found.next(level), level+1);
				else if (found.prev(level+1).distanceTo(found, level) != MIN_DIST)
					found.insertBefore(found.prev(level), level+1);
				found.remove(level);
			}

			Node next = found;
			while (next != null) {
				Node curr = next;
				int level = curr.getMaxLevel();

				if (curr.prev(level).getMaxLevel() != level && curr.next(level).getMaxLevel() != level) {
					next = curr.prev(level);
					if (curr.next(level).getMaxLevel() < next.getMaxLevel() || next==head)
						next = curr.next(level);
				}
				curr.remove(level);
				curr.delete(level);
			}

			while (head.getMaxLevel() > 0 && head.distanceTo(tail, head.getMaxLevel()) < MIN_DIST) {
				head.delete(head.getMaxLevel());
				tail.delete(head.getMaxLevel());
			}
		}

		public int findFloor(int value) {
			return findFloors(value).get(0).getValue();
		}

		public List<Node> findFloors(int value) {
			LinkedList<Node> ret = new LinkedList<Node>();
			Node curr = head;
			for (int level = head.getMaxLevel(); level >=0; level--) {
				while (curr.next(level).getValue() <= value)
					curr = curr.next(level);
				ret.addFirst(curr);
			}
			return ret;
		}

		public void output() {
			for (int i=0; i<=head.getMaxLevel(); i++) {
				System.out.printf("Level %d: ", i);
				Node curr = head.next(i);
				while (curr!=tail) {
					System.out.print(" "+curr.getValue());
					curr = curr.next(i);
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
		int num = Integer.parseInt(in.readLine());

		SkipList sorted = new SkipList(0, num+1);
		int queries = Integer.parseInt(in.readLine());
		for (int i=0; i<queries; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			if (st.nextToken().charAt(0) == 'i') {
				sorted.insert(Integer.parseInt(st.nextToken()));
			} else {
				int rmv = sorted.findFloor(Integer.parseInt(st.nextToken()));
				sorted.delete(rmv);
				System.out.println(rmv);
			}
		}
	}
}