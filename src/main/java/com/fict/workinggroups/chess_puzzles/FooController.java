package com.fict.workinggroups.chess_puzzles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FooController {

    @RequestMapping("/foo")
    public String fooMethod() {
        return "foo";
    }


}
