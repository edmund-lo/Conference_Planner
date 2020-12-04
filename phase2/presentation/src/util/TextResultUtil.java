package util;

import javafx.css.PseudoClass;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Collection;

public class TextResultUtil {
    public static final TextResultUtil Instance = new TextResultUtil();

    public static TextResultUtil getInstance() { return Instance; }

    private TextResultUtil() {}

    public void addPseudoClass(String pseudoClassString, Node node) {
        PseudoClass pseudoClass = PseudoClass.getPseudoClass(pseudoClassString);
        node.pseudoClassStateChanged(pseudoClass, true);
    }

    public void removeAllPseudoClasses(Node node) {
        Collection<String> classes = new ArrayList<>();
        classes.add("error");
        classes.add("warning");
        classes.add("success");
        node.getPseudoClassStates().removeAll(classes);
    }
}
