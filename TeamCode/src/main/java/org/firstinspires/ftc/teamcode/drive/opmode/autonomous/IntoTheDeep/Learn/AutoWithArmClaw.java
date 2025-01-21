package org.firstinspires.ftc.teamcode.drive.opmode.autonomous.IntoTheDeep.Learn;

import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AutoWithArmClaw", group="practice")
public class AutoWithArmClaw extends LinearOpMode {
    private Claw claw;
    private Slide slide;
    //private Pivot pivot;

    @Override
    public void runOpMode() {
        claw = new Claw(hardwareMap);
        slide = new Slide(hardwareMap, telemetry);
        // pivot = new Pivot(hardwareMap);

        waitForStart();
        Actions.runBlocking(
            slide.moveToHome(.5)
        );

        while (opModeIsActive()) {
            Actions.runBlocking(
                new SequentialAction(
                    slide.moveToPosition(1500, 0.5),
                    claw.openClaw()
                ));
        }
    }
}