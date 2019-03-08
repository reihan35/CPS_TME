package lift.test;
import org.junit.Test;

import lift.contracts.InvariantError;
import lift.contracts.LiftContract;
import lift.contracts.PreconditionError;
import liftimpl3.Lift3;

public class Lift3Test extends AbstractLiftTest {

	@Override
	public void beforeTests() {
		// TODO Auto-generated method stub

	}
	
	
	@Test
	public void init_precondition1() {
		LiftContract lcontract = new LiftContract(new Lift3());
		lcontract.init(2, 5);
	}

	@Test(expected = PreconditionError.class)
	public void init_precondition2() {
		LiftContract lcontract = new LiftContract(new Lift3());
		lcontract.init(5, 2);
	}
	

}
