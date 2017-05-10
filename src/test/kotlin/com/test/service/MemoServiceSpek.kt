package com.test.service

import com.test.model.Memo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * Created by masao-yamakawa on 2017/05/10.
 */
class MemoServiceSpek : Spek ({
    val memoService = MemoService()

    describe("join 呼び出し時") {
        val memoValue = "memo"
        val authorValue = "author"
        val result = memoService.join("$memoValue", "$authorValue")

        it("Memo クラスが返却される") {
            Assert.assertTrue(result is Memo)
        }
        it("返却内容が正しい") {
            Assert.assertTrue(result.memo.equals(memoValue))
            Assert.assertTrue(result.author.equals(authorValue))
        }
    }

})