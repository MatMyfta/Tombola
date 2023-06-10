/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tombola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author acer
 */
public class Player {
    List<List<Integer>> cartelle = new ArrayList<>();

    
    Player(int maxCartelle) {
	for (int i = 0; i < maxCartelle; i++) {
	    List<Integer> l = new ArrayList();
	    for (int j = 1; j <= 90; j++) {
		l.add(j);
	    }
	    Collections.shuffle(l);
	    List<Integer> cartella = new ArrayList();
	    for (int j = 0; j < 15; j++) {
		cartella.add(l.get(j));
	    }
	    cartelle.add(cartella);
	}
    }
    
    public void copri(Integer n) {
	for (List<Integer> cartella : cartelle)
	    if(cartella.indexOf(n) >= 0) 
		cartella.set(cartella.indexOf(n), 0);
    }
    
    public int cerca (List<Integer> c, int n) {
	return c.indexOf(n);
    }
    
    private static boolean vittoria (List<Integer> cartella) {
	/*for (int i = 0; i < cartella.size(); i++) {
	    if(cartella.get(i) != 0) {
		return false;
	    }
	}*/
	for (Integer n : cartella)
	    if (n != 0)
		return false;
	return true;
    }
    
    public boolean hoVinto() {
	for (List<Integer> cartella : cartelle) {
	    if (vittoria(cartella)) return true;
	}
	return false;
    }
}
