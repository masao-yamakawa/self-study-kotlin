package com.test.controller.page

import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage
import org.springframework.ui.Model
import org.springframework.stereotype.Controller
import sun.security.x509.OIDMap.addAttribute
import java.util.ArrayList
import com.test.model.Memo
import com.test.service.MemoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


/**
 * Created by masao-yamakawa on 2017/05/09.
 */
@Controller
@RequestMapping ("memo")
class MemoController {
//    var memoService: MemoService

/*
    get() {
        return this.memoService
    }
    set(value) {
        this.memoService = value
    }
*/

//    @Autowired
//    constructor(memoService: MemoService){
//        this.memoService = memoService
//    }

//    constructor()

    @RequestMapping ("")
    fun get(model: Model) : String {
/*
        List<Memo> items = new ArrayList<>();
        items.add(getMemoService().join("Join Memo", "Join Author"));

        model.addAttribute("items", items);
        return "memo";
*/

        val items: MutableList<Memo> = mutableListOf()
//        items.add(memoService.join("empty memo", "empty author"))
        var item: Memo = Memo()
        item.memo = "empty memo"
        item.author = "empty author"

        items.add(item)

        model.addAttribute("items", items)
        return "memo"
    }

    @RequestMapping("param/{memo:[a-zA-Z0-9]+}")
    fun getParams(@PathVariable memo: String,
                  @RequestParam(required = false, defaultValue = "Default Author") author: String,
                  model: Model) : String {
        /*
        List<Memo> items = new ArrayList<>();
        Memo item = new Memo();
        item.setMemo(memo);
        item.setAuthor(author);
        items.add(item);

        model.addAttribute("items", items);
        return "memo";
        */

        var item = Memo()
        item.memo = memo
        item.author = author
        model.addAttribute("items", listOf(item))
        return "memo"
    }


    @RequestMapping (value = "", method = arrayOf(RequestMethod.POST))
    fun post(@ModelAttribute item: Memo, model: Model): String {
/*
        List<Memo> items = new ArrayList<>();
        items.add(item);

        model.addAttribute("items", items);
        return "memo";
*/
        model.addAttribute("items", listOf(item))
        return "memo"
    }
}