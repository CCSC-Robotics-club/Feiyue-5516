package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * the class that controls the shooter
 * @Author Sam
 * @Version 0.0
 */
public class ShooterSubsystem extends SubsystemBase {
    private TalonSRX shooter;
    private TalonSRX kicker;
    private Timer toggleDisableTimer = new Timer();

    private boolean disabled;
    private boolean shooterActivated;
    private boolean kickerActivated;
    /** creates the shooter subsystem */
    public ShooterSubsystem() {
        shooter = new TalonSRX(Constants.ShooterModuleConstants.shooterPort);
        kicker = new TalonSRX(Constants.ShooterModuleConstants.kickerPort);

        shooter.setNeutralMode(NeutralMode.Coast);
        kicker.setNeutralMode(NeutralMode.Brake);
        this.disabled = false;
        this.shooterActivated = false;
        this.toggleDisableTimer.start();
        SmartDashboard.putNumber("shooting power", (int)(Constants.ShooterModuleConstants.shooterShootPower*100));
    }

    @Override
    public void periodic() {
        SmartDashboard.putString("shooter", disabled ? "disabled" : "enabled");
        if (disabled) {
            this.shooter.set(TalonSRXControlMode.PercentOutput, 0);
            this.kicker.set(TalonSRXControlMode.PercentOutput, 0);
            return;
        }
        if (shooterActivated)
            shooter.set(ControlMode.PercentOutput, SmartDashboard.getNumber("shooting power", (int)(Constants.ShooterModuleConstants.shooterShootPower*100)) / 100);
        else
            shooter.set(TalonSRXControlMode.PercentOutput, Constants.ShooterModuleConstants.shooterStandByPower);
        if (kickerActivated)
            kicker.set(TalonSRXControlMode.PercentOutput, Constants.ShooterModuleConstants.kickerPower);
        else
            kicker.set(TalonSRXControlMode.PercentOutput, 0);
    }

    public void toggleDisable() {
        if (this.toggleDisableTimer.get() < 0.3) // avoid multi-touching
            return;
        this.disabled = !disabled;
        this.toggleDisableTimer.reset();
    }

    public void disable() {
        this.disabled = true;
    }

    public void setShooterActivated() {
        this.shooterActivated = true;
    }

    public void setShooterStandBy() {
        this.shooterActivated = false;
    }

    public void setKickerActivated() {
        if (shooterActivated)
            this.kickerActivated = true;
    }

    public void setKickerStandBy() {
        this.kickerActivated = false;
    }
}

