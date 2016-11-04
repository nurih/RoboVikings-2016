package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Jennifer on 10/29/2016.
 */
@Autonomous(name = "linefollow" , group = "test")
public class line_follow extends OpMode{
    private final int noPower = 0;
    private final double lowPower = 0.2;
    private final double lightLevel = 0.5;
    public DcMotor rightMotor = null;
    public DcMotor leftMotor = null;
    public OpticalDistanceSensor lineFollower;
    public TouchSensor bumper;

    @Override
    public void init() {
        rightMotor = hardwareMap.dcMotor.get("rmotor");
        leftMotor = hardwareMap.dcMotor.get("lmotor");
        bumper = hardwareMap.touchSensor.get("bumper");
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setPower(noPower);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setPower(noPower);
    }

    @Override
    public void loop() {
        telemetry.addLine(String.format("Left Motor Power: ", leftMotor.getPower()));
        telemetry.addLine(String.format("Right Motor Power: ", rightMotor.getPower()));
        telemetry.addLine(String.format("Line Follower Light Level: ", lineFollower.getLightDetected()));
        telemetry.update();

        if (bumper.isPressed()) {
            leftMotor.setPower(noPower);
            rightMotor.setPower(noPower);
        } else {
            if (lineFollower.getLightDetected() >= lightLevel) {
                leftMotor.setPower(lowPower);
                rightMotor.setPower(noPower);
            } else
            {
                leftMotor.setPower(noPower);
                rightMotor.setPower(lowPower);
            }
        }
    }
}
