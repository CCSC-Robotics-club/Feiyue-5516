package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

import java.util.HashMap;
import java.util.Map;

public class AutoFactory {
    private enum AutoMode {
        LEAVE_COMMUNITY,
        LEAVE_COMMUNITY_AND_SHOOT
    }

    private AutoMode defaultMode = AutoMode.LEAVE_COMMUNITY;

    private final DriveSubsystem driveSubsystem;
    private final SendableChooser<AutoMode> modeChooser;

    private final Map<AutoMode, Command> autoModesCommandMap = new HashMap<>();

    public AutoFactory(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.modeChooser = new SendableChooser<>();
        this.modeChooser.setDefaultOption("leave-community(default)", defaultMode);

        autoModesCommandMap.put(AutoMode.LEAVE_COMMUNITY, null);
        autoModesCommandMap.put(AutoMode.LEAVE_COMMUNITY_AND_SHOOT, null);

        for (AutoMode autoMode: AutoMode.values()) {
            if (autoMode == defaultMode)
                modeChooser.setDefaultOption(autoMode.name(), autoMode);
            else
                modeChooser.addOption(autoMode.name(), autoMode);
        }
        SmartDashboard.putData("auto mode", modeChooser);
    }

    public Command getAutoCommand() {
        return autoModesCommandMap.get(modeChooser.getSelected());
    }
}
