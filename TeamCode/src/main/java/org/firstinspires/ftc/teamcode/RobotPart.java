package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * This class contains all the controllable robot parts that we control
 **/

public enum RobotPart {

    /*
    * Left Drive Motor
    * */
    lmotor(DcMotor.class),

    /**
     * Right Drive Motor
     */
    rmotor(DcMotor.class),

    /*
    * Forklift Motor
    * */
    forkliftmotor(DcMotor.class),

    /**
     *  Winder for slingshot
     */
    windermotor(DcMotor.class),

    /*
    * Elevator Motor
    * */
    elevatormotor(DcMotor.class),

    /*
    * Left Flipper Servo
    * */
    lflipperservo(Servo.class),
    /*
    * Right Flipper Servo
    * */
    rflipperservo(Servo.class),

    /*
    * Elevator Motor top limit switch
    * */
    elevatorlimitswitch(TouchSensor.class),

    /*
    * Elevator Scoop
    * */
    scoopservo(Servo.class),

    ;

    private Class<? extends HardwareDevice> _partType;

    RobotPart(Class<? extends HardwareDevice> partType) {
        _partType = partType;
    }

    public Class<? extends HardwareDevice> getPartType() {
        return _partType;
    }
}
