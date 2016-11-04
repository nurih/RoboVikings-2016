package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
enum State{
    INITIALIZE , MOVE , CHECK1 , TURN, CHECK2 , STOP
}
@Autonomous(name = "auto2" , group = "test")
public class Try2 extends OpMode {
    State state_s;
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;
    public void start() {
        state_s = State.INITIALIZE;
    }
    @Override
    public void init() {
        leftMotor   = hardwareMap.dcMotor.get("lmotor");
        rightMotor  = hardwareMap.dcMotor.get("rmotor");
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        telemetry.addLine(String.format("Runtime: " + getRuntime()));
        telemetry.update();
            }
    @Override
    public void loop() {

        switch (state_s) {
            case INITIALIZE:
                state_s = State.MOVE;
                resetStartTime();
                break;
            case MOVE:
                this.DriveRobot(1.0, - 1.0);
                state_s = State.CHECK1;
                break;
            case CHECK1:
                if (getRuntime() >= 5) state_s = State.TURN;
                break;
            case TURN:
                this.TurnRobot(-1.0, -1.0);
                state_s = State.CHECK2;
            case CHECK2:
                if (getRuntime() >= 10) state_s = State.STOP;
            case STOP:
                this.StopRobot();
                break;
            default:
                state_s = State.STOP;
                break;
        }
    }

    private void TurnRobot(double v, double v1) {
        leftMotor.setPower(v);
        rightMotor.setPower(v1);
    }

    private void StopRobot() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }
    private void DriveRobot(double v, double v1) {
        leftMotor.setPower(v);
        rightMotor.setPower(v1);
    }
}
