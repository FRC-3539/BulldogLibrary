package org.bytingbulldogs.bulldoglibrary.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Button Wrapper to allow triggers to be used as boolean buttons
 * 
 * @author FRC-3539
 *
 * @since 02/02/17
 */

public class TriggerButton extends Button {
    private int triggerAxis;
    /* The joystick that the trigger is on. */
    private GenericHID joystick;
    /* The minimal value until the button deactivate */
    private double lowRange = .1;
    /* The minimal value until the button activates */
    private double topRange = .75;

    public TriggerButton(GenericHID joystick, int axis) {
        triggerAxis = axis;
        this.joystick = joystick;
    }

    /**
     * returns the current value of the trigger
     */
    public double getValue() {
        return joystick.getRawAxis(triggerAxis);
    }

    /**
     * Allows us to change the range of the button
     * 
     * @param topRange The minimal value until the button deactivate.
     * @param lowRange The minimal value until the button activates.
     */
    public void setRange(double topRange, double lowRange) {
        this.lowRange = lowRange;
        this.topRange = topRange;
    }

    /**
     * returns weather the button is being "pressed" or not.
     */
    @Override
    public boolean get() {
        if (Math.abs(joystick.getRawAxis(triggerAxis)) > topRange) {
            return true;
        } else if (Math.abs(joystick.getRawAxis(triggerAxis)) < lowRange) {
            return false;
        }
        return false;
    }

}