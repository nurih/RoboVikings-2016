package org.firstinspires.ftc.teamcode;

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

/**
 * Created by Robovikings on 12/4/2016.
 */
public class VisualTargets {

    public static final String TAG = "RoboVikings 9887";
    public static final float MillimetersPerInch = 25.4f;
    public static final float RobotWidthMillimeters = 18 * MillimetersPerInch;
    public static final float fieldWidthMillimeters = (12 * 12 - 2) * MillimetersPerInch;
    private VuforiaTrackables trackables;

    private void AssignTrackableImageLocation(VuforiaTrackable trackable, float translationDx, float translationDy, AxesOrder axesOrder, int firstAngle, int secondAngle) {
        OpenGLMatrix redTargetLocationOnField = OpenGLMatrix
                .translation( translationDx, translationDy, 0 )
                .multiplied( Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, axesOrder,
                        AngleUnit.DEGREES, firstAngle, secondAngle, 0 ) );
        trackable.setLocation( redTargetLocationOnField );
        RobotLog.ii( TAG, "%s =%s", trackable.getName(), redTargetLocationOnField.formatAsTransform() );
    }

    public VisualTargets() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters( com.qualcomm.ftcrobotcontroller.R.id.cameraMonitorViewId );
        parameters.vuforiaLicenseKey = TeamShared.VisionKey;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforiaLocalizer = ClassFactory.createVuforiaLocalizer( parameters );

        trackables = vuforiaLocalizer.loadTrackablesFromAsset( "FTC_2016-17" );

        trackables.get( 0 ).setName( TargetImages.Wheels.name() );
        trackables.get( 1 ).setName( TargetImages.Tools.name() );
        trackables.get( 2 ).setName( TargetImages.Legos.name() );
        trackables.get( 3 ).setName( TargetImages.Gears.name() );

        AssignTrackableImageLocation( trackables.get( 0 ), -fieldWidthMillimeters / 2, 0, AxesOrder.XZX, 90, 90 );
        AssignTrackableImageLocation( trackables.get( 1 ), 600 - fieldWidthMillimeters / 2, 0, AxesOrder.XZX, 90, 90 );
        AssignTrackableImageLocation( trackables.get( 2 ), 0, fieldWidthMillimeters / 2, AxesOrder.XZX, 90, 0 );
        AssignTrackableImageLocation( trackables.get( 2 ), 0, 600 + fieldWidthMillimeters / 2, AxesOrder.XZX, 90, 0 );


        OpenGLMatrix phoneLocationOnRobot = OpenGLMatrix
                .translation( RobotWidthMillimeters / 2, 0, 0 )
                .multiplied( Orientation.getRotationMatrix(
                        AxesReference.EXTRINSIC, AxesOrder.YZY,
                        AngleUnit.DEGREES, -90, 0, 0 ) );
        RobotLog.ii( TAG, "phone=%s", phoneLocationOnRobot.formatAsTransform() );


        for (int i = 0; i < 3; i++) {
            ((VuforiaTrackableDefaultListener) trackables.get( i ).getListener()).setPhoneInformation( phoneLocationOnRobot, parameters.cameraDirection );
        }

        trackables.activate();

        RobotLog.ii( TAG, "Activated Trackable images in Visual Targets");

    }

    protected void finalize() {
        trackables.deactivate();
        trackables.clear();
    }

    public VuforiaTrackable getWheelsTrackable() {
        return trackables.get( 0 );
    }

    public VuforiaTrackable getToolsTrackable() {
        return trackables.get( 1 );
    }

    public VuforiaTrackable getLegosTrackable() {
        return trackables.get( 2 );
    }

    public VuforiaTrackable getGearsTrackable() {
        return trackables.get( 3 );
    }

    public VuforiaTrackable getTrackable(int i) {
        return trackables.get( Math.abs( i % 4 ) );
    }

    public Orientation getOrientation(VuforiaTrackable imageToDriveTo) {
        VuforiaTrackableDefaultListener listener = (VuforiaTrackableDefaultListener) imageToDriveTo.getListener();
        if (listener.isVisible()) {
            OpenGLMatrix pose = listener.getPose();
            if (pose != null) {
                Orientation orientation = Orientation.getOrientation( pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES );
                return orientation;
            }
        }
        return null;
    }

}
