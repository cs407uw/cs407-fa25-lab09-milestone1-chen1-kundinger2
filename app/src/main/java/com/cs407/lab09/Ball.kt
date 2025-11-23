package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }
        val newVelocityX = velocityX+0.5f*(accX+xAcc)*dT
        val newVelocityY = velocityY+0.5f*(accY+yAcc)*dT
        val lx = velocityX*dT+(1f/6f)*(dT*dT)*(3*accX+xAcc)
        val ly = velocityY*dT+(1f/6f)*(dT*dT)*(3*accY+yAcc)

        accX = xAcc
        accY = yAcc
        velocityX = newVelocityX
        velocityY = newVelocityY
        posX += lx
        posY += ly
        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        val rightBoundary = backgroundWidth - ballSize
        val bottomBoundary = backgroundHeight - ballSize
        if (posX < 0f) {
            accX = 0f
            velocityX = 0f
            posX = 0f
        } else if (posX > rightBoundary) {
            accX = 0f
            velocityX = 0f
            posX = rightBoundary
        }
        if (posY < 0f) {
            accY = 0f
            velocityY = 0f
            posY = 0f
        } else if (posY > bottomBoundary) {
            accY = 0f
            velocityY = 0f
            posY = bottomBoundary
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = (backgroundWidth - ballSize)/2f
        posY = (backgroundHeight - ballSize)/2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}