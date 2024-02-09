package frc.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.Mecanum.TeleopCommand;
import frc.robot.subsystems.MecanumSubsystem;

public class Dashboard {

    private final ShuffleboardTab mainTab;
    private final ShuffleboardTab teleopTab;

    public Dashboard() {
        // Shuffleboard.getTab("LiveWindow").disableRecording();
        mainTab = Shuffleboard.getTab("Main");
        teleopTab = Shuffleboard.getTab("Teleop");
        createMainTab();
        createTeleopTab();
    }

    private void createMainTab() {
        ShuffleboardLayout mainLayout = mainTab.getLayout("Robot Status", BuiltInLayouts.kList)
                .withSize(2, 2)
                .withPosition(0, 0)
                .withProperties(Map.of("Label position", "LEFT"));
        mainLayout.add("Robot Enabled", false);
        mainLayout.add("Robot Mode", "Unknown");

        ShuffleboardLayout controlLayout = mainTab.getLayout("Control", BuiltInLayouts.kList)
                .withSize(2, 2)
                .withPosition(2, 0)
                .withProperties(Map.of("Label position", "LEFT"));
        mainTab.add("Teleop Tab", false).withWidget(BuiltInWidgets.kToggleButton);
    }

    private void createTeleopTab() {
        ShuffleboardLayout teleopLayout = teleopTab.getLayout("Teleop Controls", BuiltInLayouts.kList)
                .withSize(2, 2)
                .withPosition(0, 0)
                .withProperties(Map.of("Label position", "LEFT"));
        teleopLayout.add("Left Bumper", false);
        teleopLayout.add("Right Bumper", false);
        teleopLayout.add("Joystick Y Axis", 0.0);
        teleopLayout.add("Joystick Z Axis", 0.0);
    }

    public void updateMainTab(boolean isEnabled, String mode) {
        mainTab.add("Robot Enabled", isEnabled);
        mainTab.add("Robot Mode", mode);
        // mainTab.getLayout("Robot Status").getTitle("Robot Enabled").setBoolean(isEnabled);
        // mainTab.getLayout("Robot Status").getEntry("Robot Mode").setString(mode);
    }

    public void updateTeleopTab(boolean leftBumper, boolean rightBumper,double xAxis, double yAxis, double zAxis) {
        // teleopTab.add("Left Bumper", leftBumper);
        // teleopTab.add("Right Bumper", rightBumper);
        // teleopTab.add("Joystick X Axis", xAxis);
        // teleopTab.add("Joystick Y Axis", yAxis);
        // teleopTab.add("Joystick Z Axis", zAxis);
        
        // teleopTab.getLayout("Teleop Controls").getEntry("Left Bumper").setBoolean(leftBumper);
        // teleopTab.getLayout("Teleop Controls").getEntry("Right Bumper").setBoolean(rightBumper);
        // teleopTab.getLayout("Teleop Controls").getEntry("Joystick Y Axis").setDouble(yAxis);
        // teleopTab.getLayout("Teleop Controls").getEntry("Joystick Z Axis").setDouble(zAxis);
    }
}
