// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.MecanumDrive;



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
  
  double maxSpeed = 1; 

  double xAxis;
  double yAxis;
  double zAxis;

  // boolean rightBumper;
  // boolean leftBumper;

  private MecanumDrive mecanumDrive = new MecanumDrive(motorLeftFront::set, motorLeftBack::set, motorRightFront::set, motorRightBack::set);

  public MecanumSubsystem() {
    motorLeftFront.restoreFactoryDefaults();
    motorLeftBack.restoreFactoryDefaults();
    motorRightFront.restoreFactoryDefaults();
    motorRightBack.restoreFactoryDefaults();

    encoderLeftFront = motorLeftFront.getEncoder();
    encoderLeftBack = motorLeftBack.getEncoder();
    encoderRightFront = motorRightFront.getEncoder();
    encoderRightBack = motorRightBack.getEncoder();
  
    resetEncoders();
  }

  public void drive(double xSpeed, double ySpeed, double rotation) {
    System.err.println("xSpeed: " + xSpeed);
    System.err.println("ySpeed: " + ySpeed);

    motorLeftFront.setInverted(true);
    motorLeftBack.setInverted(true);
    
    mecanumDrive.driveCartesian(ySpeed, xSpeed, rotation);
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
  public RelativeEncoder getEncoderLeftBack() {
    return encoderLeftBack;
  }

  public RelativeEncoder getEncoderRightBack() {
    return encoderRightBack;
  }

  public RelativeEncoder getEncoderLeftFront() {
    return encoderLeftFront;
  }

  public RelativeEncoder getEncoderRightFront() {
    return encoderRightFront;
  }

  public CANSparkMax getMotorLeftFront() {
    return motorLeftFront;
  }

  public CANSparkMax getMotorLeftBack() {
    return motorLeftBack;
  }
  public CANSparkMax getMotorRightFront() {
    return motorRightFront;
  }
  public CANSparkMax getMotorRightBack() {
    return motorRightBack;
  }
  

  @Override
  public void periodic() {
    // double tickToFeet = (encoderLeftBack.getPosition() * 6 * Math.PI)/(12 * gearRatio );
    // System.err.println("Position == " + (encoderLeftBack.getPosition())+"\n");
    //System.err.println("encoderLeftBack.getPosition() * 6 * Math.PI ==" + (encoderLeftBack.getPosition() * 6 * Math.PI)+"\n");
    //System.err.println("(12 * gearRatio)==" + (12 * gearRatio)+"\n");
    //System.err.println("Encoder Left Back Tick To Feet ==" + tickToFeet+"\n");
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
