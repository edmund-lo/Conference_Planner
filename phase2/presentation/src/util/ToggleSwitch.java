package util;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * Utility class for a toggle switch between on and off
 */
public class ToggleSwitch extends StackPane {
    private final Rectangle back = new Rectangle(30, 10, Color.RED);
    private final Button button = new Button();
    private final String buttonStyleOff =
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE;";
    private final String buttonStyleOn =
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #00893d;";
    private final BooleanProperty state;

    /**
     * ToggleSwitch constructor with default settings
     */
    public ToggleSwitch() {
        this.state = new SimpleBooleanProperty(false);
        init();
        button.setFocusTraversable(false);
    }

    /**
     * Applies initial setting and transforms a JavaFX StackPane into a toggle switch
     */
    private void init() {
        getChildren().addAll(back, button);
        setMinSize(30, 15);
        back.maxWidth(30);
        back.minWidth(30);
        back.maxHeight(10);
        back.minHeight(10);
        back.setArcHeight(back.getHeight());
        back.setArcWidth(back.getHeight());
        back.setFill(Color.valueOf("#ced5da"));
        double r = 2.0;
        button.setShape(new Circle(r));
        setAlignment(button, Pos.CENTER_LEFT);
        button.setMaxSize(15, 15);
        button.setMinSize(15, 15);
        button.setStyle(buttonStyleOff);
    }

    /**
     * Event handler when the switch is clicked on
     * @return EventHandler of Event object representing the event handler
     */
    public EventHandler<Event> toggleOnClick() {
        return e -> {
            if (state.getValue()) {
                button.setStyle(buttonStyleOff);
                back.setFill(Color.valueOf("#ced5da"));
                setAlignment(button, Pos.CENTER_LEFT);
                //state.setValue(false);
            } else {
                button.setStyle(buttonStyleOn);
                back.setFill(Color.valueOf("#80C49E"));
                setAlignment(button, Pos.CENTER_RIGHT);
                //state.setValue(true);
            }
        };
    }

    /**
     * Gets current toggle state
     * @return boolean representing whether switch is on or off
     */
    public boolean getToggleState() {
        return this.state.getValue();
    }

    /**
     * Sets the toggle state to state
     * @param state boolean representing new state
     */
    public void setToggleState(boolean state) {
        this.state.setValue(state);
    }

    /**
     * Gets the button associated with the toggle switch
     * @return JavaFX Button object representing button class variable
     */
    public Button getToggleButton() { return this.button; }
}
