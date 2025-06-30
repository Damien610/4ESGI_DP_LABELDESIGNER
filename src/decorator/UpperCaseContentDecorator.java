package decorator;

import model.ILabelElement;

public class UpperCaseContentDecorator extends LabelElementDecorator {

    public UpperCaseContentDecorator(ILabelElement element) {
        super(element);
    }

    @Override
    public String getContent() {
        return super.getContent().toUpperCase();
    }

    @Override
    public String toString() {
        return "[UpperCaseDecorator -> " + decoratedElement.toString() + "]";
    }
}
