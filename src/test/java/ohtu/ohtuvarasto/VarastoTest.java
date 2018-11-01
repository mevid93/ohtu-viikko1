package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void varastossaEiTarpeeksiTilaa(){
        varasto.lisaaVarastoon(11);
        
        // vastastossa pitäisi olla tavaraa 10, kun lisättiin 11
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanEnemmänKuinTavaraaOn(){
        varasto.lisaaVarastoon(8);
        
        // kun otetaan enemmän kuin mitä varastossa on tavaraa, tulisi 
        // metodin palauttaa kaikki mitä varastossa on 
        assertEquals(8, varasto.otaVarastosta(10), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanNegatiivinenMaaraTavaraa() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(-2);
        
        // negatiivinen lisäys ei muuta varaston tavara määrää
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanNegatiivinenMaaraTavaraa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-2);
        
        // negatiivinen otto ei muuta varaston tavaramäärää
        assertEquals(0.0, varasto.otaVarastosta(-2), vertailuTarkkuus);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoNegatiivisellaTilavuudella() {
        Varasto varasto1 = new Varasto(-2);
        
        // kun luodaan varasto, jonka tilavuus <= 0, niin varaston tilavuus on 0
        assertEquals(0.0, varasto1.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoesitysToimii() {
        varasto.lisaaVarastoon(8);
        String mjono = "saldo = 8.0, vielä tilaa 2.0";
        assertEquals(mjono, varasto.toString());
    }
    
    @Test
    public void tarkempiKonstruktoriValideillaArvoilla() {
        Varasto varasto1 = new Varasto(10, 8);
        
        assertEquals(10, varasto1.getTilavuus(), vertailuTarkkuus);
        assertEquals(8, varasto1.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tarkempiKonstruktoriNegatiivisillaArvoilla() {
        Varasto varasto1 = new Varasto(-8, -8);
        assertEquals(0, varasto1.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto1.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tarkempiKonstruktoriPienemmallaTilavuudella() {
        Varasto varasto1 = new Varasto(7, 8);
        assertEquals(7, varasto1.getTilavuus(), vertailuTarkkuus);
        assertEquals(7, varasto1.getSaldo(), vertailuTarkkuus);
    }
}