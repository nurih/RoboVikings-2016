package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;

/**
 * Created by Robovikings on 11/12/2016.
 */
@TeleOp(name = "Drive To Gears", group = "Tests")
public class DriveToGears extends OpMode {


    private static final double slowPower = 0.25;
    private static final double fastPower = .3;

    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;


    VuforiaTrackable imageToDriveTo;

    @Override
    public void init() {

        leftMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);
        rightMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);

        telemetry.addLine("Initializing lmotor and rmotor");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setPower(0);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setPower(0);

        telemetry.addLine("init() done.");
        telemetry.update();

        imageToDriveTo = TeamVision.getGearsTrackable();
    }


    @Override
    public void loop() {

        VuforiaTrackableDefaultListener listener = (VuforiaTrackableDefaultListener) imageToDriveTo.getListener();

        if (listener.isVisible()) {
            telemetry.addLine(String.format("I see %s", imageToDriveTo.getName()));

            OpenGLMatrix pose = listener.getPose();
            if (pose != null) {
                Orientation orientation = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                telemetry.addLine(String.format("[X=%s][Y=%s][X=%s]", orientation.firstAngle, orientation.secondAngle, orientation.thirdAngle));
                telemetry.addData("Orientation", orientation.toString());
                telemetry.addData(imageToDriveTo.getName(), pose.formatAsTransform());

                driveToImage(orientation.secondAngle);

            } else {
                telemetry.addData("Unknown position", imageToDriveTo.getName());
            }
        } else {
            telemetry.addData("Not seeing", imageToDriveTo.getName());
        }
    }

    private void driveToImage(float yAxisAngle) {
        if (yAxisAngle > 0) {
            leftMotor.setPower(slowPower);
            rightMotor.setPower(0);
            telemetry.addLine("Turn right");
        } else if (yAxisAngle < 0) {
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

