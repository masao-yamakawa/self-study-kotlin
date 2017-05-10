package com.test.controller.page

import com.test.model.Memo
import com.test.service.MemoService
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

/**
 * Created by masao-yamakawa on 2017/05/09.
 */
class MemoControllerSpek : Spek ({

    // beforeSpec的なイメージ
//    val service = MockMvcBuilders.standaloneSetup(MemoService()).build()
    val mvc = MockMvcBuilders.standaloneSetup(MemoController()).build()

    describe("/memoにGETでアクセスした時") {
        val result = mvc.perform(MockMvcRequestBuilders.get("/memo/"))

        it("HTTPステータスコードが200で返却される") {
            result.andExpect(MockMvcResultMatchers.status().isOk)
        }
    }

    describe("/memoにPOSTでアクセスした時") {
        val result = mvc.perform(
                MockMvcRequestBuilders
                        .post("/memo/")
                        .param("memo", "memo")
                        .param("author", "author")
        )

        it("HTTPステータスコードが200で返却される") {
            result.andExpect(MockMvcResultMatchers.status().isOk)
        }
        it ("期待通りの値がMemoオブジェクトに格納される") {
            Assert.assertNotNull(result.andReturn().modelAndView.modelMap["items"])

            val items = result.andReturn().modelAndView.modelMap["items"] as List<*>
            assert(items.size == 1)

            val item = items[0] as Memo
            assert(item.memo == "memo")
            assert(item.author == "author")
        }
    }

    describe("/memo/param にGETでアクセスした時") {
        on ("PathVariableの指定がなかった場合") {
            val result = mvc.perform(
                    MockMvcRequestBuilders
                            .get("/memo/param")).andReturn()
            it ("HTTPステータスコードが404で返却される") {
                Assert.assertEquals(404, result.response.status)
            }
        }
        on ("PathVariableに英数字以外が指定の場合") {
            val pathVariable = "value_123"
            val result = mvc.perform(
                    MockMvcRequestBuilders
                            .get("/memo/param/$pathVariable")).andReturn()
            it ("HTTPステータスコードが404で返却される") {
                Assert.assertEquals(404, result.response.status)
            }
        }
        on ("PathVariableに英数字指定の場合") {
            val pathVariable = "value123"
            val result = mvc.perform(
                    MockMvcRequestBuilders
                            .get("/memo/param/$pathVariable")).andReturn()
            it ("HTTPステータスコードが200で返却される") {
                Assert.assertEquals(200, result.response.status)
            }
            it ("pathVariable の内容が memo に格納される") {
                val items = result.modelAndView.modelMap["items"]!! as List<*>
                Assert.assertTrue(items.size == 1)

                val item = items[0] as Memo
                Assert.assertTrue(item.memo == pathVariable)
            }
        }
        on ("RequestParamにauthor指定なしの場合") {
            val memoValue = "memoValue"
            val result = mvc.perform(
                    MockMvcRequestBuilders
                            .get("/memo/param/$memoValue")).andReturn()
            it ("HTTPステータスコードが200で返却される") {
                Assert.assertEquals(200, result.response.status)
            }
            it ("authoe の内容に ’Default Author’ が格納される") {
                val items = result.modelAndView.modelMap["items"]!! as List<*>
                Assert.assertTrue(items.size == 1)

                val item = items[0] as Memo
                Assert.assertTrue(item.memo == memoValue)
                Assert.assertTrue(item.author == "Default Author")
            }
        }
        on ("RequestParamにauthor指定された場合") {
            val memoValue = "memoValue"
            val authorValue = "author1234"
            val result = mvc.perform(
                    MockMvcRequestBuilders
                            .get("/memo/param/$memoValue?author=$authorValue")).andReturn()
            it ("HTTPステータスコードが200で返却される") {
                Assert.assertEquals(200, result.response.status)
            }
            it ("author の内容に 指定された内容が格納される") {
                val items = result.modelAndView.modelMap["items"]!! as List<*>
                Assert.assertTrue(items.size == 1)

                val item = items[0] as Memo
                Assert.assertTrue(item.memo == "memoValue")
                Assert.assertTrue(item.author == authorValue)
            }
        }
    }

})
