package com.paladinzzz.game.screens.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.database.JSONfunctions;
import com.paladinzzz.game.screens.GameScreen;
import com.paladinzzz.game.screens.LevelScreen;
import com.paladinzzz.game.screens.worldobjects.finishObject;
import com.paladinzzz.game.screens.worldobjects.fluidKillable;
import com.paladinzzz.game.sprites.Ant;
import com.paladinzzz.game.sprites.Mole;
import com.paladinzzz.game.sprites.Wurrumpie;
import com.paladinzzz.game.util.TempMS;
import com.paladinzzz.game.util.playerMemory;
import com.paladinzzz.game.util.scoreMethods;

//Deze klas registreert contact tussen twee fixtures in onze wereld

public class CollisionListener implements ContactListener {
    private CrossplatformApp game;
    private JSONfunctions json = new JSONfunctions();
    private TempMS tempMS;

    public CollisionListener(CrossplatformApp game, TempMS tempMS) {
        this.game = game;
        this.tempMS = tempMS;
    }

    @Override
    public void beginContact(final Contact contact) {
        //Eerst moeten we uitzoeken of de mol deel naamt aan botsing
        boolean isAmole = false;
        boolean isBmole = false;
        Fixture fixOne = contact.getFixtureA();
        Fixture fixTwo = contact.getFixtureB();
        Object udA = fixOne.getBody().getUserData();
        Object udB = fixTwo.getBody().getUserData();

        if (udA instanceof Mole) {
            isAmole = true;
            isBmole = false;
        } else if (udB instanceof Mole) {
            isBmole = true;
            isAmole = false;
        }

        //Vervolgens checken we of de mol gedood wordt door e.g. fluids en Ants
        if ((isAmole) && (udB instanceof fluidKillable)) {
            ((Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof fluidKillable)) {
            ((Mole) udB).killMole();
        }

        if ((isAmole) && (udB instanceof Ant)) {
            playerMemory.player.reducePoints();
            ((Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof Ant)) {
            playerMemory.player.reducePoints();
            ((Mole) udB).killMole();
        }

        //Verder checken we natuurlijk of de mol een worm eet en dus punten scoort
        if ((isAmole) && (udB instanceof Wurrumpie)) {
            playerMemory.player.addPoints();
            ((Wurrumpie) udB).killWurrumpie();
        } else if ((isBmole) && (udA instanceof Wurrumpie)) {
            playerMemory.player.addPoints();
            ((Wurrumpie) udA).killWurrumpie();
        }

        //En als laatste de finish:
        if (((isAmole) && (udB instanceof finishObject)) || (isBmole) && (udA instanceof finishObject)) {
            if (playerMemory.player.worldAndLevelData.getCurrentWorld() == 1) {
                if (playerMemory.player.worldAndLevelData.getCurrentLevel() == 2) {
                    playerMemory.player.levelOneDone = true;
                    // reset player score, return naar LevelScreen.
                    json.sethaslevel(playerMemory.player.getName(), "haslevel1", 1);
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.resetScore();
                            playerMemory.player.worldAndLevelData.setCurrentLevel(1);
                            game.setScreen(new LevelScreen(game, tempMS));
                        }
                    });
                }
                else {
                    playerMemory.player.worldAndLevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            playerMemory.player.worldAndLevelData.setCurrentLevel(2);
                            game.setScreen(new GameScreen(game, tempMS));
                        }
                    });
                }
            }
            else if (playerMemory.player.worldAndLevelData.getCurrentWorld() == 2) {
                if (playerMemory.player.worldAndLevelData.getCurrentLevel() == 2) {
                    playerMemory.player.levelTwoDone = true;
                    json.sethaslevel(playerMemory.player.getName(), "haslevel2", 1);
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.setPlayerScore(0);
                            playerMemory.player.worldAndLevelData.setCurrentLevel(1);
                            game.setScreen(new LevelScreen(game, tempMS));
                        }
                    });
                } else {
                    playerMemory.player.worldAndLevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            playerMemory.player.worldAndLevelData.setCurrentLevel(2);
                            game.setScreen(new GameScreen(game, tempMS));
                        }
                    });
                }
            }
            else if (playerMemory.player.worldAndLevelData.getCurrentWorld() == 3) {
                if (playerMemory.player.worldAndLevelData.getCurrentLevel() == 2) {
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.setPlayerScore(0);
                            playerMemory.player.worldAndLevelData.setCurrentLevel(1);
                            game.setScreen(new LevelScreen(game, tempMS));
                        }
                    });
                } else {
                    playerMemory.player.worldAndLevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            playerMemory.player.worldAndLevelData.setCurrentLevel(2);
                            game.setScreen(new GameScreen(game, tempMS));
                        }
                    });
                }
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
