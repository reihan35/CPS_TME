package src.fileprio.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.sound.midi.SysexMessage;
import javax.swing.text.html.HTMLDocument.Iterator;

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
	
	public void print_file() {
		if(isEmpty()) {
	        System.out.println("[]");
		}
        System.out.print("File : [");
		for (Entry<Integer, ArrayList<T>> entry : file.entrySet()) {
	        Integer key = entry.getKey();
	        ArrayList<T> value = entry.getValue();
	        System.out.print(" "+ key + ":" + value + " ");
	    }
		System.out.print("]");
        System.out.print("ActivePrio : [");
		for (Entry<Integer, Integer> entry : activePrio.entrySet()) {
	        Integer key = entry.getKey();
	        Integer value = entry.getValue();
	        System.out.print(" "+ key + ":" + value + " ");
	    }
		System.out.print("]\n");
	}
	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		int s = 0;
		for (Entry<Integer, ArrayList<T>> entry : file.entrySet()) {
	        int value = entry.getValue().size();
	        s = s + value;
		}
		return s;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return getSize()==0;
	}

	@Override
	public Set<Integer> getActivePrio() {
		// TODO Auto-generated method stub
		return activePrio.keySet();
	}

	@Override
	public boolean isActive(int i) {
		return activePrio.containsKey(i);
	}

	@Override
	public int getMaxPrio() {
		if(!isEmpty()) {
			return Collections.max(activePrio.keySet());
		}
		else {
			return 0;
		}
		
	}

	@Override
	public int getSizePrio(int i) {
		if(isActive(i)) {
			return activePrio.get(i);
		}
		else {
			return 0;
		}
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
		return file.get(i).get(k-1);
	}

	@Override
	public void putPrio(int i, T t) {
		// TODO Auto-generated method stub
		if (!isActive(i)) {
			System.out.println("coucouuuu");
			activePrio.put(i,1);
			ArrayList<T> a = new ArrayList<T>();
			a.add(t);
			file.put(i,a);
			//System.out.println(activePrio);
		}
		else {
			activePrio.put(i,activePrio.get(i)+1);
			file.get(i).add(0,t);
		}
	}

	@Override
	public void put(T e) {
		// TODO Auto-generated method stub
		activePrio.put(this.getMaxPrio(),activePrio.get(this.getMaxPrio())+1);
		file.get(this.getMaxPrio()).add(0,e);
	}

	@Override
	public void removePrio(int i) {
		// TODO Auto-generated method stub
		if (activePrio.get(i)==1) {
			System.out.println("Hello");
			activePrio.remove(i);
			file.remove(i);
		}
		else {
			activePrio.put(i,activePrio.get(i)-1);
			file.get(i).remove(0);
		}
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
	public HashMap<Integer,Integer> size_prios(){
	  return activePrio;
	}

  	@Override
	public HashMap<Integer, ArrayList<T>> elems_prios() {
	// TODO Auto-generated method stub
  		return file;
  	}


}
