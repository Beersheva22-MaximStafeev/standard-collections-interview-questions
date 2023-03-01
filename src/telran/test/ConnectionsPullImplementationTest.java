package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import telran.util.ConnectionsPoolImplementation;
import telran.util.Connection;

class ConnectionsPullImplementationTest {

	ConnectionsPoolImplementation connManager;
	String[] ip = new String[] {"a", "b", "c", "d", "e", "f"};
	Connection[] connections = new Connection[ip.length]; 
	
	@BeforeEach
	void setUp() throws Exception {
		connManager = new ConnectionsPoolImplementation(2);
		for (int i = 0; i < ip.length; i++) {
			connections[i] = new Connection(i, ip[i], 1);
			connManager.addConnection(connections[i]);
		}
	}

	@Test
	void test() {
		assertEquals(connections[4], connManager.getConnection(4));
		assertEquals(connections[5], connManager.getConnection(5));
		assertNull(connManager.getConnection(0));
		assertNull(connManager.getConnection(1));
		assertNull(connManager.getConnection(2));
		assertNull(connManager.getConnection(3));
	}

}
