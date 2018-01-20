/* Name: Jamie Brandon
 * Email: jbrandon@brandeis.edu
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

	@Test
	public void canEnqueue() {
		Queue queue = new Queue(20);
		Player Jamie = new Player("Jamie");
		queue.enqueue(Jamie);
		System.out.println(queue.toString());
		fail("Not yet implemented");
	}

}
