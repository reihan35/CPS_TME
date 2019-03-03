package src.fileprio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public interface FilePrios<T> {

	/* Observators */
	
	public int getSize();
	public boolean isEmpty();
	public Set getActivePrio();
	public boolean isActive(int i);
	public int getMaxPrio();
	public int getSizePrio(int i);
	// \pre getSize(i) > 0
	public T getPrio(int i);
	// \pre getSize() > 0
	public T getElem();
	// \pre i \in getActivePrio() 
	// \pre k <= getSizePrio(i)
	// \pre k>0
	public T getElemPrio(int i,int k);
	public HashMap<Integer, Integer> size_prios();
	public HashMap<Integer, ArrayList<T>> elems_prios();
	public void print_file();

	
	
	/* \invariants */
	// \inv: getSize() = \Somme(\Forall i \in getActivePrio() getSizePrio(i)) 
	// \inv: isEmpty() == (getSize() == 0)
	// \inv: \Forall int i \with i \in getactivePrios() == isActive(i)
	// \inv: getMaxPrio() == max(getActivePrio()) \with (\Forall E max(E) = int x \with (x \in (E \Union {0})) \Forall int y \with ( y \in E) x>=y)
	// \inv: \Forall int i \with i \in getactivePrios() getPrio(i) == getElemPrio(i,1)
	// \inv: getElem() == getPrio(getMaxPrio())
	// \inv: \Forall int i \with i \in getActivePrio() getSizePrio(i) > 0 
	// \inv: \Forall int i \with in \not \in getActivePrio() getSizePrio(i) == 0 
	// \inv: \Forall int i \with i \in getActivePrio() \Forall k \with 1 < k < getSizePrio(i) getelemPrio(i,k) != NULL
	
	
	/* Constructors */
	
	// \post: getSize() == 0
	public void init();
	
	/* Operators */
	
	// \pre: i >= 0 
	// \pre: e != NULL
	// \post: isActive()@pre \impl getActivePrio() == getActivePrio()@pre 
	// \post: \not (isActive()@pre) \impl getActivePrio() == (getActivePrio()@pre \Union {i})
	// \post: getSizePrio(i) == getSizePrio(i)@pre +1
	// \post: \Forall int j \with (j \in getActivePrios()\{i}) getSizePrio(j) == getSizePrio(j)@pre
	// \post: getPrio(i) == e
	// \post: \Forall k \with (2 < k < getSizePrio(i)@pre +1), getElemPrio(i, k) == getElemPrio(i, k-1)@pre
	// \post: \Forall j \with (j \in getActivePrios()\{i}), \Forall k \with (1 < k < getSizePrio(j)@pre), getElemPrio(j, k) == getElemPrio(j, k)@pre
	public void putPrio(int i, T t);
	
	// \pre: e != NULL
	// \post: put(e) == putPrio(e, getMaxPrio()) 
	public void put(T e);
	
	// \pre: getSizePrio(i) > 0
	// \post: size_prio_at_pre > 1 \impl (getActivePrios() == getActivePrios()@pre)
	// \post: size_prio_at_pre == 1) \impl (getActivePrios() == getActivePrios()@pre\{i})
	// \post: getSizePrio(i) == getSizePrios(i)@pre -1
	// \post: \Forall j \with (j \in getActivePrios()\{i}), getSizePrio(j) == getSizePrio(j)@pre
	// \post: \Forall k \with (1 < k < getSizePrio(i)@pre - 1), getElemPrio(i, k) == getElemPrio(i, k)@pre
	// \post: \Forall j \with (j \in getActivePrios()@pre\{i}) \Forall k \with (1 < k < getSizePrio(j)@pre), getElemPrio(j, k) == getElemPrio(j, k)@pre
	public void removePrio(int i);
	
	// \pre: getSize() > 0
	// \post: remove() = removePrio(getMaxPrio())
	public void remove();
}
