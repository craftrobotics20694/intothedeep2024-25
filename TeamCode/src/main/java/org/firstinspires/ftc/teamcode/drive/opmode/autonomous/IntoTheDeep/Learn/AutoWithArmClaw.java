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
        slide = new Slide(hardwareMap);
        // pivot = new Pivot(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("Left Slide Pos", slide.leftSlide.getCurrentPosition());
            telemetry.addData("Right Slide Pos", slide.rightSlide.getCurrentPosition());
            telemetry.update();

            Actions.runBlocking(
                slide.moveToPosition(300, 50)
            );
        }
    }
}
