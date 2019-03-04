package src.fileprio.contract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import src.fileprio.service.FilePrios;

public abstract class FilesPrioDecorator<T> implements FilePrios<T>{
	
	private FilePrios<T> delegates;

	public FilesPrioDecorator(FilePrios<T> delegates) {
		super();
		this.delegates = delegates;
	}

	public void init() {
		delegates.init();
	}
	
	public int getSize() {
		return delegates.getSize();
	}

	public boolean isEmpty() {
		return delegates.isEmpty();
	}

	public Set<Integer> getActivePrio() {
		return delegates.getActivePrio();
	}

	public boolean isActive(int i) {
		return delegates.isActive(i);
	}

	public int getMaxPrio() {
		return delegates.getMaxPrio();
	}

	public int getSizePrio(int i) {
		return delegates.getSizePrio(i);
	}

	public T getPrio(int i) {
		return delegates.getPrio(i);
	}

	public T getElem() {
		return delegates.getElem();
	}

	public T getElemPrio(int i, int k) {
		return delegates.getElemPrio(i, k);
	}

	public void putPrio(int i, T t) {
		delegates.putPrio(i, t);
	}

	public void put(T e) {
		delegates.put(e);
	}

	public void removePrio(int i) {
		delegates.removePrio(i);
	}

	public void remove() {
		delegates.remove();
	}
	
	
	public HashMap<Integer, Integer> size_prios() {
		return delegates.size_prios();
	}
	
	public HashMap<Integer, ArrayList<T>> elem_prios() {
		return delegates.elems_prios();
	}
	
	public void print_file() {
		delegates.print_file();		
	}

	
	
}
