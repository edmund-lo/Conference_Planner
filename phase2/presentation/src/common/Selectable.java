package common;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public abstract class Selectable {
    private final BooleanProperty selected;

    protected Selectable() {
        this.selected = new SimpleBooleanProperty(false);
    }

    public BooleanProperty getSelected() {
        return selected;
    }

    public boolean isChecked() {
        return this.selected.getValue();
    }

    public void setChecked(boolean selected) {
        this.selected.setValue(selected);
    }
}
