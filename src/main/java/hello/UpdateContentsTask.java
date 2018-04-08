package hello;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UpdateContentsTask {
    @Scheduled(fixedRate = 300)//Tiempo en el que actualizo el contenido de twitter
    public void UpdateContentsTime() {


    }
}
