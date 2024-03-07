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

    DigitalInput topLimitSwitch = new DigitalInput(3);

    private CANSparkMax shooterRollers = new CANSparkMax(6, MotorType.kBrushless); //front
    private CANSparkMax shooterWheel = new CANSparkMax(7, MotorType.kBrushless); //back
    
    private SparkPIDController frontPidController = shooterRollers.getPIDController();
    private SparkPIDController backPidController = shooterWheel.getPIDController();

    private RelativeEncoder frontRollersEncoder = shooterRollers.getEncoder();

    // CANSparkMax
    public ShooterSubsystem() {
        shooterRollers.restoreFactoryDefaults();
        shooterWheel.restoreFactoryDefaults();

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
        shooterRollers.set(0);
        shooterWheel.set(0);
    }

    public void intake() {
        System.err.println("in shoot intake");
        shooterRollers.setInverted(true);
        shooterWheel.setInverted(true);

        frontPidController.setReference(operatorConstants.intakeSpeed, CANSparkMax.ControlType.kVelocity);
        backPidController.setReference(operatorConstants.intakeSpeed, CANSparkMax.ControlType.kVelocity);
    }

    public void shootAmp() {
        System.err.println("in shoot Amp");
        shooterRollers.setInverted(false);
        shooterWheel.setInverted(false);

        frontPidController.setReference(operatorConstants.shootTrapSpeed, CANSparkMax.ControlType.kVelocity);
        System.err.println(frontRollersEncoder.getVelocity());
        if (frontRollersEncoder.getVelocity() <= operatorConstants.shootTrapSpeed) {
            backPidController.setReference(operatorConstants.backRollersShootSpeed, CANSparkMax.ControlType.kVelocity);
        }
    }

    public void shootTrap() {
        System.err.println("in shoot Trap");
        shooterRollers.setInverted(false);
        shooterWheel.setInverted(false);

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