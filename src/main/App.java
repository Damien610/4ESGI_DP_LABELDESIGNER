package main;

import builder.*;
import composite.CompositeLabelElement;
import decorator.UpperCaseContentDecorator;
import model.*;
import observer.LoggerObserver;

import java.time.LocalDateTime;
import java.util.Arrays;

public class App {

    public static void printLabel(LabelModel label) {
        System.out.println("Label créé : " + label.getName());

        for (ILabelElement e : label.getElements()) {
            printElement(e, "  ");
        }
    }

    public static void printElement(ILabelElement element, String indent) {
        if (element instanceof CompositeLabelElement composite) {
            System.out.println(indent + "- Composite [" + composite.getType() + "] :");
            for (ILabelElement child : composite.getChildren()) {
                printElement(child, indent + "  ");
            }
        } else {
            System.out.println(indent + "- Element [" + element.getType() + "] : " + element.getContent());
        }
    }

    public static void main(String[] args) {
        CompositeLabelElement blocPrix = new CompositeLabelElement();
        blocPrix.setPositionX(10);
        blocPrix.setPositionY(50);
        blocPrix.setWidth(200);
        blocPrix.setHeight(40);

        blocPrix.addElement(new LabelElementBuilder()
                .withPositionX(10).withPositionY(50)
                .withWidth(100).withHeight(20)
                .withType(TypeElement.TEXT)
                .withContent("12,99 €")
                .build());

        blocPrix.addElement(new LabelElementBuilder()
                .withPositionX(10).withPositionY(70)
                .withWidth(100).withHeight(20)
                .withType(TypeElement.TEXT)
                .withContent("Reference")
                .withUppercase(true)
                .build());


        LabelElement barcodeElement = new LabelElementBuilder()
                .withType(TypeElement.BARCODE)
                .withContent("123456789012")
                .withPositionX(10)
                .withPositionY(80)
                .withWidth(150)
                .withHeight(40)
                .withObserver(new LoggerObserver())
                .build();

        LabelElement promoText = new LabelElementBuilder()
                .withType(TypeElement.TEXT)
                .withContent("Promo du jour")
                .withPositionX(20)
                .withPositionY(130)
                .withWidth(200)
                .withHeight(20)
                .withObserver(new LoggerObserver())
                .build();


        CompositeLabelElement decoratedBlocPrix = decorateCompositeElements(blocPrix);

        LabelModel label = new LabelModelBuilder()
                .withName("Étiquette Article")
                .withLabelType(LabelType.ARTICLE)
                .withWidth(300)
                .withHeight(150)
                .withCreatedAt(LocalDateTime.now())
                .withElements(Arrays.asList(decoratedBlocPrix, barcodeElement, promoText))
                .build();

        label.addObserver(new LoggerObserver());
        printLabel(label);
    }


    public static CompositeLabelElement decorateCompositeElements(CompositeLabelElement original) {
        CompositeLabelElement result = new CompositeLabelElement();
        result.setPositionX(original.getPositionX());
        result.setPositionY(original.getPositionY());
        result.setWidth(original.getWidth());
        result.setHeight(original.getHeight());

        result.addObserver(new LoggerObserver());

        for (ILabelElement child : original.getChildren()) {
            if (child instanceof LabelElement le) {
                le.addObserver(new LoggerObserver());
                if (le.isUppercase()) {
                    result.addElement(new UpperCaseContentDecorator(le));
                } else {
                    result.addElement(le);
                }
            } else if (child instanceof CompositeLabelElement composite) {
                result.addElement(decorateCompositeElements(composite));
            } else {
                result.addElement(child);
            }
        }

        return result;
    }
}