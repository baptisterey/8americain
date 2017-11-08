package model;
import view.Observer;

import java.util.ArrayList;
import java.util.List;


public class Observable {
    
    private List<Observer> observers = new ArrayList<Observer> ();

    
    public void addObserver(Observer observer) {
    }

    
    public void deleteObserver(String observer) {
    }

    
    public void notifyObservers() {
    }

}
