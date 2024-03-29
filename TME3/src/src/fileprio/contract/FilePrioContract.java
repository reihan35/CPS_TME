package src.fileprio.contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;


import src.fileprio.service.FilePrios;

public class FilePrioContract<T> extends FilesPrioDecorator<T> {

	public FilePrioContract(FilePrios delegates) {
		super(delegates);
		// TODO Auto-generated constructor stub
	}
	public void checkInvariant() {
		// \inv: getSize() = \Somme(\Forall i \in getActivePrio() getSizePrio(i))
		int somme = 0 ;
		for(int i : getActivePrio()) {
			somme = somme + getSizePrio(i);
		}
		
		if(somme != getSize()) {
			throw new InvariantError("getSize() = \\Somme(\\Forall i \\in getActivePrio() getSizePrio(i))");
		}
		
		//\inv: isEmpty() == (getSize() == 0)
		if(isEmpty() && getSize() != 0) {
			throw new InvariantError("isEmpty() == (getSize() == 0)");
		}
		
		// \inv: \Forall int i \with i \in getactivePrios() == isActive(i)
		for(int i : getActivePrio()) {
			if(!isActive(i)) {
				throw new InvariantError("\\Forall int i \\with i \\in getactivePrios() == isActive(i)");
			}
		}
		
		// \inv: getMaxPrio() == max(getActivePrio()) \with (\Forall E max(E) = int x \with (x \in (E \Union {0})) \Forall int y \with ( y \in E) x>=y)		
		try {
			int max = (int) Collections.max(getActivePrio());
			if(max != getMaxPrio() ) {
				 throw new InvariantError("getMaxPrio() == max(getActivePrio())");
			}
		} catch (Exception e) {
			if(getMaxPrio() !=0) {
				 throw new InvariantError("getMaxPrio() == max(getActivePrio())");
			}
		}
		
		//\inv: \Forall int i \with i \in getactivePrios() getPrio(i) == getElemPrio(i,1)
		for(int i : getActivePrio()) {
			if(getPrio(i)!=getElemPrio(i, 1)) {
				throw new InvariantError(" \\Forall int i \\with i \\in getactivePrios() getPrio(i) == getElemPrio(i,1)");
			}
		}
		
		// \inv: getElem() == getPrio(getMaxPrio())
		for(int i : getActivePrio()) {
			if(getElem()!=getPrio(getMaxPrio())) {
				throw new InvariantError("getElem() == getPrio(getMaxPrio())");
			}
		}
		
		// \inv: \Forall int i \with i \in getActivePrio() getSizePrio(i) > 0 
		for(int i : getActivePrio()) {
			if(getSizePrio(i) <= 0) {
				throw new InvariantError("\\Forall int i \\with i \\in getActivePrio() getSizePrio(i) > 0");
			}
		}
		
		// \inv: \Forall int i \with in \not \in getActivePrio() getSizePrio(i) == 0 
		if(!isEmpty()) {
			for(int i=0;i<50;i++) {
				int randomNum = ThreadLocalRandom.current().nextInt(0, 1000 + 1);
				if(!isActive(i) && getSizePrio(i) != 0) {
					throw new InvariantError("\\Forall int i \\with in \\not \\in getActivePrio() getSizePrio(i) == 0 ");
				}
			}
		}
		
		// \inv: \Forall int i \with i \in getActivePrio() \Forall k \with 1 < k < getSizePrio(i) getelemPrio(i,k) != NULL
		for(int i = 0; i<getActivePrio().size();i++) {
			for(int k = 2 ;k<getSizePrio(i);k++ ) {
				if (getElemPrio(i, k)==null) {
					throw new InvariantError("\\Forall int i \\with i \\in getActivePrio() \\Forall k \\with 1 < k < getSizePrio(i) getelemPrio(i,k) != NULL");
				}
			}
		}
		
	}
	
	@Override
	public void init() {
		super.init();
		checkInvariant();
		//\post: getSize() == 0
		if (getSize()!=0) {
			throw new PostconditionError("init : getSize() == 0");
		}
	}
	

