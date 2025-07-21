package builder;

import model.*;
import observer.LabelObserver;

import java.util.ArrayList;
import java.util.List;

public class LabelElementBuilder {
    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private TypeElement type;
    private String police;
    private String size;
    private String content;
    private boolean uppercase;

    private final List<LabelObserver> observers = new ArrayList<>(); // 👈

    public LabelElementBuilder withPositionX(int x) {
        this.positionX = x;
        return this;
    }

    public LabelElementBuilder withPositionY(int y) {
        this.positionY = y;
        return this;
    }

    public LabelElementBuilder withWidth(int width) {
        this.width = width;
        return this;
    }

    public LabelElementBuilder withHeight(int height) {
        this.height = height;
        return this;
    }

    public LabelElementBuilder withType(TypeElement type) {
        this.type = type;
        return this;
    }

    public LabelElementBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public LabelElementBuilder withPolice(String police) {
        this.police = police;
        return this;
    }

    public LabelElementBuilder withSize(String size) {
        this.size = size;
        return this;
    }

    public LabelElementBuilder withUppercase(boolean value) {
        this.uppercase = value;
        return this;
    }

    public LabelElementBuilder withObserver(LabelObserver observer) {
        this.observers.add(observer);
        return this;
    }

    public LabelElement build() {
        LabelElement element = new LabelElement();

        for (LabelObserver observer : observers) {
            element.addObserver(observer);
        }

        element.setPositionX(positionX);
        element.setPositionY(positionY);
        element.setWidth(width);
        element.setHeight(height);
        element.setType(type);
        element.setContent(content);
        element.setPolice(police);
        element.setSize(size);
        element.setUppercase(uppercase);

        return element;
    }
}
