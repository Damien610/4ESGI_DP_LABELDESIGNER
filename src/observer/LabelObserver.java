package observer;

import model.ILabelElement;

public interface LabelObserver {
    void onElementChanged(ILabelElement element, String propertyChanged, Object oldValue, Object newValue);
}