	public void put_prio_post_conditions(Set getActive_at_pre,boolean isActive_at_pre ,
			int size_prio_at_pre, HashMap<Integer, Integer> size_prio_at_pre_others, 
			HashMap<Integer,ArrayList<T>> elems_at_pre,int i,T e) {
		
		// \post: isActive()@pre \impl getActivePrio() == getActivePrio()@pre 
		if(isActive_at_pre && getActivePrio() != getActive_at_pre) {
			throw new PostconditionError("isActive()@pre \\impl getActivePrio() == getActivePrio()@pre ");
		}
		
		// \post: \not (isActive()@pre) \impl getActivePrio() == (getActivePrio()@pre \Union {i})
		Set<Integer> getActive_at_pre2 = new HashSet<Integer>(getActive_at_pre);
		getActive_at_pre2.add(i);
	
		if((!isActive_at_pre) && !getActivePrio().equals(getActive_at_pre2) ) {
			throw new PostconditionError("isActive()@pre \\impl getActivePrio() == getActivePrio()@pre ");
		}
		
		// \post: getSizePrio(i) == getSizePrio(i)@pre +1
		if(getSizePrio(i) != size_prio_at_pre + 1) {
			throw new PostconditionError("getSizePrio(i) == getSizePrio(i)@pre +1");
		}
		
		// \post: \Forall int j \with (j \in getActivePrios()\{i}) getSizePrio(j) == getSizePrio(j)@pre
		Set<Integer> g = new HashSet<Integer>(getActive_at_pre);
		if (g.remove(i)) {
			for(int j=2 ; j<g.size();j++) {
				if (getSizePrio(j) != size_prio_at_pre_others.get(j)) {
					throw new PostconditionError(" \\Forall int j \\with (j \\in getActivePrios()\\{i}) getSizePrio(j) == getSizePrio(j)@pre");
				}
			}
		}
		
		// \post: getPrio(i) == e
		if(getPrio(i)!=e) {
			throw new PostconditionError(" getPrio(i) == e");
		}
		
		//	\post: \Forall k \with (2 < k < getSizePrio(i)@pre +1), getElemPrio(i, k) == getElemPrio(i, k-1)@pre
		for(int k = 2 ; k < size_prio_at_pre + 1; k++) {
			if(getElemPrio(i, k) != elems_at_pre.get(i).get(k-1)) {
				throw new PostconditionError(" \\Forall k \\with (2 < k < getSizePrio(i)@pre +1), getElemPrio(i, k) == getElemPrio(i, k-1)@pre");
			}
		}
		// \post: \Forall j \with (j \in getActivePrios()\{i}), \Forall k \with (1 < k < getSizePrio(j)@pre), getElemPrio(j, k) == getElemPrio(j, k)@pre
		Set<Integer> h = new HashSet<Integer>(getActive_at_pre);
		if (h.remove(i)) {
			for(int j : h) {
				for(int k = 2; k < size_prio_at_pre_others.get(j);k++) {
					if (getElemPrio(i, k) != elems_at_pre.get(i).get(k)) {
						throw new PostconditionError(" \\Forall int j \\with (j \\in getActivePrios()\\{i}) getSizePrio(j) == getSizePrio(j)@pre");
					}
				}
			}
		}
		
	}
	
	@Override
	public void putPrio(int i, T e) {
		
		// \pre: i >= 0
		if(i < 0) {
			throw new PreconditionError("i>=0");
		}
		// \pre: e != NULL
		if(e == null) {
			throw new PreconditionError("\\pre: e != NULL");
		}
		// Captures
		Set getActive_at_pre = getActivePrio();
		boolean isActive_at_pre = isActive(i);
		int size_prio_at_pre = getSizePrio(i);
		HashMap<Integer, Integer> size_prio_at_pre_others = size_prios();
		HashMap<Integer,ArrayList<T>> elems_at_pre = elems_prios();
		// TRAITEMENT
		
		super.putPrio(i,e);

		// post-invariant
		checkInvariant();
		
		//post-conditions
		put_prio_post_conditions(getActive_at_pre,isActive_at_pre,size_prio_at_pre,
				size_prio_at_pre_others,elems_at_pre,i,e);
	}
	
	
	public void put(T e) {

		// \pre: e != NULL
		if(e == null) {
			throw new PreconditionError("\\pre: e != NULL");
		}	
		// Captures
		Set getActive_at_pre = getActivePrio();
		boolean isActive_at_pre = isActive(getMaxPrio());
		int size_prio_at_pre = getSizePrio(getMaxPrio());
		HashMap<Integer, Integer> size_prio_at_pre_others = size_prios();
		HashMap<Integer,ArrayList<T>> elems_at_pre = elems_prios();
	
		
		// TRAITEMENT
		super.put(e);
		
		// post-invariant
		checkInvariant();
		
		// \post: put(e)@pre == putPrio(e, getMaxPrio()@pre)@pre 
		put_prio_post_conditions(getActive_at_pre,isActive_at_pre,size_prio_at_pre,
				size_prio_at_pre_others,elems_at_pre,getMaxPrio(),e);
	}
	
