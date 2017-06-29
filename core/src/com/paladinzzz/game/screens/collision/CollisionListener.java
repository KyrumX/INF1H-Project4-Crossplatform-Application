package com.paladinzzz.game.screens.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.paladinzzz.game.screens.worldobjects.finishObject;
import com.paladinzzz.game.screens.worldobjects.fluidKillable;
import com.paladinzzz.game.sprites.Ant;

//Deze klas registreert contact tussen twee fixtures in onze wereld
public class CollisionListener implements ContactListener {
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
            ((com.paladinzzz.game.sprites.Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof Ant)) {
            ((com.paladinzzz.game.sprites.Mole) udB).killMole();
        }

        if ((isAmole) && (udB instanceof com.paladinzzz.game.sprites.Wurrumpie)) {
            ((com.paladinzzz.game.sprites.Wurrumpie) udB).killWurrumpie();
        } else if ((isBmole) && (udA instanceof com.paladinzzz.game.sprites.Wurrumpie)) {
            ((com.paladinzzz.game.sprites.Wurrumpie) udA).killWurrumpie();
            }

        //Finish line
        if ((isAmole) && (udB instanceof finishObject)) {
            ((com.paladinzzz.game.sprites.Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof finishObject)) {
            ((com.paladinzzz.game.sprites.Mole) udB).killMole();
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
