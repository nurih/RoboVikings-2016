package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous(name = "Autonomous NO CAP BALL BUMP")
public class AutonomousCompetition2NoDrive extends OpMode {

    // winder stuff
    private final int noPower = 0;
    private final double WindupTime = 1;
    private final double WindupPower = 1;
    private final double startingPosition = Servo.MAX_POSITION;
    private final double finalPosition = Servo.MIN_POSITION + 0.25;
    private final double flipperStartingPosition = 0.25;
    private final int flipperFinalPosition = 1;
    public DcMotor elevatorMotor;
    public TouchSensor upperTouchSensor;
    public TouchSensor lowerTouchSensor;
    public Servo scoopServo = null;
    public DcMotor winderMotor = null;
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    public int rightMotorStartPosition;
    public int leftMotorStartPosition;

    public TouchSensor wallTouch = null;
    public Servo leftFlipper = null;
    public Servo rightFlipper = null;
    AutoState currentState;

    // alliance stuff
    int allianceIndex = 0;
    Alliance alliance = Alliance.Blue;
    private Servo beaconPusher = null;


    private double elevatorSpeed;

    @Override
    public void init() {


        leftFlipper = TeamShared.getRobotPart(hardwareMap, RobotPart.lflipperservo);
        leftFlipper.setPosition(flipperFinalPosition);

        rightFlipper = TeamShared.getRobotPart(hardwareMap, RobotPart.rflipperservo);
        rightFlipper.setPosition(flipperStartingPosition);
        telemetry.addLine("Initialized flippers");

        wallTouch = TeamShared.getRobotPart(hardwareMap, RobotPart.walltouchsensor);
        telemetry.addLine("Initialized wall touch sensor");

        beaconPusher = TeamShared.getRobotPart(hardwareMap, RobotPart.beaconservo);
        beaconPusher.setPosition(0.5);
        telemetry.addLine("Initialized beacon pusher");


        winderMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.windermotor);
        winderMotor.setDirection(DcMotor.Direction.FORWARD);
        winderMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        winderMotor.setPower(noPower);

        telemetry.addLine("Initialized winder motor");

        leftMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftMotor.setPower(0);

        rightMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.rmotor);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setPower(0);


        telemetry.addLine("Initialized lmotor and rmotor");

        scoopServo = TeamShared.getRobotPart(hardwareMap, RobotPart.scoopservo);
        scoopServo.setPosition((startingPosition / finalPosition) / 2.0);
        telemetry.addLine("Initialized scoop");

        elevatorMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatormotor);
        elevatorMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        elevatorMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        elevatorMotor.setPower(0);
        lowerTouchSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatortouchlower);
        upperTouchSensor = TeamShared.getRobotPart(hardwareMap, RobotPart.elevatortouchupper);
        telemetry.addLine("Initialized elevator");


        telemetry.addData("Alliance ", alliance.name());

        currentState = AutoState.START;
    }


    @Override
    public void init_loop() {
        if (gamepad1.y) {
            allianceIndex = (allianceIndex + 1) % 2;
            alliance = Alliance.values()[allianceIndex];
        }
        telemetry.addData("Alliance ", alliance.name());
        rightMotorStartPosition = rightMotor.getCurrentPosition();
        leftMotorStartPosition = leftMotor.getCurrentPosition();

        int delta = 20;
        rightMotor.setTargetPosition(rightMotorStartPosition + delta);
        leftMotor.setTargetPosition(leftMotorStartPosition + delta);
    }


    @Override
    public void loop() {
        elevatorSpeed = 0.5;

        switch (currentState) {
            case START:
                resetStartTime();
                currentState = AutoState.SHOOT;
                break;
            case SHOOT:
                if (getRuntime() < WindupTime) {
                    winderMotor.setPower(WindupPower);
                } else {
                    winderMotor.setPower(noPower);
                    currentState = AutoState.ELEVATE;
                }
                break;

            case ELEVATE:
                if (!upperTouchSensor.isPressed()) {
                    elevatorMotor.setPower(elevatorSpeed);
                } else {

                    elevatorMotor.setPower(noPower);
                    elevatorMotor.setDirection(DcMotorSimple.Direction.REVERSE);
                    resetStartTime();

                    currentState = AutoState.SCOOP;
                }
                break;
            case SCOOP:
                if (getRuntime() < 0.8) {
                    scoopServo.setPosition(finalPosition);
                } else {
                    scoopServo.setPosition(startingPosition);
                    resetStartTime();
                    currentState = AutoState.LOWER;
                }
                break;
            case LOWER:
                if (getRuntime() < 1.75 || lowerTouchSensor.isPressed()) {
                    elevatorMotor.setPower(elevatorSpeed);
                } else {
                    elevatorMotor.setPower(noPower);
                    resetStartTime();

                    currentState = AutoState.SHOOT2;
                }
                break;
            case SHOOT2:
                if (getRuntime() < WindupTime) {
                    winderMotor.setPower(WindupPower);
                } else {
                    winderMotor.setPower(noPower);
                    resetStartTime();
                    currentState = AutoState.STOP;
                }
                break;
            case STOP:
                winderMotor.setPower(noPower);
                elevatorMotor.setPower(noPower);
                stopDriveMotors();
                break;
            default:
                currentState = AutoState.STOP;
                break;
        }
        telemetry.addData("State ", currentState);
        telemetry.addData("Time ", getRuntime());

        telemetry.addData("lmotor position", leftMotor.getCurrentPosition());

        telemetry.addData("rmotor position", rightMotor.getCurrentPosition());
    }


    private void stopDriveMotors() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

}
