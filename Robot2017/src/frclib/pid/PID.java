package frclib.pid;

public class PID {
	private double kp = 0.0;
	private double ki = 0.0;
	private double kd = 0.0;
	private double setpoint = 0.0;
	private double lastInput = 0.0;
	private double input = 0.0;
	private double output = 0.0;
	private double error = 0.0;
	private double inputChange = 0.0;
	private double outMax = 0.0;
	private double outMin = 0.0;
	private double Iop = 0.0;
	private double offset = 0.0;
	private int c = 0;

	public PID(double p, double i, double d, double setpoint) {
		this.kp = p;
		this.ki = i;
		this.kd = d;
		this.setpoint = setpoint;
	}

	public void setTunings(double p, double i, double d) {
		this.kp = p;
		this.ki = i;
		this.kd = d;
	}

	public void setSetpoint(double value) {
		this.setpoint = value;
	}

	public void setOutputLimits(double min, double max) {
		this.outMax = max;
		this.outMin = min;
	}

	public void setOnTargetOffset(double value) {
		this.offset = value;
	}

	public boolean onTarget() {
		if (this.setpoint + this.offset > this.input && this.setpoint - this.offset < this.input) {
			c += 1;
		} else {
			c = 0;
		}
		return c > 10;
	}

	public double compute(double in) {
		this.input = in;
		this.error = this.setpoint - this.input;
		this.inputChange = this.input - this.lastInput;
		this.Iop = this.ki * this.error;
		if (this.Iop > this.outMax) {
			this.Iop = this.outMax;
		}
		this.output = this.kp * this.error + this.Iop - this.kd * this.inputChange;
		this.lastInput = this.input;
		if (this.output > this.outMax) {
			this.output = this.outMax;
		} else if (this.output < this.outMin) {
			this.output = this.outMin;
		}
		return this.output;
	}
}
