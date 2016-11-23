package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.TeamVision;

/**
 * Created by Robovikings on 11/22/2016.
 */

@TeleOp(name = "See Gears Experimental", group = "Test")
public class SeeTarget extends OpMode {

    int imageIndex = 0;
    int allianceIndex = 0;
    Alliance alliance;
    private VuforiaTrackable imageToTrack;

    @Override
    public void init() {
        chooseImage();
        chooseAlliance();
    }

    private void chooseImage() {
        imageToTrack = TeamVision.getTrackable(imageIndex);
        telemetry.addData("Tracking ", imageToTrack.getName());
    }

    @Override
    public void init_loop() {
        if (gamepad1.x) {
            imageIndex++;
            chooseImage();
        }
        if (gamepad1.y) {
            allianceIndex = (allianceIndex + 1) % 2;
            chooseAlliance();
        }
    }

    private void chooseAlliance() {
        alliance = Alliance.values()[allianceIndex];
        telemetry.addData("Alliance", alliance.name());
    }

    @Override
    public void loop() {
        Orientation orientation = TeamVision.getOrientation(imageToTrack);
        if (orientation != null) {
            telemetry.addData("Orientation", orientation.toString());
        }
    }
}
