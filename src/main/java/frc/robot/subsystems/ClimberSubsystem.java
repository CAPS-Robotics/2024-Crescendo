// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.OperatorConstants;

public class ClimberSubsystem extends SubsystemBase {

  MecanumSubsystem mec_subsystem = new MecanumSubsystem();

  DigitalInput topLimitSwitch = new DigitalInput(2);
  DigitalInput bottomLimitSwitch = new DigitalInput(3);

  private CANSparkMax climber = new CANSparkMax(12, MotorType.kBrushless);

  private RelativeEncoder climbEncoder = climber.getEncoder();

  // CANSparkMax
  public ClimberSubsystem() {
    climber.restoreFactoryDefaults();
    climbEncoder.setPosition(0);
  }

  public void climb() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}