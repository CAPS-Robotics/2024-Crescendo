// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.Mecanum.TeleopCommand;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class MecanumSubsystem extends SubsystemBase {
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



  public void drive(double yAxis, double zAxis) {
    // motorLeftFront.set(-yAxis);
    // motorRightFront.set(zAxis);
    // motorLeftBack.set(-yAxis);
    // motorRightBack.set(-zAxis);

    motorLeftFront.set(-1);
    motorRightFront.set(1);
    motorLeftBack.set(-1);
    motorRightBack.set(-1);
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
    System.err.println("Encoder Left Front" + encoderLeftFront.getPosition());
    // System.err.println("Encoder Left Back" + encoderLeftBack.getPosition());
    // System.err.println("Encoder Right Front" + encoderRightFront.getPosition());
    // System.err.println("Encoder Right Back" + encoderRightBack.getPosition());
    System.err.println("Position Conversion Factor " + encoderLeftFront.getPositionConversionFactor());
    System.err.println("Counts per rev " + encoderLeftFront.getCountsPerRevolution());
    System.err.println("Measurement Period " + encoderLeftFront.getMeasurementPeriod());
    System.err.println("Velocity Conversion Factor" + encoderLeftFront.getVelocityConversionFactor());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
