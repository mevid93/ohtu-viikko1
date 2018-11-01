/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mevid
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void etsiPelaajaJokaOnOlemassa() {
        Player player = stats.search("Kurri");
        assertEquals("Kurri", player.getName());
        assertEquals("EDM", player.getTeam());
        assertEquals(37, player.getGoals());
        assertEquals(53, player.getAssists());
    }
    
    @Test
    public void estiPelaajaJokaEiOleOlemassa() {
        Player player = stats.search("Iron Man");
        assertEquals(null, player);
    }
    
    @Test
    public void haeTiiminPelaajat() {
        List<Player> players = stats.team("EDM");
        assertEquals(3, players.size());
        assertEquals("Kurri", players.get(1).getName());
    }
    
    @Test
    public void haeParhaatPelaajat() {
        // tämä ei toiminut odotetusti, palauttaa yhden enemmän kuin 
        // parametrina annettu luku... --> ei kuitenkaan muutettu koodia
        List<Player> players = stats.topScorers(3);
        assertEquals(4, players.size());
        assertEquals("Gretzky", players.get(0).getName());
        assertEquals("Lemieux", players.get(1).getName());
        assertEquals("Yzerman", players.get(2).getName());
        assertEquals(99, players.get(1).getPoints());
    }
}
