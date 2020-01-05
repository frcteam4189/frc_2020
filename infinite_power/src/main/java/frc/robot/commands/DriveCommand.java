/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain mDriveTrain;
  private final XboxController mDriveController;
  /**
   * Creates a new DriveCommand.
   */
  public DriveCommand(DriveTrain driveTrain, XboxController driveXboxController) {
    this.mDriveTrain = driveTrain;
    this.mDriveController = driveXboxController;
    addRequirements(this.mDriveTrain);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mDriveTrain.setDefaultCommand(this);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double yAxis = mDriveController.getY(Hand.kLeft);
    double xAxis = mDriveController.getX(Hand.kRight);
    mDriveTrain.openLoopControl(yAxis, xAxis);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
