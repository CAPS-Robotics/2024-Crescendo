// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Mecanum;

import frc.robot.subsystems.ClimberSubsystem;
// import frc.robot.Dashboard;
import frc.robot.subsystems.MecanumSubsystem;
// import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SlideSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopCommand extends Command {

  Joystick joystick;
  double speed;
  double xAxis;
  double yAxis;
  double zAxis;

  double leftTrigger;
  double rightTrigger;

  boolean xButton;
  boolean yButton;
  boolean aButton;
  boolean bButton;

  boolean leftBumper;
  boolean rightBumper;

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private MecanumSubsystem mec_subsystem;
  // private ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
  private SlideSubsystem slideSubsystem = new SlideSubsystem();
  private ClimberSubsystem climberSubsystem = new ClimberSubsystem();
  // private Dashboard dashboard;

  public TeleopCommand(MecanumSubsystem subsystem, Joystick joystick) {
    this.mec_subsystem = subsystem;
    this.joystick = joystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mec_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    speed = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    xAxis = joystick.getRawAxis(0); //Strafe
    yAxis = joystick.getRawAxis(1); //Front and Back
    zAxis = joystick.getRawAxis(4); //Turning


    leftTrigger = joystick.getRawAxis(2);
    rightTrigger = joystick.getRawAxis(3);

    xButton = joystick.getRawButton(3);
    yButton = joystick.getRawButton(4);
    aButton = joystick.getRawButton(1); // Endgame
    bButton = joystick.getRawButton(2);

    leftBumper = joystick.getRawButton(5);
    rightBumper = joystick.getRawButton(6);

    if(aButton) {
      System.err.println("Endgame");
      mec_subsystem.endGame();
    } 

    if (!xButton && !yButton) {
      slideSubsystem.stop();
    } else if (xButton) {
      slideSubsystem.slideToPosition(.25, 100);
    } else if (yButton) {
      slideSubsystem.slideToPosition(-.25, 50);
    } else {
      System.err.println("What?");
    }

    mec_subsystem.drive(-1 * xAxis, yAxis, -1 * zAxis);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}