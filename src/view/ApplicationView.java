package view;

import controller.Controller;

public abstract class ApplicationView {
    protected Controller c;
    
    
    abstract public void showApplicationStart(String initInfo);
    abstract public void showMainMenu();
    abstract public void showApplicationEnd(String endInfo);
    
    public void setController(Controller c){
        this.c=c;
    }
}
