package ui.menu;

import java.rmi.RemoteException;

public interface MenuEvent {
    public void selected(int index, int subIndex) throws Exception;
     
}
