package com.paladinzzz.game.screens.collision;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.paladinzzz.game.CrossplatformApp;
import com.paladinzzz.game.screens.GameScreen;
import com.paladinzzz.game.screens.LevelScreen;
import com.paladinzzz.game.screens.MenuScreen;
import com.paladinzzz.game.screens.worldobjects.finishObject;
import com.paladinzzz.game.screens.worldobjects.fluidKillable;
import com.paladinzzz.game.sprites.Ant;
import com.paladinzzz.game.sprites.Wurrumpie;
import com.paladinzzz.game.util.playerMemory;
import com.paladinzzz.game.util.scoreMethods;

//Deze klas registreert contact tussen twee fixtures in onze wereld
public class CollisionListener implements ContactListener {
    private CrossplatformApp game;

    public CollisionListener(CrossplatformApp game) {
        this.game = game;
    }

    @Override
    public void beginContact(Contact contact) {
        boolean isAmole = false;
        boolean isBmole = false;
        Fixture fixOne = contact.getFixtureA();
        Fixture fixTwo = contact.getFixtureB();
        Object udA = fixOne.getBody().getUserData();
        Object udB = fixTwo.getBody().getUserData();

        if (udA instanceof com.paladinzzz.game.sprites.Mole) {
            isAmole = true;
            isBmole = false;
        } else if (udB instanceof com.paladinzzz.game.sprites.Mole) {
            isBmole = true;
            isAmole = false;
        }

        if ((isAmole) && (udB instanceof fluidKillable)) {
            ((com.paladinzzz.game.sprites.Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof fluidKillable)) {
            ((com.paladinzzz.game.sprites.Mole) udB).killMole();
        }

        if ((isAmole) && (udB instanceof Ant)) {
            playerMemory.player.reducePoints();
            ((com.paladinzzz.game.sprites.Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof Ant)) {
            playerMemory.player.reducePoints();
            ((com.paladinzzz.game.sprites.Mole) udB).killMole();
        }

        if ((isAmole) && (udB instanceof Wurrumpie)) {
            playerMemory.player.addPoints();
            ((Wurrumpie) udB).killWurrumpie();
        } else if ((isBmole) && (udA instanceof Wurrumpie)) {
            playerMemory.player.addPoints();
            ((Wurrumpie) udA).killWurrumpie();
        }



        //Finish line
        if ((isAmole) && (udB instanceof finishObject)) {
            // als level == 2
            if (playerMemory.player.WorldData.getCurrentWorld() == 1) {
                if (playerMemory.player.LevelData.getCurrentLevel() == 2) {
                    playerMemory.player.levelOneDone = true;
                    // reset player score, return naar LevelScreen
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.setPlayerScore(0);
                            playerMemory.player.LevelData.setCurrentLevel(1);
                            game.setScreen(new LevelScreen(game));
                        }
                    });
                } else {
                    // anders ga je naar het volgende level
                    playerMemory.player.LevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            game.setScreen(new GameScreen(game));
                        }
                    });
                }
            } else if (playerMemory.player.WorldData.getCurrentWorld() == 2) {
                if (playerMemory.player.LevelData.getCurrentLevel() == 2) {
                    playerMemory.player.levelOneDone = true;
                    // reset player score, return naar LevelScreen
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.setPlayerScore(0);
                            playerMemory.player.LevelData.setCurrentLevel(1);
                            game.setScreen(new LevelScreen(game));
                        }
                    });
                } else {
                    // anders ga je naar het volgende level
                    playerMemory.player.LevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            game.setScreen(new GameScreen(game));
                        }
                    });
                }
            } else {
                if (playerMemory.player.LevelData.getCurrentLevel() == 2) {
                    playerMemory.player.levelOneDone = true;
                    // reset player score, return naar LevelScreen
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.setPlayerScore(0);
                            playerMemory.player.LevelData.setCurrentLevel(1);
                            game.setScreen(new LevelScreen(game));
                        }
                    });
                } else {
                    // anders ga je naar het volgende level
                    playerMemory.player.LevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            game.setScreen(new GameScreen(game));
                        }
                    });
                }
            }

        } else if ((isBmole) && (udA instanceof finishObject)) {
            // als level == 2
            if (playerMemory.player.WorldData.getCurrentWorld() == 1) {
                if (playerMemory.player.LevelData.getCurrentLevel() == 2) {
                    playerMemory.player.levelOneDone = true;
                    // reset player score, return naar LevelScreen
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.setPlayerScore(0);
                            playerMemory.player.LevelData.setCurrentLevel(1);
                            MenuScreen.musicHandler.stopMusic();
                            MenuScreen.musicHandler.setMusic("Music/Main_Menu_Theme.ogg");
                            MenuScreen.musicHandler.playMusic();
                            game.setScreen(new LevelScreen(game));
                        }
                    });
                } else {
                    // anders ga je naar het volgende level
                    playerMemory.player.LevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            game.setScreen(new GameScreen(game));
                        }
                    });
                }
            } else if (playerMemory.player.WorldData.getCurrentWorld() == 2) {
                if (playerMemory.player.LevelData.getCurrentLevel() == 2) {
                    playerMemory.player.levelOneDone = true;
                    // reset player score, return naar LevelScreen
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.setPlayerScore(0);
                            playerMemory.player.LevelData.setCurrentLevel(1);
                            game.setScreen(new LevelScreen(game));
                        }
                    });
                } else {
                    // anders ga je naar het volgende level
                    playerMemory.player.LevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            game.setScreen(new GameScreen(game));
                        }
                    });
                }
            } else {
                if (playerMemory.player.LevelData.getCurrentLevel() == 2) {
                    playerMemory.player.levelOneDone = true;
                    // reset player score, return naar LevelScreen
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            scoreMethods.score();
                            playerMemory.player.setPlayerScore(0);
                            playerMemory.player.LevelData.setCurrentLevel(1);
                            game.setScreen(new LevelScreen(game));
                        }
                    });
                } else {
                    // anders ga je naar het volgende level
                    playerMemory.player.LevelData.addLevel();
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            game.setScreen(new GameScreen(game));
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
