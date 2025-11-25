package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorIDs;

public class elbow extends SubsystemBase {
  private final SparkMax elbowMotor;
  private final RelativeEncoder encoder;
  private final SparkClosedLoopController elbowController;


  // ---- Change this to your real gear ratio ----
  private static final double gearRatio = 1.0;  
  private static final double degreesPerMotorRot = 360.0 / gearRatio;

  public elbow() {
      elbowMotor = new SparkMax(MotorIDs.mElbow, MotorType.kBrushless);

      SparkMaxConfig config = new SparkMaxConfig();
      config.inverted(false);
      config.idleMode(IdleMode.kBrake);
      config.smartCurrentLimit(40);
      config.closedLoop
            .p(1.0)
            .i(0.0)
            .d(0.1);

      elbowMotor.configure(config, null, null);

      encoder = elbowMotor.getEncoder();
      elbowController = elbowMotor.getClosedLoopController();
  }

  /** Convert motor rotations → elbow angle (degrees). */
  public double getAngle() {
      return encoder.getPosition() * degreesPerMotorRot;
  }

  /** Get encoder rotations, if needed. */
  public double getPosition() {
      return encoder.getPosition();
  }

  /** Set a new desired angle. */
  public void setTargetAngle(double angleDeg) {
      double position = angleDeg/degreesPerMotorRot;
      elbowController.setReference(position, ControlType.kPosition);
  }

  /** Manually control power (-1 to 1). */
  public void setPower(double power) {
      elbowMotor.set(power);
  }

  /** Stop motor. */
  public void stop() {
      elbowMotor.set(0);
  }



  @Override
  public void periodic() {
  }
}
