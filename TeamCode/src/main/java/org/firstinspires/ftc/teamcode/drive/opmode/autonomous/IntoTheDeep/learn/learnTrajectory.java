package org.firstinspires.ftc.teamcode.drive.opmode.autonomous.IntoTheDeep.learn;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;


@Config
@Autonomous(name="learnTrajectory", group="learn")
public class learnTrajectory extends LinearOpMode {

    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-35, -50, Math.toRadians(90)));

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
