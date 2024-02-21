// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Mecanum;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.MecanumSubsystem;

public final class Autos {
  /** Example static factory for an autonomous command. */
  public static Command driveAuto(MecanumSubsystem subsystem, double setPoint) {
    return Commands.sequence(new AutonCommand(subsystem,setPoint));
   }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
