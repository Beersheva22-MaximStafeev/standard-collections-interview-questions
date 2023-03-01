package telran.util;

import java.util.HashMap;

public class ConnectionsPoolImplementation implements ConnectionsPool {
	private static final int MAXIMUM_CONNECTIONS = 20;
	private int maximumConnections;

	private HashMap<Integer, Node> hash = new HashMap<>();
	private Node head = null;
	private Node tail = null;
	
	private static class Node {
		Connection connection;
		Node prev = null;
		Node next = null;
		
		public Node(Connection connection) {
			this.connection = connection;
		}
	}

	public ConnectionsPoolImplementation(int maximumConnections) {
		this.maximumConnections = maximumConnections;
	}
	
	public ConnectionsPoolImplementation() {
		this(MAXIMUM_CONNECTIONS);
	}

	@Override
	public boolean addConnection(Connection connection) {
		boolean res = hash.containsKey(connection.getId());
		if (!res) {
			Node node = new Node(connection);
			moveHeadTo(node);
			hash.put(connection.getId(), node);
			if (hash.size() > maximumConnections) {
				removeLastNode();
			}
		}
		return res;
	}

	private void removeLastNode() {
		// List not empty
		hash.remove(tail.connection.getId());
		if (head == tail) {
			head = null;
			tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
				
	}

	private void moveHeadTo(Node node) {
		if (head == null) {
			tail = node;
			head = node;
		} else {
			head.prev = node;
			node.next = head;
			head = node;
		}
	}

	@Override
	public Connection getConnection(int id) {
		Node node = hash.get(id);
		Connection res = null;
		if (node != null) {
			moveNodeToHead(node);
			res = node.connection;
		}
		return res;
	}

	private void moveNodeToHead(Node node) {
		if (node != head) {
			if (node == tail) {
				tail = tail.prev;
				tail.next = null;
			} else {
				node.prev.next = node.next;
				node.next.prev = node.prev;
			}
			node.prev = null;
			node.next = head;
			head.prev = node;
			head = node;
		}		
	}
}