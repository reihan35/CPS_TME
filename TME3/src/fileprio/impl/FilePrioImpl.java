package src.fileprio.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import com.sun.javafx.collections.ArrayListenerHelper;

import src.fileprio.service.FilePrios;

public class FilePrioImpl<T> implements FilePrios<T> {
	private HashMap<Integer,ArrayList<T>> file;
	private HashMap<Integer,Integer> activePrio;
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.file = new HashMap<>();
		this.activePrio = new HashMap<>();
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return file.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return getSize()==0;
	}

	@Override
	public HashSet<Integer> getActivePrio() {
		// TODO Auto-generated method stub
		return (HashSet<Integer>) activePrio.keySet();
	}

	@Override
	public boolean isActive(int i) {
		return activePrio.containsKey(i);
	}

	@Override
	public int getMaxPrio() {
		// TODO Auto-generated method stub
		return Collections.max(activePrio.keySet());
	}

	@Override
	public int getSizePrio(int i) {
		// TODO Auto-generated method stub
		return activePrio.get(i);
	}

	@Override
	public T getPrio(int i) {
		// TODO Auto-generated method stub
		return file.get(i).get(0);
	}

	@Override
	public T getElem() {
		// TODO Auto-generated method stub
		return file.get(getMaxPrio()).get(0);
	}

	@Override
	public T getElemPrio(int i, int k) {
		// TODO Auto-generated method stub
		return file.get(getMaxPrio()).get(k);
	}

	@Override
	public void putPrio(int i, T t) {
		// TODO Auto-generated method stub
		activePrio.put(i,activePrio.get(i)+1);
		file.get(i).add(t);
	}

	@Override
	public void put(T e) {
		// TODO Auto-generated method stub
		activePrio.put(this.getMaxPrio(),activePrio.get(this.getMaxPrio())+1);
		file.get(this.getMaxPrio()).add(e);
	}

	@Override
	public void removePrio(int i) {
		// TODO Auto-generated method stub
		if (activePrio.get(i)==1) {
			activePrio.remove(i);
			file.remove(i);
		}
		activePrio.put(i,activePrio.get(i)-1);
		file.remove(i).remove(0);
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		if (activePrio.get(this.getMaxPrio())==1) {
			activePrio.remove(this.getMaxPrio());
			file.remove(this.getMaxPrio());
		}
		activePrio.put(this.getMaxPrio(),activePrio.get(this.getMaxPrio())-1);
		file.get(this.getMaxPrio()).remove(0);
		
	}
}
