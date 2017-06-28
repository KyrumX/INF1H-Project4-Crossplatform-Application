package com.paladinzzz.game.screens.collision;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.paladinzzz.game.screens.worldobjects.bounceObject;
import com.paladinzzz.game.screens.worldobjects.finishObject;
import com.paladinzzz.game.screens.worldobjects.fluidKillable;
import com.paladinzzz.game.screens.worldobjects.groundObject;
import com.paladinzzz.game.sprites.Ant;
import com.paladinzzz.game.sprites.Mole;
import com.paladinzzz.game.sprites.Wurrumpie;

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

        if (udA instanceof Mole) {
            isAmole = true;
            isBmole = false;
        } else if (udB instanceof Mole) {
            isBmole = true;
            isAmole = false;
        }

        if ((isAmole) && (udB instanceof fluidKillable)) {
            ((Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof fluidKillable)) {
            ((Mole) udB).killMole();
        }

        if ((isAmole) && (udB instanceof Ant)) {
            ((Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof Ant)) {
            ((Mole) udB).killMole();
        }

        if ((isAmole) && (udB instanceof Wurrumpie)) {
            ((Wurrumpie) udB).killWurrumpie();
        } else if ((isBmole) && (udA instanceof Wurrumpie)) {
            ((Wurrumpie) udA).killWurrumpie();
            }

        //Finish line
        if ((isAmole) && (udB instanceof finishObject)) {
            ((Mole) udA).killMole();
        } else if ((isBmole) && (udA instanceof finishObject)) {
            ((Mole) udB).killMole();
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
