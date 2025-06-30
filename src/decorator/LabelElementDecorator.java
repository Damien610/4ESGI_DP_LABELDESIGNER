package decorator;


import model.ILabelElement;
import model.TypeElement;

public abstract class LabelElementDecorator implements ILabelElement {
    protected ILabelElement decoratedElement;

    public LabelElementDecorator(ILabelElement decoratedElement) {
        this.decoratedElement = decoratedElement;
    }

    @Override
    public String getContent() {
        return decoratedElement.getContent();
    }

    @Override
    public TypeElement getType() {
        return decoratedElement.getType();
    }

    @Override
    public int getPositionX() {
        return decoratedElement.getPositionX();
    }

    @Override
    public int getPositionY() {
        return decoratedElement.getPositionY();
    }

    @Override
    public int getWidth() {
        return decoratedElement.getWidth();
    }

    @Override
    public int getHeight() {
        return decoratedElement.getHeight();
    }

    @Override
    public String getPolice() {
        return decoratedElement.getPolice();
    }

    @Override
    public String getSize() {
        return decoratedElement.getSize();
    }
}