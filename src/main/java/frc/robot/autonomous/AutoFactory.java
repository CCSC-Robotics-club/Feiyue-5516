package frc.robot.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

import java.util.HashMap;
import java.util.Map;

public class AutoFactory {
    private enum AutoModes {
        LEAVE_COMMUNITY,
        LEAVE_COMMUNITY_AND_SHOOT
    }

    private final DriveSubsystem driveSubsystem;
    private final SendableChooser<AutoModes> modeChooser;

    private final Map<AutoModes, Command> autoModesCommandMap = new HashMap<>();

    public AutoFactory(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        this.modeChooser = new SendableChooser<>();
        this.modeChooser.setDefaultOption("leave-community", AutoModes.LEAVE_COMMUNITY);
        for (AutoModes autoMode: AutoModes.values())
            this.autoModesCommandMap.put(autoMode, )
        
    }
}
