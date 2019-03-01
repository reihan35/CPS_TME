package bridge.contracts;

import bridge.decorators.LimitedRoadDecorator;
import bridge.services.LimitedRoadService;

public class LimitedRoadContract extends LimitedRoadDecorator {

	public LimitedRoadContract(LimitedRoadService delegate) {
		super(delegate);
	}
	

	public void checkInvariant() {
		if(!(isFull() == (getNbCars() == getLimit()))) {
			throw new ContractError("LimitedRoadContract, Invariant failed:(isFull() == (getNbCars() == getLimit()) )");
		}
		if(!(getNbCars() <= getLimit())) {
			throw new ContractError("LimitedRoadContract, Invariant failed:getNbCars() <= getLimit()");
		}
	}
	
	public void init(int lim) {
		
		
		 // pre: lim>0

		if(!(lim >= 0)) {
			throw new ContractError("LimitedRoadContract, pre: lim>0");
		}
		super.init(lim);
		
		//	 post: getLimit() == lim
		if(!(getNbCars() == 0)) {
			throw new ContractError("LimitedRoadContract, post: getLimit() == lim");
		}
		if(!(getLimit() == lim)) {
			throw new ContractError("LimitedRoadContract, post: getLimit() == lim");
		}
	}
	
	public void enter() {
		int lim_at_pre = getLimit();
		//pre: !isFull()
		if(!isFull()){
			throw new ContractError("LimitedRoadContract, pre: !isFull()");
		}
		super.enter();
		if(lim_at_pre != getLimit()) {
			throw new ContractError("LimitedRoadContract, const: lim");
		}
	}
	
	
}
