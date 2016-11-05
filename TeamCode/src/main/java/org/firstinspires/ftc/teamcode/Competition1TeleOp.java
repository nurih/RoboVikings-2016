package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Competitin 1 TeleOp")
public class Competition1TeleOp extends OpMode {
    List<OpMode> opModeList = new ArrayList<OpMode>();

    @Override
    public void init() {

        // collect some op modes and add them to the list
        opModeList.add(new Drive());
        opModeList.add(new Flippers());
        opModeList.add(new Forklift());
        opModeList.add(new ParticleElevator());


        // call individual init methods on each op mode
        for (OpMode opMode : opModeList) {
            opMode.init();
        }
    }

    @Override
    public void stop() {
        for (OpMode opMode : opModeList) {
            opMode.stop();
        }
    }

    @Override
    public void start() {
        for (OpMode opMode : opModeList) {
            opMode.start();
        }
    }

    @Override
    public void loop() {
        for (OpMode opMode : opModeList) {
            opMode.loop();
        }
    }
}
