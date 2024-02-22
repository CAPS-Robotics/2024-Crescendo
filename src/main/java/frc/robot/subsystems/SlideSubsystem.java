// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//2 motor 2 neo

public class SlideSubsystem extends SubsystemBase {
  MecanumSubsystem mec_subsystem = new MecanumSubsystem();
  DigitalInput topLimitSwitch = new DigitalInput(0);
  DigitalInput bottomLimitSwitch = new DigitalInput(1);

  private CANSparkMax topSlide = new CANSparkMax(9, MotorType.kBrushless);
  private CANSparkMax bottomSlide = new CANSparkMax(10, MotorType.kBrushless);

  public RelativeEncoder encoderTopSlide;
  public RelativeEncoder encoderBottomSlide;

  /** Creates a new ExampleSubsystem. */
  public SlideSubsystem() {
    topSlide.restoreFactoryDefaults();
    bottomSlide.restoreFactoryDefaults();

    encoderTopSlide = topSlide.getEncoder();
    encoderBottomSlide = bottomSlide.getEncoder();

    encoderTopSlide.setPosition(0);
    encoderBottomSlide.setPosition(0);
  }

  public void stop() {
    topSlide.set(0);
    bottomSlide.set(0);
  }

  public void slide(double slideSpeed) {
    if (slideSpeed > 0) {
      System.err.println("Move up" + slideSpeed);
      topSlide.set(slideSpeed);
      bottomSlide.set(slideSpeed);
      if (!topLimitSwitch.get()) {
        stop();
      }
    } else if (slideSpeed < 0) {
      System.err.println("Move down" + slideSpeed);
      topSlide.set(slideSpeed);
      bottomSlide.set(slideSpeed);
      if (!bottomLimitSwitch.get()) {
        stop();
      }
    } else {
      stop();
    }
  }

  public void slideToPosition(double slideSpeed, double position) {
    if (slideSpeed > 0) {
      if (position <= encoderTopSlide.getPosition() || position <= encoderBottomSlide.getPosition()) {
        stop();

      } else {
        System.err.println(encoderBottomSlide.getPosition());
        slide(slideSpeed);
      }
    } else if (slideSpeed < 0) {
      if (position >= encoderTopSlide.getPosition() || position >= encoderBottomSlide.getPosition()) {
        stop();

      } else {
        System.err.println(encoderBottomSlide.getPosition());
        slide(slideSpeed);
      }
    }
  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}