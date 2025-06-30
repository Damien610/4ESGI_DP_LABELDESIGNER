package builder;

import model.*;

public class LabelElementBuilder {

    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private TypeElement type = TypeElement.TEXT;
    private String police = "Arial";
    private String size = "12";
    private String content;
    private boolean uppercase = false;

    public LabelElementBuilder withPositionX(int positionX) {
        this.positionX = positionX;
        return this;
    }

    public LabelElementBuilder withPositionY(int positionY) {
        this.positionY = positionY;
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

    public LabelElementBuilder withPolice(String police) {
        this.police = police;
        return this;
    }

    public LabelElementBuilder withSize(String size) {
        this.size = size;
        return this;
    }

    public LabelElementBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public LabelElementBuilder withUppercase(boolean uppercase) {
        this.uppercase = uppercase;
        return this;
    }

    public LabelElement build() {
        LabelElement element = new LabelElement();
        element.setPositionX(positionX);
        element.setPositionY(positionY);
        element.setWidth(width);
        element.setHeight(height);
        element.setType(type);
        element.setPolice(police);
        element.setSize(size);
        element.setContent(content);
        element.setUppercase(uppercase);
        return element;
    }
}

