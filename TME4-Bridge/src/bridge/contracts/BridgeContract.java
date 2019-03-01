  package bridge.contracts;

import bridge.services.BridgeService;

public class BridgeContract extends LimitedRoadContract implements BridgeService {

	public BridgeContract(BridgeService delegate) {
		super(delegate);
	}

	@Override
	protected BridgeService getDelegate() {
		return (BridgeService) super.getDelegate();
	}
	
	@Override
	public int getNbIn() {
		return getDelegate().getNbIn();
	}

	@Override
	public int getNbOut() {
		return getDelegate().getNbOut();
	}
	
	public void checkInvariant() {
		// inv: getNbCars() == getNbIn() + getNbOut()
		
		if(!(getNbCars() == getNbIn() + getNbOut())){
			throw new ContractError("Invariant failed: getNbCars() == getNbIn() + getNbOut()");
		}
		// inv: getNbIn() >= 0

		if(!(getNbIn()>=0)) {
			throw new ContractError("Invariant failed: getNbIn()>=0");
		}
		// inv: getNbOut() >= 0

		if(!(getNbOut() >=0)) {
			throw new ContractError("Invariant failed: getNbOut()>=0");
		}
		super.checkInvariant();
	}
	

	@Override
	public void init(int lim) {
		 //pre: lim > 0
		
		if(lim <= 0) {
			throw new ContractError("Bridge Contract : init pre : lim > 0");
		}
		super.init(lim);
		// post: getNbIn() == 0
		if(getNbIn()!=0) {
			throw new ContractError("Bridge Contract : init post: getNbIn() == 0");
		}
		 // post: getNbOut() == 0
		if(getNbOut()!=0) {
			throw new ContractError("Bridge Contract : init post: getNbOut() == 0");
		}
	}


	@Override
	public void leaveIn() {
		 //pre: getNbIn() > 0
		 if(getNbIn()<=0) {
			throw new ContractError("Bridge Contract : leaveIn pre : getNbIn() > 0");
		 }
		 int limit_at_pre = getLimit();
		 int nb_in_at_pre = getNbIn();
		 int nb_out_at_pre = getNbOut();
		 
		 checkInvariant();
		 getDelegate().leaveIn();
		 checkInvariant(); 
		 
		 //post: getNbIn() == getNbIn()@pre - 1
		 if(getNbIn()!=nb_in_at_pre - 1) {
			 throw new ContractError("Bridge Contract : leaveIn post : getNbIn() == getNbIn()@pre - 1\n" );
		 }
		
		 //post: getNbOut() == getNbOut()@pre
		 if(getNbOut()!=nb_out_at_pre ) {
			 throw new ContractError("Bridge Contract : leaveIn post : getNbIn() == getNbOut()@pre\n" );
		 }
		 
		 if(getLimit() != limit_at_pre) {
			 Contractor.defaultContractor().postconditionError("BridgeContract", "leaveIn", "getLimit() == limit_at_pre");
		 }
	}

	@Override
	public void enterIn() {
		 //pre: !isFull()
		 if(isFull()) {
			 Contractor.defaultContractor().preconditionError("BridgeContract", "enterIn", "!isFull()");
		 }
		 int limit_at_pre = getLimit();
		 int nb_in_at_pre = getNbIn();
		 int nb_out_at_pre = getNbOut();
		 
		 checkInvariant();
		 getDelegate().enterIn();
		 checkInvariant(); 
		 
		 //post: getNbIn() == getNbIn()@pre + 1
		 if(getNbIn()!=nb_in_at_pre + 1) {
			 throw new ContractError("Bridge Contract : enterIn post : getNbIn() == getNbIn()@pre + 1\n" );
		 }
		
		 //post: getNbOIn() == getNbIn()@pre
		 if(getNbOut()!=nb_out_at_pre ) {
			 throw new ContractError("Bridge Contract : enterIn post : getNbIn() == getNbOut()@pre\n" );
		 }
		 
		 if(getLimit() != limit_at_pre) {
			 Contractor.defaultContractor().postconditionError("BridgeContract", "enterIn", "getLimit() == limit_at_pre");
		 }
	}

	@Override
	public void enterOut() {
		//pre: !isFull()
		 if(isFull()) {
			 Contractor.defaultContractor().preconditionError("BridgeContract", "enterOut", "!isFull()");
		 }
		 int limit_at_pre = getLimit();
		 int nb_in_at_pre = getNbIn();
		 int nb_out_at_pre = getNbOut();
		 
		 checkInvariant();
		 getDelegate().enterOut();
		 checkInvariant();
		 
		//post: getNbOut() == getNbOut()@pre + 1
		 if(getNbOut()!=nb_out_at_pre + 1 ) {
			 throw new ContractError("Bridge Contract : enterOut post : getNbOut() == getNbOut()@pre + 1 \n" );
		 }
		
		 //post: getNbIn() == getNbIn()@pre
		 if(getNbIn()!=nb_in_at_pre  ) {
			 throw new ContractError("Bridge Contract : enterOut post : getNbIn() == getNbIn()@pre \n" );
		 }
		 
		 if(getLimit() != limit_at_pre) {
			 Contractor.defaultContractor().postconditionError("BridgeContract", "enterOut", "getLimit() == limit_at_pre");
		 }
	}

