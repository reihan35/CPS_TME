package lift.test;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


import lift.contracts.CommandsContract;
import lift.contracts.InvariantError;
import lift.contracts.LiftContract;
import lift.contracts.PreconditionError;
import lift.impl.CommandsImpl;
import lift.services.CommandsService;
import liftimpl1.Lift1;
import liftimpl3.Lift3;

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
	
	@Test
	public void openDoor_precondition() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.closeDoor();
		lcontract.doorAck();
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
	
	//II. Transitions (on verifie juste qu'il n'y a pas d'erreur levée
	@Test
	public void init_transition() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
	}
	
	
	@Test
	public void beginMoveup_transition() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addUpCommand(3);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.beginMoveUp();
	}
	
	@Test
	public void beginMoveDown_transition() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addDownCommand(1);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.beginMoveDown();
	}
	
	@Test
	public void openDoor_transition() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.openDoor();
	}
	
	@Test
	public void selectLevel_transition() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.selectLevel(3);
	}
	
	//... not doing all transitions, but check for every method it doesn't blow up when called with righful parameters
	
	// III; Etats remarquables: faire descendre l'ascenseur lorsqu'il est tout en bas
	
	//doit lever une exception d'invariant
	@Test (expected=InvariantError.class)
	public void check_boundings() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);
		lcontract.getCommands().addDownCommand(1);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.beginMoveDown();
		lcontract.stepMoveDown();
	}
	
	//IV. Paires de transition
	
	//V. Scénario
	@Test
	//Aller a l'étage 4 (2 étages), puis redescendre a l'étage 3. On teste ainsi à peu près toutes les fonctions
	// et une suite d'opérations cohérentes pour l'utilisation de l'ascenseur
	public void scenario_1() {
		LiftContract lcontract = new LiftContract(new Lift1());
		lcontract.init(2, 5);

		lcontract.selectLevel(4);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.beginMoveUp();
		lcontract.stepMoveUp();
		lcontract.stepMoveUp();
		lcontract.endMoveUp();
		lcontract.openDoor();
		lcontract.doorAck();
		lcontract.getCommands().addDownCommand(3);
		lcontract.closeDoor();
		lcontract.doorAck();
		lcontract.beginMoveDown();
		lcontract.stepMoveDown();
		lcontract.endMoveDown();
		lcontract.openDoor();
		lcontract.doorAck();
	}
	
	

}
