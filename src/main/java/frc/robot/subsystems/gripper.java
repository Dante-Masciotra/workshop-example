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

public class gripper extends SubsystemBase {
  private final SparkMax gripperMotor;
  private final RelativeEncoder encoder;

  public gripper() {
      gripperMotor = new SparkMax(MotorIDs.mGripper, MotorType.kBrushless);

      // Simple config object
      SparkMaxConfig config = new SparkMaxConfig();
      config.inverted(false);
      config.idleMode(IdleMode.kBrake);
      config.smartCurrentLimit(40);

      // Apply config
      gripperMotor.configure(config, null, null);

      // Get the NEO integrated encoder
      encoder = gripperMotor.getEncoder();
  }

  /** Basic power control (-1 to 1). */
  public void setPower(double power) {
      gripperMotor.set(power);
  }

  /** Stop the motor. */
  public void stop() {
      gripperMotor.set(0);
  }

  /** Get raw encoder position in motor rotations. */
  public double getPosition() {
      return encoder.getPosition();
  }

  @Override
  public void periodic() {
  }
}