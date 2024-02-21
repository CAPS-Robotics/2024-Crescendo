// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import frc.robot.Dashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



public class MecanumSubsystem extends SubsystemBase{
  /** Creates a new ExampleSubsystem. */

  // Dashboard dashboard = new Dashboard();

  NetworkTable table;

  //DriveBase
  // private CANSparkMax motorLeftFront = new CANSparkMax(3, MotorType.kBrushless); //3
  // private CANSparkMax motorLeftBack = new CANSparkMax(4, MotorType.kBrushless); //4
  // private CANSparkMax motorRightFront = new CANSparkMax(2, MotorType.kBrushless); //2
  // private CANSparkMax motorRightBack = new CANSparkMax(5, MotorType.kBrushless); //5

  // //Shooter
  // private Talon rollerTop = new Talon(6);
  // private Talon rollerBottom = new Talon(7);
  // private Talon loader = new Talon(8);

  // //Climber
  // private CANSparkMax chain = new CANSparkMax(11, MotorType.kBrushless);

  // private RelativeEncoder encoderLeftFront;
  // private RelativeEncoder encoderLeftBack;
  // private RelativeEncoder encoderRightFront;
  // private RelativeEncoder encoderRightBack;
  
 // private TeleopCommand teleopCommand ;
  
  double maxSpeed = 1; 

  double xAxis;
  double yAxis;
  double zAxis;

  // boolean rightBumper;
  // boolean leftBumper;
  double aprilId = SmartDashboard.getNumber("id", -1);
  double aprilAngle = SmartDashboard.getNumber("angle", 1000);
  double aprilDist = SmartDashboard.getNumber("dist", -1);

  boolean climbReady = false;

 // private MecanumDrive mecanumDrive = new MecanumDrive(motorLeftFront::set, motorLeftBack::set, motorRightFront::set, motorRightBack::set);

  public MecanumSubsystem() {
    // motorLeftFront.restoreFactoryDefaults();
    // motorLeftBack.restoreFactoryDefaults();
    // motorRightFront.restoreFactoryDefaults();
    // motorRightBack.restoreFactoryDefaults();

    // encoderLeftFront = motorLeftFront.getEncoder();
    // encoderLeftBack = motorLeftBack.getEncoder();
    // encoderRightFront = motorRightFront.getEncoder();
    // encoderRightBack = motorRightBack.getEncoder();
  
    resetEncoders();
  }

  public void drive(double xSpeed, double ySpeed, double rotation) {
    // System.err.println("xSpeed: " + xSpeed);
    // System.err.println("ySpeed: " + ySpeed);

    // motorLeftFront.setInverted(true);
    // motorLeftBack.setInverted(true);
    
    // mecanumDrive.driveCartesian(ySpeed, xSpeed, rotation);
  }

  public void endGame() {
    System.err.println();
    System.err.println();
    System.err.println();
  }

  public void stop() {
    // motorLeftFront.set(0);
    // motorRightFront.set(0);
    // motorLeftBack.set(0);
    // motorRightBack.set(0);
  }

  public void resetEncoders() {
    // motorLeftFront.restoreFactoryDefaults();
    // motorLeftBack.restoreFactoryDefaults();
    // motorRightFront.restoreFactoryDefaults();
    // motorRightFront.restoreFactoryDefaults();

    // encoderLeftFront.setPosition(0);
    // encoderLeftBack.setPosition(0);
    // encoderRightFront.setPosition(0);
    // encoderRightBack.setPosition(0);
  }

  //Drive
  // public CANSparkMax getMotorLeftFront() { return motorLeftFront; }
  // public CANSparkMax getMotorLeftBack() { return motorLeftBack; }
  // public CANSparkMax getMotorRightFront() { return motorRightFront; }
  // public CANSparkMax getMotorRightBack() { return motorRightBack; }

  // //Drive Encoders
  // public RelativeEncoder getEncoderLeftBack() { return encoderLeftBack; }
  // public RelativeEncoder getEncoderRightBack() { return encoderRightBack; }
  // public RelativeEncoder getEncoderLeftFront() { return encoderLeftFront; }
  // public RelativeEncoder getEncoderRightFront() { return encoderRightFront; }  

  //Shooter

  //Slide

  //Climber


  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Left Front Position", encoderLeftFront.getPosition());
    // SmartDashboard.putNumber("Left Back Position", encoderLeftBack.getPosition());
    // SmartDashboard.putNumber("Right Front Position", encoderRightFront.getPosition());
    // SmartDashboard.putNumber("Right Back Position", encoderRightBack.getPosition());

    // SmartDashboard.putNumber("Left Front Velocity", encoderLeftFront.getVelocity());
    // SmartDashboard.putNumber("Left Back Velocity", encoderLeftBack.getVelocity());
    // SmartDashboard.putNumber("Right Front Velocity", encoderRightFront.getVelocity());
    // SmartDashboard.putNumber("Right Back Velocity", encoderRightBack.getVelocity());

    if (aprilId == 14 || aprilId == 15 || aprilId == 16) {
      climbReady = true;
    }

    SmartDashboard.putBoolean("Ready To Climb", climbReady);
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
