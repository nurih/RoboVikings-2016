package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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

import java.util.ArrayList;
import java.util.List;


@Autonomous(name = "Hello Picture Driven", group = "Demo")
public class HelloPictureDemo extends OpMode {

    public static final String TAG = "Robo Vikings 9887";

    OpenGLMatrix lastLocation = null;


    VuforiaLocalizer vuforia;
    /**
     * A list to hold the trackable images posted on the walls
     */
    List<VuforiaTrackable> allTrackables = new ArrayList<>();

    @Override
    public void init() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(com.qualcomm.ftcrobotcontroller.R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = TeamShared.VisionKey;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables trackables = this.vuforia.loadTrackablesFromAsset("FTC_2016-17");

        trackables.get(0).setName("Wheels");
        trackables.get(1).setName("Toools");
        trackables.get(2).setName("Legos");
        trackables.get(3).setName("Gears");

        allTrackables.addAll(trackables);

        float mmPerInch = 25.4f;
        float mmBotWidth = 18 * mmPerInch;            // ... or whatever is right for your robot
        float fieldWidthMM = (12 * 12 - 2) * mmPerInch;   // the FTC field is ~11'10" center-to-center of the glass panels


        AssignTrackableImageLocation(trackables.get(0), -fieldWidthMM / 2, 0, AxesOrder.XZX, 90, 90);
        AssignTrackableImageLocation(trackables.get(1), 600 - fieldWidthMM / 2, 0, AxesOrder.XZX, 90, 90);
        AssignTrackableImageLocation(trackables.get(2), 0, fieldWidthMM / 2, AxesOrder.XZX, 90, 0);
        AssignTrackableImageLocation(trackables.get(2), 0, 60 + fieldWidthMM / 2, AxesOrder.XZX, 90, 0);


        /**
         * Create a transformation matrix describing where the phone is on the robot. Here, we
         * put the phone on the right hand side of the robot with the screen facing in (see our
         * choice of BACK camera above) and in landscape mode. Starting from alignment between the
         * robot's and phone's axes, this is a rotation of -90deg along the Y axis.
         *
         * When determining whether a rotation is positive or negative, consider yourself as looking
         * down the (positive) axis of rotation from the positive towards the origin. Positive rotations
         * are then CCW, and negative rotations CW. An example: consider looking down the positive Z
         * axis towards the origin. A positive rotation about Z (ie: a rotation parallel to the the X-Y
         * plane) is then CCW, as one would normally expect from the usual classic 2D geometry.
         */
        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation(mmBotWidth / 2, 0, 0)
                .multiplied(Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.YZY,
                        AngleUnit.DEGREES, -90, 0, 0));
        RobotLog.ii(TAG, "phone=%s", phoneLocationOnRobot.formatAsTransform());

        /**
         * Let the trackable listeners we care about know where the phone is. We know that each
         * listener is a {@link VuforiaTrackableDefaultListener} and can so safely cast because
         * we have not ourselves installed a listener of a different type.
         */
        for (int i = 0; i < 3; i++) {
            ((VuforiaTrackableDefaultListener) trackables.get(i).getListener()).setPhoneInformation(phoneLocationOnRobot, parameters.cameraDirection);
        }

        /**
         * A brief tutorial: here's how all the math is going to work:
         *
         * C = phoneLocationOnRobot  maps   phone coords -> robot coords
         * P = tracker.getPose()     maps   image target coords -> phone coords
         * L = redTargetLocationOnField maps   image target coords -> field coords
         *
         * So
         *
         * C.inverted()              maps   robot coords -> phone coords
         * P.inverted()              maps   phone coords -> imageTarget coords
         *
         * Putting that all together,
         *
         * L x P.inverted() x C.inverted() maps robot coords to field coords.
         *
         * @see VuforiaTrackableDefaultListener#getRobotLocation()
         */


        trackables.activate();
        telemetry.addLine("Initialized... I can see now! And I like Waffles");
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

    /**
     * User defined loop method
     * <p/>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        for (VuforiaTrackable trackable : allTrackables) {
            /**
             * getUpdatedRobotLocation() will return null if no new information is available since
             * the last time that call was made, or if the trackable is not currently visible.
             * getRobotLocation() will return null if the trackable is not currently visible.
             */
            telemetry.addData(trackable.getName(), ((VuforiaTrackableDefaultListener) trackable.getListener()).isVisible() ? "Visible" : "Not Visible");    //

            OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener) trackable.getListener()).getUpdatedRobotLocation();
            if (robotLocationTransform != null) {
                lastLocation = robotLocationTransform;
            }
        }
        /**
         * Provide feedback as to where the robot was last located (if we know).
         */
        if (lastLocation != null) {
            //  RobotLog.vv(TAG, "robot=%s", format(lastLocation));
            telemetry.addData("Pos", lastLocation.formatAsTransform());
        } else {
            telemetry.addData("Pos", "Unknown");
        }
        telemetry.update();
    }

    @Override public void stop(){

    }
}



