package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Scoop", group = "Mini Op")
public class Scoop extends OpMode {
    private final double startingPosition = Servo.MAX_POSITION;
    private final double finalPosition = Servo.MIN_POSITION + 0.35;

    public Servo scoopServo = null;

    @Override
    public void init() {
        scoopServo = TeamShared.getRobotPart( hardwareMap, RobotPart.scoopservo );
        scoopServo.setPosition( startingPosition );
    }

    @Override
    public void loop() {
        if (gamepad2.a) {
            telemetry.addLine( "Scoop dumping... " + finalPosition );
            scoopServo.setPosition( finalPosition );
        } else {
            telemetry.addLine( "Scoop at rest.. " + startingPosition );
            scoopServo.setPosition( startingPosition );
        }
    }
}
