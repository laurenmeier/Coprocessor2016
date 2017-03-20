
package org.usfirst.frc.team3501.robot;

import java.io.UnsupportedEncodingException;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

  static I2C Wire = new I2C(Port.kOnboard, 42);
  Joystick joy = new Joystick(1);
  byte[] ReadData = new byte[3];

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {

  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
   * Dashboard, remove all of the chooser code and uncomment the getString code
   * to get the auto name from the text box below the Gyro
   *
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {

  }

  /**
   * This function is called periodically during autonomous
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {

  }

  /**
   * This function is called periodically during operator control
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    sendMessage();
    // uncomment statement below to call the method
    // for the Roborio to receive a message from the coprocessor
    // receiveMessage();
  }

  public void sendMessage() {
    String WriteString = "go";
    char[] CharArray = WriteString.toCharArray();
    byte[] WriteData = new byte[CharArray.length];
    for (int i = 0; i < CharArray.length; i++) {
      WriteData[i] = (byte) CharArray[i];
    }
    Wire.transaction(WriteData, WriteData.length, null, 0);
  }

  public void receiveMessage() {
    // not currently a working solution, however our research has lead to the
    // conclusion that the bug is due to an encoding error
    byte[] dataReceived = new byte[3];
    String message = "";
    boolean received = Wire.transaction(null, 0, dataReceived,
        dataReceived.length);
    try {
      message = new String(dataReceived, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    if (message.equals("go"))
      System.out.println("Message received");
    else
      System.out.println("Not received");
  }

  /**
   * This function is called periodically during test mode
   */
  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }
}
