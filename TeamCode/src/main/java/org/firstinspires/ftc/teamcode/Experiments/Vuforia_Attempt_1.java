package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.teamcode.TeamShared;

/**
 * Created by Robovikings on 10/29/2016.
 */
@Autonomous(name = "Vuforia_Attempt_1", group = "Test")
@Disabled
public class Vuforia_Attempt_1 extends LinearOpMode {

    public static final String TAG = "Vuforia_Attempt_1";

    OpenGLMatrix lastLocation = null;

    VuforiaLocalizer Vuforia;

    @Override  public void runOpMode() throws InterruptedException {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(com.qualcomm.ftcrobotcontroller.R.id.cameraMonitorViewId);
        parameters.vuforiaLicenseKey = TeamShared.VisionKey;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;
        this.Vuforia = ClassFactory.createVuforiaLocalizer(parameters);

    }
}
