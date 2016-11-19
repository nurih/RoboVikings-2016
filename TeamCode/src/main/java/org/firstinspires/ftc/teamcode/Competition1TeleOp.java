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
        op.hardwareMap = this.hardwareMap;
        op.telemetry = this.telemetry;
        op.gamepad1 = this.gamepad1;
        op.gamepad2 = this.gamepad2;

        if (tryInit(op)) {
            opModeList.add(op);
        } else {
            telemetry.addLine("OP MODE IS NOT USABLE!!!" + op.getClass().getSimpleName());
        }
    }

    private boolean tryInit(OpMode opMode) {
        telemetry.addLine("Calling init() -> " + opMode.getClass().getSimpleName());


        try {
            opMode.init();
            return true;
        } catch (Exception e) {
            telemetry.addLine("FAILED init() on " + opMode.getClass().getSimpleName());
            telemetry.addData("Exception: ", e);
            return false;
        }
    }

    @Override
    public void init() {
        // collect some op modes and add them to the list
        telemetry.addLine("Adding mini op modes to list");

        addOpMode(new Drive());
        addOpMode(new Flippers());
        addOpMode(new Forklift());
        addOpMode(new Elevator());
        addOpMode(new Scoop());
        addOpMode(new Winder());
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
