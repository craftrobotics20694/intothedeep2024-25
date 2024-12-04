package org.firstinspires.ftc.teamcode.drive.opmode.manual;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

@TeleOp(name="ManualSlopMode", group="practice")
public class manualTest extends OpMode{

    // Create motor & servo variables
    DcMotor leftFront, rightFront, leftBack, rightBack; // Drive Motors
    DcMotor leftSlide, rightSlide, leftSlidePivot, rightSlidePivot; // Slide Motors

    ServoImplEx leftClaw, rightClaw; // Claw Servos

    double leftSlideStartPos;
    double rightSlideStartPos;

    double leftSlidePivStartPos;
    double rightSlidePivStartPos;

    public void init() {
        // Drive Motors
        leftFront = hardwareMap.dcMotor.get("leftFront");
        // leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightFront = hardwareMap.dcMotor.get("rightFront");
        // rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftBack = hardwareMap.dcMotor.get("leftBack");
        // leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightBack = hardwareMap.dcMotor.get("rightBack");
        // rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Slide Motors
        leftSlide = hardwareMap.dcMotor.get("leftSlide");
        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightSlide = hardwareMap.dcMotor.get("rightSlide");
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftSlidePivot = hardwareMap.dcMotor.get("leftSlidePivot");
        leftSlidePivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightSlidePivot = hardwareMap.dcMotor.get("rightSlidePivot");
        rightSlidePivot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Claw Servos
        leftClaw = hardwareMap.get(ServoImplEx.class, "leftClaw");
        rightClaw = hardwareMap.get(ServoImplEx.class, "rightClaw");

        // Set the slide motors to brake when no power is applied
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlidePivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlidePivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // TODO: Reverse Necessary Motors
        leftSlidePivot.setDirection(DcMotorSimple.Direction.REVERSE); // Reverse left pivot motor

        leftSlideStartPos = leftSlide.getCurrentPosition();
        rightSlideStartPos = rightSlide.getCurrentPosition();

        leftSlidePivStartPos = leftSlidePivot.getCurrentPosition();
        rightSlidePivStartPos = rightSlidePivot.getCurrentPosition();
    }

    public void loop() {

        // TODO: Program Gamepad 1
        // Controls the robot's movement

        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double rotInput = gamepad1.left_trigger - gamepad1.right_trigger;

        leftFront.setPower((y + x - rotInput));
        leftBack.setPower((-y + x - rotInput));
        rightFront.setPower((y - x + rotInput));
        rightBack.setPower((y + x + rotInput));

        // TODO: Program Gamepad 2
        // Controls the ViperSlides

        // TODO: Program Claw


        double vert = gamepad2.left_stick_y; // Vertical height

        if (leftSlide.getCurrentPosition() > (leftSlideStartPos + 2000) || rightSlide.getCurrentPosition() < (rightSlideStartPos - 2000)) {
            leftSlide.setPower(-vert);
            rightSlide.setPower(vert);
        }
        else {
            leftSlide.setPower(-vert);
            rightSlide.setPower(vert);
        }

        telemetry.addData("Left Slide Position", leftSlide.getCurrentPosition());
        telemetry.addData("Right Slide Position", rightSlide.getCurrentPosition());

        telemetry.addData("left Pivot Position", leftSlidePivot.getCurrentPosition());
        telemetry.addData("Right Pivot Position", rightSlidePivot.getCurrentPosition());

        double pivot = gamepad2.right_stick_y;

        if (leftSlidePivot.getCurrentPosition() > (leftSlidePivStartPos + 1000) || rightSlidePivot.getCurrentPosition() <= (rightSlidePivStartPos - 2000)) {
            leftSlidePivot.setPower(pivot);
            rightSlidePivot.setPower(-pivot);
        }
        else {
            leftSlidePivot.setPower(pivot);
            rightSlidePivot.setPower(-pivot);
        }
    }
}