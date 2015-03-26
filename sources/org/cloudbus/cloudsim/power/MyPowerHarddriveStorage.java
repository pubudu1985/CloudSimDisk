/*
 * Title: CloudSim EES Extention Description: CloudSim extention for Energy Efficient Storage
 * Licence: GPL - http://www.gnu.org/copyleft/gpl.html Copyright (c) 2015, Luleå University of
 * Techonology
 */
package org.cloudbus.cloudsim.power;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.MyHarddriveStorage;
import org.cloudbus.cloudsim.ParameterException;
import org.cloudbus.cloudsim.power.models.PowerModel;
import org.cloudbus.cloudsim.storage.models.harddrives.StorageModelHdd;

/**
 * MyPowerHarddriveStorage enables simulation of power-aware Hard drives.
 * 
 * @author baplou
 */
public class MyPowerHarddriveStorage extends MyHarddriveStorage {
	
	/**
	 * The power model.
	 */
	private PowerModel	powerModelHdd;
	
	/**
	 * Creates a new harddrive storage base on a specific Storage Model Hdd.
	 * 
	 * @param id
	 * 
	 * @param name
	 *            the name of the new harddrive storage
	 * @param storageModelHdd
	 *            the specific model of Hard disk drive
	 * @param powerModel
	 *            the power model
	 * @throws ParameterException
	 *             when the name and the capacity are not valid
	 */
	public MyPowerHarddriveStorage(
			int id,
			String name,
			StorageModelHdd storageModelHdd,
			PowerModel powerModel) throws ParameterException {
		super(id, name, storageModelHdd);
		setPowerModelHdd(powerModel);
	}
	
	/**
	 * Sets the power model.
	 * 
	 * @param powerModelHdd
	 *            the new power model for Hdd
	 */
	protected void setPowerModelHdd(PowerModel powerModelHdd) {
		this.powerModelHdd = powerModelHdd;
	}
	
	/**
	 * Gets the power model.
	 * 
	 * @return the power model
	 */
	public PowerModel getPowerModelHdd() {
		return powerModelHdd;
	}
	
	/**
	 * Gets the power.
	 * 
	 * @param mode
	 *            0 for idle, 1 for operating
	 * @return the power
	 */
	public double getPower(double mode) {
		double power = 0;
		if (mode != 0 && mode != 1) {
			Log.printLine(this.getName() + ".getPower(): Warning - 0 for Idle mode, 1 for operating mode.");
			return power;
		}
		
		try {
			power = getPowerModelHdd().getPower(mode);
		} catch (Exception e) {
			Logger.getLogger(MyPowerHarddriveStorage.class.getName()).log(Level.SEVERE, null, e);
			System.exit(0);
		}

		return power;
	}
}