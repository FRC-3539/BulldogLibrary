// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.bytingbulldogs.bulldoglibrary.INIConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import org.ini4j.Wini;
import org.ini4j.Profile.Section;

import edu.wpi.first.wpilibj.DriverStation;

/** Add your docs here. */
public class INIConfig {
	Wini ini;
	private String filename;

	public INIConfig(String filename) {
		this.filename = filename;
		reloadConfig();
	}

	public void autoPopulate(Object o) {
		reloadConfig();
		if (ini != null) {
			Field[] fields = o.getClass().getFields();
			String className = o.getClass().getSimpleName();

			for (Field field : fields) {
				String fieldName = field.getName();

				try {
					if (getOrDefault(className, fieldName, null, field.getType()) != null) {
						field.setAccessible(true);

						field.set(o, getOrDefault(className, fieldName, null, field.getType()));

					}

				} catch (IllegalArgumentException | IllegalAccessException e) {
					//DriverStation.reportWarning("Could not load item " + field.getName() + " from file " + filename,
					//		e.getStackTrace());
				}
			}
		}
	}

	/**
	 * 
	 * @return if the option or section is not found returns the default value
	 *         otherwise returns the found value.
	 */
	public <T> T getOrDefault(String sectionName, String optionName, T defaultValue, Class<T> clazz) {
		if (ini != null) {
			if (ini.containsKey(sectionName)) {
				Section s = ini.get(sectionName);
				if (s.containsKey(optionName)) {
					return s.get(optionName, clazz);
				}
				return defaultValue;
			}
			return defaultValue;
		}
		return defaultValue;
	}

	public String getFileName() {
		return filename;
	}

	/**
	 * 
	 * @return True if reload was successful at finding and loading file. False if
	 *         not.
	 */
	public boolean reloadConfig() {
		try {
			ini = new Wini(new File(filename));
			return true;
		} catch (IOException e) {
			//DriverStation.reportWarning("Could not load Ini File " + filename,
			//				e.getStackTrace());
			ini = null;
		}
		return false;
	}

	/**
	 * 
	 * @return True if save was successful. False if
	 *         not.
	 */
	public boolean save(Object o) {
		Field[] fields = o.getClass().getFields();
		String className = o.getClass().getSimpleName();

		ini = new Wini();
		
		for (Field field : fields) {
			String fieldName = field.getName();

			try {
				field.setAccessible(true);
				ini.add(className, fieldName, field.get(field.getType()));
				ini.add("Type", fieldName,field.getType().getSimpleName());
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}

		try {
			ini.store(new File(filename));
			return true;
		} catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
	}

}
