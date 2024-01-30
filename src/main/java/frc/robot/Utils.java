package frc.robot;

import com.revrobotics.CANSparkMax;

public class Utils {
    double speed = 1;
    
    public void strafeRight(CANSparkMax motorLeftFront, CANSparkMax motorLeftBack, CANSparkMax motorRightFront, CANSparkMax motorRightBack) {
        motorLeftFront.set(-speed);
        motorRightFront.set(-speed);
        motorLeftBack.set(speed);
        motorRightBack.set(speed);
    }

    public void strafeLeft(CANSparkMax motorLeftFront, CANSparkMax motorLeftBack, CANSparkMax motorRightFront, CANSparkMax motorRightBack) {
        motorLeftFront.set(speed);
        motorRightFront.set(speed);
        motorLeftBack.set(-speed);
        motorRightBack.set(-speed);
    }
}
