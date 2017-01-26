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
	private int ontargets = 0;
	private int ontarget = 10;
	private double P = 0;
	private double I = 0;
	private double D = 0;
	private double lasterror = 0;
	private long looptime = 100;
	private long lastcompute = 0;

	public PID(double p, double i, double d, double setpoint) {
		this.kp = p;
		this.ki = i;
		this.kd = d;
		this.setpoint = setpoint;
	}

	public void setOnTargetCount(int count) {
		ontarget = count;
	}

	public void setLoopTime(long milis) {
		looptime = milis;
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
			ontargets += 1;
		} else {
			ontargets = 0;
		}
		return ontargets > ontarget;
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

	public double compute2(double in) {
		if (System.currentTimeMillis() > lastcompute) {
			this.input = in;
			this.error = this.setpoint - this.input;
			P = kp * error;
			I = ki * (I + (error * looptime));
			D = kd * ((error - lasterror) / looptime);
			output = P + I + D;
			lasterror = error;
			lastcompute = System.currentTimeMillis() + looptime;
		}
		if (this.output > this.outMax) {
			this.output = this.outMax;
		} else if (this.output < this.outMin) {
			this.output = this.outMin;
		}
		return this.output;
	}
}
