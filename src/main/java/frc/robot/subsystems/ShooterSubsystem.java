// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.OperatorConstants;

public class ShooterSubsystem extends SubsystemBase {

    // MecanumSubsystem mec_subsystem = new MecanumSubsystem();
    OperatorConstants operatorConstants = new OperatorConstants();

    DigitalInput topLimitSwitch = new DigitalInput(4);

    private CANSparkMax frontRollers = new CANSparkMax(12, MotorType.kBrushless);
    private CANSparkMax backRollers = new CANSparkMax(11, MotorType.kBrushless);
    
    private SparkPIDController frontPidController = frontRollers.getPIDController();
    private SparkPIDController backPidController = backRollers.getPIDController();

    private RelativeEncoder frontRollersEncoder = frontRollers.getEncoder();

    // CANSparkMax
    public ShooterSubsystem() {
        frontRollers.restoreFactoryDefaults();
        backRollers.restoreFactoryDefaults();

        frontPidController.setP(operatorConstants.kP);
        frontPidController.setI(operatorConstants.kI);
        frontPidController.setD(operatorConstants.kD);
        frontPidController.setOutputRange(-1, 1);

        backPidController.setP(operatorConstants.kP);
        backPidController.setI(operatorConstants.kI);
        backPidController.setD(operatorConstants.kD);
        backPidController.setOutputRange(-1, 1);
    }

    public void stop() {
        frontRollers.set(0);
        backRollers.set(0);
    }

    public void intake() {
        frontRollers.setInverted(true);
        backRollers.setInverted(true);

        frontPidController.setReference(operatorConstants.intakeSpeed, CANSparkMax.ControlType.kVelocity);
        backPidController.setReference(operatorConstants.intakeSpeed, CANSparkMax.ControlType.kVelocity);
    }

    public void shootAmp() {
        frontRollers.setInverted(false);
        backRollers.setInverted(false);

        frontPidController.setReference(operatorConstants.shootTrapSpeed, CANSparkMax.ControlType.kVelocity);
        System.err.println(frontRollersEncoder.getVelocity());
        if (frontRollersEncoder.getVelocity() <= operatorConstants.shootTrapSpeed) {
            backPidController.setReference(operatorConstants.backRollersShootSpeed, CANSparkMax.ControlType.kVelocity);
        }
    }

    public void shootTrap() {
        frontRollers.setInverted(false);
        backRollers.setInverted(false);

        frontPidController.setReference(operatorConstants.shootTrapSpeed, CANSparkMax.ControlType.kVelocity);
        System.err.println(frontRollersEncoder.getVelocity());
        if (frontRollersEncoder.getVelocity() <= operatorConstants.shootTrapSpeed) {
            backPidController.setReference(operatorConstants.backRollersShootSpeed, CANSparkMax.ControlType.kVelocity);
        }
    }

    @Override
    public void periodic() { // make no errors
        // if(intake == true && !noteDetector.get()) {
        //     intake();
        // } else if (noteDetector.get()) {
        //     intake = false;
        // }
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }
}