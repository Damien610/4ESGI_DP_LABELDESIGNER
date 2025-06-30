package composite;

import java.util.ArrayList;
import java.util.List;
import model.ILabelElement;
import model.TypeElement;
import observer.LabelObserver;

public class CompositeLabelElement implements ILabelElement {

    private final List<ILabelElement> children = new ArrayList<>();
    private final List<LabelObserver> observers = new ArrayList<>();

    private int positionX;
    private int positionY;
    private int width;
    private int height;

    public void addElement(ILabelElement element) {
        children.add(element);
        notifyObservers("addElement", null, element);
    }

    public void removeElement(ILabelElement element) {
        children.remove(element);
        notifyObservers("removeElement", element, null);
    }


    public List<ILabelElement> getChildren() {
        return children;
    }

    @Override
    public String getContent() {
        return children.stream()
                .map(ILabelElement::getContent)
                .reduce("", (a, b) -> a + "\n" + b);
    }

    @Override
    public TypeElement getType() {
        return TypeElement.TEXT;
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getPolice() {
        return null; // Un groupe peut ne pas avoir de police
    }

    @Override
    public String getSize() {
        return null;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void addObserver(LabelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LabelObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String property, Object oldValue, Object newValue) {
        for (LabelObserver observer : observers) {
            observer.onElementChanged(this, property, oldValue, newValue);
        }
    }

    @Override
    public String toString() {
        return "[CompositeLabelElement with " + children.size() + " elements]";
    }
}
