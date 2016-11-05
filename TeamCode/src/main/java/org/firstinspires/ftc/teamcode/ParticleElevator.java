package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Particle Elevator", group = "mini Op")
public class ParticleElevator extends OpMode {
    DcMotor motor;


    @Override
    public void init() {
        telemetry.addLine("Initializing!");
        motor = hardwareMap.dcMotor.get("elevatormotor");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        motor.setPower(0);
    }


    @Override
    public void loop() {
        motor.setPower(this.gamepad2.right_stick_y);
    }
}
