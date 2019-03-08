package lift.test;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

import lift.contracts.CommandsContract;
import lift.contracts.InvariantError;
import lift.contracts.LiftContract;
import lift.contracts.PreconditionError;
import lift.impl.CommandsImpl;
import lift.services.CommandsService;
import liftimpl1.Lift1;

public class Lift1Test extends AbstractLiftTest {

	//PreConditions
	
	@Override
	public void beforeTests() {
		// TODO Auto-generated method stub

	}
	
	
	@Test
	public void init_precondition1() {
		try {
			LiftContract lcontract = new LiftContract(new Lift1());
			lcontract.init(2, 5);
		}catch(Error e) {
			assertFalse(e instanceof PreconditionError);
		}
	
	}

	@Test(expected = PreconditionError.class)
	public void init_precondition2() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(5, 2);
	}
	
	@Test(expected = PreconditionError.class)
	public void init_precondition3() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(-2, 5);
	}
	
	@Test
	public void beginMoveup_precondition1() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addUpCommand(3);
		lcontract.closeDoor();
		lcontract.doorAck();
		try {
			lcontract.beginMoveUp();
		}catch(Error e) {
			assertFalse(e instanceof PreconditionError);
		}
	}
	
	@Test(expected = PreconditionError.class)
	public void beginMoveup_precondition2() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addUpCommand(1);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.beginMoveUp();
	}
	
	@Test
	public void stepMoveup_precondition1() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addUpCommand(3);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.beginMoveUp();
		try {
			lcontract.stepMoveUp();
		}catch(Error e) {
			System.out.println(e);
			assertFalse(e instanceof PreconditionError);
		}
	}
	
	@Test(expected = PreconditionError.class)
	public void stepMoveup_precondition2() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addUpCommand(1);
		lcontract.stepMoveUp();
	}
	
	@Test
	public void endMoveup_precondition1() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addUpCommand(3);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.beginMoveUp();
		lcontract.stepMoveUp();
		try {
			lcontract.endMoveUp();
		}catch(Error e) {
			System.out.println(e);
			assertFalse(e instanceof PreconditionError);
		}
	}
	
	@Test(expected = PreconditionError.class)
	public void endMoveup_precondition2() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addUpCommand(1);
		lcontract.endMoveUp();
	}
	
	public void openDoor_precondition() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.closeDoor();
		lcontract.openDoor();
		try {
			lcontract.openDoor();
		}catch(Error e) {
			System.out.println(e);
			assertFalse(e instanceof PreconditionError);
		}
	}

	@Test(expected = PreconditionError.class)
	public void openDoor_precondition2() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.openDoor();

	}
	@Test
	public void selectLevel_preconditions() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		try {
			lcontract.selectLevel(3);
		}catch(Error e) {
			System.out.println(e);
			assertFalse(e instanceof PreconditionError);
		}
	}
	
	@Test(expected = PreconditionError.class)
	public void selectLevel_preconditions2() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.selectLevel(7);
	
	}
	
	//Transitions
	
	

}
