/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
/**
 * Add your docs here.
 */
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private final WPI_TalonSRX mLeftDriveMaster;
  private final WPI_TalonSRX mRightDriveMaster;
  private final WPI_TalonSRX mLeftDriveSlave;
  private final WPI_TalonSRX mRightDriveslave;
  private final DifferentialDrive mDifferentialDrive;
  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrain() {
    mLeftDriveMaster = new WPI_TalonSRX(Constants.kLeftDriveMasterID);
    mLeftDriveSlave = new WPI_TalonSRX(Constants.kLeftDriveSlaveID);
    mRightDriveMaster = new WPI_TalonSRX(Constants.kRightDriveMasterID);
    mRightDriveslave = new WPI_TalonSRX(Constants.kRightDriveSlaveID);
    initTalonSRX(mLeftDriveMaster);
    initTalonSRX(mRightDriveMaster);
    mLeftDriveSlave.follow(mLeftDriveMaster);
    mRightDriveslave.follow(mRightDriveMaster);

    mDifferentialDrive = new DifferentialDrive(mLeftDriveMaster, mRightDriveMaster);
    mDifferentialDrive.setDeadband(0.1);
    
  }
  public static void initTalonSRX(WPI_TalonSRX talonSRX){
    talonSRX.configFactoryDefault();
    talonSRX.setNeutralMode(NeutralMode.Brake);
    talonSRX.neutralOutput();
    talonSRX.setSensorPhase(false);
    talonSRX.configNominalOutputForward(0.0);
    talonSRX.configNominalOutputReverse(0.0);
    talonSRX.configClosedloopRamp(3);
  }

  public void openLoopControl(double move, double turn){
    mDifferentialDrive.arcadeDrive(move, turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
