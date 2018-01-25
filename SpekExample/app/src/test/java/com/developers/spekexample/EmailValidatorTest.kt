package com.developers.spekexample

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertTrue
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.junit.Assert.assertFalse

/**
 * Created by Amanjeet Singh on 25/1/18.
 */
@RunWith(JUnitPlatform::class)
class EmailValidatorTest : Spek({

    given("dummy Email for checking our Validator") {

        val validator = EmailValidator()
        val validMail = "amanjeetsingh150@gmail.com"
        val invalidMail = "1223a"
        val invalidMailTwo = "name@email..com"
        val validMailTwo = "name@email.co.uk"

        on("A valid Email ($validMail)") {
            it("should return valid") {
                assertTrue(validator.isValidEmail(validMail))
            }
        }

        on("A valid Email ($validMailTwo)") {
            it("should return valid") {
                assertTrue(validator.isValidEmail(validMailTwo))
            }
        }

        on("A invalid Email ($invalidMail)") {
            it("should have invalid") {
                assertFalse(validator.isValidEmail(invalidMail))
            }
        }

        on("Second invalid mail ($invalidMailTwo)") {
            it("should have invalid") {
                assertFalse(validator.isValidEmail(invalidMailTwo))
            }
        }

    }
})