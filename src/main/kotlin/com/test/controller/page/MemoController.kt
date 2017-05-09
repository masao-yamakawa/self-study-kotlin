package com.test.controller.page

import com.sun.corba.se.impl.protocol.giopmsgheaders.RequestMessage
import org.springframework.ui.Model
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestMapping
import sun.security.x509.OIDMap.addAttribute
import java.util.ArrayList
import com.test.model.Memo
import org.springframework.web.bind.annotation.RequestAttribute


/**
 * Created by masao-yamakawa on 2017/05/09.
 */
@Controller
@RequestMapping ("memo")
class MemoController {

    @RequestMapping ("")
    fun get(model: Model) : String {
/*
        List<Map<String, Object>> items = new ArrayList<>();
        Map<String, Object> item = new HashMap<>();
        item.put("memo", "Empty Memo");
        item.put("author", "Empty Author");
        items.add(item);

        model.addAttribute("items", items);
        return "memo";
*/

        val items: MutableList<Map<String, Any>> = mutableListOf()
        var item: MutableMap<String, Any> = mutableMapOf()

        item.put("memo", "Empty Memo")
        item.put("author", "Empty Author")

        items.add(item)
        model.addAttribute("items", items)

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
        val items = ArrayList<Memo>()
        items.add(item)

        model.addAttribute("items", items)
        return "memo"
    }
}