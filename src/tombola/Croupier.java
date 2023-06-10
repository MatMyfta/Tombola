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
public class Croupier{
    List<Integer> tabellone = new ArrayList();
    List<Integer> sacchetto;
    String ext_n = new String();
    
    Croupier() {
	for (int i = 1; i <= 90; i++) {
	    tabellone.add(i);
	}
	sacchetto = new ArrayList(tabellone);
	Collections.shuffle(sacchetto);
    }
    
    public Object extract() {
	Object t = sacchetto.get(0);
	ext_n = t.toString();
	tabellone.set(tabellone.indexOf(sacchetto.get(0)), 0);
	sacchetto.remove(sacchetto.get(0));
	return t;
    }
}
