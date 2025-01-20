package org.firstinspires.ftc.teamcode.drive.opmode.autonomous.IntoTheDeep.Learn;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Pivot {
    private final DcMotor leftPivot, rightPivot;

    public Pivot(HardwareMap hardwareMap) {
        leftPivot = hardwareMap.get(DcMotor.class, "leftPivot");
        rightPivot = hardwareMap.get(DcMotor.class, "rightPivot");

        leftPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightPivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

}
