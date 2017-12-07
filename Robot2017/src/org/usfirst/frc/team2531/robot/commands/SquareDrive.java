package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SquareDrive extends Command {
	
	private long startTime, interval;
	private double power;
	
	private boolean end;
	
	private int loop;

	public SquareDrive(long interval, double power) {
		requires(Robot.drive);
		
		this.interval = interval;
		this.power = power;
	}
	
	protected void initialize() {
		System.out.println("-> SquareDrive");
	
		startTime = System.currentTimeMillis();
		end = false;
		
		loop = 0;
	}
	
	protected void execute() {
		if (elapsedTime() < interval) {
			Robot.drive.axisdrive(1, power);
		} else if (elapsedTime() < interval * 2) {
			Robot.drive.axisdrive(3, power);
		} else if (elapsedTime() < interval * 3) {
			Robot.drive.axisdrive(0, power);
		} else if (elapsedTime() < interval * 4) {
			Robot.drive.axisdrive(2, power);
		} else {
			startTime = System.currentTimeMillis();
			loop++;
			
			if (loop >= 1) {
				end = true;
			}
		}
	}
	
	protected boolean isFinished() {
		return end;
	}

	protected void end() {
		Robot.drive.stop();
		System.out.println("-! SquareDrive");
	}
	
	protected void interrupted() {
		end();
	}
	
	private long elapsedTime() {
		return System.currentTimeMillis() - startTime;
	}
}
