package sensors;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.ColorDetector;
import lejos.robotics.ColorIdentifier;


/**
 *  <b>Color sensor</b><br>
 *  Color sensorin avulla mitataan valon määrää
 *  robotin alla.
 */
public class ColorSensor implements ColorDetector, ColorIdentifier
{
	
	/**
	 * Sensori
	 */
	EV3ColorSensor sensor;
	
	/**
	 * Tallentaa saadut samplet
	 * muuttujaan
	 */
	float[] sample;

	/**
	 * Luo Color Sensor-luokan
	 * @param port
	 */
	public ColorSensor(Port port) {
		sensor = new EV3ColorSensor(port);
		sensor.setCurrentMode("Red");
		sensor.setFloodlight(true);
		sample = new float[sensor.sampleSize()];
	}
	
	/**
	 * Aktivoi Color Sensor
	 */
	public void Enable() {
		sensor.setCurrentMode("Red");
		sensor.setFloodlight(true);
	}
	
	/**
	 * Palauttaa sensorin
	 * @return EV3ColorSensor
	 */
	public EV3ColorSensor getSensor()
	{
		return sensor;
	}
	
	/**
	 * Asettaa sensorin asetukset
	 * samplejen hakuun
	 */
	public void setRedMode()
	{
		sensor.setCurrentMode("Red");
		sample = new float[sensor.sampleSize()];
	}
	
	/**
	 * Hakee sensoriarvon ja palauttaa
	 * float-muodossa (0.00-1.00), 0 = musta
	 * @return float
	 */
	public float getRed()
	{
		sensor.fetchSample(sample, 0);
		
		return sample[0];
	}
	
	/**
	 * Sulkee sensorin
	 */
	public void close()
	{
		sensor.close();
	}
	
	/**
	 * Asettaa flood lightin päälle/pois
	 */
	public void setFloodLight(boolean on)
	{
		sensor.setFloodlight(on);
	}
	
	/**
	 * Asettaa flood lightille värin
	 */
	public void setFloodLight(int color)
	{
		sensor.setFloodlight(color);
	}
	
	/**
	 * Hakee Värin
	 * @return int
	 */
	public static String colorName(int color)
	{
		switch (color)
		{
			case Color.NONE:
				return "None";
				
			case Color.BLACK:
				return "Black";
				
			case Color.BLUE:
				return "Blue";
				
			case Color.BROWN:
				return "Brown";
				
			case Color.CYAN:
				return "Cyan";
				
			case Color.DARK_GRAY:
				return "Dark Gray";
				
			case Color.GRAY:
				return "Gray";
				
			case Color.GREEN:
				return "Green";
				
			case Color.LIGHT_GRAY:
				return "Light Gray";
				
			case Color.MAGENTA:
				return "Magenta";
				
			case Color.ORANGE:
				return "Orange";
				
			case Color.PINK:
				return "Pink";
				
			case Color.RED:
				return "Red";
				
			case Color.WHITE:
				return "White";
				
			case Color.YELLOW:
				return "Yellow";
		}
		
		return "";
	}

	/**
	 * Hakee värin ID:n
	 * @return int
	 */
	@Override
	public int getColorID() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Hakee värin
	 */
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}
}