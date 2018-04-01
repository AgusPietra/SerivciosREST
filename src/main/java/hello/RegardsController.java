package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegardsController {

    private static final String template = "Hello, %s!";
    @RequestMapping(value="/regards",method=RequestMethod.POST)
    public Regards regards(@RequestParam(value="sender", defaultValue="Unknown") String sender) {
        return new Regards( String.format(template, sender));
    }
}
