package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

public class HolonomicDriveSubsystem {
    private Pose2d m_poseError = new Pose2d();
    private Rotation2d m_rotationError = new Rotation2d();
    private Pose2d m_poseTolerance = new Pose2d();
    private boolean m_enabled = true;
    private double theta; //Degrees

    private final PIDController m_xController;
    private final PIDController m_yController;
    private final ProfiledPIDController m_thetaController;

    public HolonomicDriveSubsystem(PIDController xController, PIDController yController, ProfiledPIDController thetaController) {
        m_xController = xController;
        m_yController = yController;
        m_thetaController = thetaController;
        m_thetaController.enableContinuousInput(0, (theta * Math.PI)/180);
  }

}
