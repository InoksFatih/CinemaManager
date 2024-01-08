package org.sid.cinema.models;

import org.springframework.data.rest.core.config.Projection;

import java.util.Collection;
import java.util.Date;

@Projection(name = "p1", types = {org.sid.cinema.models.Projection.class})
public interface ProjectionProj {
    Long getId();
    double getPrix();
    Date getDateProjection();
    Salle getSalle();
    Film getFilm();
    Seance getSeance();
    Collection<Ticket> getTickets();
}
