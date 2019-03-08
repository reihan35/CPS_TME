
package lift.test;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import lift.contracts.InvariantError;
import lift.contracts.LiftContract;
import lift.contracts.PreconditionError;
import liftimpl1.Lift1;
import liftimpl2.Lift2;

public class Lift2Test extends AbstractLiftTest {

	@Override
	public void beforeTests() {
		// TODO Auto-generated method stub

	}
	
	
	@Test
	public void init_precondition1() {
		LiftContract lcontract = new LiftContract(new Lift2());
		lcontract.init(2, 5);
	}

	@Test(expected = PreconditionError.class)
	public void init_precondition2() {
		LiftContract lcontract = new LiftContract(new Lift2());
		lcontract.init(5, 2);
	}
	
	


}
