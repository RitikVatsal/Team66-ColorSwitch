//GROUP 66
//Ritik Vatsal (2019321) | Vaibhav Saxena (2019342)

import java.io.*;
import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;

import java.sql.Struct;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Timer;
import java.lang.*;

class Colours extends Parent
{
    protected String coloursarr[] = {"Pink","Purple","Blue","Yellow"};
    protected int currColour = -1;
    protected Random r = new Random();

    protected void setColour(int newc)
    {
        currColour = newc;
    }
    protected String getColour()
    {
        return coloursarr[currColour];
    }
}

class Ball extends Colours
{
    protected Circle circle = new Circle();
    protected double xval = 225;
    protected double yval = 700;
    protected int hitgrnd = 0;
    protected int hitobstacle = 0;

    Ball(Stage stage)
    {
        this.setColour(r.nextInt(4));

        circle.setRadius(15);
        circle.setTranslateX(xval);
        circle.setTranslateY(yval);
        circle.setFill(Color.WHITE);

        getChildren().addAll(circle);
    }

    public void switchColour(ColourSwitcher cs)
    {
        switch(cs.nextColour)
        {
            case 0: circle.setFill(Color.DEEPPINK);
                break;
            case 1: circle.setFill(Color.DARKVIOLET);
                break;
            case 2: circle.setFill(Color.DEEPSKYBLUE);
                break;
            case 3: circle.setFill(Color.YELLOW);
                break;
        }
    }
}

class ObstacleForSerial implements Serializable
{
    protected int data = 0;
}

abstract class Obstacle extends Colours implements Serializable
{
    protected int objType = 0;
    protected Group g1;
    protected Path ring1;
    protected Path ring2;
    protected Path ring3;
    protected Path ring4;
    protected double yy;
    protected int creatednext = 0;

    public abstract void movement(Group g);

    protected Path arc1( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DEEPPINK;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX + innerrad);
        mv.setY( yy);

