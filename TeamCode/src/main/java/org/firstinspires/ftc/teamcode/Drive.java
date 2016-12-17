package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "Drive", group = "Mini Op")
public class Drive extends OpMode {
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    @Override
    public void init() {
        leftMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.lmotor );
        rightMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.rmotor );


        leftMotor.setDirection( DcMotor.Direction.REVERSE );
        leftMotor.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.BRAKE );
        leftMotor.setPower( 0 );

        rightMotor.setDirection( DcMotor.Direction.FORWARD );
        rightMotor.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.BRAKE );
        rightMotor.setPower( 0 );

        telemetry.addLine( String.format( "Initializing %s  and %s", RobotPart.lmotor, RobotPart.rmotor ) );
    }

    @Override
    public void loop() {
        double powerLevel = 0.6;

        leftMotor.setPower( powerLevel * gamepad1.left_stick_y );
        rightMotor.setPower( powerLevel * gamepad1.right_stick_y );
    }
}
