package org.sid.cinema.controllers;


import lombok.Data;
import lombok.ToString;
import org.sid.cinema.models.Film;
import org.sid.cinema.models.Ticket;
import org.sid.cinema.repo.FilmRepository;
import org.sid.cinema.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaController {

    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path="/imageFilm/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") long id) throws Exception{
        Film film = filmRepository.findById(id).get();
        String PhotoName = film.getPhoto();
        File file = new File(System.getProperty("user.home") + "/Desktop/Project De Cinema/backend/images/" + PhotoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payerTickets")
    @Transactional
    public List<Ticket> payerTickets(@RequestBody TicketForm ticketForm){
        List<Ticket> listTicket = new ArrayList<>();
        ticketForm.getTickets().forEach(idTicket -> {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setReserve(true);
            ticket.setCodePayement(ticketForm.getCodePayement());
            ticketRepository.save(ticket);
            listTicket.add(ticket);
        });
        return  listTicket;
    }

}
@Data
@ToString
class TicketForm {
    private String nomClient;
    private Integer codePayement;
    private List<Long> tickets = new ArrayList<>();
}