        ArcTo arcdraw1 = new ArcTo();
        arcdraw1.setRadiusX(innerrad);
        arcdraw1.setRadiusY(innerrad);
        arcdraw1.setX(centerX);
        arcdraw1.setY( yy - innerrad);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX + innerrad);
        moveTo2.setY( yy);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX + rad);

        ArcTo arcdraw2 = new ArcTo();
        arcdraw2.setRadiusX(rad);
        arcdraw2.setRadiusY(rad);
        arcdraw2.setX(centerX);
        arcdraw2.setY( yy - rad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy - innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(horizontal);
        p.getElements().add(arcdraw2);
        p.getElements().add(vertical);

        return p;
    }
    protected Path arc2( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DEEPSKYBLUE;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX);
        mv.setY( yy - innerrad);

        ArcTo arcdraw1 = new ArcTo();
        arcdraw1.setRadiusX(innerrad);
        arcdraw1.setRadiusY(innerrad);
        arcdraw1.setX(centerX - innerrad);
        arcdraw1.setY( yy);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX);
        moveTo2.setY( yy - innerrad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy - rad);

        ArcTo arcdraw2 = new ArcTo();
        arcdraw2.setRadiusX(rad);
        arcdraw2.setRadiusY(rad);
        arcdraw2.setX(centerX - rad);
        arcdraw2.setY( yy);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX - innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(vertical);
        p.getElements().add(arcdraw2);
        p.getElements().add(horizontal);

        return p;
    }
    protected Path arc3( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DARKVIOLET;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX - innerrad);
        mv.setY( yy);

        ArcTo arcdraw1 = new ArcTo();
        arcdraw1.setRadiusX(innerrad);
        arcdraw1.setRadiusY(innerrad);
        arcdraw1.setX(centerX);
        arcdraw1.setY( yy + innerrad);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX - innerrad);
        moveTo2.setY( yy);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX - rad);

        ArcTo arcdraw2 = new ArcTo();
        arcdraw2.setRadiusX(rad);
        arcdraw2.setRadiusY(rad);
        arcdraw2.setX(centerX);
        arcdraw2.setY( yy + rad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy + innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(horizontal);
        p.getElements().add(arcdraw2);
        p.getElements().add(vertical);

        return p;
    }
    protected Path arc4( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.YELLOW;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX);
        mv.setY( yy + innerrad);

        ArcTo arcdraw1 = new ArcTo();
        arcdraw1.setRadiusX(innerrad);
        arcdraw1.setRadiusY(innerrad);
        arcdraw1.setX(centerX + innerrad);
        arcdraw1.setY( yy);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX);
        moveTo2.setY( yy + innerrad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy + rad);

        ArcTo arcdraw2 = new ArcTo();
        arcdraw2.setRadiusX(rad);
        arcdraw2.setRadiusY(rad);
        arcdraw2.setX(centerX + rad);
        arcdraw2.setY( yy);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX + innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(vertical);
        p.getElements().add(arcdraw2);
        p.getElements().add(horizontal);

        return p;
    }

    protected Path arc12( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DEEPPINK;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX + innerrad);
        mv.setY( yy-50);

        ArcTo arcdraw1 = new ArcTo();
        arcdraw1.setRadiusX(innerrad);
        arcdraw1.setRadiusY(innerrad);
        arcdraw1.setX(centerX+50);
        arcdraw1.setY( yy - innerrad);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX + innerrad);
        moveTo2.setY( yy-50);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX + rad);

        ArcTo arcdraw2 = new ArcTo();
        arcdraw2.setRadiusX(rad);
        arcdraw2.setRadiusY(rad);
        arcdraw2.setX(centerX+50);
        arcdraw2.setY( yy - rad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy - innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(horizontal);
        p.getElements().add(arcdraw2);
        p.getElements().add(vertical);

        return p;
    }
    protected Path arc22( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DEEPSKYBLUE;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-50);
        mv.setY( yy - innerrad);

        ArcTo arcdraw1 = new ArcTo();
        arcdraw1.setRadiusX(innerrad);
        arcdraw1.setRadiusY(innerrad);
        arcdraw1.setX(centerX - innerrad);
        arcdraw1.setY( yy-50);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX-50);
        moveTo2.setY( yy - innerrad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy - rad);

        ArcTo arcdraw2 = new ArcTo();
        arcdraw2.setRadiusX(rad);
        arcdraw2.setRadiusY(rad);
        arcdraw2.setX(centerX - rad);
        arcdraw2.setY( yy-50);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX - innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(vertical);
        p.getElements().add(arcdraw2);
        p.getElements().add(horizontal);

        return p;
    }
    protected Path arc32( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DARKVIOLET;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX - innerrad);
        mv.setY( yy+50);

        ArcTo arcdraw1 = new ArcTo();
        arcdraw1.setRadiusX(innerrad);
        arcdraw1.setRadiusY(innerrad);
        arcdraw1.setX(centerX-50);
        arcdraw1.setY( yy + innerrad);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX - innerrad);
        moveTo2.setY( yy+50);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX - rad);

        ArcTo arcdraw2 = new ArcTo();
        arcdraw2.setRadiusX(rad);
        arcdraw2.setRadiusY(rad);
        arcdraw2.setX(centerX-50);
        arcdraw2.setY( yy + rad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy + innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(horizontal);
        p.getElements().add(arcdraw2);
        p.getElements().add(vertical);

        return p;
    }
    protected Path arc42( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.YELLOW;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+50);
        mv.setY( yy + innerrad);

        ArcTo arcdraw1 = new ArcTo();
        arcdraw1.setRadiusX(innerrad);
        arcdraw1.setRadiusY(innerrad);
        arcdraw1.setX(centerX + innerrad);
        arcdraw1.setY( yy+50);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX+50);
        moveTo2.setY( yy + innerrad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy + rad);

        ArcTo arcdraw2 = new ArcTo();
        arcdraw2.setRadiusX(rad);
        arcdraw2.setRadiusY(rad);
        arcdraw2.setX(centerX + rad);
        arcdraw2.setY( yy+50);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX + innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(vertical);
        p.getElements().add(arcdraw2);
        p.getElements().add(horizontal);

        return p;
    }

    protected Path rect1( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DEEPPINK;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX + innerrad);
        mv.setY( yy);

        LineTo arcdraw1 = new LineTo();
        arcdraw1.setX(centerX);
        arcdraw1.setY( yy - innerrad);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX + innerrad);
        moveTo2.setY( yy);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX + rad);

        LineTo arcdraw2 = new LineTo();
        arcdraw2.setX(centerX);
        arcdraw2.setY( yy - rad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy - innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(horizontal);
        p.getElements().add(arcdraw2);
        p.getElements().add(vertical);

        return p;
    }
    protected Path rect2( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DEEPSKYBLUE;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX);
        mv.setY( yy - innerrad);

        LineTo arcdraw1 = new LineTo();
        arcdraw1.setX(centerX - innerrad);
        arcdraw1.setY( yy);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX);
        moveTo2.setY( yy - innerrad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy - rad);

        LineTo arcdraw2 = new LineTo();
        arcdraw2.setX(centerX - rad);
        arcdraw2.setY( yy);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX - innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(vertical);
        p.getElements().add(arcdraw2);
        p.getElements().add(horizontal);

        return p;
    }
    protected Path rect3( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DARKVIOLET;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX - innerrad);
        mv.setY( yy);

        LineTo arcdraw1 = new LineTo();
        arcdraw1.setX(centerX);
        arcdraw1.setY( yy + innerrad);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX - innerrad);
        moveTo2.setY( yy);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX - rad);

        LineTo arcdraw2 = new LineTo();
        arcdraw2.setX(centerX);
        arcdraw2.setY( yy + rad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy + innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(horizontal);
        p.getElements().add(arcdraw2);
        p.getElements().add(vertical);

        return p;
    }
    protected Path rect4( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.YELLOW;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX);
        mv.setY( yy + innerrad);

        LineTo arcdraw1 = new LineTo();
        arcdraw1.setX(centerX + innerrad);
        arcdraw1.setY( yy);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX);
        moveTo2.setY( yy + innerrad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy + rad);

        LineTo arcdraw2 = new LineTo();
        arcdraw2.setX(centerX + rad);
        arcdraw2.setY( yy);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX + innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(vertical);
        p.getElements().add(arcdraw2);
        p.getElements().add(horizontal);

        return p;
    }

    protected Path rect12( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DEEPPINK;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX + innerrad);
        mv.setY( yy-50);

        LineTo arcdraw1 = new LineTo();
        arcdraw1.setX(centerX+50);
        arcdraw1.setY( yy - innerrad);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX + innerrad);
        moveTo2.setY( yy-50);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX + rad);

        LineTo arcdraw2 = new LineTo();
        arcdraw2.setX(centerX+50);
        arcdraw2.setY( yy - rad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy - innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(horizontal);
        p.getElements().add(arcdraw2);
        p.getElements().add(vertical);

        return p;
    }
    protected Path rect22( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DEEPSKYBLUE;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-50);
        mv.setY( yy - innerrad);

        LineTo arcdraw1 = new LineTo();
        arcdraw1.setX(centerX - innerrad);
        arcdraw1.setY( yy-50);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX-50);
        moveTo2.setY( yy - innerrad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy - rad);

        LineTo arcdraw2 = new LineTo();
        arcdraw2.setX(centerX - rad);
        arcdraw2.setY( yy-50);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX - innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(vertical);
        p.getElements().add(arcdraw2);
        p.getElements().add(horizontal);

        return p;
    }
    protected Path rect32( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.DARKVIOLET;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX - innerrad);
        mv.setY( yy+50);

        LineTo arcdraw1 = new LineTo();
        arcdraw1.setX(centerX-50);
        arcdraw1.setY( yy + innerrad);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX - innerrad);
        moveTo2.setY( yy+50);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX - rad);

        LineTo arcdraw2 = new LineTo();
        arcdraw2.setX(centerX-50);
        arcdraw2.setY( yy + rad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy + innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(horizontal);
        p.getElements().add(arcdraw2);
        p.getElements().add(vertical);

        return p;
    }
    protected Path rect42( )
    {
        double centerX = 225;
        double rad = 140;
        double innerrad = 120;
        Color bg = Color.YELLOW;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+50);
        mv.setY( yy + innerrad);

        LineTo arcdraw1 = new LineTo();
        arcdraw1.setX(centerX + innerrad);
        arcdraw1.setY( yy+50);

        MoveTo moveTo2 = new MoveTo();
        moveTo2.setX(centerX+50);
        moveTo2.setY( yy + innerrad);

        VLineTo vertical = new VLineTo();
        vertical.setY( yy + rad);

        LineTo arcdraw2 = new LineTo();
        arcdraw2.setX(centerX + rad);
        arcdraw2.setY( yy+50);

        HLineTo horizontal = new HLineTo();
        horizontal.setX(centerX + innerrad);

        p.getElements().add(mv);
        p.getElements().add(arcdraw1);
        p.getElements().add(moveTo2);
        p.getElements().add(vertical);
        p.getElements().add(arcdraw2);
        p.getElements().add(horizontal);

        return p;
    }

    protected Path hand1( )
    {
        double centerX = 225;
        Color bg = Color.DEEPPINK;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+70);
        mv.setY( yy-60);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX+150);
        mv1.setY( yy-140);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX+140);
        mv2.setY( yy-150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX+60);
        mv3.setY( yy-70);

        LineTo mv4 = new LineTo();
        mv4.setX(centerX+70);
        mv4.setY( yy-60);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);
        p.getElements().add(mv4);

        return p;
    }
    protected Path hand2( )
    {
        double centerX = 225;
        Color bg = Color.DEEPSKYBLUE;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-70);
        mv.setY( yy-60);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX-150);
        mv1.setY( yy-140);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX-140);
        mv2.setY( yy-150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX-60);
        mv3.setY( yy-70);

        LineTo mv4 = new LineTo();
        mv4.setX(centerX-70);
        mv4.setY( yy-60);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);
        p.getElements().add(mv4);

        return p;
    }
    protected Path hand3( )
    {
        double centerX = 225;
        Color bg = Color.DARKVIOLET;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-70);
        mv.setY( yy+60);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX-150);
        mv1.setY( yy+140);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX-140);
        mv2.setY( yy+150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX-60);
        mv3.setY( yy+70);

        LineTo mv4 = new LineTo();
        mv4.setX(centerX-70);
        mv4.setY( yy+60);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);
        p.getElements().add(mv4);

        return p;
    }
    protected Path hand4( )
    {
        double centerX = 225;
        Color bg = Color.YELLOW;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+70);
        mv.setY( yy+60);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX+150);
        mv1.setY( yy+140);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX+140);
        mv2.setY( yy+150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX+60);
        mv3.setY( yy+70);

        LineTo mv4 = new LineTo();
        mv4.setX(centerX+70);
        mv4.setY( yy+60);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);
        p.getElements().add(mv4);

        return p;
    }

    protected Path hand12( )
    {
        double centerX = 325;
        Color bg = Color.DEEPPINK;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+20);
        mv.setY( yy-10);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX+150);
        mv1.setY( yy-140);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX+140);
        mv2.setY( yy-150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX+10);
        mv3.setY( yy-20);

        LineTo mv4 = new LineTo();
        mv4.setX(centerX+20);
        mv4.setY( yy-10);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);
        p.getElements().add(mv4);

        return p;
    }
    protected Path hand22( )
    {
        double centerX = 325;
        Color bg = Color.DEEPSKYBLUE;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-20);
        mv.setY( yy-10);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX-150);
        mv1.setY( yy-140);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX-140);
        mv2.setY( yy-150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX-10);
        mv3.setY( yy-20);

        LineTo mv4 = new LineTo();
        mv4.setX(centerX-20);
        mv4.setY( yy-10);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);
        p.getElements().add(mv4);

        return p;
    }
    protected Path hand32( )
    {
        double centerX = 325;
        Color bg = Color.DARKVIOLET;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-20);
        mv.setY( yy+10);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX-150);
        mv1.setY( yy+140);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX-140);
        mv2.setY( yy+150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX-10);
        mv3.setY( yy+20);

        LineTo mv4 = new LineTo();
        mv4.setX(centerX-20);
        mv4.setY( yy+10);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);
        p.getElements().add(mv4);

        return p;
    }
    protected Path hand42( )
    {
        double centerX = 325;
        Color bg = Color.YELLOW;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+20);
        mv.setY( yy+10);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX+150);
        mv1.setY( yy+140);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX+140);
        mv2.setY( yy+150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX+10);
        mv3.setY( yy+20);

        LineTo mv4 = new LineTo();
        mv4.setX(centerX+20);
        mv4.setY( yy+10);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);
        p.getElements().add(mv4);

        return p;
    }

    protected Path tri1( )
    {
        double centerX = 225;
        Color bg = Color.DEEPPINK;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+120);
        mv.setY( yy);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX+120);
        mv1.setY( yy-120);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX);
        mv2.setY( yy-120);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX+120);
        mv3.setY( yy);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);

        return p;
    }
    protected Path tri2( )
    {
        double centerX = 225;
        Color bg = Color.DEEPSKYBLUE;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-120);
        mv.setY( yy);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX-120);
        mv1.setY( yy-120);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX);
        mv2.setY( yy-120);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX-120);
        mv3.setY( yy);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);

        return p;
    }
    protected Path tri3( )
    {
        double centerX = 225;
        Color bg = Color.DARKVIOLET;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-120);
        mv.setY( yy);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX-120);
        mv1.setY( yy+120);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX);
        mv2.setY( yy+120);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX-120);
        mv3.setY( yy);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);

        return p;
    }
    protected Path tri4( )
    {
        double centerX = 225;
        Color bg = Color.YELLOW;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+120);
        mv.setY( yy);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX+120);
        mv1.setY( yy+120);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX);
        mv2.setY( yy+120);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX+120);
        mv3.setY( yy);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);

        return p;
    }

    protected Path tri12( )
    {
        double centerX = 225;
        Color bg = Color.DEEPPINK;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+150);
        mv.setY( yy-50);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX+150);
        mv1.setY( yy-150);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX+50);
        mv2.setY( yy-150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX+150);
        mv3.setY( yy-50);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);

        return p;
    }
    protected Path tri22()
    {
        double centerX = 225;
        Color bg = Color.DEEPSKYBLUE;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-150);
        mv.setY( yy-50);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX-150);
        mv1.setY( yy-150);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX-50);
        mv2.setY( yy-150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX-150);
        mv3.setY( yy-50);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);

        return p;
    }
    protected Path tri32()
    {
        double centerX = 225;
        Color bg = Color.DARKVIOLET;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX-150);
        mv.setY( yy+50);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX-150);
        mv1.setY( yy+150);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX-50);
        mv2.setY( yy+150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX-150);
        mv3.setY( yy+50);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);

        return p;
    }
    protected Path tri42()
    {
        double centerX = 225;
        Color bg = Color.YELLOW;

        Path p = new Path();
        p.setFillRule(FillRule.EVEN_ODD);
        p.setFill(bg);
        p.setStroke(bg);

        MoveTo mv = new MoveTo();
        mv.setX(centerX+150);
        mv.setY( yy+50);

        LineTo mv1 = new LineTo();
        mv1.setX(centerX+150);
        mv1.setY( yy+150);

        LineTo mv2 = new LineTo();
        mv2.setX(centerX+50);
        mv2.setY( yy+150);

        LineTo mv3 = new LineTo();
        mv3.setX(centerX+150);
        mv3.setY( yy+50);

        p.getElements().add(mv);
        p.getElements().add(mv1);
        p.getElements().add(mv2);
        p.getElements().add(mv3);

        return p;
    }
}

