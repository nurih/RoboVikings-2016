package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.RobotPart;
import org.firstinspires.ftc.teamcode.TeamShared;

@Autonomous(name = "BeaconsNoBalls")
public class BeaconPusherAuto extends OpMode {
    private DcMotor rightMotor = null;
    private DcMotor leftMotor = null;
    private Servo beaconPusher = null;
    private TouchSensor wallTouch;
    private OpticalDistanceSensor lineFollower;
    private ColorSensor colorSensor;
    AutoState2 currentState;
    private enum AutoState2 {
        START, DRIVE, FOLLOW, PUSH, STOP
    }
    private final double lightLevel = 0.5;
    @Override
    public void init() {
        leftMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setPower(0);

        rightMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.rmotor);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setPower(0);

        beaconPusher = TeamShared.getRobotPart(hardwareMap, RobotPart.beaconservo);
        beaconPusher.setPosition(0.5);
        telemetry.addLine("Initialized beacon pusher");

        wallTouch = TeamShared.getRobotPart(hardwareMap, RobotPart.walltouchsensor);
        telemetry.addLine("Initialized wall touch sensor");

        colorSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.colorsensor);
        colorSensor.enableLed(false);

        currentState = AutoState2.START;
    }

    @Override
    public void loop() {
        switch(currentState)
        {
            case START:
                resetStartTime();
                currentState = AutoState2.DRIVE;
                break;
            case DRIVE:
                if (getRuntime() < 5 || lineFollower.getLightDetected() <= lightLevel) {
                    leftMotor.setPower(0.3);
                    rightMotor.setPower(0.3);
                } else {
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    resetStartTime();
                    currentState = AutoState2.FOLLOW;
                }
                break;
            case FOLLOW:
                if (wallTouch.isPressed() || getRuntime() >=5) {
                    leftMotor.setPower(0);
                    rightMotor.setPower(0);
                    currentState = AutoState2.PUSH;
                } else {
                    if (lineFollower.getLightDetected() >= lightLevel) {
                        leftMotor.setPower(0.2);
                        rightMotor.setPower(0);
                    } else {
                        leftMotor.setPower(0);
                        rightMotor.setPower(0.2);
                    }
                }
                break;
            case PUSH:
        }

    }
}
