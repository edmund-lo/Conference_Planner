package common;

import javafx.beans.property.BooleanProperty;

public abstract class Selectable {
    private BooleanProperty selected;

    public BooleanProperty getSelected() {
        return selected;
    }

    public boolean getChecked() {
        return this.selected.getValue();
    }

    public void setChecked(boolean selected) {
        this.selected.setValue(selected);
    }
}
