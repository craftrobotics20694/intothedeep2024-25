package org.firstinspires.ftc.teamcode.drive.opmode.autonomous.IntoTheDeep.learn;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.MecanumDrive;

// This is the second intro to autonomous
// We will be adding claw and lift functionality
// You will find this is actually easier than you'd expect because of RoadRunner

@Config
@Autonomous(name = "learnAuto2", group = "learn")
public class learnAuto2 extends LinearOpMode {

    public class Claw {
        private Servo clawLeft;
        private Servo clawRight;

        public Claw(HardwareMap hardwareMap) {
            clawLeft = hardwareMap.get(Servo.class, "clawLeft");
            clawRight = hardwareMap.get(Servo.class, "clawRight");
        }

        public class CloseClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                clawLeft.setPosition(.5);
                clawRight.setPosition(.5);
                return false;
            }
        }
        public Action closeClaw() {
            return new CloseClaw();
        }

        public class OpenClaw implements Action {
            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                clawLeft.setPosition(0);
                clawRight.setPosition(0);
                return false;
            }
        }
        public Action openClaw() {
            return new OpenClaw();
        }
    }

    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-25, -25, Math.toRadians(90)));
        Claw claw = new Claw(hardwareMap);

        Action trajectoryAction1;
        Action trajectoryAction2;
        Action trajectoryAction3;

        trajectoryAction1 = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(0,0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(180))
                .lineToY(-25)
                .setTangent(Math.toRadians(90))
                .lineToX(25)
                .setTangent(Math.toRadians(0))
                .waitSeconds(0.5)
                .build();

        trajectoryAction2 = drive.actionBuilder(new Pose2d(25, -25, Math.toRadians(90)))
                .strafeTo(new Vector2d(0,0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(180))
                .lineToY(-25)
                .setTangent(Math.toRadians(90))
                .lineToX(25)
                .setTangent(Math.toRadians(0))
                .waitSeconds(0.5)
                .build();

        trajectoryAction3 = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(0,0))
                .waitSeconds(0.5)
                .setTangent(Math.toRadians(180))
                .lineToY(-25)
                .setTangent(Math.toRadians(90))
                .lineToX(25)
                .setTangent(Math.toRadians(0))
                .waitSeconds(0.5)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryAction1,
                        trajectoryAction2,
                        trajectoryAction3
                )
        );
    }
}
