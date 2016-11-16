package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.RobotPart;
import org.firstinspires.ftc.teamcode.TeamShared;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robovikings on 11/12/2016.
 */

public class DriveToGears extends OpMode {
    public static final String TAG = "RoboVikings 9887";
    public static final float MillimetersPerInch = 25.4f;
    public static final float RobotWidthMillimeters = 18 * MillimetersPerInch;
    public static final float fieldWidthMillimeters = (12 * 12 - 2) * MillimetersPerInch;


    private static final double slowPower = 0.25;
    private static final double fastPower = .3;

    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;
    VuforiaLocalizer vuforia;

    List<VuforiaTrackable> trackableImages = new ArrayList<>();


    @Override
    public void init() {

        leftMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);
        rightMotor = TeamShared.getRobotPart(hardwareMap, RobotPart.lmotor);

        telemetry.addLine("Initializing lmotor and rmotor");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setPower(0);
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setPower(0);

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(com.qualcomm.ftcrobotcontroller.R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = TeamShared.VisionKey;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables trackables = this.vuforia.loadTrackablesFromAsset("FTC_2016-17");

        trackables.get(0).setName("Wheels");
        trackables.get(1).setName("Toools");
        trackables.get(2).setName("Legos");
        trackables.get(3).setName("Gears");

        trackableImages.addAll(trackables);

        AssignTrackableImageLocation(trackables.get(0), -fieldWidthMillimeters / 2, 0, AxesOrder.XZX, 90, 90);
        AssignTrackableImageLocation(trackables.get(1), 600 - fieldWidthMillimeters / 2, 0, AxesOrder.XZX, 90, 90);
        AssignTrackableImageLocation(trackables.get(2), 0, fieldWidthMillimeters / 2, AxesOrder.XZX, 90, 0);
        AssignTrackableImageLocation(trackables.get(2), 0, 60 + fieldWidthMillimeters / 2, AxesOrder.XZX, 90, 0);


        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(RobotWidthMillimeters / 2, 0, 0)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.YZY,
                        AngleUnit.DEGREES, -90, 0, 0));
        RobotLog.ii(TAG, "phone=%s", phoneLocationOnRobot.formatAsTransform());


        for (int i = 0; i < 3; i++) {
            ((VuforiaTrackableDefaultListener) trackables.get(i).getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        }


        trackables.activate();
        telemetry.addLine("init() done.");
        telemetry.update();
    }

    private void AssignTrackableImageLocation(VuforiaTrackable trackable, float translationDx, float translationDy, AxesOrder axesOrder, int firstAngle, int secondAngle) {
        OpenGLMatrix redTargetLocationOnField = OpenGLMatrix
                .translation(translationDx, translationDy, 0)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, axesOrder,
                        AngleUnit.DEGREES, firstAngle, secondAngle, 0));
        trackable.setLocation(redTargetLocationOnField);
        RobotLog.ii(TAG, "%s =%s", trackable.getName(), redTargetLocationOnField.formatAsTransform());
    }


    @Override
    public void loop() {
        for (VuforiaTrackable trackedImage : trackableImages) {

            VuforiaTrackableDefaultListener listener = (VuforiaTrackableDefaultListener) trackedImage.getListener();

            if (listener.isVisible()) {
                telemetry.addLine(String.format("I see %s", trackedImage.getName()));

                OpenGLMatrix pose = listener.getPose();
                if (pose != null) {
                    Orientation orientation = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
                    telemetry.addData("X", orientation.firstAngle);
                    telemetry.addData("Y", orientation.secondAngle);
                    telemetry.addData("Z", orientation.thirdAngle);
                    telemetry.addData("Orientation", orientation.toString());


                    driveToImage(orientation.secondAngle);


                }
                telemetry.addData(trackedImage.getName(), pose == null ? "Unknown location" : pose.formatAsTransform());


            } else {
                telemetry.addData(trackedImage.getName(), "Not seeing");
            }

        }

        telemetry.update();
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
            telemetry.addLine("Streight");
        }
    }
}
