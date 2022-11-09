package runnables;

import lejos.hardware.port.SensorPort;
import lejos.robotics.LampController;
import sensors.ColorSensor;
import util.DATA;

/**
 * <b>MeasureColor luokka</b><br>
 * Luokka hakee, kuinka paljon punaista valoa heijastuu sensorin
 * alla olevasta pinnasta ja tallentaa tiedon DATA-utilityluokkaan
 */
public class MeasureColor implements Runnable{

	
	/**
	 * ColorSensor muuttuja, jolle annetaan SensorPort parametri,
	 * johon sensori on kiinnitetty
	 */
	ColorSensor cs = new ColorSensor(SensorPort.S3);
	
	
	/**
	 * Runnable rajapinnan pää-metodi<br>
	 * säie pyorii kokoajan hakien sensorin väriarvoa ja tallentaa
	 * sen <i>DATA.colorValue</i> muuttujaan
	 */
	@Override
	public void run() {
		while(DATA.shouldRun) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Hakee ColorSensor luokasta arvon ja tallentaa sen DATA luokkaan
			//System.out.println("Measuring color");
			DATA.colorValue = cs.getRed();
			
		}
	}
	
	/**
	 * Värisensorin käynnistys
	 */
	public void startColorSensor() {
		cs.Enable();
	}
	
	/**
	 * Värisensorin sulkeminen
	 */
	public void stopColorSensor() {
		cs.close();
	}
	
	
	

}