	@Override
	public void leaveOut() {
		//pre: getNbOut() > 0
		 if(getNbOut()<=0) {
			throw new ContractError("Bridge Contract : leaveOut pre : getNbOut() > 0");
		 }
		 
		 int limit_at_pre = getLimit();
		 int nb_in_at_pre = getNbIn();
		 int nb_out_at_pre = getNbOut();
		 
		 checkInvariant();
		 getDelegate().leaveOut();
		 checkInvariant();
		 
		//post: getNbOut() == getNbOut()@pre + 1
		 if(getNbOut()!=nb_out_at_pre - 1 ) {
			 throw new ContractError("Bridge Contract : leaveOut post : getNbOut() == getNbOut()@pre - 1 \n" );
		 }
		
		 //post: getNbIn() == getNbIn()@pre
		 if(getNbIn()!=nb_in_at_pre  ) {
			 throw new ContractError("Bridge Contract : leaveOut post : getNbIn() == getNbIn()@pre \n" );
		 }
		 
		 if(getLimit() != limit_at_pre) {
			 Contractor.defaultContractor().postconditionError("BridgeContract", "leaveOut", "getLimit() == limit_at_pre");
		 }
	}
	@Override
	public void enter() {
		//pre: !isFull()
		 if(isFull()) {
			 Contractor.defaultContractor().preconditionError("BridgeContract", "enter", "!isFull()");
		 }
		 
		 int limit_at_pre = getLimit();
		 int nb_in_at_pre = getNbIn();
		 int nb_out_at_pre = getNbOut();
		 
		 checkInvariant();
		 super.enter();
		 checkInvariant();
		 
		 /*getNbIn()@pre > getNbOut()@pre
		        then
		          getNbIn() == getNbIn()@pre
		          getNbOut() == getNbOut()@pre + 1 
		        else
		          getNbIn() == getNbIn()@pre + 1
		          getNbOut() == getNbOut()@pre 
		 */
		 if(nb_in_at_pre > nb_out_at_pre) {
			 if (!(getNbIn() == nb_in_at_pre && getNbOut() == nb_out_at_pre + 1)) {
				 Contractor.defaultContractor().postconditionError("BridgeContract", "enter", "getNbIn() == getNbIn()@pre and getNbOut() == getNbOut()@pre + 1 ");
			 } 
		 }
		 
		 else {
			 if (!(getNbIn() == nb_in_at_pre + 1 && getNbOut() == nb_out_at_pre )) {
				 Contractor.defaultContractor().postconditionError("BridgeContract", "enter", "getNbIn() == getNbIn()@pre + 1 and getNbOut() == getNbOut()@pre  ");
			 } 
		 }
		 
		 if(getLimit() != limit_at_pre) {
			 Contractor.defaultContractor().postconditionError("BridgeContract", "enter", "getLimit() == limit_at_pre");
		 }
	}
	
	@Override
	public void leave() {
		 //pre: getNbCars() > 0
		 if(getNbCars() <= 0 ) {
			 Contractor.defaultContractor().preconditionError("BridgeContract", "leave", "getNbCars() > 0");
		 }
		 
		 int limit_at_pre = getLimit();
		 int nb_in_at_pre = getNbIn();
		 int nb_out_at_pre = getNbOut();
		 
		 checkInvariant();
		 super.leave();
		 checkInvariant();
		 
		 /**
			 * post: if getNbIn()@pre > getNbOut()@pre
			 *       then
			 *         getNbIn() == getNbIn()@pre - 1
			 *         getNbOut() == getNbOut()@pre
			 *       else
			 *         getNbIn() == getNbIn()@pre
			 *         getNbOut() == getNbOut()@pre - 1
			 */
		 if(nb_in_at_pre > nb_out_at_pre) {
			 if (!(getNbIn() == nb_in_at_pre -1  && getNbOut() == nb_out_at_pre )) {
				 Contractor.defaultContractor().postconditionError("BridgeContract", "leave", "getNbIn() == getNbIn()@pre - 1 and getNbOut() == getNbOut()@pre  ");
			 } 
		 }
		 
		 else {
			 if (!(getNbIn() == nb_in_at_pre  && getNbOut() == nb_out_at_pre - 1 )) {
				 Contractor.defaultContractor().postconditionError("BridgeContract", "leave", "getNbIn() == getNbIn()@pre and getNbOut() == getNbOut()@pre - 1  ");
			 } 
		 }
		 
		 if(getLimit() != limit_at_pre) {
			 Contractor.defaultContractor().postconditionError("BridgeContract", "leave", "getLimit() == limit_at_pre");
		 }
	}
	
}
