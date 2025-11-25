// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elbow;
import frc.robot.subsystems.gripper;
import frc.robot.subsystems.shoulder;
import frc.robot.subsystems.wrist;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class grabPosition extends Command {
  private final elbow m_Elbow;  
  private final wrist m_Wrist;  
  private final shoulder m_Shoulder;  
  private final gripper m_Gripper;  
  /** Creates a new grabPosition. */
  public grabPosition(elbow elbow, shoulder shoulder, gripper gripper, wrist wrist) {
    m_Elbow = elbow;
    m_Wrist = wrist;
    m_Shoulder = shoulder;
    m_Gripper = gripper;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(elbow,shoulder,gripper,wrist);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Elbow.setTargetAngle(45);
    m_Shoulder.setTargetAngle(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Wrist.setPower(0.5);
    m_Gripper.setPower(-0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
