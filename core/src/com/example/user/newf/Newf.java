package com.example.user.newf;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;


import java.util.Random;

import sun.rmi.runtime.Log;

public class Newf extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Texture birds[];
    Texture p1, p2, ga;
    BitmapFont f;
    int flap = 0;
    float birdy = 0;
    float v = 0;
    int gamestate = 0;
    float gr = 2;
    float g = 400;
    int nv = 4;
    Random r;
    float tu[] = new float[nv];
    float tux[] = new float[nv];
    float dp, tv = 4;
    Circle c;
    int score = 0;
    int tube = 0;

    //ShapeRenderer s;
   Rectangle rt[];
    Rectangle rb[];

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("bg.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");
        ga = new Texture("gg.jpg");

        p1 = new Texture("toptube.png");
        p2 = new Texture("bottomtube.png");
        rt = new Rectangle[nv];
        rb = new Rectangle[nv];
        dp = Gdx.graphics.getWidth() * 7 / 10;
        r = new Random();
        c = new Circle();
        f = new BitmapFont();
        f.setColor(Color.RED);
        f.getData().setScale(10);


        //s=new ShapeRenderer();


    gamestart();


    }
    public void gamestart()
    {
        birdy = Gdx.graphics.getHeight() / 2 - birds[0].getHeight() / 2;
        for (int i = 0; i < nv; i++) {
            tu[i] = (r.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - g - 200);
            tux[i] = Gdx.graphics.getWidth() / 2 - p1.getWidth() / 2 + i * dp + Gdx.graphics.getWidth();
            rt[i] = new Rectangle();
            rb[i] = new Rectangle();


        }
    }
    @Override
    public void render () {
        batch.begin();
        batch.draw(img,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        if(gamestate==1)
        {

            if(tux[tube]<Gdx.graphics.getWidth()*7/10)
            {
                score++;
                Gdx.app.log("sc", String.valueOf(score));
                if(tube<nv-1)
                {
                    tube++;
                }
                else
                {
                    tube=0;
                }

            }
            if( Gdx.input.justTouched())
            {
                v=-25;

            }
         for(int i=0;i<nv;i++)
         {
            if(tux[i]<-p1.getWidth())
            {
                tu[i]=(r.nextFloat()-0.5f)*(Gdx.graphics.getWidth()-g-200);
            }
            else
            {
                tux[i]=tux[i]-tv;
            }
             batch.draw(p1, tux[i], Gdx.graphics.getHeight() / 2 + g / 2 + tu[i]);
             batch.draw(p2, tux[i], Gdx.graphics.getHeight() / 2 - p1.getHeight() - g / 2 + tu[i]);
             rt[i]=new Rectangle(tux[i],Gdx.graphics.getHeight() / 2 + g / 2 + tu[i],p1.getWidth(),p1.getHeight());
             rb[i]=new Rectangle(tux[i],Gdx.graphics.getHeight() / 2 - p2.getHeight() - g/2+tu[i],p2.getWidth(),p2.getHeight());
         }
            if(birdy>0) {
                v = v + gr;
                birdy -= v;
            }
            else {
                gamestate=2;
            }

        }
        else if(gamestate==0)
        {
            if( Gdx.input.justTouched())
            {
                gamestate=1;
            }
        }

        else if(gamestate==2) {
            batch.draw(ga, Gdx.graphics.getWidth() / 2 - ga.getWidth() / 2, Gdx.graphics.getHeight() / 2 - ga.getHeight() / 2);
            if (Gdx.input.justTouched()) {

                gamestate = 1;
                score = 0;
                tube = 0;
                v = 0;
                gamestart();

            }
        }
            if(flap==0)
            {
                flap=1;
            }
            else
            {
                flap=0;
            }





            batch.draw(birds[flap],Gdx.graphics.getWidth()/2-birds[flap].getWidth()/2,birdy);
            f.draw(batch, String.valueOf(score),70,140);

            c.set(Gdx.graphics.getWidth()/2, birds[flap].getHeight()/2+ birdy,birds[flap].getWidth()/2);

            //s.begin(ShapeRenderer.ShapeType.Filled);
            //s.setColor(Color.RED);
            //s.circle(c.x,c.y,c.radius);
            for(int i=0;i<nv;i++)
            {
                // s.rect(tux[i],Gdx.graphics.getHeight() / 2 + g / 2 + tu[i],p1.getWidth(),p1.getHeight());
                //s.rect(tux[i],Gdx.graphics.getHeight() / 2 - p2.getHeight() - g / 2+tu[i],p2.getWidth(),p2.getHeight());
                if(Intersector.overlaps(c,rt[i])||Intersector.overlaps(c,rb[i]))
                {
                    gamestate=2;
                }
            }

        batch.end();
        }


        // s.end();




}

