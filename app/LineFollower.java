package app;

import runnables.*;
import util.DATA;

import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Stopwatch;
import lejos.hardware.lcd.LCD;

public class LineFollower {
	
	
	/**
	 * <b>Line follower-luokka</b><br>
	 * Line followerissa luodaan säikeet ja
	 * määritellään robotin toiminta
	 * 
	 */
	public static void main(String[] args) {
		/**
		 *  Port A: Left motor
		 * Port B: Right motor
		 * Port 3: Colour sensor
		 * Port 4: Ultrasonic sensor
		 */
		
		/**
		 * Luo runMotorille säikeen
		 */
		RunMotor runMotor=new RunMotor();
		Thread motor=new Thread(runMotor);
		
		/**
		 * Luo Color Sensorille säikeen
		 */
		MeasureColor colorSensor=new MeasureColor();
		Thread color=new Thread(colorSensor);
		
		/**
		 * Luo Distance Sensorille säikeen
		 */
		MeasureDistance distSensor=new MeasureDistance();
		Thread distThread = new Thread(distSensor);
		
		System.out.println("Press any button to MOVE and STOP");
		Button.waitForAnyPress();
        
		/**
		 * Kaynnistaa distance sensorin säikeen ja color sensorin
		 */
        distThread.start();
		color.start();
		
		
		/**
		 * Soittaa viiden sekunnin countdown-äänitiedoston
		 */
		Sound.playSample(new File("Countdown.wav"), 100);
		
		motor.start();
		
		/**
		 * Luo sekuntikellon instanssin ja aloittaa ajan laskemisen
		 */
		Stopwatch stopwatch = new Stopwatch();
		
		
		/**
		 * Käynnistää moottorin ja distance sensorin
		 */
		runMotor.startMotor();
		
		distSensor.startSensor();
	
		/**
		 * Soittaa äänitiedostoa koko ajon ajan
		 */
		Sound.playSample(new File("PiggyMono.wav"), 100);
		
        
		/**
		 * Odottaa kunnes robotti lopettaa ajon
		 */
        while (DATA.shouldRun) {
				
		}
			
        /**
		 * Pysäyttää moottorit ja sulkee sensorit
		 */
		runMotor.stopMotor();
		
		distSensor.closeSensor();
		colorSensor.stopColorSensor();
		
		/**
		 * Hakee kuluneen ajan
		 * @param aikaMs kulunut aika millisekunteina
		 * @param aikaSec kulunut aika sekunteina
		 * @param aikaMin kulunut aika minuutteina
		 */
		int aikaMs = stopwatch.elapsed();
		int aikaSec = aikaMs/1000;
		int aikaMin = aikaSec/60;
		
		/**
		 * Putsaa LCD-nayton
		 * Tulostaa rata-ajan LCD-ruudulle
		 */
		LCD.clear();
		LCD.drawString("Rata-aika oli: "+aikaSec, 0, 4);
		
		/**
		 * Odottaa näppäimen painallusta
		 */
		Button.waitForAnyPress();
		
		}
		
	}
	
	



