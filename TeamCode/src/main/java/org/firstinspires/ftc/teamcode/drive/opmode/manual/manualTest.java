package org.firstinspires.ftc.teamcode.drive.opmode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="ManualSlopMode", group="practice")
public class manualTest extends OpMode{

    // Create motor & servo variables
    DcMotor leftFront, rightFront, leftBack, rightBack; // Drive motors
    DcMotor leftSlide, rightSlide, leftSlidePivot, rightSlidePivot;

    Servo leftClaw, rightClaw;

    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        leftSlide = hardwareMap.dcMotor.get("leftSlide");
        rightSlide = hardwareMap.dcMotor.get("rightSlide");

        // Set the slide motors to brake when no power is applied
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftSlidePivot = hardwareMap.dcMotor.get("leftSlidePivot");
        rightSlidePivot = hardwareMap.dcMotor.get("rightSlidePivot");

        leftClaw = hardwareMap.servo.get("leftClaw");
        rightClaw = hardwareMap.servo.get("rightClaw");

        // Reverse one side of the claw
        leftClaw.setDirection(Servo.Direction.REVERSE);

        // Reverse left slide motor
        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        // Reverse left pivot motor
        leftSlidePivot.setDirection(DcMotorSimple.Direction.REVERSE);
        // Set claw positions to 0 (closed)
//        leftClaw.setPosition(0);
//        rightClaw.setPosition(0);
    }

    public void loop() {

        // TODO: Program Gamepad 1
        // Controls the robot's movement

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rotInput = gamepad1.left_trigger - gamepad1.right_trigger;

        leftFront.setPower((-y - x + rotInput));
        leftBack.setPower((-y + x + rotInput));
        rightFront.setPower((y - x + rotInput));
        rightBack.setPower((y + x + rotInput));

        // TODO: Program Gamepad 2
        // Controls the ViperSlides

        double vert = gamepad2.left_stick_y; // Vertical height
        telemetry.addData("Slide Position", vert);

        if (rightSlide.getCurrentPosition() >= 3000 || leftSlide.getCurrentPosition() <= -3000) {
            leftSlide.setPower(-vert*0.75);
            rightSlide.setPower(vert*0.75);
        }
        else {
            leftSlide.setPower(-vert);
            rightSlide.setPower(vert);
        }

        double pivot = gamepad2.right_stick_y;
        telemetry.addData("Pivot Position", pivot);

        telemetry.addData("pivotPosition", leftSlidePivot.getCurrentPosition());

        if (leftSlidePivot.getCurrentPosition() >= 200 || rightSlidePivot.getCurrentPosition() <= 200) {
            leftSlidePivot.setPower(pivot);
            rightSlidePivot.setPower(pivot);
        }
    }
}