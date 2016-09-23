package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Hello World", group = "Demos")
public class HelloWorldOpMode extends OpMode {
    /**
     * User defined init method
     * <p/>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
        telemetry.addLine(String.format("Starting Op Mode!%s", getRuntime()));
        telemetry.update();
    }

    /**
     * User defined loop method
     * <p/>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {

        telemetry.addLine(String.format("LoopetiLoop!%s", getRuntime()));
        telemetry.update();
    }

    @Override
    public void stop() {
        telemetry.addLine("Stopping Op Mode!");
        telemetry.update();
    }
}
