package pro.zinoviev.springbootconfig.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.zinoviev.springbootconfig.entity.Droid;

@RestController
@RequestMapping("/droid")
public class DroidController {
    private final Droid droid;

    public DroidController(Droid droid){
        this.droid = droid;
    }

    @GetMapping
    Droid getDroid(){
        return this.droid;
    }
}
