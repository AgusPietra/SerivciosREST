package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegardsController {

    @RequestMapping(value="/regards",method=RequestMethod.POST)
    public Regards regards() {
        return new Regards("Same to you!");
    }
}
