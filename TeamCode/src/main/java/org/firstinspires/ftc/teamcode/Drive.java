package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "Drive", group = "Mini Op")
public class Drive extends OpMode {
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;
    private double powerLevel;
    private boolean isDrivingForward;
    @Override
    public void init() {
        powerLevel = 0;
        leftMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.lmotor );
        rightMotor = TeamShared.getRobotPart( hardwareMap, RobotPart.rmotor );

        SetMotorDirections(true);

        leftMotor.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.BRAKE );
        leftMotor.setPower( 0 );

        rightMotor.setZeroPowerBehavior( DcMotor.ZeroPowerBehavior.BRAKE );
        rightMotor.setPower( 0 );

        telemetry.addLine( String.format( "Initializing %s  and %s", RobotPart.lmotor, RobotPart.rmotor ) );
    }

    @Override
    public void loop() {
        telemetry.addLine(String.format("Driving Forward: %" , isDrivingForward));
        if (gamepad1.x)
        {
            powerLevel = 0.4;
        }
        else if (gamepad1.y)
        {
            powerLevel = 1;
        }

        if(gamepad1.dpad_up)
        {
            SetMotorDirections( true);
        }
        if(gamepad1.dpad_down)
        {
            SetMotorDirections(false);
        }

        SetMotorPower(powerLevel, isDrivingForward);

    }


    private void SetMotorDirections(boolean isForward){
        isDrivingForward = isForward;

        if(isForward)
        {
            leftMotor.setDirection( DcMotor.Direction.REVERSE );
            rightMotor.setDirection( DcMotor.Direction.FORWARD );}
        else{
            leftMotor.setDirection( DcMotor.Direction.FORWARD );
            rightMotor.setDirection( DcMotor.Direction.REVERSE );
        }
    }
    private void SetMotorPower(double level, boolean isForward) {
        if (isForward)
        {
            leftMotor.setPower( level * gamepad1.right_stick_y );
            rightMotor.setPower( level * gamepad1.left_stick_y );
        }
        else
        {
            leftMotor.setPower( level * gamepad1.left_stick_y );
            rightMotor.setPower( level * gamepad1.right_stick_y );
        }
    }
}
