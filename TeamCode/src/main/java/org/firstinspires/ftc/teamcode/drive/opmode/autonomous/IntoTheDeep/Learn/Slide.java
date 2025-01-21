package org.firstinspires.ftc.teamcode.drive.opmode.autonomous.IntoTheDeep.Learn;


import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slide {

    public final DcMotor leftSlide, rightSlide;
    public Telemetry telemetry;

    public Slide(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;

        leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");

        leftSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightSlide.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public class MoveToPosition implements Action {
        private final int targetPosition;
        private final double power;
        private long startTime = 0;

        public MoveToPosition(int targetPosition, double power) {
            this.targetPosition = targetPosition;
            this.power = power;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            if (startTime == 0) {
                leftSlide.setTargetPosition(targetPosition);
                rightSlide.setTargetPosition(targetPosition);

                leftSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                leftSlide.setPower(power);
                rightSlide.setPower(power);

                startTime = System.currentTimeMillis();
            }

            telemetry.addData("Left Slide Position", leftSlide.getCurrentPosition());
            telemetry.addData("Right Slide Position", rightSlide.getCurrentPosition());
            telemetry.update();


            // Check if both motors are still running towards their target positions
            if (Math.abs(leftSlide.getCurrentPosition() - targetPosition) > 5 || Math.abs(rightSlide.getCurrentPosition() - targetPosition) > 5) {
                return true; // Still moving
            }

            // Once the slide has reached the target, stop the motors
            leftSlide.setPower(0);
            rightSlide.setPower(0);
            return false; // Action complete
        }
    }

    public class MoveToHome implements Action {
        private final double power;

        public MoveToHome(double power) {
            this.power = power;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            leftSlide.setPower(-0.5);
            rightSlide.setPower(-0.5);
            return false;
        }

    }

    public Action moveToHome(double power) {
        return new MoveToHome(power);
    }

    // Create a method to return a new MoveToPositionAction instance
    public Action moveToPosition(int targetPosition, double power) {
        return new MoveToPosition(targetPosition, power);
    }

}
