package org.bytingbulldogs.bulldoglibrary.controllers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Button Wrapper to allow directional pad to mock a Button
 * 
 * @author FRC-3539
 *
 * @since 11/12/17
 */

public class DirectionalButton extends Button {
	private int neededAngle;
	private Direction direction;
	private GenericHID joystick;

	/**
	 * Directions that the directional pad supports.
	 * <li>{@link #UP}</li>
	 * <li>{@link #UPRIGHT}</li>
	 * <li>{@link #RIGHT}</li>
	 * <li>{@link #DOWNRIGHT}</li>
	 * <li>{@link #DOWN}</li>
	 * <li>{@link #DOWNLEFT}</li>
	 * <li>{@link #LEFT}</li>
	 * <li>{@link #UPLEFT}</li>
	 */
	public enum Direction {

		/** The Top Directional Button */
		UP,
		/** The Top Right Directional Button */
		UPRIGHT,
		/** The Right Directional Button */
		RIGHT,
		/** The Bottom Right Directional Button */
		DOWNRIGHT,
		/** The Bottom Directional Button */
		DOWN,
		/** The Bottom Left Directional Button */
		DOWNLEFT,
		/** The Left Directional Button */
		LEFT,
		/** The Top Left Directional Button */
		UPLEFT
	}

	/**
	 * Constructor of the directional button wrapper requires a GenericHID joystick
	 * and a direction.
	 * 
	 * @param joystick  the joystick that this button is on.
	 * @param direction the direction enum the relates to the direction that
	 *                  triggers this button.
	 */

	public DirectionalButton(GenericHID joystick, Direction direction) {
		neededAngle = 1;
		this.direction = direction;
		this.joystick = joystick;

		switch (direction) {
			case UP:
				neededAngle = 0;
				break;
			case UPRIGHT:
				neededAngle = 45;
				break;
			case RIGHT:
				neededAngle = 90;
				break;
			case DOWNRIGHT:
				neededAngle = 135;
				break;
			case DOWN:
				neededAngle = 180;
				break;
			case DOWNLEFT:
				neededAngle = 225;
				break;
			case LEFT:
				neededAngle = 270;
				break;
			case UPLEFT:
				neededAngle = 315;
				break;

		}
	}

	/**
	 * returns the current angle of the directional button for example up will be 0
	 * and right will be 90 and up right will be 45.
	 * 
	 * @return the angle value of the directional button
	 */
	public int getPOV() {
		return joystick.getPOV();
	}

	/**
	 * Returns if the button is currently pressed.
	 * 
	 * @return a boolean which is true if the button is currently pressed.
	 */
	public boolean get() {
		if (joystick.getPOV() == neededAngle) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the direction enum of this button.
	 * 
	 * @return the direction enum of this button.
	 */
	public Direction getDirection() {
		return direction;
	}
}