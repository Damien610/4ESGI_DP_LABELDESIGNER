package observer;

import model.ILabelElement;

public class LoggerObserver implements LabelObserver {
    @Override
    public void onElementChanged(ILabelElement element, String propertyChanged, Object oldValue, Object newValue) {
        System.out.println("[LOG] " + element.toString() +
                " → propriété '" + propertyChanged +
                "' changée de [" + oldValue + "] à [" + newValue + "]");
    }
}