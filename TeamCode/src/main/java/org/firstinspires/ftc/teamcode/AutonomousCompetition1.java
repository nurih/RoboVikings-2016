package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;

/**
 * Created by Robovikings on 11/19/2016.
 */

enum Auto {
    START, SHOOT, CHECK, ELEVATE, SCOOP, LOWER, SHOOT2, BUMP, STOP,
}

@Autonomous(name = "Autonomous Competition 1", group = "Comp")
public class AutonomousCompetition1 extends OpMode {

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
    public TouchSensor wallTouch = null;
    public Servo leftFlipper = null;
    public Servo rightFlipper = null;
    Auto state_s;
    // vision stuff
    int imageIndex = 0;
    // driving stuff
    // alliance stuff
    int allianceIndex = 0;
    Alliance alliance = Alliance.Blue;
    private Servo beaconPusher = null;
    private VisualTargets visualTargets;
    private VuforiaTrackable imageToTrack;
    private double elevatorSpeed;

    @Override
    public void init() {


        leftFlipper = TeamShared.getRobotPart( hardwareMap, RobotPart.lflipperservo );
        leftFlipper.setPosition( flipperStartingPosition );

        rightFlipper = TeamShared.getRobotPart( hardwareMap, RobotPart.rflipperservo );
        rightFlipper.setPosition( flipperFinalPosition );

        wallTouch = TeamShared.getRobotPart( hardwareMap, RobotPart.walltouchsensor );

        beaconPusher = TeamShared.getRobotPart( hardwareMap, RobotPart.beaconservo );
        beaconPusher.setPosition( 0.5 );

        visualTargets = new VisualTargets();
        imageToTrack = visualTargets.getTrackable( imageIndex );


        winderMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.windermotor );
        winderMotor.setDirection( DcMotor.Direction.FORWARD );
        winderMotor.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.FLOAT );
        winderMotor.setPower( noPower );

        telemetry.addLine( "Initializing winder motor" );

        leftMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.lmotor );
        leftMotor.setDirection( DcMotor.Direction.REVERSE );
        leftMotor.setPower( 0 );
        leftMotor.setMode( DcMotor.RunMode.RUN_TO_POSITION );
        leftMotor.setTargetPosition( leftMotor.getCurrentPosition() - 6000 );

        rightMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.rmotor );
        rightMotor.setDirection( DcMotor.Direction.FORWARD );
        rightMotor.setPower( 0 );
        rightMotor.setMode( DcMotor.RunMode.RUN_TO_POSITION );
        rightMotor.setTargetPosition( rightMotor.getCurrentPosition() + 6000 );

        telemetry.addLine( "Initialized lmotor and rmotor" );

        scoopServo = TeamShared.getRobotPart( hardwareMap, RobotPart.scoopservo );
        scoopServo.setPosition( (startingPosition / finalPosition) / 2.0 );

        elevatorMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.elevatormotor );

        elevatorMotor.setDirection( DcMotorSimple.Direction.FORWARD );

        elevatorMotor.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.BRAKE );

        elevatorMotor.setPower( 0 );

        lowerTouchSensor = TeamShared.getRobotPart( hardwareMap, RobotPart.elevatortouchlower );
        upperTouchSensor = TeamShared.getRobotPart( hardwareMap, RobotPart.elevatortouchupper );

        telemetry.addData( "Tracking ", imageToTrack.getName() );
        telemetry.addData( "Alliance ", alliance.name() );

        state_s = Auto.START;
    }


    @Override
    public void init_loop() {
        if (gamepad1.x) {
            imageIndex = imageIndex + 1 % 4;
            imageToTrack = visualTargets.getTrackable( imageIndex );
        }
        if (gamepad1.y) {
            allianceIndex = (allianceIndex + 1) % 2;
            alliance = Alliance.values()[allianceIndex];
        }

        telemetry.addData( "Tracking ", imageToTrack.getName() );
        telemetry.addData( "Alliance ", alliance.name() );
    }


    @Override
    public void loop() {
        elevatorSpeed = 0.5;
        switch (state_s) {
            case START:
                resetStartTime();

                state_s = Auto.SHOOT;
                break;
            case SHOOT:
                if (getRuntime() < WindupTime) {
                    winderMotor.setPower( WindupPower );
                } else {
                    winderMotor.setPower( noPower );
                    state_s = Auto.ELEVATE;
                }
                break;

            case ELEVATE:
                if (!upperTouchSensor.isPressed()) {
                    elevatorMotor.setPower( elevatorSpeed );
                } else {

                    elevatorMotor.setPower( noPower );
                    elevatorMotor.setDirection( DcMotorSimple.Direction.REVERSE );
                    resetStartTime();

                    state_s = Auto.SCOOP;
                }
                break;
            case SCOOP:
                if (getRuntime() < 0.8) {
                    scoopServo.setPosition( finalPosition );
                } else {
                    scoopServo.setPosition( startingPosition );
                    resetStartTime();
                    state_s = Auto.LOWER;
                }
                break;
            case LOWER:
                if (getRuntime() < 1.75 || lowerTouchSensor.isPressed()) {
                    elevatorMotor.setPower( elevatorSpeed );
                } else {
                    elevatorMotor.setPower( noPower );
                    resetStartTime();

                    state_s = Auto.SHOOT2;
                }
                break;
            case SHOOT2:
                if (getRuntime() < WindupTime) {
                    winderMotor.setPower( WindupPower );
                } else {
                    winderMotor.setPower( noPower );
                    resetStartTime();
                    state_s = Auto.BUMP;
                }
                break;
            case BUMP:
                if (getRuntime() > 3 || wallTouch.isPressed()) {
                    state_s = Auto.STOP;
                } else {

                    rightMotor.setPower( 1 );
                    leftMotor.setPower( 1 );
                }
            case STOP:
                winderMotor.setPower( noPower );
                elevatorMotor.setPower( noPower );
                stopDriveMotors();
                break;
            default:
                state_s = Auto.STOP;
                break;
        }
        telemetry.addData( "State ", state_s );
        telemetry.addData( "Time ", getRuntime() );
    }

    public void tryToDrive() {

        Orientation orientation = visualTargets.getOrientation( imageToTrack );
        if (orientation != null) {

            telemetry.addLine( String.format( "\n[X= %d ]\n[Y= %d ]\n[X= %d ]", orientation.firstAngle, orientation.secondAngle, orientation.thirdAngle ) );

            telemetry.addData( "Orientation", orientation.toString() );

            driveToImage( orientation.secondAngle );
        } else {
            telemetry.addData( "Not seeing", imageToTrack.getName() );
            stopDriveMotors();
        }
    }

    private void stopDriveMotors() {
        leftMotor.setPower( 0 );
        rightMotor.setPower( 0 );
    }

    private void driveToImage(float yAxisAngle) {
        final double slowPower = 0.15;
        final double fastPower = .3;
        final double noPower = 0;
        final double angleThreshold = 0.4;

        // stop if hitting the wall
        if (wallTouch.isPressed()) {
            stopDriveMotors();
            state_s = Auto.STOP;
        }

        if (yAxisAngle < -angleThreshold) {
            leftMotor.setPower( slowPower );
            rightMotor.setPower( noPower );
            telemetry.addLine( "Turn right" );
        } else if (yAxisAngle > angleThreshold) {
            leftMotor.setPower( noPower );
            rightMotor.setPower( slowPower );
            telemetry.addLine( "Turn left" );
        } else {
            leftMotor.setPower( fastPower );
            rightMotor.setPower( fastPower );
            telemetry.addLine( "Straight" );
        }
    }

}
