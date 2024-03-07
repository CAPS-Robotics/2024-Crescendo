// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Mecanum;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.Distances;
import frc.robot.subsystems.ClimberSubsystem;
// import frc.robot.Dashboard;
import frc.robot.subsystems.MecanumSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.SlideSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** An example command that uses an example subsystem. */
public class TeleopCommand extends Command {

  Joystick joystick;
  int pov;
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

  double distNow;
  double distToMove;

  int aprilId = -1;
  double aprilAngle = 1000;
  double aprilDist = -1;

  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private ClimberSubsystem climbSubsystem;
  private MecanumSubsystem mec_subsystem;
  private SlideSubsystem slideSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private OperatorConstants operatorConstants;
  private Distances distances;

  public TeleopCommand(MecanumSubsystem subsystem, ShooterSubsystem shoot_Subsystem,
                         Joystick joystick) {
    operatorConstants = new OperatorConstants();
    distances = new Distances();

    this.shooterSubsystem = shoot_Subsystem;
    // this.slideSubsystem = slideSubsystem; 
    // this.climbSubsystem = climbSubsystem;
    this.mec_subsystem = subsystem;
    this.joystick = joystick;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mec_subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    speed = 0;
    pov = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pov = joystick.getPOV();

    xAxis = joystick.getX(); // Strafe
    yAxis = joystick.getY(); // Front and Back
    zAxis = joystick.getZ(); // Turning

    SmartDashboard.putNumber("x", xAxis);
    SmartDashboard.putNumber("y", yAxis);
    SmartDashboard.putNumber("z", zAxis);

    leftTrigger = joystick.getRawAxis(2);
    rightTrigger = joystick.getRawAxis(3);

    xButton = joystick.getRawButton(3);
    yButton = joystick.getRawButton(4);
    aButton = joystick.getRawButton(1); // Endgame
    bButton = joystick.getRawButton(2);

    leftBumper = joystick.getRawButton(5);
    rightBumper = joystick.getRawButton(6);

    //System.err.println(SmartDashboard.getNumber("id", -1));

    aprilId = (int) SmartDashboard.getNumber("id", -1);
    aprilAngle = SmartDashboard.getNumber("angle", 1000);
    aprilDist = SmartDashboard.getNumber("dist", -1);
    if (yButton) {
      shooterSubsystem.intake();
    } else if (xButton) {
      shooterSubsystem.shootAmp();
    } else if (bButton) {
      shooterSubsystem.shootTrap();
    } else {
    // shooterSubsystem.stop();
    }
     
    //  if (aButton) {
    //   System.err.println("Endgame");
    //   if(aprilId == 12) {
    //     distNow = Math.sqrt(Math.pow(aprilDist, 2) - Math.pow(operatorConstants.heightCamTo12, 2)); 
    //     distToMove = distNow - operatorConstants.distToChain;
    //   } else { //TODO: add without april tag movement
    //     if(mec_subsystem.avgEncoderPosition() <= operatorConstants.inchToRev*distToMove) {
    //       climbSubsystem.climb();
    //     }
    //   }
    // }
    // System.out.println("POV" + pov);
    // if (pov == 270) { // define pov
    //   System.err.println("0");
    //   slideSubsystem.pos0();
    // } else if (pov == 180) {
    //   System.err.println("1");
    //   slideSubsystem.pos1();
    // } else if (pov == 90) {
    //   System.err.println("2");
    //   slideSubsystem.pos2();
    // } else {
    //   slideSubsystem.stop();
    // }
    //  if(SmartDashboard.getNumber("id", -1)!=-1.0 & SmartDashboard.getNumber("id", -1)!=-2.0){
    //   shooterSubsystem.intake();
    //  } else {
    //    shooterSubsystem.stop();
    //  }

    mec_subsystem.drive(-1 * xAxis, yAxis, -1 * zAxis);

    // System.err.println(aprilId);
    // if (leftBumper) {
    //   if(aprilId == 12) {
        
    //   } else {
    //     System.err.println("Dont see 12");
    //   }
    // } else {
    //   mec_subsystem.drive(0,0,0);
    // }
  }

  public void mecToDist(int id, double distToReach) {
    System.err.println(id + ": To reach: " + distToReach + "Current dist: " + aprilDist);
    if(aprilId == id) {
      if(aprilDist-1 > distToReach) {
        mec_subsystem.drive(0, operatorConstants.speedToClimb, 0);
      } else if (aprilDist+1 < distToReach) {
        mec_subsystem.drive(0, -1 * operatorConstants.speedToClimb, 0);
      } else {
        System.err.println("Stopped");
      }
    }
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