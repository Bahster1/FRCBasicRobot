package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.config.RobotMap;

public class DrivetrainSubsystem extends Subsystem
{
    private static DrivetrainSubsystem _instance;
    private final WPI_TalonSRX _leftMaster, _rightMaster;
    private final DifferentialDrive _wheels;

    private DrivetrainSubsystem()
    {
        super("DrivetrainSubsystem");

        WPI_TalonSRX leftSlave = new WPI_TalonSRX(RobotMap.LEFT_SLAVE_ID);
        WPI_TalonSRX rightSlave = new WPI_TalonSRX(RobotMap.RIGHT_SLAVE_ID);
        _leftMaster = new WPI_TalonSRX(RobotMap.RIGHT_MASTER_ID);
        _rightMaster = new WPI_TalonSRX(RobotMap.LEFT_MASTER_ID);

        SpeedControllerGroup leftGroup = new SpeedControllerGroup(_leftMaster, leftSlave);
        SpeedControllerGroup rightGroup = new SpeedControllerGroup(_rightMaster, rightSlave);

        _wheels = new DifferentialDrive(leftGroup, rightGroup);
        _wheels.setSafetyEnabled(false);
    }

    public void arcadeDrive(double moveVale, double rotateValue)
    {
        _wheels.arcadeDrive(moveVale, rotateValue);
    }

    public static DrivetrainSubsystem getInstance()
    {
        if (_instance == null)
            _instance = new DrivetrainSubsystem();

        return _instance;
    }

    @Override
    protected void initDefaultCommand()
    {

    }
}
