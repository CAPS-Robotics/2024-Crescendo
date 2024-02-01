// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.hal.DriverStationJNI;
import edu.wpi.first.util.WPIUtilJNI;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.internal.DriverStationModeThread;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

/**
 * The VM is configured to automatically run this class. If you change the name of this class or the
 * package after creating this project, you must also update the build.gradle file in the project.
 */
public class Robot extends RobotBase {
  Utils utils = new Utils();
  private CANSparkMax motorLeftFront;
   private CANSparkMax motorLeftBack;
   private CANSparkMax motorRightFront;
   private CANSparkMax motorRightBack;

   private RelativeEncoder encoderLeftFront;
   private RelativeEncoder encoderLeftBack;
   private RelativeEncoder encoderRightFront;
   private RelativeEncoder encoderRightBack;

   public final int encoderTicks = 42;
   public final int wheelDiameter = 6;
   public double tickToFeet = ((1/encoderTicks) * Math.PI * wheelDiameter)/12;
   public double travelDistance = 10;
   public double kp = 0.1;
   

   double maxSpeed = 1;
   double xAxis;
   double yAxis;
   double zAxis;

   boolean rightBumper;
   boolean leftBumper;



  private Joystick logitech = new Joystick(0);
  

  public void robotInit() {
    System.out.println("PROGRAM STARTED !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    motorLeftFront = new CANSparkMax(3, MotorType.kBrushless); //3
    motorLeftBack = new CANSparkMax(4, MotorType.kBrushless); //4
    motorRightFront = new CANSparkMax(2, MotorType.kBrushless); //2
    motorRightBack = new CANSparkMax(5, MotorType.kBrushless); //5

    encoderLeftFront = motorLeftFront.getEncoder();
    encoderLeftBack = motorLeftBack.getEncoder();
    encoderRightFront = motorRightFront.getEncoder();
    encoderRightBack = motorRightBack.getEncoder();

    System.out.println("Channels setup !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    System.out.println("Channels setup completed");

  }

  
  public void disabled() {}

  public void autonomousInit(){
  encoderLeftFront.setPosition(0);
  encoderRightFront.setPosition(0);
  encoderLeftBack.setPosition(0);
  encoderRightBack.setPosition(0);

  System.out.println("Init Pos:" + encoderLeftFront.getPosition());
  }

  public void autonomous() {
    System.out.println("Auton");
    double robotPosition = encoderLeftFront.getPosition() * tickToFeet;
    double error = travelDistance - robotPosition;
    double outputSpeed = kp * error;

    System.out.println("robot position = " + robotPosition);
    System.out.println("error = " + error);
    System.out.println("outputSpeed = " + outputSpeed);
    
    
    motorLeftFront.set(outputSpeed);
    motorRightFront.set(-outputSpeed);
    motorLeftBack.set(outputSpeed);
    motorRightBack.set(-outputSpeed);

     


  }
 
  public void teleop() {
    System.out.println("Channels teleop !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    xAxis = logitech.getX() * maxSpeed;
    yAxis = logitech.getY() * maxSpeed;
    zAxis = logitech.getRawAxis(3) * maxSpeed;

    leftBumper = logitech.getRawButton(5);
    rightBumper = logitech.getRawButton(6);

    // System.out.println("x: " + xAxis);
    // System.out.println("y: " + yAxis);
    // System.out.println("z: " + zAxis);
    SmartDashboard.putNumber("x", xAxis);
    SmartDashboard.putNumber("y", yAxis);
    SmartDashboard.putNumber("z", zAxis);
   /**  
    SmartDashboard.putBoolean("Left Bumper Pressed: ", leftBumper);
    SmartDashboard.putBoolean("Right Bumper Pressed: ", rightBumper);
    SmartDashboard.putNumber("Left Front Velocity: ", encoderLeftFront.getVelocity());
    SmartDashboard.putNumber("Left Back Velocity: ", encoderLeftBack.getVelocity());
    SmartDashboard.putNumber("Right Front Velocity: ", encoderRightFront.getVelocity());
    SmartDashboard.putNumber("Right Back Velocity: ", encoderRightBack.getVelocity());
    SmartDashboard.putNumber("Left Front Position: ", encoderLeftFront.getPosition());
    SmartDashboard.putNumber("Left Back Position: ", encoderLeftBack.getPosition());
    SmartDashboard.putNumber("Right Front Position: ", encoderRightFront.getPosition());
    SmartDashboard.putNumber("Right Back Position: ", encoderRightBack.getPosition());**/


    // System.out.println("Left Bumper Pressed: " + leftBumper);
    // System.out.println("Right Bumper Pressed: " + rightBumper);
    System.out.println("Left Front Velocity: " + encoderLeftFront.getVelocity());
    // //System.out.println("Left Back Velocity: " + encoderLeftBack.getVelocity());
    // System.out.println("Right Front Velocity: " + encoderRightFront.getVelocity());
    // System.out.println("Right Back Velocity: " + encoderRightBack.getVelocity());
     System.out.println("Left Front Position: " + encoderLeftFront.getPosition());
    // System.out.println("Left Back Position: " + encoderLeftBack.getPosition());
    // System.out.println("Right Front Position: " + encoderRightFront.getPosition());
    // System.out.println("Right Back Position: " + encoderRightBack.getPosition());
    System.out.println(encoderLeftFront.getPositionConversionFactor());
    System.out.println(encoderLeftFront.getVelocityConversionFactor());

    if(leftBumper) {
      utils.strafeLeft(motorLeftFront, motorLeftBack, motorRightFront, motorRightBack);
    } else if (rightBumper) {
      utils.strafeRight(motorLeftFront, motorLeftBack, motorRightFront, motorRightBack);
    } else {
      motorLeftFront.set(yAxis);
      motorRightFront.set(-zAxis);
      motorLeftBack.set(yAxis);
      motorRightBack.set(-zAxis);
    }
  }

  public void test() {}

  private volatile boolean m_exit;

  @Override
  public void startCompetition() {
    robotInit();

    DriverStationModeThread modeThread = new DriverStationModeThread();

    int event = WPIUtilJNI.createEvent(false, false);

    DriverStation.provideRefreshedDataEventHandle(event);

    // Tell the DS that the robot is ready to be enabled
    DriverStationJNI.observeUserProgramStarting();


    while (!Thread.currentThread().isInterrupted() && !m_exit) {
     
      if (isDisabled()) {
        modeThread.inDisabled(true);
        disabled();
        modeThread.inDisabled(false);

        while (isDisabled()) {
          try {
            WPIUtilJNI.waitForObject(event);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      } else {
        if (isAutonomous()) {
        modeThread.inAutonomous(true);
        autonomousInit();
        // while(isAutonomous())
        // {
        //   autonomous();
        // }
        
        modeThread.inAutonomous(false);
        while (isAutonomousEnabled()) {
          try {
            WPIUtilJNI.waitForObject(event);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      } else if (isTest()) {
        modeThread.inTest(true);
        test();
        modeThread.inTest(false);
        while (isTest() && isEnabled()) {
          try {
            WPIUtilJNI.waitForObject(event);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          }
        }
      } else {
          modeThread.inTeleop(true);
          System.out.println("Tele");
          modeThread.inTeleop(false);
          while (isTeleopEnabled()) {
            try {
              teleop();
              WPIUtilJNI.waitForObject(event);
              
            } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
            }
          }
        }   
      }
    }

    System.out.println("After while");

    DriverStation.removeRefreshedDataEventHandle(event);
    modeThread.close();
  }

  @Override
  public void endCompetition() {
    m_exit = true;
  }
}
