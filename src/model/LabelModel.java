package model;

import observer.LabelObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LabelModel {
    private String name;
    private LabelType labelType;
    private float width;
    private float height;
    private LocalDateTime createdAt;
    private List<LabelObserver> observers = new ArrayList<>();
    private List<ILabelElement> elements = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LabelType getLabelType() {
        return labelType;
    }

    public void setLabelType(LabelType labelType) {
        this.labelType = labelType;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ILabelElement> getElements() {
        return elements;
    }

    public void setElements(List<ILabelElement> elements) {
        this.elements = elements;
    }

    public void addObserver(LabelObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String property, Object oldValue, ILabelElement newValue) {
        for (LabelObserver observer : observers) {
            observer.onElementChanged(newValue, property, oldValue, newValue);
        }
    }

    public void addElement(ILabelElement element) {
        this.elements.add(element);
        notifyObservers("addElement", null, element);
    }
}

