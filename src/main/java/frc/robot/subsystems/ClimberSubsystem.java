// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class ClimberSubsystem extends SubsystemBase {

  MecanumSubsystem mec_subsystem;
  ClimbConstants climbConstants = new ClimbConstants();

  private CANSparkMax climber = new CANSparkMax(9, MotorType.kBrushed);
  DigitalInput topLimitSwitch = new DigitalInput(0);
  
  // CANSparkMax
  public ClimberSubsystem(MecanumSubsystem mecanumSubsystem) {
    mec_subsystem = mecanumSubsystem;
    climber.restoreFactoryDefaults();
  }

  @SuppressWarnings("static-access")
  public void climb() {
    climber.restoreFactoryDefaults();
    climber.set(climbConstants.climbSpeed);
  }

  @SuppressWarnings("static-access")
  @Override
  public void periodic() {
    if (!topLimitSwitch.get()) {
      climber.set(0);
    }
  }

  public void endgame() {

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}