// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class MecanumSubsystem extends SubsystemBase {
  double gearRatio = Constants.OperatorConstants.gearRatio;
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax motorLeftFront = new CANSparkMax(3, MotorType.kBrushless); //3
  private CANSparkMax motorLeftBack = new CANSparkMax(4, MotorType.kBrushless); //4
  private CANSparkMax motorRightFront = new CANSparkMax(2, MotorType.kBrushless); //2
  private CANSparkMax motorRightBack = new CANSparkMax(5, MotorType.kBrushless); //5

  private RelativeEncoder encoderLeftFront;
  private RelativeEncoder encoderLeftBack;
  private RelativeEncoder encoderRightFront;
  private RelativeEncoder encoderRightBack;
  
 // private TeleopCommand teleopCommand ;
  
  double xAxis;
  double yAxis;
  double zAxis;

  boolean rightBumper;
  boolean leftBumper;

  public MecanumSubsystem() {
    encoderLeftFront = motorLeftFront.getEncoder();
    encoderLeftBack = motorLeftBack.getEncoder();
    encoderRightFront = motorRightFront.getEncoder();
    encoderRightBack = motorRightBack.getEncoder();   
    
    resetEncoders();
  }



  public void drive(double yAxis) {
    motorLeftFront.set(-yAxis);
    motorRightFront.set(yAxis);
    motorLeftBack.set(-yAxis);
    motorRightBack.set(-yAxis);
  }
  
  public void strafeLeft(double speed) {
    motorLeftFront.set(-speed);
    motorRightFront.set(-speed);
    motorLeftBack.set(speed);
    motorRightBack.set(-speed);
    }

  public void strafeRight(double speed) {
    motorLeftFront.set(speed);
    motorRightFront.set(speed);
    motorLeftBack.set(-speed);
    motorRightBack.set(speed);
  }

  public void stop() {
    motorLeftFront.set(0);
    motorRightFront.set(0);
    motorLeftBack.set(0);
    motorRightBack.set(0);
  }

  public void resetEncoders() {
    motorLeftFront.restoreFactoryDefaults();
    motorLeftBack.restoreFactoryDefaults();
    motorRightFront.restoreFactoryDefaults();
    motorRightFront.restoreFactoryDefaults();

    encoderLeftFront.setPosition(0);
    encoderLeftBack.setPosition(0);
    encoderRightFront.setPosition(0);
    encoderRightBack.setPosition(0);
  }

  @Override
  public void periodic() {
    double tickToFeet = (encoderLeftBack.getPosition() * 6 * Math.PI)/(12 * gearRatio);
    System.err.println("Encoder Left Back" + encoderLeftBack.getPosition());
    System.err.println("Encoder Left Back Tick To Feet" + tickToFeet);
    // System.err.println("Encoder Left Back" + encoderLeftBack.getPosition());
    // System.err.println("Encoder Right Front" + encoderRightFront.getPosition());
    // System.err.println("Encoder Right Back" + encoderRightBack.getPosition());
    // System.err.println("Position Conversion Factor " + encoderLeftFront.getPositionConversionFactor());
    // System.err.println("Counts per rev " + encoderLeftFront.getCountsPerRevolution()); //12.571471214294434
    // System.err.println("Measurement Period " + encoderLeftFront.getMeasurementPeriod());
    // System.err.println("Velocity Conversion Factor" + encoderLeftFront.getVelocityConversionFactor());

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
