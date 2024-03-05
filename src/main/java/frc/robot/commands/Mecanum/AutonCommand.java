// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Mecanum;

import frc.robot.Constants.OperatorConstants;
// import frc.robot.Dashboard;
import frc.robot.subsystems.MecanumSubsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class AutonCommand extends Command {

  // double maxSpeed = .5;
  // double setPoint; // Ft
  // double totalRotations;
  // double kp;
  // double error;
  // double outputSpeed;

  CANSparkMax motorLeftFront;
  CANSparkMax motorLeftBack;
  CANSparkMax motorRightFront;
  CANSparkMax motorRightBack;

  RelativeEncoder encoderLeftFront;
  RelativeEncoder encoderLeftBack;
  RelativeEncoder encoderRightFront;
  RelativeEncoder encoderRightBack;

  int aprilId = (int) SmartDashboard.getNumber("id", -1);
  double aprilAngle = SmartDashboard.getNumber("angle", 1000);
  double aprilDist = SmartDashboard.getNumber("dist", -1);

  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private MecanumSubsystem mec_subsystem;
  private OperatorConstants operatorConstants;

  public AutonCommand(MecanumSubsystem subsystem, double setPoint) {
    System.err.println("Auton Command subs" + subsystem);
    this.mec_subsystem = subsystem;
    operatorConstants = new OperatorConstants();
    // this.setPoint = setPoint;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mec_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    motorLeftFront = mec_subsystem.getMotorLeftFront();
    motorLeftBack = mec_subsystem.getMotorLeftBack();
    motorRightFront = mec_subsystem.getMotorRightFront();
    motorRightBack = mec_subsystem.getMotorRightBack();

    encoderLeftFront = motorLeftFront.getEncoder();
    encoderLeftBack = motorLeftBack.getEncoder();
    encoderRightFront = motorRightFront.getEncoder();
    encoderRightBack = motorRightBack.getEncoder();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (avgEncoderPosition() <= 7 * operatorConstants.ftToRev) {
      mec_subsystem.drive(operatorConstants.movementSpeed, 0, 0);
    }

    if (DriverStation.getAlliance().equals("Red")) {
      if (DriverStation.getLocation().getAsInt() == 1 || DriverStation.getLocation().getAsInt() == 2) {
        if (aprilDist <= operatorConstants.distToAmp * 12) {
          mec_subsystem.drive(operatorConstants.movementSpeed, 0, 0);
        }
      }
    } else if (DriverStation.getAlliance().equals("Blue")) {

    } else {
      System.err.println("Driver Station Alliance Invalid State: " + DriverStation.getAlliance());
    }

    if (aprilId > 0) {
      if(aprilId == 12 && aprilAngle >= 89 && aprilAngle <= 91) { //7
        SmartDashboard.putBoolean("April-Present", true);
      } else {
        SmartDashboard.putBoolean("April-Present", false);
      }
      mec_subsystem.drive(.25, 0, 180 - aprilAngle);
    }
    // System.err.println(" in autoCommand Execute method");
    // System.err.println("Sp: " + setPoint);
    // // setPoint = 10;
    // totalRotations = ((setPoint * 12) / (6 * Math.PI)) * 12.57;
    // System.err.println("total Rotations: " + totalRotations);

    // error = totalRotations - mec_subsystem.getEncoderLeftBack().getPosition();
    // System.err.println("Error:" + error);
    // kp = 1 / totalRotations;
    // outputSpeed = error * kp;
    // System.err.println("OutSpeed In Auto: " + outputSpeed);
    // if (outputSpeed > 0) {
    //   mec_subsystem.drive(0, outputSpeed, 0);
    // } else {
    //   System.err.println("STOP!");
    // }
  }

  private double avgEncoderPosition() {
    return (encoderLeftFront.getPosition() + encoderLeftBack.getPosition() + encoderRightFront.getPosition() + encoderRightBack.getPosition())/4;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}