package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Competition 1 TeleOp")
public class Competition1TeleOp extends OpMode {
    List<OpMode> opModeList = new ArrayList<OpMode>();

    protected void addOpMode(OpMode op) {
        telemetry.addLine("Adding mini op mode " + op.toString());
        telemetry.update();
        op.hardwareMap = this.hardwareMap;
        op.telemetry = this.telemetry;
        op.gamepad1 = this.gamepad1;
        op.gamepad2 = this.gamepad2;


        opModeList.add(op);
    }

    @Override
    public void init() {

        // collect some op modes and add them to the list
        telemetry.addLine("Adding mini op modes to list");
        telemetry.update();


        addOpMode(new Drive());
        addOpMode(new Flippers());
        addOpMode(new Forklift());
        addOpMode(new Elevator());
        addOpMode(new Winder());

        telemetry.addLine("calling init() on mini op modes");
        telemetry.update();
        // call individual init methods on each op mode
        for (OpMode opMode : opModeList) {
            telemetry.addLine("init:: " + opMode.toString());
            telemetry.update();

            try {
                opMode.init();
            } catch (Exception e) {
                telemetry.addLine("failed init() on " + opMode.getClass().getSimpleName());
                telemetry.addData("Exception: ", e);
            }
        }
    }

    @Override
    public void loop() {
        for (OpMode opMode : opModeList) {
            try {
                opMode.loop();
            } catch (Exception e) {
                telemetry.addLine("Failed loop() on " + opMode.toString());
                telemetry.addData("Exception", e);
            }
        }
    }

}
