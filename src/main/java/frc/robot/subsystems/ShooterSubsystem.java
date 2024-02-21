// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.OperatorConstants;

//3 motors 1 neo 2 talon

public class ShooterSubsystem extends SubsystemBase {
    /** Creates a new ExampleSubsystem. */

    MecanumSubsystem mec_subsystem = new MecanumSubsystem();

    DigitalInput topLimitSwitch = new DigitalInput(0);
    DigitalInput bottomLimitSwitch = new DigitalInput(1);

    private CANSparkMax frontRollers = new CANSparkMax(12, MotorType.kBrushless);
    private CANSparkMax backRollers = new CANSparkMax(11, MotorType.kBrushless);

    // CANSparkMax
    public ShooterSubsystem() {
        frontRollers.restoreFactoryDefaults();
        backRollers.restoreFactoryDefaults();
    }

    public void intake() {
        frontRollers.setInverted(true);
        backRollers.setInverted(true);

        frontRollers.set(OperatorConstants.intakeSpeed);
        backRollers.set(OperatorConstants.intakeSpeed);
    }

    public void shootAmp() {
        frontRollers.setInverted(false);
        backRollers.setInverted(false);

        frontRollers.set(OperatorConstants.shootAmpSpeed);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        backRollers.set(OperatorConstants.backRollersShootSpeed);
    }

    public void shootSpeaker() {
        frontRollers.setInverted(false);
        backRollers.setInverted(false);

        frontRollers.set(OperatorConstants.shootSpeakerSpeed);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        backRollers.set(OperatorConstants.backRollersShootSpeed);
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
