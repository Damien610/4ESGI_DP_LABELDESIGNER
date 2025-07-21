package model;

import observer.LabelObserver;

import java.util.ArrayList;
import java.util.List;

public class LabelElement implements ILabelElement {
    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private TypeElement type;
    private String police;
    private String size;
    private String content;
    private boolean uppercase;
    private final List<LabelObserver> observers = new ArrayList<>();

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        int old = this.positionX;
        this.positionX = positionX;
        notifyObservers("positionX", old, positionX);
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        int old = this.positionY;
        this.positionY = positionY;
        notifyObservers("positionY", old, positionY);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TypeElement getType() {
        return type;
    }

    public void setType(TypeElement type) {
        this.type = type;
    }

    public String getPolice() {
        return police;
    }

    public void setPolice(String police) {
        this.police = police;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        String old = this.content;
        this.content = content;
        notifyObservers("content", old, content);
    }

    public boolean isUppercase() {
        return uppercase;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
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
        String typeLabel = (type != null) ? type.name() : "(building)";
        String contentLabel = (content != null) ? content : "(building)";
        return "[LabelElement " + typeLabel + ": " + contentLabel + "]";
    }

}
