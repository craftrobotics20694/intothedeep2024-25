package com.example.meepmeeptesting.Learn;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class mapAStar {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 50, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, -35, Math.toRadians(90)))
                .turn(Math.toRadians(-30))
                .strafeTo(new Vector2d(0, 35))
                .turn(Math.toRadians(-120))
                .strafeTo(new Vector2d(35, -35))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(-40, 10))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(40,10))
                .turn(Math.toRadians(-150))
                .strafeTo(new Vector2d(-35,-35))
                .turn(Math.toRadians(-120))
                .waitSeconds(0.2)
                .build());

        // Getting the image of our play field to be used
        Image img = null;
        try {
            img = ImageIO.read(new File("MeepMeepTesting/src/main/java/com/example/meepmeeptesting/FIELD_INTOTHEDEEP.png"));
        }
        catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        meepMeep.setBackground(img)
            .setBackgroundAlpha(0.95f)
            .addEntity(myBot)
            .setDarkMode(true)
            .start();
    }
}