class Obj1 extends Obstacle implements Serializable
{
    Obj1(double centerY)
    {
        creatednext = 0;
        objType = 1;
        this.yy = centerY;
        ring1 = arc1();
        ring2 = arc2();
        ring3 = arc3();
        ring4 = arc4();
        g1 = new Group(ring1,ring2,ring3,ring4);
        getChildren().addAll(g1);
        this.movement(g1);
    }

    public void movement(Group g)
    {
        RotateTransition rt = new RotateTransition(Duration.millis(40000),g);
        rt.setByAngle(3600);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }
}

class Obj2 extends Obstacle implements Serializable
{
    Obj2(double centerY)
    {
        creatednext = 0;
        objType = 2;
        this.yy = centerY;
        ring1 = rect1();
        ring2 = rect2();
        ring3 = rect3();
        ring4 = rect4();
        g1 = new Group(ring1,ring2,ring3,ring4);
        getChildren().addAll(g1);
        this.movement(g1);
    }

    public void movement(Group g)
    {
        RotateTransition rt = new RotateTransition(Duration.millis(40000),g);
        rt.setByAngle(3600);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }
}

class Obj3 extends Obstacle implements Serializable
{
    Obj3(double centerY)
    {
        creatednext = 0;
        objType = 3;
        this.yy = centerY;
        ring1 = hand1();
        ring2 = hand2();
        ring3 = hand3();
        ring4 = hand4();
        g1 = new Group(ring1,ring2,ring3,ring4);
        getChildren().addAll(g1);
        this.movement(g1);
    }

    public void movement(Group g)
    {
        RotateTransition rt = new RotateTransition(Duration.millis(40000),g);
        rt.setByAngle(3600);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }
}

class Obj4 extends Obstacle implements Serializable
{
    Obj4(double centerY)
    {
        creatednext = 0;
        objType = 4;
        this.yy = centerY;
        ring1 = tri1();
        ring2 = tri2();
        ring3 = tri3();
        ring4 = tri4();
        g1 = new Group(ring1,ring2,ring3,ring4);
        getChildren().addAll(g1);
        this.movement(g1);
    }

    public void movement(Group g)
    {
        RotateTransition rt = new RotateTransition(Duration.millis(40000),g);
        rt.setByAngle(3600);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }
}

class Obj5 extends Obstacle implements Serializable
{
    Obj5(double centerY)
    {
        creatednext = 0;
        objType = 5;
        this.yy = centerY;
        ring1 = tri12();
        ring2 = tri22();
        ring3 = tri32();
        ring4 = tri42();
        g1 = new Group(ring1,ring2,ring3,ring4);
        getChildren().addAll(g1);
        this.movement(g1);
    }

    public void movement(Group g)
    {
        RotateTransition rt = new RotateTransition(Duration.millis(40000),g);
        rt.setByAngle(3600);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }
}

class Obj6 extends Obstacle implements Serializable
{
    Obj6(double centerY)
    {
        creatednext = 0;
        objType = 6;
        this.yy = centerY;
        ring1 = rect12();
        ring2 = rect22();
        ring3 = rect32();
        ring4 = rect42();
        g1 = new Group(ring1,ring2,ring3,ring4);
        getChildren().addAll(g1);
        this.movement(g1);
    }

    public void movement(Group g)
    {
        RotateTransition rt = new RotateTransition(Duration.millis(40000),g);
        rt.setByAngle(3600);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }
}

class Obj7 extends Obstacle implements Serializable
{
    Obj7(double centerY)
    {
        creatednext = 0;
        objType = 7;
        this.yy = centerY;
        ring1 = arc12();
        ring2 = arc22();
        ring3 = arc32();
        ring4 = arc42();
        g1 = new Group(ring1,ring2,ring3,ring4);
        getChildren().addAll(g1);
        this.movement(g1);
    }

    public void movement(Group g)
    {
        RotateTransition rt = new RotateTransition(Duration.millis(40000),g);
        rt.setByAngle(3600);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }
}

class Obj8 extends Obstacle implements Serializable
{
    Obj8(double centerY)
    {
        creatednext = 0;
        objType = 8;
        this.yy = centerY;
        ring1 = hand12();
        ring2 = hand22();
        ring3 = hand32();
        ring4 = hand42();
        g1 = new Group(ring1,ring2,ring3,ring4);
        getChildren().addAll(g1);
        this.movement(g1);
    }

    public void movement(Group g)
    {
        RotateTransition rt = new RotateTransition(Duration.millis(40000),g);
        rt.setByAngle(3600);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }
}

class Star extends Parent
{
    protected Image star1 = new Image("file:///C:/Resources/1.png");
    protected ImageView s1 = new ImageView(star1);
    protected Circle circle = new Circle();
    protected int passed = 0;

    Star(double centerY)
    {
        s1.setTranslateX(205);
        s1.setTranslateY(centerY);
        s1.setFitHeight(40);
        s1.setFitWidth(40);
        s1.setEffect(new GaussianBlur(3));

        circle.setRadius(15);
        circle.setTranslateX(225);
        circle.setTranslateY(centerY+20);
        circle.setFill(Color.WHITE);
        circle.setVisible(false);

        getChildren().addAll(s1,circle);
        this.twinkle();
    }

    public void twinkle()
    {
        FadeTransition ft = new FadeTransition(Duration.millis(500),s1);

        ft.setFromValue(0);
        ft.setToValue(1);

        ft.setAutoReverse(true);
        s1.setVisible(true);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.play();
    }

    public void hide()
    {
        getChildren().remove(s1);
        getChildren().remove(circle);
    }
}

class ColourSwitcher extends Colours
{
    protected int nextColour = -1;
    protected Image switcher1 = new Image("file:///C:/Resources/2.png");
    protected ImageView sw1 = new ImageView(switcher1);
    protected Circle circle = new Circle();
    protected int switched = 0;

    ColourSwitcher(Ball b,double centerY)
    {
        randomColour(b);

        sw1.setTranslateX(205);
        sw1.setTranslateY(centerY);
        sw1.setFitHeight(40);
        sw1.setFitWidth(40);
        movement();

        circle.setRadius(15);
        circle.setTranslateX(225);
        circle.setTranslateY(centerY+20);
        circle.setFill(Color.WHITE);
        circle.setVisible(false);

        getChildren().addAll(sw1,circle);
    }

    protected void randomColour(Ball b)
    {
        while(nextColour==b.currColour || nextColour==-1)
        {
            nextColour = r.nextInt(4);
        }
    }

    public void movement()
    {
        RotateTransition rt = new RotateTransition(Duration.millis(150000),sw1);
        rt.setByAngle(72000);
        rt.setCycleCount(Animation.INDEFINITE);

        if (!rt.isAutoReverse()){
            rt.play();
        }
    }

    public void hide()
    {
        getChildren().remove(sw1);
        getChildren().remove(circle);
    }
}

public class Main extends Application
{
    int GameOver;
    public int CurrScore;
    public int fixedscore;
    MediaPlayer mp;
    MediaPlayer ded;
    MediaPlayer pnt;
    MediaPlayer swch;
    MediaPlayer heet;
    MediaPlayer revi;
    public static int[] sgames;
    private GameMenu igmenu;
    private GameMenu scoreDisplay;
    private GameMenu gameMenu;
    private GameMenu splash;
    public int theme;
    protected Timeline t1;
    protected Timeline t2;
    protected Timeline t3;
    protected Timeline t4;
    protected Timeline t5;
    ImageView goover;
    protected int paused = 0;
    protected int loop = 1;

