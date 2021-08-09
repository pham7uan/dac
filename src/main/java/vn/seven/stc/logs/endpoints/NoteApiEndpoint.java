package vn.seven.stc.logs.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import vn.seven.stc.logs.models.Note;
import vn.seven.stc.config.ApplicationProperties;
import vn.seven.stc.core.CrudApiEndpoint;
import vn.seven.stc.logs.services.NoteService;

@RestController
@RequestMapping("/api/notes")
@EnableConfigurationProperties(ApplicationProperties.class)
public class NoteApiEndpoint extends CrudApiEndpoint<Note,Long> {

    private static Logger logger = LoggerFactory.getLogger(NoteApiEndpoint.class);
    private NoteService noteService;

    @Autowired
    public NoteApiEndpoint(NoteService service) {
        super(service);
        this.noteService = service;
        this.baseUrl = "/api/notes";
    }

//    @RequestMapping(path = "/attach", method = RequestMethod.POST)
//    public void upload(@RequestParam("id") Long id,@RequestParam("file") MultipartFile[] files) throws IOException {
//        noteService.attachFile(id,files);
//    }

}
