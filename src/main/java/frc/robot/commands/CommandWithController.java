package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.config.ControllerMap;
import frc.robot.subsystems.ControllerSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;

public class CommandWithController extends Command
{
	private final DrivetrainSubsystem _drivetrain;
	private final ControllerSubsystem _controller;

	public CommandWithController()
	{
		_drivetrain = DrivetrainSubsystem.getInstance();
		_controller = ControllerSubsystem.getInstance();
	}

	@Override
	public void execute()
	{
		double driveSpeed = _controller.getJoystick().getRawAxis(ControllerMap.RIGHT_TRIGGER) -
				_controller.getJoystick().getRawAxis(ControllerMap.LEFT_TRIGGER);
		double driveAngle = _controller.getJoystick().getRawAxis(ControllerMap.LEFT_AXIS_X);

		_drivetrain.arcadeDrive(driveSpeed, driveAngle);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

	@Override
	protected void end()
	{
		_drivetrain.arcadeDrive(0, 0);
	}

	@Override
	protected void interrupted()
	{
		end();
	}
}
