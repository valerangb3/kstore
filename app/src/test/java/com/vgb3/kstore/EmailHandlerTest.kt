package com.vgb3.kstore

import com.vgb3.kstore.utils.EmailHandler
import org.junit.Assert.assertEquals
import org.junit.Test

class EmailHandlerTest {
    @Test
    fun `email test should return true`() {
        val testEmail = "vgb3@gmail.ru"
        val emailHandler = EmailHandler()
        val expected = true
        val actual = emailHandler.validate(testEmail)
        assertEquals(expected, actual)
    }
}