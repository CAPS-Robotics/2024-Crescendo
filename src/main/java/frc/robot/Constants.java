// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    // Auto
    public static final double gearRatio = 12.571471214294434 / 4096;
    public static final double inchToRev = 1/(6*Math.PI);
    public static final double ftToRev = 2/Math.PI;

    // Shooter
    public static final double intakeSpeed = .25; //tmp
    public static final double shootTrapSpeed = .75; //2808 rpm
    public static final double shootAmpSpeed = .5; //tmp
    public static final double backRollersShootSpeed = .35; //tmp

    public static final double kP = .0002;
    public static final double kI = 0;
    public static final double kD = 0;    

    // Slide
    public static final double slideSpeed = .1;
    public static final double slidePosition2 = 0.8;

    //Endgame 
    public static final double heightCamTo12 = 48 - 15;
    public static final double distToChain = 20;

    // Climb
    public static final double climbSpeed = .5;
    public static final double maxClimbExtension = 270/360;

    // Speeds
    public static final double speedToClimb = .5;
  }

  public static class Distances {
    public static final double climbDist = 50; //in.
  }
}