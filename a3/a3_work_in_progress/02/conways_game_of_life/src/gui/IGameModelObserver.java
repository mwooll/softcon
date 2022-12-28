package gui;

public interface IGameModelObserver {

    void currentPlayerChanged();
    void stateCanDeleteChanged();
    void stateCanCreateChanged();

}
