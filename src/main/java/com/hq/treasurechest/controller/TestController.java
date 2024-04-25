package com.hq.treasurechest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Hi, the genuine idea2023";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> headTypeTest(@PathVariable Long id) {
        // TODO 如果id所对应数据存在则返回ok
        return ResponseEntity.ok().build();
        // TODO 否则返回notfound
    }
}
