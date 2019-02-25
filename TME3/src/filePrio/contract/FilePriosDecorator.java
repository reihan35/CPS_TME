package filePrio.contract;

import java.util.HashSet;

import filePrios.service.FilePrios;

public abstract class FilePriosDecorator<T> implements FilePrios<T>{
	
	public int getSize() {
		return delegate.getSize();
	}

	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	public HashSet<Integer> getActivePrio() {
		return delegate.getActivePrio();
	}

	public boolean isActive() {
		return delegate.isActive();
	}

	public int getMaxPrio() {
		return delegate.getMaxPrio();
	}

	public int getSizePrio(int i) {
		return delegate.getSizePrio(i);
	}

	public T getPrio(int i) {
		return delegate.getPrio(i);
	}

	public T getElem() {
		return delegate.getElem();
	}

	public T getElemPrio(int i, int k) {
		return delegate.getElemPrio(i, k);
	}

	public void init() {
		delegate.init();
	}

	public void putPrio(int i, T t) {
		delegate.putPrio(i, t);
	}

	public void put(T e) {
		delegate.put(e);
	}

	public void removePrio(int i) {
		delegate.removePrio(i);
	}

	public void remove() {
		delegate.remove();
	}

	private FilePrios<T> delegate;

	public FilePriosDecorator(FilePrios<T> delegate) {
		this.delegate = delegate;
	}
	

}
