// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Mecanum;

// import frc.robot.Dashboard;
import frc.robot.subsystems.MecanumSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class TeleopCommand extends Command {

  XboxController xboxController;
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
  // private Dashboard dashboard;

  public TeleopCommand(MecanumSubsystem subsystem, XboxController joystick) {
    this.mec_subsystem = subsystem;
    this.xboxController = joystick;
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
    xAxis = xboxController.getLeftX(); //Strafe
    yAxis = xboxController.getLeftY(); //Front and Back
    zAxis = xboxController.getRightX(); //Turning

    leftTrigger = xboxController.getLeftTriggerAxis();
    rightTrigger = xboxController.getRightTriggerAxis();

    xButton = xboxController.getXButtonPressed();
    yButton = xboxController.getYButtonPressed();
    aButton = xboxController.getAButtonPressed();
    bButton = xboxController.getBButtonPressed();

    leftBumper = xboxController.getLeftBumperPressed();
    rightBumper = xboxController.getRightBumperPressed();

    System.err.println(yAxis);
    // leftBumper = logitech.getRawButton(5);
    // rightBumper = logitech.getRawButton(6);
    mec_subsystem.drive(-1 * xAxis, yAxis, -1 * zAxis);

    // Update the dashboard with current values
    // dashboard.updateTeleopTab(leftBumper, rightBumper, yAxis, zAxis);
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