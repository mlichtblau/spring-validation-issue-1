package io.lichtblau.springvalidationdemo1;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @PostMapping("/{pathVariable}/test1")
    String test1(@PathVariable("pathVariable") String pathVariable, @Valid @RequestBody DemoTransport demoTransport) {
        return "SUCCESS";
    }

    @PostMapping("/{pathVariable}/test2")
    String test2(@NotNull @PathVariable("pathVariable") String pathVariable, @Valid @RequestBody DemoTransport demoTransport) {
        return "SUCESS";
    }

}
