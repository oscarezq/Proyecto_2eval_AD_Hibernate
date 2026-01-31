/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.PlayerRepository;
import dao.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import modelo.Player;
import modelo.Team;
import vista.Vista;

/**
 *
 * @author Óscar Ezquerro Sá
 */
public class Controller {

    private Vista v;
    private EntityManager em;
    private PlayerRepository pr;
    private TeamRepository tr;

    public Controller(Vista v) {
        this.v = v;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadDePersistencia");
        this.em = emf.createEntityManager();
        this.pr = new PlayerRepository(em);
        this.tr = new TeamRepository(em);
    }

    public void buscarJugadoresPorPosicion() {
        String posicion = "Gnome Lineman";
        List<Player> players = pr.buscarJugadoresPorPosicion(posicion);

        v.mostrar("\nMostrando jugadores que juegen en la posición " + posicion + ":");
        if (!players.isEmpty()) {
            for (Player player : players) {
                v.mostrar(player.toString());
            }
        } else {
            v.mostrar("No se ha encontrado ningun jugador");
        }
    }
    
    public void buscarEquiposPorRoster() {
        String roster = "Gnome";
        List<Team> teams = tr.buscarEquiposPorRoster(roster);
        
        v.mostrar("\nMostrando equipos que sean de la raza " + roster + ":");
        if(!teams.isEmpty()) {
            for (Team team : teams) {
                v.mostrar(team.toString());
            }
        } else {
            v.mostrar("No se ha encontrado ningun equipo");
        }
    }
    
    public void insertarEquipo() {
        Team team = new Team("Equipo Oscar", "Humano");
        
        v.mostrar("\n" + tr.insertarEquipo(team));
    }
    
    public void modificarEquipo() {
        v.mostrar("\n" + tr.modificarEquipo(1193898, "Ganadores FC"));
    }
    
    public void borrarEquipo() {
        v.mostrar("\n" + tr.borrarEquipoPorId(1193898));
    }
    
    public void insertarJugador() {
        Player player = new Player("Oscar Ezquerro", "Delantero pichichi");
        
        v.mostrar("\n" + pr.insertarJugador(player));
    }
    
    public void modificarJugador() {
        v.mostrar("\n" + pr.modificarJugador(17145072, "Robert Lewandowski"));
    }
    
    public void borrarJugador() {
        v.mostrar("\n" + pr.borrarJugadorPorId(17145072));
    }

}
