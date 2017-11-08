package view;
import controller.*;
import java.util.Observer;

public abstract class  View implements Observer {
    
    private Controller controller;

    public int choisirCarteJoueur() {
		return 0;
    }
    
    public View () {
    	this.controller = new Controller();
    }

}
