// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants;

public class SlideSubsystem extends SubsystemBase {
  // MecanumSubsystem mec_subsystem = new MecanumSubsystem();
  OperatorConstants operatorConstants = new OperatorConstants();

  DigitalInput bottomLimitSwitch = new DigitalInput(0);
  DigitalInput firstPosSenseor = new DigitalInput(1);
  DigitalInput secondPosSenseor = new DigitalInput(2);

  private CANSparkMax slideMotor = new CANSparkMax(8, MotorType.kBrushless);
  public RelativeEncoder encoderBottomSlide;

  private boolean fromBottom = true;

  public SlideSubsystem() { 
    slideMotor.restoreFactoryDefaults();
    encoderBottomSlide = slideMotor.getEncoder();
    encoderBottomSlide.setPosition(0);
  }

  public void stop() {
    slideMotor.set(0);
  }

  public void slide(double slideSpeed) {
  }

  public void pos0() {
    System.out.println("Limit Switch 1: " + bottomLimitSwitch.get());
    if (bottomLimitSwitch.get()) {
      System.out.println("BottomP ");
      slideMotor.set(-1 * operatorConstants.slideSpeed);
    } else if (!bottomLimitSwitch.get()){
      stop();
    }
  }

  public void pos1() {
    System.out.println("Limit Switch 3: " + firstPosSenseor.get());   
    if (!bottomLimitSwitch.get()) {
      fromBottom = true;
    } else if (!secondPosSenseor.get()) {
      fromBottom = false;
    }
    if (firstPosSenseor.get() && fromBottom) {
      System.out.println("POS 1 STOP from bottom");
      slideMotor.set(operatorConstants.slideSpeed);
    } else if (firstPosSenseor.get() && !fromBottom) {
      System.out.println("POS 1 STOP not from bottom");
      slideMotor.set(-1 * operatorConstants.slideSpeed);
    } else {
    stop();
    }
  }

  public void pos2() {
    System.out.println("Limit Switch 2: " + secondPosSenseor.get());
    if (secondPosSenseor.get()) {
      System.out.println("POS 2 STOP ");
      slideMotor.set(operatorConstants.slideSpeed);
    } else {
      stop();
    }
  }

  @Override
  public void periodic() {
    // System.err.println(bottomLimitSwitch.get());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}