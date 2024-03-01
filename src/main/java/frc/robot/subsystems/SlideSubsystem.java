// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.fasterxml.jackson.databind.util.PrimitiveArrayBuilder;
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


  private CANSparkMax slideMotor = new CANSparkMax(12, MotorType.kBrushless);
 


  public RelativeEncoder encoderTopSlide;
  public RelativeEncoder encoderBottomSlide;

  int slidePosition;

  public SlideSubsystem() {
    slideMotor.restoreFactoryDefaults();

    encoderBottomSlide = slideMotor.getEncoder();

    encoderBottomSlide.setPosition(0);
  }

  public void stop() {
    slideMotor.set(0);
  }

  public void slide(double slideSpeed, int counter) {
    System.out.println("Limit Switch 1: "+bottomLimitSwitch.get());
    System.out.println("Limit Switch 2: "+secondPosSenseor.get());
    System.out.println("Limit Switch 3: "+firstPosSenseor.get());



    if (counter == 1 && !firstPosSenseor.get())
    {

      System.out.println("POS 1 STOP ");
      stop();

      }
      else if (counter == 2 && !secondPosSenseor.get())
      {
        System.out.println("POS 2 STOP ");
        stop();
      }else if (counter == 0 && !bottomLimitSwitch.get())
      {
        System.out.println("BottomP ");
        stop();

      }else
      {
       slideMotor.set(slideSpeed);
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
