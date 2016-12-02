package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;

/**
 * Created by Robovikings on 11/12/2016.
 */
@TeleOp(name = "Drive To Gears", group = "Tests")
public class DriveToGears extends OpMode {


    private static final double slowPower = 0.15;
    private static final double fastPower = .3;

    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    VuforiaTrackable imageToDriveTo;

    @Override
    public void init() {

        leftMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);
        rightMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.rmotor);

        telemetry.addLine("Initializing lmotor and rmotor");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setPower(0);

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setPower(0);

        telemetry.addLine("init() done.");

        imageToDriveTo = TeamVision.getGearsTrackable();
    }


    @Override
    public void loop() {

        Orientation orientation = TeamVision.getOrientation(imageToDriveTo);
        if (orientation != null) {

            telemetry.addLine(String.format("\n[X= %d ]\n[Y= %d ]\n[X= %d ]", orientation.firstAngle, orientation.secondAngle, orientation.thirdAngle));

            telemetry.addData("Orientation", orientation.toString());

            driveToImage(orientation.secondAngle);
        }

        else {
            telemetry.addData("Not seeing", imageToDriveTo.getName());
            stopMotors();
        }
    }

    private void stopMotors() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    private void driveToImage(float yAxisAngle) {
        if (yAxisAngle < -0.4) {
            leftMotor.setPower(slowPower);
            rightMotor.setPower(0);
            telemetry.addLine("Turn right");
        } else if (yAxisAngle > .4) {
            leftMotor.setPower(0);
            rightMotor.setPower(slowPower);
            telemetry.addLine("Turn left");
        } else {
            leftMotor.setPower(fastPower);
            rightMotor.setPower(fastPower);
            telemetry.addLine("Straight");
        }
    }
}

