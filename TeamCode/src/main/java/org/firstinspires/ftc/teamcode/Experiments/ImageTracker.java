package org.firstinspires.ftc.teamcode.Experiments;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.teamcode.VisualTargets;


@TeleOp(name = "Image Tracker", group = "Demo")
public class ImageTracker extends OpMode {
    VuforiaTrackable gears;
    VisualTargets visualTargets;

    @Override
    public void init() {

        telemetry.addLine( "Init() enered" );
        visualTargets = new VisualTargets();

        telemetry.addLine( "getting target image" );
        gears = visualTargets.getGearsTrackable();

        telemetry.addData( "looking for ", gears.getName() );
    }


    @Override
    public void loop() {
        Orientation orientation = visualTargets.getOrientation( gears );

        if (orientation != null) {

            telemetry.addLine( String.format( "\n[X= %f ]\n[Y= %f ]\n[X= %f ]", orientation.firstAngle, orientation.secondAngle, orientation.thirdAngle ) );

            telemetry.addData( "Orientation", orientation.toString() );

            telemetry.addData( "Go " , orientation.secondAngle> 0? "LEft": "Right" );
        }
    }
}



