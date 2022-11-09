package runnables;

import lejos.hardware.Sound;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;
import util.DATA;

/**
 * RunMotor luokalla kontrolloidaan robotin moottoreita. Moottorien avulla hoidetaan väistaminen, kääntyminen ja yleisesti liikkuminen
 */
public class RunMotor implements Runnable{
	/**
	 * Moottorien vakio nopeus
	 */
	int power = 60;
	//midpoint home 0.22, koulu 0.1
	
	/**
	 * Värisensorista saadun mustan ja valkoisen arvon keskipiste
	 */
	float midpoint = 0.1f;
	
	/**
	 * Molemille moottoreille luodaan omat oliot, joita voidaan hallita erikseen
	 */
	UnregulatedMotor motorA = new UnregulatedMotor(MotorPort.A);
	UnregulatedMotor motorB = new UnregulatedMotor(MotorPort.B);
	
	/**
	 * Main metodissa tehty motor säie suorittaa run() metodin
	 */
	@Override
	public void run() {
		/**
		 * Loopin ansiosta robotti liikkuu niin kauan kunnes shouldRun on false
		 */
		while (DATA.shouldRun) {
			/**
			 * Thread.sleep avustaa säikeiden oikeanlaista toimintaa
			 */
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/**
			 * Laittaa molemmat moottorit pyörimään eteenpäin
			 */
			motorA.forward();
			motorB.forward();
			//Kotona keskikohta 0.22 musta 0.06 valkoinen 0.3
			//koulussa keskikohta 0.1 musta 0.01 valkoinen 0.22
			//System.out.println(DATA.colorValue);
			//before 0.26, 0,22
			//koulussa 0.15, 0.05
			
			/**
			 * Jos robotin värisensori on valkoisella liikaa
			 */
			if(DATA.colorValue > 0.15) {
                turnRightMath();
			}
			/**
			 * Jos robotin värisensori on mustalla liikaa
			 */
			else if (DATA.colorValue < 0.05) {
                turnLeftMath();
            }
			/**
			 * Muuten aja eteenpain
			 */
			else {
				startMotor();
			}
 			
        }
	}
	
	/**
	 * Asettaa moottorien tehot vakio arvoon, jotta robotti kulkisi suoraan eteenpain
	 */
	public void startMotor() {
    	motorA.setPower(power);
    	motorB.setPower(power);
	}
	/**
	 * Asettaa moottorien tehot nolliin, seka pistää ohjelman päätökseen asettamalla shouldRun arvon falseksi
	 */
	public void stopMotor() {
		DATA.shouldRun=false;
		motorA.setPower(0);
    	motorB.setPower(0);
		
	}
	
	/**
	 * Laskee moottorille oikean tehon arvon oikealle kääntymiseen.
	 * Metodi laskee virhearvon siita kuinka kaukana viivan reunasta robotti on väriarvojen avulla.
	 * Metodi kertoo virhearvon vakiolla, pyöristää sen, vähentää nykyisestä arvosta, minkä jälkeen asettaa arvon uudeksi tehoksi.
	 */
	public void turnRightMath() {
		float error;
		float steer;
		int steerInt;
		int powerFinal;
		error = DATA.colorValue - midpoint;
		steer = error * 3.4f;
		steerInt = Math.round(steer*100);
		powerFinal = power-steerInt;
		//System.out.print("Right ");
		//System.out.println(powerFinal);
		motorB.setPower(powerFinal);
	}
	
	/**
	 * Laskee moottorille oikean tehon arvon vasemmalle kaantymiseen.
	 * Laskentatapa on sama kuin oikealle kääntyessa, mutta vakio, jolla kerrotaan on hieman eri,
	 * seka tehon vähennys tehdään toiselle moottorille.
	 */
	public void turnLeftMath() {
		float error;
		float steer;
		int steerInt;
		int powerFinal;
		error = midpoint - DATA.colorValue;
		steer = error * 4.0f;
		steerInt = Math.round(steer*100);
		powerFinal = power-steerInt;
		//System.out.print("Left ");
		//System.out.println(powerFinal);
		motorA.setPower(powerFinal);
	}
	
	/**
	 * Käännytaan oikealle kaaressa
	 */
	public void turnRight() {
    	motorB.setPower(27);
    	motorA.setPower(50);
    }
	
	/**
	 * Käännytaan vasemmalle kaaressa
	 */
	public void turnLeft() {
    	motorA.setPower(15);
    }
	
	/**
	 * Käännytaan paikallaan oikealle
	 */
	public void turnRightAll() {
		//System.out.println("Right");
		motorB.setPower(0);
	}
	
	/**
	 * Käännytaan paikallaan vasemmalle
	 */
	public void turnLeftAll() {
		//System.out.println("Left");
		motorA.setPower(0);
		motorB.setPower(40);
	}

}
