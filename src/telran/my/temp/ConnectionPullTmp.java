package telran.my.temp;

import java.util.*;

public class ConnectionPullTmp<T> {
	private static final int MAXIMUM_CONNECTIONS = 20;
	private int maximumConnections;
	
	Integer lastId = 0;
	LinkedHashMap<Integer, Connection<T>> connections = new LinkedHashMap<>();
	
	private static class Connection<T> {
		Integer id;
		T someInformation;
		
		public Connection(Integer id, T someInformation) {
			this.id = id;
			this.someInformation = someInformation;
		}
	}

	/**
	 * @param someInformation
	 * @return id for the new connection
	 */
	public Integer addConnection(T someInformation) {
		Connection<T> connection = new Connection<>(generateNewId(), someInformation);
		if (connections.size() >= maximumConnections) {
			removeOldestElement();
		}
		connections.put(connection.id, connection);
		return connection.id;
	}
	
	public void updateConnection(Integer id) {
		if (connections.containsKey(id)) {
			connections.put(id, connections.remove(id));
		}
	}
	
	public void removeConnection(Integer id) {
		connections.remove(id);
	}

	private Integer generateNewId() {
		return ++lastId;
	}
	
	public T getConnectionInfo(Integer id) {
		return connections.containsKey(id) ? connections.get(id).someInformation : null;
	}

	private void removeOldestElement() {
		// FIX ME
		connections.remove(connections.keySet().iterator().next());
	}

	public ConnectionPullTmp(int maximumConnections) {
		this.maximumConnections = maximumConnections;
	}
	
	public ConnectionPullTmp() {
		this(MAXIMUM_CONNECTIONS);
	}
}
