/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private final DigitalInput mLeftEncoderDigitalInputA;
  private final DigitalInput mLeftEncoderDigitalInputB;
  private final DigitalInput mRightEncoderDigitalInputA;
  private final DigitalInput mRightEncoderDigitalInputB;
  private final Encoder mLeftEncoder;
  private final Encoder mRightEncoder;
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

    mLeftEncoderDigitalInputA = new DigitalInput(Constants.kLeftEncoderChannelA);
    mLeftEncoderDigitalInputB = new DigitalInput(Constants.kLeftEncoderChannelB);
    mRightEncoderDigitalInputA = new DigitalInput(Constants.kRightEncoderChannelA);
    mRightEncoderDigitalInputB = new DigitalInput(Constants.kRightEncoderChannelB);
    mLeftEncoder = new Encoder(mLeftEncoderDigitalInputA, mLeftEncoderDigitalInputB, false, EncodingType.k4X);
    mRightEncoder = new Encoder(mRightEncoderDigitalInputA, mRightEncoderDigitalInputB, false, EncodingType.k4X);  
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

  public void outputTelemetry(){
    // SmartDashboard.putNumber("Left Encoder Count", mLeftEncoder.getDistance());
    // SmartDashboard.putNumber("Right Encoder Count", mRightEncoder.getDistance());
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
