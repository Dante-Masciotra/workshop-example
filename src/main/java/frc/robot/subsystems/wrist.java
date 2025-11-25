// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIDs;;

public class wrist extends SubsystemBase {
  private final SparkMax wristMotor;
  private final RelativeEncoder encoder;

  public wrist() {
      wristMotor = new SparkMax(MotorIDs.mWrist, MotorType.kBrushless);

      // Simple config object
      SparkMaxConfig config = new SparkMaxConfig();
      config.inverted(false);
      config.idleMode(IdleMode.kBrake);
      config.smartCurrentLimit(40);

      // Apply config
      wristMotor.configure(config, null, null);

      // Get the NEO integrated encoder
      encoder = wristMotor.getEncoder();
  }

  /** Basic power control (-1 to 1). */
  public void setPower(double power) {
      wristMotor.set(power);
  }

  /** Stop the motor. */
  public void stop() {
      wristMotor.set(0);
  }

  /** Get raw encoder position in motor rotations. */
  public double getPosition() {
      return encoder.getPosition();
  }

  @Override
  public void periodic() {
  }
}