    class Game
    {
        private long score = 0;
        private long savedscore1 = 0;
        private long savedscore2 = 0;
        private int rungame = 0;
        private int bonusgame = 0;
        protected Ball ball;
        ColourSwitcher cs1;
        Obstacle o1;
        Star s1;
        ColourSwitcher cs2;
        Obstacle o2;
        Star s2;
        ColourSwitcher cs3;
        Obstacle o3;
        Star s3;

        public void CollisionFunc()
        {
            t1.pause();
            t2.pause();
            t4.pause();
            if(ball.hitobstacle==0)
            {
                ball.hitobstacle = 1;
                MissionFailed(1);
                System.out.println("Hit Obstacle! Game Over!");
                GameOver = 1;
                goover.setVisible(true);

            }
        }

        Game(Stage stage,int scorevalue,int dta)
        {
            System.out.println("New Game Started!");
            CurrScore=scorevalue;
            fixedscore=scorevalue;
            GameOver = 0;
            paused = 0;
            score = scorevalue;
            loop = dta;

            Pane top = new Pane();
            top.setPrefSize(450, 800);
            Scene scene2 = new Scene(top);

            Image bgHS2 = new Image("file:///C:/Resources/hs2.png");
            if (theme>0){
                Random rand = new Random();
                int clr=rand.nextInt(5);
                if (clr==0){
                    bgHS2 = new Image("file:///C:/Resources/clr/1.png");
                }
                else if (clr==1){
                    bgHS2 = new Image("file:///C:/Resources/clr/2.png");
                }
                else if (clr==2){
                    bgHS2 = new Image("file:///C:/Resources/clr/3.png");
                }
                else if (clr==3){
                    bgHS2 = new Image("file:///C:/Resources/clr/4.png");
                }
                else {
                    bgHS2 = new Image("file:///C:/Resources/clr/5.png");
                }
            }
            ImageView imgvu = new ImageView(bgHS2);
            imgvu.setFitHeight(800);
            imgvu.setFitWidth(450);

            Image gover = new Image("file:///C:/Resources/gover.png");
            goover = new ImageView(gover);
            goover.setFitHeight(800);
            goover.setFitWidth(450);

            if(loop==1)
            {
                ball = new Ball(stage);
                cs1 = new ColourSwitcher(ball,550);
                o1 = new Obj1(280);
                s1 = new Star(260);
                cs2 = new ColourSwitcher(ball,-1000);
                o2 = new Obj2(-1000);
                s2 = new Star(-1000);
                cs3 = new ColourSwitcher(ball,-1000);
                o3 = new Obj3(-1000);
                s3 = new Star(-1000);
            }
            else if(loop==2)
            {
                ball = new Ball(stage);
                cs1 = new ColourSwitcher(ball,550);
                o1 = new Obj2(280);
                s1 = new Star(260);
                cs2 = new ColourSwitcher(ball,-1000);
                o2 = new Obj3(-1000);
                s2 = new Star(-1000);
                cs3 = new ColourSwitcher(ball,-1000);
                o3 = new Obj4(-1000);
                s3 = new Star(-1000);
            }
            else if(loop==3)
            {
                ball = new Ball(stage);
                cs1 = new ColourSwitcher(ball,550);
                o1 = new Obj3(280);
                s1 = new Star(260);
                cs2 = new ColourSwitcher(ball,-1000);
                o2 = new Obj4(-1000);
                s2 = new Star(-1000);
                cs3 = new ColourSwitcher(ball,-1000);
                o3 = new Obj5(-1000);
                s3 = new Star(-1000);
            }
            else if(loop==4)
            {
                ball = new Ball(stage);
                cs1 = new ColourSwitcher(ball,550);
                o1 = new Obj4(280);
                s1 = new Star(260);
                cs2 = new ColourSwitcher(ball,-1000);
                o2 = new Obj5(-1000);
                s2 = new Star(-1000);
                cs3 = new ColourSwitcher(ball,-1000);
                o3 = new Obj6(-1000);
                s3 = new Star(-1000);
            }
            else if(loop==5)
            {
                ball = new Ball(stage);
                cs1 = new ColourSwitcher(ball,550);
                o1 = new Obj5(280);
                s1 = new Star(260);
                cs2 = new ColourSwitcher(ball,-1000);
                o2 = new Obj6(-1000);
                s2 = new Star(-1000);
                cs3 = new ColourSwitcher(ball,-1000);
                o3 = new Obj7(-1000);
                s3 = new Star(-1000);
            }
            else if(loop==6)
            {
                ball = new Ball(stage);
                cs1 = new ColourSwitcher(ball,550);
                o1 = new Obj6(280);
                s1 = new Star(260);
                cs2 = new ColourSwitcher(ball,-1000);
                o2 = new Obj7(-1000);
                s2 = new Star(-1000);
                cs3 = new ColourSwitcher(ball,-1000);
                o3 = new Obj8(-1000);
                s3 = new Star(-1000);
            }
            else if(loop==7)
            {
                ball = new Ball(stage);
                cs1 = new ColourSwitcher(ball,550);
                o1 = new Obj7(280);
                s1 = new Star(260);
                cs2 = new ColourSwitcher(ball,-1000);
                o2 = new Obj8(-1000);
                s2 = new Star(-1000);
                cs3 = new ColourSwitcher(ball,-1000);
                o3 = new Obj1(-1000);
                s3 = new Star(-1000);
            }
            else if(loop==8)
            {
                ball = new Ball(stage);
                cs1 = new ColourSwitcher(ball,550);
                o1 = new Obj8(280);
                s1 = new Star(260);
                cs2 = new ColourSwitcher(ball,-1000);
                o2 = new Obj1(-1000);
                s2 = new Star(-1000);
                cs3 = new ColourSwitcher(ball,-1000);
                o3 = new Obj2(-1000);
                s3 = new Star(-1000);
            }

            goover.setVisible(false);
            ball.hitgrnd = 0;
            ball.hitobstacle = 0;
            igmenu = new GameMenu(stage,2);
            scoreDisplay = new GameMenu(stage,3);
            igmenu.setVisible(true);
            scoreDisplay.setVisible(true);
            String newscore = "SCORE : " + Long.toString(score);
            scoreDisplay.score.setText(newscore);
            top.getChildren().addAll(imgvu,ball,cs1,o1,s1,cs2,o2,s2,cs3,o3,s3,goover,igmenu,scoreDisplay);
            int view;
            if ((view=4)<6)
            {
                stage.setScene(scene2);
                stage.show();
            }

            // Scrolling
            t4 = new Timeline(new KeyFrame(Duration.millis(20),new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent etl)
                {
                    cs1.sw1.setLayoutY(cs1.sw1.getLayoutY()+3);
                    cs1.circle.setLayoutY(cs1.circle.getLayoutY()+3);
                    o1.g1.setLayoutY(o1.g1.getLayoutY()+3);
                    o1.yy += 3;
                    s1.circle.setLayoutY(s1.circle.getLayoutY()+3);
                    s1.s1.setLayoutY(s1.s1.getLayoutY()+3);

                    cs2.sw1.setLayoutY(cs2.sw1.getLayoutY()+3);
                    cs2.circle.setLayoutY(cs2.circle.getLayoutY()+3);
                    o2.g1.setLayoutY(o2.g1.getLayoutY()+3);
                    o2.yy += 3;
                    s2.circle.setLayoutY(s2.circle.getLayoutY()+3);
                    s2.s1.setLayoutY(s2.s1.getLayoutY()+3);

                    cs3.sw1.setLayoutY(cs3.sw1.getLayoutY()+3);
                    cs3.circle.setLayoutY(cs3.circle.getLayoutY()+3);
                    o3.g1.setLayoutY(o3.g1.getLayoutY()+3);
                    o3.yy += 3;
                    s3.circle.setLayoutY(s3.circle.getLayoutY()+3);
                    s3.s1.setLayoutY(s3.s1.getLayoutY()+3);
                }
            }));
            t4.setCycleCount(Timeline.INDEFINITE);
            t5 = new Timeline(new KeyFrame(Duration.millis(20),new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent etl)
                {
                    cs1.sw1.setLayoutY(cs1.sw1.getLayoutY()+5);
                    cs1.circle.setLayoutY(cs1.circle.getLayoutY()+5);
                    o1.g1.setLayoutY(o1.g1.getLayoutY()+5);
                    o1.yy += 5;
                    s1.circle.setLayoutY(s1.circle.getLayoutY()+5);
                    s1.s1.setLayoutY(s1.s1.getLayoutY()+5);

                    cs2.sw1.setLayoutY(cs2.sw1.getLayoutY()+5);
                    cs2.circle.setLayoutY(cs2.circle.getLayoutY()+5);
                    o2.g1.setLayoutY(o2.g1.getLayoutY()+5);
                    o2.yy += 5;
                    s2.circle.setLayoutY(s2.circle.getLayoutY()+5);
                    s2.s1.setLayoutY(s2.s1.getLayoutY()+5);

                    cs3.sw1.setLayoutY(cs3.sw1.getLayoutY()+5);
                    cs3.circle.setLayoutY(cs3.circle.getLayoutY()+5);
                    o3.g1.setLayoutY(o3.g1.getLayoutY()+5);
                    o3.yy += 5;
                    s3.circle.setLayoutY(s3.circle.getLayoutY()+5);
                    s3.s1.setLayoutY(s3.s1.getLayoutY()+5);
                }
            }));
            t5.setCycleCount(Timeline.INDEFINITE);

            // Ball Up
            t1 = new Timeline(new KeyFrame(Duration.millis(20),new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent etl)
                {
                    ball.circle.setLayoutY(ball.circle.getLayoutY()-5);
                    if(ball.circle.getLayoutY()<-300 && ball.circle.getLayoutY()>-500)
                        t4.play();
                    if(ball.circle.getLayoutY()<-500)
                        t5.play();

                    Shape sh3;
                    sh3 = Shape.intersect(ball.circle,o1.ring1);
                    Shape sh4;
                    sh4 = Shape.intersect(ball.circle,o1.ring2);
                    Shape sh5;
                    sh5 = Shape.intersect(ball.circle,o1.ring3);
                    Shape sh6;
                    sh6 = Shape.intersect(ball.circle,o1.ring4);

                    Shape sh7;
                    sh7 = Shape.intersect(ball.circle,o2.ring1);
                    Shape sh8;
                    sh8 = Shape.intersect(ball.circle,o2.ring2);
                    Shape sh9;
                    sh9 = Shape.intersect(ball.circle,o2.ring3);
                    Shape sh10;
                    sh10 = Shape.intersect(ball.circle,o2.ring4);

                    Shape sh11;
                    sh11 = Shape.intersect(ball.circle,o3.ring1);
                    Shape sh12;
                    sh12 = Shape.intersect(ball.circle,o3.ring2);
                    Shape sh13;
                    sh13 = Shape.intersect(ball.circle,o3.ring3);
                    Shape sh14;
                    sh14 = Shape.intersect(ball.circle,o3.ring4);

                    if(sh3.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPPINK){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh4.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPSKYBLUE){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh5.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DARKVIOLET){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh6.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.YELLOW){}
                        else
                        {CollisionFunc();}
                    }

                    else if(sh7.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPPINK){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh8.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPSKYBLUE){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh9.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DARKVIOLET){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh10.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.YELLOW){}
                        else
                        {CollisionFunc();}
                    }

                    else if(sh11.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPPINK){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh12.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPSKYBLUE){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh13.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DARKVIOLET){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh14.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.YELLOW){}
                        else
                        {CollisionFunc();}
                    }
                }
            }));
            t1.setCycleCount(Timeline.INDEFINITE);

            // Ball Down
            t2 = new Timeline(new KeyFrame(Duration.millis(20),new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent etl)
                {
                    t4.pause();
                    t5.pause();
                    ball.circle.setLayoutY(ball.circle.getLayoutY()+6);

                    Shape sh3;
                    sh3 = Shape.intersect(ball.circle,o1.ring1);
                    Shape sh4;
                    sh4 = Shape.intersect(ball.circle,o1.ring2);
                    Shape sh5;
                    sh5 = Shape.intersect(ball.circle,o1.ring3);
                    Shape sh6;
                    sh6 = Shape.intersect(ball.circle,o1.ring4);

                    Shape sh7;
                    sh7 = Shape.intersect(ball.circle,o2.ring1);
                    Shape sh8;
                    sh8 = Shape.intersect(ball.circle,o2.ring2);
                    Shape sh9;
                    sh9 = Shape.intersect(ball.circle,o2.ring3);
                    Shape sh10;
                    sh10 = Shape.intersect(ball.circle,o2.ring4);

                    Shape sh11;
                    sh11 = Shape.intersect(ball.circle,o3.ring1);
                    Shape sh12;
                    sh12 = Shape.intersect(ball.circle,o3.ring2);
                    Shape sh13;
                    sh13 = Shape.intersect(ball.circle,o3.ring3);
                    Shape sh14;
                    sh14 = Shape.intersect(ball.circle,o3.ring4);

                    if(sh3.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPPINK){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh4.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPSKYBLUE){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh5.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DARKVIOLET){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh6.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.YELLOW){}
                        else
                        {CollisionFunc();}
                    }

                    else if(sh7.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPPINK){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh8.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPSKYBLUE){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh9.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DARKVIOLET){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh10.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.YELLOW){}
                        else
                        {CollisionFunc();}
                    }

                    else if(sh11.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPPINK){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh12.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DEEPSKYBLUE){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh13.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.DARKVIOLET){}
                        else
                        {CollisionFunc();}
                    }
                    else if(sh14.getLayoutBounds().getWidth()!=-1)
                    {
                        if(ball.circle.getFill()==Color.YELLOW){}
                        else
                        {CollisionFunc();}
                    }
                }
            }));
            t2.setCycleCount(Timeline.INDEFINITE);

            // Collision
            t3 = new Timeline(new KeyFrame(Duration.millis(1),new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent etl)
                {
                    Shape sh1;
                    sh1 = Shape.intersect(ball.circle,cs1.circle);
                    Shape sh2;
                    sh2 = Shape.intersect(ball.circle,s1.circle);
                    Shape sh3;
                    sh3 = Shape.intersect(ball.circle,cs2.circle);
                    Shape sh4;
                    sh4 = Shape.intersect(ball.circle,s2.circle);
                    Shape sh5;
                    sh5 = Shape.intersect(ball.circle,cs3.circle);
                    Shape sh6;
                    sh6 = Shape.intersect(ball.circle,s3.circle);

                    if(sh1.getLayoutBounds().getWidth()!=-1)
                    {
                        if(cs1.switched==0)
                        {   change(1);
                            cs1.switched = 1;
                            cs1.hide();
                            ball.switchColour(cs1);
                            System.out.println("Colour Changed!");
                        }
                    }
                    else if(sh2.getLayoutBounds().getWidth()!=-1)
                    {
                        if(s1.passed==0)
                        {
                            yesyesyes(1);
                            System.out.println("Score +1!");
                            score++;
                            CurrScore=(int)score;
                            fixedscore=(int)score;
                            String newscore = "SCORE : " + Long.toString(score);
                            scoreDisplay.score.setText(newscore);
                            s1.hide();
                            s1.passed = 1;
                        }
                    }
                    else if(sh3.getLayoutBounds().getWidth()!=-1)
                    {
                        if(cs2.switched==0)
                        {   change(1);
                            cs2.switched = 1;
                            cs2.hide();
                            ball.switchColour(cs2);
                            System.out.println("Colour Changed!");
                        }
                    }
                    else if(sh4.getLayoutBounds().getWidth()!=-1)
                    {
                        if(s2.passed==0)
                        {
                            yesyesyes(1);
                            System.out.println("Score +1!");
                            score++;
                            CurrScore=(int)score;
                            fixedscore=(int)score;
                            String newscore = "SCORE : " + Long.toString(score);
                            scoreDisplay.score.setText(newscore);
                            s2.hide();
                            s2.passed = 1;
                        }
                    }
                    else if(sh5.getLayoutBounds().getWidth()!=-1)
                    {
                        if(cs3.switched==0)
                        {   change(1);
                            cs3.switched = 1;
                            cs3.hide();
                            ball.switchColour(cs3);
                            System.out.println("Colour Changed!");
                        }
                    }
                    else if(sh6.getLayoutBounds().getWidth()!=-1)
                    {
                        if(s3.passed==0)
                        {
                            yesyesyes(1);
                            System.out.println("Score +1!");
                            score++;
                            CurrScore=(int)score;
                            fixedscore=(int)score;
                            String newscore = "SCORE : " + Long.toString(score);
                            scoreDisplay.score.setText(newscore);
                            s3.hide();
                            s3.passed = 1;
                        }
                    }

                    else if(ball.circle.getLayoutY()>100)
                    {
                        t1.pause();
                        t2.pause();
                        t4.pause();
                        if(ball.hitgrnd==0)
                        {
                            ball.hitgrnd = 1;
                            MissionFailed(1);
                            System.out.println("Hit Ground! Game Over!");
                            GameOver = 1;
                            goover.setVisible(true);
                        }
                    }
                    else if(ball.hitobstacle==1)
                    {
                        t1.pause();
                        t2.pause();
                        t4.pause();
                    }

                    if(o1.yy>=280)
                    {
                        if(o1.creatednext==0)
                        {
                            switch(loop)
                            {
                                case 1:
                                    cs2 = new ColourSwitcher(ball,-10);
                                    o2 = new Obj2(-300);
                                    s2 = new Star(-320);
                                    loop++;
                                    break;
                                case 2:
                                    cs2 = new ColourSwitcher(ball,-10);
                                    o2 = new Obj3(-300);
                                    s2 = new Star(-320);
                                    loop++;
                                    break;
                                case 3:
                                    cs2 = new ColourSwitcher(ball,-10);
                                    o2 = new Obj4(-300);
                                    s2 = new Star(-320);
                                    loop++;
                                    break;
                                case 4:
                                    cs2 = new ColourSwitcher(ball,-10);
                                    o2 = new Obj5(-300);
                                    s2 = new Star(-320);
                                    loop++;
                                    break;
                                case 5:
                                    cs2 = new ColourSwitcher(ball,-10);
                                    o2 = new Obj6(-300);
                                    s2 = new Star(-320);
                                    loop++;
                                    break;
                                case 6:
                                    cs2 = new ColourSwitcher(ball,-10);
                                    o2 = new Obj7(-300);
                                    s2 = new Star(-320);
                                    loop++;
                                    break;
                                case 7:
                                    cs2 = new ColourSwitcher(ball,-10);
                                    o2 = new Obj8(-300);
                                    s2 = new Star(-320);
                                    loop++;
                                    break;
                                case 8:
                                    cs2 = new ColourSwitcher(ball,-10);
                                    o2 = new Obj1(-300);
                                    s2 = new Star(-320);
                                    loop = 1;
                                    break;
                            }

                            o1.creatednext = 1;
                            top.getChildren().removeAll(goover,igmenu,scoreDisplay);
                            top.getChildren().addAll(cs2,o2,s2,goover,igmenu,scoreDisplay);
                        }
                    }
                    if(o2.yy>=280)
                    {
                        if(o2.creatednext==0)
                        {
                            switch(loop)
                            {
                                case 1:
                                    cs3 = new ColourSwitcher(ball,-10);
                                    o3 = new Obj2(-300);
                                    s3 = new Star(-320);
                                    loop++;
                                    break;
                                case 2:
                                    cs3 = new ColourSwitcher(ball,-10);
                                    o3 = new Obj3(-300);
                                    s3 = new Star(-320);
                                    loop++;
                                    break;
                                case 3:
                                    cs3 = new ColourSwitcher(ball,-10);
                                    o3 = new Obj4(-300);
                                    s3 = new Star(-320);
                                    loop++;
                                    break;
                                case 4:
                                    cs3 = new ColourSwitcher(ball,-10);
                                    o3 = new Obj5(-300);
                                    s3 = new Star(-320);
                                    loop++;
                                    break;
                                case 5:
                                    cs3 = new ColourSwitcher(ball,-10);
                                    o3 = new Obj6(-300);
                                    s3 = new Star(-320);
                                    loop++;
                                    break;
                                case 6:
                                    cs3 = new ColourSwitcher(ball,-10);
                                    o3 = new Obj7(-300);
                                    s3 = new Star(-320);
                                    loop++;
                                    break;
                                case 7:
                                    cs3 = new ColourSwitcher(ball,-10);
                                    o3 = new Obj8(-300);
                                    s3 = new Star(-320);
                                    loop++;
                                    break;
                                case 8:
                                    cs3 = new ColourSwitcher(ball,-10);
                                    o3 = new Obj1(-300);
                                    s3 = new Star(-320);
                                    loop = 1;
                                    break;
                            }

                            o2.creatednext = 1;
                            top.getChildren().removeAll(goover,igmenu,scoreDisplay);
                            top.getChildren().addAll(cs3,o3,s3,goover,igmenu,scoreDisplay);
                        }
                    }
                    if(o3.yy>=280)
                    {
                        if(o3.creatednext==0)
                        {
                            switch(loop)
                            {
                                case 1:
                                    cs1 = new ColourSwitcher(ball,-10);
                                    o1 = new Obj2(-300);
                                    s1 = new Star(-320);
                                    loop++;
                                    break;
                                case 2:
                                    cs1 = new ColourSwitcher(ball,-10);
                                    o1 = new Obj3(-300);
                                    s1 = new Star(-320);
                                    loop++;
                                    break;
                                case 3:
                                    cs1 = new ColourSwitcher(ball,-10);
                                    o1 = new Obj4(-300);
                                    s1 = new Star(-320);
                                    loop++;
                                    break;
                                case 4:
                                    cs1 = new ColourSwitcher(ball,-10);
                                    o1 = new Obj5(-300);
                                    s1 = new Star(-320);
                                    loop++;
                                    break;
                                case 5:
                                    cs1 = new ColourSwitcher(ball,-10);
                                    o1 = new Obj6(-300);
                                    s1 = new Star(-320);
                                    loop++;
                                    break;
                                case 6:
                                    cs1 = new ColourSwitcher(ball,-10);
                                    o1 = new Obj7(-300);
                                    s1 = new Star(-320);
                                    loop++;
                                    break;
                                case 7:
                                    cs1 = new ColourSwitcher(ball,-10);
                                    o1 = new Obj8(-300);
                                    s1 = new Star(-320);
                                    loop++;
                                    break;
                                case 8:
                                    cs1 = new ColourSwitcher(ball,-10);
                                    o1 = new Obj1(-300);
                                    s1 = new Star(-320);
                                    loop = 1;
                                    break;
                            }

                            o3.creatednext = 1;
                            top.getChildren().removeAll(goover,igmenu,scoreDisplay);
                            top.getChildren().addAll(cs1,o1,s1,goover,igmenu,scoreDisplay);
                        }
                    }
                }
            }));
            t3.setCycleCount(Timeline.INDEFINITE);
            t3.play();

            scene2.setOnMousePressed(
                    event -> {
                        if(paused==0)
                        {
                            t2.pause();
                            t1.play();
                        }
                    }
            );
            scene2.setOnMouseReleased(
                    event -> {
                        if(paused==0)
                        {
                            t1.pause();
                            t2.play();
                        }
                    }
            );
        }
    }

    public void BGmusic(int status) {
        Media bgMusic;
        bgMusic = new Media("file:///C:/Resources/bg.mp3");
        mp =new MediaPlayer(bgMusic);
        mp.setCycleCount(Timeline.INDEFINITE);
        if (status==1)mp.play();
        if (status==0)mp.pause();

    }

    public void MissionFailed(int status) {
        Media bgMusic;
        bgMusic = new Media("file:///C:/Resources/ded.mp3");
        ded =new MediaPlayer(bgMusic);
        if (status==1)ded.play();
        if (status==0)ded.pause();
    }

    public void yesyesyes(int status) {
        Media bgMusic;
        bgMusic = new Media("file:///C:/Resources/pnt.mp3");
        pnt =new MediaPlayer(bgMusic);
        if (status==1)pnt.play();
        if (status==0)pnt.pause();
    }

    public void change(int status) {
        Media bgMusic;
        bgMusic = new Media("file:///C:/Resources/swch.mp3");
        swch =new MediaPlayer(bgMusic);
        if (status==1)swch.play();
        if (status==0)swch.pause();
    }

    public void hit(int status) {
        Media bgMusic;
        bgMusic = new Media("file:///C:/Resources/hit.mp3");
        heet =new MediaPlayer(bgMusic);
        if (status==1)heet.play();
        if (status==0)heet.pause();
    }

    public void reve(int status) {
        Media bgMusic;
        bgMusic = new Media("file:///C:/Resources/rev.mp3");
        revi =new MediaPlayer(bgMusic);
        if (status==1)revi.play();
        if (status==0)revi.pause();
    }



    @Override
    public void start(Stage stage) throws Exception
    {
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        stage.setTitle("ColorSwitch V_1.0.2");

        Pane top = new Pane();

        Label label1 = new Label("Play ColorSwitch");
        top.setPrefSize(450, 800);
        Scene scene1 = new Scene(top);
        scene1.setCursor(Cursor.CROSSHAIR);
        Image bgHS1 = new Image("file:///C:/Resources/hs1.png");
        ImageView imgvu = new ImageView(bgHS1);

        imgvu.setFitHeight(800);
        imgvu.setFitWidth(450);

        //ROTATING RINGS

        Image out = new Image("file:///C:/Resources/outer.png");
        ImageView outer = new ImageView(out);

        outer.setFitHeight(370);
        outer.setFitWidth(370);
        outer.setTranslateX(42);
        outer.setTranslateY(220);

        Image mid = new Image("file:///C:/Resources/MID.png");
        ImageView middle = new ImageView(mid);

        middle.setFitHeight(306);
        middle.setFitWidth(306);
        middle.setTranslateX(74.5);
        middle.setTranslateY(252);

        Image in = new Image("file:///C:/Resources/INN.png");
        ImageView inner = new ImageView(in);

        inner.setFitHeight(236);
        inner.setFitWidth(236);
        inner.setTranslateX(110);
        inner.setTranslateY(287);

        RotateTransition rto = new RotateTransition(Duration.millis(195500),outer);
        rto.setByAngle(36000);
        rto.setCycleCount(Animation.INDEFINITE);
        RotateTransition rtm = new RotateTransition(Duration.millis(195500),middle);
        rtm.setByAngle(-36000);
        rtm.setCycleCount(Animation.INDEFINITE);
        RotateTransition rti = new RotateTransition(Duration.millis(195500),inner);
        rti.setByAngle(36000);
        rti.setCycleCount(Animation.INDEFINITE);

        if (!rto.isAutoReverse()){
            rto.play();
            rtm.play();
            rti.play();
        }

        //END
        theme=-1;
        BGmusic(1);
        gameMenu = new GameMenu(stage,0);
        splash = new GameMenu(stage,1);
        gameMenu.setVisible(false);
        top.getChildren().addAll(imgvu,gameMenu,splash,outer,middle,inner);

        splash.setVisible(true);

        scene1.setOnKeyPressed(event -> {

            if (!gameMenu.isVisible()) {
                splash.setVisible(false);
                FadeTransition ft = new FadeTransition(Duration.seconds(1),gameMenu);
                int view;
                if ((view=4)<6){
                    ft.setFromValue(0);
                    ft.setToValue(1);
                }

                gameMenu.setVisible(true);
                ft.play();
            }
            else {}

        });



        stage.setScene(scene1);
        stage.centerOnScreen();

    }

    private class GameMenu extends Parent
    {
        private final int obst = 4;
        public Text score;

        public GameMenu(Stage stage, int type)
        {   setOnMouseClicked(event -> {hit(1); });
            if (type==0){
                VBox menu0 = new VBox(10);
                VBox menu1 = new VBox(10);
                VBox menuPlay = new VBox(10);
                VBox music = new VBox();
                menu0.setTranslateX(100);
                menu0.setTranslateY(630);

                menu1.setTranslateX(100);
                menu1.setTranslateY(630);

                menuPlay.setTranslateX(127);
                menuPlay.setTranslateY(306);

                music.setTranslateX(370);
                music.setTranslateY(10);
                int vision;
                TranslateTransition bb1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                TranslateTransition bb = new TranslateTransition(Duration.seconds(0.25), menu1);
                vision=2;
                menu1.setTranslateX(400);
                menuButtons btnnew = new menuButtons("NEW GAME", 0,1);
                btnnew.setOnMouseClicked(event -> {
//
                    setVisible(false);
                    new Game(stage,0,1);
                });

                menuButtons musicCtrl = new menuButtons("MUSIC", 0,65);
                musicCtrl.setOnMouseClicked(event -> {


                    if (mp.isMute()==true){
                        mp.setMute(false);
                    }
                    else{
                        mp.setMute(true);
                    }

                });

                menuButtons dsn = new menuButtons("THEME", 0,65);
                dsn.setOnMouseClicked(event -> {


                    if (theme>0){
                        theme=-1;
                    }
                    else{
                        theme=1;
                    }

                });
                menuButtons btnCircle = new menuButtons("alwaysPlay", 1,0);
                btnCircle.setOnMouseClicked(event -> {
//                FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
//                ft.setFromValue(1);
//                ft.setToValue(0);
//                ft.setOnFinished(evt -> setVisible(false));
//                ft.play();
                    new Game(stage,0,1);
                });
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);

                menuButtons btnLoad = new menuButtons("LOAD GAME", 0,1);
                btnLoad.setOnMouseClicked(event -> {
                    int view;
                    if ((view=4)<6){getChildren().add(menu1);}
//                BGmusic(0);
                    tt.setToX(menu0.getTranslateX() - 400);

                    tt.play();
                    tt1.setToX(menu0.getTranslateX());



                    tt1.play();
                    int textSize=tt.getCycleCount();
                    tt.setOnFinished(evt -> {
                        if ((view)<6){ getChildren().remove(menu0);}
                    });
                });

                menuButtons btnSave1 = null;
                menuButtons btnSave2 = null;

                menuButtons btnBack = new menuButtons("< BACK", 0,1);
                btnBack.setOnMouseClicked(event -> {
                    getChildren().add(menu0);

                    bb.setToX(menu1.getTranslateX() + 400);
                    bb.play();

                    bb1.setToX(menu1.getTranslateX());


                    bb1.play();
                    int VisibleParamCheck;
                    if (!isVisible()){VisibleParamCheck = 0;}
                    else
                    {VisibleParamCheck=1;}
                    bb.setOnFinished(evt -> {
                        getChildren().remove(menu1);
                    });
                });


                if (vision>1) {

                    btnSave1 = new menuButtons("SAVE1 : "+sgames[0]+" PTS", 0, 1);

                    btnSave2 = new menuButtons("SAVE2 : "+sgames[1]+" PTS", 0, 1);
                }
                menuButtons btnExit = new menuButtons("EXIT", 0,1);
                btnExit.setOnMouseClicked(event -> {
                    System.out.println("Exiting Game...");
                    System.exit(0);
                });
                btnSave1.setOnMouseClicked(event -> {
                    System.out.println("Loading Game...");

                    ObstacleForSerial objs = new ObstacleForSerial();
                    try {
                        FileInputStream f1 = new FileInputStream("C:/Resources/Save1Serial.txt");
                        ObjectInputStream input1 = new ObjectInputStream(f1);
                        objs = (ObstacleForSerial) input1.readObject();
                        input1.close();
                        f1.close();
                    }
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    int toSend = objs.data%8;
                    toSend++;
                    reve(1);
                    new Game(stage,sgames[0],toSend);

                });
                btnSave2.setOnMouseClicked(event -> {
                    System.out.println("Loading Game...");

                    ObstacleForSerial objs = new ObstacleForSerial();
                    try {
                        FileInputStream f1 = new FileInputStream("C:/Resources/Save2Serial.txt");
                        ObjectInputStream input1 = new ObjectInputStream(f1);
                        objs = (ObstacleForSerial) input1.readObject();
                        input1.close();
                        f1.close();
                    }
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    int toSend = objs.data%8;
                    toSend++;
                    reve(1);

                    new Game(stage,sgames[1],toSend);
                });

                menu0.getChildren().addAll(btnnew, btnLoad, btnExit);
                menu1.getChildren().addAll(btnBack, btnSave1, btnSave2);
                menuPlay.getChildren().addAll(btnCircle);
                music.getChildren().addAll(musicCtrl,dsn);

                Rectangle bg = new Rectangle(450, 800);
                bg.setFill(Color.GRAY);
                int VisibleParamCheck;
                if (!isVisible()){VisibleParamCheck = 0;}
                else
                {VisibleParamCheck=1;}
                bg.setOpacity(0.3);

                getChildren().addAll(bg, menu0, menuPlay,music);
            }

            else if (type==1){
                VBox start = new VBox(10);
                start.setTranslateX(112);
                start.setTranslateY(660);
                menuButtons starter = new menuButtons("PRESS ANY BUTTON",2,0);
                int view;
                if ((view=4)<6){
                    start.getChildren().addAll(starter);}
                getChildren().addAll(start);
            }

            else if (type==2){
                VBox menu0 = new VBox(10);
                VBox menu1 = new VBox(10);
                VBox pausebtn = new VBox();
                menu0.setTranslateX(100);
                menu0.setTranslateY(630);

                menu0.setVisible(false);
                pausebtn.setTranslateX(370);
                pausebtn.setTranslateY(10);
                menu1.setTranslateX(100);
                menu1.setTranslateY(630);

                menuButtons btnnew = new menuButtons("NEW GAME", 0,1);
                btnnew.setOnMouseClicked(event -> {
                    new Game(stage,0,1);
                });

                menuButtons btnres = new menuButtons("RESURRECT", 0,1);

                btnres.setOnMouseClicked(event -> {
                    reve(1);

                    if (CurrScore>=3){
                        new Game(stage, CurrScore-3,((fixedscore)%8)+1);
                    }
                    else {
                        System.out.println("--- NOT ENOUGH POINTS ---");
                    }
                });


                menuButtons btnsave = new menuButtons("SAVE", 0,65);
                btnsave.setOnMouseClicked(event -> {
                    sgames[1]=sgames[0];
                    sgames[0]=CurrScore;
                    File file = new File("C:/Resources/userSaves.ini");

                    try {
                        FileWriter fw = new FileWriter(file.getAbsoluteFile());
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(sgames[0] + "\n" + sgames[1]);
                        bw.close();

                        //Copy 1 in 2 - DeSerialization + Serialization
                        ObstacleForSerial objs = new ObstacleForSerial();
                        FileInputStream f = new FileInputStream("C:/Resources/Save1Serial.txt");
                        ObjectInputStream input = new ObjectInputStream(f);
                        objs = (ObstacleForSerial) input.readObject();
                        input.close();
                        f.close();
                        FileOutputStream f1 = new FileOutputStream("C:/Resources/Save2Serial.txt");
                        ObjectOutputStream output1 = new ObjectOutputStream(f1);
                        output1.writeObject(objs);
                        output1.close();
                        f1.close();

                        //Serialization
                        ObstacleForSerial obj = new ObstacleForSerial();
                        obj.data = fixedscore;
                        FileOutputStream f2 = new FileOutputStream("C:/Resources/Save1Serial.txt");
                        ObjectOutputStream output = new ObjectOutputStream(f2);
                        output.writeObject(obj);
                        output.close();
                        f2.close();
                        System.out.println("Serialized!");
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                });
                menuButtons dsn = new menuButtons("THEME", 0,65);
                dsn.setOnMouseClicked(event -> {


                    if (theme>0){
                        theme=-1;
                    }
                    else{
                        theme=1;
                    }

                });
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);

                TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);

                menuButtons btnLoad = new menuButtons("LOAD GAME", 0,1);
                btnLoad.setOnMouseClicked(event -> {
                    int view;
                    if ((view=4)<6){getChildren().add(menu1);}
//                BGmusic(0);
                    tt.setToX(menu0.getTranslateX() - 400);

                    tt.play();
                    tt1.setToX(menu0.getTranslateX());



                    tt1.play();
                    int textSize=tt.getCycleCount();
                    tt.setOnFinished(evt -> {
                        if ((view)<6){ getChildren().remove(menu0);}
                    });
                });
                menuButtons btnSave1 = null;
                menuButtons btnSave2 = null;
                TranslateTransition bb1 = new TranslateTransition(Duration.seconds(0.5), menu0);
                TranslateTransition bb = new TranslateTransition(Duration.seconds(0.25), menu1);
                menuButtons btnBack = new menuButtons("< BACK", 0,1);
                btnBack.setOnMouseClicked(event -> {
                    getChildren().add(menu0);

                    bb.setToX(menu1.getTranslateX() + 400);
                    bb.play();

                    bb1.setToX(menu1.getTranslateX());


                    bb1.play();
                    int VisibleParamCheck;
                    if (!isVisible()){VisibleParamCheck = 0;}
                    else
                    {VisibleParamCheck=1;}
                    bb.setOnFinished(evt -> {
                        getChildren().remove(menu1);
                    });
                });




                btnSave1 = new menuButtons("SAVE1 : "+sgames[0]+" PTS", 0, 1);

                btnSave2 = new menuButtons("SAVE2 : "+sgames[1]+" PTS", 0, 1);
                btnSave1.setOnMouseClicked(event -> {
                    System.out.println("Loading Game...");

                    ObstacleForSerial objs = new ObstacleForSerial();
                    try {
                        FileInputStream f1 = new FileInputStream("C:/Resources/Save1Serial.txt");
                        ObjectInputStream input1 = new ObjectInputStream(f1);
                        objs = (ObstacleForSerial) input1.readObject();
                        input1.close();
                        f1.close();
                    }
                    catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    int toSend = objs.data%8;
                    toSend++;
                    reve(1);

                    new Game(stage,sgames[0],toSend);
                });
                btnSave2.setOnMouseClicked(event -> {
                    System.out.println("Loading Game...");

                    ObstacleForSerial objs = new ObstacleForSerial();
                    try {
                        FileInputStream f1 = new FileInputStream("C:/Resources/Save2Serial.txt");
                        ObjectInputStream input1 = new ObjectInputStream(f1);
                        objs = (ObstacleForSerial) input1.readObject();
                        input1.close();
                        f1.close();
                    }
                    catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    int toSend = objs.data%8;
                    toSend++;
                    reve(1);

                    new Game(stage,sgames[1],toSend);
                });

                Rectangle bg = new Rectangle(450, 800);
                bg.setFill(Color.GRAY);
                menuButtons Pause = new menuButtons("PAUSE", 0,65);
                Pause.setOnMouseClicked(event -> {
                    if(paused==0)
                    {
                        FadeTransition ft = new FadeTransition(Duration.seconds(1),menu0);

                        ft.setFromValue(0);
                        ft.setToValue(1);
                        bg.setOpacity(0.9);
                        bg.setVisible(true);
                        menu0.setVisible(true);
                        ft.play();

                        paused = 1;
                        t1.pause();
                        t2.pause();
                        t3.pause();
                        t4.pause();
                    }
                    else if(paused==1)
                    {
                        FadeTransition ft = new FadeTransition(Duration.seconds(1),menu0);

                        ft.setFromValue(1);
                        ft.setToValue(0);
                        bg.setOpacity(0.9);
                        bg.setVisible(false);
                        menu0.setVisible(false);
                        ft.play();

                        paused = 0;
                        t3.play();
                    }
                });
                menuButtons musicCtrl = new menuButtons("MUSIC", 0,65);
                musicCtrl.setOnMouseClicked(event -> {


                    if (mp.isMute()==true){
                        mp.setMute(false);
                    }
                    else{
                        mp.setMute(true);
                    }

                });

                menuButtons btnExit = new menuButtons("EXIT", 0,1);
                btnExit.setOnMouseClicked(event -> {
                    System.out.println("Exiting Game...");
                    System.exit(0);
                });

                menu0.getChildren().addAll(btnnew,btnres, btnLoad, musicCtrl,  btnExit);
                pausebtn.getChildren().addAll(Pause, musicCtrl,btnsave,dsn);
                menu1.getChildren().addAll(btnBack, btnSave1, btnSave2);


                int VisibleParamCheck;
                if (!isVisible()){VisibleParamCheck = 0;}
                else
                {VisibleParamCheck=1;}
                bg.setOpacity(0.1);
//                bg.setVisible(false);
                getChildren().addAll( bg,menu0,pausebtn);
            }

            else if (type==3){
                VBox start = new VBox(10);
                start.setTranslateX(6);
                start.setTranslateY(10);
                score = new Text("SCORE : 0");
                score.setFill(Color.WHITE);
                score.setFont(new Font(20));
                int view;
                if ((view=4)<6){
                    start.getChildren().addAll(score);}
                getChildren().addAll(start);
            }
        }
    }

    private static class menuButtons extends StackPane
    {
        private Text text;

        public menuButtons(String name, int type, int width)
        {
            if (type==0)
            {
                text = new Text(name);
                text.setFont(text.getFont().font(20));
                text.setFill(Color.WHITE);
                Rectangle bg;
                if (width==1){

                    bg = new Rectangle(250, 30);
                    bg.setEffect(new GaussianBlur(3.5));
                    bg.setFill(Color.BLACK);
                    bg.setOpacity(0.6);
                }
                else
                {
                    bg = new Rectangle(width, 30);
                    bg.setEffect(new GaussianBlur(3.5));
                    bg.setFill(Color.BLACK);
                    bg.setOpacity(0.6);
                }
                int VisibleParamCheck;
                if (!isVisible()){VisibleParamCheck = 0;}
                else
                {VisibleParamCheck=1;}
                setAlignment(Pos.CENTER_LEFT);
                setRotate(-0.5);
                getChildren().addAll(bg, text);
                if (VisibleParamCheck<0){
                    if (!isVisible()){
                        VisibleParamCheck=4;
                    }
                }
                setOnMouseEntered(event -> {
                    bg.setTranslateX(10);
                    text.setTranslateX(10);
                    int bgTEXT=1;
                    while(bgTEXT<1){
                        bgTEXT=bgTEXT+(int)bg.getHeight();
                        for (int i = 0; i < bgTEXT; i++) {
                            bgTEXT=bgTEXT+1;
                        }
                    }
                    bg.setFill(Color.WHITE);
                    text.setFill(Color.BLACK);
                });
                int bgColor;
                bgColor=(int)bg.getArcHeight();
                setOnMouseExited(event -> {
                    bg.setTranslateX(0);

                    text.setTranslateX(0);
                    int bgTEXT=1;
                    while(bgTEXT<1){
                        bgTEXT=bgTEXT+(int)bg.getHeight();
                        for (int i = 0; i < bgTEXT; i++) {
                            bgTEXT=bgTEXT+1;
                        }
                    }
                    bg.setFill(Color.BLACK);
                    text.setFill(Color.WHITE);
                    bgTEXT=0;
                });

                DropShadow drop = new DropShadow(50, Color.WHITE);
                drop.setInput(new Glow());
                VisibleParamCheck=0;
                if (!isVisible()){VisibleParamCheck = 0;}
                else
                {VisibleParamCheck=1;}
                setOnMousePressed(event -> setEffect(drop));
                setOnMouseReleased(event -> setEffect(null));
            }
            else if (type==1){
                Circle bg = new Circle(100);
                bg.setOpacity(0.3);
                bg.setFill(Color.BLACK);
                bg.setEffect(new GaussianBlur(6));
                int bgColor;
                bgColor=(int)bg.getCenterX();
                setAlignment(Pos.CENTER);
                setRotate(-0.5);
                getChildren().addAll(bg);
                bgColor=(int)(bg.getCenterX()-bg.getCenterY());
                setOnMouseEntered(event -> {
                    bg.setTranslateX(1);

                    bg.setFill(Color.WHITE);
                });

                setOnMouseExited(event -> {
                    bg.setTranslateX(0);
                    bg.setFill(Color.BLACK);
                });

                DropShadow drop = new DropShadow(50, Color.WHITE);
                drop.setInput(new Glow());
                int VisibleParamCheck;
                if (!isVisible()){VisibleParamCheck = 0;}
                else
                {VisibleParamCheck=1;}
                setOnMousePressed(event -> setEffect(drop));
                setOnMouseReleased(event -> setEffect(null));
                setOnMousePressed(event -> setEffect(drop));
                setOnMouseReleased(event -> setEffect(null));
            }

            else if (type==2){
                text = new Text(name);
                text.setFont(text.getFont().font(24));
                text.setFill(Color.WHITE);



                setAlignment(Pos.BOTTOM_CENTER);
                setRotate(-0.5);
                getChildren().addAll( text);
                DropShadow drop = new DropShadow(50, Color.WHITE);
                drop.setInput(new Glow());
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        File file=new File("C:/Resources/userSaves.ini");
        Scanner SaveLoad = new Scanner(file);
        sgames=new int[2];
        sgames[0]=SaveLoad.nextInt();
        sgames[1]=SaveLoad.nextInt();
        System.out.println("Starting Game...");
        launch();
        System.out.println("Exiting Game...");
    }
}
