package com.developers.kotlintest

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FunSpec

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
class EmailValidationTest : FunSpec() {

    private val validMail = "amanjeetsingh150@gmail.com"

    init {
        test("Valid mail: ($validMail) should return true and pass the test.") {
            val emailValidator = EmailValidator()
            emailValidator.isValidEmail(validMail) shouldBe true
        }
    }
}