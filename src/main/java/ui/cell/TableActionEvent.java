package ui.cell;

import java.rmi.RemoteException;

public interface TableActionEvent {

    public void onEdit(int row) throws RemoteException;

    public void onDelete(int row);

    public void onView(int row);
}
