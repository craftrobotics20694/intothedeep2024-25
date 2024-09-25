package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MeepMeepTesting {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep = new MeepMeep(1000);

        // Defining our first bot
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        // Building the trajectory for our first bot
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-35, 60, Math.toRadians(270)))
                        .turn(Math.toRadians(90))
                        .lineToX(34)
                        .turn(Math.toRadians(-90))
                        .lineToY(34)
                        .turn(Math.toRadians(90))
                        .lineToX(60)
                        .turn(Math.toRadians(-90))
                        .lineToY(-34)
                        .turn(Math.toRadians(-90))
                        .lineToX(0)
                        .turn(Math.toRadians(90))
                        .lineToY(-60)
                        .turn(Math.toRadians(90))
                        .lineToX(35)
                        .turn(Math.toRadians(90))
                        .build());

        // Getting the image of our play field to be used
        Image img = null;
        try {img = ImageIO.read(new File("MeepMeepTesting/src/main/java/com/example/meepmeeptesting/FIELD_INTOTHEDEEP.png")); }
            catch (IOException e) {}

        meepMeep.setBackground(img)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

