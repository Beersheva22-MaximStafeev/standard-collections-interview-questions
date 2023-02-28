package telran.util;

import java.time.temporal.Temporal;
import java.util.*;

public class ConnectionPull<T> {
	private static final int MAXIMUM_CONNECTIONS = 20;
	Integer lastId = 0;
	LinkedHashMap<Integer, Connection<T>> connections = new LinkedHashMap<>();
	
	private static class Connection<T> {
		Integer id;
		T someInformation;
		Temporal lastAccessed;
		
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
		Connection<T> connection = new Connection<>(++lastId, someInformation);
		if (connections.size() >= MAXIMUM_CONNECTIONS) {
			removeOldestElement();
		}
		connections.put(connection.id, connection);
		return lastId;
	}

	private void removeOldestElement() {
		connections.remove(connections.keySet().iterator().next());
	}
}
