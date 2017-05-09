package com.test.service

import org.springframework.stereotype.Service
import com.test.model.Memo
import java.time.LocalDate

/**
 * Created by masao-yamakawa on 2017/05/09.
 */
@Service
class MemoService {
/*
    public Memo join(String memo, String author) {
        Memo item = new Memo();
        item.setMemo(memo);
        item.setAuthor(author);
        item.setCreated(new Date());

        return item;
    }
*/

    fun join(memo: String, author: String): Memo {
        var item: Memo = Memo()
        item.memo = memo
        item.author = author
        item.created = LocalDate.now()
        return item
    }

}