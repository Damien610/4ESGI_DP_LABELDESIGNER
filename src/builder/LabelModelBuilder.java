package builder;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LabelModelBuilder {

    private String name;
    private LabelType labelType;
    private float width;
    private float height;
    private LocalDateTime createdAt = LocalDateTime.now();
    private List<ILabelElement> elements = new ArrayList<>();

    public LabelModelBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LabelModelBuilder withLabelType(LabelType labelType) {
        this.labelType = labelType;
        return this;
    }

    public LabelModelBuilder withWidth(float width) {
        this.width = width;
        return this;
    }

    public LabelModelBuilder withHeight(float height) {
        this.height = height;
        return this;
    }

    public LabelModelBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LabelModelBuilder addElement(LabelElement element) {
        this.elements.add(element);
        return this;
    }

    public LabelModelBuilder withElements(List<ILabelElement> elements) {
        this.elements = elements;
        return this;
    }

    public LabelModel build() {
        LabelModel model = new LabelModel();
        model.setName(name);
        model.setLabelType(labelType);
        model.setWidth(width);
        model.setHeight(height);
        model.setCreatedAt(createdAt);
        for (ILabelElement e : this.elements) {
            model.addElement(e);
        }
        return model;
    }
}
