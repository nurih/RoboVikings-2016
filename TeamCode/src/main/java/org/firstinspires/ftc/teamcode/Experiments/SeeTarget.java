package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.teamcode.Alliance;
import org.firstinspires.ftc.teamcode.TeamVision;

/**
 * Created by Robovikings on 11/22/2016.
 */
@Disabled
@TeleOp(name = "See Gears Experimental", group = "Test")
public class SeeTarget extends OpMode {

    int imageIndex = 0;
    int allianceIndex = 0;
    Alliance alliance;
    private VuforiaTrackable imageToTrack;

    @Override
    public void init() {
        imageToTrack = TeamVision.getTrackable( imageIndex );

        alliance = Alliance.values()[allianceIndex];
        telemetry.addData( "Alliance", alliance.name() );
    }


    @Override
    public void init_loop() {
        if (gamepad1.x) {
            imageIndex = imageIndex + 1 % 4;
            imageToTrack = TeamVision.getTrackable( imageIndex );
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
        Orientation orientation = TeamVision.getOrientation( imageToTrack );
        if (orientation != null) {
            telemetry.addData( "Orientation", orientation.toString() );
        }
    }

}
