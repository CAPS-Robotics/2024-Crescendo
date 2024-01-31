package frc.robot;

import com.revrobotics.CANSparkMax;

public class Utils {
    driveAssist driveAssist = new driveAssist();

    private CANSparkMax motorLeftFront;
   private CANSparkMax motorLeftBack;
   private CANSparkMax motorRightFront;
   private CANSparkMax motorRightBack; //test

    double strafeSpeed = 1;

    public Utils(CANSparkMax motorLeftFront, CANSparkMax motorLeftBack, CANSparkMax motorRightFront, CANSparkMax motorRightBack) {
        this.motorLeftFront = motorLeftFront;
        this.motorLeftBack = motorLeftBack;
        this.motorRightFront = motorRightFront;
        this.motorRightBack = motorRightBack;
    }
    
    //teleop
    public void strafeRight(CANSparkMax motorLeftFront, CANSparkMax motorLeftBack, CANSparkMax motorRightFront, CANSparkMax motorRightBack) {
        motorLeftFront.set(-strafeSpeed);
        motorRightFront.set(-strafeSpeed);
        motorLeftBack.set(strafeSpeed);
        motorRightBack.set(strafeSpeed);
    }

    public void strafeLeft(CANSparkMax motorLeftFront, CANSparkMax motorLeftBack, CANSparkMax motorRightFront, CANSparkMax motorRightBack) {
        motorLeftFront.set(strafeSpeed);
        motorRightFront.set(strafeSpeed);
        motorLeftBack.set(-strafeSpeed);
        motorRightBack.set(-strafeSpeed);
    }

    public void move(CANSparkMax motorLeftFront, CANSparkMax motorLeftBack, CANSparkMax motorRightFront, CANSparkMax motorRightBack, double ySpeed, double zSpeed) {
        motorLeftFront.set(ySpeed);
        motorRightFront.set(-zSpeed);
        motorLeftBack.set(ySpeed);
        motorRightBack.set(-zSpeed);
    }

    //auton
    public void driveToDistance(CANSparkMax motorLeftFront, CANSparkMax motorLeftBack, CANSparkMax motorRightFront, CANSparkMax motorRightBack, double speed, double distance) {
        while(isFinished(distance)) {
            move(motorLeftFront, motorLeftBack, motorRightFront, motorRightBack, speed, 0);
        }
    }

    public boolean isFinished(double distance) {
        return Math.abs(driveAssist.getAvgDistance() - distance) <= 0.1;
    }
}
