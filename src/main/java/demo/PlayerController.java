package demo;

import com.sun.tracing.dtrace.ModuleAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.print.Book;
import java.util.List;

/**
 * Created by Marion on 04/06/15.
 */
@Controller
@RequestMapping("/reading")
public class PlayerController {

    PlayerRepository playerRepository;
    @Autowired
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(
            @PathVariable("reader") String reader,
            Model model) {
        List<Player> readingList =
                playerRepository.findAll();
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }
    @RequestMapping(value="/{reader}", method=RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Player book) {
        book.setFirstName(reader);
        System.out.println("Check "+book);
        playerRepository.save(book);
        return "redirect:/reading/{reader}";
    }

    @ModelAttribute("books")
    public List<Player> pop(){
        return playerRepository.findAll();
    }
}
