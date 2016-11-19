package org.firstinspires.ftc.teamcode.Experiments;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptNullOp;


@Disabled
@Autonomous(name = "AutoBasic" , group = "test")
public class TheAuto extends OpMode {

    public DcMotor leftMotor   = null;
    public DcMotor rightMotor  = null;
    @Override
    public void init() {
        leftMotor   = hardwareMap.dcMotor.get("lmotor");
        rightMotor  = hardwareMap.dcMotor.get("rmotor");

        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

    @Override
    public void loop() {
        telemetry.addLine(String.format("Right Encoder: %s", rightMotor.getCurrentPosition()));
        telemetry.update();
        telemetry.addLine(String.format("Left Encoder: %s", leftMotor.getCurrentPosition()));
        telemetry.update();
        leftMotor.setPower(.05);
        rightMotor.setPower(-.05);
        leftMotor.setTargetPosition(5);
        rightMotor.setTargetPosition(-5);
        if (leftMotor.getCurrentPosition() >= leftMotor.getTargetPosition() && rightMotor.getCurrentPosition() <= rightMotor.getTargetPosition()) {
            leftMotor.setTargetPosition(10);
            rightMotor.setTargetPosition(0);
            leftMotor.setPower(.05);
            rightMotor.setPower(.05);
            if (leftMotor.getCurrentPosition() >= leftMotor.getTargetPosition() && rightMotor.getCurrentPosition() <= rightMotor.getTargetPosition()) {
                leftMotor.setPower(0);
                rightMotor.setPower(0);
            }
        }
    }
public void register(OpModeManager manager) {
    manager.register("NullOp", ConceptNullOp.class);
    manager.register("TheAuto" , TheAuto.class);
    }

    }



