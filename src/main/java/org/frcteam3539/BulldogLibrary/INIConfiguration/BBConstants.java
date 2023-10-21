// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.frcteam3539.BulldogLibrary.INIConfiguration;

/** Add your docs here. */
public abstract class BBConstants {
	private INIConfig iniConfig;
	private Boolean reloadable;

	public BBConstants(String fileName, boolean reloadable)
	{
		iniConfig = new INIConfig(fileName);
		iniConfig.autoPopulate(this);
		this.reloadable = reloadable;
	}
	public void reload()
	{
		if(this.reloadable)
			iniConfig.autoPopulate(this);
		
	}
	public void save()
	{
		iniConfig.save(this);
		
	}
}
