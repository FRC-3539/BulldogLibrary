// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package org.bytingbulldogs.bulldoglibrary.networking;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Used to get the mac addresses of the robot and other devices. */
public class MacAddress {
	String practice;
	boolean listAddresses;

	public MacAddress(String practice) {
		this(practice, true);
	}

	public MacAddress(String practice, boolean listAddresses) {
		this.practice = practice;
		this.listAddresses = listAddresses;
	}

	public boolean getIsPractice() {
		try {
			for (byte[] macAddress : getMacAddresses()) {
				// Check if we are the practice bot
				if (practice.equals(macToString(macAddress))) {
					return true;
				}
			}
		} catch (IOException e) {
			DriverStation.reportError("Error Retrieving Mac Addresses.", e.getStackTrace());
		}
		return false;
	}

	/**
	 * Gets the MAC addresses of all present network adapters.
	 *
	 * @return the MAC addresses of all network adapters.
	 */
	public List<byte[]> getMacAddresses() throws IOException {
		List<byte[]> macAddresses = new ArrayList<>();

		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

		NetworkInterface networkInterface;
		while (networkInterfaces.hasMoreElements()) {
			networkInterface = networkInterfaces.nextElement();

			byte[] address = networkInterface.getHardwareAddress();
			if (address == null) {
				continue;
			}

			macAddresses.add(address);
		}

		listAddresses(macAddresses);

		return macAddresses;
	}

	public void listAddresses(List<byte[]> macAddresses) {
		if (listAddresses) {
			String[] macAddressStrings;
			macAddressStrings = macAddresses.stream().map(this::macToString).toArray(String[]::new);
			SmartDashboard.putStringArray("MAC Addresses", macAddressStrings);
			SmartDashboard.putString("Practice Bot MAC Address", practice);
		}

	}

	public String macToString(byte[] address) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < address.length; i++) {
			if (i != 0) {
				builder.append(':');
			}
			builder.append(String.format("%02X", address[i]));
		}
		return builder.toString();
	}

}