	public void remove_prio_post_conditions(Set getActive_at_pre,boolean isActive_at_pre ,
			int size_prio_at_pre, HashMap<Integer, Integer> size_prio_at_pre_others, 
			HashMap<Integer,ArrayList<T>> elems_at_pre,int i) {
		
		
		//\post: (size_prio_at_pre) > 1) \impl (getActivePrios() == getActivePrios()@pre)
		if(getSizePrio(i) > 1 && getActivePrio() != getActive_at_pre) {
			throw new PostconditionError("(getSizePrio(i) > 1) \\impl (getActivePrios() == getActivePrios()@pre) ");
		}
		
		
		// \post: (size_prio_at_pre == 1) \impl (getActivePrios() == getActivePrios()@pre\{i})
		Set<Integer> z = new HashSet<Integer>(getActive_at_pre);
		if(size_prio_at_pre == 1) {
			if(z.remove(i) && z!= getActive_at_pre) {
				throw new PostconditionError("(getSizePrio(i) == 1) \\impl (getActivePrios() == getActivePrios()@pre\\{i})");
			}
		}
		
		// \post: getSizePrio(i) == getSizePrio(i)@pre - 1
		if(getSizePrio(i) != size_prio_at_pre - 1) {
			throw new PostconditionError("getSizePrio(i) == getSizePrio(i)@pre - 1");
		}
		
		// \post: \Forall int j \with (j \in getActivePrios()\{i}) getSizePrio(j) == getSizePrio(j)@pre
		Set<Integer> g = new HashSet<Integer>(getActive_at_pre);
		if (g.remove(i)){
			for(int j : g) {
				if (getSizePrio(j) != size_prio_at_pre_others.get(j)) {
					throw new PostconditionError(" \\Forall int j \\with (j \\in getActivePrios()\\{i}) getSizePrio(j) == getSizePrio(j)@pre");
				}
			}
		}
		
		// \post: \Forall k \with (1 < k < getSizePrio(i)@pre - 1), getElemPrio(i, k) == getElemPrio(i, k)@pre
		for(int k = 2 ; k < size_prio_at_pre - 1; k++) {
			if(getElemPrio(i, k) != elems_at_pre.get(i).get(k)) {
				throw new PostconditionError("\\Forall k \\with (1 < k < getSizePrio(i)@pre - 1), getElemPrio(i, k) == getElemPrio(i, k)@pre\n");
			}
		}
		// \post: \Forall j \with (j \in getActivePrios()@pre\{i}) \Forall k \with (1 < k < getSizePrio(j)@pre), getElemPrio(j, k) == getElemPrio(j, k)@pre
		Set<Integer> h = new HashSet<Integer>(getActive_at_pre);
		if (h.remove(i)) {
			for(int j : h) {
				for(int k = 2; k < size_prio_at_pre_others.get(j);k++) {
					if (getElemPrio(i, k) != elems_at_pre.get(i).get(k)) {
						throw new PostconditionError("\\Forall j \\with (j \\in getActivePrios()@pre\\{i}) \\Forall k \\with (1 < k < getSizePrio(j)@pre), getElemPrio(j, k) == getElemPrio(j, k)@pre");
					}
				}
			}
		}
	}
	
	public void removePrio(int i) {
		// \pre: getSizePrio(i) > 0
		if(getSizePrio(i) < 0) {
			throw new PreconditionError("getSizePrio(i) > 0");
		}
		// Captures
		Set getActive_at_pre = getActivePrio();
		boolean isActive_at_pre = isActive(i);
		int size_prio_at_pre = getSizePrio(i);
		HashMap<Integer, Integer> size_prio_at_pre_others = size_prios();
		HashMap<Integer, ArrayList<T>> elems_at_pre = elems_prios();
		// TRAITEMENT

		super.removePrio(i);

		// post-invariant
		checkInvariant();
		
		//post-conditions
		remove_prio_post_conditions(getActive_at_pre,isActive_at_pre,size_prio_at_pre,
				size_prio_at_pre_others,elems_at_pre,i);

	}
	
	public void remove() {
		// \pre: getSize() > 0
		if(getSize()<=0) {
			throw new PreconditionError("getSize() > 0");

		}
		// Captures
		Set getActive_at_pre = getActivePrio();
		boolean isActive_at_pre = isActive(getMaxPrio());
		int size_prio_at_pre = getSizePrio(getMaxPrio());
		HashMap<Integer, Integer> size_prio_at_pre_others = size_prios();
		HashMap<Integer, ArrayList<T>> elems_at_pre = elems_prios();
		
		// TRAITEMENT
		super.remove();
				
		// post-invariant
		checkInvariant();
		
		//post-conditions
		remove_prio_post_conditions(getActive_at_pre,isActive_at_pre,size_prio_at_pre,
				size_prio_at_pre_others,elems_at_pre,getMaxPrio());
	}
	

	@Override
	public HashMap<Integer,Integer> size_prios() {
		return super.size_prios();
	}
	@Override
	public HashMap<Integer,ArrayList<T>> elems_prios() {
		return super.elem_prios();
	}
	@Override
	public void print_file() {
		super.print_file();
		
	}

}
